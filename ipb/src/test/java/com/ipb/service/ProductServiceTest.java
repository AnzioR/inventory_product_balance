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
      Product product = new Product(null,7000, 6000, 2500, "2023-02-05",880770L);
      productService.register(product);
      System.out.println(product);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  void modify() {
    Product product = new Product(16L,9, 6500, 2500, "2023-02-05",880770L);
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
      productService.remove(1L);
    } catch(Exception e) {
      e.printStackTrace();
      System.out.println("삭제 오류 발생!");
    }
  }

  @Test
  void get() {
    Product product = null;
    try {
      product = productService.get(1L);
      System.out.println(product);
    } catch(Exception e) {
      e.printStackTrace();
      System.out.println("검색오류 발생!!!");
    }
  }
<<<<<<< HEAD
=======
  @Test
  void search() {

    try {
      List<Product> 오 = productService.search("비");
      System.out.println(오);
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
//@Test
//void testSelectCategoryName() {
//  String categoryName = "과자";
//  List<Product> list = null;
//  try {
//    list = productService.selectcategoryname(categoryName);
//    for(Product product : list) {
//      System.out.println(product);
//    }
//  } catch(Exception e) {
//    e.printStackTrace();
//    System.out.println("카테고리 검색 오류 발생!!");
//  }
//}
>>>>>>> 8390789fd066878e11f48d7a18a1e53ebb4d5c27
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

  //발주할 때, 수량을 변경해줌
  @Test
  void updateqnt() {
    try {
      Product product = productService.get(1L);
      product.setQnt(555);
      productService.updateqnt(product);
      System.out.println(product);
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("발주로 인한 재고 수량 변경에 실패했습니다.");
    }
  }

}


