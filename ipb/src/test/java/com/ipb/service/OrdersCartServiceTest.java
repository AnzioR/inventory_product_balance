package com.ipb.service;

import com.ipb.domain.Orders;
import com.ipb.domain.OrdersCart;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class OrdersCartServiceTest {

  @Autowired
  OrdersCartService ordersCartService;

  @Test
  void register() {
    try {
      OrdersCart cart = new OrdersCart(null, 444, 1L, 2L);
      ordersCartService.register(cart);
      System.out.println(cart);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  void modify() {
    OrdersCart cart = new OrdersCart(1L, 200, 2L, 2L);
    try {
      ordersCartService.modify(cart);
    } catch(Exception e) {
      e.printStackTrace();
      System.out.println("카트 수량 변경을 실패했습니다.");
    }
  }

  @Test
  void remove() {
    try {
      ordersCartService.remove(2L);
    } catch(Exception e) {
      e.printStackTrace();
      System.out.println("카트 삭제 오류가 발생했습니다.");
    }
  }

  @Test
  void get() {
    OrdersCart cart = null;
    try {
      cart = ordersCartService.get(2L);
      System.out.println(cart);
    } catch(Exception e) {
      e.printStackTrace();
      System.out.println("카트 검색오류가 발생했습니다.");
    }
  }

  @Test
  void testGet() {
    List<OrdersCart> list = null;
    try {
      list = ordersCartService.get();
      for(OrdersCart ca : list) {
        System.out.println(ca);
      }
    } catch(Exception e) {
      e.printStackTrace();
      System.out.println("전체 카트 검색 오류가 발생했습니다.");
    }
  }

  @Test
  void cartlist() {
    List<OrdersCart> list = null;
    try {
      list = ordersCartService.cartlist(3L);
      for(OrdersCart oc : list) {
        System.out.println(oc);
      }
    } catch(Exception e) {
      e.printStackTrace();
      System.out.println("매장별 발주카트 가져오기를 실패했습니다.");
    }
  }

  @Test
  void removecart() {
    try {
      ordersCartService.removecart(2L);
    } catch(Exception e) {
      e.printStackTrace();
      System.out.println("카트 비우기 오류가 발생했습니다.");
    }
  }

}