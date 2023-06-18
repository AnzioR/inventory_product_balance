package com.ipb.controller;

import com.ipb.service.NotificationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/notifications")
@Api(tags = {"알림"})
public class NotificationController {

  @Autowired
  NotificationService notificationService;

  public NotificationController(NotificationService notificationService) {
    this.notificationService = notificationService;
  }

  @ApiOperation(value = "유통기한 알림", notes = "store_id가 보유한 상품의 유통기한 알림이 간다")
  @GetMapping(value = "/expiration/{storeId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public SseEmitter getProductExpirationNotifications(@PathVariable Long storeId) {
    return notificationService.getProductExpirationNotifications(storeId);
  }

  @ApiOperation(value = "재고임박 알림", notes = "store_id가 보유한 상품의 재고임박 알림이 간다")
  @GetMapping(value = "/low-inventory/{storeId}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public SseEmitter getLowInventoryNotifications(@PathVariable Long storeId) {

    return notificationService.getLowInventoryNotifications(storeId);
  }
}

















