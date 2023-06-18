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

  //배송중인 경우, 점포에서 주문한 발주 수량을 수정
  @Override
  public void modify(Orders orders) throws Exception {
    if (orders.getDelivery_id() == 1) {
      ordersMapper.update(orders);
    } else {
    }
  }

  //발주취소 ---안씀
  public void ordersCancel(Orders orders) throws Exception {
    if (orders.getDelivery_id() == 1) {
      ordersMapper.ordersCancel(orders);

      // Product 정보 조회
      Product product = productMapper.select(orders.getProduct_id());
      // Product 수량 변경
      productMapper.updateQnt(product);
    } else {
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
  public List<Orders> searchDate(Date orders_date) throws Exception {
    //원하는 데이터 포맷 지정
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    //지정한 포맷으로 변환
    String strNowDate = simpleDateFormat.format(orders_date);

    return ordersMapper.searchDate(strNowDate);
  }

  //주문 상태조회 : 발주한 상품의 아이디로 주문 상태를 조회
  public Orders searchDeliveryStatus(Long id) throws Exception {
    return ordersMapper.searchDeliveryStatus(id);
  }

  //매장별 전체 발주 조회(본사에서 사용함)
  public List<Orders> selectStore(Long id) throws Exception {
    return ordersMapper.selectStore(id);
  }

  //매장의 상세 발주 조회(본사에서 사용함)
  public Orders selectDetailStoreOrders(Long id) throws Exception {
    return ordersMapper.selectDetailStoreOrders(id);
  }

  public List<Orders> selectStoreOrdersByStoreId(Orders orders) throws Exception {
    return ordersMapper.selectStoreOrdersByStoreId(orders);
  }

  ;

  //매장별 발주 수정(본사에서 사용함)
  public void updateStoreOrders(Orders orders) throws Exception {
    ordersMapper.updateStoreOrders(orders);
  }

  //매장에서 발주하기, 발주할 때 상품 수량도 변경함(안씀 오류 코드)
//  public void orders(Orders orders, Product product) throws Exception {
//    ordersMapper.insert(orders);
//    productMapper.updateQnt(product);
//  }


  //발주카트에 담긴 상품들을 리스트로 처리해서 발주로 이동시킴
  //(카트: 상품번호,수량,발주매장번호에 추가로 배송상태, 발주일을 더해서 Orders로 보낸다.)
  public List<OrdersCart> addOrder(Long store_id) throws Exception {
    // 장바구니 목록(상품번호, 수량, 점포번호)에서 발주를 진행한다.
    // 본사에 충분한 재고가 있는지 고려해야하고, (주문가능한 것만 조회)
    List<OrdersCart> orderableList = ordersCartMapper.findOrderableOrders(store_id);
    // 정상적으로 재고가 있는 경우에는, 발주가 정상 처리되고
    normalOrderProcess(orderableList);
    // 정상적으로 재고가 없는 경우에는, 따로 리스트를 반환합니다.
    List<OrdersCart> unorderableList = ordersCartMapper.findUnorderableOrders(store_id);
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
      if (oc.getQnt() > maxQnt) {
        oc.setQnt(maxQnt);
      }
    }
    // 정상 발주 처리 진행
    normalOrderProcess(unOrderableList);
  }

  // 정상적으로 발주처리되는 부분이 반복되기 때문에 하나의 메서드로 추출해서 이용하도록 작성
  private void normalOrderProcess(List<OrdersCart> orderRequestList) throws Exception {
    if (orderRequestList.size() > 0) {
      //오더를 담고 있는 리스트를 만든다
      List<Orders> orderList = new ArrayList<Orders>();
      for (OrdersCart oc : orderRequestList) {
        orderList.add(new Orders(oc.getQnt(), oc.getProduct_id(), oc.getStore_id(), 1L, 1L));
      }
      // (order 테이블 레코드 추가)
      ordersMapper.insertList(orderList);

      // cart에서 제거됩니다.
      ordersCartMapper.removeCartList(orderRequestList);

      //상품재고, 점포상품재고 관련기능은 삭제 -> 배송상태 변경에 따라 재고 변경기능 수정함
    }
  }


  //발주된 상품의 배송상태 변경 && 재고상태 변경
  public Orders updateDeliveryStatus(Orders orders) throws Exception {
    //orders_id로 발주를 찾아서 배송상태를 바꾼다.
    ordersMapper.updateDeliveryStatus(orders);

    //상품의 수량을 업데이트를 위해 발주에서 product_id를 찾는다.
    Product product = productMapper.select(orders.getProduct_id());
    if (orders.getDelivery_id() == 2) {//배송중인 경우, 본사의 재고 수량 감소

      int orders_qnt = orders.getQnt(); //발주 수량을 가져온다.
      product.setQnt(product.getQnt() - orders_qnt); //발주가능상품의 재고를 기존 수량에서 발주수량만큼 감소시킨다.
      productMapper.updateQnt(product); //발주가능상품의 재고수량을 업데이트 해준다.

    } else if (orders.getDelivery_id() == 3) {//배송완료인 경우, 점포의 재고 수량 증가
      //점포에 처음 들어오는 상품이거나 들어왔던 상품이겠찌? -> product_id와 store_id로 store_product를 조회한다.
      StoreProduct storeProduct = storeProductMapper.getStoreProductFromStoreIdAndProductId(new StoreProduct(orders.getProduct_id(), orders.getStore_id()));

      double eventRate = 1; //이벤트할인율은 1을 기본값으로 갖는다.
      int storePrice = (int) Math.round(product.getPrice() * eventRate); //상품의 가격은 발주가능상품가격 * 이벤트할인율으로 담는다.

      if (storeProduct == null) {
        //처음 들어오는 상품 -> 점포에 추가
        storeProduct = new StoreProduct(orders.getQnt(), orders.getProduct_id(), orders.getStore_id(), true, storePrice, eventRate);
        storeProductMapper.insert(storeProduct);

      } else {
        //기존에 있는 거 -> 점포 재고 증가
        storeProduct.setQnt(storeProduct.getQnt() + orders.getQnt());
        storeProduct.setStore_price(storePrice);
        storeProduct.setEvent_rate(eventRate);
        storeProductMapper.updateqnt(storeProduct);
      }
    }
    //값을 리턴한다.
    return orders;
  }

  //발주를 일자별 리스트로 보여준다.
  public List<Orders> selectListByDate(Long store_id) throws Exception {
    return ordersMapper.selectListByDate(store_id);
  }

  ;

  //본사에서 발주 내역을 확인할 때, 발주를 일자별 리스트로 보여준다.
  public List<Orders> selectListByDateDesc() throws Exception {
    return ordersMapper.selectListByDateDesc();
  }

}

