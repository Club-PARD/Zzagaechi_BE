package com.zzagaechi.view.calendar.service;

import com.zzagaechi.view.calendar.dto.response.CalendarResponse;
import com.zzagaechi.plan.repository.PlanRepository;
import com.zzagaechi.plansub.repository.PlanSubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CalendarService {
    private final PlanRepository planRepository;
    private final PlanSubRepository planSubRepository;

    public CalendarResponse getCalendar(String userid, YearMonth yearMonth) {
        // 해당 월의 시작일과 마지막일 계산
        LocalDate startDate = yearMonth.atDay(1);
        LocalDate endDate = yearMonth.atEndOfMonth();

        // 해당 월의 Plan 목록 조회 및 DTO 변환
        List<CalendarResponse.CalendarPlanDto> plans = planRepository
                .findAllByMonthRange(
                        userid,
                        startDate,
                        endDate
                )
                .stream()
                .map(plan -> CalendarResponse.CalendarPlanDto.from(plan))
                .toList();

        // 해당 월의 PlanSub 목록 조회 및 DTO 변환
        List<CalendarResponse.CalendarPlanSubDto> planSubs = planSubRepository
                .findAllByMonthRange(
                        userid,
                        startDate,
                        endDate
                )
                .stream()
                .map(planSub -> CalendarResponse.CalendarPlanSubDto.from(planSub))
                .toList();

        return CalendarResponse.builder()
                .plans(plans)
                .planSubs(planSubs)
                .build();
    }
}