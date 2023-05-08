package com.ipb.controller;

import com.ipb.domain.Staff;
import com.ipb.domain.Weather;
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
        Staff staff1 = staffService.login(staff.getLogin_id(),staff.getPwd());
        if (staff1 ==null){
            return null;
        }
        System.out.println(staff1);
        String weather = OpenWeatherUtill.getWeather(staff1.getArea());
        Weather weather1 = OpenWeatherUtill.WeatherInfo(weather);
        weather1.setStore_id(staff1.getStore_id());
        System.out.println(weather1);
        weatherService.register(weather1);
        return staff1;
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
