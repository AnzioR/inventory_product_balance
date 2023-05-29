package com.ipb.controller;

import com.ipb.domain.OrdersCart;
import com.ipb.service.OrdersCartService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class OrdersCartController {

  @Autowired
  OrdersCartService ordersCartService;

  //발주카트에 상품 담기 ok
  @ApiOperation(value = "발주카트 담기 ")
  @PostMapping("/add")
  public OrdersCart register(@RequestBody OrdersCart ordersCart) {
    try {
      ordersCartService.register(ordersCart);
      return ordersCart;
    } catch (Exception e) {
      System.out.println("카트담기에 실패했습니다.");
      e.printStackTrace();
      return null;
    }
  }

  //발주카트에서 주문하려는 상품의 수량을 변경하기 ok
  @ApiOperation(value = "발주카트에 담긴 상품 수량 수정 " ,notes = "Integer qnt 변경가능")
  @PutMapping("/update")
  public OrdersCart updateCart(Long id, @RequestBody OrdersCart ordersCart) {
    try {
      ordersCartService.modify(ordersCart);
      return ordersCart;
    } catch(Exception e) {
      System.out.println("수량 변경에 실패했습니다.");
      e.printStackTrace();
      return null;
    }
  }

  //발주 카트에 담긴 상품 삭제 ok
  @ApiOperation(value = "발주카트 삭제 : 발주카트에 담긴 상품 자체를 삭제함 ",notes = "orders_cart id로 삭제")
  @DeleteMapping("/delete/{id}")
  public void delete(@PathVariable Long id) {
    try {
      ordersCartService.remove(id);
      System.out.println("담긴 상품이 삭제되었습니다.");
    } catch (Exception e) {
      System.out.println("상품 삭제를 실패했습니다.");
      throw new RuntimeException(e);
    }
  }

  //발주 카트 조회 : 점포에서 발주카트에 담은 상품 중 아이디번호에 해당되는 내역을 조회 ok
  @ApiOperation(value = "발주카트에 담은 상품 중 아이디번호에 해당되는 내역을 조회 " ,notes = "orders_cart id로 조회")
  @GetMapping("/search/{id}")
  public OrdersCart cartDetail(@PathVariable Long id) {
    try {
      return ordersCartService.get(id);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  // 발주 카트 전체 조회 : 본사에서 모든 점포들이 발주카트에 담은 내역 전체를 조회 ok
  @ApiOperation(value = "발주 카트 전체 조회 ")
  @GetMapping("/all")
  public List<OrdersCart> cartAll() {
    try {
      return ordersCartService.get();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  //발주카트를 리스트로 만들어서 발주 버튼을 클릭하면 리스트를 넘겨줌 OK
  @ApiOperation(value = "발주카트 조회" ,notes = "store_id로 조회")
  @GetMapping("/cartlist/{id}")
  public ResponseEntity<List<OrdersCart>> cartList(@PathVariable Long id) {
    try {
      return ResponseEntity.ok(ordersCartService.cartList(id));
    } catch(Exception e) {
      System.out.println("주문정보 전송에 오류가 발생했습니다.");
      e.printStackTrace();
      return ResponseEntity.notFound().build();
    }
  }

  //발주카트 비우기 : 발주카트에 담긴 상품을 삭제하는 기능이 아님! 발주카트가 주문으로 넘어갈때 카트 전체가 비워지는 기능 ok
  @ApiOperation(value = "발주카트 비우기" ,notes = "store_id 사용")
  @DeleteMapping("/removecart/{id}")
  public void deleteCart(@PathVariable Long id) {
    try {
      ordersCartService.removeCart(id);
      System.out.println("발주카트가 비워졌습니다.");
    } catch (Exception e) {
      System.out.println("발주카트비우기를 실패했습니다.");
      throw new RuntimeException(e);
    }
  }
  

}
