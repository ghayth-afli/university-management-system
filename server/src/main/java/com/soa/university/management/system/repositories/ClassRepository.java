package com.soa.university.management.system.repositories;

import com.soa.university.management.system.models.Cl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository extends JpaRepository<Cl,Long> {
}
