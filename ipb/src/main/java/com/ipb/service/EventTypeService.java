package com.ipb.service;

import com.ipb.domain.EventType;
import com.ipb.domain.OrdersCart;
import com.ipb.frame.MyService;
import com.ipb.mapper.EventTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventTypeService implements MyService<Long, EventType> {

  @Autowired
  EventTypeMapper eventTypeMapper;

  @Override
  public void register(EventType eventType) throws Exception {
    eventTypeMapper.insert(eventType);
  }

  @Override
  public void modify(EventType eventType) throws Exception {
    eventTypeMapper.update(eventType);
  }

  @Override
  public void remove(Long id) throws Exception {
    eventTypeMapper.delete(id);
  }

  @Override
  public EventType get(Long id) throws Exception {
    return eventTypeMapper.select(id);
  }

  @Override
  public List<EventType> get() throws Exception {
    return eventTypeMapper.selectall();
  }
}
