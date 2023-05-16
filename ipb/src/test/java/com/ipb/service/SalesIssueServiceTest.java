package com.ipb.service;

import com.ipb.domain.OrdersIssue;
import com.ipb.domain.SalesIssue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
class SalesIssueServiceTest {

  @Autowired
  SalesIssueService salesIssueService;
  @Test
  void register() {
    try {
      SalesIssue salesIssue = new SalesIssue(null, 1L, 20, 2L, new Date());
      salesIssueService.register(salesIssue);
      System.out.println(salesIssue);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  void modify() {
    SalesIssue salesIssue = new SalesIssue(16L, 1L, 12, 2L, new Date());
    try {
      salesIssueService.modify(salesIssue);
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("수정 오류가 발생했습니다.");
    }
  }

  @Test
  void remove() {
    try {
      salesIssueService.remove(16L);
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("삭제 오류가 발생했습니다.");
    }
  }

  @Test
  void get() {
    try {
      SalesIssue salesIssue = salesIssueService.get(1L);
      System.out.println(salesIssue);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  @DisplayName("Sales issue test")
  void salesissuealllist() {
    List<SalesIssue> list = null;
    try {
      list = salesIssueService.salesissuealllist();
      System.out.println(list.size());
      for (SalesIssue salesIssue : list) {
        System.out.println(salesIssue);
      }
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("전체검색 오류가 발생했습니다.");
    }
  }
  @Test
  void salesissuestore() {
    try {
      List<SalesIssue> salesissuestore = salesIssueService.salesissuestore(2L);
      for (SalesIssue o : salesissuestore){
        System.out.println(o);
      }
    } catch(Exception e) {
      e.printStackTrace();
      System.out.println("오류가 발생.");
    }
  }
  @Test
  void ordersissuedesc() {
    try {
      List<SalesIssue> salesissuedesc = salesIssueService.salesissuedesc(3L);
      for (SalesIssue o : salesissuedesc){
        System.out.println(o);
      }
    } catch(Exception e) {
      e.printStackTrace();
      System.out.println("오류가 발생.");
    }
  }
}

