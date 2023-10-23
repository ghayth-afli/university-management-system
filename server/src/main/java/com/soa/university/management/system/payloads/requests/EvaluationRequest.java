package com.soa.university.management.system.payloads.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EvaluationRequest {
    private Integer absences;
    private Double tp;
    private Double ds;
    private Double exam;
    private Double average;
}
