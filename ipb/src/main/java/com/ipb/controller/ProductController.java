package com.ipb.controller;

import com.ipb.domain.Product;
import com.ipb.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

  @Autowired
  ProductService productService;

  @GetMapping("/productlist")
  public List<Product> get() {
    try {
      List<Product> list = productService.get();
      return list;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }
}
