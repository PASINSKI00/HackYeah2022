package com.pasinski.sl.backend.mission;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.Date;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    @Query("SELECT m FROM Mission m WHERE m.startDate <= :now AND m.dueDate >= :now")
    Mission getActualMission(@Param("now") Timestamp now);

}
