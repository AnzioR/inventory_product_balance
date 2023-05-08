package com.ipb.controller;

import com.ipb.domain.Event;
import com.ipb.domain.EventProduct;
import com.ipb.domain.EventType;
import com.ipb.service.EventProductService;
import com.ipb.service.EventService;
import com.ipb.service.EventTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class EventController {

  @Autowired
  EventService eventService;
  @Autowired
  EventTypeService eventTypeService;
  @Autowired
  EventProductService eventProductService;


  //이벤트 등록 ok
  @PostMapping("/event/add")
  public Event register(Event event){
    try {
      eventService.register(event);
      return event;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  //이벤트 상품 수정 : id 작성안하면 에러 발생하므로 적어줘야 한다 ok
  @PutMapping("/eventdetail/update/{id}")
  public Event updateEvent(@PathVariable Long id, Event event) {
    try {
      eventService.modify(event);
      return event;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  //이벤트 상품 삭제 ok
  @DeleteMapping("/eventdetail/delete/{id}")
  public void deleteEvent(@PathVariable Long id) {
    try {
      eventService.remove(id);
      System.out.println("이벤트가 삭제되었습니다.");
    } catch (Exception e) {
      System.out.println("이벤트 삭제를 실패했습니다.");
      throw new RuntimeException(e);
    }
  }

  //이벤트 이름으로 조회하기 ok
  @GetMapping("/event/search/{name}")
  public Event searcheventname(@PathVariable String name) {
    try {
      return eventService.searcheventname(name);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  //이벤트 상세 조회하기 ok
  @GetMapping("/eventdetail/{id}")
  public Event eventDetail(@PathVariable Long id) {
    try {
      return eventService.get(id);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  //전체 이벤트 목록 조회 ok
  @GetMapping("/eventlist")
  public List<Event> eventList() {
    try {
      return eventService.get();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  //이벤트 타입별로 조회하기 ok
  @GetMapping("/selectbytype/{id}")
  public List<Event> selectbytype(@PathVariable Long id) {
    try {
      return eventService.selectbytype(id);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  //이벤트가 진행중인 상품 조회 ok
  @GetMapping("/eventproductlist/{name}")
  public List<EventProduct> searcheventproduct(@PathVariable String name) {
    try {
      return eventProductService.searcheventproduct(name);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }



//  //이벤트 타입 등록하기
//  @PostMapping("/eventtype/add")
//  public EventType register(EventType type){
//    try {
//      eventTypeService.register(type);
//      return type;
//    } catch (Exception e) {
//      e.printStackTrace();
//      return null;
//    }
//  }
//
//  //이벤트 타입 수정하기
//  @PutMapping("/eventtypedetail/update/{id}")
//  public EventType updateEvent(@PathVariable Long id, EventType type) {
//    try {
//      eventTypeService.modify(type);
//      return type;
//    } catch (Exception e) {
//      throw new RuntimeException(e);
//    }
//  }
//
//  //이벤트 타입 삭제하기
//  @DeleteMapping("/eventtypedetail/delete/{id}")
//  public void deleteEventType(@PathVariable Long id) {
//    try {
//      eventTypeService.remove(id);
//      System.out.println("이벤트타입이 삭제되었습니다.");
//    } catch (Exception e) {
//      System.out.println("이벤트타입 삭제를 실패했습니다.");
//      throw new RuntimeException(e);
//    }
//  }
//
//  //이벤트 타입 상세조회
//  @GetMapping("/eventtypedetail/{id}")
//  public EventType eventTypeDetail(@PathVariable Long id) {
//    try {
//      return eventTypeService.get(id);
//    } catch (Exception e) {
//      throw new RuntimeException(e);
//    }
//  }
//
//  //이벤트 타입 전체조회
//  @GetMapping("/eventtypelist")
//  public List<EventType> eventTypeList() {
//    try {
//      return eventTypeService.get();
//    } catch (Exception e) {
//      throw new RuntimeException(e);
//    }
//  }
//


}
