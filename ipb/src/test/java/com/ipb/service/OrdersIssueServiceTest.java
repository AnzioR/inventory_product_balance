package com.ipb.service;

import com.ipb.domain.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
class OrdersIssueServiceTest {

  @Autowired
  OrdersIssueService ordersIssueService;

//  ordersIssue에 order_id, qnt, orders_desc_id, dispossal_date 을 써서 주문 이슈가 발생한 상품을 등록 할 수 있다.
  @Test
  void register() {
    try {
      OrdersIssue ordersIssue = new OrdersIssue(null, 141L, 8, 2L, new Date());
      ordersIssueService.register(ordersIssue);
      System.out.println(ordersIssue);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  // (요구사항에는 없지만 필요할까봐) ordersIssue에 order_id, qnt, orders_desc_id, dispossal_date 을 써서 주문 이슈가 발생한 상품을 수정 할 수 있다.
//  수정 되는 부분은 qnt,disposal 부분만 업데이트 할 수 있도록 만들었다
  @Test
   void modify() {
    OrdersIssue ordersIssue = new OrdersIssue(2L, 1L, 12, 2L, new Date());
    try {
      ordersIssueService.modify(ordersIssue);
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("수정 오류가 발생했습니다.");
    }
  }

  // (요구사항에는 없지만 필요할까봐)ordersIssue id를 삭제를 할 수 있다.
  @Test
  void remove() {
    try {
      ordersIssueService.remove(2L);
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("삭제 오류가 발생했습니다.");
    }
  }

// (요구사항에는 없지만필요할까봐)
  @Test
  void get() {
    try {
      OrdersIssue ordersIssue = ordersIssueService.get(1L);
      System.out.println(ordersIssue);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
  // orders issue 전체 상세조회가 가능하다
//  이슈 ,이슈 상품이름,판매가격,수량, 등록일,유통기한,카테고리,점포아이디
  @Test
  @DisplayName("Order issue test")
  void ordersissuealllist() {
    List<OrdersIssue> list = null;
    try {
      list = ordersIssueService.ordersissuealllist();
      System.out.println(list.size());
      for (OrdersIssue ordersissue : list) {
        System.out.println(ordersissue);
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("전체검색 오류가 발생했습니다.");
    }
  }


//  점포의 아이디 값으로 order issue 상세 조회가 가능하다
  //  이슈 ,이슈 상품이름,판매가격,수량, 등록일,유통기한,카테고리
    @Test
    void ordersissuestore() {
      try {
        List<OrdersIssue> ordersissuestore = ordersIssueService.ordersissuestore(2L);
        for (OrdersIssue o : ordersissuestore){
          System.out.println(o);
        }
      } catch(Exception e) {
        e.printStackTrace();
        System.out.println("오류가 발생.");
      }
    }

//    orders issue로 issue product 상세 조회가 가능하다
  //  이슈 ,이슈 상품이름,판매가격,수량, 등록일,유통기한,카테고리,점포아이디
  @Test
  void ordersissuedesc() {
    try {
      List<OrdersIssue> ordersissuedesc = ordersIssueService.ordersissuedesc(3L);
      for (OrdersIssue o : ordersissuedesc){
        System.out.println(o);
      }
    } catch(Exception e) {
      e.printStackTrace();
      System.out.println("오류가 발생.");
    }
  }
  }
