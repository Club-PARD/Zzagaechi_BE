package com.zzagaechi.plansubdetail.service;

import com.zzagaechi.plansubdetail.dto.request.PlanSubDetailBulkCreateRequest;
import com.zzagaechi.plansubdetail.entity.PlanSubDetail;
import com.zzagaechi.plansubdetail.repository.PlanSubDetailRepository;
import com.zzagaechi.plansub.entity.PlanSub;
import com.zzagaechi.plansub.repository.PlanSubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlanSubDetailService {
    private final PlanSubDetailRepository planSubDetailRepository;
    private final PlanSubRepository planSubRepository;

    @Transactional
    public void createDetails(PlanSubDetailBulkCreateRequest request) {
        PlanSub planSub = planSubRepository.findById(request.getPlanSubId())
                .orElseThrow(() -> new RuntimeException("PlanSub not found"));
            
        List<PlanSubDetail> details = request.getDetails().stream()
                .map(detail -> PlanSubDetail.builder()
                        .planSub(planSub)
                        .content(detail.getContent())
                        .date(detail.getDate())
                        .startTime(detail.getStartTime())
                        .endTime(detail.getEndTime())
                        .isCompleted(false)
                        .build())
                .collect(Collectors.toList());
            
        planSubDetailRepository.saveAll(details);
    }


}
