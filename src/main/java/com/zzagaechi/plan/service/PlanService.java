package com.zzagaechi.plan.service;

import com.zzagaechi.plan.dto.request.PlanUpdateRequest;
import com.zzagaechi.plan.dto.response.DateFindResponse;
import com.zzagaechi.plan.entity.Plan;
import com.zzagaechi.plan.dto.request.PlanCreateReq;
import com.zzagaechi.plan.repository.PlanRepository;
import com.zzagaechi.user.entity.User;
import com.zzagaechi.user.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanService {

    private final PlanRepository planRepo;
    private final UserRepo userRepo;


    @Transactional
    public int createSchedule(String userId, PlanCreateReq.DateDto req) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        Plan plan = Plan.builder()
                .user(user)
                .title(req.getTitle())
                .doDate(req.getDoDate())
                .build();
        planRepo.save(plan);
        return plan.getPlanId();
    } //plan/날짜만 들어왔을 떄 처리

    @Transactional
    public int createSchedule(String userId, PlanCreateReq.DateTimeDto req) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        Plan plan = Plan.builder()
                .user(user)
                .title(req.getTitle())
                .doDate(req.getDoDate())
                .startTime(req.getStartTime())
                .endTime(req.getEndTime())
                .build();
        plan = planRepo.save(plan);
        return plan.getPlanId();
    }//plan 날짜와 시간이 같이 들어왔을 떄

    @Transactional
    public void deletePlan(String userId, int planId) {//Id로 삭제 cascade
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        Plan plan = planRepo.findById(planId)
                .orElseThrow(() -> new IllegalArgumentException("Plan not found with id: " + planId));

        planRepo.delete(plan);
    }

    @Transactional
    public void toggleCompleteList(String userId, List<Integer> planIds) {
        if (planIds == null || planIds.isEmpty()) {
            return;
        }

        planIds.forEach(planId -> {
            Plan plan = planRepo.findById(planId)
                    .orElseThrow(() -> new IllegalArgumentException("Plan not found with id: " + planId));

            if (!plan.getUser().getUid().equals(userId)) {
                throw new RuntimeException("Unauthorized access");
            }

            plan.toggleComplete();
        });
    }

    @Transactional(readOnly = true)
    public List<DateFindResponse> getPlansByDate(String userId, LocalDate date) {
        // 시간이 있는 일정 (시간순 정렬)
        List<Plan> timeSchedules = planRepo.findAllByUser_UidAndDoDateAndStartTimeIsNotNullOrderByStartTimeAsc(userId, date);
        // 시간이 없는 일정
        List<Plan> noTimeSchedules = planRepo.findAllByUser_UidAndDoDateAndStartTimeIsNull(userId, date);

        List<DateFindResponse> result = new ArrayList<>(); //여기서 합쳐서 plan리스트 만들고 daily에서 plansubdetail과 합쳐서 return

        // 시간이 있는 일정들을 먼저 추가
        result.addAll(timeSchedules.stream()
                .map(DateFindResponse::from)
                .toList());

        // 시간이 없는 일정들을 뒤에 추가
        result.addAll(noTimeSchedules.stream()
                .map(DateFindResponse::from)
                .toList());

        return result;
    }


    @Transactional
    public void editPlan(String userId, int planId, PlanUpdateRequest request) {
        Plan plan = planRepo.findById(planId)
                .orElseThrow(() -> new RuntimeException("Plan not found"));

        if (!plan.getUser().getUid().equals(userId)) {
            throw new RuntimeException("Unauthorized access");
        }

        plan.update(
                request.getDoDate(),
                request.getStartTime(),
                request.getEndTime(),
                request.getTitle()
        );


    }
}
