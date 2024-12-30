package com.zzagaechi.plansub.service;

import com.zzagaechi.plansub.entity.PlanSub;
import com.zzagaechi.plansub.dto.request.PlanSubCreateRequest;
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
    public int createPlanSub( String userId, PlanSubCreateRequest.CreateTimeDto dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        PlanSub planSub = PlanSub.builder()
                .user(user)
                .plansubtitle(dto.getPlansubtitle())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .deadline(dto.getDeadline())
                .isCompleted(false)
                .build();
        planSubRepository.save(planSub);
        return planSub.getId();
    }//종료시간이 있을떄 create

    @Transactional
    public int createPlanSub(String userId, PlanSubCreateRequest.CreateDto dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        PlanSub planSub = PlanSub.builder()
                .user(user)
                .plansubtitle(dto.getPlansubtitle())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .isCompleted(false)
                .build();
        planSubRepository.save(planSub);
        return planSub.getId();
    }//종료시간이 없을떄 create

    @Transactional
    public void deletePlanSub(String userId, int planSubId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        PlanSub planSub = planSubRepository.findById(planSubId)
                .orElseThrow(() -> new IllegalArgumentException("PlanSub not found with id: " + planSubId));

        if (!planSub.getUser().getUid().equals(userId)) {
            throw new IllegalArgumentException("User does not have permission to delete this plan sub");
        }

        // cascade = CascadeType.ALL과 orphanRemoval = true 설정으로 인해
        // 연관된 PlanSubDetail들도 자동으로 삭제됩니다
        planSubRepository.delete(planSub);
    }







}


