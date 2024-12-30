package com.zzagaechi.plansubdetail.dto.response;

import com.zzagaechi.plansub.entity.PlanSub;
import com.zzagaechi.plansubdetail.entity.PlanSubDetail;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@Schema(description = "PlanSub에 속한 세부 작업 목록 응답")
public class PlanSubDetailsByPlanSubResponse {
    @Schema(description = "PlanSub 제목")
    private String title;

    @Schema(description = "세부 작업 목록")
    private List<PlanSubDetailListResponse> details;

    public static PlanSubDetailsByPlanSubResponse of(PlanSub planSub, List<PlanSubDetail> details) {
        return PlanSubDetailsByPlanSubResponse.builder()
                .title(planSub.getPlansubtitle())
                .details(details.stream()
                        .map(PlanSubDetailListResponse::from)
                        .toList())
                .build();
    }
}