package com.marineindustryproj.repository;

import com.marineindustryproj.domain.EmploymentType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EmploymentType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EmploymentTypeRepository extends JpaRepository<EmploymentType, Long>, JpaSpecificationExecutor<EmploymentType> {

}
