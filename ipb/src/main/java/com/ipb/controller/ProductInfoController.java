package com.ipb.controller;

import com.ipb.domain.Product;
import com.ipb.domain.ProductInfo;
import com.ipb.service.ProductInfoService;
import com.ipb.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productInfo")
public class ProductInfoController {

  @Autowired
  ProductInfoService productInfoService;

  @GetMapping("/list")
  public List<ProductInfo> get() {
    try {
      List<ProductInfo> list = productInfoService.get();
      return list;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @PostMapping("/add")
  public ProductInfo register(ProductInfo productInfo) {
    try {
      productInfoService.register(productInfo);
      return productInfo;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  @GetMapping("/detail")
  public ProductInfo detail(Long product_code) {
    try {
      return productInfoService.get(product_code  );
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  @DeleteMapping("/delete")
  public void delete(Long product_code) {
    try {
      productInfoService.remove(product_code);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @PutMapping("/update")
  public ProductInfo update(ProductInfo productInfo) {
    try {
      productInfoService.modify(productInfo);
      return productInfo;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }

  }
  @GetMapping("/categoryname")
  public List<ProductInfo> get(String categoryname) {
    try {
      List<ProductInfo> selectcategoryname = productInfoService.selectcategoryname(categoryname);
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




























