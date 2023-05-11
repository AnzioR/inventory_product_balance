package com.ipb.service;

import com.ipb.domain.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
//    try {
//      // store_id에 해당하는 카트에 담긴 상품 리스트를 가져온다
//      Long storeId = 3L;
//      List<OrdersCart> cartItems = ordersCartService.cartlist(storeId);
//      //System.out.println(cartItems);
//
//      // 상품 정보를 Orders 객체로 변환한다
//      List<Orders> transOrders = new ArrayList<>();
//      System.out.println("transOrders 출력 : "+transOrders);
//
//      for (OrdersCart item : cartItems) {
//        Orders order = new Orders();
//        order.setProduct_id(item.getProduct_id());
//        order.setQnt(item.getQnt());
//        order.setStore_id(item.getStore_id());
//        order.setDelivery_id(1L); //발주가 이루어지는 시점에서 상품의 배송상태는 배송준비중(1)이다
//        order.setOrders_date(new Date());
//        transOrders.add(order);
//        System.out.println("결과 = "+order);
//        System.out.println("transOrders = " + transOrders);
//      }
//
//      /*검증 완료*/
//
//      // Orders 객체를 전송한다
////      for (Orders order : transOrders) {
//////        ordersService.register(order);
////        ordersService.addOrder(order);
////        System.out.println(order);
////      }
//
//      // Product 정보 조회
//      Orders orders = new Orders();
//      Product product = productService.get(orders.getProduct_id());
//
//      // Product 수량 변경
//      if(product.getQnt() - orders.getQnt() >= 0) {
//        product.setQnt(product.getQnt() - orders.getQnt());
//        productService.updateqnt(product);
//      } else {
//        System.out.println("주문 수량이 재고를 초과합니다. 다시 확인해주세요.");
//      }
////      Assertions.assertEquals(cartItems.size(), transOrders.size());
////      for (Orders transOrder : transOrders) {
////        Assertions.assertEquals()
////      }
//    } catch (Exception e) {
//      e.printStackTrace();
//      System.out.println("카트에 담긴 상품을 발주하는 것이 실패했습니다.");
//    }

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
//  @Test
//  void addorder() {
//    try {
//      List<OrdersCart> cartItems = ordersCartService.cartlist(3L);
//      List<Orders> transOrders = new ArrayList<>();
//
//      for (OrdersCart item : cartItems) {
//        Orders order = new Orders();
//        order.setProduct_id(item.getProduct_id());
//        order.setQnt(item.getQnt());
//        order.setStore_id(item.getStore_id());
//        order.setDelivery_id(1L); //발주가 이루어지는 시점에서 상품의 배송상태는 배송준비중(1)이다
//        order.setOrders_date(new Date());
//        transOrders.add(order);
//        ordersService.addorder(3L);
//
//        //발주를 하는 경우 발주가능상품의 수량에서 주문수량을 차감한다
//        Product product = productService.get(order.getProduct_id());
//
//        //만일 발주가능상품의 수량보다 주문수량이 많은 경우 발주할 수 없다
//        if(product.getQnt() - order.getQnt() >= 0) {
//          product.setQnt(product.getQnt() - order.getQnt());
//          productService.updateqnt(product);
//        } else {
//          throw new IllegalStateException("주문 수량이 재고를 초과합니다. 다시 확인해주세요.");
//        }
//
//        //발주를 하게 되면 점포의 재고수량을 주문수량만큼 바로 증가시켜준다(이 기능은 향후 배송완료 시점에 증가될 수 있도록 수정을 하면 좋겠어요!)
//        StoreProduct sp = new StoreProduct(order.getProduct_id(), order.getStore_id());
//        System.out.println(sp);
//        sp = storesProductService.getstoreproductfromstoreidandproductid(sp); //storeproduct 어떻게 데려와야되는건지.......
//        System.out.println("sp = " + sp);
//        sp.setQnt(sp.getQnt() + order.getQnt());
//        storesProductService.storeupdateqnt(sp);
//      }
//    } catch(Exception e) {
//      e.printStackTrace();
//      System.out.println("발주를 실패했습니다.");
//    }
//
//  }
}