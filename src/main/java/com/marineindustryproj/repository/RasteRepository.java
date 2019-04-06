package com.marineindustryproj.repository;

import com.marineindustryproj.domain.Raste;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Raste entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RasteRepository extends JpaRepository<Raste, Long>, JpaSpecificationExecutor<Raste> {

}
