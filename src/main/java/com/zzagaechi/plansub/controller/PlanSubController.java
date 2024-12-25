package com.zzagaechi.plansub.controller;

import com.zzagaechi.plansub.dto.request.PlanSubCreateRequest;
import com.zzagaechi.plansub.service.PlanSubService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "PlanSub", description = "세부작업이 있는 일정 API")
@RestController
@RequestMapping("/plansub")
@RequiredArgsConstructor
public class PlanSubController {
    private final PlanSubService planSubService;

    @Operation(summary = "일정 생성 - 종료일O + 종료시간O",
            description = "새로운 일정을 생성합니다. (종료일 O, 종료시간 O)")
    @PostMapping("/with-enddate-endtime")
    public ResponseEntity<Void> createWithEndDateEndTime(
            @AuthenticationPrincipal String userId,
            @Valid @RequestBody PlanSubCreateRequest.CreateEndDateWithEndTimeDto req) {
        planSubService.createPlanSub(req, userId);
        return ResponseEntity.status(201).build();
    }

    @Operation(summary = "일정 생성 - 종료일O + 종료시간X",
            description = "새로운 일정을 생성합니다. (종료일 O, 종료시간 X)")
    @PostMapping("/with-enddate")
    public ResponseEntity<Void> createWithEndDate(
            @AuthenticationPrincipal String userId,
            @Valid @RequestBody PlanSubCreateRequest.CreateEndDateDto req) {
        planSubService.createPlanSub(req, userId);
        return ResponseEntity.status(201).build();
    }

    @Operation(summary = "일정 생성 - 종료일X + 종료시간O",
            description = "새로운 일정을 생성합니다. (종료일 X, 종료시간 O)")
    @PostMapping("/with-endtime")
    public ResponseEntity<Void> createWithEndTime(
            @AuthenticationPrincipal String userId,
            @Valid @RequestBody PlanSubCreateRequest.CreateEndTimeDto req) {
        planSubService.createPlanSub(req, userId);
        return ResponseEntity.status(201).build();
    }

    @Operation(summary = "일정 생성 - 종료일X + 종료시간X",
            description = "새로운 일정을 생성합니다. (종료일 X, 종료시간 X)")
    @PostMapping("/with-nothing")
    public ResponseEntity<Void> createWithNothing(
            @AuthenticationPrincipal String userId,
            @Valid @RequestBody PlanSubCreateRequest.CreateNothingDto req) {
        planSubService.createPlanSub(req, userId);
        return ResponseEntity.status(201).build();
    }
}
