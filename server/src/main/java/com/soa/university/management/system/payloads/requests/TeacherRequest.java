package com.soa.university.management.system.payloads.requests;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TeacherRequest {
    private Long phoneNumber;
    private String firstName;
    private String lastName;
    private String email;
    private Double salary;
}