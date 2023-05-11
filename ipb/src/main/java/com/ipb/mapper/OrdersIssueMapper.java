package com.ipb.mapper;

import com.ipb.domain.OrdersIssue;
import com.ipb.frame.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface OrdersIssueMapper extends MyMapper<Long, OrdersIssue> {
}
