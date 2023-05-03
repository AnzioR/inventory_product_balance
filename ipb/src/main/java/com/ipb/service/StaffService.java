package com.ipb.service;

import com.ipb.domain.Staff;
import com.ipb.frame.MyService;
import com.ipb.mapper.StaffMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
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
}

