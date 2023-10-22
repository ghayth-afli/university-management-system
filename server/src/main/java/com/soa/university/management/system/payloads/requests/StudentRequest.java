package com.soa.university.management.system.payloads.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequest {
    private String firstName;
    private String lastName;
    private Integer age;
    private String email;
    private String phoneNumber;
    private String address;
    private String city;
    private String country;
    private String picture;
}
