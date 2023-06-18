package com.ipb.service;


import com.ipb.domain.Staff;
import com.ipb.frame.MyService;
import com.ipb.mapper.StaffMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class StaffService implements MyService<Long, Staff> {
  //UserDetailService
  @Autowired
  StaffMapper staffMapper;


  @Override
  public void register(Staff staff) throws Exception {
    staffMapper.insert(staff);
  }

  @Override
  public void modify(Staff staff) throws Exception {
    staffMapper.update(staff);
  }

  @Override
  public void remove(Long id) throws Exception {
    staffMapper.delete(id);
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
      if (staff != null && staff.getPwd().equals(pwd)) {
        return staff;
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;

  }

  public List<Staff> selectallname() throws Exception {
    return staffMapper.selectallname();

  }

  public Boolean checkId(String id) throws Exception {
    Boolean result = true;
    if (staffMapper.checkID(id) >= 1) {
      result = false;
    }
    return result;
  }

}

