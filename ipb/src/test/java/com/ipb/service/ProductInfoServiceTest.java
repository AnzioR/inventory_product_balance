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
      ProductInfo productInfo = new ProductInfo(880772L, "한라봉", "제주도", "제주 한라봉","한라.img", 3L, "과자",20);
      productInfoService.register(productInfo);
      System.out.println(productInfo);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  void modify() {
    ProductInfo productInfo = new ProductInfo(880770L, "박카스", "오리온", "비타오백 가루","가루.img", 3L, "과자",20);
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
      productInfoService.remove(880789L);
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

//  @Test
//  void selectcategoryname() {
//    List<Product> categoryname = null;
//    try {
//      categoryname = productService.get();
//      for(Product product : categoryname) {
//        System.out.println(product);
//      }
//    } catch(Exception e) {
//      e.printStackTrace();
//      System.out.println("카테고리 검색 오류 발생!!");
//    }
//  }
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


