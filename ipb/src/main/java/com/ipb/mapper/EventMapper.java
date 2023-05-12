package com.ipb.mapper;

import com.ipb.domain.Event;
import com.ipb.frame.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface EventMapper extends MyMapper<Long, Event> {
  public Event searcheventname(String name) throws Exception;

  public Event selectbytype(Long id) throws Exception;
}
