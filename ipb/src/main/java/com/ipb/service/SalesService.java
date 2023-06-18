package com.ipb.service;


import com.ipb.domain.Sales;
import com.ipb.domain.StoreProduct;
import com.ipb.frame.MyService;
import com.ipb.mapper.SalesMapper;
import com.ipb.mapper.StoreProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesService implements MyService<Long, Sales> {
  @Autowired
  SalesMapper salesMapper;
  @Autowired
  StoreProductMapper storeProductMapper;


  @Override
  public void register(Sales sales) throws Exception {


    salesMapper.insert(sales);
    // 상품을 주문할때 store product가 음수가 되면 안되지만 음수가 되는 상황 발생
  }

  @Override
  public void modify(Sales sales) throws Exception {

  }

  @Override
  public void remove(Long id) throws Exception {
    Sales sales = salesMapper.select(id);
    salesMapper.salesdelete(sales);
    StoreProduct storeProduct = storeProductMapper.select(sales.getStore_product_id());
    storeProduct.setQnt(storeProduct.getQnt() + sales.getQnt());
    storeProductMapper.updateqnt(storeProduct);

  }

  @Override
  public Sales get(Long aLong) throws Exception {
    return null;
  }

  @Override
  public List<Sales> get() throws Exception {
    return salesMapper.selectall();
  }

  //스토어 아이디를 넣으면 해당 점포의 매출이 나온다. 날짜와 관계없이 뿌렸으니까 프론트에서 나누어서 가져올수있을거같다.
  public List<Sales> selectsalesbystore(Long store_id) throws Exception {
    return salesMapper.selectsalesbystore(store_id);
  }

  // 매장에서 판매가된 상품을 찾아와서 판매가 이루어졌던 상품에 수량과 매장의 해당 상품의 숫자를 거해 원상복구를 해줌
  public void salesdelete(Long id) throws Exception {
    Sales sales = salesMapper.select(id);
    StoreProduct storeProduct = storeProductMapper.select(sales.getStore_product_id());
    storeProduct.setQnt(storeProduct.getQnt() + sales.getQnt());
    storeProductMapper.updateqnt(storeProduct);
    salesMapper.salesdelete(sales);
  }

  public void sales(Sales sales, StoreProduct storeProduct) throws Exception {
    salesMapper.insert(sales);
    storeProductMapper.updateqnt(storeProduct);
  }
}
