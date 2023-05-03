package com.ipb.service;

import com.ipb.domain.Board;
import com.ipb.domain.Event;
import com.ipb.frame.MyService;
import com.ipb.mapper.EventMappper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService implements MyService<Long, Event> {

  @Autowired
  EventMappper eventMappper;

  @Override
  public Board register(Event event) throws Exception {
    eventMappper.insert(event);
    return null;
  }

  @Override
  public Board modify(Event event) throws Exception {
    eventMappper.update(event);
    return null;
  }

  @Override
  public void remove(Long id) throws Exception {
    eventMappper.delete(id);
  }

  @Override
  public Event get(Long id) throws Exception {
    return eventMappper.select(id);
  }

  @Override
  public List<Event> get() throws Exception {
    return eventMappper.selectall();
  }
}
