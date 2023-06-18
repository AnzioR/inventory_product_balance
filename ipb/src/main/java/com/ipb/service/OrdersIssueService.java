package com.ipb.service;


import com.ipb.domain.OrdersIssue;
import com.ipb.frame.MyService;
import com.ipb.mapper.OrdersIssueMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersIssueService implements MyService<Long, OrdersIssue> {

  @Autowired
  OrdersIssueMapper ordersIssueMapper;


  @Override
  public void register(OrdersIssue ordersIssue) throws Exception {
    ordersIssueMapper.insert(ordersIssue);
  }

  @Override
  public void modify(OrdersIssue ordersIssue) throws Exception {
    ordersIssueMapper.update(ordersIssue);
  }

  @Override
  public void remove(Long id) throws Exception {
    ordersIssueMapper.delete(id);
  }

  @Override
  public OrdersIssue get(Long id) throws Exception {
    return ordersIssueMapper.select(id);
  }

  @Override
  public List<OrdersIssue> get() throws Exception {
    return ordersIssueMapper.selectall();
  }

  public List<OrdersIssue> ordersissuealllist() throws Exception {
    return ordersIssueMapper.ordersissuealllist();
  }

  public List<OrdersIssue> ordersissuestore(Long store_id) throws Exception {
    return ordersIssueMapper.ordersissuestore(store_id);
  }

  public List<OrdersIssue> ordersissuedesc(Long orders_desc_id) throws Exception {
    return ordersIssueMapper.ordersissuedesc(orders_desc_id);
  }
}


