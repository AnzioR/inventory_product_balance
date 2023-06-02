package com.ipb.controller;

import com.ipb.domain.*;
import com.ipb.domain.StockInfo;
import com.ipb.domain.StoreAutoOrders;
import com.ipb.domain.StoreProduct;
import com.ipb.service.NotificationService;
import com.ipb.service.StoreProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.HashMap;
import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("/notifications")
public class NotificationController {
  @Autowired
 NotificationService notificationService;

  public NotificationController(NotificationService notificationService) {
    this.notificationService = notificationService;
  }

  @GetMapping("/expiration/{storeId}")
  public Flux<ServerSentEvent<String>> getProductExpirationNotifications(@PathVariable Long storeId) {
    return notificationService.getProductExpirationNotifications(storeId);
  }

  @GetMapping("/low-inventory/{storeId}")
  public Flux<ServerSentEvent<String>> getLowInventoryNotifications(@PathVariable Long storeId) {
    return notificationService.getLowInventoryNotifications(storeId);
  }
}

//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/notifications")
//public class NotificationController {
//
//  @Autowired
//  NotificationService notificationService;
//  @GetMapping("/expiration")
//  public Flux<ServerSentEvent<String>> getProductExpirationNotifications(@RequestParam("store_id") Long storeId) {
//    return notificationService.getProductExpirationNotifications(storeId);
//  }
//  @GetMapping("/inventory")
//  public Flux<ServerSentEvent<String>> getLowInventoryNotifications(@RequestParam("store_id") Long storeId) {
//    return notificationService.getLowInventoryNotifications(storeId);
//  }
//
//}

















