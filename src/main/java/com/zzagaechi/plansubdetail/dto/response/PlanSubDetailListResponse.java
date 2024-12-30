package com.zzagaechi.plansubdetail.dto.response;

import com.zzagaechi.plansubdetail.entity.PlanSubDetail;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalTime;

@Getter
@Builder
public class PlanSubDetailListResponse {//오늘할일의 세분화목록 보내주도록 하는 dto
    private int detailId;
    private String plansubtitle;
    private String content;
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean isCompleted;



    public static PlanSubDetailListResponse from(PlanSubDetail detail) {
        return PlanSubDetailListResponse.builder()
                .detailId(detail.getId())
                .plansubtitle(detail.getPlanSub().getPlansubtitle()) //plansub의 title
                .content(detail.getContent())
                .startTime(detail.getStartTime())
                .endTime(detail.getEndTime())
                .isCompleted(detail.isCompleted())
                .build();
    }
}
