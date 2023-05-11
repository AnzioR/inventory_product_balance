package com.ipb.service;


import com.ipb.domain.*;
import com.ipb.frame.MyService;
import com.ipb.mapper.ProductMapper;
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

  @Override
  public void register(StoreProduct storeProduct) throws Exception {
    storeProductMapper.insert(storeProduct);
  }

  @Override
  public void modify(StoreProduct storeProduct) throws Exception {
    storeProductMapper.update(storeProduct);
  }

  @Override
  public void remove(Long id) throws Exception {
    storeProductMapper.delete(id);
  }

  @Override
  public StoreProduct get(Long id) throws Exception {
    return storeProductMapper.select(id);
  }
  public void updateqnt(StoreProduct storeProduct) throws Exception {
    storeProductMapper.updateqnt(storeProduct);
  }
  @Override
  public List<StoreProduct> get() throws Exception {
    return storeProductMapper.selectall();
  }
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

    public List<StoreProduct> selectall()throws Exception {
    return storeProductMapper.selectall();

  }
////  public void modifyQuantity(Long id, Integer newQuantity) throws Exception {
////    StoreProduct storeProduct = storeProductMapper.select(id);
////    storeProduct.changeQuantity(newQuantity);
////    storeProductMapper.update(storeProduct);
//  }
  public List<StockInfo> searchstoreproduct(String txt, Long store_id) throws Exception {
    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put("txt", txt);
    map.put("store_id", store_id);
    return storeProductMapper.searchstoreproduct(map);
  }
  public List<StockInfo> selectstoreproduct(Long store_id)throws Exception {
    return storeProductMapper.selectstoreproduct(store_id);

  }
}

