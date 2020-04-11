package com.marineindustryproj.repository;

import com.marineindustryproj.domain.LevelFourEffectiveness;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the LevelFourEffectiveness entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LevelFourEffectivenessRepository extends JpaRepository<LevelFourEffectiveness, Long>, JpaSpecificationExecutor<LevelFourEffectiveness> {

    @Query(value = "select distinct level_four_effectiveness from LevelFourEffectiveness level_four_effectiveness left join fetch level_four_effectiveness.documents",
        countQuery = "select count(distinct level_four_effectiveness) from LevelFourEffectiveness level_four_effectiveness")
    Page<LevelFourEffectiveness> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct level_four_effectiveness from LevelFourEffectiveness level_four_effectiveness left join fetch level_four_effectiveness.documents")
    List<LevelFourEffectiveness> findAllWithEagerRelationships();

    @Query("select level_four_effectiveness from LevelFourEffectiveness level_four_effectiveness left join fetch level_four_effectiveness.documents where level_four_effectiveness.id =:id")
    Optional<LevelFourEffectiveness> findOneWithEagerRelationships(@Param("id") Long id);

}
