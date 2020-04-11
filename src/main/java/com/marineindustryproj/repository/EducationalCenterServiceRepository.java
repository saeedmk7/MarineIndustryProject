package com.marineindustryproj.repository;

import com.marineindustryproj.domain.EducationalCenterService;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EducationalCenterService entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EducationalCenterServiceRepository extends JpaRepository<EducationalCenterService, Long>, JpaSpecificationExecutor<EducationalCenterService> {

}
