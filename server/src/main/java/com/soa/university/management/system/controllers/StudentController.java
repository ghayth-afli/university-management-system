package com.soa.university.management.system.controllers;

import com.soa.university.management.system.models.*;
import com.soa.university.management.system.models.Module;
import com.soa.university.management.system.payloads.requests.EvaluationRequest;
import com.soa.university.management.system.payloads.requests.StudentRequest;
import com.soa.university.management.system.payloads.responses.MessageResponse;
import com.soa.university.management.system.repositories.*;
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
    @Autowired
    EvaluationRepository evaluationRepository;
    @Autowired
    ModuleRepository moduleRepository;
    @Autowired
    ScheduleRepository scheduleRepository;
    @PostMapping("/students")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addStudent(@RequestBody StudentRequest studentRequest){
        Student student = new Student(studentRequest.getFirstName(),studentRequest.getLastName(),studentRequest.getAge(),studentRequest.getEmail(),studentRequest.getPhoneNumber(),studentRequest.getAddress());
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

    @PutMapping("/students/{id}/module/{moduleId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> evaluateStudent(@RequestBody EvaluationRequest evaluationRequest, @PathVariable Long id, @PathVariable Long moduleId){
        Optional<Student> studentResponse = studentRepository.findById(id);
        if (studentResponse.isEmpty()){
            return ResponseEntity.ok(new MessageResponse("Student not found"));
        }
        Optional<Module> moduleResponse = moduleRepository.findById(id);
        if (moduleResponse.isEmpty()){
            return ResponseEntity.ok(new MessageResponse("Module not found"));
        }
        Schedule schedule = scheduleRepository.findByModuleId(moduleId);
        if (schedule == null){
            return ResponseEntity.ok(new MessageResponse("Schedule not found"));
        }
        Evaluation evaluation = evaluationRepository.findByStudentIdAndScheduleId(id,schedule.getId());
        evaluation.setAbsences(evaluationRequest.getAbsences());
        evaluation.setDs(evaluationRequest.getDs());
        evaluation.setTp(evaluationRequest.getTp());
        evaluation.setExam(evaluationRequest.getExam());
        evaluationRepository.save(evaluation);
        return ResponseEntity.ok(new MessageResponse("Evaluation successfully updated"));
    }

}
