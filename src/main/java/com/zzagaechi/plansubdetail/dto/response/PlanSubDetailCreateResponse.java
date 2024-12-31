package com.zzagaechi.plansubdetail.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class PlanSubDetailCreateResponse {
    private int plansubId;
    private List<DetailResponse> details;

    @Getter
    @Builder
    @AllArgsConstructor
    public static class DetailResponse {
        private int detailId;
        private String content;
        private LocalDate date;
        private LocalTime startTime;
        private LocalTime endTime;
    }
}