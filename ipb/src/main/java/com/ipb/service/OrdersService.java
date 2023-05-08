package com.ipb.service;

import com.ipb.domain.Orders;
import com.ipb.frame.MyService;
import com.ipb.mapper.OrdersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrdersService implements MyService<Long, Orders> {

  @Autowired
  OrdersMapper ordersMapper;

  @Override
  public void register(Orders orders) throws Exception {
    ordersMapper.insert(orders);
  }

  @Override
  public void modify(Orders orders) throws Exception {
    if(orders.getDelivery_id() == 1) {
      ordersMapper.update(orders);
    } else {
      System.out.println("배송준비중이 아니므로 수정할 수 없습니다.");
    }
  }

  @Override
  public void remove(Long id) throws Exception {
    ordersMapper.delete(id);
  }

  @Override
  public Orders get(Long id) throws Exception {
    return ordersMapper.select(id);
  }

  @Override
  public List<Orders> get() throws Exception {
    return ordersMapper.selectall();
  }

  //날짜로 주문내역 조회하기 (해당되는 날짜의 주문목록을 불러온다.)
  public Orders searchdate(Date orders_date) throws Exception {
    return ordersMapper.searchdate(orders_date);
  }

  //주문 상태조회 : 발주한 상품의 아이디로 주문 상태를 조회
  public Orders searchdeliverystatus(Long id) throws Exception {
    return ordersMapper.searchdeliverystatus(id);
  }
}
