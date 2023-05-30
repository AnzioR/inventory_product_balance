package com.ipb.service;

import com.ipb.domain.*;
import com.ipb.frame.MyService;
import com.ipb.mapper.OrdersMapper;
import com.ipb.mapper.ProductMapper;
import com.ipb.mapper.StoreAutoOrdersMapper;
import com.ipb.mapper.StoreProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class StoreAutoOrdersService {
  @Autowired
  StoreAutoOrdersMapper storeAutoOrdersMapper;

  @Autowired
  StoreProductMapper storeProductMapper;

  @Autowired
  ProductService productService;

  @Autowired
  OrdersMapper ordersMapper;

  @Autowired
  SmsService smsService;

  @Autowired
  StoreService storeService;

  //@Scheduled(cron = "0 0 0 * * *") //매일 자정을 기준
  @Scheduled(fixedDelay = 1000*60*60)
  public void checkStock() throws Exception {
    try {
      // 매일 자동발주 리스트를 가져와서
      List<StoreAutoOrders> autoOrdersList = storeAutoOrdersMapper.getAutoList();

      System.out.println("======= 자동발주 리스트 =========");
      for(StoreAutoOrders autoOrders : autoOrdersList) {
        System.out.println(autoOrders);
      }
      System.out.println("==============================");

      // 점포 내 자동발주 상품의 재고를 확인
      for (StoreAutoOrders autoOrder : autoOrdersList) {
        List<StoreProduct> storeProductList = storeProductMapper.getStoreProdListByProdCodeAndStoreId(autoOrder.getProduct_code(), autoOrder.getStore_id());
        System.out.println("======= 상품 리스트  =========");
        for(StoreProduct storeProduct : storeProductList) {
          System.out.println(storeProduct);
        }
        System.out.println("======= =====================");

        // 상품인포코드를 통해 조회하고, 여러개면 합계를 이용해 현재 보유수량을 구한다.
        int currentQnt = 0;
        for (StoreProduct storeProduct : storeProductList) {
          currentQnt += storeProduct.getQnt();
        }
        System.out.println("현재 점포의 보유 수량 " + currentQnt);
        // 본사가 가지고 있는 수량을 구한다.
        int productQnt = productService.getProductQntByProductCode(autoOrder.getProduct_code());
        System.out.println("현재 본사의 보유 수량 " + productQnt);
        int minQnt = autoOrder.getMin_qnt();
        int standardQnt = autoOrder.getQnt();
        System.out.println("자동발주 최소 수량 " + minQnt);
        System.out.println("자동발주 기준 수량 " + standardQnt);

        // 현재 수량이 최소 수량보다 작으면, 즉 자동발주가 필요한 상황이라면
        if (currentQnt < minQnt) {
          System.out.println("자동발주가 필요합니다. 진행하겠습니다");
          // 자동발주 필요 수량을 구한다.
          int autoOrderQnt = standardQnt - currentQnt;
          
          // 본사에 보유한 상품이 더 적은 경우는 있는 만큼 주문되도록 한다.
          if (productQnt < autoOrderQnt) {
              autoOrderQnt = productQnt;
          }
          // 해당 상품코드와 동일한 상품목록을 가져온다. (본사에서 _ 유통기간이 얼마 안남은게 처음에 온다.)
          List<Product> productList = productService.getProductListByProductCode(autoOrder.getProduct_code());

          // 그리고 반복문을 통해 각각 주문을 진행한다.
          System.out.println("상품 주문합니다.");

          for (Product product : productList) {
            int realOrderQnt = product.getQnt();
            if (autoOrderQnt < product.getQnt()) {
              realOrderQnt = autoOrderQnt;
            }
            System.out.println("[주문] " + realOrderQnt + "개");
            Orders order = new Orders(realOrderQnt, product.getId(), autoOrder.getStore_id(), 1L, 2L);
            ordersMapper.insert(order);
            autoOrderQnt -= realOrderQnt;
            if (autoOrderQnt == 0) {
              System.out.println("더 이상 주문하지 않겠어");
              break;
            }
          }

          // 자동발주를 신청하는 점포관리자의 연락처를 찾는다.
          Long storeId = autoOrder.getStore_id(); // 우리가 가진 정보는 storeId

          // storeId로 전화번호를 가져오는 서비스를 이용하자! 전화번호는 문자열이니까 Store로 안가져와도 된다!
          String num = storeService.selectNumber(storeId);
          System.out.println("확인] " + num);

          //전화번호를 받아오는 형식을 변경한다.
          String formattedNum = num.replaceAll("-", "");

          //점포관리자에게 자동발주되었음을 문자로 알려준다.
          Message msg = new Message(formattedNum, "자동발주가 진행됩니다. 사이트에서 확인하세요");
          // 전화번호 가져와 지니까 문자 가겠죠~ 끝!
          smsService.sendSms(msg);

        }

      }
    } catch (Exception e) {
      //점포관리자에게 자동발주가 실패했음을 문자로 알려준다.
      Message errMsg = new Message("01049010828", "자동발주를 실패했습니다. 다시 확인해주세요.");
      smsService.sendSms(errMsg);
      e.printStackTrace();
    }
  }

}
