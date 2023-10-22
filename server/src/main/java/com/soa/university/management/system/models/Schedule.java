package com.soa.university.management.system.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;

import java.sql.Time;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "schedules")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Time startTime;
    private Time endTime;
    private String day;
    private String room;
    @OneToMany(mappedBy = "schedule")
    private List<Evaluation> evaluations;
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
    @ManyToOne
    @JoinColumn(name = "module_id")
    private Module module;

    public Schedule(Time startTime, Time endTime, String day, String room, Teacher teacher, Module module) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.day = day;
        this.room = room;
        this.teacher = teacher;
        this.module = module;
    }
}