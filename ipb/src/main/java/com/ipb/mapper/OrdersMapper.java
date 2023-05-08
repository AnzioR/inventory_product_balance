package com.ipb.mapper;

import com.ipb.domain.Orders;
import com.ipb.frame.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Mapper
@Repository
public interface OrdersMapper extends MyMapper<Long, Orders> {
  public Orders searchdate(Date orders_date) throws Exception;
  public Orders searchdeliverystatus(Long id) throws Exception;
}
