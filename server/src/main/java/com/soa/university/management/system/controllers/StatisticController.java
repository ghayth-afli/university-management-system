package com.soa.university.management.system.controllers;

import com.soa.university.management.system.models.Evaluation;
import com.soa.university.management.system.payloads.responses.StatisticResponse;
import com.soa.university.management.system.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("/satistics")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getStatistics(){
        Integer sumAbsences = 0;
        if (evaluationRepository.findAll().size() > 0){
            for (Evaluation ev : evaluationRepository.findAll()){
                if (ev.getAbsences() != null)sumAbsences += ev.getAbsences();
            }
        }
        return ResponseEntity.ok(new StatisticResponse(studentRepository.findAll().size(),teacherRepository.findAll().size(),administratorRepository.findAll().size(),classRepository.findAll().size(),sumAbsences));
    }
}
