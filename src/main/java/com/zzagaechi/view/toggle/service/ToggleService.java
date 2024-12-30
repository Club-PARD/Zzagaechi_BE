package com.zzagaechi.view.toggle.service;

import com.zzagaechi.plan.entity.Plan;
import com.zzagaechi.plan.repository.PlanRepository;
import com.zzagaechi.plansubdetail.entity.PlanSubDetail;
import com.zzagaechi.plansubdetail.repository.PlanSubDetailRepository;
import com.zzagaechi.view.toggle.dto.ToggleRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ToggleService {
    private final PlanRepository planRepository;
    private final PlanSubDetailRepository planSubDetailRepository;

    @Transactional
    public void toggleCompletion(ToggleRequestDto request, String userId) {
        if (request.getPlanIds() != null && !request.getPlanIds().isEmpty()) {
            List<Plan> userPlans = planRepository.findAllByUserUidAndPlanIdIn(userId, request.getPlanIds());
            userPlans.forEach(plan -> plan.toggleComplete());
            planRepository.saveAll(userPlans);
        }


        if (request.getPlanSubDetailIds() != null && !request.getPlanSubDetailIds().isEmpty()) {
            List<PlanSubDetail> userPlanSubDetails = planSubDetailRepository
                .findAllByPlanSub_User_UidAndIdIn(userId, request.getPlanSubDetailIds());
            userPlanSubDetails.forEach(detail -> detail.toggleComplete());
            planSubDetailRepository.saveAll(userPlanSubDetails);
        }
    }
}
