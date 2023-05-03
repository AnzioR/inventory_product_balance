package com.ipb.mapper;

import com.ipb.domain.Board;
import com.ipb.frame.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface BoardMapper extends MyMapper<Long, Board> {
}
