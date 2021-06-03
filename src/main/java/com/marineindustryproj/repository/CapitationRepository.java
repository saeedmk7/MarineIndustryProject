package com.marineindustryproj.repository;

import com.marineindustryproj.domain.Capitation;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Capitation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CapitationRepository extends JpaRepository<Capitation, Long>, JpaSpecificationExecutor<Capitation> {

}
