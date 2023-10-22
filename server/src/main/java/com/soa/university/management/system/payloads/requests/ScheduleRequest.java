package com.soa.university.management.system.payloads.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleRequest {
    private Time startTime;
    private Time endTime;
    private String day;
    private String room;
    private Long teacherId;
    private Long moduleId;
}
