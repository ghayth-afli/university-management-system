package com.soa.university.management.system.repositories;

import com.soa.university.management.system.models.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {
    Evaluation findByStudentIdAndScheduleId(Long id, Long id1);
}
