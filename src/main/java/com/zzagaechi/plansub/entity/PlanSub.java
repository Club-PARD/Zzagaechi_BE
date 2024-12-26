package com.zzagaechi.plansub.entity;

import com.zzagaechi.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Schema(description = "세부 작업")
@Entity
@Table(name = "plan_sub")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanSub {
    @Schema(description = "세부 작업 ID")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Schema(description = "사용자 정보")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uid", nullable = false)
    private User user;

    @Schema(description = "제목")
    @Column(nullable = false)
    private String title;

    @Schema(description = "시작 날짜")
    @Column(nullable = false)
    private LocalDate startDate;

    @Schema(description = "종료 날짜")
    @Column
    private LocalDate endDate;

    @Schema(description = "종료 시간")
    @Column
    private LocalTime endTime;

    @Schema(description = "완료 여부")
    @Column(nullable = false)
    private boolean isCompleted = false;
}
