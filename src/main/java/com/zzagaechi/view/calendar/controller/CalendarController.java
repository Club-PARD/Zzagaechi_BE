package com.zzagaechi.view.calendar.controller;

import com.zzagaechi.view.calendar.dto.response.CalendarResponse;
import com.zzagaechi.view.calendar.service.CalendarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.YearMonth;

@Tag(name = "월별 일정 조회", description = "캘린더")
@RestController
@RequestMapping("/calender")
@RequiredArgsConstructor
public class CalendarController {
    private final CalendarService calendarService;
    @Operation(summary = "월별 일정 조회", description = "특정 월의 모든 일정 조회")
    @GetMapping("/{userId}/{yearMonth}")
    public ResponseEntity<CalendarResponse> getCalendarEvents(
            @PathVariable String userId,
            @PathVariable @DateTimeFormat
            (pattern = "yyyy-MM") YearMonth yearMonth)//YearMonth타입으로 변환
    {
        return ResponseEntity.ok(calendarService.getCalendar(userId,yearMonth)); //uid랑 yearmonth를 보내면
    }





}