package com.marineindustryproj.repository;

import com.marineindustryproj.domain.OrganizationChartAuthority;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the OrganizationChartAuthority entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrganizationChartAuthorityRepository extends JpaRepository<OrganizationChartAuthority, Long>, JpaSpecificationExecutor<OrganizationChartAuthority> {

}
