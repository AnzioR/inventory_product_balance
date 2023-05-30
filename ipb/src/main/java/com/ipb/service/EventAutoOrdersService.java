package com.ipb.service;

import com.ipb.domain.*;
import com.ipb.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class EventAutoOrdersService {

    @Autowired
    EventProductMapper eventProductMapper;
    @Autowired
    EventAutoOrdersMapper eventAutoOrdersMapper;
    @Autowired
    OrdersMapper ordersMapper;
    @Autowired
    EventMapper eventMapper;
    @Autowired
    ProductService productService;
    @Autowired
    SalesMapper salesMapper;

    //매장 id 에 따른 내역리스트 보여주기
    public List<EventAutoOrders> getList(Long store_id) throws Exception {
        return eventAutoOrdersMapper.listByStoreId(store_id);
    }

    //수량 업데이트
    public void update(EventAutoOrders order) throws Exception {
        eventAutoOrdersMapper.updateQnt(order);
    }
    @Scheduled(cron = "0 0 0 * * *") //매일 자정을 기준
//    @Scheduled(fixedDelay = 60 * 60 * 1000)
    public void AutoEventProductFirst() throws Exception {
// 현재 날짜 가져오기
        LocalDate currentDate = LocalDate.now();

// 5일 추가
        LocalDate afterDate = currentDate.plusDays(5);

// 날짜 형식 지정
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

// 문자열로 변환
        String dateString = afterDate.format(formatter);

// 결과 출력
        // 해당 날짜에 해당하는 이벤트가 있을시가 5일전에 모든 점포에 담아주는 마술
        List<EventProduct> eventProducts = eventProductMapper.searcheventproductbystartdate(dateString);
        // 5일 뒤 + 1년 전 이벤트 정보 가져오기
        Event previousEvent = eventMapper.findPreviousEvent(dateString);

        // 50개씩 주문 넣기
        for (EventProduct ep : eventProducts) {
            eventAutoOrdersMapper.inserteventorder(ep.getId());
        }

        if (previousEvent != null) {
            for (EventProduct ep : eventProducts) {
                if (productService.findProductCodeByEventIdAndCompareProdCode(previousEvent.getId(), ep.getProduct_code())) {
                    System.out.println(ep.getProduct_code() + "번 상품은 지난 이벤트에도 있던 상품");
                    // 지난 이벤트 기간 중 ep.getProduct_code()번 상품의 매장별 판매량 조회
                    List<EventAutoOrders> EventOrderListSalesQnt = salesMapper.getTotalQntByEventIdAndProdCode(previousEvent.getId(), ep.getProduct_code());
                    // 수정하기 (eventProductId와 storeId로 찾아서 50인 qnt를 판매량으로 바꿔준다)
                    for (EventAutoOrders eao : EventOrderListSalesQnt) {
                        eventAutoOrdersMapper.updateQntByEvent(ep.getId(), eao.getStore_id(), (int) Math.ceil(eao.getQnt() * 1.1));
                    }
                }
            }
        }
    }


    //3일전의 상품이 있는걸 자정에 조회하는거 성공 (이벤트 자동발주 리스트에 있는걸을)
//    @Scheduled(fixedDelay = 60 * 60 * 1000)
    @Scheduled(cron = "0 0 0 * * *") //매일 자정을 기준
    public void AutoEventProductSecond() throws Exception {
// 현재 날짜 가져오기
        LocalDate currentDateSec = LocalDate.now();

// 3일 추가
        LocalDate afterDateSec = currentDateSec.plusDays(3);

        DateTimeFormatter formatterSec = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String dateStringSec = afterDateSec.format(formatterSec);

//이벤트 3일 남은 상품이 리스트에 있을시에 발주를 진행
        List<EventAutoOrders> eventAutoOrders = eventAutoOrdersMapper.searchDueEventProduct(dateStringSec);
//해당 리스트가 존재할시 밑의 로직을 진행함
        if (eventAutoOrders.size() > 0) {
//새로운 주문 리스트를 생성한다
            List<Orders> orderList = new ArrayList<Orders>();
//각각 주문 리스트에서 존재하는 리스트들의 값을 가지고 재 값을 넣는다.
            for (EventAutoOrders ea : eventAutoOrders) {
                orderList.add(new Orders(ea.getQnt(), ea.getProduct_id(), ea.getStore_id(), 1L, 1L));
            }
//오더에 해당 값을 넣어준다
            ordersMapper.insertList(orderList);
            System.out.println("이벤트상품들을 발주등록 하였습니다. 발주페이지를 확인해주세요.");
//오더에 넣어준 값을 리스트에서 다 삭제 시켜준다
            eventAutoOrdersMapper.removeEventList(eventAutoOrders);
        } else {
            System.out.println("해당사항없음");
        }
    }
}


