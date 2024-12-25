package com.zzagaechi.plan.entity;

import com.zzagaechi.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;
import java.time.LocalTime;

@Schema(description = "일정 엔티티 - 세분화 OFF")
@Entity
@Table(name = "plan")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Plan {

    @Schema(description = "일정 ID", example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;

    @Schema(description = "사용자 정보")
    @ManyToOne(fetch = FetchType.LAZY) //유저는 많은 일정을 가질 수 있음
    @JoinColumn(name = "uid", nullable = false)
    private User user;

    @Schema(description = "일정 제목", example = "프로젝트 회의")
    @Column(nullable = false)
    private String title;

    @Schema(description = "시작 날짜", example = "2024-01-01")
    @Column(nullable = false)
    private LocalDate startDate;
    //여기까진 필수

    @Schema(description = "종료 날짜", example = "2024-01-03")
    private LocalDate endDate;

    @Schema(description = "시작 시간 (종료 시간과 쌍으로 존재)", example = "09:00")
    private LocalTime startTime;

    @Schema(description = "종료 시간 (시작 시간과 쌍으로 존재)", example = "18:00")
    private LocalTime endTime;

    @Schema(description = "완료 여부", example = "false")
    @Column(nullable = false)
    private Boolean isCompleted = false;
}
