package com.marineindustryproj.repository;

import com.marineindustryproj.domain.ActivationNiazsanji;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ActivationNiazsanji entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ActivationNiazsanjiRepository extends JpaRepository<ActivationNiazsanji, Long>, JpaSpecificationExecutor<ActivationNiazsanji> {

}
