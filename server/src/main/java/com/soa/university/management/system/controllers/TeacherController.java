package com.soa.university.management.system.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity <?>addTeacher( @RequestBody TeacherRequest teacherRequest) {
        Teacher teacher = new Teacher(teacherRequest.getFirstName(),teacherRequest.getLastName(),teacherRequest.getEmail()
        ,teacherRequest.getPicture(),teacherRequest.getSalary());
        teacherRepository.save(teacher);
        return ResponseEntity.ok(teacherRepository.findAll());       
}
 @PostMapping("/teachers")
 @PreAuthorize("hasRole('ADMIN')")
 public ResponseEntity<?> getAllTeachers(){
    return ResponseEntity.ok(teacherRepository.findAll());}

@PostMapping("/teachers")
@PreAuthorize("hasRole('ADMIN')")
public ResponseEntity<?> getTeacher(@PathVariable Long id){
    Optional<Teacher> teacher = teacherRepository.findById(id);
    if(!teacher.isEmpty()){
    return ResponseEntity.ok(new MessageResponse("Teacher not found"));}
    return ResponseEntity.ok(teacher);
}
 @PostMapping("/teachers")
 @PreAuthorize("hasRole('ADMIN')")
 public ResponseEntity<?> updateTeacher(@PathVariable Long id,@RequestBody TeacherRequest teacherRequest){
    Optional<Teacher> teacherResponse=teacherRepository.findById(id);
    if (!teacherResponse.isEmpty()) {
        return ResponseEntity.badRequest().body(new MessageResponse("Teacher not found"));
    }
    Teacher teacher = teacherResponse.get();
    teacher.setFirstName(teacherRequest.getFirstName());
    teacher.setLastName(teacherRequest.getLastName());
    teacher.setEmail(teacherRequest.getEmail());
    teacher.setPicture(teacherRequest.getPicture());
    teacher.setSalary(teacherRequest.getSalary());
    teacherRepository.save(teacher);
    return ResponseEntity.ok(new MessageResponse("Teacher successfully updated"));
 }
 @DeleteMapping("/teachers/{id}")
 @PreAuthorize("hasRole('ADMIN')")
 public ResponseEntity<?> deleteTeacher(@PathVariable Long id){
    Optional<Teacher> teacherResponse=teacherRepository.findById(id);
    if (teacherResponse.isEmpty()) {
        return ResponseEntity.badRequest().body(new MessageResponse("Teacher not found"));
 }
 teacherRepository.deleteById(id);
 return ResponseEntity.ok(new MessageResponse("Teacher deleted successfully"));
}}