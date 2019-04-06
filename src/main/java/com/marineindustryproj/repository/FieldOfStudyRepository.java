package com.marineindustryproj.repository;

import com.marineindustryproj.domain.FieldOfStudy;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the FieldOfStudy entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FieldOfStudyRepository extends JpaRepository<FieldOfStudy, Long>, JpaSpecificationExecutor<FieldOfStudy> {

}
