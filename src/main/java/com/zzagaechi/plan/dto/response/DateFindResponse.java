package com.zzagaechi.plan.dto.response;

import com.zzagaechi.plan.entity.Plan;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalTime;

@Schema(description = "일정 목록 조회 응답")
@Getter
@Builder
public class DateFindResponse {
    private int planId;
    private String plantitle;
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean isCompleted;


    public static DateFindResponse from(Plan plan) {
        return DateFindResponse.builder()
                .planId(plan.getPlanId())
                .plantitle(plan.getPlantitle())
                .startTime(plan.getStartTime())
                .endTime(plan.getEndTime())
                .isCompleted(plan.isCompleted())
                .build();
    }
}