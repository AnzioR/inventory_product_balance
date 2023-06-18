package com.ipb.mapper;

import com.ipb.domain.StoreProductIssue;
import com.ipb.frame.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StoreProductIssueMapper extends MyMapper<Long, StoreProductIssue> {

  public List<StoreProductIssue> salesissuealllist() throws Exception;

  public List<StoreProductIssue> salesissuestore(Long store_id) throws Exception;

  public List<StoreProductIssue> salesissuedesc(Long orders_desc_id) throws Exception;
}
