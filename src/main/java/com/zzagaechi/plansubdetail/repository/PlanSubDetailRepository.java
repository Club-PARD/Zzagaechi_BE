package com.zzagaechi.plansubdetail.repository;

import com.zzagaechi.plansubdetail.entity.PlanSubDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PlanSubDetailRepository extends JpaRepository<PlanSubDetail, Integer> {
    List<PlanSubDetail> findAllByPlanSub_User_UidAndDateOrderByStartTimeAsc(String uid, LocalDate date);
    // 오늘 나의 모든 세부 일정을 시간순으로 보기

    List<PlanSubDetail> findAllByPlanSubIdOrderByDateAscStartTimeAsc(int planSubId);
    //특정 세부 작업의 모든 일정을 날짜/시간순으로 보기

    List<PlanSubDetail> findAllByPlanSub_User_UidAndIdIn(String userId, List<Integer> ids);


}
