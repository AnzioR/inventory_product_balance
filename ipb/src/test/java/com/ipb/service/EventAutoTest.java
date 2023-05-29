package com.ipb.service;

import com.ipb.domain.Event;
import com.ipb.domain.EventAutoOrders;
import com.ipb.domain.StoreProduct;
import com.ipb.mapper.EventAutoOrdersMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class EventAutoTest {

    @Autowired
    EventAutoOrdersService eventAutoOrdersService;

    @Test
    void 점포아이디에따른자동발주리스트조회() {
        try {
            List<EventAutoOrders> eventAutoOrders = eventAutoOrdersService.getList(1L);
            System.out.println(eventAutoOrders);
        } catch (Exception e) {
            e.printStackTrace();
        }
//성공
    }
    @Test
    void modify() {
        EventAutoOrders storeProduct = new EventAutoOrders(5634L, 200);
        try {
            eventAutoOrdersService.update(storeProduct);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("수정 오류가 발생!");
        }
    }
}
