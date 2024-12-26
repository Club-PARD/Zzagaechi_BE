package com.zzagaechi.plansub.controller;

import com.zzagaechi.plansub.dto.request.PlanSubCreateRequest;
import com.zzagaechi.plansub.dto.response.PlanSubCreateResponse;
import com.zzagaechi.plansub.service.PlanSubService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "세분화하는 plan")
@RestController
@RequestMapping("/plansub")
@RequiredArgsConstructor
public class PlanSubController {
    private final PlanSubService planSubService;


    @Operation(summary = "일정 생성 - 종료일O + 종료시간O",
            description = "새로운 일정을 생성합니다. (종료일 O, 종료시간 O)"
                    + "@param PlanSubCreateRequest.CreateEndDateWithEndTimeDto - 일정명, 시작날짜, 종료날짜, 종료시간  <br />")
    @PostMapping("/{userId}/with-enddate-endtime/")
    public ResponseEntity<PlanSubCreateResponse> createWithEndDateEndTime(
            @PathVariable String userId,
            @Valid @RequestBody PlanSubCreateRequest.CreateEndDateWithEndTimeDto req) {
        int id = planSubService.createPlanSub(req, userId);
        return ResponseEntity.status(201).body(new PlanSubCreateResponse(id));
    }

    @Operation(summary = "일정 생성 - 종료일O + 종료시간X",
            description = "새로운 일정을 생성합니다. (종료일 O, 종료시간 X)"
                    + "@param PlanSubCreateRequest.CreateEndDateDto - 일정명, 시작날짜,종료날짜 <br />")
    @PostMapping("/{userId}/with-enddate")
    public ResponseEntity<PlanSubCreateResponse> createWithEndDate(
            @PathVariable String userId,
            @Valid @RequestBody PlanSubCreateRequest.CreateEndDateDto req) {
        int id = planSubService.createPlanSub(req, userId);
        return ResponseEntity.status(201).body(new PlanSubCreateResponse(id));
    }

    @Operation(summary = "일정 생성 - 종료일X + 종료시간O",
            description = "새로운 일정을 생성합니다. (종료일 X, 종료시간 O)"
                    + "@param  PlanSubCreateRequest.CreateEndTimeDto - 일정명, 시작날짜, 종료시간<br />")
    @PostMapping("/{userId}/with-endtime")
    public ResponseEntity<PlanSubCreateResponse> createWithEndTime(
            @PathVariable String userId,
            @Valid @RequestBody PlanSubCreateRequest.CreateEndTimeDto req) {
        int id = planSubService.createPlanSub(req, userId);
        return ResponseEntity.status(201).body(new PlanSubCreateResponse(id));
    }

    @Operation(summary = "일정 생성 - 종료일X + 종료시간X",
            description = "새로운 일정을 생성합니다. (종료일 X, 종료시간 X)"
                    + "@param PlanSubCreateRequest.CreateNothingDto - 일정명, 시작날짜 <br />")
    @PostMapping("/{userId}/with-nothing")
    public ResponseEntity<PlanSubCreateResponse> createWithNothing(
            @PathVariable String userId,
            @Valid @RequestBody PlanSubCreateRequest.CreateNothingDto req) {
        int id = planSubService.createPlanSub(req, userId);
        return ResponseEntity.status(201).body(new PlanSubCreateResponse(id));
    }
}
