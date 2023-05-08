package com.ipb.service;

import com.ipb.domain.Board;
import com.ipb.frame.MyService;
import com.ipb.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class BoardService implements MyService<Long, Board> {
  @Autowired
  BoardMapper boardMapper;
  @Override
  public void register(Board board) throws Exception {
    boardMapper.insert(board);
  }

  @Override
  public void modify(Board board) throws Exception {
    boardMapper.update(board);
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

  public List<Board> searchBoard(String txt) throws Exception {
    return boardMapper.searchBoard(txt);
  }
}
