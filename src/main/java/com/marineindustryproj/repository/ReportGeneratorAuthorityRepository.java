package com.marineindustryproj.repository;

import com.marineindustryproj.domain.ReportGeneratorAuthority;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ReportGeneratorAuthority entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReportGeneratorAuthorityRepository extends JpaRepository<ReportGeneratorAuthority, Long>, JpaSpecificationExecutor<ReportGeneratorAuthority> {

}
