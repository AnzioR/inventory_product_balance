package com.ipb.service;

import com.ipb.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class StoreProductServiceTest {

  @Autowired
  StoreProductService storeProductService;

  @Test
  void register() {
    try {
      StoreProduct storeProduct = new StoreProduct(null, 521, 1L, 2L, true);
      storeProductService.register(storeProduct);
      System.out.println(storeProduct);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  void modify() {
    StoreProduct storeProduct = new StoreProduct(7L, 334, 1L, 1L, true);
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
      storeProductService.remove(9L);
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("삭제 오류 발생!");
    }
  }

  @Test
  void get() {
    StoreProduct storeProduct = null;
    try {
      storeProduct = storeProductService.get(1L);
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
      storeProduct = storeProductService.get(3L);
      System.out.println(storeProduct);
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("검색오류가 발생했습니다.");
    }

  }

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

  @Test
  void testSearchBoard() {
    List<StockInfo> result = null;
    try {
      result = storeProductService.searchstoreproduct("독도",2L);
      for (StockInfo stockInfo : result) {
        System.out.println(stockInfo);
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  void selectstoreproduct() {
    List<StockInfo> result = null;
    try {
        result = storeProductService.selectstoreproduct(2L);
      for (StockInfo stockInfo : result) {
        System.out.println(stockInfo);
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
