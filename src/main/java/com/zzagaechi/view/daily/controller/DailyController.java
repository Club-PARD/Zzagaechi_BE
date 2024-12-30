package com.zzagaechi.view.daily.controller;

import com.zzagaechi.view.daily.dto.response.DailyResponse;
import com.zzagaechi.view.daily.service.DailyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
@Tag(name = "날짜별 일정조회")
@RestController
@RequestMapping("/daily")
@RequiredArgsConstructor
public class DailyController {
    private final DailyService dailyService;

    @Operation(summary = "일일 진행률 및 할 일 목록 조회",
            description = "특정 사용자의 특정 날짜 진행률과 모든 할 일 목록(일정, 세부 작업)을 조회")
    @GetMapping("/{userId}/{date}")
    public ResponseEntity<DailyResponse> getDaily(
            @PathVariable String userId,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(dailyService.getDaily(userId, date)); //uid와 날짜를 받으면
    }
}