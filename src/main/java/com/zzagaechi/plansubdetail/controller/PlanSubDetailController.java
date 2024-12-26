package com.zzagaechi.plansubdetail.controller;

import com.zzagaechi.plansubdetail.dto.request.PlanSubDetailCreateRequest;
import com.zzagaechi.plansubdetail.service.PlanSubDetailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "세부 작업 상세 API", description = "세부 작업의 상세 정보를 관리하는 API")
@RestController
@RequestMapping("/api/plansubdetail")
@RequiredArgsConstructor
public class PlanSubDetailController {

    private final PlanSubDetailService planSubDetailService;

    @Operation(summary = "세부 작업 시간 설정", description = "세부 작업에 대한 구체적인 시간을 설정합니다.")
    @PostMapping("/create")
    public ResponseEntity<Void> createDetail(@RequestBody PlanSubDetailCreateRequest request) {
        planSubDetailService.createDetail(request);
        return ResponseEntity.status(201).build();
    }
}
