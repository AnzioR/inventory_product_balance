package com.ipb.mapper;

import com.ipb.domain.EventAutoOrders;
import com.ipb.domain.Orders;
import com.ipb.domain.Sales;
import com.ipb.frame.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface SalesMapper extends MyMapper<Long, Sales> {
  //발주 취소
  public List<Sales> selectsalesbystore(Long store_id) throws Exception;

  public void salesdelete(Sales sales) throws Exception;

  public List<EventAutoOrders> getTotalQntByEventIdAndProdCode(Long previousEventId, Long productCode) throws Exception;
}
