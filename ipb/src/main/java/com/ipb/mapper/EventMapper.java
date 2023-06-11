package com.ipb.mapper;

import com.ipb.domain.Event;
import com.ipb.frame.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface EventMapper extends MyMapper<Long, Event> {
  public Event searchEventName(String name) throws Exception;

  public Event selectByType(Long id) throws Exception;

  public Event findPreviousEvent(String dateString) throws Exception;
//  public List<Event> availablevent() throws Exception;
//  public List<Event> soonevent() throws Exception;
//  public List<Event> expirevent() throws Exception;
}