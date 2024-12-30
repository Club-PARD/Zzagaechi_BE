package com.zzagaechi.view.daily.dto.response;


import com.zzagaechi.plan.dto.response.DateFindResponse;
import com.zzagaechi.plansubdetail.dto.response.PlanSubDetailListResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Schema(description = "일일 진행률 및 할 일 목록 응답")
@Getter
@Builder
public class DailyResponse {
    @Schema(description = "날짜")
    private LocalDate date;

    @Schema(description = "총 할 일 개수")
    private int totalCount;

    @Schema(description = "완료된 일 개수")
    private int completedCount;

    @Schema(description = "일정 목록")
    private List<DateFindResponse> plans;

    @Schema(description = "세부 작업 목록")
    private List<PlanSubDetailListResponse> details;

    public static DailyResponse of(
            LocalDate date,
            List<DateFindResponse> plans,
            List<PlanSubDetailListResponse> details) {

        int planTotal = plans.size();
        int planCompleted = (int) plans.stream()
                .filter(DateFindResponse::isCompleted)
                .count();

        int detailTotal = details.size();
        int detailCompleted = (int) details.stream()
                .filter(PlanSubDetailListResponse::isCompleted)
                .count();
        //각각 사이즈 측정


        int total = planTotal + detailTotal;
        int completed = planCompleted + detailCompleted;
        //합쳐서

        return DailyResponse.builder()
                .date(date)
                .totalCount(total)
                .completedCount(completed)
                .plans(plans)
                .details(details)
                .build();
        //return
    }
}