package com.ipb.service;

import com.ipb.domain.Event;
import com.ipb.domain.EventAutoOrders;
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
}
