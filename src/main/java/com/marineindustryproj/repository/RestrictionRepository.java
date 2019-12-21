package com.marineindustryproj.repository;

import com.marineindustryproj.domain.Restriction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Restriction entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RestrictionRepository extends JpaRepository<Restriction, Long>, JpaSpecificationExecutor<Restriction> {

    /*@Query(value = "select distinct restriction from Restriction restriction left join fetch restriction.requestEducationalModules left join fetch restriction.educationalModules",
        countQuery = "select count(distinct restriction) from Restriction restriction")
    Page<Restriction> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct restriction from Restriction restriction left join fetch restriction.requestEducationalModules left join fetch restriction.educationalModules")
    List<Restriction> findAllWithEagerRelationships();

    @Query("select restriction from Restriction restriction left join fetch restriction.requestEducationalModules left join fetch restriction.educationalModules where restriction.id =:id")
    Optional<Restriction> findOneWithEagerRelationships(@Param("id") Long id);*/

}
