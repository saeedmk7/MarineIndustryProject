package com.marineindustryproj.repository;

import com.marineindustryproj.domain.PriorityCriteria;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PriorityCriteria entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PriorityCriteriaRepository extends JpaRepository<PriorityCriteria, Long>, JpaSpecificationExecutor<PriorityCriteria> {

}
