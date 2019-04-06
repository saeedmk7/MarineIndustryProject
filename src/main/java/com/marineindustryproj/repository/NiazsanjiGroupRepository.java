package com.marineindustryproj.repository;

import com.marineindustryproj.domain.NiazsanjiGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the NiazsanjiGroup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NiazsanjiGroupRepository extends JpaRepository<NiazsanjiGroup, Long>, JpaSpecificationExecutor<NiazsanjiGroup> {

    @Query(value = "select distinct niazsanji_group from NiazsanjiGroup niazsanji_group left join fetch niazsanji_group.jobs left join fetch niazsanji_group.educationalModules",
        countQuery = "select count(distinct niazsanji_group) from NiazsanjiGroup niazsanji_group")
    Page<NiazsanjiGroup> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct niazsanji_group from NiazsanjiGroup niazsanji_group left join fetch niazsanji_group.jobs left join fetch niazsanji_group.educationalModules")
    List<NiazsanjiGroup> findAllWithEagerRelationships();

    @Query("select niazsanji_group from NiazsanjiGroup niazsanji_group left join fetch niazsanji_group.jobs left join fetch niazsanji_group.educationalModules where niazsanji_group.id =:id")
    Optional<NiazsanjiGroup> findOneWithEagerRelationships(@Param("id") Long id);

}
