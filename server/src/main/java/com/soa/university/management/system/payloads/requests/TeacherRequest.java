package com.soa.university.management.system.payloads.requests;

import java.util.List;

import com.soa.university.management.system.models.Schedule;

import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TeacherRequest {
    private String picture;
    private String firstName;
    private String lastName;
    private String email;
    private Double salary;
      @OneToMany(mappedBy = "teacher")
    private List<Schedule> schedules;
}