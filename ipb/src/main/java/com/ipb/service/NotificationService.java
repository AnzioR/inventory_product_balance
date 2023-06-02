package com.ipb.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.ipb.domain.Message;
import com.ipb.domain.Product;
import com.ipb.domain.ProductInfo;
import com.ipb.domain.StoreProduct;
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
import reactor.core.publisher.Flux;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService {

  @Autowired
  StoreService storeService;

  @Autowired
  SmsService smsService;

  private final StoreProductMapper storeProductMapper;
  private final ProductInfoMapper productInfoMapper;

  public NotificationService(StoreProductMapper storeProductMapper, ProductInfoMapper productInfoMapper) {
    this.storeProductMapper = storeProductMapper;
    this.productInfoMapper = productInfoMapper;
  }

  public Flux<ServerSentEvent<String>> getProductExpirationNotifications(Long storeId) {
    List<StoreProduct> expiringProducts = storeProductMapper.getProductsExpiringInThreeDays(storeId);

    if (expiringProducts.isEmpty()) {
      return Flux.empty(); // 유통기한이 임박한 상품이 없을 경우 빈 Flux 반환
    }

    String expirationMessage = "유통기한이 3일 이하로 남은 상품이 있습니다.: " + expiringProducts;
    System.out.println(expirationMessage); // 유통기한 알림 메시지 출력

    //문자메세지 발송
    String num = null;
    try {
      num = storeService.selectNumber(storeId);
    } catch (Exception e) {
      System.out.println("메시지를 보낼 점포아이디 조회에 실패했습니다.");
      e.printStackTrace();
    }
    String formattedNum = num.replaceAll("-", "");
    Message message = new Message(formattedNum, "유통기한이 3일이하로 남은 상품이 있습니다.");

    try {
      smsService.sendSms(message);
      System.out.println(message);
    } catch (JsonProcessingException | URISyntaxException | InvalidKeyException | NoSuchAlgorithmException |
             UnsupportedEncodingException e) {
      System.out.println("오류가 발생했습니다.");
      e.printStackTrace();
    }


    return Flux.just(ServerSentEvent.<String>builder()
            .event("expirationNotification")
            .data(expirationMessage)
            .build())
        .repeat()
        .delayElements(Duration.ofSeconds(1))
        .distinctUntilChanged(); // 중복 알림 제거
  }

  public Flux<ServerSentEvent<String>> getLowInventoryNotifications(Long storeId) {
    List<StoreProduct> storeProducts = storeProductMapper.getLowInventoryProducts(storeId);

    List<StoreProduct> lowInventoryProducts = new ArrayList<>();

    for (StoreProduct storeProduct : storeProducts) {
      Long productInfoId = storeProduct.getProduct_code();
      ProductInfo productInfo = productInfoMapper.getProductInfoByProductId(productInfoId);

      if (storeProduct.getQnt() < productInfo.getSafe_qnt()) {
        lowInventoryProducts.add(storeProduct);
      }
    }

    if (lowInventoryProducts.isEmpty()) {
      return Flux.empty(); // 재고 임박 상품이 없을 경우 빈 Flux 반환
    }

    String lowInventoryMessage = "재고 임박 상품이 있습니다.: " + lowInventoryProducts;
    System.out.println(lowInventoryMessage); // 재고 알림 메시지 출력

    return Flux.just(ServerSentEvent.<String>builder()
            .event("lowInventoryNotification")
            .data(lowInventoryMessage)
            .build())
        .repeat()
        .delayElements(Duration.ofSeconds(1))
        .distinctUntilChanged(); // 중복 알림 제거
  }
  }

//@Service
//@EnableScheduling
//public class NotificationService {
//
//  private final StoreProductMapper storeProductMapper;
//
//  public NotificationService(StoreProductMapper storeProductMapper) {
//    this.storeProductMapper = storeProductMapper;
//  }
//
////  @Scheduled(cron = "0 0 0 * * *") // 매 자정에 실행
//  @Scheduled(fixedDelay = 1000*60*60)
//  public void sendProductExpirationNotifications() {
//    Long storeId = 2L; // 예시로 1번 매장을 기준으로 알림을 보냄, 이부분은 test 느낌으로 쓰고있음 나중에 전체 주석해도
//    Flux<ServerSentEvent<String>> notifications = getProductExpirationNotifications(storeId);
//
//    // 웹 소켓 연결 등을 통해 알림을 웹으로 전송하는 로직을 추가해야하는데,, 쓸 꺼면
//    // 알림이 notifications Flux에 전송되면 해당 알림을 웹으로 전달
//    notifications.subscribe();
//  }
//
//  //store_id 가 매개변수인데 로그인할떄 store_id로 보내줘
//  public Flux<ServerSentEvent<String>> getProductExpirationNotifications(Long storeId) {
//    List<StoreProduct> expiringProducts = storeProductMapper.getProductsExpiringInThreeDays(storeId);
//
//    if (expiringProducts.isEmpty()) {
//      return Flux.empty(); // 상품이 없을 경우 빈 Flux 반환
//    }
//
//    String message = "유통기한이 3일 이하로 남은 상품이 있습니다.: " + expiringProducts;
//    System.out.println(message); // 알림 메시지 출력
//
//    return Flux.just(ServerSentEvent.<String>builder()
//            .event("notification")
//            .data(message)
//            .build())
//        .repeat()
//        .delayElements(Duration.ofSeconds(1))
//        .distinctUntilChanged(); // 중복 알림 제거
//
//  }
//}

//@Service
//public class NotificationService {
//  private final SimpMessagingTemplate messagingTemplate;
//  private final EmitterMapper emitterMapper;
//  private final StoreProductMapper storeProductMapper;
//
//  public NotificationService(SimpMessagingTemplate messagingTemplate, EmitterMapper emitterMapper, StoreProductMapper storeProductMapper) {
//    this.messagingTemplate = messagingTemplate;
//    this.emitterMapper = emitterMapper;
//    this.storeProductMapper = storeProductMapper;
//  }
//
//  @Scheduled(cron = "0 0 8 * * *") // 매일 오전 8시에 실행되도록 설정
//  public void scheduleExpiryNotifications() {
//    sendExpiryNotifications();
//  }
//
//  public void sendExpiryNotifications() {
//    int daysBeforeExpiry = 3;
//    List<StoreProduct> expiringProducts = emitterMapper.findProductsExpiringSoon(daysBeforeExpiry);
//
//    for (StoreProduct storeProduct : expiringProducts) {
//      // 알림을 보내는 로직을 구현
//      String message = "유통기한이 임박한 상품이 있습니다: " + storeProduct.getName();
//      sendNotification(message);
//    }
//  }
//
//  private void sendNotification(String message) {
//    // 알림을 보내는 로직을 구현
//    messagingTemplate.convertAndSend("/topic/notifications", message); // 웹소켓을 통해 클라이언트에게 알림 메시지를 보냄
//  }
//
//  public void checkExpiringProducts() {
//    LocalDate expiryDate = LocalDate.now().plusDays(3);
//    List<StoreProduct> expiringProducts = storeProductMapper.findExpiringProducts(expiryDate);
//
//    if (!expiringProducts.isEmpty()) {
//      messagingTemplate.convertAndSend("/topic/notifications", "유통기한이 3일 남은 상품이 있습니다."); // 웹소켓을 통해 클라이언트에게 알림 메시지를 보냄
//    }
//  }
//}

