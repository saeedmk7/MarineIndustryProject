package com.marineindustryproj.repository;

import com.marineindustryproj.domain.NiazsanjiPersonCriteria;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the NiazsanjiPersonCriteria entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NiazsanjiPersonCriteriaRepository extends JpaRepository<NiazsanjiPersonCriteria, Long>, JpaSpecificationExecutor<NiazsanjiPersonCriteria> {

}
