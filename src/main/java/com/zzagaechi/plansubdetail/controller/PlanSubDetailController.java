package com.zzagaechi.plansubdetail.controller;

import com.zzagaechi.plansubdetail.dto.request.PlanSubDetailListeRequest;
import com.zzagaechi.plansubdetail.dto.request.ToggleListRequest;
import com.zzagaechi.plansubdetail.dto.request.UpdateRequest;
import com.zzagaechi.plansubdetail.dto.response.PlanSubDetailCreateResponse;
import com.zzagaechi.plansubdetail.dto.response.PlanSubDetailsByPlanSubResponse;
import com.zzagaechi.plansubdetail.service.PlanSubDetailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@Tag(name = "세부 작업의 상제정보(ch1보기)")
@RestController
@RequestMapping("/plansubdetail")
@RequiredArgsConstructor
public class PlanSubDetailController {
    private final PlanSubDetailService planSubDetailService;

    @Operation(
            summary = "세부 작업 상세 일괄 생성",
            description = "여러 개의 세부 작업 상세를 한 번에 생성합니다.")
    @PostMapping("/{userId}/{planSubId}")
    public ResponseEntity<PlanSubDetailCreateResponse> createDetails(
            @PathVariable String userId,
            @PathVariable int planSubId,
            @Valid @RequestBody PlanSubDetailListeRequest request) {
        return ResponseEntity
                .status(201)
                .body(planSubDetailService.createDetails(userId, planSubId, request));
    }

    @Operation(summary = "세부 작업 상세 삭제")
    @DeleteMapping("/{userId}/{detailId}")
    public ResponseEntity<Void> deleteDetail(
            @PathVariable String userId,
            @PathVariable int detailId) {
        planSubDetailService.deleteDetail(userId, detailId);
        return ResponseEntity.ok().build();
    }


    /*@Operation(summary = "세부 작업 상세 완료 여부 일괄 토글")
    @PatchMapping("/{userId}/toggle")
    public ResponseEntity<Void> toggleCompleteList(
            @PathVariable String userId,
            @Valid @org.springframework.web.bind.annotation.RequestBody ToggleListRequest request) {
        planSubDetailService.toggleCompleteList(userId, request.getDetailIds());
        return ResponseEntity.ok().build();
    }
*/

    @Operation(summary = "내용수정", description = "세부 작업내용을 수정합니다.")
    @PatchMapping("/{userId}/{detailId}")
    public ResponseEntity<Void> updateDetail(
            @PathVariable String userId,
            @PathVariable int detailId,
            @org.springframework.web.bind.annotation.RequestBody UpdateRequest request) {
        planSubDetailService.updateDetail(userId, detailId, request);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "같은 PlanSub에 속한 세부 작업들 조회",
            description = "주어진 세부 작업이 속한 PlanSub의 모든 세부 작업과 제목을 조회합니다.")
    @GetMapping("/{userId}/{detailId}")
    public ResponseEntity<PlanSubDetailsByPlanSubResponse> getDetailWithSiblings(
            @PathVariable String userId,
            @PathVariable int detailId) {
        return ResponseEntity.ok(planSubDetailService.getDetailsByDetailId(userId, detailId));
    }
}
