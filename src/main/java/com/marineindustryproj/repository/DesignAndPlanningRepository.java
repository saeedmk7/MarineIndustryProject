package com.marineindustryproj.repository;

import com.marineindustryproj.domain.DesignAndPlanning;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the DesignAndPlanning entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DesignAndPlanningRepository extends JpaRepository<DesignAndPlanning, Long>, JpaSpecificationExecutor<DesignAndPlanning> {

    @Query(value = "select distinct design_and_planning from DesignAndPlanning design_and_planning left join fetch design_and_planning.people left join fetch design_and_planning.documents",
        countQuery = "select count(distinct design_and_planning) from DesignAndPlanning design_and_planning")
    Page<DesignAndPlanning> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct design_and_planning from DesignAndPlanning design_and_planning left join fetch design_and_planning.people left join fetch design_and_planning.documents")
    List<DesignAndPlanning> findAllWithEagerRelationships();

    @Query("select design_and_planning from DesignAndPlanning design_and_planning left join fetch design_and_planning.people left join fetch design_and_planning.documents where design_and_planning.id =:id")
    Optional<DesignAndPlanning> findOneWithEagerRelationships(@Param("id") Long id);

}
