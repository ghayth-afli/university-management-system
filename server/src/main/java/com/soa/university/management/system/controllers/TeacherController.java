package com.soa.university.management.system.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.soa.university.management.system.models.Teacher;
import com.soa.university.management.system.payloads.requests.TeacherRequest;
import com.soa.university.management.system.payloads.responses.MessageResponse;
import com.soa.university.management.system.repositories.TeacherRepository;

@RestController
@RequestMapping("/api")
public class TeacherController {
    @Autowired
    TeacherRepository teacherRepository;

    @PostMapping("/teachers")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addTeacher(@RequestBody TeacherRequest teacherRequest){
        Teacher teacher = new Teacher(teacherRequest.getPicture(),teacherRequest.getFirstName(),teacherRequest.getLastName(),teacherRequest.getEmail(),teacherRequest.getSalary());
        teacherRepository.save(teacher);
        return ResponseEntity.ok(new MessageResponse("Teacher successfully added"));
    }

    @GetMapping("/teachers")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllTeacher(){
        return ResponseEntity.ok(teacherRepository.findAll());
    }

    @GetMapping("/teachers/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getTeacher(@PathVariable Long id){
        Optional<Teacher> teacher = teacherRepository.findById(id);
        if (teacher.isEmpty()){
            return ResponseEntity.ok(new MessageResponse("Teacher not found"));
        }
        return ResponseEntity.ok(teacher);
    }

    @PutMapping("/teachers/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> editTeacher(@PathVariable Long id, @RequestBody TeacherRequest teacherRequest){
        Optional<Teacher> teacherResponse = teacherRepository.findById(id);
        if (teacherResponse.isEmpty()){
            return ResponseEntity.ok(new MessageResponse("Teacher not found"));
        }
        Teacher teacher = teacherResponse.get();
        teacher.setPicture(teacherRequest.getPicture());
        teacher.setFirstName(teacherRequest.getFirstName());
        teacher.setLastName(teacherRequest.getLastName());
        teacher.setEmail(teacherRequest.getEmail());
        teacher.setSalary(teacherRequest.getSalary());
        teacherRepository.save(teacher);
        return ResponseEntity.ok(new MessageResponse("Teacher successfully edited"));
    }

    @DeleteMapping("/teachers/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteTeacher(@PathVariable Long id){
        Optional<Teacher> teacherResponse = teacherRepository.findById(id);
        if (teacherResponse.isEmpty()){
            return ResponseEntity.ok(new MessageResponse("Teacher not found"));
        }
        teacherRepository.deleteById(id);
        return ResponseEntity.ok(new MessageResponse("Teacher successfully deleted"));
    }
}