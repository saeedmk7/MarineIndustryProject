package com.marineindustryproj.repository;

import com.marineindustryproj.domain.JamHelpAuthority;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the JamHelpAuthority entity.
 */
@SuppressWarnings("unused")
@Repository
public interface JamHelpAuthorityRepository extends JpaRepository<JamHelpAuthority, Long>, JpaSpecificationExecutor<JamHelpAuthority> {

}
