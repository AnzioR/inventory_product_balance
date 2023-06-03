package com.ipb.mapper;

import com.ipb.domain.Orders;
import com.ipb.domain.StoreAutoOrders;
import com.ipb.frame.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StoreAutoOrdersMapper extends MyMapper<Long, StoreAutoOrders> {
  //store_product_id로 store_auto_orders 전체를 가져옴
  public StoreAutoOrders selectBySpi(Long store_product_id) throws Exception;

  //자동발주에 등록된 리스트 전체를 가져온다.
  public List<StoreAutoOrders> getAutoList() throws Exception;

  //자동발주 신청했던 기준재고수량, 최소재고수량을 변경
  public void changeQnt(StoreAutoOrders storeAutoOrders) throws Exception;

  //자동발주내역 삭제
  public void deleteAuto(Long id) throws Exception;

  //점포별 자동발주 내역 가져오기
  public List<StoreAutoOrders> selectAutoList(Long store_id) throws Exception;
}
