package com.ipb.service;


import com.ipb.domain.*;
import com.ipb.frame.MyService;
import com.ipb.mapper.ProductMapper;
import com.ipb.mapper.SalesMapper;
import com.ipb.mapper.StoreProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreProductService implements MyService <Long, StoreProduct> {

  @Autowired
  StoreProductMapper storeProductMapper;
  @Autowired
  SalesService salesService;

  //  store product 등록
  @Override
  public void register(StoreProduct storeProduct) throws Exception {
    storeProductMapper.insert(storeProduct);
  }

  //  store product 수정
  @Override
  public void modify(StoreProduct storeProduct) throws Exception {
    storeProductMapper.update(storeProduct);
  }
  //  store product 삭제
  @Override
  public void remove(Long id) throws Exception {
    storeProductMapper.delete(id);
  }

  @Override
  public StoreProduct get(Long id) throws Exception {
    return storeProductMapper.select(id);
  }

  //  store product 수량변경 (재고 수량보다 많은 주문 시 작동하지 않도록 수정 완료)
  public void updateqnt(Sales sales) throws Exception {
    StoreProduct product = get(sales.getStore_product_id());
    int real_stock = product.getQnt();
    int order_stock = sales.getQnt();

    if (real_stock < order_stock) {
      throw new Exception("재고 수량보다 많은 주문은 불가능합니다");
    } else {
      product.setQnt(real_stock - order_stock);
      System.out.println(">>>>>>>>>>>>>>>>>바뀐 재고량 ==" + product.getQnt());
      storeProductMapper.updateqnt(product);
      salesService.register(sales);
    }
  }
  //  store product
  @Override
  public List<StoreProduct> get() throws Exception {
    return storeProductMapper.selectall();
  }

  //  점포 재고를 카테고리별로 가져옴
  public List<StockInfo> selectcategoryname(String categoryname, Long store_id) throws Exception {
//    return storeProductMapper.selectcategoryname(categoryname,store_id);
//  }
    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put("category_name", categoryname);
    map.put("store_id", store_id);
    System.out.println(map);
    List<StockInfo> storeProducts = storeProductMapper.selectcategoryname(map);

    return storeProducts;
  }
//재고 전체보기
    public List<StoreProduct> selectall()throws Exception {
    return storeProductMapper.selectall();

  }
////  public void modifyQuantity(Long id, Integer newQuantity) throws Exception {
////    StoreProduct storeProduct = storeProductMapper.select(id);
////    storeProduct.changeQuantity(newQuantity);
////    storeProductMapper.update(storeProduct);
//  }
  //점포재고에서 검색을 해서 상품을 가져올수있음
  public List<StockInfo> searchstoreproduct(String txt, Long store_id) throws Exception {
    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put("txt", txt);
    map.put("store_id", store_id);
    return storeProductMapper.searchstoreproduct(map);
  }
  //store id 로 각각의 점포의 재고를 조회 할 수 있음
  public List<StoreProduct> selectstoreproduct(Long store_id)throws Exception {
    return storeProductMapper.selectstoreproduct(store_id);
  }
  //발주할 때, 점포의 재고수량을 변경
  public void storeupdateqnt(StoreProduct storeProduct) throws Exception {
    storeProductMapper.storeupdateqnt(storeProduct);
  }
  //store_id와 product_id를 조회하는 기능
  public StoreProduct getstoreproductfromstoreidandproductid(StoreProduct storeProduct) throws Exception {
    StoreProduct st = storeProductMapper.getstoreproductfromstoreidandproductid(storeProduct);
    return st;
  }
}

