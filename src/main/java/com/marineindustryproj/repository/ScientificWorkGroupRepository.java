package com.marineindustryproj.repository;

import com.marineindustryproj.domain.ScientificWorkGroup;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ScientificWorkGroup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ScientificWorkGroupRepository extends JpaRepository<ScientificWorkGroup, Long>, JpaSpecificationExecutor<ScientificWorkGroup> {

}
