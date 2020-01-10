package com.marineindustryproj.repository;

import com.marineindustryproj.domain.PriorityCriteriaValue;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PriorityCriteriaValue entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PriorityCriteriaValueRepository extends JpaRepository<PriorityCriteriaValue, Long>, JpaSpecificationExecutor<PriorityCriteriaValue> {

}
