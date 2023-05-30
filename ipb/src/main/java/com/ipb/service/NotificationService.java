package com.ipb.service;


import com.ipb.domain.StoreProduct;
import com.ipb.frame.MyService;
import com.ipb.mapper.EmitterMapper;
import com.ipb.mapper.StoreProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
@Service
public class NotificationService {

  private final StoreProductMapper storeProductMapper;

  @Autowired
  public NotificationService(StoreProductMapper storeProductMapper) {
    this.storeProductMapper = storeProductMapper;
  }

  public Flux<ServerSentEvent<String>> getProductExpirationNotifications(Long storeId) {
    // 유통기한이 3일 이하로 남은 상품 조회
    List<StoreProduct> expiringProducts = storeProductMapper.getProductsExpiringInThreeDays(storeId);

    if (expiringProducts.isEmpty()) {
      return Flux.empty(); // 상품이 없을 경우 빈 Flux 반환
    }

    // 알림 생성 및 전송
    String message = "유통기한이 3일 이하로 남은 상품이 있습니다.: " + expiringProducts;
    System.out.println(message); // 알림 메시지 출력

    return Flux.just(ServerSentEvent.<String>builder()
            .event("notification")
            .data(message)
            .build())
        .repeat()
        .delayElements(Duration.ofSeconds(1))
        .distinctUntilChanged(); // 중복 알림 제거
  }
}
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
//    messagingTemplate.convertAndSend("/topic/notifications", message); // 웹소켓을 통해 클라이언트에게 알림 메시지를 보냅니다.
//  }
//
//  public void checkExpiringProducts() {
//    LocalDate expiryDate = LocalDate.now().plusDays(3);
//    List<StoreProduct> expiringProducts = storeProductMapper.findExpiringProducts(expiryDate);
//
//    if (!expiringProducts.isEmpty()) {
//      messagingTemplate.convertAndSend("/topic/notifications", "유통기한이 3일 남은 상품이 있습니다."); // 웹소켓을 통해 클라이언트에게 알림 메시지를 보냅니다.
//    }
//  }
//}

