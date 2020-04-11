package com.marineindustryproj.repository;

import com.marineindustryproj.domain.EducationalCenterGradeScore;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EducationalCenterGradeScore entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EducationalCenterGradeScoreRepository extends JpaRepository<EducationalCenterGradeScore, Long>, JpaSpecificationExecutor<EducationalCenterGradeScore> {

}
