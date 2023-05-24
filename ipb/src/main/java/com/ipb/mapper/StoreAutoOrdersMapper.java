package com.ipb.mapper;

import com.ipb.domain.Orders;
import com.ipb.domain.StoreAutoOrders;
import com.ipb.frame.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StoreAutoOrdersMapper extends MyMapper<Long, StoreAutoOrdersMapper> {
  //store_product_id로 store_auto_orders 전체를 가져옴
  public StoreAutoOrders selectBySpi(Long store_product_id) throws Exception;
}
