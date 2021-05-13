package com.marineindustryproj.repository;

import com.marineindustryproj.domain.ApplicationProcess;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the ApplicationProcess entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ApplicationProcessRepository extends JpaRepository<ApplicationProcess, Long>, JpaSpecificationExecutor<ApplicationProcess> {

    @Query(value = "select distinct application_process from ApplicationProcess application_process left join fetch application_process.documents",
        countQuery = "select count(distinct application_process) from ApplicationProcess application_process")
    Page<ApplicationProcess> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct application_process from ApplicationProcess application_process left join fetch application_process.documents")
    List<ApplicationProcess> findAllWithEagerRelationships();

    @Query("select application_process from ApplicationProcess application_process left join fetch application_process.documents where application_process.id =:id")
    Optional<ApplicationProcess> findOneWithEagerRelationships(@Param("id") Long id);

}
