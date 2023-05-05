package com.ipb.service;

import com.ipb.utill.OpenWeatherUtill;
import com.ipb.utill.WeatherUtill;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
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
  @Test
  public void weateher() throws Exception {
    String busan = OpenWeatherUtill.getWeather("busan");
    JSONParser jsonparser = new JSONParser();
    JSONObject weather = (JSONObject)jsonparser.parse(busan);
    JSONArray weatherA = (JSONArray) weather.get("weather");
    JSONObject WeatherB = (JSONObject) weatherA.get(0);
    String EWeather = (String)WeatherB.get("main");
    String KWeather = (String)WeatherB.get("description");



    JSONObject weatherC = (JSONObject) weather.get("main");
    Double temps = (Double) weatherC.get("temp");


    System.out.println("(영어)날씨 = " + EWeather);
    System.out.println("(한국)날씨 = " + KWeather);
    System.out.println("기온 = " + temps);



  }
}
