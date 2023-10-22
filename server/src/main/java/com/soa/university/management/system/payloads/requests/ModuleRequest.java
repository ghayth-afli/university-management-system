package com.soa.university.management.system.payloads.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ModuleRequest {
    private String name;
    private Double nbHours;
    private Double coefficient;
}
