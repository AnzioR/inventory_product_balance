package com.ipb.service;

import com.ipb.domain.StoreProduct;
import com.ipb.mapper.StoreProductMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
class NotificationServiceTest {


    @Mock
    private StoreProductMapper storeProductMapper;

    @Test
    public void testGetProductExpirationNotifications() {
      NotificationService notificationService = new NotificationService(storeProductMapper);

      Long storeId =2L; // 상점의 ID 설정

      // Mock 데이터 설정
      List<StoreProduct> expiringProducts = new ArrayList<>();
      // expiringProducts에 유통기한이 3일 이하로 남은 상품들을 추가

      when(storeProductMapper.getProductsExpiringInThreeDays(storeId)).thenReturn(expiringProducts);

      // getProductExpirationNotifications 메서드 테스트
      Flux<ServerSentEvent<String>> notifications = notificationService.getProductExpirationNotifications(storeId);

      // 원하는 동작을 확인하는 코드 작성해야함

      // Mapper의 getProductsExpiringInThreeDays 메서드가 호출되는지 검증
      verify(storeProductMapper, times(1)).getProductsExpiringInThreeDays(storeId);
    }
  }