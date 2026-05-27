// src/main/java/com/example/studentapi/repository/StudentRepository.java
package com.example.studentapi.repository;

import com.example.studentapi.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // JpaRepository gives you save, findAll, findById, delete — for free!
}