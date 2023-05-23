package com.ipb.service;

import com.ipb.domain.EventProduct;
import com.ipb.mapper.EventProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class EventAutoOrdersService {

    @Autowired
    EventProductMapper eventMapper;

    @Scheduled(fixedDelay = 60 * 60)
    public void run() throws Exception {
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
        // 이벤트 상품을 조회 했을때
        List<EventProduct> abc = eventMapper.searcheventproductbystartdate(dateString);
        System.out.println(abc);
        for (EventProduct a:abc){
            System.out.println(a.getProduct_id());
        }
    }
}

