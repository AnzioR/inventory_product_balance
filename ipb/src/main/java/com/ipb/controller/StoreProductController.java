package com.ipb.controller;

import com.ipb.domain.*;
import com.ipb.service.NotificationService;
import com.ipb.service.ProductService;
import com.ipb.service.StoreProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/storeproduct")
public class StoreProductController {
  @Autowired
 NotificationService notificationService;
  @Autowired
  StoreProductService storeProductService;

//이건 그냥 스토어 id 와 관련없이 다 뿌려는거라서 필요가 없다.
//  @GetMapping("/list")
//  public List<StoreProduct> get() {
//    try {
//      List<StoreProduct> storeProducts = productService.get();
//      return storeProducts;
//    } catch (Exception e) {
//      throw new RuntimeException(e);
//    }
//  }
//이것도 발주의 개념이라 필요 없음
//  @PostMapping("/add")
//  public StoreProduct register(@RequestBody StoreProduct product) {
//    try {
//      productService.register(product);
//      return product;
//    } catch (Exception e) {
//      e.printStackTrace();
//      return null;
//    }
//  }


  //store_id 랑 category_id 는 안뿌려주는데 나중에 에러가 날지 안날지 궁금하다. 내생각에는 필요 없을거같다

  @GetMapping("/detail")
  public StoreProduct detail(Long id) {
    try {
      return storeProductService.get(id);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
// 이부분은 삭제인데 삭제는 되는것이 아니고 재고를 0으로 만드는 것이다.
  //DELETE 라서 리턴 타입이 보이드일건데 이건 삭제가 아니기 때문에 개선해야 할 내용
  @DeleteMapping("/delete")
  public void delete(Long id) {
    try {
      storeProductService.remove(id);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
//상품의 수량과 이용중을 업데이트 한다.
  @PutMapping("/update")
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
  public List<StoreProduct> allProductExpByStoreId(@PathVariable Long store_id) {
    try {
      List<StoreProduct> selectallexpStoreProduct = storeProductService.selectallexpStoreProduct(store_id);
      return selectallexpStoreProduct;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }



  //해당 점포보유상품을 카테고리 이름별로 분류
  @GetMapping("/categoryname/{store_id}")
  public List<StockInfo> getByCategory(String categoryname, @PathVariable Long store_id) {
    try {
      List<StockInfo> selectcategoryname = storeProductService.selectcategoryname(categoryname,store_id);
      return selectcategoryname;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
  @GetMapping("/categoryname/{store_id}/days")
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
  public List<StockInfo> searchByName(String txt, @PathVariable Long store_id) {
    try {
      List<StockInfo> searchstoreproduct = storeProductService.searchstoreproduct(txt,store_id);
      return searchstoreproduct;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  //상품을 자동발주 신청하기
  @PostMapping("/auto-order")
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

  //상품의 폐기를 누르면 상품수량=0, is_using=0 으로 변경한다.
  @PutMapping("/qntzero")
  public StoreProduct qntZero(Long id, @RequestBody StoreProduct storeProduct) {
    try {
      storeProductService.qntZero(storeProduct);
      return storeProduct;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }


//  @PostMapping("/messages")
//  public ResponseEntity<String> createStoreProduct(@RequestBody StoreProduct storeProduct) {
//    Long storeId = storeProduct.getStore_id();
//    // Store ID를 사용하여 상품 생성 로직을 구현
//    // ...
//
//    // MyBatis의 Mapper를 사용하여 상품 저장
//    // productMapper.saveProduct(product);
//
//    return ResponseEntity.ok("StoreProduct created successfully.");
//  }
//    @GetMapping("/expiring")
//    public ResponseEntity<List<StoreProduct>> getExpiringStoreProduct() {
//      // 유통기한이 임박한 상품 조회 로직을 구현
//      // ...
//      // MyBatis의 Mapper를 사용하여 유통기한이 임박한 상품 조회
//      // List<StoreProduct> expiringStoreProduct = StoreproductMapper.findExpiringStoreProduct(days);
//
//      List<StoreProduct> expiringStoreProduct = null;
//      return ResponseEntity.ok(null);
//    }

    // 다른 API 메서드들도 추가해야합니다.
  }






























