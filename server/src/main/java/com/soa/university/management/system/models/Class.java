package com.soa.university.management.system.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Class {
    private Long Id;
    private String name;
    private int nbrStudents;

}