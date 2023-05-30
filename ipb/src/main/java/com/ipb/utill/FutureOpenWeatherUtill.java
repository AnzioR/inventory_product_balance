package com.ipb.utill;



import com.ipb.domain.Store;
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


public class FutureOpenWeatherUtill {

  //parse 디펜던시
  public static String getFutureWeather(Store store) throws IOException, ParseException {

    StringBuilder urlBuilder = new StringBuilder("http://api.openweathermap.org/data/2.5/forecast?lat="+store.getLat()+"&lon="+store.getLon()+"&appid=bb12b4c410743f76108847dd974e4a61"); /*URL*/
    URL url = new URL(urlBuilder.toString());
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("GET");
    conn.setRequestProperty("Content-type", "application/json");
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

  public static String FutureWeatherInfo(String txt) throws ParseException {

    JSONParser jsonParser = new JSONParser();
    JSONObject weather = (JSONObject)jsonParser.parse(txt);
    JSONArray weatherA = (JSONArray) weather.get("list");
    JSONObject WeatherB = (JSONObject) weatherA.get(15);
    JSONArray EWeather = (JSONArray)WeatherB.get("weather");
    JSONObject EAWeather = (JSONObject) EWeather.get(0);
    String EA = (String)EAWeather.get("main");
    return EA;
  }
}
