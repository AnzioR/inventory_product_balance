package com.ipb.service;

import com.ipb.domain.Event;
import com.ipb.frame.MyService;
import com.ipb.mapper.EventMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService implements MyService<Long, Event> {

  @Autowired
  EventMapper eventMapper;

  @Override
  public void register(Event event) throws Exception {
    eventMapper.insert(event);

  }

  @Override
  public void modify(Event event) throws Exception {
    eventMapper.update(event);
  }

  @Override
  public void remove(Long id) throws Exception {
    eventMapper.delete(id);
  }

  @Override
  public Event get(Long id) throws Exception {
    return eventMapper.select(id);
  }

  @Override
  public List<Event> get() throws Exception {
    return eventMapper.selectall();
  }
//  public List<Event> available() throws Exception {
//    return eventMapper.availablevent();
//  }
//  public List<Event> soon() throws Exception {
//    return eventMapper.soonevent();
//  }
//  public List<Event> expire() throws Exception {
//    return eventMapper.expirevent();
//  }


  public Event searchEventName(String name) throws Exception {
    return eventMapper.searchEventName(name);
  }

  public Event selectByType(Long id) throws Exception {
    return eventMapper.selectByType(id);
  }
}
