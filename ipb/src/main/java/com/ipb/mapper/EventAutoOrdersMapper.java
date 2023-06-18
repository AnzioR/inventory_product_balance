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
  public void inserteventorder(Long id) throws Exception;

  public List<EventAutoOrders> searchDueEventProduct(String date) throws Exception;

  public void removeEventList(List<EventAutoOrders> eventAutoOrders) throws Exception;

  public List<EventAutoOrders> listByStoreId(Long store_id) throws Exception;

  public void updateQnt(EventAutoOrders eventAutoOrders) throws Exception;

  public void updateQntByEvent(Long id, Long storeId, int ceil) throws Exception;
}