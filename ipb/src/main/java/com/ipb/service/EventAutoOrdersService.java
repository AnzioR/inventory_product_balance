package com.ipb.service;

import com.ipb.domain.EventAutoOrders;
import com.ipb.domain.EventProduct;
import com.ipb.domain.Orders;
import com.ipb.mapper.EventAutoOrdersMapper;
import com.ipb.mapper.EventProductMapper;
import com.ipb.mapper.OrdersMapper;
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
    EventProductMapper eventMapper;
    @Autowired
    EventAutoOrdersMapper eventAutoOrdersMapper;
    @Autowired
    OrdersMapper ordersMapper;

    @Scheduled(fixedDelay = 60 * 60* 1000)
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
        System.out.println(dateString);
        // 해당 날짜에 해당하는 이벤트가 있을시가 5일전에 모든 점포에 담아주는 마술
        List<EventProduct> eventProducts = eventMapper.searcheventproductbystartdate(dateString);
        for (EventProduct ep:eventProducts){
            eventAutoOrdersMapper.inserteventorder(ep.getId());
        }
    }
//3일전의 상품이 있는걸 자정에 조회하는거 성공 (이벤트 자동발주 리스트에 있는걸을)
    @Scheduled(fixedDelay = 60 * 60*1000)
    public void AutoEventProductSecond() throws Exception {
// 현재 날짜 가져오기
        LocalDate currentDateSec = LocalDate.now();

// 3일 추가
        LocalDate afterDateSec = currentDateSec.plusDays(3);

        DateTimeFormatter formatterSec = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String dateStringSec = afterDateSec.format(formatterSec);

//이벤트 3일 남은 상품이 리스트에 있을시에 발주를 진행 (진행중)
        System.out.println("하나");

//        List<EventAutoOrders> eventAutoOrders = eventAutoOrdersMapper.searchDueEventProduct(dateStringSec);
//        if (eventAutoOrders != null) {
//            System.out.println("둘");
//            System.out.println("ㄴ"+eventAutoOrders);
//            List<Orders> orderList = new ArrayList<Orders>();
//            for (EventAutoOrders ea : eventAutoOrders) {
//                orderList.add(new Orders(ea.getQnt(), ea.getProduct_id(), ea.getStore_id() ,1L,1L));
//            }
//            ordersMapper.insertList(orderList);
////            for (EventAutoOrders ea : eventAutoOrders) {
////                eventAutoOrdersMapper.removeList(ea.getEvent_product_id());}
//        }else {
//            System.out.println("실패");
//        }
//
//
//    }

}
6}

