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

}


