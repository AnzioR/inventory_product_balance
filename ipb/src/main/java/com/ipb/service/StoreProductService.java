package com.ipb.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.ipb.domain.*;
import com.ipb.frame.MyService;
import com.ipb.mapper.OrdersCartMapper;
import com.ipb.mapper.StoreAutoOrdersMapper;
import com.ipb.mapper.StoreProductIssueMapper;
import com.ipb.mapper.StoreProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static java.time.LocalDate.now;

@Service
@RequiredArgsConstructor
public class StoreProductService implements MyService<Long, StoreProduct> {

  @Autowired
  StoreProductMapper storeProductMapper;
  @Autowired
  StoreService storeService;

  @Autowired
  SalesService salesService;

  @Autowired
  StoreAutoOrdersMapper storeAutoOrdersMapper;

  @Autowired
  StoreProductIssueMapper storeProductIssueMapper;

  @Autowired
  SmsService smsService;


  @Autowired
  OrdersCartMapper ordersCartMapper;

  //  store product 등록
  @Override
  public void register(StoreProduct storeProduct) throws Exception {
    storeProductMapper.insert(storeProduct);

  }

  //  store product 수정
  @Override
  public void modify(StoreProduct storeProduct) throws Exception {
    storeProductMapper.update(storeProduct);
  }

  //  store product 삭제
  @Override
  public void remove(Long id) throws Exception {
    storeProductMapper.delete(id);
  }

  @Override
  public StoreProduct get(Long id) throws Exception {
    return storeProductMapper.select(id);
  }

  //  store product 수량변경 (재고 수량보다 많은 주문 시 작동하지 않도록 수정 완료) - sales 에서 사용중...
  public void updateqnt(Sales sales) throws Exception {
    StoreProduct product = get(sales.getStore_product_id());
    int real_stock = product.getQnt();
    int order_stock = sales.getQnt();

    if (real_stock < order_stock) {
      throw new Exception("재고 수량보다 많은 주문은 불가능합니다");
    } else {
      product.setQnt(real_stock - order_stock);
      storeProductMapper.updateqnt(product);
      salesService.register(sales);
    }
  }

  //  store product
  @Override
  public List<StoreProduct> get() throws Exception {
    return storeProductMapper.selectall();
  }

  //  점포 재고를 카테고리별로 가져옴
  public List<StockInfo> selectcategoryname(String categoryname, Long store_id) throws Exception {
    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put("category_name", categoryname);
    map.put("store_id", store_id);
    List<StockInfo> storeProducts = storeProductMapper.selectcategoryname(map);

    return storeProducts;
  }

  //today 기준으로 날짜별 상품 확인 (D-3,D-4..)
  public List<StockInfo> selectexpAndExpiringSoon(String categoryname, Long store_id, int days) throws Exception {
    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put("category_name", categoryname);
    map.put("store_id", store_id);
    map.put("days", days);
    List<StockInfo> storeProducts = storeProductMapper.selectexpAndExpiringSoon(map);

    return storeProducts;
  }

  //재고 전체보기
  public List<StoreProduct> selectall() throws Exception {
    return storeProductMapper.selectall();
  }

  public void update(StoreProduct storeProduct) throws Exception {
    storeProductMapper.update(storeProduct);
  }

  //점포재고에서 검색을 해서 상품을 가져올수있음
  public List<StockInfo> searchstoreproduct(String txt, Long store_id) throws Exception {
    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put("txt", txt);
    map.put("store_id", store_id);
    return storeProductMapper.searchstoreproduct(map);
  }

  //store id 로 각각의 점포의 재고를 조회 할 수 있음
  public List<StoreProduct> selectstoreproduct(Long store_id) throws Exception {
    return storeProductMapper.selectstoreproduct(store_id);
  }

  //store id 로 각각의 점포의 유통기한을 조회 할 수있음
  public List<StoreProduct> selectallexpStoreProduct(Long store_id) throws Exception {
    return storeProductMapper.selectallexpStoreProduct(store_id);
  }

  //발주할 때, 점포의 재고수량을 변경
  public void storeUpdateQnt(StoreProduct storeProduct) throws Exception {
    storeProductMapper.storeUpdateQnt(storeProduct);
  }

  //store_id와 product_id를 조회하는 기능
  public StoreProduct getStoreProductFromStoreIdAndProductId(StoreProduct storeProduct) throws Exception {
    StoreProduct st = storeProductMapper.getStoreProductFromStoreIdAndProductId(storeProduct);
    return st;
  }

  //자동발주를 신청하면 자동주문상태를 변경하고 자동발주 리스트에 추가해준다.
  public void autoOrderRequest(StoreAutoOrders sao) throws Exception {
    storeAutoOrdersMapper.insert(sao);
    StoreProduct sp = storeProductMapper.select(sao.getStore_product_id());
    sp.set_auto(true);
    storeProductMapper.update(sp);
  }

