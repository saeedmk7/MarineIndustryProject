package com.marineindustryproj.repository;

import com.marineindustryproj.domain.Goal;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Goal entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GoalRepository extends JpaRepository<Goal, Long>, JpaSpecificationExecutor<Goal> {

}
