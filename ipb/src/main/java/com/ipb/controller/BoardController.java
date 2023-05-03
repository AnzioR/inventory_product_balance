package com.ipb.controller;

import com.ipb.domain.Board;
import com.ipb.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
  @Autowired
  BoardService boardService;

  @PostMapping("/add")
  public Board add(Board board) {
    try {
      return boardService.register(board);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @GetMapping("/list")
  public List<Board> boardList() {
    try {
      return boardService.get();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @PutMapping("/update")
  public Board boardUpdate(Board board) {
    try {
      return boardService.modify(board);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @GetMapping("/detail")
  public Board boardDetail (Long id){
    try {
      return boardService.get(id);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @DeleteMapping("/delete")
  public void delete(Long id){
    try {
      boardService.remove(id);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
