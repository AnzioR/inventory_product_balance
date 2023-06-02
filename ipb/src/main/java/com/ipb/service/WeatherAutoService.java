package com.ipb.service;

import com.ipb.domain.Orders;
import com.ipb.domain.Product;
import com.ipb.domain.Store;
import com.ipb.utill.FutureOpenWeatherUtill;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherAutoService {
    @Autowired
    ProductService productService;
    @Autowired
    StoreProductService storeProductService;
    @Autowired
    StoreService storeService;
    @Autowired
    OrdersService ordersService;
// 자동 발주별로 cross check가 필요 할거같다. 안만들어도 이유를 ? 만들어보자
 @Scheduled(cron = "0 0 0 * * *") //매일 자정을 기준
//    @Scheduled(fixedDelay = 1000 * 60 * 60)
    public void checkWeather() throws Exception {
        List<Store> rainStore = getStoreListAfter3daysWillBeRain();

        // 상품코드를 통해 유통기한이 지나지 않고 재고가 있는 상품리스트를 가져온다. [유통기한 도래순 정렬]
        List<Product> availableProductListByProductInfo = productService.getAvailableProductListByProductInfo(10L);

        for (Store store : rainStore) { // 비오는 점포 리스트에서 하나씩 꺼내서 주문하기
            // 점포마다 비가 오는 경우, 특정 상품의 보유권장량이 30개이다
            // 만약 점포에 30개 미만이 있다면 그 차이만큼을 주문하고, 30개 이상이라면 주문하지 않는다.
            int thisStoreProductQnt = storeProductService.getStoreProductQntByStoreIdAndProductCode(store.getId(), 10L);
            int orderQnt = 30 - thisStoreProductQnt;
            if (orderQnt > 0) { // 점포에 30개 미만이 있는 경우 주문을 진행한다. 30개 이상이라면 주문하지 않는다.
                for (Product product : availableProductListByProductInfo) { // 상품리스트에서 하나씩 꺼내서 주문하기
                    int headQnt = product.getQnt(); // 본사에서 보유한 상품 수량

                    while (orderQnt != 0 && headQnt != 0) { // 본사에서 보유한 상품 수량이 0이 될 때까지 주문을 진행한다.
                        if (orderQnt >= headQnt) { // 주문량이 본사에서 보유한 상품 수량보다 많은 경우
                            Orders orders = new Orders(headQnt, product.getId(), store.getId(), 1L, 3L);
                            ordersService.register(orders);
                            orderQnt -= headQnt; // 주문량에서 본사에서 보유한 상품 수량을 빼준다.
                            headQnt = 0; // 본사에서 보유한 상품 수량을 0으로 만든다. [다음 유통기한 상품으로 넘어가기 위함]
                        } else { // 주문량이 본사에서 보유한 상품 수량보다 적은 경우
                            Orders orders = new Orders(orderQnt, product.getId(), store.getId(), 1L, 3L);
                            ordersService.register(orders);
                            orderQnt = 0; // 주문량을 0으로 만든다. [주문을 완료했기 때문]
                        }
                    }
                }
            }
        }
    }

    public List<Store> getStoreListAfter3daysWillBeRain() throws Exception {
        // 3일 뒤에 비가 오는 점포 리스트를 만들기 위한 변수 생성
        List<Store> rainStore = new ArrayList<Store>();

        // 전체 Store 가져오기
        List<Store> stores = storeService.get();
        for (Store store : stores) {
            String futureWeather = FutureOpenWeatherUtill.getFutureWeather(store);
            String threeDay = FutureOpenWeatherUtill.FutureWeatherInfo(futureWeather);
            if (threeDay.equals("Rain")) {
                rainStore.add(store);
            } else if (threeDay.equals("Clouds")) {
            } else {
            }
        }
        return rainStore;
    }
}
