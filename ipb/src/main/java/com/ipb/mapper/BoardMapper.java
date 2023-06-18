package com.ipb.mapper;

import com.ipb.domain.Board;
import com.ipb.frame.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;

@Mapper
@Repository
public interface BoardMapper extends MyMapper<Long, Board> {
  public List<Board> searchBoard(String txt);

}
