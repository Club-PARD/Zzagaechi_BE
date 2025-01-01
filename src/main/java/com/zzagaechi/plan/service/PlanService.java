package com.zzagaechi.plan.service;

import com.zzagaechi.plan.dto.request.PlanUpdateRequest;
import com.zzagaechi.plan.dto.response.DateFindResponse;
import com.zzagaechi.plan.entity.Plan;
import com.zzagaechi.view.toggle.entity.PlanDateToggle;
import com.zzagaechi.view.toggle.repository.PlanDateToggleRepository;
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
import java.util.Map;
import java.util.Optional;
import static java.util.stream.Collectors.toMap;

@Service
@RequiredArgsConstructor
public class PlanService {

    private final PlanRepository planRepo;
    private final UserRepo userRepo;
    private final PlanDateToggleRepository planDateToggleRepository;

    @Transactional
    public int createSchedule(String userId, PlanCreateReq.DateDto req) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        Plan plan = Plan.builder()
                .user(user)
                .plantitle(req.getPlantitle())
                .startDate(req.getStartDate())
                .endDate(req.getEndDate())
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
                .plantitle(req.getPlantitle())
                .startDate(req.getStartDate())
                .endDate(req.getEndDate())
                .startTime(req.getStartTime())
                .build();
        plan = planRepo.save(plan);
        return plan.getPlanId();
    }//plan 날짜와 시간이 같이 들어왔을 떄



    @Transactional
    public void deletePlan(String userId, int planId) {
        // 1. Plan 조회
        Plan plan = planRepo.findById(planId)
                .orElseThrow(() -> new IllegalArgumentException("Plan not found with id: " + planId));

        // 2. 권한 체크 추가
        if (!plan.getUser().getUid().equals(userId)) {
            throw new RuntimeException("Unauthorized access: This plan belongs to another user");
        }

        // 3. 관련된 plan_date_toggle 데이터 삭제
        planDateToggleRepository.deleteAllByPlan_PlanId(planId);

        // 4. plan 삭제
        planRepo.delete(plan);
    }



    @Transactional(readOnly = true)//daily에서 사용
    public List<DateFindResponse> getPlansByDate(String userId, LocalDate date) {
        // 시간이 있는 일정 
        //ex) date = 2023-06-01을 받으면
        //시작일이 6/1일보다 같거나 작으면서 종료일이 6/1일보다 같거나 큰 일정을
        List<Plan> timeSchedules = planRepo.findAllByUser_UidAndStartDateLessThanEqualAndEndDateGreaterThanEqualAndStartTimeIsNotNullOrderByStartTimeAsc(
                userId, date, date);//jpa를 쓰기위해 date 2번 보내야함

        // 시간이 없는 일정 위와 동일하게
        List<Plan> noTimeSchedules = planRepo.findAllByUser_UidAndStartDateLessThanEqualAndEndDateGreaterThanEqualAndStartTimeIsNull(
                userId, date, date);////jpa를 쓰기위해 date 2번 보내야함

        // 모든 일정의 ID 목록 만들고
        List<Integer> planIds = new ArrayList<>();//일단 빈 리스트를 만들고
        planIds.addAll(timeSchedules.stream().map(plan -> plan.getPlanId()).toList());
        planIds.addAll(noTimeSchedules.stream().map(plan -> plan.getPlanId()).toList());


        // 해당 날짜의 토글 상태 조회
        List<PlanDateToggle> dateToggles = planDateToggleRepository.findAllByPlan_PlanIdInAndDate(planIds, date);
        List<DateFindResponse> result = new ArrayList<>();

       // 시간이 있는 일정들을 먼저 추가
        for (Plan plan : timeSchedules) {
            Optional<PlanDateToggle> toggle = dateToggles.stream()
                    .filter(t -> t.getPlan().getPlanId() == plan.getPlanId())
                    //토글의 planId가 현재 보고 있는 plan의 Id와 같은 것만 남겨줘
                    .findFirst();
            result.add(DateFindResponse.from(plan, toggle));
        }//.filter는 중간연산 .findFist같은 최종연산 무조건 필요

       // 시간이 없는 일정들을 뒤에 추가
        for (Plan plan : noTimeSchedules) {
            Optional<PlanDateToggle> toggle = dateToggles.stream()
                    .filter(t -> t.getPlan().getPlanId() == plan.getPlanId())
                    .findFirst();
            result.add(DateFindResponse.from(plan, toggle));
        }

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
                request.getStartDate(),
                request.getEndDate(),
                request.getStartTime(),
                request.getTitle()
        );
    }
}
