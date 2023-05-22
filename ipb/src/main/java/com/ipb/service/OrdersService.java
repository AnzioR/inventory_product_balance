package com.ipb.service;

import com.ipb.domain.Orders;
import com.ipb.domain.OrdersCart;
import com.ipb.domain.Product;
import com.ipb.domain.StoreProduct;
import com.ipb.frame.MyService;
import com.ipb.mapper.OrdersCartMapper;
import com.ipb.mapper.OrdersMapper;
import com.ipb.mapper.ProductMapper;
import com.ipb.mapper.StoreProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class OrdersService implements MyService<Long, Orders> {

  @Autowired
  OrdersMapper ordersMapper;

  @Autowired
  ProductMapper productMapper;

  @Autowired
  OrdersCartMapper ordersCartMapper;

  @Autowired
  StoreProductMapper storeProductMapper;

  @Autowired
  StoreProductService storeProductService;

  // storeId 추가로 인해 addOrder() 메서드로 대체
  @Override
  public void register(Orders orders) throws Exception {
    ordersMapper.insert(orders);
  }

  @Override
  public void modify(Orders orders) throws Exception {
    if(orders.getDelivery_id() == 1) {
      ordersMapper.update(orders);
    } else {
      System.out.println("배송준비중이 아니므로 수정할 수 없습니다.");
    }
  }

  //발주취소
  public void orderscancel(Orders orders) throws Exception {
    if(orders.getDelivery_id() == 1) {
      ordersMapper.orderscancel(orders);

      // Product 정보 조회
      Product product = productMapper.select(orders.getProduct_id());
      // Product 수량 변경
      productMapper.updateqnt(product);
    } else {
      System.out.println("배송준비중이 아니므로 취소할 수 없습니다.");
    }
  }

  @Override
  public void remove(Long id) throws Exception {
    ordersMapper.delete(id);
  }

  @Override
  public Orders get(Long id) throws Exception {
    return ordersMapper.select(id);
  }

  @Override
  public List<Orders> get() throws Exception {
    return ordersMapper.selectall();
  }

  //날짜로 주문내역 조회하기 (해당되는 날짜의 주문목록을 불러온다.)
  public List<Orders> searchdate(Date orders_date) throws Exception {
    //원하는 데이터 포맷 지정
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    //지정한 포맷으로 변환
    String strNowDate = simpleDateFormat.format(orders_date);

    return ordersMapper.searchdate(strNowDate);
  }

  //주문 상태조회 : 발주한 상품의 아이디로 주문 상태를 조회
  public Orders searchdeliverystatus(Long id) throws Exception {
    return ordersMapper.searchdeliverystatus(id);
  }

  //매장별 전체 발주 조회
  public List<Orders> selectstore(Long id) throws Exception {
    return ordersMapper.selectstore(id);
  }

  //매장별 상세 발주 조회
  public List<Orders> selectdetailstoreorders(Long id) throws Exception {
   return ordersMapper.selectdetailstoreorders(id);
  }

  //매장별 발주 수정
  public void updatestoreorders(Orders orders) throws Exception {
    ordersMapper.updatestoreorders(orders);
  }

  //매장에서 발주하기, 발주할 때 상품 수량도 변경함(안씀 오류 코드)
  public void orders(Orders orders, Product product) throws Exception {
    ordersMapper.insert(orders);
    productMapper.updateqnt(product);
  }

  //발주카트에 담긴 상품들을 리스트로 처리해서 발주로 이동시킴
  //(카트:상품번호,수량,발주매장번호에 추가로 배송상태, 발주일을 더해서 Orders로 보낸다.)
  public List<OrdersCart> addorder(Long store_id) throws Exception {
    // 장바구니 목록(상품번호, 수량, 점포번호)에서 발주를 진행한다.
    // 본사에 충분한 재고가 있는지 고려해야하고, (주문가능한 것만 조회)
    List<OrdersCart> orderableList = ordersCartMapper.findOrderableOrders(store_id);
    // 정상적으로 재고가 있는 경우에는, 발주가 정상 처리되고
    normalOrderProcess(orderableList);
    // 정상적으로 재고가 없는 경우에는, 따로 리스트를 반환합니다.
    List<OrdersCart> unorderableList = ordersCartMapper.findUnorderableOrders(store_id);
    System.out.println(unorderableList);
    if (unorderableList.size() == 0) {
      return null;
    } else {
      return unorderableList;
    }
  }

  // 본사재고보다 많은 발주가 들어간 목록들을 매개변수로 하여, 본사 보유 최대 수량을 발주
  public void maxOrder(List<OrdersCart> unOrderableList) throws Exception {
    // 장바구니 목록에 수량을 본사 최대 수량으로 변경
    for (OrdersCart oc : unOrderableList) {
      int maxQnt = productMapper.select(oc.getProduct_id()).getQnt();
      oc.setQnt(maxQnt);
    }
    // 정상 발주 처리 진행
    normalOrderProcess(unOrderableList);
  }

  // 정상적으로 발주처리되는 부분이 반복되기 때문에 하나의 메서드로 추출해서 이용하도록 작성
  private void normalOrderProcess(List<OrdersCart> orderRequestList) throws Exception {
    if (orderRequestList.size() > 0) {
      List<Orders> orderList = new ArrayList<Orders>();
      for (OrdersCart oc : orderRequestList) {
        orderList.add(new Orders(oc.getQnt(), oc.getProduct_id(), oc.getStore_id(), 1L));
      }//프로덕트 아이드를 통해서 productmapper.get(product_id).price*event_rate = store_price ,, 상태가 배송완료일떄 이걸 처리할게
      // (order 테이블 레코드 추가)
      ordersMapper.insertList(orderList);

      // 정상처리된 발주는 점포재고 테이블에 새롭게 추가되거나, qnt가 증가되고
      //지금은 1일떄 증가하지만 나중에 delivery_id =3 일떄 추가하는 걸로.

      storeProductService.updateOrInsert(orderRequestList);
      // cart에서 제거됩니다.
      //본사 재고는 delivery_id 가 2번일떄 줄어들어야해
      ordersCartMapper.removeCartList(orderRequestList);
      // 본사에서 재고도 줄입니다.
      productMapper.updateQntAll(orderRequestList);
    }
  }

}
