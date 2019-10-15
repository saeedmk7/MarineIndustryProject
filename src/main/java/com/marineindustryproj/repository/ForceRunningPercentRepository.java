package com.marineindustryproj.repository;

import com.marineindustryproj.domain.ForceRunningPercent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the ForceRunningPercent entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ForceRunningPercentRepository extends JpaRepository<ForceRunningPercent, Long>, JpaSpecificationExecutor<ForceRunningPercent> {

    @Query(value = "select distinct force_running_percent from ForceRunningPercent force_running_percent left join fetch force_running_percent.organizationCharts",
        countQuery = "select count(distinct force_running_percent) from ForceRunningPercent force_running_percent")
    Page<ForceRunningPercent> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct force_running_percent from ForceRunningPercent force_running_percent left join fetch force_running_percent.organizationCharts")
    List<ForceRunningPercent> findAllWithEagerRelationships();

    @Query("select force_running_percent from ForceRunningPercent force_running_percent left join fetch force_running_percent.organizationCharts where force_running_percent.id =:id")
    Optional<ForceRunningPercent> findOneWithEagerRelationships(@Param("id") Long id);

}
