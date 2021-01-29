package com.marineindustryproj.repository;

import com.marineindustryproj.domain.SecurityLevel;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * Spring Data  repository for the SecurityLevel entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SecurityLevelRepository extends JpaRepository<SecurityLevel, Long>, JpaSpecificationExecutor<SecurityLevel> {

    @Query("select securityLevel from Organization securityLevel where securityLevel.title =:title")
    Optional<SecurityLevel> findByTitle(@Param("title") String title);

}
