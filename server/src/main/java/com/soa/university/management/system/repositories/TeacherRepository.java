package com.soa.university.management.system.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soa.university.management.system.models.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher,Long> {
    
}