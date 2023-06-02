package com.ipb.controller;

import com.ipb.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/notifications")
public class NotificationController {

  @Autowired
  NotificationService notificationService;

  public NotificationController(NotificationService notificationService) {
    this.notificationService = notificationService;
  }

    @GetMapping(value ="/expiration/{storeId}",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter getProductExpirationNotifications(@PathVariable Long storeId) {
      return notificationService.getProductExpirationNotifications(storeId);
    }

  //  @GetMapping("/low-inventory/{storeId}")
  @GetMapping(value = "/low-inventory/{storeId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public SseEmitter getLowInventoryNotifications(@PathVariable Long storeId) {
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

















