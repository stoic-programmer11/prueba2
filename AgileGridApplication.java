package com.example.demo;

import com.example.demo.elements.Board;
import com.example.demo.elements.List;
import com.example.demo.elements.Status;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class AgileGridApplication {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Board board = new Board();

    boolean exit = false;
    while (!exit) {
      displayMenu();
      int choice = scanner.nextInt();
      scanner.nextLine(); // consume newline

      switch (choice) {
        case 1:
          addList(scanner, board);
          break;
        case 2:
          addCardToList(scanner, board);
          break;
        case 3:
          displayBoard(board);
          break;
        case 4:
          searchList(scanner, board);
          break;
        case 5:
          searchCardById(scanner, board);
          break;
        case 6:
          deleteCardById(scanner, board);
          break;
        case 7:
          updateCardById(scanner, board);
          break;
        case 8:
          deleteListByName(scanner, board);
          break;
        case 9:
          updateListByName(scanner, board);
          break;
        case 10:
          exit = true;
          break;
        default:
          System.out.println("Invalid choice");
      }

      board.displayLists();
    }
    scanner.close();
  }

  private static void displayMenu() {
    System.out.println("1. Add List");
    System.out.println("2. Add Card to List");
    System.out.println("3. Display Board");
    System.out.println("4. Search List");
    System.out.println("5. Search Card by ID");
    System.out.println("6. Delete Card by ID");
    System.out.println("7. Update Card by ID");
    System.out.println("8. Delete List by Name");
    System.out.println("9. Update List by Name");
    System.out.println("10. Exit");
    System.out.print("Select option: ");
  }

  private static void addList(Scanner scanner, Board board) {
    System.out.print("Enter list name: ");
    String listName = scanner.nextLine();
    board.addList(listName);
  }

  private static void addCardToList(Scanner scanner, Board board) {
    System.out.print("Enter list name: ");
    String listToAddCard = scanner.nextLine();
    List currentList = board.head;
    while (currentList != null && !currentList.name.equals(listToAddCard)) {
      currentList = currentList.next;
    }
    if (currentList != null) {
      System.out.print("Enter card title: ");
      String cardTitle = scanner.nextLine();
      System.out.print("Enter card description: ");
      String cardDescription = scanner.nextLine();
      System.out.print("Enter due date (YYYY-MM-DD): ");
      String dueDateInput = scanner.nextLine();
      Date dueDate = null; // Initialize dueDate with null
      try {
        dueDate = new SimpleDateFormat("yyyy-MM-dd").parse(dueDateInput);
      } catch (Exception e) {
        System.out.println("Invalid date format. Please enter the date in YYYY-MM-DD format.");
        // Assign a default value to dueDate or handle the error as appropriate
      }
      if (dueDate != null) { // Only proceed if dueDate is initialized
        System.out.print("Enter assignees (comma-separated): ");
        String assigneesInput = scanner.nextLine();
        String[] assigneesArray = assigneesInput.split(",");
        String assignees = String.join(", ", assigneesArray);
        System.out.println("Choose status:");
        System.out.println("1. Pending");
        System.out.println("2. In Progress");
        System.out.println("3. Completed");
        System.out.print("Select option: ");
        int statusChoice = scanner.nextInt();
        Status status;
        switch (statusChoice) {
          case 1:
            status = Status.PENDING;
            break;
          case 2:
            status = Status.IN_PROGRESS;
            break;
          case 3:
            status = Status.COMPLETED;
            break;
          default:
            System.out.println("Invalid choice. Setting status to Pending");
            status = Status.PENDING;
            break;
        }
        currentList.addCard(cardTitle, cardDescription, dueDate, assignees, status, board);
      } else {
        System.out.println("Due date not provided. Card creation aborted.");
      }
    } else {
      System.out.println("List not found.");
    }
  }


  private static void displayBoard(Board board) {
    if (board.head == null) {
      System.out.println("No lists found");
    } else {
      board.displayLists();
    }
  }

  private static void searchList(Scanner scanner, Board board) {
    System.out.print("Enter list name to search: ");
    String listToSearch = scanner.nextLine();
    List foundList = board.findList(listToSearch);
    if (foundList != null) {
      System.out.println("List found: " + foundList.name);
    } else {
      System.out.println("List not found.");
    }
  }

  private static void searchCardById(Scanner scanner, Board board) {
    System.out.print("Enter card ID to search: ");
    int cardIdToSearch = scanner.nextInt();
    List listContainingCard = board.head;
    while (listContainingCard != null) {
      com.example.demo.elements.Card foundCard = listContainingCard.findCard(cardIdToSearch);
      if (foundCard != null) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(foundCard.getDueDate());
        System.out.println("Card found in list: " + listContainingCard.name);
        System.out.println("ID: " + foundCard.getId() + "\nTitle: " + foundCard.getTitle() + "\nDescription: " + foundCard.getDescription() +
            "\nDue Date: " + formattedDate + "\nAssignees: " + foundCard.getAssignees() + "\nStatus: " + foundCard.getStatus());
        break;
      }
      listContainingCard = listContainingCard.next;
    }
    if (listContainingCard == null) {
      System.out.println("Card with ID " + cardIdToSearch + " not found");
    }
  }

  private static void deleteCardById(Scanner scanner, Board board) {
    System.out.print("Enter card ID to delete: ");
    int cardIdToDelete = scanner.nextInt();
    scanner.nextLine(); // consume newline
    List listContainingCardToDelete = board.head;
    while (listContainingCardToDelete != null) {
      com.example.demo.elements.Card cardToDelete = listContainingCardToDelete.findCard(cardIdToDelete);
      if (cardToDelete != null) {
        listContainingCardToDelete.deleteCard(cardIdToDelete);
        System.out.println("Card deleted successfully.");
        break;
      }
      listContainingCardToDelete = listContainingCardToDelete.next;
    }
    if (listContainingCardToDelete == null) {
      System.out.println("Card with ID " + cardIdToDelete + " not found");
    }

  }

  private static void updateCardById(Scanner scanner, Board board) {
    System.out.print("Enter card ID to update: ");
    int cardIdToUpdate = scanner.nextInt();
    scanner.nextLine(); // consume newline
    List listContainingCardToUpdate = board.head;
    while (listContainingCardToUpdate != null) {
      com.example.demo.elements.Card cardToUpdate = listContainingCardToUpdate.findCard(cardIdToUpdate);
      if (cardToUpdate != null) {
        System.out.print("Enter new title: ");
        String newTitle = scanner.nextLine();
        System.out.print("Enter new description: ");
        String newDescription = scanner.nextLine();
        System.out.print("Enter new due date (YYYY-MM-DD): ");
        String newDueDateInput = scanner.nextLine();
        Date newDueDate;
        try {
          newDueDate = new SimpleDateFormat("yyyy-MM-dd").parse(newDueDateInput);
        } catch (Exception e) {
          System.out.println("Invalid date format. Please enter the date in YYYY-MM-DD format");
          break;
        }
        System.out.print("Enter new assignees (comma-separated): ");
        String newAssigneesInput = scanner.nextLine();
        String[] newAssigneesArray = newAssigneesInput.split(",");
        String newAssignees = String.join(", ", newAssigneesArray);
        System.out.println("Choose new status:");
        System.out.println("1. Pending");
        System.out.println("2. In Progress");
        System.out.println("3. Completed");
        System.out.print("Select option: ");
        int newStatusChoice = scanner.nextInt();
        Status newStatus;
        switch (newStatusChoice) {
          case 1:
            newStatus = Status.PENDING;
            break;
          case 2:
            newStatus = Status.IN_PROGRESS;
            break;
          case 3:
            newStatus = Status.COMPLETED;
            break;
          default:
            System.out.println("Invalid choice. Setting status to Pending");
            newStatus = Status.PENDING;
            break;
        }
        listContainingCardToUpdate.updateCard(cardIdToUpdate, newTitle, newDescription, newDueDate, newAssignees, newStatus);
        break;
      }
      listContainingCardToUpdate = listContainingCardToUpdate.next;
    }
    if (listContainingCardToUpdate == null) {
      System.out.println("Card with ID " + cardIdToUpdate + " not found");
    }
  }

  private static void deleteListByName(Scanner scanner, Board board) {
    System.out.print("Enter list name to delete: ");
    String listNameToDelete = scanner.nextLine();
    board.deleteListByName(listNameToDelete);
    System.out.println("List deleted successfully.");
  }



  private static void updateListByName(Scanner scanner, Board board) {
    System.out.print("Enter old list name to update: ");
    String oldListName = scanner.nextLine();
    System.out.print("Enter new list name: ");
    String newListName = scanner.nextLine();
    board.updateListByName(oldListName, newListName);
    System.out.println("List updated successfully.");
  }
}
