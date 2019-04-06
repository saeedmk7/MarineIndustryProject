package com.marineindustryproj.repository;

import com.marineindustryproj.domain.WorkIndustry;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the WorkIndustry entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WorkIndustryRepository extends JpaRepository<WorkIndustry, Long>, JpaSpecificationExecutor<WorkIndustry> {

}
