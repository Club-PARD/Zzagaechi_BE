package com.zzagaechi.plansub.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zzagaechi.user.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
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
    private Long id;

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

    @Schema(description = "세부 작업 목록")
    @Column(columnDefinition = "TEXT")
    @Convert(converter = SubtasksConverter.class)
    private List<String> subtasks;

    @Schema(description = "완료 여부")
    @Column(nullable = false)
    private boolean isCompleted = false;
}

@Converter
class SubtasksConverter implements AttributeConverter<List<String>, String> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<String> attribute) {
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            return "[]";
        }
    }

    @Override
    public List<String> convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData, new TypeReference<List<String>>() {});
        } catch (JsonProcessingException e) {
            return List.of();
        }
    }
}
