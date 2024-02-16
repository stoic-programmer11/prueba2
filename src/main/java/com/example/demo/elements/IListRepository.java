package com.example.demo.elements;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IListRepository extends JpaRepository<List, Long> {
}