package com.marineindustryproj.repository;

import com.marineindustryproj.domain.LevelThreeCriteriaGroup;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the LevelThreeCriteriaGroup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LevelThreeCriteriaGroupRepository extends JpaRepository<LevelThreeCriteriaGroup, Long>, JpaSpecificationExecutor<LevelThreeCriteriaGroup> {

}
