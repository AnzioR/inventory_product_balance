package com.ipb.service;

import com.ipb.domain.Orders;
import com.ipb.domain.Store;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import static org.assertj.core.util.DateUtil.now;


@SpringBootTest
class OrdersServiceTest {

  @Autowired
  OrdersService ordersService;

  @Test
  void register() {
    try {
      Orders orders = new Orders(null, 200, 2L, 2L, 1L, new Date());
      ordersService.register(orders);
      System.out.println(orders);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  void modify() {
      try {
        Orders orders = ordersService.get(1L);
        orders.setQnt(50);
        ordersService.modify(orders);
      } catch (Exception e) {
        e.printStackTrace();
        System.out.println("발주 수량 변경을 실패했습니다.");
      }

  }

  @Test
  void remove() {
    try {
      ordersService.remove(1L);
    } catch(Exception e) {
      e.printStackTrace();
      System.out.println("발주 취소 오류가 발생했습니다.");
    }
  }

  @Test
  void get() {
    Orders orders = null;
    try {
      orders = ordersService.get(1L);
      System.out.println(orders);
    } catch(Exception e) {
      e.printStackTrace();
      System.out.println("발주 검색오류가 발생했습니다.");
    }
  }

  @Test
  void testGet() {
    List<Orders> list = null;
    try {
      list = ordersService.get();
      for(Orders or : list) {
        System.out.println(or);
      }
    } catch(Exception e) {
      e.printStackTrace();
      System.out.println("전체 발주 검색 오류가 발생했습니다.");
    }
  }
  
  //날짜를 임의로 넣어서 확인 해 볼 수 없었서 now()로 null 찍히는 것 확인함
  //new Date()로 두고 테스트했는데 같은 날짜 인데도 null 값으로 찍힘ㅜ 없어서 그런건가.....
  @Test
  void searchdate() {
    Orders orders = null;
    try {
      orders = ordersService.searchdate(now());
      System.out.println(orders);
    } catch(Exception e) {
      e.printStackTrace();
      System.out.println("날짜로 발주조회하기를 실패했습니다.");
    }
  }
}