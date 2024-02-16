package com.example.demo.elements;

import java.util.Date;

public class Card {
  private int id; // Unique ID for the card
  private String title;
  private String description;
  private Date dueDate;
  private String assignees;
  private Status status; // Enum for card status
  Card next;
  Card prev;

  public Card(String title, String description, Date dueDate, String assignees, Status status) {
    this.id = -1; // Initialize with a default value, will be set later
    this.title = title;
    this.description = description;
    this.dueDate = dueDate;
    this.assignees = assignees;
    this.status = status;
    this.next = null;
    this.prev = null;
  }

  // Getter and setter methods for the fields

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getDueDate() {
    return dueDate;
  }

  public void setDueDate(Date dueDate) { // Modificar el parámetro
    this.dueDate = dueDate;
  }

  public String getAssignees() {
    return assignees;
  }

  public void setAssignees(String assignees) {
    this.assignees = assignees;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }


}
