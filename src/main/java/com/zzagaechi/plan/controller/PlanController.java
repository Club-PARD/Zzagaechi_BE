package com.zzagaechi.plan.controller;

import com.zzagaechi.plan.dto.request.PlanCreateReq;
import com.zzagaechi.plan.service.PlanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Plan", description = "일정 API")
@RestController
@RequestMapping("/plan")
@RequiredArgsConstructor
public class PlanController {
    
    private final PlanService planService;
    
    @Operation(summary = "일정 생성 - 종료일O, 시작및종료시간O",
            description = "새로운 일정을 생성합니다. (종료일 O, 시작/종료시간 O) <br />"
                    + "@param PlanCreateReq.CreateWithEndDateAndTimeDto - 일정명, 시작날짜, 종료날짜, 시작시간, 종료시간 <br />"
                    + "@return 201 Created 상태코드와 생성된 일정의 ID <br />"
                    + "@exception 입력값 유효성 검증 실패 시 400 Bad Request 반환")

    @PostMapping("/with-enddate-fulltime")
    public ResponseEntity<Void> createWithEndDateFullTime(
            @AuthenticationPrincipal String userId,
            @Valid @RequestBody PlanCreateReq.CreateEndDateAndTimeDto req) {
        planService.createSchedule(req, userId);
        return ResponseEntity.status(201).build();
    }

    @Operation(summary = "일정 생성 - 종료일O, 시작및종료시간X",
            description = "새로운 일정을 생성합니다. (종료일 O, 시작/종료시간 X) <br />"
                    + "@param PlanCreateReq.CreateWithEndDateDto - 일정명, 시작날짜, 종료날짜 <br />"
                    + "@return 201 Created 상태코드와 생성된 일정의 ID <br />"
                    + "@exception 입력값 유효성 검증 실패 시 400 Bad Request 반환")
    @PostMapping("/with-enddate")
    public ResponseEntity<Void> createWithEndDate(
            @AuthenticationPrincipal String userId,
            @Valid @RequestBody PlanCreateReq.CreateEndDateDto req) {
        planService.createSchedule(req, userId);
        return ResponseEntity.status(201).build();
    }

    @Operation(summary = "일정 생성 - 종료일X, 시작및종료시간O",
            description = "새로운 일정을 생성합니다. (종료일 X, 시작/종료시간 O) <br />"
                    + "@param PlanCreateReq.CreateWithTimeDto - 일정명, 시작날짜, 시작시간, 종료시간 <br />"
                    + "@return 201 Created 상태코드와 생성된 일정의 ID <br />"
                    + "@exception 입력값 유효성 검증 실패 시 400 Bad Request 반환")
    @PostMapping("/with-fulltime")
    public ResponseEntity<Void> createWithFullTime(
            @AuthenticationPrincipal String userId,
            @Valid @RequestBody PlanCreateReq.CreateTimeDto req) {
        planService.createSchedule(req, userId);
        return ResponseEntity.status(201).build();
    }

    @Operation(summary = "일정 생성 - 종료일X, 시작및종료시간X",
            description = "새로운 일정을 생성합니다. (종료일 X, 시작/종료시간 X) <br />"
                    + "@param PlanCreateReq.CreateBasicDto - 일정명, 시작날짜 <br />"
                    + "@return 201 Created 상태코드와 생성된 일정의 ID <br />"
                    + "@exception 입력값 유효성 검증 실패 시 400 Bad Request 반환")
    @PostMapping("/with-nothing")
    public ResponseEntity<Void> createWithNothing(
            @AuthenticationPrincipal String userId,
            @Valid @RequestBody PlanCreateReq.CreateNothingDto req) {
        planService.createSchedule(req, userId);
        return ResponseEntity.status(201).build();
    }
}
