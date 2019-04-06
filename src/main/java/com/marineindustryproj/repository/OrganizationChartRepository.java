package com.marineindustryproj.repository;

import java.util.List;

import com.marineindustryproj.domain.OrganizationChart;
import com.marineindustryproj.service.dto.OrganizationChartDTO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the OrganizationChart entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrganizationChartRepository extends JpaRepository<OrganizationChart, Long>, JpaSpecificationExecutor<OrganizationChart> {
    String ALL_ORGANIZATIONCHART_CACHE = "allOrganizationChart";

    @Cacheable(cacheNames = ALL_ORGANIZATIONCHART_CACHE)
    List<OrganizationChart> findAll();
}
