package com.soa.university.management.system.repositories;

import com.soa.university.management.system.models.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
    Schedule findByModuleId(Long moduleId);
}
