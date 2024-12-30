package com.zzagaechi.plansub.repository;

import com.zzagaechi.plansub.entity.PlanSub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PlanSubRepository extends JpaRepository<PlanSub, Integer> {

    // 특정 월에 걸쳐있는 모든 일정 조회 (캘린더용)
    // 해당 월이 일정 기간에 포함되는 모든 일정 조회
    @Query("SELECT p FROM PlanSub p WHERE p.user.uid = :uid " +
           "AND p.startDate <= :endOfMonth " +    // 시작일이 월의 마지막 날보다 작거나 같고
           "AND p.endDate >= :startOfMonth " +    // 종료일이 월의 첫 날보다 크거나 같은 일정
           "ORDER BY p.startDate ASC")
    List<PlanSub> findAllByMonthRange(
            @Param("uid") String uid,
            @Param("startOfMonth") LocalDate startOfMonth,
            @Param("endOfMonth") LocalDate endOfMonth
    );

}
