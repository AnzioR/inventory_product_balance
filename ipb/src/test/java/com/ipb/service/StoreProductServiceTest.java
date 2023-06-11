package com.ipb.service;

import com.ipb.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.mockito.Mockito.mock;

@SpringBootTest
class StoreProductServiceTest {

  @Autowired
  StoreProductService storeProductService;

  @Test
  void register() {
    try {
      StoreProduct storeProduct = new StoreProduct(null, 518, 3L, 2L, true,2000,1.0);
      storeProductService.register(storeProduct);
      System.out.println(storeProduct);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  void modify() {
    StoreProduct storeProduct = new StoreProduct(270L, 8, 3L, 2L, false,4000,2.0);
    try {
      storeProductService.modify(storeProduct);
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("수정 오류가 발생!");
    }
  }

  @Test
  void remove() {
    try {
      storeProductService.remove(3L);
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("삭제 오류 발생!");
    }
  }

  @Test
  void get() {
    StoreProduct storeProduct = null;
    try {
      storeProduct = storeProductService.get(261L);
      System.out.println(storeProduct);
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("검색오류 발생!!!");
    }
  }

  @Test
  void select() {
    StoreProduct storeProduct = null;
    try {
      storeProduct = storeProductService.get(1L);
      System.out.println(storeProduct);
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("검색오류가 발생했습니다.");
    }

  }

//  @Test
//  void testselectexp() {
//    String categoryName = "생선";
//    List<StockInfo> list = null;
//    try {
//      list = storeProductService.selectcategoryname(categoryName,2L);
//      for (StockInfo stockInfo : list) {
//        System.out.println(stockInfo);
//      }
//    } catch (Exception e) {
//      e.printStackTrace();
//      System.out.println("카테고리 검색 오류 발생!!");
//    }
//  }
  @Test
  void testSelectCategoryName() {
    String categoryName = "생선";
    List<StockInfo> list = null;
    try {
      list = storeProductService.selectcategoryname(categoryName,2L);
      for (StockInfo stockInfo : list) {
        System.out.println(stockInfo);
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("카테고리 검색 오류 발생!!");
    }
  }
// (유통기한 날짜 별 조회) store 아이디로 카테고리를 검색한 후 날짜를 검색하면 그 오늘 기준으로 날짜 이하의 상품이 조회된다
  @Test
  void selectexpAndExpiringSoon() {
    String categoryName = "생선";
    List<StockInfo> list = null;
    try {
      list = storeProductService.selectexpAndExpiringSoon (categoryName,2L,5);
      for (StockInfo stockInfo : list) {
        System.out.println(stockInfo);
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("카테고리 검색 오류 발생!!");
    }
  }
  @Test
  void searchstoreproduct() {
    List<StockInfo> result = null;
    try {
      result = storeProductService.searchstoreproduct("오징어",2L);
      for (StockInfo stockInfo : result) {
        System.out.println(stockInfo);
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  void selectstoreproduct() {
    List<StoreProduct> result = null;
    try {
        result = storeProductService.selectstoreproduct(2L);
      for (StoreProduct stockInfo : result) {
        System.out.println(stockInfo);
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  //발주할 때, 수량을 변경해줌
  @Test
  void storeUpdateQnt() {
    try {
      StoreProduct storeProduct = storeProductService.get(1L);
      storeProduct.setQnt(1000);
      storeProductService.storeUpdateQnt(storeProduct);
      System.out.println(storeProduct);
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("발주로 인한 재고 수량 변경에 실패했습니다.");
    }
  }

  //store_id와 product_id를 조회하는 기능
  @Test
  void getStoreProductFromStoreIdAndProductId() {
    try {
      StoreProduct st = new StoreProduct();
      st.setStore_id(1L);
      st.setProduct_id(1L);

      System.out.println(st);
      st = storeProductService.getStoreProductFromStoreIdAndProductId(st);
      System.out.println(st);
    } catch(Exception e) {
      e.printStackTrace();
      System.out.println("조회가 실패했습니다.");
    }
  }

  //발주가 성공했을 때, 점포보유상품의 재고를 증가시키는 기능
//  @Test
//  void updateOrInsert() {
//    // 테스트를 위한 임시 주문 가능한 상품 목록 생성
//    List<OrdersCart> orderableList = new ArrayList<>();
//    orderableList.add(new OrdersCart(1L, 10,1L, 1L)); // 상품 ID: 1, 점포 ID: 1, 수량: 100
//    orderableList.add(new OrdersCart(2L, 50,2L, 1L)); // 상품 ID: 2, 점포 ID: 1, 수량: 100
//
//    // 테스트를 위한 임시 StoreProduct 객체 생성
//    StoreProduct sp1 = new StoreProduct(1L, 1L);
//    sp1.setQnt(20); // 기존 재고 수량: 20
//    sp1.set_using(true);
//
//    StoreProduct sp2 = new StoreProduct(2L, 1L);
//    sp1.setQnt(20); // 기존 재고 수량: 20
//    sp1.set_using(true);
//
//    try {
//      storeProductService.updateOrInsert(orderableList);
//      System.out.println(orderableList);
//    } catch (Exception e) {
//      System.out.println("점포보유상품 재고 변경을 실패했습니다.");
//      e.printStackTrace();
//    }
//
//  }
}
