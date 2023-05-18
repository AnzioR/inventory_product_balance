package com.ipb.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SchedulerService {

  @Scheduled(fixedDelay = 1000*60*60) // 1시간마다 실행
  public void run() {
    //상품 수량 갖고오기


    System.out.println("재고수량 검색을 완료했습니다.");
  }
}
