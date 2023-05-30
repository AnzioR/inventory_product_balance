package com.ipb.mapper;

import com.ipb.domain.Store;
import com.ipb.frame.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface StoreMapper extends MyMapper<Long, Store> {
  public String selectNumber(Long id) throws Exception;
}
