package com.marineindustryproj.repository;

import com.marineindustryproj.domain.TeachType;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TeachType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TeachTypeRepository extends JpaRepository<TeachType, Long>, JpaSpecificationExecutor<TeachType> {

}
