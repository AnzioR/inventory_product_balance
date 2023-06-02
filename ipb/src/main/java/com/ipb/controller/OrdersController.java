package com.ipb.controller;

import com.ipb.domain.Message;
import com.ipb.domain.Orders;
import com.ipb.domain.OrdersCart;
import com.ipb.domain.SmsResponse;
import com.ipb.service.OrdersCartService;
import com.ipb.service.OrdersService;
import com.ipb.service.SmsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = {"발주 관련 컨트롤러"})
public class OrdersController {
  @Autowired
  OrdersService ordersService;

  @Autowired
  OrdersCartService ordersCartService;

  @Autowired
  SmsService smsService;

  //발주 : 점포에서 주문넣기 (재고 수량이 부족한 것은 더 주문하지 않기로 선택한 경우) o
  @PostMapping("/addorder")
  @ApiOperation(value = "발주 : 점포에서 주문넣기 (재고 수량이 부족한 것은 더 주문하지 않기로 선택한 경우)")
  public ResponseEntity<?> addOrder(@RequestBody Map<String, Long> requestBody) {
    // 특정 점포에 있는 카트 상품들을 주문
    try {
      Long store_id = requestBody.get("store_id");
      List<OrdersCart> addorder = ordersService.addOrder(store_id);
      System.out.println(addorder);
      if (addorder != null && addorder.size() > 0) {
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

  //발주 : 점포에서 주문넣기 (재고 수량이 부족한 것은 있는 재고만큼 주문하기로 선택한 경우) o
  @PostMapping("/maxorder")
  @ApiOperation(value = "발주 : 점포에서 주문넣기 (재고 수량이 부족한 것은 있는 재고만큼 주문하기로 선택한 경우)")
  public ResponseEntity<?> maxOrder(@RequestBody List<OrdersCart> unOrderableList) {
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
//  @PostMapping("/ordersdetail/add")
//  public Orders insert(@RequestBody Orders orders){
//    try {
//      ordersService.register(orders);
//      return orders;
//    } catch (Exception e) {
//      e.printStackTrace();
//      return null;
//    }
//  }

  //발주 취소 : 점포에서 주문한 발주를 취소함, delivery_id를 주문취소상태인 4로 변경함
//  @PutMapping("/ordersdetail/update/{storeId}/{ordersId}")
//  public Orders update(@PathVariable Long storeId, @PathVariable Long ordersId, @RequestBody Orders orders) {
//    try {
//      ordersService.modify(orders);
//      return orders;
//    } catch (Exception e) {
//      throw new RuntimeException(e);
//    }
//  }

  //발주내역 삭제 : 점포에서 주문한 발주내역을 삭제
  @DeleteMapping("/orderdetail/delete/{id}")
  @ApiOperation(value = "발주내역 삭제 : 점포에서 주문한 발주내역을 삭제")
  public void deleteEvent(@PathVariable Long id) {
    try {
      ordersService.remove(id);
      System.out.println("발주내역이 삭제되었습니다.");
    } catch (Exception e) {
      System.out.println("발주내역 삭제를 실패했습니다.");
      throw new RuntimeException(e);
    }
  }

  //발주 조회 : 점포에서 주문한 발주내역 중 발주번호에 해당하는 내역 조회 o
  @GetMapping("/search/{id}")
  @ApiOperation(value = "발주 조회 : 점포에서 주문한 발주내역 중 발주번호에 해당하는 내역 조회")
  public Orders ordersDetail(@PathVariable Long id) {
    try {
      return ordersService.get(id);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  //발주 전체 조회 : 본사가 점포들이 발주한 내역 전체를 조회 o
  @GetMapping("/all")
  @ApiOperation(value = "발주 전체 조회 : 본사가 점포들이 발주한 내역 전체를 조회")
  public List<Orders> cartAll() {
    try {
      return ordersService.get();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  //본사에서 날짜를 선택해서 지정된 날짜에 해당하는 발주내역을 조회 o
  @GetMapping("/searchdate/{date}")
  @ApiOperation(value = "본사에서 날짜를 선택해서 지정된 날짜에 해당하는 발주내역을 조회")
  public List<Orders> searchDate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date) {
    try {
      return ordersService.searchDate(date);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  //발주한 상품의 배송 상태를 조회 o
  @GetMapping("/delivery/{id}")
  @ApiOperation(value = "발주한 상품의 배송 상태를 조회")
  public Orders searchDeliveryStatus(@PathVariable Long id) {
    try {
      return ordersService.searchDeliveryStatus(id);
    } catch(Exception e) {
      System.out.println("배송상태 조회를 실패했습니다.");
      e.printStackTrace();
      return null;
    }
  }

  //store_id로 해당 매장의 전체 발주내역 조회 o
  @GetMapping("/select-store-orders/{id}")
  @ApiOperation(value = "store_id로 해당 매장의 전체 발주내역 조회")
  public List<Orders> selectStore(@PathVariable Long id) {
    try {
      return ordersService.selectStore(id);
    } catch(Exception e) {
      System.out.println("매장별 전체 발주 조회를 실패했습니다.");
      e.printStackTrace();
      return null;
    }
  }


  //매장의 상세 발주내역을 발주번호로 조회 : 해당 정보 한개만 상세하게 보여줌 o
  @GetMapping("/store-orders-details/{id}")
  @ApiOperation(value = "매장의 상세 발주내역을 발주번호로 조회 : 해당 정보 한개만 상세하게 보여줌")
  public Orders selectDetailStoreOrders(@PathVariable Long id) {
    try {
      return ordersService.selectDetailStoreOrders(id);
    } catch(Exception e) {
      System.out.println("매장별 상세정보 발주 조회를 실패했습니다.");
      e.printStackTrace();
      return null;
    }
  }

  //검색한 store_id와 날짜를 조회하여 발주와 관련 상세정보를 리스트로 보여줌 o
  @GetMapping("/store-orders-detail-list")
  @ApiOperation(value = "검색한 store_id의 발주와 관련 상세정보를 리스트로 보여줌")
  public List<Orders> selectStoreOrdersByStoreId(String orderDate, Long storeId) {
    try {
      java.sql.Date od = java.sql.Date.valueOf(orderDate);
      Orders orders = new Orders(storeId, od);
      System.out.println(orders);
      return ordersService.selectStoreOrdersByStoreId(orders);
    } catch(Exception e) {
      System.out.println("매장별 상세정보 발주 조회를 실패했습니다.");
      e.printStackTrace();
      return null;
    }
  }

  //매장별 발주 수정 o
  @PutMapping("/update-orders")
  @ApiOperation(value = "매장별 발주 수정")
  public void updateStoreOrders(Long id, @RequestBody Orders orders) {
    try {
      ordersService.updateStoreOrders(orders);
    } catch(Exception e) {
      System.out.println("발주 수정에 실패했습니다.");
      e.printStackTrace();
    }
  }

  //매장별 발주 삭제 (api 정의서에 있는데 위에 작성된 삭제기능과 동일하다고 생각되어 만들지 않음)


  //발주한 상품의 배송상태를 변경 o
  // -> 상태를 변경해주면 바뀐 배송상태에 따라 발주가능상품 재고수량 또는 점포보유상품 재고수량이 변경된다.
  @PutMapping("/update-delivery")
  @ApiOperation(value = "발주한 상품의 배송상태를 변경")
  public Orders updateDeliveryStatus(@RequestBody Orders orders) {
    try {
      return ordersService.updateDeliveryStatus(orders);
    } catch(Exception e) {
      System.out.println("배송상태 변경에 실패했습니다.");
      e.printStackTrace();
      return null;
    }
  }

  //발주리스트를 날짜로 묶어서 보여줌 o
  @GetMapping("/store-orders-date/{store_id}")
  @ApiOperation(value = "발주리스트를 날짜로 묶어서 보여줌")
  public List<Orders> selectListByDate(@PathVariable Long store_id) {
    try {
      return ordersService.selectListByDate(store_id);
    } catch(Exception e) {
      System.out.println("매장별 상세정보 발주 조회를 실패했습니다.");
      e.printStackTrace();
      return null;
    }
  }

  //본사에서 발주리스트를 볼 때 날짜로 묶어서 보여줌
  @GetMapping("/store-orders-date-desc")
  @ApiOperation(value = "발본사에서 발주리스트를 볼 때 날짜로 묶어서 보여줌")
  public List<Orders> selectListByDateDesc() {
    try {
      return ordersService.selectListByDateDesc();
    } catch(Exception e) {
      System.out.println("날짜별 발주 조회를 실패했습니다.");
      e.printStackTrace();
      return null;
    }
  }


}
