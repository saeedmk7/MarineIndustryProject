package com.marineindustryproj.repository;

import com.marineindustryproj.domain.TeachingApproach;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TeachingApproach entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TeachingApproachRepository extends JpaRepository<TeachingApproach, Long>, JpaSpecificationExecutor<TeachingApproach> {

}
