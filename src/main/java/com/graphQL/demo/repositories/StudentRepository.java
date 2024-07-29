package com.graphQL.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.graphQL.demo.entities.Student;

public interface StudentRepository extends JpaRepository<Student, String> {

}
