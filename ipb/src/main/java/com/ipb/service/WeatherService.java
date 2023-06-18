package com.ipb.service;


import com.ipb.domain.Weather;
import com.ipb.frame.MyService;
import com.ipb.mapper.WeatherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class WeatherService implements MyService<Long, Weather> {

  @Autowired
  WeatherMapper weatherMapper;


  @Override
  public void register(Weather weather) throws Exception {
    weatherMapper.insert(weather);

  }

  @Override
  public void modify(Weather weather) throws Exception {

  }

  @Override
  public void remove(Long id) throws Exception {
    weatherMapper.delete(id);
  }

  @Override
  public Weather get(Long store_id) throws Exception {
    return weatherMapper.select(store_id);
  }

  @Override
  public List<Weather> get() throws Exception {
    return null;
  }
}
