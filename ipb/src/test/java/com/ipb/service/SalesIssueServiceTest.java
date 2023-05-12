//package com.ipb.service;
//
//import com.ipb.domain.OrdersIssue;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.Date;
//
//@SpringBootTest
//class SalesIssueServiceTest {
//
//  @Autowired
//  OrdersIssueService ordersIssueService;
//
//  @Test
//  void register() {
//    try {
//      OrdersIssue ordersIssue = new OrdersIssue(null,1L,20,2L,new Date());
//      ordersIssueService.register(ordersIssue);
//      System.out.println(ordersIssue);
//    } catch (Exception e) {
//      throw new RuntimeException(e);
//    }
//  }
//
//  @Test
//  void modify() {
//    OrdersIssue ordersIssue = new OrdersIssue(23L,1L,22,2L,new Date());
//    try {
//      ordersIssueService.modify(ordersIssue);
//    } catch(Exception e) {
//      e.printStackTrace();
//      System.out.println("수정 오류가 발생했습니다.");
//    }
//  }
//
//  @Test
//  void remove() {
//    try {
//      ordersIssueService.remove(23L);
//    } catch(Exception e) {
//      e.printStackTrace();
//      System.out.println("삭제 오류가 발생했습니다.");
//    }
//  }
//fini


//  @Test
//  void get() {
//    OrdersIssue ordersIssue = null;
//    try {
//      ordersIssue = ordersIssueService.get(2L);
//      System.out.println(ordersIssue);
//    } catch(Exception e) {
//      e.printStackTrace();
//      System.out.println("검색오류가 발생했습니다.");
//    }
//  }
//
//  @Test
//  void testGet() {
//    List<OrdersIssue> list = null;
//    try {
//      list = ordersIssueService.get();
//      for(OrdersIssue ol : list) {
//        System.out.println(ol);
//      }
//    } catch(Exception e) {
//      e.printStackTrace();
//      System.out.println("전체검색 오류가 발생했습니다.");
//    }
//  }
//}