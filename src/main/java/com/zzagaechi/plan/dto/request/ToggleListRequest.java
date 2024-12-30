package com.zzagaechi.plan.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Schema(description = "일정 완료 여부 일괄 토글 요청")
@Getter
@NoArgsConstructor
public class ToggleListRequest {
    @Schema(
            description = "Plan ID 리스트",
            example = "[1, 2, 3]"
    )
    private List<Integer> planIds;
}