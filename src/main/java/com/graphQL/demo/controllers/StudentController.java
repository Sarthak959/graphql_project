package com.graphQL.demo.controllers;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.graphQL.demo.dtos.StudentDto;
import com.graphQL.demo.entities.Student;
import com.graphQL.demo.exceptions.NoDataFoundException;
import com.graphQL.demo.services.StudentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class StudentController {
    private final StudentService service;

    @QueryMapping(name = "getStudent")
    public Student getById(@Argument String id){
        return service.getById(id);
    }

    @QueryMapping
    public java.util.List<Student> getAllStudents() {
        log.debug("Data got :{}", service.getAllStudents());
        return service.getAllStudents();
    }

    @MutationMapping
    public Student createStudent(@Argument StudentDto dto) {
        log.info("Dto recieved : {}", dto);
        return service.createStudent(dto);
    }
    // type Mutation{
    // createStudent(dto:StudentDto):Student
    // }
    // Exact variable and data type should get match to execute mutation
    // If I put student instead of dto it wont get the value for dto

    @MutationMapping
    public Student updateStudent(@Argument String id, @Argument StudentDto dto){
        return service.updateStudent(id, dto);
    }

    @MutationMapping
    public String deleteStudent(@Argument String id) {
        return service.deleteStudent(id);
    }


}
