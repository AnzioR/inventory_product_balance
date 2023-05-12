package com.ipb.service;

import com.ipb.domain.Orders;
import com.ipb.domain.Product;
import com.ipb.domain.Sales;
import com.ipb.domain.StoreProduct;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.assertj.core.util.DateUtil.now;


@SpringBootTest
class SalesServiceTest {

  @Autowired
  SalesService salesService;

  @Autowired
  StoreProductService storeProductService;

  @Test
  void register() {
    try {
      Sales sales = new Sales(null,8000,5L,null);
        storeProductService.updateqnt(sales);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
// 판매 취소 로직 성고옹 재고 다시 돌아옴
  @Test
  void delete() {
      try {
      salesService.remove(5L);
      } catch (Exception e) {
        e.printStackTrace();
        System.out.println("발주 수량 변경을 실패했습니다.");
      }
  }

//  //가져온 주문의 delivery_id가 1인 경우는 테스트 성공
//  //delivery_id가 2인 경우는 발주취소를 실패했습니다라는 문구 출력
//  @Test
//  void orderscancel() {
//    try {
//      Orders orders = ordersService.get(1L);
//      ordersService.orderscancel(orders);
//
//      // Product 정보 조회
//      Product product = productService.get(orders.getProduct_id());
//
//      // Product 수량 변경
//      product.setQnt(product.getQnt() + orders.getQnt());
//      productService.updateqnt(product);
//
//    } catch (Exception e) {
//      e.printStackTrace();
//      System.out.println("발주 취소를 실패했습니다.");
//    }
//  }
//
  @Test
  void 점포별_매출_조회() {
    try {
      List<Sales> selectsalesbystore = salesService.selectsalesbystore(2L);
      for (Sales s : selectsalesbystore){
        System.out.println(s);
      }
    } catch(Exception e) {
      e.printStackTrace();
      System.out.println("발주 취소 오류가 발생했습니다.");
    }
  }
//
//  @Test
//  void get() {
//    Orders orders = null;
//    try {
//      orders = ordersService.get(1L);
//      System.out.println(orders);
//    } catch(Exception e) {
//      e.printStackTrace();
//      System.out.println("발주 검색오류가 발생했습니다.");
//    }
//  }
//
//  @Test
//  void testGet() {
//    List<Orders> list = null;
//    try {
//      list = ordersService.get();
//      for(Orders or : list) {
//        System.out.println(or);
//      }
//    } catch(Exception e) {
//      e.printStackTrace();
//      System.out.println("전체 발주 검색 오류가 발생했습니다.");
//    }
//  }
//
//  //날짜를 임의로 넣어서 확인 해 볼 수 없었서 now()로 null 찍히는 것 확인함
//  //new Date()로 두고 테스트했는데 같은 날짜 인데도 null 값으로 찍힘ㅜ 없어서 그런건가.....
//  @Test
//  void searchdate() {
//    Orders orders = null;
//    try {
//      orders = ordersService.searchdate(now());
//      System.out.println(orders);
//    } catch(Exception e) {
//      e.printStackTrace();
//      System.out.println("날짜로 발주조회하기를 실패했습니다.");
//    }
//  }
//
//  //매장별 전체 발주 조회
//  @Test
//  void selectstore() {
//    List<Orders> list = null;
//    try {
//      list = ordersService.selectstore(2L);
//      for(Orders or : list) {
//        System.out.println(or);
//      }
//    } catch(Exception e) {
//      e.printStackTrace();
//      System.out.println("매장별 발주 조회를 실패했습니다.");
//    }
//  }
//
//  //매장별 상세 발주 조회
//  @Test
//  void selectdetailstoreorders() {
//    List<Orders> list = null;
//    try {
//      list = ordersService.selectdetailstoreorders(2L);
//      for(Orders or : list) {
//        System.out.println(or);
//      }
//    } catch(Exception e) {
//      e.printStackTrace();
//      System.out.println("매장별 발주 상세 정보 조회를 실패했습니다.");
//    }
//  }
//
//  //매장별 발주 수정
//  @Test
//  void updatestoreorders() {
//    try {
//      Orders orders = ordersService.get(1L);
//      orders.setQnt(444);
//      orders.setDelivery_id(2L);
//      ordersService.updatestoreorders(orders);
//      System.out.println(orders);
//    } catch(Exception e) {
//      e.printStackTrace();
//      System.out.println("수정할 수 없습니다.");
//    }
//  }
}