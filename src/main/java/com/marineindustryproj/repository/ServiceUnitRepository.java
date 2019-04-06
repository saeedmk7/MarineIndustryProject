package com.marineindustryproj.repository;

import com.marineindustryproj.domain.ServiceUnit;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ServiceUnit entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ServiceUnitRepository extends JpaRepository<ServiceUnit, Long>, JpaSpecificationExecutor<ServiceUnit> {

}
