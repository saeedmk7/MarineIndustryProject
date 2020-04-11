package com.marineindustryproj.repository;

import com.marineindustryproj.domain.EffectivenessPhaseLevel;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EffectivenessPhaseLevel entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EffectivenessPhaseLevelRepository extends JpaRepository<EffectivenessPhaseLevel, Long>, JpaSpecificationExecutor<EffectivenessPhaseLevel> {

}
