package com.soa.university.management.system.payloads.responses;

import com.soa.university.management.system.models.Speciality;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class StatisticResponse {
    private Integer nbOfStudents;
    private Integer nbOfTeachers;
    private Integer nbOfAdministrators;
    private Integer nbOfClasses;
    private Integer nbOfAbsences;
    private Map<Speciality, Integer> nbAbsencesByClass = new HashMap<>();
}