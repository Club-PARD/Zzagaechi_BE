package com.zzagaechi.plan.controller;
import com.zzagaechi.plan.dto.request.PlanCreateReq;
import com.zzagaechi.plan.dto.request.ToggleListRequest;
import com.zzagaechi.plan.dto.request.PlanUpdateRequest;
import com.zzagaechi.plan.dto.response.DateFindResponse;
import com.zzagaechi.plan.service.PlanService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@Tag(name = "세분화하지 않는 plan(마트가기)")
@RestController
@RequestMapping("/plan")
@RequiredArgsConstructor
public class PlanController {
    
    private final PlanService planService;



    @Operation(summary = "세분화가 없는 일정 생성(Date)")
    @PostMapping("/{userId}/date")
    public ResponseEntity<Integer> createWithNothing(
            @PathVariable String userId,
            @Valid @RequestBody PlanCreateReq.DateDto req) {
        int planId = planService.createSchedule(userId, req);
        return ResponseEntity.status(201).body(planId);
    }

    @Operation(summary = "세분화가 없는 일정 생성(Date+Time)")
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

    @Operation(summary = "일정 완료 여부 일괄 토글")
    @PatchMapping("/{userId}/toggle")
    public ResponseEntity<Void> toggleCompleteList(
            @PathVariable String userId, @Valid @RequestBody ToggleListRequest request) {
        planService.toggleCompleteList(userId, request.getPlanIds());
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






    /*@Operation(summary = "특정 날짜의 일정 목록 조회",
            description = "지정된 날짜의 모든 일정을 반환합니다. 시간이 있는 일정은 시간순으로 정렬되어 먼저 나오고, 시간이 없는 일정이 그 뒤에 표시됩니다.")
    @GetMapping("/date/{date}")
    public ResponseEntity<List<DateFindResponse>> getPlansByDate(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(planService.getPlansByDate(date));
    }
*/




}



