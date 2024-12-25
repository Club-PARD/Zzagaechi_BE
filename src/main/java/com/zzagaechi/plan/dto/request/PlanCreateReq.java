package com.zzagaechi.plan.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalTime;

@Schema(description = "일정 생성 요청 DTO")
public class PlanCreateReq {
    
    @Getter
    @NoArgsConstructor
    @Schema(description = "세분화(X)+ 종료일(O) + 시작/종료시간(O)")
    public static class CreateEndDateAndTimeDto {
        @Schema(description = "일정명", example = "프로젝트 미팅")
        @NotBlank(message = "일정명은 필수입니다")
        private String title;
        
        @Schema(description = "시작날짜", example = "2024-01-01")
        @NotNull(message = "시작날짜는 필수입니다")
        private LocalDate startDate;
        
        @Schema(description = "종료날짜", example = "2024-01-03")
        @NotNull(message = "종료날짜를 입력해야합니다.")
        private LocalDate endDate;
        
        @Schema(description = "시작시간", example = "09:00")
        @NotNull(message = "시작시간을 입력해야합니다")
        private LocalTime startTime;
        
        @Schema(description = "종료시간", example = "10:30")
        @NotNull(message = "종료시간을 입력해야합니다.")
        private LocalTime endTime;
    }
    
    @Getter
    @NoArgsConstructor
    @Schema(description = "종료일(O) + 시작/종료시간(X)")
    public static class CreateEndDateDto {
        @Schema(description = "일정명", example = "프로젝트 기간")
        @NotBlank(message = "일정명은 필수입니다")
        private String title;
        
        @Schema(description = "시작날짜", example = "2024-01-01")
        @NotNull(message = "시작날짜는 필수입니다")
        private LocalDate startDate;
        
        @Schema(description = "종료날짜", example = "2024-01-31")
        @NotNull(message = "종료날짜는 필수입니다")
        private LocalDate endDate;
    }
    
    @Getter
    @NoArgsConstructor
    @Schema(description = "종료일(X) + 시작/종료시간(O)")
    public static class CreateTimeDto {
        @Schema(description = "일정명", example = "일일 스크럼")
        @NotBlank(message = "일정명은 필수입니다")
        private String title;
        
        @Schema(description = "시작날짜", example = "2024-01-01")
        @NotNull(message = "시작날짜는 필수입니다")
        private LocalDate startDate;
        
        @Schema(description = "시작시간", example = "09:00")
        @NotNull(message = "시작시간은 필수입니다")
        private LocalTime startTime;
        
        @Schema(description = "종료시간", example = "09:30")
        @NotNull(message = "종료시간은 필수입니다")
        private LocalTime endTime;
    }
    
    @Getter
    @NoArgsConstructor
    @Schema(description = "종료일(X) + 시작/종료시간(X)")
    public static class CreateNothingDto {
        @Schema(description = "일정명", example = "휴가")
        @NotBlank(message = "일정명은 필수입니다")
        private String title;
        
        @Schema(description = "시작날짜", example = "2024-01-01")
        @NotNull(message = "시작날짜는 필수입니다")
        private LocalDate startDate;
    }
}
