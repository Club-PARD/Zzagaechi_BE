package com.zzagaechi.plansubdetail.service;

import com.zzagaechi.plansubdetail.dto.request.PlanSubDetailListeRequest;
import com.zzagaechi.plansubdetail.dto.request.UpdateRequest;
import com.zzagaechi.plansubdetail.dto.response.PlanSubDetailCreateResponse;
import com.zzagaechi.plansubdetail.dto.response.PlanSubDetailListResponse;
import com.zzagaechi.plansubdetail.dto.response.PlanSubDetailsByPlanSubResponse;
import com.zzagaechi.plansubdetail.entity.PlanSubDetail;
import com.zzagaechi.plansubdetail.repository.PlanSubDetailRepository;
import com.zzagaechi.plansub.entity.PlanSub;
import com.zzagaechi.plansub.repository.PlanSubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlanSubDetailService {
    private final PlanSubDetailRepository planSubDetailRepository;
    private final PlanSubRepository planSubRepository;

    @Transactional
    public PlanSubDetailCreateResponse createDetails(String userId, int planSubId, PlanSubDetailListeRequest request) {
        PlanSub planSub = planSubRepository.findById(planSubId)
                .orElseThrow(() -> new RuntimeException("PlanSub not found"));

        // 권한 검증: 해당 PlanSub가 요청한 사용자의 것인지 확인
        if (!planSub.getUser().getUid().equals(userId)) {
            throw new RuntimeException("Unauthorized access");
        }

        List<PlanSubDetail> savedDetails = request.getDetails().stream()
                .map(detail -> PlanSubDetail.builder()
                        .planSub(planSub)
                        .content(detail.getContent())
                        .date(detail.getDate())
                        .startTime(detail.getStartTime())
                        .endTime(detail.getEndTime())
                        .isCompleted(false)
                        .build())
                .map(detail -> planSubDetailRepository.save(detail))
                .toList();

        List<PlanSubDetailCreateResponse.DetailResponse> detailResponses = savedDetails.stream()
                .map(detail -> PlanSubDetailCreateResponse.DetailResponse.builder()
                        .detailId(detail.getId())
                        .content(detail.getContent())
                        .date(detail.getDate())
                        .startTime(detail.getStartTime())
                        .endTime(detail.getEndTime())
                        .build())
                .toList();

        return PlanSubDetailCreateResponse.builder()
                .plansubId(planSubId)
                .details(detailResponses)
                .build();
    }


    @Transactional(readOnly = true)//오늘 할일 리스트
    public List<PlanSubDetailListResponse> getDetailsByDate(String userId, LocalDate date) {
        return planSubDetailRepository.findAllByPlanSub_User_UidAndDateOrderByStartTimeAsc(userId, date)
                .stream()
                .map(PlanSubDetailListResponse::from)
                .toList();
    }


    @Transactional
    public void deleteDetail(String userId, int detailId) {
        PlanSubDetail detail = planSubDetailRepository.findById(detailId)
                .orElseThrow(() -> new IllegalArgumentException("PlanSubDetail not found with id: " + detailId));

        // 권한 체크: 해당 유저의 세부일정인지 확인
        if (!detail.getPlanSub().getUser().getUid().equals(userId)) {
            throw new RuntimeException("Unauthorized access");
        }

        planSubDetailRepository.delete(detail);
    }


    //토글 상태변경
    @Transactional
    public void toggleCompleteList(String userId, List<Integer> detailIds) {
        if (detailIds == null || detailIds.isEmpty()) {
            return;//아무것도 없으면 반환
        }

        detailIds.forEach(detailId -> {
            PlanSubDetail detail = planSubDetailRepository.findById(detailId)
                    .orElseThrow(() -> new IllegalArgumentException("PlanSubDetail not found with id: " + detailId));

            // 권한 체크: 해당 유저의 세부일정인지 확인
            if (!detail.getPlanSub().getUser().getUid().equals(userId)) {
                throw new RuntimeException("Unauthorized access");
            }

            detail.toggleComplete();
        });
    }

    @Transactional
    public void updateDetail(String userId, int detailId, UpdateRequest request) {
        PlanSubDetail detail = planSubDetailRepository.findById(detailId)
                .orElseThrow(() -> new IllegalArgumentException("PlanSubDetail not found with id: " + detailId));

        // 권한 체크
        if (!detail.getPlanSub().getUser().getUid().equals(userId)) {
            throw new RuntimeException("Unauthorized access");
        }

        detail.update(
                request.getContent(),
                request.getDate(),
                request.getStartTime(),
                request.getEndTime()
        );
    }

    @Transactional(readOnly = true)
    public PlanSubDetailsByPlanSubResponse getDetailsByDetailId(String userId, int detailId) {
        // 요청된 PlanSubDetail 조회
        PlanSubDetail detail = planSubDetailRepository.findById(detailId)
                .orElseThrow(() -> new IllegalArgumentException("PlanSubDetail not found with id: " + detailId));

        // 권한 체크
        if (!detail.getPlanSub().getUser().getUid().equals(userId)) {
            throw new RuntimeException("Unauthorized access");
        }

        // 같은 PlanSub를 가진 모든 PlanSubDetail 조회
        PlanSub planSub = detail.getPlanSub();
        List<PlanSubDetail> details = planSubDetailRepository
                .findAllByPlanSubIdOrderByDateAscStartTimeAsc(planSub.getId());

        return PlanSubDetailsByPlanSubResponse.of(planSub, details);
    }


}
