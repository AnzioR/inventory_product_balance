package com.ipb.controller;

import com.ipb.domain.EventAutoOrders;
import com.ipb.service.EventAutoOrdersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@Api(tags = {"이벤트 자동 발주"})
public class EventAutoOrdersController {

    @Autowired
    EventAutoOrdersService eventAutoOrdersService;

    //
    @GetMapping("/eventAutoOrders/{store_id}")
    @ApiOperation(value = "이벤트 자동발주", notes = "store_id로 이벤트 자동발주 리스트를 가져온다")
    public List<EventAutoOrders> getListOfEventAutoOrders(@PathVariable Long store_id) {
        try {
            List<EventAutoOrders> list = eventAutoOrdersService.getList(store_id);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PutMapping("/eventAutoOrders/update")
    @ApiOperation(value = "이벤트 자동발주 수량 수정")
    public void updateQnt(@RequestBody EventAutoOrders eventAutoOrders){
        try {
            eventAutoOrdersService.update(eventAutoOrders);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}