package com.ipb.controller;

import com.ipb.domain.Sales;
import com.ipb.service.SalesService;
import com.ipb.service.StoreProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sales")
public class SalesController {
    @Autowired
    SalesService salesService;
    @Autowired
    StoreProductService storeProductService;
//saleissue 가 발생하면 해당매출을 삭제 해야 하기 때문에 넣음 해당 상품의 id 값을 넣으면 STORE-PRODUCT 와 sales 의 수량이 조정됨
    @DeleteMapping("/delete")
    public void delete(Long id) {
        try {
            salesService.salesdelete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //각 매장별 매출 조회 기능 store_id 로 구분(본사 는 구별 가능하고 점포는 세션에 따라서 각 점포의 매출만 보이게끔)
    @GetMapping("/listbystore")
    public List<Sales> listByStore(Long store_id){
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
    @PostMapping("/add")
    public void add(@RequestBody Sales sales){
        try {
            storeProductService.updateqnt(sales);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
