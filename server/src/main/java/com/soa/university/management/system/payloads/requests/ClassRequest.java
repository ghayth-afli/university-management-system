package com.soa.university.management.system.payloads.requests;

import com.soa.university.management.system.models.Speciality;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClassRequest {
    private Speciality speciality;
    private String grade;
    private Character grp;
}
