package com.ipb.controller;

import com.ipb.domain.Product;
import com.ipb.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Api(value = "productController v1" ,tags = "본사 상품 API" )
@RestController
@RequestMapping("/product")
@Api(tags = {"본사 상품"})
public class ProductController {

  @Autowired
  ProductService productService;

  //  본사 상품 전체를 list로 뿌려준다.
  @ApiOperation(value = "본사 상품 리스트")
  @GetMapping("/list/{store_id}")
  public List<Product> allProductByStoreId(@PathVariable Long store_id) {
    try {
      List<Product> orderlistproduct = productService.orderlistproduct(store_id);
      return orderlistproduct;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  //  본사 상품을 등록한다.
  @ApiOperation(value = "본사 상품 추가", notes = "등록정보 : Long id,Long product_info_id,int qnt,int price,int cost,String exp")
  @PostMapping("/add")
  public Product register(@RequestBody Product product) {
    try {
      productService.register(product);
      return product;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  //  본사 상품을 상세 조회한다.
  @ApiOperation(value = "본사 상품 상세조회")
  @GetMapping("/detail")
  public Product detail(Long id) {
    try {
      return productService.get(id);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  //  본사 상품을 삭제한다
  @ApiOperation(value = "본사 상품 삭제")
  @DeleteMapping("/delete")
  public void delete(Long id) {
    try {
      productService.remove(id);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  //  본사 상품을 업데이트 한다
  @ApiOperation(value = "본사 상품 수정")
  @PutMapping("/update")
  public Product update(@RequestBody Product product) {
    try {
      productService.modify(product);
      return product;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }

  }

  //  본사 상품을 카테고리 별로 조회를 한다.
  @ApiOperation(value = "본사 상품 카테고리별 조회")
  @GetMapping("/categoryname")
  public List<Product> get(String categoryname) {
    try {
      List<Product> selectcategoryname = productService.selectcategoryname(categoryname);
      return selectcategoryname;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}




























