package com.marineindustryproj.repository;

import com.marineindustryproj.domain.SecurityLevel;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SecurityLevel entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SecurityLevelRepository extends JpaRepository<SecurityLevel, Long>, JpaSpecificationExecutor<SecurityLevel> {

}
