package com.zzagaechi.plansubdetail.service;

import com.zzagaechi.plansubdetail.dto.request.PlanSubDetailCreateRequest;
import com.zzagaechi.plansubdetail.entity.PlanSubDetail;
import com.zzagaechi.plansubdetail.repository.PlanSubDetailRepository;
import com.zzagaechi.plansub.entity.PlanSub;
import com.zzagaechi.plansub.repository.PlanSubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlanSubDetailService {
    
    private final PlanSubDetailRepository planSubDetailRepository;
    private final PlanSubRepository planSubRepository;

    @Transactional
    public void createDetail(PlanSubDetailCreateRequest request) {
        PlanSub planSub = planSubRepository.findById(request.getPlanSubId())
                .orElseThrow(() -> new RuntimeException("PlanSub not found"));

        PlanSubDetail detail = PlanSubDetail.builder()
                .planSub(planSub)
                .content(request.getContent())
                .date(request.getDate())
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .isCompleted(false)
                .build();

        planSubDetailRepository.save(detail);
    }
}
