package com.marineindustryproj.repository;

import com.marineindustryproj.domain.ConditionsOfParticipant;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ConditionsOfParticipant entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ConditionsOfParticipantRepository extends JpaRepository<ConditionsOfParticipant, Long>, JpaSpecificationExecutor<ConditionsOfParticipant> {

}
