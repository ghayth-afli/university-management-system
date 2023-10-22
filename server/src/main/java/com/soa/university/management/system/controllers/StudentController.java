package com.soa.university.management.system.controllers;

import com.soa.university.management.system.models.Cl;
import com.soa.university.management.system.models.Student;
import com.soa.university.management.system.payloads.requests.StudentRequest;
import com.soa.university.management.system.payloads.responses.MessageResponse;
import com.soa.university.management.system.repositories.ClassRepository;
import com.soa.university.management.system.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;
    @Autowired
    ClassRepository classRepository;

    @PostMapping("/students")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addStudent(@RequestBody StudentRequest studentRequest){
        Student student = new Student(studentRequest.getFirstName(),studentRequest.getLastName(),studentRequest.getAge(),studentRequest.getEmail(),studentRequest.getPhoneNumber(),studentRequest.getAddress(),studentRequest.getCity(),studentRequest.getCountry(),studentRequest.getPicture());
        studentRepository.save(student);
        return ResponseEntity.ok(new MessageResponse("Student successfully added"));
    }

    @GetMapping("/students")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllStudent(){
        return ResponseEntity.ok(studentRepository.findAll());
    }

    @GetMapping("/students/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getStudent(@PathVariable Long id){
        Optional<Student> student = studentRepository.findById(id);
        if (student.isEmpty()){
            return ResponseEntity.ok(new MessageResponse("Student not found"));
        }
        return ResponseEntity.ok(student);
    }

    @PutMapping("/students/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> editStudent(@PathVariable Long id, @RequestBody StudentRequest studentRequest){
        Optional<Student> studentResponse = studentRepository.findById(id);
        if (studentResponse.isEmpty()){
            return ResponseEntity.ok(new MessageResponse("Student not found"));
        }
        Student student = studentResponse.get();
        student.setFirstName(studentRequest.getFirstName());
        student.setLastName(studentRequest.getLastName());
        student.setAge(studentRequest.getAge());
        student.setEmail(studentRequest.getEmail());
        student.setPhoneNumber(studentRequest.getPhoneNumber());
        student.setAddress(studentRequest.getAddress());
        student.setCity(studentRequest.getCity());
        student.setCountry(studentRequest.getCountry());
        student.setPicture(studentRequest.getPicture());
        studentRepository.save(student);
        return ResponseEntity.ok(new MessageResponse("Student successfully edited"));
    }

    @DeleteMapping("/students/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id){
        Optional<Student> studentResponse = studentRepository.findById(id);
        if (studentResponse.isEmpty()){
            return ResponseEntity.ok(new MessageResponse("Student not found"));
        }
        studentRepository.deleteById(id);
        return ResponseEntity.ok(new MessageResponse("Student successfully deleted"));
    }

    //assign student to class
    @PutMapping("/students/{id}/class/{classId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> assignStudentToClass(@PathVariable Long id, @PathVariable Long classId){
        Optional<Student> studentResponse = studentRepository.findById(id);
        if (studentResponse.isEmpty()){
            return ResponseEntity.ok(new MessageResponse("Student not found"));
        }
        Optional<Cl> classResponse = classRepository.findById(classId);
        if (classResponse.isEmpty()){
            return ResponseEntity.ok(new MessageResponse("Class not found"));
        }
        Student student = studentResponse.get();
        Cl classe = classResponse.get();
        student.setCl(classe);
        studentRepository.save(student);
        return ResponseEntity.ok(new MessageResponse("Student successfully assigned to class"));
    }

}
