package com.soa.university.management.system.models;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
public class Module {
    private Long Id;
    private String name;
    private int creditPoints;
    private int Ds;
    private int Exam;
    private int duration;
}