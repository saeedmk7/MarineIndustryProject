package com.marineindustryproj.repository;

import com.marineindustryproj.domain.LevelFourScore;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the LevelFourScore entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LevelFourScoreRepository extends JpaRepository<LevelFourScore, Long>, JpaSpecificationExecutor<LevelFourScore> {

}
