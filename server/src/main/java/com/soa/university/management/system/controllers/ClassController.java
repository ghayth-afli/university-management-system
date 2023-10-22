package com.soa.university.management.system.controllers;

import com.soa.university.management.system.models.Cl;
import com.soa.university.management.system.models.Student;
import com.soa.university.management.system.payloads.requests.ClassRequest;
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
public class ClassController {
    @Autowired
    ClassRepository classRepository;

    @Autowired
    StudentRepository studentRepository;

    @PostMapping("/classes")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addClass(@RequestBody ClassRequest classRequest){
        Cl classe = new Cl(classRequest.getSpeciality(),classRequest.getGrade(),classRequest.getGrp());
        classRepository.save(classe);
        return ResponseEntity.ok(new MessageResponse("Administrator successfully added"));
    }

    @GetMapping("/classes")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllClass(){
        return ResponseEntity.ok(classRepository.findAll());
    }

    @GetMapping("/classes/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getClass(@PathVariable Long id){
        Optional<Cl> classe = classRepository.findById(id);
        if (classe.isEmpty()){
            return ResponseEntity.ok(new MessageResponse("Class not found"));
        }
        return ResponseEntity.ok(classe);
    }

    @PutMapping("/classes/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> editClass(@PathVariable Long id, @RequestBody ClassRequest classRequest){
        Optional<Cl> classeResponse = classRepository.findById(id);
        if (classeResponse.isEmpty()){
            return ResponseEntity.ok(new MessageResponse("Class not found"));
        }
        Cl classe = classeResponse.get();
        classe.setSpeciality(classRequest.getSpeciality());
        classe.setGrade(classRequest.getGrade());
        classe.setGrp(classRequest.getGrp());
        classRepository.save(classe);
        return ResponseEntity.ok(new MessageResponse("Class successfully edited"));
    }

    @DeleteMapping("/classes/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteClass(@PathVariable Long id){
        Optional<Cl> classeResponse = classRepository.findById(id);
        if (classeResponse.isEmpty()){
            return ResponseEntity.ok(new MessageResponse("Class not found"));
        }
        classRepository.deleteById(id);
        return ResponseEntity.ok(new MessageResponse("Class successfully deleted"));
    }

    //add student to class
    @PostMapping("/classes/{idClass}/students/{idStudent}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addStudentToClass(@PathVariable Long idClass, @PathVariable Long idStudent){
        Optional<Cl> classeResponse = classRepository.findById(idClass);
        if (classeResponse.isEmpty()){
            return ResponseEntity.ok(new MessageResponse("Class not found"));
        }
        Optional<Student> studentResponse = studentRepository.findById(idStudent);
        if (studentResponse.isEmpty()){
            return ResponseEntity.ok(new MessageResponse("Student not found"));
        }
        Cl classe = classeResponse.get();
        Student student = studentResponse.get();
        classe.getStudents().add(student);
        classRepository.save(classe);
        return ResponseEntity.ok(new MessageResponse("Student successfully added to class"));
    }

    //remove student from class
    @DeleteMapping("/classes/{idClass}/students/{idStudent}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> removeStudentFromClass(@PathVariable Long idClass, @PathVariable Long idStudent){
        Optional<Cl> classeResponse = classRepository.findById(idClass);
        if (classeResponse.isEmpty()){
            return ResponseEntity.ok(new MessageResponse("Class not found"));
        }
        Optional<Student> studentResponse = studentRepository.findById(idStudent);
        if (studentResponse.isEmpty()){
            return ResponseEntity.ok(new MessageResponse("Student not found"));
        }
        Cl classe = classeResponse.get();
        Student student = studentResponse.get();
        classe.getStudents().remove(student);
        classRepository.save(classe);
        return ResponseEntity.ok(new MessageResponse("Student successfully removed from class"));
    }

    //get all students from class
    @GetMapping("/classes/{idClass}/students")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllStudentsFromClass(@PathVariable Long idClass){
        Optional<Cl> classeResponse = classRepository.findById(idClass);
        if (classeResponse.isEmpty()){
            return ResponseEntity.ok(new MessageResponse("Class not found"));
        }
        Cl classe = classeResponse.get();
        return ResponseEntity.ok(classe.getStudents());
    }
}
