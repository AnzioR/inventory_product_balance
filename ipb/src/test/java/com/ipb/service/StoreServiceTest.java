package com.ipb.service;

import com.ipb.domain.Store;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StoreServiceTest {

  @Autowired
  StoreService storeService;

  @Test
  void register() {
    try {
      Store store = new Store(null, "센텀텀", "부산시 해운대구 센텀로 12345", "051-0000-1111", "부산센텀점");
      storeService.register(store);
      System.out.println(store);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  void modify() {
    Store store = new Store(3L, "부산센텀시티점", "부산시 해운대구 센텀로 123", "051-1111-2222", "부산센템시티점");
    try {
      storeService.modify(store);
    } catch(Exception e) {
      e.printStackTrace();
      System.out.println("수정 오류가 발생했습니다.");
    }
  }

  @Test
  void remove() {
    try {
      storeService.remove(3L);
    } catch(Exception e) {
      e.printStackTrace();
      System.out.println("삭제 오류가 발생했습니다.");
    }
  }

  @Test
  void get() {
    Store store = null;
    try {
      store = storeService.get(3L);
      System.out.println(store);
    } catch(Exception e) {
      e.printStackTrace();
      System.out.println("검색오류가 발생했습니다.");
    }
  }

  @Test
  void testGet() {
    List<Store> list = null;
    try {
      list = storeService.get();
      for(Store st : list) {
        System.out.println(st);
      }
    } catch(Exception e) {
      e.printStackTrace();
      System.out.println("전체검색 오류가 발생했습니다.");
    }
  }
}