package com.ipb.service;

import com.ipb.domain.Staff;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StaffServiceTest {
    @Autowired
    StaffService staffService;

    @Test
    void register() {

        try {
            Staff staff = new Staff(null,"민지","sks1234","1111", 1L);
            staffService.register(staff);
            System.out.println(staff);
        } catch (Exception e) {
             e.printStackTrace();
        }

    }

    @Test
    void modify() {
    }

    @Test
    void remove() {
    }

    @Test
    void get() {
        try {
            Staff staff = staffService.get(1L);
            System.out.println(staff);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testGet() {
        try {
            List<Staff> staff = staffService.get();
            for (Staff staf : staff) {
                System.out.println(staf);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}