package com.ipb.service;

import com.ipb.domain.Event;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
class EventServiceTest {

  @Autowired
  EventService eventService;

  @Test
  void register() {
    try {
      Event event = new Event(null, "5.18이벤트", 2L, "518이미지", "2023-05-18", "2023-05-18");
      eventService.register(event);
      System.out.println(event);
    } catch (Exception e) {
      e.printStackTrace();
    }

    //디티오를 받아왔어 매개변수로
    // new Order



  }

  @Test
  void modify() {
    Event event = new Event(60L, "광안리 이벤트", 1L, "gwang.jpg", "2023-05-18", "2023-05-18");
    try {
      eventService.modify(event);
    } catch(Exception e) {
      e.printStackTrace();
      System.out.println("수정 오류가 발생했습니다.");
    }
  }

  @Test
  void remove() {
    try {
      eventService.remove(8L);
    } catch(Exception e) {
      e.printStackTrace();
      System.out.println("삭제 오류가 발생했습니다.");
    }
  }

  @Test
  void searchEventName() {
    Event event = null;
    try {
      event = eventService.searchEventName("과자");
      System.out.println(event);
    } catch(Exception e) {
      e.printStackTrace();
      System.out.println("검색오류가 발생했습니다.");
    }
  }

  @Test
  void get() {
    Event event = null;
    try {
      event = eventService.get(1L);
      System.out.println(event);
    } catch(Exception e) {
      e.printStackTrace();
      System.out.println("검색오류가 발생했습니다.");
    }
  }

  @Test
  void testGet() {
    List<Event> list = null;
    try {
      list = eventService.get();
      for(Event event : list) {
        System.out.println(event);
      }
    } catch(Exception e) {
      e.printStackTrace();
      System.out.println("전체검색 오류가 발생했습니다.");
    }
  }

  @Test
  void selectByType() {
    Event event = null;
    try {
      event = eventService.selectByType(1L);
      System.out.println(event);
    } catch(Exception e) {
      e.printStackTrace();
      System.out.println("이벤트 타입 검색 오류가 발생했습니다.");
    }
  }
}