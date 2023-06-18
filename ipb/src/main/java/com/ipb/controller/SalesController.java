package com.ipb.controller;

import com.ipb.domain.Sales;
import com.ipb.service.SalesService;
import com.ipb.service.StoreProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sales")
@Api(tags = {"매출"})
public class SalesController {
  @Autowired
  SalesService salesService;
  @Autowired
  StoreProductService storeProductService;

  //saleissue 가 발생하면 해당매출을 삭제 해야 하기 때문에 넣음 해당 상품의 id 값을 넣으면 STORE-PRODUCT 와 sales 의 수량이 조정됨
  @DeleteMapping("/delete")
  @ApiOperation(value = "매출 삭제", notes = "각 매출의 id로 부분삭제")
  public void delete(Long id) {
    try {
      salesService.salesdelete(id);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  //각 매장별 매출 조회 기능 store_id 로 구분(본사 는 구별 가능하고 점포는 세션에 따라서 각 점포의 매출만 보이게끔)
  @GetMapping("/listbystore")
  @ApiOperation(value = "각 점포별 매출 조회", notes = "각 점표별로 store_id로 매출 조회")
  public List<Sales> listByStore(Long store_id) {
    List<Sales> selectsalesbystore = null;
    try {
      selectsalesbystore = salesService.selectsalesbystore(store_id);
      return selectsalesbystore;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  //매장 전체의 매출 조회 기능 (본사 전용)
  @GetMapping("/list")
  @ApiOperation(value = "전체매장 매출 리스트 조회")
  public List<Sales> list() {
    List<Sales> sales = null;
    try {
      sales = salesService.get();
      return sales;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  // 매출을 등록하는 로직 salesmapper 에 자세히 설명이 되어있으며 등록과 동시에 storeproduct가 감소되어야 하기때문에
//  //storeproductservice를 이용 재고수량 보다 판매량이 많으면 -가 되는게 아닌 판매가 이루어 지지 않게 만듬
  @PostMapping("/add")
  @ApiOperation(value = "매출 등록", notes = "qnt,store_product_id,now() 로 등록을 한다")
  public void add(@RequestBody Sales sales) {
    try {
      storeProductService.updateqnt(sales);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
