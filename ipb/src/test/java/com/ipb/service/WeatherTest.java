package com.ipb.service;

import com.ipb.utill.WeatherUtill;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@SpringBootTest
public class WeatherTest {


  @Test
  public void context() throws Exception {
    String weatherInfo = WeatherUtill.getWeatherInfo("65", "123");
    System.out.println(weatherInfo);

  }
  @Test
  public void day() {
    Calendar cal = Calendar.getInstance();
    int year = cal.get(Calendar.YEAR);
    int month = cal.get(Calendar.MONTH+1);
    int day = cal.get(Calendar.DATE);

    Date date = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
    System.out.println(sdf);
  }
}
