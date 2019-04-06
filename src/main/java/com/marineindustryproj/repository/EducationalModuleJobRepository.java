package com.marineindustryproj.repository;

import com.marineindustryproj.domain.EducationalModuleJob;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EducationalModuleJob entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EducationalModuleJobRepository extends JpaRepository<EducationalModuleJob, Long>, JpaSpecificationExecutor<EducationalModuleJob> {

}
