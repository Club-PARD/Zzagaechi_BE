package com.zzagaechi.view.toggle.controller;

import com.zzagaechi.view.toggle.dto.ToggleRequestDto;
import com.zzagaechi.view.toggle.service.ToggleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Tag(name = "토글", description = "완료 상태 토글")
@RestController
@RequestMapping("/toggle")
@RequiredArgsConstructor
public class ToggleController {
    private final ToggleService toggleService;


    @Operation(summary = "일정 날짜별 완료 상태 토글", description = "특정 날짜의 일정 완료 상태를 토글합니다.")
    @PatchMapping("/{userId}/{date}")
    public ResponseEntity<Void> toggleDateCompletion(
            @PathVariable String userId,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
            @RequestBody ToggleRequestDto request) {
        toggleService.toggleCompletion(request, userId, date);
        return ResponseEntity.ok().build();
    }
}
