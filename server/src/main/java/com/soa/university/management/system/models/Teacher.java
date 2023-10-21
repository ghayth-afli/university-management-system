package com.soa.university.management.system.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String picture;
    private String firstName;
    private String lastName;
    private String email;
    private Double salary;
    @OneToMany(mappedBy = "teacher")
    private List<Schedule> schedules;

    public Teacher( String picture, String firstName, String lastName, String email, Double salary, List<Schedule> schedules) {
        this.picture = picture;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.salary = salary;
        this.schedules = schedules;
    }
    
}