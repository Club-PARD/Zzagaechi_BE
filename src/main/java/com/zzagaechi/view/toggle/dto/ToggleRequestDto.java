package com.zzagaechi.view.toggle.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;

@Schema(description = "토글 요청 DTO")
@Getter
@NoArgsConstructor
public class ToggleRequestDto {
    @Schema(description = "Plan ID 리스트", example = "[1, 2, 3]")
    private List<Integer> planIds;
    
    @Schema(description = "PlanSubDetail ID 리스트", example = "[4, 5, 6]")
    private List<Integer> planSubDetailIds;
}
