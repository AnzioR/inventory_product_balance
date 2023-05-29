package com.ipb.controller;

import com.ipb.domain.EventAutoOrders;
import com.ipb.service.EventAutoOrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EventAutoOrdersController {

    @Autowired
    EventAutoOrdersService eventAutoOrdersService;

//
    @GetMapping("eventAutoOrders/{store_id}")
    public List<EventAutoOrders> getListOfEventAutoOrders(@PathVariable Long store_id) {
        try {
            List<EventAutoOrders> list = eventAutoOrdersService.getList(store_id);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        return null;
        }
    }

    @PutMapping("eventAutoOrders/update")
    public void updateQnt(EventAutoOrders eventAutoOrders){
        try {
            eventAutoOrdersService.update(eventAutoOrders);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
