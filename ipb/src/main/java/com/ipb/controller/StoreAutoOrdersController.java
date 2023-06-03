package com.ipb.controller;

import com.ipb.domain.Orders;
import com.ipb.domain.StoreAutoOrders;
import com.ipb.mapper.StoreAutoOrdersMapper;
import com.ipb.service.StoreAutoOrdersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auto")
@Api(tags = {"발주 관련 컨트롤러"})
public class StoreAutoOrdersController {

  @Autowired
  StoreAutoOrdersService storeAutoOrdersService;


  //자동발주 수량 옵션을 변경한다.
  @PutMapping("/qnt-change")
  @ApiOperation(value = "자동발주 수량옵션 변경")
  public void updateStoreOrders(Long id, @RequestBody StoreAutoOrders storeAutoOrders) {
    try {
      storeAutoOrdersService.modify(storeAutoOrders);
    } catch(Exception e) {
      System.out.println("수량 변경에 실패했습니다.");
      e.printStackTrace();
    }
  }

  //자동발주를 삭제한다.
  @DeleteMapping("/delete")
  public void delete(Long id){
    try {
      storeAutoOrdersService.remove(id);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  //자동발주 리스트 가져오기
  @GetMapping("/getList/id")
  @ApiOperation(value = "자동발주 리스트 전체 가져오기")
  public List<StoreAutoOrders> getAutoList(@PathVariable Long store_id) {
    try {
      return storeAutoOrdersService.selectAutoList(store_id);
    } catch (Exception e) {
      System.out.println("자동발주 리스트 불러오기를 실패했습니다.");
      e.printStackTrace();
      return null;
    }
  }

}
