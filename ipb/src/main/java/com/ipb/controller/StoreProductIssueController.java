package com.ipb.controller;

import com.ipb.domain.StoreProduct;
import com.ipb.domain.StoreProductIssue;
import com.ipb.service.StoreProductIssueService;
import com.ipb.service.StoreProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping("/issue")
public class StoreProductIssueController {

  @Autowired
  StoreProductService storeProductService;

  @Autowired
  StoreProductIssueService storeProductIssueService;

  //유통기한이 하루 지난 점포보유 상품들을 폐기하고 점포보유상품 이슈 테이블에 등록한다.
  @PostMapping("/add")
  public StoreProductIssue register(@RequestBody StoreProductIssue storeProductIssue) {
    try {
      storeProductIssueService.register(storeProductIssue);
      return storeProductIssue;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  private void registerIssue(StoreProduct sp) throws Exception {
    storeProductIssueService.register(new StoreProductIssue(sp.getId(),sp.getProduct_id(),  sp.getQnt() ,5L, new Date()));
  }

}
