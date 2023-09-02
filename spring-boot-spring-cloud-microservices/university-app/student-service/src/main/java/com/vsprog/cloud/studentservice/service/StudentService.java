package com.vsprog.cloud.studentservice.service;

import com.vsprog.cloud.studentservice.entity.Student;
import com.vsprog.cloud.studentservice.feignclients.AddressFeignClient;
import com.vsprog.cloud.studentservice.repository.StudentRepository;
import com.vsprog.cloud.studentservice.request.CreateStudentRequest;
import com.vsprog.cloud.studentservice.response.AddressResponse;
import com.vsprog.cloud.studentservice.response.StudentResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private WebClient webClient;

    @Autowired
    private AddressFeignClient addressFeignClient;

    @Autowired
    private CommonService commonService;

    public StudentResponse createStudent(CreateStudentRequest createStudentRequest) {
        Student student = new Student();
        student.setFirstName(createStudentRequest.getFirstName());
        student.setLastName(createStudentRequest.getLastName());
        student.setEmail(createStudentRequest.getEmail());
        student.setAddressId(createStudentRequest.getAddressId());
        student = studentRepository.save(student);

        StudentResponse studentResponse = new StudentResponse(student);
        // studentResponse.setAddressResponse(getAddressById(student.getAddressId()));
        studentResponse.setAddressResponse(commonService.getAddressById(student.getAddressId()));

        return studentResponse;
    }

    public StudentResponse getById(long id) {
        Student student = studentRepository.findById(id).get();
        StudentResponse studentResponse = new StudentResponse(student);
        // studentResponse.setAddressResponse(getAddressById(student.getAddressId())); // web client
        // studentResponse.setAddressResponse(addressFeignClient.getById(student.getAddressId())); feign rest client
        studentResponse.setAddressResponse(commonService.getAddressById(student.getAddressId()));
        return studentResponse;
    }

}
