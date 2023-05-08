package com.ipb.service;

import com.ipb.domain.Store;
import com.ipb.domain.Weather;
import com.ipb.frame.MyService;
import com.ipb.mapper.StoreMapper;
import com.ipb.mapper.WeatherMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
  public Weather get(Long aLong) throws Exception {
    return null;
  }

  @Override
  public List<Weather> get() throws Exception {
    return null;
  }


}
