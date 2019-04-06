package com.marineindustryproj.repository;

import com.marineindustryproj.domain.TeachApproach;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TeachApproach entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TeachApproachRepository extends JpaRepository<TeachApproach, Long>, JpaSpecificationExecutor<TeachApproach> {

}
