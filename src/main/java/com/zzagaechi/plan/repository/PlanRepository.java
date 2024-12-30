package com.zzagaechi.plan.repository;

import com.zzagaechi.plan.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

/**
 * 
 */
public interface PlanRepository extends JpaRepository<Plan, Integer> {

    // 
    List<Plan> findAllByUser_UidAndDoDateAndStartTimeIsNotNullOrderByStartTimeAsc(String uid, LocalDate date);
    // 
    List<Plan> findAllByUser_UidAndDoDateAndStartTimeIsNull(String uid, LocalDate date);


    List<Plan> findAllByUser_UidAndDoDateBetweenOrderByDoDateAsc(
            String uid,
            LocalDate startOfMonth,
            LocalDate endOfMonth
    );//


    List<Plan> findAllByUserUidAndPlanIdIn(String userId, List<Integer> planIds);
}
