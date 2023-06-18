package com.ipb.service;

import com.ipb.domain.Board;
import com.ipb.frame.MyService;
import com.ipb.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BoardService implements MyService<Long, Board> {
  @Autowired
  BoardMapper boardMapper;

  @Override
  public void register(Board board) throws Exception {
    boardMapper.insert(board);
  }
  //board 의 staff id 가 아닌 staff 의 로그인 정보에서 store_id 를 받아와야함 이쪽은 세션으로 프론트에서 처리

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
