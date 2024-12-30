package com.zzagaechi.view.toggle.service;

import com.zzagaechi.plan.entity.Plan;
import com.zzagaechi.plan.repository.PlanRepository;
import com.zzagaechi.plansubdetail.entity.PlanSubDetail;
import com.zzagaechi.plansubdetail.repository.PlanSubDetailRepository;
import com.zzagaechi.view.toggle.dto.ToggleRequestDto;
import com.zzagaechi.view.toggle.entity.PlanDateToggle;
import com.zzagaechi.view.toggle.repository.PlanDateToggleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ToggleService {
    private final PlanRepository planRepository;
    private final PlanSubDetailRepository planSubDetailRepository;
    private final PlanDateToggleRepository planDateToggleRepository;

    @Transactional
    public void toggleCompletion(ToggleRequestDto request, String userId, LocalDate date) {
        // Plan 토글 처리
        if (request.getPlanIds() != null && !request.getPlanIds().isEmpty()) {
            List<Plan> userPlans = planRepository.findAllByUserUidAndPlanIdIn(userId, request.getPlanIds());
            
            List<PlanDateToggle> toggles = new ArrayList<>();
            
            for (Plan plan : userPlans) {//plan을 userPlans 사이즈 만큼 하나씩
                PlanDateToggle toggle = planDateToggleRepository
                    .findByPlan_PlanIdAndDate(plan.getPlanId(), date) //있으면 일단 넘어가고
                    .orElseGet(() -> {
                        PlanDateToggle newToggle = PlanDateToggle.builder()//없으면 하나씩 만들어서
                            .plan(plan)
                            .date(date)
                            .isCompleted(false)
                            .build();
                        return planDateToggleRepository.save(newToggle);
                    });
                
                toggle.toggleComplete();//상태바꾸고
                planDateToggleRepository.save(toggle);//저장
            }
        }

        // PlanSubDetail 토글 처리
        if (request.getPlanSubDetailIds() != null && !request.getPlanSubDetailIds().isEmpty()) {
            List<PlanSubDetail> userPlanSubDetails = planSubDetailRepository
                .findAllByPlanSub_User_UidAndIdIn(userId, request.getPlanSubDetailIds());
            userPlanSubDetails.forEach(PlanSubDetail::toggleComplete);
            planSubDetailRepository.saveAll(userPlanSubDetails);
        }
    }
}
