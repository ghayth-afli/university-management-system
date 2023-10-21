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
    private Double nbHours;
    private Double coefficient;
    @OneToMany(mappedBy = "module")
    private List<Schedule> schedules;
}