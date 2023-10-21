package com.soa.university.management.system.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soa.university.management.system.models.Teacher;
import com.soa.university.management.system.payloads.requests.TeacherRequest;
import com.soa.university.management.system.repositories.TeacherRepository;

@RestController
@RequestMapping("/api")
public class TeacherController {
   
    @Autowired
    TeacherRepository teacherRepository;
    @PostMapping("/teachers")
    @PreAuthorize("hasRole('Teacher')")
    public ResponseEntity <?>addTeacher( @RequestBody TeacherRequest teacherRequest) {
        Teacher teacher = new Teacher(teacherRequest.getFirstName(),teacherRequest.getLastName(),teacherRequest.getEmail()
        ,teacherRequest.getPicture(),teacherRequest.getSalary(), teacherRequest.getSchedules());
        teacherRepository.save(teacher);
        return ResponseEntity.ok(teacherRepository.findAll());       
}
 @PostMapping("/teachers")
 @PreAuthorize("hasRole('Teacher')")
 public ResponseEntity<?> getAllTeachers(){
    return ResponseEntity.ok(teacherRepository.findAll());}
    
}