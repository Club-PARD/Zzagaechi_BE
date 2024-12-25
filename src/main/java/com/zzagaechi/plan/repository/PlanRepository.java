package com.zzagaechi.plan.repository;

import com.zzagaechi.plan.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

public interface PlanRepository extends JpaRepository<Plan, Long> {

}
