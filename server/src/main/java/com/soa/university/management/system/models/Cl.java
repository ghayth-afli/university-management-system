package com.soa.university.management.system.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "cls")
public class Cl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Speciality speciality;
    private String grade;
    private Character grp;
    @OneToMany(mappedBy = "cl")
    @JsonIgnore
    private List<Student> students;

    public Cl(Speciality speciality, String grade, Character grp) {
        this.speciality = speciality;
        this.grade = grade;
        this.grp = grp;
    }
}
