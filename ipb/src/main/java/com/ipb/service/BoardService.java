package com.ipb.service;

import com.ipb.domain.Board;
import com.ipb.frame.MyService;
import com.ipb.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class BoardService implements MyService<Long, Board> {
  @Autowired
  BoardMapper boardMapper;
  @Override
  public Board register(Board board) throws Exception {
    boardMapper.insert(board);
    return board;
  }

  @Override
  public Board modify(Board board) throws Exception {
    boardMapper.update(board);
    return board;
  }

  @Override
  public void remove(Long id) throws Exception {
    boardMapper.delete(id);
  }

  @Override
  public Board get(Long id) throws Exception {
    return boardMapper.select(id);
  }

  @Override
  public List<Board> get() throws Exception {
    return boardMapper.selectall();
  }
}
