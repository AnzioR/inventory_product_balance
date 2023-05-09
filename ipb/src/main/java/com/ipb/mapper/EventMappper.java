package com.ipb.mapper;

import com.ipb.domain.Event;
import com.ipb.frame.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface EventMappper extends MyMapper<Long, Event> {
  public Event searcheventname(String name) throws Exception;

  public Event selectbytype(Long id) throws Exception;
}
