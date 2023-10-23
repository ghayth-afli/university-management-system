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
@Table(name = "modules")
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double nbHours;
    private Double coefficient;
    @OneToMany(mappedBy = "module")
    @JsonIgnore
    private List<Schedule> schedules;

    public Module(String name, Double nbHours, Double coefficient) {
        this.name = name;
        this.nbHours = nbHours;
        this.coefficient = coefficient;
    }
}