package com.ipb.mapper;

import com.ipb.domain.OrdersCart;
import com.ipb.frame.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface OrdersCartMapper extends MyMapper<Long, OrdersCart> {

  //발주카트들을 리스트로 만들어주는 기능
  public List<OrdersCart> cartList(Long id) throws Exception;

  //발주카트의 리스트 전체를 비우는 기능
  public void removeCart(Long store_id) throws Exception;

  public List<OrdersCart> findOrderableOrders(Long store_id) throws Exception;

  public List<OrdersCart> findUnorderableOrders(Long store_id) throws Exception;

  public void removeCartList(List<OrdersCart> orderableList) throws Exception;

  OrdersCart selectByProductIdAndStoreId(Map<String, Object> params);

  void updateQnt(Map<String, Object> params);

  // exists 메서드 추가
  int exists(OrdersCart ordersCart);

}
