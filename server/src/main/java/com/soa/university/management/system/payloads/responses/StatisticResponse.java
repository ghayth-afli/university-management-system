package com.soa.university.management.system.payloads.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StatisticResponse {
    private Integer nbOfStudents;
    private Integer nbOfTeachers;
    private Integer nbOfAdministrators;
    private Integer nbOfClasses;
    private Integer nbOfAbsences;
}