package com.ipb.controller;

import com.ipb.domain.Product;
import com.ipb.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

  @Autowired
  ProductService productService;

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
  public Product register(Product product) {
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
  public Product update(Product product) {
    try {
      productService.modify(product);
      return product;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }

  }
}