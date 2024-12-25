package com.zzagaechi.plansubdetail.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Schema(description = "세부 작업 상세 생성 요청")
public class PlanSubDetailCreateRequest {
    
    @Getter
    @NoArgsConstructor
    public static class TodoDetailDto {
        @Schema(description = "날짜", example = "2024-01-01")
        @NotNull
        private LocalDate date;

        @Schema(description = "시작 시간", example = "09:00")
        @NotNull
        private LocalTime startTime;

        @Schema(description = "종료 시간", example = "12:00")
        @NotNull
        private LocalTime endTime;
    }

    @Getter
    @NoArgsConstructor
    public static class CreateRequest {
        @Schema(description = "세부 작업 ID")
        @NotNull
        private Long planSubId;

        @Schema(description = "할일별 시간 정보 목록")
        @NotNull
        private List<TodoDetailDto> todoDetails;
    }
}
