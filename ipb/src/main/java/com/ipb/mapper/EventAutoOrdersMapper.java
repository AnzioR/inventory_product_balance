package com.ipb.mapper;


import com.ipb.domain.EventAutoOrders;
import com.ipb.domain.StockInfo;
import com.ipb.domain.StoreProduct;
import com.ipb.frame.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Mapper
@Repository
public interface EventAutoOrdersMapper extends MyMapper<Long, EventAutoOrders> {
    public void inserteventorder (Long id) throws Exception;
    public List<EventAutoOrders> searchDueEventProduct (String date) throws Exception;
}