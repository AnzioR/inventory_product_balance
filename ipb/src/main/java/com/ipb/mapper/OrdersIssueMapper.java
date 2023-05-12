package com.ipb.mapper;

import com.ipb.domain.OrdersIssue;
import com.ipb.domain.StockInfo;
import com.ipb.frame.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrdersIssueMapper extends MyMapper<Long, OrdersIssue> {

  public List<OrdersIssue> ordersissueproductlist(Long store_id) throws Exception;
}
