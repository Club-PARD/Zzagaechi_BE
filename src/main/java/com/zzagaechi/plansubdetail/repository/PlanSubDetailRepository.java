package com.zzagaechi.plansubdetail.repository;

import com.zzagaechi.plansubdetail.entity.PlanSubDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PlanSubDetailRepository extends JpaRepository<PlanSubDetail, Long> {

}
