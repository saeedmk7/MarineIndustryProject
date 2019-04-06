package com.marineindustryproj.repository;

import com.marineindustryproj.domain.MainTask;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the MainTask entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MainTaskRepository extends JpaRepository<MainTask, Long>, JpaSpecificationExecutor<MainTask> {

    @Query(value = "select distinct main_task from MainTask main_task left join fetch main_task.subTasks left join fetch main_task.jobs left join fetch main_task.people",
        countQuery = "select count(distinct main_task) from MainTask main_task")
    Page<MainTask> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct main_task from MainTask main_task left join fetch main_task.subTasks left join fetch main_task.jobs left join fetch main_task.people")
    List<MainTask> findAllWithEagerRelationships();

    @Query("select main_task from MainTask main_task left join fetch main_task.subTasks left join fetch main_task.jobs left join fetch main_task.people where main_task.id =:id")
    Optional<MainTask> findOneWithEagerRelationships(@Param("id") Long id);

}
