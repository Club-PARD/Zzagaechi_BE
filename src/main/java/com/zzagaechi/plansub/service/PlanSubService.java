package com.zzagaechi.plansub.service;

import com.zzagaechi.plansub.dto.request.PlanSubCreateRequest.*;
import com.zzagaechi.plansub.entity.PlanSub;
import com.zzagaechi.plansub.repository.PlanSubRepository;
import com.zzagaechi.user.entity.User;
import com.zzagaechi.user.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlanSubService {
    private final PlanSubRepository planSubRepository;
    private final UserRepo userRepository;

    @Transactional
    public void createPlanSub(CreateEndDateWithEndTimeDto dto, String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        PlanSub planSub = PlanSub.builder()
                .user(user)
                .title(dto.getTitle())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .endTime(dto.getEndTime())
                .isCompleted(false)
                .build();
        planSubRepository.save(planSub);
    }

    @Transactional
    public void createPlanSub(CreateEndDateDto dto, String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        PlanSub planSub = PlanSub.builder()
                .user(user)
                .title(dto.getTitle())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .isCompleted(false)
                .build();
        planSubRepository.save(planSub);
    }

    @Transactional
    public void createPlanSub(CreateEndTimeDto dto, String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        PlanSub planSub = PlanSub.builder()
                .user(user)
                .title(dto.getTitle())
                .startDate(dto.getStartDate())
                .endTime(dto.getEndTime())
                .isCompleted(false)
                .build();
        planSubRepository.save(planSub);
    }

    @Transactional
    public void createPlanSub(CreateNothingDto dto, String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        PlanSub planSub = PlanSub.builder()
                .user(user)
                .title(dto.getTitle())
                .startDate(dto.getStartDate())
                .isCompleted(false)
                .build();
        planSubRepository.save(planSub);
    }
}
