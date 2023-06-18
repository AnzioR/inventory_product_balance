package com.ipb.controller;

import com.ipb.domain.Store;
import com.ipb.service.StoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Api(tags = {"점포"})

public class StoreController {

  @Autowired
  StoreService storeService;

  //점포 전체조회
  @GetMapping("/storelist")
  @ApiOperation(value = "점포 목록 전체조회")
  public List<Store> storeList() {
    try {
      return storeService.get();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  //점포등록
  @PostMapping("/store/add")
  @ApiOperation(value = "점포 등록")
  public Store register(@RequestBody Store store) {
    try {
      storeService.register(store);
      return store;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  //점포상세조회
  @GetMapping("/storedetail/{id}")
  @ApiOperation(value = "점포 상세보기")
  public Store storeDetail(@PathVariable Long id) {
    try {
      return storeService.get(id);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
