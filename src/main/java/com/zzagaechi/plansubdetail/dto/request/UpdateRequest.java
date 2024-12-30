package com.zzagaechi.plansubdetail.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@NoArgsConstructor
@Schema(description = "세부 작업 수정 요청")
public class UpdateRequest {
    @Schema(description = "세부 작업 내용")
    private String content;

    @Schema(description = "날짜")
    private LocalDate date;

    @Schema(description = "시작 시간")
    private LocalTime startTime;

    @Schema(description = "종료 시간")
    private LocalTime endTime;
}