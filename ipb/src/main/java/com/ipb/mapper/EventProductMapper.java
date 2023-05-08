package com.ipb.mapper;

import com.ipb.domain.EventProduct;
import com.ipb.frame.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface EventProductMapper extends MyMapper<Long, EventProduct> {
  public List<EventProduct> searcheventproduct(String name) throws Exception;
}

