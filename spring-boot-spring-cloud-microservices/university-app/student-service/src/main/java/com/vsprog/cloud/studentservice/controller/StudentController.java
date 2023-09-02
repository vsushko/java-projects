package com.vsprog.cloud.studentservice.controller;

import com.vsprog.cloud.studentservice.request.CreateStudentRequest;
import com.vsprog.cloud.studentservice.response.StudentResponse;
import com.vsprog.cloud.studentservice.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/create")
    public StudentResponse createStudent(@RequestBody CreateStudentRequest createStudentRequest) {
        return studentService.createStudent(createStudentRequest);
    }

    @GetMapping("/getById/{id}")
    public StudentResponse getById(@PathVariable long id) {
        return studentService.getById(id);
    }
}
