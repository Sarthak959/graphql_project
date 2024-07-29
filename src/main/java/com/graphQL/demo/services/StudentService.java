package com.graphQL.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.graphQL.demo.dtos.StudentDto;
import com.graphQL.demo.entities.Student;
import com.graphQL.demo.exceptions.NoDataFoundException;
import com.graphQL.demo.repositories.StudentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    
    private Student student;

    public Student getById(String id) {
        //Fetching users data from repository
        return studentRepository.findById(id).orElseThrow(() -> new NoDataFoundException(400,String.format("We were unable to find a student with the provided id: %s", id)));
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student createStudent(StudentDto dto) {
        Student student = Student.builder()
                .name(dto.getName())
                .sirname(dto.getSirname())
                .percentage(dto.getPercentage())
                .build();
        return studentRepository.save(student);
    }

    public Student updateStudent(String id, StudentDto dto) throws NoDataFoundException{
        Student student = studentRepository.findById(id).orElseThrow(()-> new NoDataFoundException(404, "We were unable to find a student with the provided id: "));
        student.setName(dto.getName());
        student.setPercentage(dto.getPercentage());
        student.setSirname(dto.getSirname());
        return studentRepository.save(student);
    }


    public String deleteStudent(String id){
        studentRepository.deleteById(id);
        return "Success!!!";
    }

}
