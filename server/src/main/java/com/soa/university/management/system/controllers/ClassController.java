package com.soa.university.management.system.controllers;

import com.soa.university.management.system.models.*;
import com.soa.university.management.system.models.Module;
import com.soa.university.management.system.payloads.requests.ClassRequest;
import com.soa.university.management.system.payloads.requests.ScheduleRequest;
import com.soa.university.management.system.payloads.responses.MessageResponse;
import com.soa.university.management.system.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class ClassController {
    @Autowired
    ClassRepository classRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    ModuleRepository moduleRepository;

    @Autowired
    EvaluationRepository evaluationRepository;

    @Autowired
    ScheduleRepository scheduleRepository;

    @PostMapping("/classes")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addClass(@RequestBody ClassRequest classRequest){
        Cl classe = new Cl(classRequest.getSpeciality(),classRequest.getGrade(),classRequest.getGrp());
        classRepository.save(classe);
        return ResponseEntity.ok(new MessageResponse("class successfully added"));
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
        /*classe.getStudents().add(student);
        classRepository.save(classe);*/
        student.setCl(classe);
        studentRepository.save(student);
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

    //add schedule to class
    @PostMapping("/classes/{id}/schedules")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addScheduleToClass(@RequestBody ScheduleRequest scheduleRequest, @PathVariable Long id){
        Optional<Cl> classeResponse = classRepository.findById(id);
        if (classeResponse.isEmpty()){
            return ResponseEntity.ok(new MessageResponse("Class not found"));
        }
        Cl classe = classeResponse.get();
        Teacher teacher = teacherRepository.findById(scheduleRequest.getTeacherId()).get();
        if (teacher == null){
            return ResponseEntity.ok(new MessageResponse("Teacher not found"));
        }
        Module module = moduleRepository.findById(scheduleRequest.getModuleId()).get();
        if (module == null){
            return ResponseEntity.ok(new MessageResponse("Module not found"));
        }
        Schedule schedule = new Schedule(scheduleRequest.getStartTime(), scheduleRequest.getEndTime(), scheduleRequest.getDay(), scheduleRequest.getRoom(),teacher,module);
        scheduleRepository.save(schedule);
        for (Student student: classe.getStudents()){
            Evaluation evaluation = new Evaluation(student,schedule);
            evaluationRepository.save(evaluation);
        }
        return ResponseEntity.ok(new MessageResponse("Schedule successfully added to class"));
    }
}
