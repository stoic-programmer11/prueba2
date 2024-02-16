package com.example.demo.elements;

public class Board {
  public List head;
  private int cardIdCounter; // Counter for generating unique card IDs

  public Board() {
    head = null;
    cardIdCounter = 1; // Initialize the counter
  }

  public void addList(String name) {
    List newList = new List(name);
    if (head == null) {
      head = newList;
    } else {
      List current = head;
      while (current.next != null) {
        current = current.next;
      }
      current.next = newList;
    }
  }

  public void displayLists() {
    List current = head;
    while (current != null) {
      System.out.println("================================================");
      System.out.println(current.name + ":");
      current.displayCards();
      System.out.println("================================================");
      current = current.next;
    }
  }

  // Getter for cardIdCounter
  public int getCardIdCounter() {
    return cardIdCounter;
  }

  // Increment the cardIdCounter
  public void incrementCardIdCounter() {
    cardIdCounter++;
  }

  // En la clase Board

  public List findList(String listName) {
    List current = head;
    while (current != null) {
      if (current.name.equals(listName)) {
        return current;
      }
      current = current.next;
    }
    return null; // La lista no se encontró
  }

  public void deleteListByName(String listName) {
    List current = head;
    List prev = null;

    while (current != null) {
      if (current.name.equals(listName)) {
        if (prev == null) {
          head = current.next;
        } else {
          prev.next = current.next;
        }
        return; // List deleted
      }
      prev = current;
      current = current.next;
    }
  }

  public void updateListByName(String oldListName, String newListName) {
    List listToUpdate = findList(oldListName);
    if (listToUpdate != null) {
      listToUpdate.name = newListName;
    } else {
      System.out.println("List not found.");
    }
  }


}
