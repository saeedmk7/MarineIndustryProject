package com.marineindustryproj.repository;

import com.marineindustryproj.domain.WorkGroup;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the WorkGroup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WorkGroupRepository extends JpaRepository<WorkGroup, Long>, JpaSpecificationExecutor<WorkGroup> {

}
