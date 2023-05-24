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
    if (ordersCart.getQnt() == 0) {
      ordersCart.setQnt(1);
    }
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

  //로그인 유저의 발주카트에 담긴 상품을 리스트로 가져온다.
  public List<OrdersCart> cartList(Long id) throws Exception {
    return ordersCartMapper.cartList(id);
  }

  //store_id에 해당되는 카트를 삭제한다.
  public void removeCart(Long store_id) throws Exception {
    ordersCartMapper.removeCart(store_id);
  }

  //카트 리스트를 삭제한다.
  public void removeCartList(List<OrdersCart> orderableList) throws Exception {
    ordersCartMapper.removeCartList(orderableList);
  }
}
