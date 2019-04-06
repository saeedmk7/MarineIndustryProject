package com.marineindustryproj.repository;

import com.marineindustryproj.domain.PollScore;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PollScore entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PollScoreRepository extends JpaRepository<PollScore, Long>, JpaSpecificationExecutor<PollScore> {

}
