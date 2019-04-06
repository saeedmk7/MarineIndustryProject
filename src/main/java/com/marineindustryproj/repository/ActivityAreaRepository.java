package com.marineindustryproj.repository;

import com.marineindustryproj.domain.ActivityArea;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ActivityArea entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ActivityAreaRepository extends JpaRepository<ActivityArea, Long>, JpaSpecificationExecutor<ActivityArea> {

}
