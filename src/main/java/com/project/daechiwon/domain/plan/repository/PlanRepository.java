package com.project.daechiwon.domain.plan.repository;

import com.project.daechiwon.domain.plan.entity.EducationPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PlanRepository extends JpaRepository<EducationPlan, Long> {
    @Query("SELECT plan FROM EducationPlan plan WHERE plan.date BETWEEN :start AND :end ORDER BY plan.date")
    List<EducationPlan> findAllInMonth(@Param("start") LocalDate start, @Param("end") LocalDate end);
}
