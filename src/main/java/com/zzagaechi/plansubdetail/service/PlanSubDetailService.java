package com.zzagaechi.plansubdetail.service;

import com.zzagaechi.plansubdetail.dto.request.PlanSubDetailCreateRequest.CreateRequest;
import com.zzagaechi.plansubdetail.dto.request.PlanSubDetailCreateRequest.TodoDetailDto;
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
    public void createDetails(CreateRequest request) {
        PlanSub planSub = planSubRepository.findById(request.getPlanSubId())
                .orElseThrow(() -> new RuntimeException("PlanSub not found"));
        
        List<String> subtasks = planSub.getSubtasks();
        List<TodoDetailDto> todoDetails = request.getTodoDetails();
        
        if (subtasks.size() != todoDetails.size()) {
            throw new RuntimeException("할일 목록의 개수와 시간 정보의 개수가 일치하지 않습니다.");
        }

        List<PlanSubDetail> details = new ArrayList<>();
        for (int i = 0; i < subtasks.size(); i++) {
            TodoDetailDto timeInfo = todoDetails.get(i);
            PlanSubDetail detail = PlanSubDetail.builder()
                    .planSub(planSub)
                    .content(subtasks.get(i))
                    .date(timeInfo.getDate())
                    .startTime(timeInfo.getStartTime())
                    .endTime(timeInfo.getEndTime())
                    .isCompleted(false)
                    .build();
            details.add(detail);
        }
        
        planSubDetailRepository.saveAll(details);
    }
}
