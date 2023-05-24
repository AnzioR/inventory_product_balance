package com.ipb.service;

import com.ipb.domain.EventProduct;
import com.ipb.domain.OrdersCart;
import com.ipb.frame.MyService;
import com.ipb.mapper.EventProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventProductService implements MyService<Long, EventProduct> {

  @Autowired
  EventProductMapper eventProductMapper;

  @Override
  public OrdersCart register(EventProduct eventProduct) throws Exception {

    return null;
  }

  @Override
  public void modify(EventProduct eventProduct) throws Exception {

  }

  @Override
  public void remove(Long aLong) throws Exception {

  }

  @Override
  public EventProduct get(Long aLong) throws Exception {
    return null;
  }

  @Override
  public List<EventProduct> get() throws Exception {
    return null;
  }

  public List<EventProduct> searchEventProduct(String name) throws Exception {
    return eventProductMapper.searchEventProduct(name);
  }
}
