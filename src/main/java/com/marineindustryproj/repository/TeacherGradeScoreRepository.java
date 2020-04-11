package com.marineindustryproj.repository;

import com.marineindustryproj.domain.TeacherGradeScore;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TeacherGradeScore entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TeacherGradeScoreRepository extends JpaRepository<TeacherGradeScore, Long>, JpaSpecificationExecutor<TeacherGradeScore> {

}
