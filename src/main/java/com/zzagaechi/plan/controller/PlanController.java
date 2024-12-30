package com.zzagaechi.plan.controller;
import com.zzagaechi.plan.dto.request.PlanCreateReq;
import com.zzagaechi.plan.dto.request.PlanUpdateRequest;
import com.zzagaechi.plan.service.PlanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Tag(name = "세분화하지 않는 plan(마트가기)")
@RestController
@RequestMapping("/plan")
@RequiredArgsConstructor
public class PlanController {
    
    private final PlanService planService;

    @Operation(summary = "간단한 일정 생성(Date)")
    @PostMapping("/{userId}/date")
    public ResponseEntity<Integer> createWithNothing(
            @PathVariable String userId,
            @Valid @RequestBody PlanCreateReq.DateDto req) {
        int planId = planService.createSchedule(userId, req);
        return ResponseEntity.status(201).body(planId);
    }

    @Operation(summary = "간단한 일정 생성(Date+Time)")
    @PostMapping("/{userId}/datetime")
    public ResponseEntity<Integer> createWithEndDateFullTime(
            @PathVariable String userId,
            @Valid @RequestBody PlanCreateReq.DateTimeDto req) {
        int planId = planService.createSchedule(userId,req);
        return ResponseEntity.status(201).body(planId);
    }


    @Operation(summary = "세분화가 없는 일정 삭제")
    @DeleteMapping("/{userId}/{planId}")
    public ResponseEntity<Void> deletePlan(
            @PathVariable String userId,
            @PathVariable int planId) {
        planService.deletePlan(userId, planId);
        return ResponseEntity.ok().build();
    }


    @Operation(summary = "내용 수정")
    @PatchMapping("/{userId}/edit/{planId}")
    public ResponseEntity<Void> editPlan(
        @PathVariable String userId,
        @PathVariable int planId,
        @RequestBody PlanUpdateRequest request
    ) {
        planService.editPlan(userId, planId, request);
        return ResponseEntity.ok().build();
    }




}



