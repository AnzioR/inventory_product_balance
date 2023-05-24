package com.ipb.service;


import com.ipb.domain.SalesIssue;
import com.ipb.frame.MyService;
import com.ipb.mapper.SalesIssueMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesIssueService implements MyService<Long, SalesIssue> {

  @Autowired
  SalesIssueMapper salesIssueMapper;


  @Override
  public void register(SalesIssue salesIssue) throws Exception {
    salesIssueMapper.insert(salesIssue);
  }

  @Override
  public void modify(SalesIssue salesIssue) throws Exception {
    salesIssueMapper.update(salesIssue);
  }

  @Override
  public void remove(Long id) throws Exception {
    salesIssueMapper.delete(id);
  }

  @Override
  public SalesIssue get(Long id) throws Exception {
    return salesIssueMapper.select(id);
  }

  @Override
  public List<SalesIssue> get() throws Exception {
    return salesIssueMapper.selectall();
  }

    public List<SalesIssue> salesissuealllist() throws Exception {
    return salesIssueMapper.salesissuealllist();
  }

  public List<SalesIssue> salesissuestore(Long store_id) throws Exception {
    return salesIssueMapper.salesissuestore(store_id);
  }
  public List<SalesIssue> salesissuedesc(Long sales_desc_id) throws Exception {
    return salesIssueMapper.salesissuedesc(sales_desc_id);
  }
  }


