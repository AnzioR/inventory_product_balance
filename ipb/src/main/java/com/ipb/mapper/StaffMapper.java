package com.ipb.mapper;

import com.ipb.domain.Staff;
import com.ipb.frame.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StaffMapper extends MyMapper<Long, Staff> {
  public Staff selectid(String login_id) throws Exception;

  public List<Staff> selectallname() throws Exception;

  public Integer checkID(String login_id) throws Exception;
}
