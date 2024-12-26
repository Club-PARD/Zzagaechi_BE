package com.zzagaechi.plan.service;

import com.zzagaechi.plan.dto.request.PlanCreateReq;
import com.zzagaechi.plan.entity.Plan;
import com.zzagaechi.plan.repository.PlanRepository;
import com.zzagaechi.user.entity.User;
import com.zzagaechi.user.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanService {
    
    private final PlanRepository planRepo;
    private final UserRepo userRepo;

    @Transactional
    public void createSchedule(PlanCreateReq.CreateEndDateAndTimeDto req, String userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        Plan plan = Plan.builder()
                .user(user)
                .title(req.getTitle())
                .startDate(req.getStartDate())
                .endDate(req.getEndDate())
                .startTime(req.getStartTime())
                .endTime(req.getEndTime())
                .isCompleted(false)
                .build();
                
        planRepo.save(plan);
    }

    @Transactional
    public void createSchedule(PlanCreateReq.CreateEndDateDto req, String userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        Plan plan = Plan.builder()
                .user(user)
                .title(req.getTitle())
                .startDate(req.getStartDate())
                .endDate(req.getEndDate())
                .isCompleted(false)
                .build();
                
        planRepo.save(plan);
    }

    @Transactional
    public void createSchedule(PlanCreateReq.CreateTimeDto req, String userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        Plan plan = Plan.builder()
                .user(user)
                .title(req.getTitle())
                .startDate(req.getStartDate())
                .startTime(req.getStartTime())
                .endTime(req.getEndTime())
                .isCompleted(false)
                .build();
                
        planRepo.save(plan);
    }

    @Transactional
    public void createSchedule(PlanCreateReq.CreateNothingDto req, String userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        Plan plan = Plan.builder()
                .user(user)
                .title(req.getTitle())
                .startDate(req.getStartDate())
                .isCompleted(false)
                .build();
                
        planRepo.save(plan);
    }


}
