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
    //OrdersCart cart = new OrdersCart(null, 555, 1L, 2L);
    //ordersCartMapper.insert(cart);

//    // 카트에 담긴 상품 리스트를 가져온다
//    List<OrdersCart> cartItems = ordersCartService.cartlist();
//
//    // 상품 정보를 Orders 객체로 변환한다
//    List<Orders> transOrders = new ArrayList<>();
//    for (OrdersCart item : cartItems) {
//      orders.setProduct_id(item.getProduct_id());
//      orders.setQnt(item.getQnt());
//      orders.setDelivery_id(1L); //발주가 이루어지는 시점에서 상품의 배송상태는 배송준비중(1)이다
//      orders.setOrders_date(new Date());
//      transOrders.add(orders);
//    }
//
//    // Orders 객체를 전송한다
//    ordersMapper.insert(orders);

//    OrdersCart ordersCart = new OrdersCart();
//
//    orders.setQnt(ordersCart.getQnt());
//    orders.setProduct_id(ordersCart.getProduct_id());
//    orders.setStore_id(ordersCart.getStore_id());
//
//    // Orders 객체에 추가 필드들에 값을 채움
//    orders.setDelivery_id(1L);
//    orders.setOrders_date(new Date());
//
//    // Orders 객체를 등록
//    ordersMapper.insert(orders);
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
      product.setQnt(product.getQnt() + orders.getQnt());
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
  public Orders searchdate(Date orders_date) throws Exception {
    return ordersMapper.searchdate(orders_date);
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
  public void addorder(Long store_id) throws Exception {
    List<OrdersCart> cartItems = ordersCartService.cartlist(store_id);
    List<Orders> transOrders = new ArrayList<>();

    for (OrdersCart item : cartItems) {
      Orders order = new Orders();
      order.setProduct_id(item.getProduct_id());
      order.setQnt(item.getQnt());
      order.setStore_id(item.getStore_id());
      order.setDelivery_id(1L); //발주가 이루어지는 시점에서 상품의 배송상태는 배송준비중(1)이다
      order.setOrders_date(new Date());
      transOrders.add(order);
      ordersMapper.insert(order);

      //발주를 하는 경우 발주가능상품의 수량에서 주문수량을 차감한다
      Product product = productService.get(order.getProduct_id());

      //만일 발주가능상품의 수량보다 주문수량이 많은 경우 발주할 수 없다
      if(product.getQnt() - order.getQnt() >= 0) {
        product.setQnt(product.getQnt() - order.getQnt());
        productService.updateqnt(product);
      } else {
        throw new IllegalStateException("주문 수량이 재고를 초과합니다. 다시 확인해주세요.");
      }

      //발주를 하게 되면 점포의 재고수량을 주문수량만큼 바로 증가시켜준다(이 기능은 향후 배송완료 시점에 증가될 수 있도록 수정을 하면 좋겠어요!)
      StoreProduct sp = storeProductMapper.getstoreproductfromstoreidandproductid(new StoreProduct(product.getId(), store_id)); //storeproduct 어떻게 데려와야되는건지.......
      System.out.println("sp = " + sp);
      sp.setQnt(sp.getQnt() + order.getQnt());
      storeProductService.storeupdateqnt(sp);
    }
  }
}
