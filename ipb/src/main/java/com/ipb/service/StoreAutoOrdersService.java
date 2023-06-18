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

  @Scheduled(cron = "0 0 0 * * *") //매일 자정을 기준
  //@Scheduled(fixedDelay = 1000*60*60)
  public void checkStock() throws Exception {
    try {
      // 매일 자동발주 리스트를 가져와서
      List<StoreAutoOrders> autoOrdersList = storeAutoOrdersMapper.getAutoList();

      Long storeIdForMsg = null;

      // 점포 내 자동발주 상품의 재고를 확인
      for (StoreAutoOrders autoOrder : autoOrdersList) {
        List<StoreProduct> storeProductList = storeProductMapper.getStoreProdListByProdCodeAndStoreId(autoOrder.getProduct_code(), autoOrder.getStore_id());

        int currentQnt = 0;
        for (StoreProduct storeProduct : storeProductList) {
          currentQnt += storeProduct.getQnt();
        }

        // 본사가 가지고 있는 수량을 구한다.
        int productQnt = productService.getProductQntByProductCode(autoOrder.getProduct_code());
        int minQnt = autoOrder.getMin_qnt();
        int standardQnt = autoOrder.getQnt();

        // 현재 수량이 최소 수량보다 작으면, 즉 자동발주가 필요한 상황이라면
        if (currentQnt < minQnt) {

          // 자동발주 필요 수량을 구한다.
          int autoOrderQnt = standardQnt - currentQnt;

          // 본사에 보유한 상품이 더 적은 경우는 있는 만큼 주문되도록 한다.
          // 이쪽에서는 주문한 점포들의 전체 수량을 고려해서 분배하지 않기 때문에, 먼저 주문이 들어온 점포에만 상품이 전해지는 문제가 있음.
          if (productQnt < autoOrderQnt) {
            autoOrderQnt = productQnt;
          }

          // 해당 상품코드와 동일한 상품목록을 가져온다. (본사에서 _ 유통기간이 얼마 안남은게 처음에 온다.)
          List<Product> productList = productService.getProductListByProductCode(autoOrder.getProduct_code());

          // 그리고 반복문을 통해 각각 주문을 진행한다.

          // 매장에게 문자를 보내야 한다. 다만, 이미 문자 메시지를 전달한 점포는 제외
          if (storeIdForMsg != autoOrder.getStore_id()) {
            storeIdForMsg = autoOrder.getStore_id();
            String msg = "자동발주가 진행됩니다. 사이트에서 확인하세요";
            //sendMsg(storeIdForMsg, msg);
          }

          for (Product product : productList) {
            int realOrderQnt = product.getQnt();
            if (autoOrderQnt < product.getQnt()) {
              realOrderQnt = autoOrderQnt;
            }
            //코드관련주석!
            Orders order = new Orders(realOrderQnt, product.getId(), autoOrder.getStore_id(), 1L, 2L); //상수로 분류해서 관리!!!!!!!!!!!!
            ordersMapper.insert(order);
            autoOrderQnt -= realOrderQnt;
            if (autoOrderQnt == 0) {
              break;
            }
          }
        }
      }
    } catch (Exception e) {
      //점포관리자에게 자동발주가 실패했음을 문자로 알려준다.
      // 관리자 뿐만 아니라 자동발주를 신청한 점주도 서버에러나 문제등으로 자동발주가 안되었음을 알면 좋을 것 같은데......
      Message errMsg = new Message("01049010828", "자동발주를 실패했습니다. 다시 확인해주세요.");

      //문자 발송이 잘 되는 것을 확인했으므로 주석처리함.
      //smsService.sendSms(errMsg);
      e.printStackTrace();
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

  //자동발주 여러개가 다 들어갔을때 수량체크 되는 부분이 있으면 좋겠지만, 일정관계상 어려움이 있었다....ㅜㅜ

  //자동발주 기준재고수량, 최소재고수량 변경기능
  public void changeQnt(StoreAutoOrders storeAutoOrders) throws Exception {
    storeAutoOrdersMapper.changeQnt(storeAutoOrders);
  }

  //자동발주 삭제
  public void deleteAuto(Long id) throws Exception {
    storeAutoOrdersMapper.deleteAuto(id);
  }

  //점포별 자동발주 리스트 불러오기
  public List<StoreAutoOrders> selectAutoList(Long store_id) throws Exception {
    return storeAutoOrdersMapper.selectAutoList(store_id);
  }

}