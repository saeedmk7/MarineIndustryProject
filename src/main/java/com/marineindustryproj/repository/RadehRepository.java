package com.marineindustryproj.repository;

import com.marineindustryproj.domain.Radeh;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Radeh entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RadehRepository extends JpaRepository<Radeh, Long>, JpaSpecificationExecutor<Radeh> {

}
