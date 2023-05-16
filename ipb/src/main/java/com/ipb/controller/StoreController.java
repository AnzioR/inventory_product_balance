package com.ipb.controller;

import com.ipb.domain.Store;
import com.ipb.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor

public class StoreController {

  @Autowired
  StoreService storeService;

 //점포 전체조회
  @GetMapping("/storelist")
  public List<Store> storeList() {
    try {
      return storeService.get();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  //점포등록
  @PostMapping("/store/add")
  public Store register(@RequestBody Store store){
    try {
      storeService.register(store);
      return store;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  //점포상세조회
  @GetMapping("/storedetail")
  public Store storeDetail(Long id){
    try {
      return storeService.get(id);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
