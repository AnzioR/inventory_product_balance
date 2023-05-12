package com.ipb.mapper;

import com.ipb.domain.OrdersIssue;
import com.ipb.domain.SalesIssue;
import com.ipb.frame.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SalesIssueMapper extends MyMapper<Long, SalesIssue> {

}
