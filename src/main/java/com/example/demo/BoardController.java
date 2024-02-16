package com.example.demo;

import com.example.demo.elements.Board;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardController {

  private Board board;
  private SimpMessagingTemplate template;

  public BoardController(SimpMessagingTemplate template) {
    this.board = new Board();
    this.template = template;
  }

  @PostMapping("/board/list")
  public void addList(@RequestBody String listName) {
    board.addList(listName);
    template.convertAndSend("/topic/board", board);
  }
}