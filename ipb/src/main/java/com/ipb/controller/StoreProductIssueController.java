package com.ipb.controller;

import com.ipb.domain.StoreProduct;
import com.ipb.domain.StoreProductIssue;
import com.ipb.service.StoreProductIssueService;
import com.ipb.service.StoreProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

import static java.time.LocalTime.now;

@RestController
@RequiredArgsConstructor
@RequestMapping("/issue")
@Api(tags = {"이슈"})

public class StoreProductIssueController {

  @Autowired
  StoreProductService storeProductService;

  @Autowired
  StoreProductIssueService storeProductIssueService;

  //유통기한이 하루 지난 점포보유 상품들을 폐기하면, 점포보유상품 이슈 테이블에 등록한다. --> 상품폐기쪽에서 동시에 진행함!
//  @PostMapping("/add")
//  @ApiOperation(value = "폐기", notes = "유통기한이 하루 지난 점포보유 상품들을 폐기하면, 점포보유상품 이슈 테이블에 등록한다")
//  public StoreProductIssue register(@RequestBody StoreProduct sp) {
//    try {
//      storeProductIssueService.register(new StoreProductIssue(sp.getId(), sp.getQnt(), 5L, new Date()));
//      System.out.println("폐기상품 정보를 등록했습니다.");
//    } catch (Exception e) {
//      System.out.println("폐기상품 정보 등록에 실패했습니다.");
//      e.printStackTrace();
//    }
//    return null;
//  }


}
