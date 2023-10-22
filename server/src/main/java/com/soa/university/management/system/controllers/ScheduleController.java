package com.soa.university.management.system.controllers;


import com.soa.university.management.system.payloads.requests.ScheduleRequest;
import com.soa.university.management.system.payloads.responses.MessageResponse;
import com.soa.university.management.system.repositories.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ScheduleController {

    @Autowired
    ScheduleRepository scheduleRepository;

    @PostMapping("/schedules")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addSchedule(@RequestBody ScheduleRequest scheduleRequest){
        //Schedule schedule = new Schedule(scheduleRequest.getStartTime(),scheduleRequest.getEndTime(),scheduleRequest.getDay(),scheduleRequest.getRoom());
        //scheduleRepository.save(schedule);
        return ResponseEntity.ok(new MessageResponse("Schedule successfully added"));
    }
}
