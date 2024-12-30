package com.zzagaechi.view.toggle.controller;

import com.zzagaechi.view.toggle.dto.ToggleRequestDto;
import com.zzagaechi.view.toggle.service.ToggleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "성공여부 전체 체크", description = "완료 상태 토글")
@RestController
@RequestMapping("/toggle")
@RequiredArgsConstructor
public class ToggleController {
    private final ToggleService toggleService;

    @Operation(summary = "완료 상태 토글", description = "Plan 또는 PlanSubDetail의 완료 상태를 토글합니다.")
    @PatchMapping("/{userId}")
    public ResponseEntity<Void> toggleCompletion(
            @PathVariable String userId,
            @RequestBody ToggleRequestDto request) {
        toggleService.toggleCompletion(request, userId);
        return ResponseEntity.ok().build();
    }
}