  //상품의 폐기를 누르면 상품수량=0, is_using=0 으로 변경한다.
  public void qntZero(StoreProduct storeProduct) {
    try {
      storeProductMapper.qntZero(storeProduct);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  //재고 수량이 부족한 상품들을 가진 점포에게 문자를 보내준다.
  @Scheduled(fixedDelay = 1000 * 60 * 60)
  public void sendMsgToStore() throws Exception {
    List<StoreProduct> getList = storeProductMapper.notAutoLessQnt(); //자동발주를 신청 안한 상품들 중에서 현재 재고량이 안전재고량보다 적은 상품들을 리스트로 가져온다.

    for (StoreProduct sp : getList) {
      int safeQnt = sp.getSafe_qnt();
      int storeQnt = sp.getQnt();

      Long num = null;
      if (safeQnt > storeQnt) {
        //매장에 문자를 발송하지만, 이미 문자를 받는 점포는 제외함
        if (num != sp.getStore_id()) {
          num = sp.getStore_id();
          String msg = "점포가 가진 상품 중 안전재고량 미달 상품이 존재합니다.";
          //메세지 발송
          //sendMsg(num, msg);

        }
      }
    }
  }

  // 메시지 보내는 메서드
  public void sendMsg(Long storeId, String msg) throws Exception {
    // storeId로 전화번호를 가져오는 서비스를 이용하자! 전화번호는 문자열이니까 Store로 안가져와도 된다!
    String num = storeService.selectNumber(storeId);
    //전화번호를 받아오는 형식을 변경한다.
    String formattedNum = num.replaceAll("-", "");
    //점포관리자에게 자동발주되었음을 문자로 알려준다.
    Message message = new Message(formattedNum, msg);

    //문자 발송이 잘 되는 것을 확인했으므로 주석처리함.
    //smsService.sendSms(message);
  }


  public Integer getStoreProductQntByStoreIdAndProductCode(Long storeId, Long product_code) throws Exception {

    Integer storeProductQntByStoreIdAndProductCode = storeProductMapper.getStoreProductQntByStoreIdAndProductCode(storeId, product_code);
    if (storeProductQntByStoreIdAndProductCode != null) {
      return storeProductQntByStoreIdAndProductCode;
    } else {
      return 0;
    }
  }

  @Scheduled(cron = "0 0 0 * * *") //매일 자정을 기준
//    @Scheduled(fixedDelay = 1000 * 60 * 60)
  public void expiring() throws Exception {
    //전체 매장 불러오기 전체 매장 아이디 넣기
    List<Store> stores = storeService.get();
    for (Store store : stores) {
      //유통기안이 max~min 사이에 있을시 그에 해당하는 재고가 이상이면 넣어주고 아니면 그냥 그대로 둔다
      List<StoreProduct> firstProductsBetweenExpiring = getProductsBetweenExpiring(7L, 5L, store.getId());
      for (StoreProduct sp : firstProductsBetweenExpiring) {
        if (sp.getQnt() > (sp.getSafe_qnt() * 3)) {
          StoreProduct storeProduct = new StoreProduct(sp.getId(), sp.getQnt(), sp.getProduct_id(), sp.getStore_id(), sp.is_using(), (int) (sp.getPrice() * 0.9), 0.9, sp.is_auto(), sp.getProduct_code());
          update(storeProduct);
        } else {
        }
      }
      List<StoreProduct> secondProductsBetweenExpiring = getProductsBetweenExpiring(5L, 3L, store.getId());
      for (StoreProduct sp : secondProductsBetweenExpiring) {
        if (sp.getQnt() > (sp.getSafe_qnt() * 2)) {
          StoreProduct storeProduct = new StoreProduct(sp.getId(), sp.getQnt(), sp.getProduct_id(), sp.getStore_id(), sp.is_using(), (int) (sp.getPrice() * 0.7), 0.7, sp.is_auto(), sp.getProduct_code());
          update(storeProduct);
        } else {
        }
      }
      List<StoreProduct> finalProductsBetweenExpiring = getProductsBetweenExpiring(3L, -1L, store.getId());
      for (StoreProduct sp : finalProductsBetweenExpiring) {
        if (sp.getQnt() > (sp.getSafe_qnt() * 1)) {
          StoreProduct storeProduct = new StoreProduct(sp.getId(), sp.getQnt(), sp.getProduct_id(), sp.getStore_id(), sp.is_using(), (int) (sp.getPrice() * 0.5), 0.5, sp.is_auto(), sp.getProduct_code());
          update(storeProduct);
        } else {
        }
      }
    }
  }

  public List<StoreProduct> getProductsBetweenExpiring(Long max, Long min, Long store_id) {
    return storeProductMapper.getProductsBetweenExpiring(max, min, store_id);
  }

  public List<StoreProduct> all() {
    return storeProductMapper.selectallStore();
  }

  //유통기한 문자 발송(수정 할 수 있으면 유통기한 임박 상품이 여러개 있으면 문자 1번만 발송 될 수 있도록)
  @Scheduled(cron = "0 0 0 * * *")
  public void sendExpirationsms() throws Exception {
    try {
      List<StoreProduct> all = storeProductMapper.selectallStore();
      Long storeIdForMsg = null;

      for (StoreProduct sp : all) {
        Long spId = sp.getStore_id();

        // 유통기한이 3일 남은 상품을 가져온다.
        List<StoreProduct> storeProductList = storeProductMapper.getExpiringStoreProductsms(spId, 3);

        // 상품별로 해당되는 점포들에게 문자를 보낸다.
        for (StoreProduct storeProduct : storeProductList) {
          if (storeIdForMsg != storeProduct.getStore_id()) {
            storeIdForMsg = storeProduct.getStore_id();

            String msg = "유통기한이 3일 이하로 남은 상품이 있습니다.";
            //sendMsg(storeIdForMsg, msg);
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
      // 예외 처리
    }
  }
}

