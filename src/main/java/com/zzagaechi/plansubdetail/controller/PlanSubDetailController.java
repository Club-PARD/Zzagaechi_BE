package com.zzagaechi.plansubdetail.controller;

import com.zzagaechi.plansubdetail.dto.request.PlanSubDetailBulkCreateRequest;
import com.zzagaechi.plansubdetail.service.PlanSubDetailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@Tag(name = "세부 작업의 상제정보 기입")
@RestController
@RequestMapping("/api/plansubdetail")
@RequiredArgsConstructor
public class PlanSubDetailController {
    private final PlanSubDetailService planSubDetailService;

    @Operation(
            summary = "세부 작업 상세 일괄 생성",
            description = """
            여러 개의 세부 작업 상세를 한 번에 생성합니다.
            
            Example:
            {
              "planSubId": 3,
              "details": [
                {
                  "content": "종이 구하기",
                  "date": "2024-01-26",
                  "startTime": "18:00",
                  "endTime": "18:00"
                },
                {
                  "content": "연필 구하기",
                  "date": "2024-01-26",
                  "startTime": "19:00",
                  "endTime": "19:30"
                },
                {
                  "content": "지우개 구하기",
                  "date": "2024-01-26",
                  "startTime": "20:00",
                  "endTime": "20:30"
                }
              ]
            }
            """)
    @PostMapping("/create")
    public ResponseEntity<Void> createDetails(@Valid @RequestBody PlanSubDetailBulkCreateRequest request) {
        planSubDetailService.createDetails(request);
        return ResponseEntity.status(201).build();
    }
}
