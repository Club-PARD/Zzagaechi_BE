package com.zzagaechi.plan.dto.request;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalTime;

@Schema(description = "세분화(x) PostDto")
public class PlanCreateReq {

    //CreateEndDateAndTimeDto 클래스 하나에 변수가다 다름
    @Getter
    @NoArgsConstructor
    @Schema(description = "일정명 + 날짜 + 시작/종료시간")
    public static class DateTimeDto {
        @Schema(description = "일정명", example = "프로젝트 미팅")
        @NotBlank(message = "일정명은 필수입니다")
        private String plantitle;

        @Schema(description = "시작 날짜", example = "2024-01-01")
        @NotNull(message = "시작 날짜는 필수입니다")
        private LocalDate startDate;

        @Schema(description = "종료 날짜", example = "2024-01-02")
        @NotNull(message = "종료 날짜는 필수입니다")
        private LocalDate endDate;

        @Schema(description = "시작시간", example = "09:00")
        @NotNull(message = "시작 시간은 필수입니다")
        private LocalTime startTime;

    }

    @Getter
    @NoArgsConstructor
    @Schema(description = "일정명 + 날짜만!")
    public static class DateDto {
        @Schema(description = "일정명", example = "마트가기")
        @NotBlank(message = "일정명은 필수입니다")
        private String plantitle;

        @Schema(description = "시작 날짜", example = "2024-01-01")
        @NotNull(message = "시작 날짜는 필수입니다")
        private LocalDate startDate;

        @Schema(description = "종료 날짜", example = "2024-01-02")
        @NotNull(message = "종료 날짜는 필수입니다")
        private LocalDate endDate;
    }
}
