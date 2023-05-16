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

  @Test
  void register() {
    try {
      OrdersIssue ordersIssue = new OrdersIssue(null, 44L, 20, 2L, new Date());
      ordersIssueService.register(ordersIssue);
      System.out.println(ordersIssue);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

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

  @Test
  void remove() {
    try {
      ordersIssueService.remove(2L);
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("삭제 오류가 발생했습니다.");
    }
  }

  @Test
  void get() {
    try {
      OrdersIssue ordersIssue = ordersIssueService.get(1L);
      System.out.println(ordersIssue);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

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
