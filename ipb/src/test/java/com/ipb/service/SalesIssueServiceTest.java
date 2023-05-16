package com.ipb.service;

import com.ipb.domain.OrdersIssue;
import com.ipb.domain.SalesIssue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
class SalesIssueServiceTest {

  @Autowired
  SalesIssueService salesIssueService;

  //  salesIssue에 store_product_id, qnt, sales_desc_id, dispossal_date 을 써서 판매 이슈가 발생한 상품을 등록 할 수 있다.
  @Test
  void register() {
    try {
      SalesIssue salesIssue = new SalesIssue(null, 1L, 20, 2L, new Date());
      salesIssueService.register(salesIssue);
      System.out.println(salesIssue);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
  // (요구사항에는 없지만 필요할까봐) //  salesIssue에 store_product_id, qnt, sales_desc_id, dispossal_date 을 써서 판매 이슈가 발생한 상품을 수정 할 수 있다.
//  수정 되는 부분은 qnt,disposal 부분만 업데이트 할 수 있도록 만들었다
  @Test
  void modify() {
    SalesIssue salesIssue = new SalesIssue(16L, 1L, 12, 2L, new Date());
    try {
      salesIssueService.modify(salesIssue);
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("수정 오류가 발생했습니다.");
    }
  }
  // (요구사항에는 없지만 필요할까봐)salesIssue에 id를 삭제를 할 수 있다.
  @Test
  void remove() {
    try {
      salesIssueService.remove(16L);
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("삭제 오류가 발생했습니다.");
    }
  }

  @Test
  void get() {
    try {
      SalesIssue salesIssue = salesIssueService.get(1L);
      System.out.println(salesIssue);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  // salesIssue 전체 상세조회가 가능하다
//  이슈 ,이슈 상품이름,판매가격,수량, 등록일,유통기한,카테고리,점포아이디
  @Test
  @DisplayName("Sales issue test")
  void salesissuealllist() {
    List<SalesIssue> list = null;
    try {
      list = salesIssueService.salesissuealllist();
      System.out.println(list.size());
      for (SalesIssue salesIssue : list) {
        System.out.println(salesIssue);
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("전체검색 오류가 발생했습니다.");
    }
  }

  //  점포의 아이디 값으로 salesIssue  상세 조회가 가능하다
  //  이슈 ,이슈 상품이름,판매가격,수량, 등록일,유통기한,카테고리
  @Test
  void salesissuestore() {
    try {
      List<SalesIssue> salesissuestore = salesIssueService.salesissuestore(2L);
      for (SalesIssue o : salesissuestore){
        System.out.println(o);
      }
    } catch(Exception e) {
      e.printStackTrace();
      System.out.println("오류가 발생.");
    }
  }

  //   salesIssue로 issue product 상세 조회가 가능하다
  //  이슈 ,이슈 상품이름,판매가격,수량, 등록일,유통기한,카테고리,점포아이디
  @Test
  void ordersissuedesc() {
    try {
      List<SalesIssue> salesissuedesc = salesIssueService.salesissuedesc(3L);
      for (SalesIssue o : salesissuedesc){
        System.out.println(o);
      }
    } catch(Exception e) {
      e.printStackTrace();
      System.out.println("오류가 발생.");
    }
  }
}

