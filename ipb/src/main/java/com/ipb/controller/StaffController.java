package com.ipb.controller;

import com.ipb.domain.Staff;
import com.ipb.domain.Weather;
import com.ipb.domain.WeatherStatus;
import com.ipb.service.StaffService;
import com.ipb.service.WeatherService;
import com.ipb.utill.OpenWeatherUtill;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/staff")
public class StaffController {
    @Autowired
    StaffService staffService;
    @Autowired
    WeatherService weatherService;

    @PostMapping("/add")
    public Staff register(Staff staff) throws Exception {
        try {
            if (staffService.checkId(staff.getLogin_id())) {
                staffService.register(staff);
            } else {
                throw new Exception("있는 아이디");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return staff;
    }
    @PostMapping("/login")
    public Staff login(@RequestBody Staff staff) throws Exception {
        Staff loginStaff = staffService.login(staff.getLogin_id(),staff.getPwd());
        if (loginStaff ==null){
            return null;
        }
        // area 를 통해서 json 가져오기
        String weather = OpenWeatherUtill.getWeather(loginStaff.getArea());
        // 가져온 json 파싱해서 필요한값 가져오기
        Weather new_weather = OpenWeatherUtill.WeatherInfo(weather);
        //로그인한 staff의 storeid 를 입력하기
        new_weather.setStore_id(loginStaff.getStore_id());
        //DB에 날씨 데이터 최종 저장하기
        weatherService.register(new_weather);
        return loginStaff;
        //원래 대로라면 날씨랑 같이 넣어줘야해
    }

    @PostMapping("/weather")
    public WeatherStatus getWeatherInfo(@RequestBody Staff staff) throws Exception {

        // 이건 3시간 단위로 조회할꺼야

        // 기존 날씨 데이터 조회
//        Weather old_weather = weatherService.get(staff.getStore_id()); // SELECT * FROM ipb1.weather where store_id = 3 order by weather_date desc limit 1;

        // 새로운 날씨 데이터
        String weather_json = OpenWeatherUtill.getWeather(staff.getArea());
        Weather new_weather = OpenWeatherUtill.WeatherInfo(weather_json);
        //DB에 날씨 데이터 최종 저장하기
        new_weather.setStore_id(staff.getStore_id());
        weatherService.register(new_weather);

//        boolean isSame = old_weather.getStatus().equals(new_weather.getStatus());
//        if(isSame) {
//            // 날씨가 기존의 날씨와 동일하면 false 반환
//            return new WeatherStatus(false, null);
////                    return "{\"바꿨냐\": false}";
//        } else {
            // 날씨가 기존의 날씨와 달라지면 true 및 바뀐 날씨 반환
            return new WeatherStatus(true, new_weather.getStatus());
//                    return "{\"바꿨냐\": true, \"바뀐날씨\":" + new_weather.getStatus() +"}";
//        }
    }


    @GetMapping("/list")
    public List<Staff> staffList(){
        try {
            return staffService.get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @GetMapping("/listname")
    public List<Staff> staffListName(){
        try {
            return staffService.selectallname();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
    @GetMapping("/detail")
    public Staff staffDetail(Long id){
        try {
            return staffService.get(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @PutMapping("/update")
    public Staff staffUpdate(Staff staff){
        try {
            staffService.modify(staff);
            return staff;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    @DeleteMapping("/delete")
    public void delete(Long id){
        try {
            staffService.remove(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
