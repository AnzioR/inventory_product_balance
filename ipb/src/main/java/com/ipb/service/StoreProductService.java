package com.ipb.service;


import com.ipb.domain.*;
import com.ipb.frame.MyService;
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

  //  store product 수량변경 (재고 수량보다 많은 주문 시 작동하지 않도록 수정 완료) - sales 에서 사용중...
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
  //today 기준으로 날짜별 상품 확인 (D-3,D-4..)
  public List<StockInfo> selectexpAndExpiringSoon(String categoryname, Long store_id, int days) throws Exception {
    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put("category_name", categoryname);
    map.put("store_id", store_id);
    map.put("days", days);
    System.out.println(map);
    List<StockInfo> storeProducts = storeProductMapper.selectexpAndExpiringSoon(map);

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
  public void storeUpdateQnt(StoreProduct storeProduct) throws Exception {
    storeProductMapper.storeUpdateQnt(storeProduct);
  }

  //store_id와 product_id를 조회하는 기능
  public StoreProduct getStoreProductFromStoreIdAndProductId(StoreProduct storeProduct) throws Exception {
    StoreProduct st = storeProductMapper.getStoreProductFromStoreIdAndProductId(storeProduct);
    return st;
  }

  //발주가 성공했을 때, 점포보유상품의 재고를 증가시키는 기능
  // //----> 배송상태가 변경되었을 때 재고 증가 + store_price, event_rate 등록으로 변경
//  public void updateOrInsert(List<OrdersCart> orderableList) throws Exception {
//    for(OrdersCart orderableCart : orderableList) {
//      StoreProduct sp = new StoreProduct(orderableCart.getProduct_id(), orderableCart.getStore_id());
//
//      // 만약 is_using 관련해 이슈가 있다면... 이 부분에 수정이 필요!
//      StoreProduct exist_sp = storeProductMapper.getStoreProductFromStoreIdAndProductId(sp);
//      // storeProduct 테이블에 존재하지 않으면
//      if (exist_sp == null) {
//        // 추가
//        sp.setQnt(orderableCart.getQnt());
//        sp.set_using(true);
//        storeProductMapper.insert(sp);
//        // 존재한다면
//      } else {
//        // 수량 변경
//        exist_sp.setQnt(exist_sp.getQnt() + orderableCart.getQnt());
//        storeProductMapper.updateqnt(exist_sp);
//      }
//    }
//  }


  //배송상태가 변경되었을 때, 점포보유상품의 재고를 증가시키는 기능 ing...
//  public StoreProduct storeupdateqnt(Orders orders) throws Exception {
//    if()
//    //store_id와 product_id 조회를 이용하여 발주된 상품을 찾아서 해당 재고 수량을 변경해준다.
//    StoreProduct sp = storeProductMapper.getstoreproductfromstoreidandproductid(orders.getProduct_id(), orders.getStore_id());
//    storeProductMapper.storeupdateqnt(storeProduct);
//    return sp;
//  }


}

