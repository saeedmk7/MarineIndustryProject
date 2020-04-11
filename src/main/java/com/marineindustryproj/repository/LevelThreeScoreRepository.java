package com.marineindustryproj.repository;

import com.marineindustryproj.domain.LevelThreeScore;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the LevelThreeScore entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LevelThreeScoreRepository extends JpaRepository<LevelThreeScore, Long>, JpaSpecificationExecutor<LevelThreeScore> {

}
