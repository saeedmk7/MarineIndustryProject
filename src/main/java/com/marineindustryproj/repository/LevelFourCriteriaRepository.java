package com.marineindustryproj.repository;

import com.marineindustryproj.domain.LevelFourCriteria;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the LevelFourCriteria entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LevelFourCriteriaRepository extends JpaRepository<LevelFourCriteria, Long>, JpaSpecificationExecutor<LevelFourCriteria> {

}
