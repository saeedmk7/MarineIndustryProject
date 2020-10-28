package com.marineindustryproj.repository;

import com.marineindustryproj.domain.MonitorLearningProcessGrade;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MonitorLearningProcessGrade entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MonitorLearningProcessGradeRepository extends JpaRepository<MonitorLearningProcessGrade, Long>, JpaSpecificationExecutor<MonitorLearningProcessGrade> {

}
