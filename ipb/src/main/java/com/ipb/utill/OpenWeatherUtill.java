package com.ipb.utill;



import com.ipb.domain.Weather;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class OpenWeatherUtill {
  //parse 디펜던시
  public static String getWeather(String local) throws IOException, ParseException {

    StringBuilder urlBuilder = new StringBuilder("https://api.openweathermap.org/data/2.5/weather?q=" + local + ",kr&appid=bb12b4c410743f76108847dd974e4a61&units=metric&lang=kr"); /*URL*/
    URL url = new URL(urlBuilder.toString());
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("GET");
    conn.setRequestProperty("Content-type", "application/json");
    System.out.println("Response code: " + conn.getResponseCode());
    BufferedReader rd;
    if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
      rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
    } else {
      rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
    }
    StringBuilder sb = new StringBuilder();
    String line;
    while ((line = rd.readLine()) != null) {
      sb.append(line);
    }
    rd.close();
    conn.disconnect();
    return sb.toString();
  }
  public static Weather WeatherInfo(String txt) throws ParseException {

    JSONParser jsonParser = new JSONParser();
    JSONObject weather = (JSONObject)jsonParser.parse(txt);
    JSONArray weatherA = (JSONArray) weather.get("weather");
    JSONObject WeatherB = (JSONObject) weatherA.get(0);
    String EWeather = (String)WeatherB.get("main");

    JSONObject weatherC = (JSONObject) weather.get("main");
    Double temps = (Double) weatherC.get("temp");

    System.out.println("(영어)날씨 = " + EWeather);
    System.out.println("기온 = " + temps);
    //웨더에 저장하는 로직
    Weather new_weather = new Weather();
    new_weather.setTemp(temps);
    new_weather.setStatus(EWeather);
    return new_weather;
    // 이쪽 파싱 불러 오기 안댐
  }
}
