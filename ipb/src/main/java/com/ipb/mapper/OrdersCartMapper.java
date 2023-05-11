package com.ipb.mapper;

import com.ipb.domain.OrdersCart;
import com.ipb.frame.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrdersCartMapper extends MyMapper<Long, OrdersCart> {

  public List<OrdersCart> cartlist(Long id) throws Exception;
}
