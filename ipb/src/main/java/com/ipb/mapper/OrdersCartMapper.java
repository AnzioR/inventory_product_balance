package com.ipb.mapper;

import com.ipb.domain.OrdersCart;
import com.ipb.frame.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrdersCartMapper extends MyMapper<Long, OrdersCart> {

  //발주카트들을 리스트로 만들어주는 기능
  public List<OrdersCart> cartlist(Long id) throws Exception;

  //발주카트의 리스트 전체를 비우는 기능
  public void removecart(Long store_id) throws Exception;

  //상품등록 -

}
