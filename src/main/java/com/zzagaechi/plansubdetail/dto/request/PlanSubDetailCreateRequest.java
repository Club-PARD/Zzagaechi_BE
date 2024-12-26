package com.zzagaechi.plansubdetail.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Schema(description = "세부 작업 상세 생성 요청")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanSubDetailCreateRequest {
    @Schema(description = "세부 작업 ID")
    @NotNull
    private Long planSubId;

    @Schema(description = "세부 작업 내용", example = "수학 공부하기")
    @NotNull
    private String content;

    @Schema(description = "날짜", example = "2024-01-26")
    @NotNull
    private LocalDate date;

    @Schema(description = "시작 시간", example = "09:00")
    @NotNull
    private LocalTime startTime;

    @Schema(description = "종료 시간", example = "10:00")
    @NotNull
    private LocalTime endTime;
}
