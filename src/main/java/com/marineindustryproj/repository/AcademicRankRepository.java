package com.marineindustryproj.repository;

import com.marineindustryproj.domain.AcademicRank;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the AcademicRank entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AcademicRankRepository extends JpaRepository<AcademicRank, Long>, JpaSpecificationExecutor<AcademicRank> {

}
