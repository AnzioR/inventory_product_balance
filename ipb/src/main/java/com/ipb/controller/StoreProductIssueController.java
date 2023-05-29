package com.ipb.controller;

import com.ipb.domain.StoreProductIssue;
import com.ipb.service.StoreProductIssueService;
import com.ipb.service.StoreProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StoreProductIssueController {

  @Autowired
  StoreProductService storeProductService;

  @Autowired
  StoreProductIssueService storeProductIssueService;

  //유통기한이 하루 지난 점포보유 상품들을 폐기하고 점포보유상품 이슈 테이블에 등록한다.
  @PostMapping("/check-exp")
  public void checkDay(@RequestBody StoreProductIssue storeProductIssue) {
    try {
      storeProductIssueService.checkExp();
    } catch(Exception e) {
      System.out.println("폐기등록을 실패했습니다.");
      e.printStackTrace();
    }
  }
}
