package com.zzagaechi.plansubdetail.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

public class PlanSubDetailRequest {
    
    @Getter
    @NoArgsConstructor
    public static class UpdateTimesDto {
        @Schema(description = "시작 시간", example = "09:00")
        @NotNull
        private LocalTime startTime;

        @Schema(description = "종료 시간", example = "10:30")
        @NotNull
        private LocalTime endTime;
    }
}
