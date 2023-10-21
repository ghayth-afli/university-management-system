package com.soa.university.management.system.models;
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
<<<<<<< HEAD
    private String email;
    private String phoneNumber;
    private String address;
    private String city;
    private String country;
    private String picture;
    @ManyToOne
    @JoinColumn(name = "cl_id")
    private Cl cl;
    @OneToMany(mappedBy = "student")
    private List<Evaluation> evaluations;
=======
    private String img;
    private Long cin;
    private Long phoneNumber;
    private String city;

>>>>>>> 1340bbb2ac9328b1a50946b1b325d0b74d2f903a
}