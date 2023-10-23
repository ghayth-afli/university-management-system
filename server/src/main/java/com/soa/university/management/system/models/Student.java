package com.soa.university.management.system.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String email;
    private Long phoneNumber;
    private String address;

    @ManyToOne
    @JoinColumn(name = "cl_id")
    private Cl cl;

    @OneToMany(mappedBy = "student")
    @JsonIgnore
    private List<Evaluation> evaluations;

    public Student(String firstName, String lastName, Integer age, String email, Long phoneNumber, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
}