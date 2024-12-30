package com.zzagaechi.view.toggle.entity;

import com.zzagaechi.plan.entity.Plan;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Schema(description = "일정 날짜별 완료 상태 엔티티")
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "plan_date_toggle")
public class PlanDateToggle {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "plan_id", nullable = false)
    private Plan plan;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private boolean isCompleted;

    public void toggleComplete() {
        this.isCompleted = !this.isCompleted;
    }
}
