package com.zzagaechi.plansubdetail.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Schema(description = "세부 작업 상세 일괄 생성 요청")
@Getter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class PlanSubDetailListeRequest {
    @Schema(description = "세부 작업 상세 (내용+날짜+시작시간+종료시간)의 리스트")
    @NotEmpty
    private List<DetailRequestDto> details;


    @Schema(description = "세부 작업 상세 정보")
    @Getter
    @NoArgsConstructor  @AllArgsConstructor
    @Builder
    public static class DetailRequestDto {//리스트에 들어갈 내용  List<DetailRequestDto> 이렇게 받을거임
        @Schema(description = "세부 작업 내용", example = "종이 구하기")
        @NotBlank
        private String content;
        
        @Schema(description = "날짜", example = "2024-01-26")
        @NotNull
        private LocalDate date;
        
        @Schema(description = "시작 시간", example = "13:00")
        @NotNull
        private LocalTime startTime;
        
        @Schema(description = "종료 시간", example = "14:00")
        @NotNull
        private LocalTime endTime;
    }

}
