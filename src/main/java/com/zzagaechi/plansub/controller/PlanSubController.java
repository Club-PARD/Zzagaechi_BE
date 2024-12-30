package com.zzagaechi.plansub.controller;

import com.zzagaechi.plansub.service.PlanSubService;
import com.zzagaechi.plansub.dto.request.PlanSubCreateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "세분화하는 plan(시험공부하기)")
@RestController
@RequestMapping("/plansub")
@RequiredArgsConstructor
public class PlanSubController {
    private final PlanSubService planSubService;



    @Operation(summary = "일정 생성 종료시간(O)")
    @PostMapping("/{userId}/endtime")
    public ResponseEntity<Integer> createWithEndDateEndTime(
            @PathVariable String userId,
            @Valid @RequestBody PlanSubCreateRequest.CreateTimeDto req) {
        int planSubId = planSubService.createPlanSub(userId,req);
        return ResponseEntity.status(201).body(planSubId);
    }


    @Operation(summary = "일정 생성 종료시간(x)")
    @PostMapping("/{userId}")
    public ResponseEntity<Integer> createWithEndDate(
            @PathVariable String userId,
            @Valid @RequestBody PlanSubCreateRequest.CreateDto req) {
        int planSubId = planSubService.createPlanSub(userId,req);
        return ResponseEntity.status(201).body(planSubId);
    }

    @Operation(summary = "세부 작업 삭제 (하위 상세 작업도 함께 삭제)")
    @DeleteMapping("/{userId}/{planSubId}")
    public ResponseEntity<Void> deletePlanSub( @PathVariable String userId, @PathVariable int planSubId) {
        planSubService.deletePlanSub(userId, planSubId);
        return ResponseEntity.ok().build();
    }






}
