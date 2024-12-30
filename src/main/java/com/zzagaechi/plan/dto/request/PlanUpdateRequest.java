package com.zzagaechi.plan.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@NoArgsConstructor
public class PlanUpdateRequest {
    private LocalDate doDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private String title;
}