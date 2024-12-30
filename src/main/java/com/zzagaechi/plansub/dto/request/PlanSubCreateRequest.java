package com.zzagaechi.plansub.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;


public class PlanSubCreateRequest {

    @Getter
    @NoArgsConstructor
    @Schema(description = "종료시간O")
    public static class CreateTimeDto {
        @Schema(description = "제목", example = "프로젝트 개발")
        @NotBlank
        private String plansubtitle;

        @Schema(description = "시작 날짜", example = "2024-01-01")
        @NotNull
        private LocalDate startDate;

        @Schema(description = "종료날짜", example = "2024-01-03")
        @NotNull(message = "종료날짜를 입력해야합니다")
        private LocalDate endDate;

        @Schema(description = "종료시간", example = "17:30")
        @NotNull(message = "종료시간을 입력해야합니다")
        private LocalTime deadline;
    }


    @Getter
    @NoArgsConstructor
    @Schema(description = "종료시간X")
    public static class CreateDto {
        @Schema(description = "제목", example = "프로젝트 기간")
        @NotBlank
        private String plansubtitle;

        @Schema(description = "시작 날짜", example = "2024-01-01")
        @NotNull
        private LocalDate startDate;

        @Schema(description = "종료날짜", example = "2024-01-31")
        @NotNull(message = "종료날짜를 입력해야합니다")
        private LocalDate endDate;
    }

}
