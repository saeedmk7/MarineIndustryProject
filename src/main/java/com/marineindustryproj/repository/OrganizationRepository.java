package com.marineindustryproj.repository;

import com.marineindustryproj.domain.Organization;
import com.marineindustryproj.domain.ScientificWorkGroup;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * Spring Data  repository for the Organization entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long>, JpaSpecificationExecutor<Organization> {

    @Query("select organization from Organization organization where organization.title =:title")
    Optional<Organization> findByTitle(@Param("title") String title);

}
