package com.marineindustryproj.repository;

import com.marineindustryproj.domain.LevelThreeEffectiveness;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the LevelThreeEffectiveness entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LevelThreeEffectivenessRepository extends JpaRepository<LevelThreeEffectiveness, Long>, JpaSpecificationExecutor<LevelThreeEffectiveness> {

    @Query(value = "select distinct level_three_effectiveness from LevelThreeEffectiveness level_three_effectiveness left join fetch level_three_effectiveness.documents",
        countQuery = "select count(distinct level_three_effectiveness) from LevelThreeEffectiveness level_three_effectiveness")
    Page<LevelThreeEffectiveness> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct level_three_effectiveness from LevelThreeEffectiveness level_three_effectiveness left join fetch level_three_effectiveness.documents")
    List<LevelThreeEffectiveness> findAllWithEagerRelationships();

    @Query("select level_three_effectiveness from LevelThreeEffectiveness level_three_effectiveness left join fetch level_three_effectiveness.documents where level_three_effectiveness.id =:id")
    Optional<LevelThreeEffectiveness> findOneWithEagerRelationships(@Param("id") Long id);

}
