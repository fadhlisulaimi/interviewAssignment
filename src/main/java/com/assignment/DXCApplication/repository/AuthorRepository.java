package com.assignment.DXCApplication.repository;

import com.assignment.DXCApplication.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
}