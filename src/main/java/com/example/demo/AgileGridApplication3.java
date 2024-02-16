package com.example.demo;

import com.example.demo.elements.Board;
import com.example.demo.elements.List;
import com.example.demo.elements.Status;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
public class AgileGridApplication3 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Client client = new Client("http://localhost:3306");

    boolean exit = false;
    while (!exit) {
      displayMenu();
      int choice = scanner.nextInt();
      scanner.nextLine(); // consume newline

      switch (choice) {
        case 1:
          addList(scanner, client);
          break;
        case 3:
          displayBoard(client);
          break;
        case 10:
          exit = true;
          break;
        default:
          System.out.println("Invalid choice");
      }
    }
    scanner.close();
  }

  private static void addList(Scanner scanner, Client client) {
    System.out.print("Enter list name: ");
    String listName = scanner.nextLine();
    Board updatedBoard = client.addList(listName);
    System.out.println(updatedBoard);
  }

  private static void displayBoard(Client client) {
    Board board = client.getBoard();
    System.out.println(board);
  }

  private static void displayMenu() {
    System.out.println("1. Add List");
    System.out.println("3. Display Board");
    System.out.println("10. Exit");
    System.out.print("Select option: ");
  }
}