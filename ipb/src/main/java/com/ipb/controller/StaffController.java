package com.ipb.controller;

import com.ipb.domain.Staff;
import com.ipb.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StaffController {
    @Autowired
    StaffService staffService;

    @PostMapping("/register")
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
    @GetMapping("/stafflist")
    public List<Staff> staffList(){
        try {
            return staffService.get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
    @GetMapping("/staffdetail")
    public Staff staffDetail(Long id){
        try {
            return staffService.get(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
