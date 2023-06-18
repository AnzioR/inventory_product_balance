package com.ipb.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ipb.domain.Message;
import com.ipb.domain.Product;
import com.ipb.domain.ProductInfo;
import com.ipb.domain.StoreProduct;
import com.ipb.domain.*;
import com.ipb.frame.MyService;
import com.ipb.mapper.EmitterMapper;
import com.ipb.mapper.ProductInfoMapper;
import com.ipb.mapper.StoreProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import reactor.core.publisher.Flux;


import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import java.io.IOException;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class NotificationService {

  @Autowired
  StoreService storeService;

  @Autowired
  OrdersCartService ordersCartService;

  private final StoreProductMapper storeProductMapper;
  private final ProductInfoMapper productInfoMapper;

  public NotificationService(StoreProductMapper storeProductMapper, ProductInfoMapper productInfoMapper) {
    this.storeProductMapper = storeProductMapper;
    this.productInfoMapper = productInfoMapper;
  }

  @Scheduled(cron = "0 0 0 * * *") // 매 자정에 실행, 유통기한이니까 정각에 확인
  public void sendProductExpirationNotifications() {
    Long storeId = 2L; // 예시로 2번 매장을 기준으로 알림을 보냄, 이 부분은 테스트용이므로 나중에 전체 주석 처리 가능
    SseEmitter emitter = getProductExpirationNotifications(storeId);
    // SseEmitter를 사용하여 알림을 웹으로 전송
    emitter.complete();


    String expirationMessage = "유통기한이 3일 이하로 남은 상품이 있습니다.: ";
  }

  //유통기한 알림
  public SseEmitter getProductExpirationNotifications(Long storeId) {
    SseEmitter emitter = new SseEmitter();

    // 간단한 예시로 2초마다 이벤트 데이터를 생성하여 클라이언트로 전송,(작업을 지정된 시간 간격으로 주기적으로 실행할 수 있도록 지원하는 클래스)
    ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    executorService.scheduleAtFixedRate(() -> {
      try {
        List<StoreProduct> expiringProducts = storeProductMapper.getProductsExpiringInThreeDays(storeId);

//        SSE(Server-Sent Events)를 사용하여 클라이언트로 메시지를 전송
//        expiringProducts 리스트가 비어있지 않은 경우에만 실행
        if (!expiringProducts.isEmpty()) {
          String expirationMessage = "유통기한이 3일 이하로 남은 상품이 있습니다.";
          // 문자와 같이 내용이 가야하는데 체크해보기 (프론트에서 내용까지 다 나오는데...+ expiringProducts)
          emitter.send(SseEmitter.event()
              //send 메서드 이용하여 sse이벤트 생성 후 믈라이언트로 데이터(내가 보내는 메세지) 전송
              .data(expirationMessage)
              .build());
        }
        emitter.complete();
      } catch (IOException e) {
        // 에러 처리 로직
        emitter.completeWithError(e);
      }
    }, 0, 2, TimeUnit.MINUTES);

    return emitter;
  }

  //재고임박 알림
  public SseEmitter getLowInventoryNotifications(Long storeId) {
    SseEmitter emitter = new SseEmitter();
    ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    List<StoreProduct> lowInventoryProducts = new ArrayList<>();

    executorService.scheduleAtFixedRate(() -> {
      try {
        List<StoreProduct> storeProducts = storeProductMapper.getLowInventoryProducts(storeId);


        // 재고 임박 상품 검사
        for (StoreProduct storeProduct : storeProducts) {
          Long productInfoId = storeProduct.getProduct_code();
          ProductInfo productInfo = productInfoMapper.getProductInfoByProductId(productInfoId);

          if (storeProduct.getQnt() < productInfo.getSafe_qnt()) {
            if (!lowInventoryProducts.contains(storeProduct)) { // 중복 체크
              lowInventoryProducts.add(storeProduct);
            }
          }
        }

        //정보가 담긴 lowInventoryProducts 에서 product_id, store_id, safe_qnt 를 구한다.
        for (StoreProduct sp : lowInventoryProducts) {
          Long orderProductId = sp.getProduct_id();
          Long orderStoreId = sp.getStore_id();

          //해당되는 재고 임박 상품을 발주카트에 담아준다. qnt는 기본 1개로 담아준다.
          OrdersCart ordersCart = new OrdersCart(1, orderProductId, orderStoreId);

          if (!ordersCartService.exists(ordersCart)) { // 중복 체크
            ordersCartService.register(ordersCart);
          }
        }

// 문자와 같이 내용이 가야하는데 체크해보기 (프론트에서 내용까지 다 나오는데...+ lowInventoryProducts)
        if (!lowInventoryProducts.isEmpty()) {
          // 알림 내용과 lowInventoryProducts를 함께 담는 JSON 객체 생성
          ObjectNode messageNode = JsonNodeFactory.instance.objectNode();
          messageNode.put("message", "재고 임박 상품이 있습니다.");
          ArrayNode productsNode = messageNode.putArray("products");
          for (StoreProduct storeProduct : lowInventoryProducts) {
            ObjectNode productNode = productsNode.addObject();
            productNode.put("id", storeProduct.getId());
            productNode.put("storeId", storeProduct.getStore_id());
            productNode.put("store_qnt", storeProduct.getQnt());
            productNode.put("Safe_qnt", storeProduct.getSafe_qnt());
            productNode.put("Product_name", storeProduct.getProduct_name());
            productNode.put("Product_code", storeProduct.getProduct_code());
          }

          // JSON 객체를 문자열로 변환
          ObjectMapper objectMapper = new ObjectMapper();
          String lowInventoryMessage = objectMapper.writeValueAsString(messageNode);

          emitter.send(SseEmitter.event()
              .data(lowInventoryMessage)
              .id("lowInventoryNotification")  // 이벤트 ID 추가
              .build());

          lowInventoryProducts.clear(); // 모든 상품이 처리되었으므로 리스트를 비움
        }
        emitter.complete();
      } catch (Exception e) {
        emitter.completeWithError(e);
      }
    }, 0, 2, TimeUnit.MINUTES);


    return emitter;
  }

}


