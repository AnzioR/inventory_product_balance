package com.ipb.service;

import com.ipb.domain.Orders;
import com.ipb.domain.StoreAutoOrders;
import com.ipb.domain.StoreProduct;
import com.ipb.mapper.OrdersMapper;
import com.ipb.mapper.StoreAutoOrdersMapper;
import com.ipb.mapper.StoreProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class StoreAutoOrdersService {
  @Autowired
  StoreAutoOrdersMapper storeAutoOrdersMapper;
  @Autowired
  StoreProductMapper storeProductMapper;

  @Autowired
  OrdersMapper ordersMapper;

//  @Scheduled(cron = "0 0 0 * * *") //매일 자정을 기준
  @Scheduled(fixedDelay = 1000*60*60) // 1시간마다 실행
  public void checkStock() throws IOException {
    ///////////////////////////////////////////////////////////////유통기한에 따른 재고 관리 추가해야 함 --> 어떻게 하지...???
    System.out.println("0시 기준 재고 확인");
    try {
      //store_product 전체를 리스트로 가져온다.
      List<StoreProduct> storeProductList = storeProductMapper.selectall();
      System.out.println(storeProductList);

      for (StoreProduct storeProduct : storeProductList) {
        Integer have_qnt = storeProduct.getQnt(); //현재 점포가 보유한 재고수량

        //store_product_id를 가져온다.
        StoreAutoOrders sao = storeAutoOrdersMapper.selectBySpi(storeProduct.getId());
        if (sao != null) {
          int min_qnt = sao.getMin_qnt();
          int stan_qnt = sao.getQnt();
          if (have_qnt < min_qnt) {
            // 주문할 개수를 구한다.
            int auto_order_qnt = stan_qnt - have_qnt;
            // 주문한다
            Orders order = new Orders(auto_order_qnt, storeProduct.getProduct_id(), storeProduct.getStore_id(), 1L, 2L);
            ordersMapper.insert(order);
          }
        }
      }
    } catch (Exception e) {
      System.out.println("점포의 재고 확인에 실패했습니다.");
      e.printStackTrace();
    }
  }
}
