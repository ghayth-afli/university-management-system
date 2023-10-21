package com.soa.university.management.system.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "modules")
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
<<<<<<< HEAD
    private Double nbHours;
    private Double coefficient;
    @OneToMany(mappedBy = "module")
    private List<Schedule> schedules;
=======
    private int creditPoints;
    private int Ds;
    private int Exam;
    private int duration;
>>>>>>> 1340bbb2ac9328b1a50946b1b325d0b74d2f903a
}