package com.marineindustryproj.repository;

import com.marineindustryproj.domain.ToolsAndFacility;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ToolsAndFacility entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ToolsAndFacilityRepository extends JpaRepository<ToolsAndFacility, Long>, JpaSpecificationExecutor<ToolsAndFacility> {

}
