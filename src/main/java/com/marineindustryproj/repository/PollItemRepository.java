package com.marineindustryproj.repository;

import com.marineindustryproj.domain.PollItem;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PollItem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PollItemRepository extends JpaRepository<PollItem, Long>, JpaSpecificationExecutor<PollItem> {

}
