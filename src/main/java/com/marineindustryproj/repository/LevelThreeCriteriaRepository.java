package com.marineindustryproj.repository;

import com.marineindustryproj.domain.LevelThreeCriteria;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the LevelThreeCriteria entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LevelThreeCriteriaRepository extends JpaRepository<LevelThreeCriteria, Long>, JpaSpecificationExecutor<LevelThreeCriteria> {

}
