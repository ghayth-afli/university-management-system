package com.soa.university.management.system.repositories;

import com.soa.university.management.system.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
}
