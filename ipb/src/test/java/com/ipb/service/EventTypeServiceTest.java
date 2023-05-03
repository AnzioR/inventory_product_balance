package com.ipb.service;

import com.ipb.domain.EventType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EventTypeServiceTest {

  @Autowired
  EventTypeService eventTypeService;

  @Test
  void register() {
    try {
      EventType type = new EventType(null, "과자 1개 증정 이벤트");
      eventTypeService.register(type);
      System.out.println(type);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  void modify() {
    EventType type = new EventType(1L, "2+2 이벤트");
    try {
      eventTypeService.modify(type);
    } catch(Exception e) {
      e.printStackTrace();
      System.out.println("수정 오류가 발생했습니다.");
    }
  }

  @Test
  void remove() {
    try {
      eventTypeService.remove(4L);
    } catch(Exception e) {
      e.printStackTrace();
      System.out.println("삭제 오류가 발생했습니다.");
    }
  }

  @Test
  void get() {
    EventType type = null;
    try {
      type = eventTypeService.get(1L);
      System.out.println(type);
    } catch(Exception e) {
      e.printStackTrace();
      System.out.println("검색오류가 발생했습니다.");
    }
  }

  @Test
  void testGet() {
    List<EventType> list = null;
    try {
      list = eventTypeService.get();
      for(EventType type : list) {
        System.out.println(type);
      }
    } catch(Exception e) {
      e.printStackTrace();
      System.out.println("전체검색 오류가 발생했습니다.");
    }
  }
}