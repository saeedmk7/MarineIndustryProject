package com.marineindustryproj.repository;

import com.marineindustryproj.domain.RequestEducationalModule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the RequestEducationalModule entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RequestEducationalModuleRepository extends JpaRepository<RequestEducationalModule, Long>, JpaSpecificationExecutor<RequestEducationalModule> {

    @Query(value = "select distinct request_educational_module from RequestEducationalModule request_educational_module left join fetch request_educational_module.scientificWorkGroups left join fetch request_educational_module.documents left join fetch request_educational_module.educationalCenters left join fetch request_educational_module.goals left join fetch request_educational_module.resources left join fetch request_educational_module.teachers left join fetch request_educational_module.restrictions",
        countQuery = "select count(distinct request_educational_module) from RequestEducationalModule request_educational_module")
    Page<RequestEducationalModule> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct request_educational_module from RequestEducationalModule request_educational_module left join fetch request_educational_module.scientificWorkGroups left join fetch request_educational_module.documents left join fetch request_educational_module.educationalCenters left join fetch request_educational_module.goals left join fetch request_educational_module.resources left join fetch request_educational_module.teachers left join fetch request_educational_module.restrictions")
    List<RequestEducationalModule> findAllWithEagerRelationships();

    @Query("select request_educational_module from RequestEducationalModule request_educational_module left join fetch request_educational_module.scientificWorkGroups left join fetch request_educational_module.documents left join fetch request_educational_module.educationalCenters left join fetch request_educational_module.goals left join fetch request_educational_module.resources left join fetch request_educational_module.teachers left join fetch request_educational_module.restrictions where request_educational_module.id =:id")
    Optional<RequestEducationalModule> findOneWithEagerRelationships(@Param("id") Long id);

}
