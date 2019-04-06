package com.marineindustryproj.repository;

import com.marineindustryproj.domain.EffectivenessIndex;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the EffectivenessIndex entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EffectivenessIndexRepository extends JpaRepository<EffectivenessIndex, Long>, JpaSpecificationExecutor<EffectivenessIndex> {

}
