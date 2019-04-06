package com.marineindustryproj.repository;

import com.marineindustryproj.domain.TeachTechnique;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the TeachTechnique entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TeachTechniqueRepository extends JpaRepository<TeachTechnique, Long>, JpaSpecificationExecutor<TeachTechnique> {

}
