package com.marineindustryproj.repository;

import com.marineindustryproj.domain.SubTask;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SubTask entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SubTaskRepository extends JpaRepository<SubTask, Long>, JpaSpecificationExecutor<SubTask> {

}
