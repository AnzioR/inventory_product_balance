package com.ipb.controller;

import com.ipb.domain.Staff;
import com.ipb.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/staff")
public class StaffController {
    @Autowired
    StaffService staffService;

    @PostMapping("/add")
    public Staff register(Staff staff){
        try {
            staffService.register(staff);
            return staff;
        } catch (Exception e) {
            e.printStackTrace();
        return null;
        }
    }
    @PostMapping("/login")
    public Staff login(Staff staff){
        Staff staff1 = staffService.login(staff.getLogin_id(),staff.getPwd());
        if (staff1 ==null){
            return null;
        }
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
    @GetMapping("/detail")
    public Staff staffDetail(Long id){
        try {
            return staffService.get(id);
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
