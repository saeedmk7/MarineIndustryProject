package com.marineindustryproj.repository;

import com.marineindustryproj.domain.NiazsanjiInput;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the NiazsanjiInput entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NiazsanjiInputRepository extends JpaRepository<NiazsanjiInput, Long>, JpaSpecificationExecutor<NiazsanjiInput> {

}
