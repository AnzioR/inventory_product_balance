package com.ipb.mapper;

import com.ipb.domain.OrdersCart;
import com.ipb.frame.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface OrdersCartMapper extends MyMapper<Long, OrdersCart> {
}
