package com.ipb.mapper;

import com.ipb.domain.Store;
import com.ipb.domain.Weather;
import com.ipb.frame.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface WeatherMapper extends MyMapper<Long, Weather> {

}
