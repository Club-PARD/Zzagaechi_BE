package com.zzagaechi.plansubdetail.entity;

import com.zzagaechi.plansub.entity.PlanSub;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Schema(description = "세부 작업 상세")
@Entity
@Table(name = "plan_sub_detail")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanSubDetail {
    @Schema(description = "세부 작업 상세 ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "세부 작업")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_sub_id", nullable = false)
    private PlanSub planSub;

    @Schema(description = "세부 작업 내용")
    @Column(nullable = false)
    private String content;

    @Schema(description = "날짜")
    @Column(nullable = false)
    private LocalDate date;

    @Schema(description = "시작 시간")
    @Column(nullable = false)
    private LocalTime startTime;

    @Schema(description = "종료 시간")
    @Column(nullable = false)
    private LocalTime endTime;

    @Schema(description = "완료 여부")
    @Column(nullable = false)
    private boolean isCompleted = false;
}
