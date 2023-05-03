package com.ipb.service;

import com.ipb.domain.Board;
import com.ipb.domain.Store;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BoardServiceTest {

  @Autowired
  BoardService boardService;

  @Test
  void register() {
    try {
      Board board = new Board(null, "테스트글입니다", "본문입니다.", 1L, null, null);
      boardService.register(board);
      System.out.println(board);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  void modify() {
    Board board = new Board(3L, "테스트글 수정입니다", "본문 수정", 1L, null, null);
    try {
      boardService.modify(board);
    } catch(Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  void remove() {
    try {
      boardService.remove(3L);
    } catch(Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  void get() {
    Board board = null;
    try {
      board = boardService.get(2L);
      System.out.println(board);
    } catch(Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  void testGet() {
    List<Board> list = null;
    try {
      list = boardService.get();
      for(Board board : list) {
        System.out.println(board);
      }
    } catch(Exception e) {
      e.printStackTrace();
    }
  }
}