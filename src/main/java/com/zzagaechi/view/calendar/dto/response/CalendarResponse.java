package com.zzagaechi.view.calendar.dto.response;

import com.zzagaechi.plan.entity.Plan;
import com.zzagaechi.plansub.entity.PlanSub;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Schema(description = "캘린더 일정 조회 응답")
@Getter
@Builder
@AllArgsConstructor
public class CalendarResponse {
    @Schema(description = "일정 목록")
    private List<CalendarPlanDto> plans;

    @Schema(description = "세부 일정 목록")
    private List<CalendarPlanSubDto> planSubs;



    @Schema(description = "plan DTO")
    @Getter
    @Builder
    public static class CalendarPlanDto {
        @Schema(description = "일정 ID")
        private int planId;

        @Schema(description = "일정 제목")
        private String plantitle;

        @Schema(description = "날짜")
        private LocalDate doDate;

        public static CalendarPlanDto from(Plan plan) {
            return CalendarPlanDto.builder()
                    .planId(plan.getPlanId())
                    .plantitle(plan.getTitle())
                    .doDate(plan.getDoDate())
                    .build();
        }
    }



    @Schema(description = "plansub DTO")
    @Getter
    @Builder
    public static class CalendarPlanSubDto {
        @Schema(description = "세부 일정 ID")
        private int id;

        @Schema(description = "세부 일정 제목")
        private String plansubtitle;

        @Schema(description = "시작 날짜")
        private LocalDate startDate;

        @Schema(description = "종료 날짜")
        private LocalDate endDate;

        @Schema(description = "마감 시간")
        private LocalTime deadline;

        public static CalendarPlanSubDto from(PlanSub planSub) {
            return CalendarPlanSubDto.builder()
                    .id(planSub.getId())
                    .plansubtitle(planSub.getPlansubtitle())
                    .startDate(planSub.getStartDate())
                    .endDate(planSub.getEndDate())
                    .deadline(planSub.getDeadline())
                    .build();
        }
    }
}