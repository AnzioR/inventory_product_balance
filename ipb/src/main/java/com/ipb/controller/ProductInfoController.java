package com.ipb.controller;

import com.ipb.domain.Product;
import com.ipb.domain.ProductInfo;
import com.ipb.service.ProductInfoService;
import com.ipb.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productInfo")
@Api(tags = {"본사 상품 정보"})
public class ProductInfoController {

  @Autowired
  ProductInfoService productInfoService;

  //  본사 상품 정보를 리스트로 불러옴
  @GetMapping("/list")
  @ApiOperation(value = "본사 상품 정보 리스트")
  public List<ProductInfo> get() {
    try {
      List<ProductInfo> list = productInfoService.get();
      return list;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  //  본사 상품 정보를 등록함
  @PostMapping("/add")
  @ApiOperation(value = "본사 상품 정보 등록")
  public ProductInfo register(@RequestBody ProductInfo productInfo) {
    try {
      productInfoService.register(productInfo);
      return productInfo;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  //  본사 상품 정보를 product_code로 조회하여 상세 볼 수 있다
  @GetMapping("/detail")
  @ApiOperation(value = "본사 상품 정보 상세보기")
  public ProductInfo detail(Long product_code) {
    try {
      return productInfoService.get(product_code);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  //  본사 상품 정보를 삭제한다.
  @DeleteMapping("/delete")
  @ApiOperation(value = "본사 상품 정보 삭제")
  public void delete(Long product_code) {
    try {
      productInfoService.remove(product_code);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  //  본사 상품 정보를 수정한다
  @PutMapping("/update")
  @ApiOperation(value = "본사 상품 정보 수정", notes = "product_code로 String name,String brand,String detail,int box_qnt,int safe_qnt,String imgname 수정 가능하다")
  public ProductInfo update(@RequestBody ProductInfo productInfo) {
    try {
      productInfoService.modify(productInfo);
      return productInfo;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }

  }

  //  본사 상품 정보를 카테고리로 조회 한다
  @GetMapping("/categoryname")
  @ApiOperation(value = "본사 상품 정보 카테고리별 조회")
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


























