package com.zzagaechi.plansubdetail.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Schema(description = "세부 작업 상세 완료 여부 일괄 토글 요청")
@Getter
@NoArgsConstructor
public class ToggleListRequest {
    @Schema(description = "토글할 세부 작업 상세 ID 목록")
    private List<Integer> detailIds;
}