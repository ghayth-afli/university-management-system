package com.soa.university.management.system.models;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
public class Student {
    private Long Id;
    private Integer age;
    private String img;
    private Long cin;
    private Long phoneNumber;
    private String city;

}