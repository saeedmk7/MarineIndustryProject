package com.marineindustryproj.repository;

import com.marineindustryproj.domain.NiazsanjiIntegration;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the NiazsanjiIntegration entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NiazsanjiIntegrationRepository extends JpaRepository<NiazsanjiIntegration, Long>, JpaSpecificationExecutor<NiazsanjiIntegration> {

}
