package com.zzagaechi.view.daily.service;

import com.zzagaechi.plan.dto.response.DateFindResponse;
import com.zzagaechi.plansubdetail.dto.response.PlanSubDetailListResponse;
import com.zzagaechi.view.daily.dto.response.DailyResponse;
import com.zzagaechi.plan.service.PlanService;
import com.zzagaechi.plansubdetail.service.PlanSubDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DailyService {
    private final PlanService planService;
    private final PlanSubDetailService planSubDetailService;

    @Transactional(readOnly = true)
    public DailyResponse getDaily(String userId, LocalDate date) {
        List<DateFindResponse> plans = planService.getPlansByDate(userId, date); //plan서비스에서 이 날짜로 리스트업 하고
        List<PlanSubDetailListResponse> details = planSubDetailService.getDetailsByDate(userId, date);
        return DailyResponse.of(date, plans, details);

    }
}