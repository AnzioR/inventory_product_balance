package com.ipb.service;

import com.ipb.domain.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ProductServiceTest {

  @Autowired
  ProductService productService;

  @Test
  void register() {
    try {
      Product product = new Product(null, "오렌지", 9999, 6000, 2500,1L, 20, null, 1L, null, "캘리포니아 오렌지", "오렌지.img");
      productService.register(product);
      System.out.println(product);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  void modify() {
    Product product = new Product(4L, "고소미", 100, 2500,1000,1L,12,null,1L,null, "튀기지않은 예감","예감.img" );
    try {
      productService.modify(product);
    } catch(Exception e) {
      e.printStackTrace();
      System.out.println("수정 오류가 발생!");
    }
  }

  @Test
  void remove() {
    try {
      productService.remove(4L);
    } catch(Exception e) {
      e.printStackTrace();
      System.out.println("삭제 오류 발생!");
    }
  }

  @Test
  void get() {
    Product product = null;
    try {
      product = productService.get(6L);
      System.out.println(product);
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
  String categoryName = "과자";
  List<Product> list = null;
  try {
    list = productService.selectcategoryname(categoryName);
    for(Product product : list) {
      System.out.println(product);
    }
  } catch(Exception e) {
    e.printStackTrace();
    System.out.println("카테고리 검색 오류 발생!!");
  }
}
  @Test
  void testGet() {
    List<Product> list = null;
    try {
      list = productService.get();
      for(Product product : list) {
        System.out.println(product);
      }
    } catch(Exception e) {
      e.printStackTrace();
      System.out.println("전체검색 오류 발생!!");
    }
  }

}


