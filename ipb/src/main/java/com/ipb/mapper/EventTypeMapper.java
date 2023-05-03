package com.ipb.mapper;

import com.ipb.domain.EventType;
import com.ipb.frame.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface EventTypeMapper extends MyMapper<Long, EventType> {
}
