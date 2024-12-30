package com.zzagaechi.view.toggle.repository;

import com.zzagaechi.view.toggle.entity.PlanDateToggle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PlanDateToggleRepository extends JpaRepository<PlanDateToggle, Integer> {
    // 특정 일정의 특정 날짜 완료 상태 조회
    //단일 일정의 완료 상태를 확인할 때 사용 (예: 일정 상세 보기)
    Optional<PlanDateToggle> findByPlan_PlanIdAndDate(int planId, LocalDate date);

    // 특정 일정들의 특정 날짜 완료 상태 조회
    //여러 일정의 완료 상태를 한 번에 조회할 때 사용 (예: 일별 보기에서 모든 일정의 상태 확인)
    List<PlanDateToggle> findAllByPlan_PlanIdInAndDate(List<Integer> planIds, LocalDate date);
}
