package com.example.demo;

import java.net.Socket;

public class ServerChecker {
  public static void main(String[] args) {
    String serverName = "localhost";
    int port = 3306;

    try (Socket socket = new Socket(serverName, port)) {
      System.out.println("Successfully connected to the server on port " + port);
    } catch (Exception e) {
      System.out.println("Could not connect to the server on port " + port);
    }
  }
}