package com.marineindustryproj.repository;

import com.marineindustryproj.domain.EffectivenessLevel;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EffectivenessLevel entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EffectivenessLevelRepository extends JpaRepository<EffectivenessLevel, Long>, JpaSpecificationExecutor<EffectivenessLevel> {

}
