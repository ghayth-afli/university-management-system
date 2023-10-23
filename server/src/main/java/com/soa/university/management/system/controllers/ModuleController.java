package com.soa.university.management.system.controllers;

import com.soa.university.management.system.models.Module;
import com.soa.university.management.system.payloads.requests.ModuleRequest;
import com.soa.university.management.system.payloads.responses.MessageResponse;
import com.soa.university.management.system.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ModuleController {

    @Autowired
    ModuleRepository moduleRepository;

    /*@PostMapping("/modules")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addModule(@RequestBody ModuleRequest moduleRequest){
        Module module = new Module(moduleRequest.getName(),moduleRequest.getNbHours(),moduleRequest.getCoefficient());
        moduleRepository.save(module);
        return ResponseEntity.ok(new MessageResponse("Module successfully added"));
    }*/

    @GetMapping("/modules")
    @PreAuthorize("hasRole('ADMIN')")
    private ResponseEntity<?> getAllModules(){
        return ResponseEntity.ok(moduleRepository.findAll());
    }

    /*@GetMapping("/modules/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    private ResponseEntity<?> getModule(@PathVariable Long id){
        Optional<Module> module = moduleRepository.findById(id);
        if (module.isEmpty()){
            return ResponseEntity.ok(new MessageResponse("Module not found"));
        }
        return ResponseEntity.ok(module);
    }

    @PutMapping("/modules/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    private ResponseEntity<?> editModule(@PathVariable Long id, @RequestBody ModuleRequest moduleRequest){
        Optional<Module> moduleResponse = moduleRepository.findById(id);
        if (moduleResponse.isEmpty()){
            return ResponseEntity.ok(new MessageResponse("Module not found"));
        }
        Module module = moduleResponse.get();
        module.setName(moduleRequest.getName());
        module.setNbHours(moduleRequest.getNbHours());
        module.setCoefficient(moduleRequest.getCoefficient());
        moduleRepository.save(module);
        return ResponseEntity.ok(new MessageResponse("Module successfully edited"));
    }

    @DeleteMapping("/modules/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    private ResponseEntity<?> deleteModule(@PathVariable Long id){
        Optional<Module> moduleResponse = moduleRepository.findById(id);
        if (moduleResponse.isEmpty()){
            return ResponseEntity.ok(new MessageResponse("Module not found"));
        }
        moduleRepository.deleteById(id);
        return ResponseEntity.ok(new MessageResponse("Module successfully deleted"));
    }*/
}
