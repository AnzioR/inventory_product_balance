package com.ipb.controller;

import com.ipb.domain.Board;
import com.ipb.service.BoardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
@Api(tags = {"게시판"})
public class BoardController {
  @Autowired
  BoardService boardService;
  //

  @PostMapping("/add")
  @ApiOperation(value = "게시글 추가", notes = "title,body_text,staff_id,imgname 로 등록이 가능하다")
  public Board add(@RequestBody Board board) {
    try {
      boardService.register(board);
      return board;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @GetMapping("/list")
  @ApiOperation(value = "게시글 목록")
  public List<Board> boardList() {
    try {
      return boardService.get();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @PutMapping("/update")
  @ApiOperation(value = "게시글 수정", notes = "board_id로 title,body_text 수정 가능하다")
  public Board boardUpdate(@RequestBody Board board) {
    try {
      boardService.modify(board);
      return board;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @GetMapping("/detail")
  @ApiOperation(value = "게시글 상세보기")
  public Board boardDetail(Long id) {
    try {
      return boardService.get(id);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @DeleteMapping("/delete")
  @ApiOperation(value = "게시글 삭제")
  public void delete(Long id) {
    try {
      boardService.remove(id);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @GetMapping("/search")
  @ApiOperation(value = "게시글 검색")
  public List<Board> boardSearch(String txt) {
    try {
      return boardService.searchBoard(txt);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
