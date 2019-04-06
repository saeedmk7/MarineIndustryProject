package com.marineindustryproj.repository;

import com.marineindustryproj.domain.NavBarItemAuthority;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the NavBarItemAuthority entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NavBarItemAuthorityRepository extends JpaRepository<NavBarItemAuthority, Long>, JpaSpecificationExecutor<NavBarItemAuthority> {

}
