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
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.time.LocalTime.now;

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
  OrdersCartService ordersCartService;

  @Autowired
  ProductService productService;

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

  //매장에서 발주하기, 발주할 때 상품 수량도 변경함
  public void orders(Orders orders, Product product) throws Exception {
    ordersMapper.insert(orders);
    productMapper.updateqnt(product);
  }


  //발주카트에 담긴 상품들을 리스트로 처리해서 발주로 이동시킴
  //(카트:상품번호,수량,발주매장번호에 추가로 배송상태, 발주일을 더해서 Orders로 보낸다.)
  @Transactional
  public List<OrdersCart> addorder(Long store_id) throws Exception {
    List<OrdersCart> cartItems = ordersCartService.cartlist(store_id);
    List<Orders> transOrders = new ArrayList<>();
    List<OrdersCart> errorItems = new ArrayList<>();
    for (OrdersCart item : cartItems) {
      Orders order = new Orders();
      order.setProduct_id(item.getProduct_id());
      order.setQnt(item.getQnt());
      order.setStore_id(item.getStore_id());
      order.setDelivery_id(1L); //발주가 이루어지는 시점에서 상품의 배송상태는 배송준비중(1)이다
      order.setOrders_date(new Date());

      // 여기서 각 상품에 대한 본사재고를 확인합니다
      int store_qnt = productService.get(order.getProduct_id()).getQnt();
      int wish_qnt = item.getQnt();

      if (store_qnt < wish_qnt) {
        // 주문이 정상적으로 처리될 수 없다
        int max_qnt = store_qnt;
        item.setQnt(max_qnt);
        errorItems.add(item);
        continue;
      } else {
        // 정상주문 처리 가능
        transOrders.add(order);
        ordersMapper.insert(order);
        //발주카트의 정보가 발주로 이동되었으므로 store_id가 가진 발주카트를 비운다. (store_id에 해당되는 orderCart를 전부 삭제함)
        ordersCartService.removecart(1L);
        //발주를 하는 경우 발주가능상품의 수량에서 주문수량을 차감한다
        // 1. 해당 상품 정보 조회
        Product product = productService.get(order.getProduct_id());
        product.setQnt(product.getQnt() - order.getQnt());
        productService.updateqnt(product);

        // 2. 존재 여부 파악
        StoreProduct sp = new StoreProduct(order.getProduct_id(), order.getStore_id());
        sp = storeProductService.getstoreproductfromstoreidandproductid(sp);
        System.out.println(sp);
        if (sp != null) {
          // 2-1. 점포에 상품이 존재할 떄, qnt 증가
          sp.setQnt(sp.getQnt() + order.getQnt());
          storeProductService.storeupdateqnt(sp);
        } else {
          //2-2. 존재하지 않는 경우에는,레코드 추가
          sp = new StoreProduct(order.getQnt(), order.getProduct_id(), order.getStore_id(), false);
          storeProductService.register(sp);
        }
      }
    }
    System.out.println("주문이 실패된 상품들은 다음과 같습니다");
    for (OrdersCart item : errorItems) {
      System.out.println(item);
    }
    return errorItems;
  }

  public void maxOrder(List<OrdersCart> failOrderList) throws Exception {
    List<Orders> transOrders = new ArrayList<>();

    for (OrdersCart item : failOrderList) {
      Orders order = new Orders();
      order.setProduct_id(item.getProduct_id());
      order.setQnt(item.getQnt());
      order.setStore_id(item.getStore_id());
      order.setDelivery_id(1L); //발주가 이루어지는 시점에서 상품의 배송상태는 배송준비중(1)이다
      order.setOrders_date(new Date());

      // 정상주문 처리 가능
      transOrders.add(order);
      ordersMapper.insert(order);
      //발주카트의 정보가 발주로 이동되었으므로 store_id가 가진 발주카트를 비운다. (store_id에 해당되는 orderCart를 전부 삭제함)
      ordersCartService.removecart(1L);
      //발주를 하는 경우 발주가능상품의 수량에서 주문수량을 차감한다
      // 1. 해당 상품 정보 조회
      Product product = productService.get(order.getProduct_id());
      product.setQnt(product.getQnt() - order.getQnt());
      productService.updateqnt(product);

      // 2. 존재 여부 파악
      StoreProduct sp = new StoreProduct(order.getProduct_id(), order.getStore_id());
      sp = storeProductService.getstoreproductfromstoreidandproductid(sp);
      System.out.println(sp);
      if (sp != null) {
        // 2-1. 점포에 상품이 존재할 떄, qnt 증가
        sp.setQnt(sp.getQnt() + order.getQnt());
        storeProductService.storeupdateqnt(sp);
      } else {
        //2-2. 존재하지 않는 경우에는,레코드 추가
        sp = new StoreProduct(order.getQnt(), order.getProduct_id(), order.getStore_id(), false);
        storeProductService.register(sp);
      }
    }
  }
}
