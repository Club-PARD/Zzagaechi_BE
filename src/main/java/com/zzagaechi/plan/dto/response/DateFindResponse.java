package com.zzagaechi.plan.dto.response;

import com.zzagaechi.plan.entity.Plan;
import com.zzagaechi.view.toggle.entity.PlanDateToggle;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalTime;
import java.util.Optional;

@Schema(description = "일정 목록 조회 응답")
@Getter
@Builder
public class DateFindResponse {
    private int planId;
    private String plantitle;
    private LocalTime startTime;
    private boolean isCompleted;


    public static DateFindResponse from(Plan plan, Optional<PlanDateToggle> dateToggle) {
        return DateFindResponse.builder()
                .planId(plan.getPlanId())
                .plantitle(plan.getPlantitle())
                .startTime(plan.getStartTime())
                .isCompleted(dateToggle.map(PlanDateToggle::isCompleted)
                        .orElse(plan.isCompleted()))
                .build();
    }
}