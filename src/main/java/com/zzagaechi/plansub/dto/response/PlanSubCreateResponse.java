package com.zzagaechi.plansub.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "세부 작업 생성 응답")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanSubCreateResponse {
    @Schema(description = "생성된 세부 작업 ID")
    private int id;
}
