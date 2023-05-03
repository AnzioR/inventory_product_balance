package com.ipb.service;

import com.ipb.domain.Staff;
import com.ipb.dto.StaffDTO;
import com.ipb.frame.MyService;
import com.ipb.mapper.StaffMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffService implements MyService<Long, Staff> {
    @Autowired
    StaffMapper staffMapper;


    @Override
    public void register(Staff staff) throws Exception {
        staffMapper.insert(staff);
    }

    @Override
    public void modify(Staff staff) throws Exception {

    }

    @Override
    public void remove(Long id) throws Exception {

    }

    @Override
    public Staff get(Long id) throws Exception {
        return staffMapper.select(id);
    }

    @Override
    public List<Staff> get() throws Exception {
        return staffMapper.selectall();
    }

    public Staff login(String login_id, String pwd) {
        try {
            Staff staff = staffMapper.selectid(login_id);
            if(staff != null && staff.getPwd().equals(pwd)){
                return staff;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
}
