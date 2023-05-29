package com.ipb.service;

import com.ipb.domain.Weather;
import com.ipb.utill.FutureOpenWeatherUtill;
import com.ipb.utill.OpenWeatherUtill;
import com.ipb.utill.WeatherUtill;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@SpringBootTest
public class WeatherTest {
  @Autowired
  WeatherService weatherService;




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
  @Test
  public void weateher() throws Exception {
    String busan = OpenWeatherUtill.getWeather("seoul");
    System.out.println(busan);

    org.json.simple.parser.JSONParser jsonParser = new JSONParser();
    org.json.simple.JSONObject weather = (org.json.simple.JSONObject)jsonParser.parse(busan);
    org.json.simple.JSONArray weatherA = (JSONArray) weather.get("weather");
    org.json.simple.JSONObject WeatherB = (org.json.simple.JSONObject) weatherA.get(0);
    String EWeather = (String)WeatherB.get("main");
    String icon = (String)WeatherB.get("icon");
    System.out.println(EWeather);
    System.out.println(icon);

    org.json.simple.JSONObject weatherC = (JSONObject) weather.get("main");
    Double temps = (Double) weatherC.get("temp");


    System.out.println(temps);










//    Weather weather = OpenWeatherUtill.WeatherInfo(busan);
//    weatherService.register(weather);
//    System.out.println(weather);


  }
  @Test
  public void futureweateher() throws Exception {
    String futureWeather = FutureOpenWeatherUtill.getFutureWeather();

    String s = FutureOpenWeatherUtill.FutureWeatherInfo(futureWeather);
    System.out.println(s);

//    Weather weather = FutureOpenWeatherUtill.FutureWeatherInfo(futureWeather);
//    System.out.println(weather);


  }
}
