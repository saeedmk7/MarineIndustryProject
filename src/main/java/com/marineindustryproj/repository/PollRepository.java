package com.marineindustryproj.repository;

import com.marineindustryproj.domain.Poll;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Poll entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PollRepository extends JpaRepository<Poll, Long>, JpaSpecificationExecutor<Poll> {

}
