package com.marineindustryproj.repository;

import com.marineindustryproj.domain.Criterion;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Criterion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CriterionRepository extends JpaRepository<Criterion, Long>, JpaSpecificationExecutor<Criterion> {

}
