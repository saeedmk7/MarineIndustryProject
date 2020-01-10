package com.marineindustryproj.repository;

import com.marineindustryproj.domain.FixCompetencyDeficiency;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the FixCompetencyDeficiency entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FixCompetencyDeficiencyRepository extends JpaRepository<FixCompetencyDeficiency, Long>, JpaSpecificationExecutor<FixCompetencyDeficiency> {

}
