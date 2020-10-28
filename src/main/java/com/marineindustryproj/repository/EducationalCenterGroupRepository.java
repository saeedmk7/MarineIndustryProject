package com.marineindustryproj.repository;

import com.marineindustryproj.domain.EducationalCenterGroup;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EducationalCenterGroup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EducationalCenterGroupRepository extends JpaRepository<EducationalCenterGroup, Long>, JpaSpecificationExecutor<EducationalCenterGroup> {

}
