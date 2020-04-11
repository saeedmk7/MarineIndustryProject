package com.marineindustryproj.repository;

import com.marineindustryproj.domain.EffectivenessPhase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the EffectivenessPhase entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EffectivenessPhaseRepository extends JpaRepository<EffectivenessPhase, Long>, JpaSpecificationExecutor<EffectivenessPhase> {

    @Query(value = "select distinct effectiveness_phase from EffectivenessPhase effectiveness_phase left join fetch effectiveness_phase.documents",
        countQuery = "select count(distinct effectiveness_phase) from EffectivenessPhase effectiveness_phase")
    Page<EffectivenessPhase> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct effectiveness_phase from EffectivenessPhase effectiveness_phase left join fetch effectiveness_phase.documents")
    List<EffectivenessPhase> findAllWithEagerRelationships();

    @Query("select effectiveness_phase from EffectivenessPhase effectiveness_phase left join fetch effectiveness_phase.documents where effectiveness_phase.id =:id")
    Optional<EffectivenessPhase> findOneWithEagerRelationships(@Param("id") Long id);

}
