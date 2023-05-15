package com.ipb.mapper;

import com.ipb.domain.Orders;
import com.ipb.domain.Product;
import com.ipb.frame.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Mapper
@Repository
public interface OrdersMapper extends MyMapper<Long, Orders> {
  //발주 취소
  public void orderscancel(Orders orders) throws Exception;

  //본사에서 날짜를 선택해서 지정된 날짜에 해당하는 발주내역을 조회
  public List<Orders> searchdate(String orders_date) throws Exception;

  //발주한 상품의 배송 상태를 조회
  public Orders searchdeliverystatus(Long id) throws Exception;

  //매장별 전체 발주 조회
  public List<Orders> selectstore(Long id) throws Exception;

  //매장별 상세 발주 조회
  public List<Orders> selectdetailstoreorders(Long id) throws Exception;

  //매장별 발주 수정
  public void updatestoreorders(Orders orders) throws Exception;

  //발주하기 : 발주카트에서 발주로 이동, 상품재고 감소, 점포상품재고 증가, 해당 점포아이디의 발주카트 전체 비우기
  public void addorder(Long store_id) throws Exception;

  //주문리스트 추가
  public void insertList(List<Orders> orderList) throws Exception;
}
