package com.ipb.controller;

import com.ipb.domain.Event;
import com.ipb.domain.Orders;
import com.ipb.domain.OrdersCart;
import com.ipb.service.OrdersCartService;
import com.ipb.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
@ResponseBody
public class OrdersController {
  @Autowired
  OrdersService ordersService;

  @Autowired
  OrdersCartService ordersCartService;

  //발주 : 점포에서 주문넣기 (재고 수량이 부족한 것은 더 주문하지 않기로 선택한 경우) ok
  @PostMapping("/addorder")
  public ResponseEntity<?> testMethod(@RequestBody Map<String, Long> requestBody) {
    // 특정 점포에 있는 카트 상품들을 주문
    try {
      Long store_id = requestBody.get("store_id");
      List<OrdersCart> addorder = ordersService.addorder(store_id);
      System.out.println(addorder);
      if (addorder.size() > 0) {
        // 일부가 주문되지 않은 경우
        return ResponseEntity.ok(addorder);
      } else {
        // 에러 없이 잘 주문된 경우
        return ResponseEntity.ok("성공");
      }
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.notFound().build();
    }
  }

  //발주 : 점포에서 주문넣기 (재고 수량이 부족한 것은 있는 재고만큼 주문하기로 선택한 경우) ok
  @PostMapping("/maxorder")
  public ResponseEntity<?> maxTestMethod(@RequestBody List<OrdersCart> unOrderableList) {
    try {
      ordersService.maxOrder(unOrderableList);
      System.out.println(unOrderableList);
      return ResponseEntity.ok().build();
    } catch (Exception e) {
      e.printStackTrace();
      return ResponseEntity.notFound().build();
    }
  }

  //발주 : 점포에서 주문 넣기
  @PostMapping("/ordersdetail/add")
  public Orders addorder(@RequestBody Orders orders){
    try {
      ordersService.register(orders);
      return orders;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  //발주 취소 : 점포에서 주문한 발주를 취소함, delivery_id를 주문취소상태인 4로 변경함
  @PutMapping("/ordersdetail/update/{storeId}/{ordersId}")
  public Orders update(@PathVariable Long storeId, @PathVariable Long ordersId, @RequestBody Orders orders) {
    try {
      ordersService.modify(orders);
      return orders;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  //발주내역 삭제 : 점포에서 주문한 발주내역을 삭제
  @DeleteMapping("/orderdetail/delete/{id}")
  public void deleteEvent(@PathVariable Long id) {
    try {
      ordersService.remove(id);
      System.out.println("발주내역이 삭제되었습니다.");
    } catch (Exception e) {
      System.out.println("발주내역 삭제를 실패했습니다.");
      throw new RuntimeException(e);
    }
  }

  //발주 조회 : 점포에서 주문한 발주내역 중 발주번호에 해당하는 내역 조회
  @GetMapping("/search/{id}")
  public Orders ordersDetail(@PathVariable Long id) {
    try {
      return ordersService.get(id);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  //발주 전체 조회 : 본사가 점포들이 발주한 내역 전체를 조회 ok
  @GetMapping("/all")
  public List<Orders> cartall() {
    try {
      return ordersService.get();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  //본사에서 날짜를 선택해서 지정된 날짜에 해당하는 발주내역을 조회 ok
  @GetMapping("/searchdate/{date}")
  public List<Orders> searchdate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) {
    try {
      return ordersService.searchdate(date);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  //발주한 상품의 배송 상태를 조회
  @GetMapping("/delivery/{id}")
  public Orders searchdeliverystatus(@PathVariable Long id) {
    try {
      return ordersService.searchdeliverystatus(id);
    } catch(Exception e) {
      System.out.println("배송상태 조회를 실패했습니다.");
      e.printStackTrace();
      return null;
    }
  }

  //매장별 전체 발주 조회 ok
  //담당 : 강다훈님 (postman 관련 프론트 문의)
  //postman 테스트 할때, Body에 form-data와 url 모두 작성해야 테스트가 성공합니다.
  //ex) http://localhost:8080/orders/selectstoreorders/2
  //id , 2
  @GetMapping("/selectstoreorders/{id}")
  public List<Orders> selectstore(@PathVariable Long id) {
    try {
      return ordersService.selectstore(id);
    } catch(Exception e) {
      System.out.println("매장별 전체 발주 조회를 실패했습니다.");
      e.printStackTrace();
      return null;
    }
  }

  //매장별 상세 발주 조회 ok
  @GetMapping("/selectstoreordersdetails/{id}")
  public List<Orders> selectdetailstoreorders(@PathVariable Long id) {
    try {
      return ordersService.selectdetailstoreorders(id);
    } catch(Exception e) {
      System.out.println("매장별 상세정보 발주 조회를 실패했습니다.");
      e.printStackTrace();
      return null;
    }
  }

  //매장별 발주 수정
  @PutMapping("/updateOrders/{id}")
  public void updateStoreOrders(@PathVariable Long id, @RequestBody Orders orders) {
    try {
      ordersService.updatestoreorders(orders);
    } catch(Exception e) {
      System.out.println("발주 수정에 실패했습니다.");
      e.printStackTrace();
    }
  }

  //매장별 발주 삭제


}
