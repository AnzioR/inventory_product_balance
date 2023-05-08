package com.ipb.service;

import com.ipb.domain.OrdersCart;
import com.ipb.frame.MyService;
import com.ipb.mapper.OrdersCartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersCartService implements MyService<Long, OrdersCart> {

  @Autowired
  OrdersCartMapper ordersCartMapper;

  @Override
  public void register(OrdersCart ordersCart) throws Exception {
    ordersCartMapper.insert(ordersCart);
  }

  @Override
  public void modify(OrdersCart ordersCart) throws Exception {
    ordersCartMapper.update(ordersCart);
  }

  @Override
  public void remove(Long id) throws Exception {
    ordersCartMapper.delete(id);
  }

  @Override
  public OrdersCart get(Long id) throws Exception {
    return ordersCartMapper.select(id);
  }

  @Override
  public List<OrdersCart> get() throws Exception {
    return ordersCartMapper.selectall();
  }
}
