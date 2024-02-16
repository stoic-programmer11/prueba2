package com.example.demo;

import com.example.demo.elements.Board;
import org.springframework.web.client.RestTemplate;

import com.example.demo.elements.Board;
import org.springframework.web.client.RestTemplate;

public class Client {

  private RestTemplate restTemplate;
  private String serverUrl;

  public Client(String serverUrl) {
    this.restTemplate = new RestTemplate();
    this.serverUrl = serverUrl;
  }

  public Board getBoard() {
    return restTemplate.getForObject(serverUrl + "/board", Board.class);
  }

  public Board addList(String listName) {
    return restTemplate.postForObject(serverUrl + "/board/list", listName, Board.class);
  }
}