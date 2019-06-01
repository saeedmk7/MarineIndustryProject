package com.marineindustryproj.repository;

import com.marineindustryproj.domain.JamHelp;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the JamHelp entity.
 */
@SuppressWarnings("unused")
@Repository
public interface JamHelpRepository extends JpaRepository<JamHelp, Long>, JpaSpecificationExecutor<JamHelp> {

}
