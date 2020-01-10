package com.marineindustryproj.repository;

import com.marineindustryproj.domain.Restriction;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Restriction entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RestrictionRepository extends JpaRepository<Restriction, Long>, JpaSpecificationExecutor<Restriction> {

}
