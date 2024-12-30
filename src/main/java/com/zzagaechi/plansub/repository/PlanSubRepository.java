package com.zzagaechi.plansub.repository;

import com.zzagaechi.plansub.entity.PlanSub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PlanSubRepository extends JpaRepository<PlanSub, Integer> {

    List<PlanSub> findAllByUser_UidAndStartDateBetween(
            String uid,
            LocalDate startDate,
            LocalDate endDate
    );



}
