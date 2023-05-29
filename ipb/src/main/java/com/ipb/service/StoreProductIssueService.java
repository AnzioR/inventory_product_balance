package com.ipb.service;

import com.ipb.domain.StoreProduct;
import com.ipb.domain.StoreProductIssue;
import com.ipb.frame.MyService;
import com.ipb.mapper.StoreProductIssueMapper;
import com.ipb.mapper.StoreProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import static java.time.LocalDate.now;

@Service
public class StoreProductIssueService implements MyService<Long, StoreProductIssue> {

  @Autowired
  StoreProductIssueMapper storeProductIssueMapper;

  @Autowired
  StoreProductMapper storeProductMapper;


  @Override
  public void register(StoreProductIssue storeProductIssue) throws Exception {
    storeProductIssueMapper.insert(storeProductIssue);
  }

  @Override
  public void modify(StoreProductIssue storeProductIssue) throws Exception {
    storeProductIssueMapper.update(storeProductIssue);
  }

  @Override
  public void remove(Long id) throws Exception {
    storeProductIssueMapper.delete(id);
  }

  @Override
  public StoreProductIssue get(Long id) throws Exception {
    return storeProductIssueMapper.select(id);
  }

  @Override
  public List<StoreProductIssue> get() throws Exception {
    return storeProductIssueMapper.selectall();
  }

  public List<StoreProductIssue> salesissuealllist() throws Exception {
    return storeProductIssueMapper.salesissuealllist();
  }

  public List<StoreProductIssue> salesissuestore(Long store_id) throws Exception {
    return storeProductIssueMapper.salesissuestore(store_id);
  }
  public List<StoreProductIssue> salesissuedesc(Long sales_desc_id) throws Exception {
    return storeProductIssueMapper.salesissuedesc(sales_desc_id);
  }


//  @Scheduled(fixedDelay = 60 * 60 * 1000)
//  public void checkDate() throws Exception {
//    //오늘 날짜를 구하고 D-1 날짜를 가져온다.
//    LocalDate todayLocalDate = now();
//    LocalDate lastDate = todayLocalDate.minusDays(1);
//    System.out.println("어제날짜 = " + lastDate);
//
//    //날짜 형식 변경
//    DateTimeFormatter dateForm = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//    String dateString = lastDate.format(dateForm);
//    System.out.println("날짜형식 수정 = " + dateString);
//
//    //점포보유상품의 유통기한이 D-1인 상품의 폐기를 위해 상품들을 가져온다.
//    List<StoreProduct> storeProductList = storeProductMapper.findProductIdAndProductCode();
//    for(StoreProduct sp : storeProductList) {
//      System.out.println(sp);
//
//      //상품의 유통기한이 D-1인 상황
//      if (sp.getExp().equals(dateString)) {
//        //폐기 버튼을 클릭하면, 점포보유상품 이슈테이블로 등록
//        storeProductIssueMapper.insert(new StoreProductIssue(sp.getStore_id(), sp.getQnt(), 5L, new Date()));
//      }
//    }
//  }




}


