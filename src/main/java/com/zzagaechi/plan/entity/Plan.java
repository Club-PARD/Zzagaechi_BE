package com.zzagaechi.plan.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;
import java.time.LocalTime;
import com.zzagaechi.user.entity.User;

@Schema(description = "일정 엔티티 - 세분화 OFF")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "plan")
public class Plan {

    @Schema(description = "일정 ID", example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int planId;

    @Schema(description = "사용자 정보")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uid", nullable = false)
    private User user;

    @Schema(description = "일정 제목", example = "프로젝트 회의")
    @Column(nullable = false)
    private String plantitle;

    @Schema(description = "시작 날짜", example = "2024-01-01")
    @Column(nullable = false)
    private LocalDate startDate;

    @Schema(description = "종료 날짜", example = "2024-01-02")
    @Column(nullable = false)
    private LocalDate endDate;

    @Schema(description = "시작 시간", example = "09:00")
    private LocalTime startTime;

    //시작/종료는 선택
    @Schema(description = "완료 여부", example = "false")
    private boolean isCompleted = false;

    public void toggleComplete() {
        this.isCompleted = !this.isCompleted;
    }//토글 변경 method

    public void update(LocalDate startDate, LocalDate endDate, LocalTime startTime,  String plantitle) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTime = startTime;
        this.plantitle = plantitle;
    }//update

}
