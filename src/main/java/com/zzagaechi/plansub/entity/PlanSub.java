package com.zzagaechi.plansub.entity;

import com.zzagaechi.plansubdetail.entity.PlanSubDetail;
import com.zzagaechi.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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
    private String plansubtitle;

    @Schema(description = "시작 날짜")
    @Column(nullable = false)
    private LocalDate startDate;

    @Schema(description = "종료 날짜")
    @Column(nullable = false)
    private LocalDate endDate;

    @Schema(description = "종료 시간")
    @Column
    private LocalTime deadline;

    @Schema(description = "완료 여부")
    private boolean isCompleted = false;

    @OneToMany(mappedBy = "planSub", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PlanSubDetail> planSubDetails = new ArrayList<>();
}
