package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AgileGridApplication2 implements CommandLineRunner {
  @Autowired
  private AgileGridService agileGridService;

  public static void main(String[] args) {
    SpringApplication.run(AgileGridApplication2.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    agileGridService.run();
  }
}