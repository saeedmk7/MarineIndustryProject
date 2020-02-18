package com.marineindustryproj.repository;

import com.marineindustryproj.domain.PeopleUnderTraining;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the PeopleUnderTraining entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PeopleUnderTrainingRepository extends JpaRepository<PeopleUnderTraining, Long>, JpaSpecificationExecutor<PeopleUnderTraining> {

}
