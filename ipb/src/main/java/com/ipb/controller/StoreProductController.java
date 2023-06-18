package com.ipb.controller;

import com.ipb.domain.*;
import com.ipb.service.NotificationService;
import com.ipb.service.ProductService;
import com.ipb.service.StoreProductIssueService;
import com.ipb.service.StoreProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/storeproduct")
@Api(tags = {"점포 보유 상품"})
public class StoreProductController {
  @Autowired
  NotificationService notificationService;

  @Autowired
  StoreProductIssueService storeProductIssueService;

  @Autowired
  StoreProductService storeProductService;

  //store_id 랑 category_id 는 안뿌려주는데 나중에 에러가 날지 안날지 궁금하다. 내생각에는 필요 없을거같다

  @GetMapping("/detail")
  @ApiOperation(value = "점포 보유 상품 상세보기")
  public StoreProduct detail(Long id) {
    try {
      return storeProductService.get(id);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  @DeleteMapping("/delete")
  @ApiOperation(value = "점포 보유 상품 수량 0으로 변경", notes = "삭제가 아니라 수량을 0으로 변경한다")
  public void delete(Long id) {
    try {
      storeProductService.remove(id);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @PutMapping("/update")
  @ApiOperation(value = "점포 보유 상품 수정", notes = "store_product_id로 qnt,is_using,store_price,event_rate,is_auto 수정 가능하다")
  public StoreProduct update(@RequestBody StoreProduct product) {
    try {
      storeProductService.modify(product);
      return product;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  //해당점포의 보유상품 전체조회
  @GetMapping("/list/{store_id}")
  @ApiOperation(value = "점포 보유상품 전체조회", notes = "store_id로 점포 보유상품을 전체 조회한다")
  public List<StoreProduct> allProductByStoreId(@PathVariable Long store_id) {
    try {
      List<StoreProduct> selectstoreproduct = storeProductService.selectstoreproduct(store_id);
      return selectstoreproduct;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  @GetMapping("/listexp/{store_id}")
  @ApiOperation(value = "점포 보유 상품 유통기한 목록", notes = "store_id로 점포 보유상품 유통기한 목록을 전체 조회한다")
  public List<StoreProduct> allProductExpByStoreId(@PathVariable Long store_id) {
    try {
      List<StoreProduct> selectallexpStoreProduct = storeProductService.selectallexpStoreProduct(store_id);
      return selectallexpStoreProduct;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  @GetMapping("/categoryname/{store_id}")
  @ApiOperation(value = "카테고리별 조회", notes = "store_id로 점포 보유 상품을 카테고리별로 조회한다")
  public List<StockInfo> getByCategory(String categoryname, @PathVariable Long store_id) {
    try {
      List<StockInfo> selectcategoryname = storeProductService.selectcategoryname(categoryname, store_id);
      return selectcategoryname;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  @GetMapping("/categoryname/{store_id}/days")
  @ApiOperation(value = "카테고리별 유통기한 조회", notes = "store_id,카테고리,날짜를 검색하면 today 기준으로 원하는 그 날짜의 이하 상품이 조회된다")
  public List<StockInfo> selectexpAndExpiringSoon(
      @PathVariable Long store_id,
      @RequestParam("categoryname") String categoryname,
      @RequestParam("days") int days
  ) {
    try {
      HashMap<String, Object> map = new HashMap<String, Object>();
      map.put("category_name", categoryname);
      map.put("store_id", store_id);
      map.put("days", days);

      List<StockInfo> selectexpAndExpiringSoon = storeProductService.selectexpAndExpiringSoon(categoryname, store_id, days);
      return selectexpAndExpiringSoon;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  //해당 점포 보유 상품을 검색해서 찾아오기 (리스트로 가능)
  @GetMapping("/searchname/{store_id}")
  @ApiOperation(value = "점포 보유 상품 검색", notes = "store_id로 점포 보유 상품을 검색한다")
  public List<StockInfo> searchByName(String txt, @PathVariable Long store_id) {
    try {
      List<StockInfo> searchstoreproduct = storeProductService.searchstoreproduct(txt, store_id);
      return searchstoreproduct;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  //상품을 자동발주 신청하기
  @PostMapping("/auto-order")
  @ApiOperation(value = "점포 보유 상품 자동발주", notes = "점포 보유 상품을 자동 발주를 신청한다")
  public ResponseEntity<?> autoOrderRequest(@RequestBody StoreAutoOrders sao) {
    try {
      if (sao.getQnt() < sao.getMin_qnt()) {
        return ResponseEntity.status(300).build();
      }
      storeProductService.autoOrderRequest(sao);
      return ResponseEntity.ok().build();
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.notFound().build();
    }
  }


  @PutMapping("/qntzero")
  @ApiOperation(value = "폐기시 수량 0", notes = "상품의 폐기를 누르면 상품수량=0, is_using=0 으로 변경한다")
  public StoreProduct qntZero(Long id, @RequestBody StoreProduct storeProduct) {
    try {
      //폐기한 상품을 폐기 테이블에 등록해준다.
      storeProductIssueService.register(new StoreProductIssue(storeProduct.getId(), storeProduct.getQnt(), 5L, new Date()));
      storeProductService.qntZero(storeProduct);
      return storeProduct;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

}






























