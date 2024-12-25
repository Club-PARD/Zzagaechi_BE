package com.zzagaechi.plansub.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Schema(description = "세부 작업 생성 요청")
public class PlanSubCreateRequest {
    @Getter
    @NoArgsConstructor
    @Schema(description = "종료일O + 종료시간O")
    public static class CreateEndDateWithEndTimeDto {
        @Schema(description = "제목", example = "프로젝트 개발")
        @NotBlank
        private String title;

        @Schema(description = "시작 날짜", example = "2024-01-01")
        @NotNull
        private LocalDate startDate;

        @Schema(description = "종료날짜", example = "2024-01-03")
        @NotNull(message = "종료날짜를 입력해야합니다")
        private LocalDate endDate;

        @Schema(description = "종료시간", example = "17:30")
        @NotNull(message = "종료시간을 입력해야합니다")
        private LocalTime endTime;

        @Schema(description = "세부 작업 목록", example = "[\"자료 조사하기\", \"회의록 작성\"]")
        private List<String> subtasks;
    }

    @Getter
    @NoArgsConstructor
    @Schema(description = "종료일O + 종료시간X")
    public static class CreateEndDateDto {
        @Schema(description = "제목", example = "프로젝트 기간")
        @NotBlank
        private String title;

        @Schema(description = "시작 날짜", example = "2024-01-01")
        @NotNull
        private LocalDate startDate;

        @Schema(description = "종료날짜", example = "2024-01-31")
        @NotNull(message = "종료날짜를 입력해야합니다")
        private LocalDate endDate;

        @Schema(description = "세부 작업 목록", example = "[\"요구사항 분석\", \"설계\", \"개발\"]")
        private List<String> subtasks;
    }

    @Getter
    @NoArgsConstructor
    @Schema(description = "종료일X + 종료시간O")
    public static class CreateEndTimeDto {
        @Schema(description = "제목", example = "일일 업무")
        @NotBlank
        private String title;

        @Schema(description = "시작 날짜", example = "2024-01-01")
        @NotNull
        private LocalDate startDate;

        @Schema(description = "종료시간", example = "18:00")
        @NotNull(message = "종료시간을 입력해야합니다")
        private LocalTime endTime;

        @Schema(description = "세부 작업 목록", example = "[\"이메일 확인\", \"보고서 작성\"]")
        private List<String> subtasks;
    }

    @Getter
    @NoArgsConstructor
    @Schema(description = "종료일X + 종료시간X")
    public static class CreateNothingDto {
        @Schema(description = "제목", example = "할일 목록")
        @NotBlank
        private String title;

        @Schema(description = "시작 날짜", example = "2024-01-01")
        @NotNull
        private LocalDate startDate;

        @Schema(description = "세부 작업 목록", example = "[\"장보기\", \"청소하기\", \"빨래하기\"]")
        private List<String> subtasks;
    }
}
