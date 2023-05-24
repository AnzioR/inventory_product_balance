package com.ipb.service;

import com.ipb.domain.OrdersCart;
import com.ipb.domain.Store;
import com.ipb.frame.MyService;
import com.ipb.mapper.StoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreService implements MyService<Long, Store> {

  @Autowired
  StoreMapper storeMapper;


  @Override
  public OrdersCart register(Store store) throws Exception {
    storeMapper.insert(store);
    return null;
  }

  @Override
  public void modify(Store store) throws Exception {
    storeMapper.update(store);
  }

  @Override
  public void remove(Long id) throws Exception {
    storeMapper.delete(id);
  }

  @Override
  public Store get(Long id) throws Exception {
    return storeMapper.select(id);
  }

  @Override
  public List<Store> get() throws Exception {
    return storeMapper.selectall();
  }
}
