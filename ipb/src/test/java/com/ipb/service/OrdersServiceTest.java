package com.ipb.service;

import com.ipb.domain.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.util.DateUtil.now;


@SpringBootTest
class OrdersServiceTest {

  @Autowired
  OrdersService ordersService;

  @Autowired
  ProductService productService;

  @Autowired
  OrdersCartService ordersCartService;

  @Autowired
  StoreProductService storesProductService;

  @Test
  void register() {
    //addorder로 기능대체함
  }

  @Test
  void modify() {
      try {
        Orders orders = ordersService.get(1L);
        orders.setQnt(500);
        ordersService.modify(orders);
      } catch (Exception e) {
        e.printStackTrace();
        System.out.println("발주 수량 변경을 실패했습니다.");
      }
  }

  //가져온 주문의 delivery_id가 1인 경우는 테스트 성공
  //delivery_id가 2인 경우는 발주취소를 실패했습니다라는 문구 출력
  @Test
  void orderscancel() {
    try {
      Orders orders = ordersService.get(1L);
      ordersService.orderscancel(orders);

      // Product 정보 조회
      Product product = productService.get(orders.getProduct_id());

      // Product 수량 변경
      product.setQnt(product.getQnt() + orders.getQnt());
      productService.updateqnt(product);

    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("발주 취소를 실패했습니다.");
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

  //날짜별로 주문한 내역을 조회
  @Test
  void searchdate() {
    List<Orders> ordersList = null;
    try {
      Date nowDate = new Date();
      ordersList = ordersService.searchdate(nowDate);
      for(Orders orders : ordersList) {
        System.out.println(orders);
      }
    } catch(Exception e) {
      e.printStackTrace();
      System.out.println("날짜로 발주조회하기를 실패했습니다.");
    }
  }

  //매장별 전체 발주 조회
  @Test
  void selectstore() {
    List<Orders> list = null;
    try {
      list = ordersService.selectstore(2L);
      for(Orders or : list) {
        System.out.println(or);
      }
    } catch(Exception e) {
      e.printStackTrace();
      System.out.println("매장별 발주 조회를 실패했습니다.");
    }
  }

  //매장별 상세 발주 조회
  @Test
  void selectdetailstoreorders() {
    List<Orders> list = null;
    try {
      list = ordersService.selectdetailstoreorders(2L);
      for(Orders or : list) {
        System.out.println(or);
      }
    } catch(Exception e) {
      e.printStackTrace();
      System.out.println("매장별 발주 상세 정보 조회를 실패했습니다.");
    }
  }

  //매장별 발주 수정
  @Test
  void updatestoreorders() {
    try {
      Orders orders = ordersService.get(1L);
      orders.setQnt(444);
      orders.setDelivery_id(2L);
      ordersService.updatestoreorders(orders);
      System.out.println(orders);
    } catch(Exception e) {
      e.printStackTrace();
      System.out.println("수정할 수 없습니다.");
    }
  }

  //발주카트에서 1.발주로 옮기고 2.상품재고 줄이고 3.점포재고 늘려줌
  // 카트에서 주문하기하면 카트 내용 삭제, 본사 재고도 줄고 주문되는거

  // 본사 재고 100개
  // 카트에 200개
  // 주문할 떄
  //  -> 정상주문
  // -> 수량부족 -> 선택해. 최대수량으로 주문할껀지 안할껀지 선택해

  // 주문 -> 정상 주문 되던가 안되던가 (하나의 서비스)
  // 최대주문 하기 서비스 ->

  @Test
  List<OrdersCart> addorder() {
    // 카트에 있는 상품들을 가져와서 주문할꺼야
    List<OrdersCart> addorder = new ArrayList<>();
    try {
      addorder = ordersService.addorder(1L);
      System.out.println(addorder);
    } catch(Exception e) {
      e.printStackTrace();
      System.out.println("발주를 실패했습니다.");
    }
    return addorder;
  }

  @Test
  void maxItemOrder() {
    List<OrdersCart> failOrderList = addorder();
    try {
      ordersService.maxOrder(failOrderList);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}