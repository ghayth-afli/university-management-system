package com.soa.university.management.system.models;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
public class Module {
    private Long Id;
    private String name;
    private int creditPoints;
    private int coefDs;
    private int coefExam;
    private int duration;
}