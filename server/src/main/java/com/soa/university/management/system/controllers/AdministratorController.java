package com.soa.university.management.system.controllers;

import com.soa.university.management.system.models.Administrator;
import com.soa.university.management.system.payloads.requests.AdministratorRequest;
import com.soa.university.management.system.payloads.responses.MessageResponse;
import com.soa.university.management.system.repositories.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AdministratorController {
    @Autowired
    AdministratorRepository administratorRepository;
    @PostMapping("/administrators")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addAdministrator(@RequestBody AdministratorRequest administratorRequest){
        Administrator administrator = new Administrator(administratorRequest.getFirstName(),administratorRequest.getLastName(),administratorRequest.getEmail(),administratorRequest.getPhoneNumber(),administratorRequest.getAddress(),administratorRequest.getPosition());
        administratorRepository.save(administrator);
        return ResponseEntity.ok(new MessageResponse("Administrator successfully added"));
    }
    @GetMapping("/administrators")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllAdministrator(){
        return ResponseEntity.ok(administratorRepository.findAll());
    }

    @GetMapping("/administrators/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAdministrator(@PathVariable Long id){
        Optional<Administrator> administrator = administratorRepository.findById(id);
        if (administrator.isEmpty()){
            return ResponseEntity.ok(new MessageResponse("Administrator not found"));
        }
        return ResponseEntity.ok(administrator);
    }

    @PutMapping("/administrators/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> editAdministrator(@PathVariable Long id, @RequestBody AdministratorRequest administratorRequest){
        Optional<Administrator> administratorResponse = administratorRepository.findById(id);
        if (administratorResponse.isEmpty()){
            return ResponseEntity.ok(new MessageResponse("Administrator not found"));
        }
        Administrator administrator = administratorResponse.get();
        administrator.setFirstName(administratorRequest.getFirstName());
        administrator.setLastName(administratorRequest.getLastName());
        administrator.setEmail(administratorRequest.getEmail());
        administrator.setPhoneNumber(administratorRequest.getPhoneNumber());
        administrator.setAddress(administratorRequest.getAddress());
        administrator.setPosition(administratorRequest.getPosition());
        administratorRepository.save(administrator);
        return ResponseEntity.ok(new MessageResponse("Administrator successfully edited"));
    }

    @DeleteMapping("/administrators/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteAdministrator(@PathVariable Long id){
        Optional<Administrator> administrator = administratorRepository.findById(id);
        if (administrator.isEmpty()){
            return ResponseEntity.ok(new MessageResponse("Administrator not found"));
        }
        administratorRepository.deleteById(id);
        return ResponseEntity.ok(new MessageResponse("Administrator successfully deleted"));
    }
}
