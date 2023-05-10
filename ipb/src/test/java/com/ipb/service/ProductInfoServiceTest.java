package com.ipb.service;


import com.ipb.domain.ProductInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ProductInfoServiceTest {

  @Autowired
  ProductInfoService productInfoService;

  @Test
  void register() {
    try {
      ProductInfo productInfo = new ProductInfo(880364L, "흑돼지", "제주도", "제주 흑돼지","흑돼지.img", 4L, 20);
      productInfoService.register(productInfo);
      System.out.println(productInfo);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  void modify() {
    ProductInfo productInfo = new ProductInfo(880364L, "흑돼지", "제주도", "제주 흑돼지","흑돼지.img", 5L, 20);
    try {
      productInfoService.modify(productInfo);
    } catch(Exception e) {
      e.printStackTrace();
      System.out.println("수정 오류가 발생!");
    }
  }

  @Test
  void remove() {
    try {
      productInfoService.remove(2L);
    } catch(Exception e) {
      e.printStackTrace();
      System.out.println("삭제 오류 발생!");
    }
  }

  @Test
  void get() {
    ProductInfo productInfo = null;
    try {
      productInfo = productInfoService.get(880771L);
      System.out.println(productInfo);
    } catch(Exception e) {
      e.printStackTrace();
      System.out.println("검색오류 발생!!!");
    }
  }

@Test
void testSelectCategoryName() {
  String categoryName = "과일";
  List<ProductInfo> list = null;
  try {
    list = productInfoService.selectcategoryname(categoryName);
    for(ProductInfo productInfo : list) {
      System.out.println(productInfo);
    }
  } catch(Exception e) {
    e.printStackTrace();
    System.out.println("카테고리 검색 오류 발생!!");
  }
}
  @Test
  void testGet() {
    List<ProductInfo> list = null;
    try {
      list = productInfoService.get();
      for(ProductInfo productInfo : list) {
        System.out.println(productInfo);
      }
    } catch(Exception e) {
      e.printStackTrace();
      System.out.println("전체검색 오류 발생!!");
    }
  }

}


