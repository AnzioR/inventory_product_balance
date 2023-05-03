package com.ipb.mapper;

import com.ipb.domain.Staff;
import com.ipb.frame.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface StaffMapper extends MyMapper<Long, Staff> {
    public Staff selectid(String login_id) throws Exception;
}
