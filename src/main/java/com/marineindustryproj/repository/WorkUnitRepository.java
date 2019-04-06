package com.marineindustryproj.repository;

import com.marineindustryproj.domain.WorkUnit;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the WorkUnit entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WorkUnitRepository extends JpaRepository<WorkUnit, Long>, JpaSpecificationExecutor<WorkUnit> {

}
