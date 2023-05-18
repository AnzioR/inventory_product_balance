package com.ipb.controller;

import com.ipb.domain.OrdersCart;
import com.ipb.service.OrdersCartService;
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
  @PutMapping("/update/{id}")
  public OrdersCart updateCart(@PathVariable Long id, @RequestBody OrdersCart ordersCart) {
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
  @GetMapping("/search/{id}")
  public OrdersCart cartDetail(@PathVariable Long id) {
    try {
      return ordersCartService.get(id);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  // 발주 카트 전체 조회 : 본사에서 모든 점포들이 발주카트에 담은 내역 전체를 조회 ok
  @GetMapping("/all")
  public List<OrdersCart> cartall() {
    try {
      return ordersCartService.get();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  //발주카트를 리스트로 만들어서 발주 버튼을 클릭하면 리스트를 넘겨줌 OK
  @GetMapping("/cartlist/{id}")
  public ResponseEntity<List<OrdersCart>> cartlist(@PathVariable Long id) {
    try {
      return ResponseEntity.ok(ordersCartService.cartlist(id));
    } catch(Exception e) {
      System.out.println("주문정보 전송에 오류가 발생했습니다.");
      e.printStackTrace();
      return ResponseEntity.notFound().build();
    }
  }

  //발주카트 비우기 : 발주카트에 담긴 상품을 삭제하는 기능이 아님! 발주카트가 주문으로 넘어갈때 카트 전체가 비워지는 기능 ok
  @DeleteMapping("/removecart/{id}")
  public void deleteCart(@PathVariable Long id) {
    try {
      ordersCartService.removecart(id);
      System.out.println("발주카트가 비워졌습니다.");
    } catch (Exception e) {
      System.out.println("발주카트비우기를 실패했습니다.");
      throw new RuntimeException(e);
    }
  }
  

}
