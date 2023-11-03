package com.soa.university.management.system.controllers;

import com.soa.university.management.system.models.Cl;
import com.soa.university.management.system.models.Evaluation;
import com.soa.university.management.system.models.Speciality;
import com.soa.university.management.system.models.Student;
import com.soa.university.management.system.payloads.responses.StatisticResponse;
import com.soa.university.management.system.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class StatisticController {
    @Autowired
    AdministratorRepository administratorRepository;
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    EvaluationRepository evaluationRepository;
    @Autowired
    ClassRepository classRepository;
    @Autowired
    ScheduleRepository scheduleRepository;

    @GetMapping("/satistics")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getStatistics(){
        List<Evaluation> ev = evaluationRepository.findAll();

        Map<Speciality, Integer> absencesByClass = new HashMap<>();

        for (Evaluation evaluation : ev) {
            Student student = evaluation.getStudent();
            Cl cl = student.getCl();
            Speciality classe = cl.getSpeciality();
            Integer absences = evaluation.getAbsences();
            System.out.println(classe);
            if (absences != null) {
                absencesByClass.put(classe, absencesByClass.getOrDefault(classe, 0) + absences);
            }
        }
        //return ResponseEntity.ok(absencesByClass);
        //return ResponseEntity.ok(evaluationRepository.findAll());
        Integer sumAbsences = 0;
        if (evaluationRepository.findAll().size() > 0){
            for (Evaluation evs : evaluationRepository.findAll()){
                if (evs.getAbsences() != null)sumAbsences += evs.getAbsences();
            }
        }
        return ResponseEntity.ok(new StatisticResponse(studentRepository.findAll().size(),teacherRepository.findAll().size(),administratorRepository.findAll().size(),classRepository.findAll().size(),sumAbsences,absencesByClass));

    }
}
