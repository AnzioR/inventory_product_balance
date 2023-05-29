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
public class ProductController {

  @Autowired
  ProductService productService;

//  @ApiOperation(value = "본사 상품 리스트")
  @GetMapping("/list")
  public List<Product> get() {
    try {
      List<Product> list = productService.get();
      return list;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

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

  @GetMapping("/detail")
  public Product detail(Long id) {
    try {
      return productService.get(id);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  @DeleteMapping("/delete")
  public void delete(Long id) {
    try {
      productService.remove(id);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

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
//  @GetMapping("/categoryname")
//  public List<Product> selectcategoryname(String categoryname) {
//    try {
//      return productService.selectcategoryname(categoryname);
//    } catch (Exception e) {
//      throw new RuntimeException(e);
//    }




























