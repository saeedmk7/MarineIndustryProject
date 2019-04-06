package com.marineindustryproj.repository;

import com.marineindustryproj.domain.RunPhase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the RunPhase entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RunPhaseRepository extends JpaRepository<RunPhase, Long>, JpaSpecificationExecutor<RunPhase> {

    @Query(value = "select distinct run_phase from RunPhase run_phase left join fetch run_phase.documents left join fetch run_phase.people",
        countQuery = "select count(distinct run_phase) from RunPhase run_phase")
    Page<RunPhase> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct run_phase from RunPhase run_phase left join fetch run_phase.documents left join fetch run_phase.people")
    List<RunPhase> findAllWithEagerRelationships();

    @Query("select run_phase from RunPhase run_phase left join fetch run_phase.documents left join fetch run_phase.people where run_phase.id =:id")
    Optional<RunPhase> findOneWithEagerRelationships(@Param("id") Long id);

}
