package com.marineindustryproj.repository;

import com.marineindustryproj.domain.ScoreItem;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ScoreItem entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ScoreItemRepository extends JpaRepository<ScoreItem, Long>, JpaSpecificationExecutor<ScoreItem> {

}
