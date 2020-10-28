package com.marineindustryproj.repository;

import com.marineindustryproj.domain.MonitorLearningProcess;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the MonitorLearningProcess entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MonitorLearningProcessRepository extends JpaRepository<MonitorLearningProcess, Long>, JpaSpecificationExecutor<MonitorLearningProcess> {

    @Query(value = "select distinct monitor_learning_process from MonitorLearningProcess monitor_learning_process left join fetch monitor_learning_process.documents",
        countQuery = "select count(distinct monitor_learning_process) from MonitorLearningProcess monitor_learning_process")
    Page<MonitorLearningProcess> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct monitor_learning_process from MonitorLearningProcess monitor_learning_process left join fetch monitor_learning_process.documents")
    List<MonitorLearningProcess> findAllWithEagerRelationships();

    @Query("select monitor_learning_process from MonitorLearningProcess monitor_learning_process left join fetch monitor_learning_process.documents where monitor_learning_process.id =:id")
    Optional<MonitorLearningProcess> findOneWithEagerRelationships(@Param("id") Long id);

}
