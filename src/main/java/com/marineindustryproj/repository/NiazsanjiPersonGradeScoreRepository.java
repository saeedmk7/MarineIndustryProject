package com.marineindustryproj.repository;

import com.marineindustryproj.domain.NiazsanjiPersonGradeScore;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the NiazsanjiPersonGradeScore entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NiazsanjiPersonGradeScoreRepository extends JpaRepository<NiazsanjiPersonGradeScore, Long>, JpaSpecificationExecutor<NiazsanjiPersonGradeScore> {

}
