package com.marineindustryproj.repository;

import com.marineindustryproj.domain.EducationalCenter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the EducationalCenter entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EducationalCenterRepository extends JpaRepository<EducationalCenter, Long>, JpaSpecificationExecutor<EducationalCenter> {

    @Query(value = "select distinct educational_center from EducationalCenter educational_center left join fetch educational_center.activityAreas left join fetch educational_center.documents",
        countQuery = "select count(distinct educational_center) from EducationalCenter educational_center")
    Page<EducationalCenter> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct educational_center from EducationalCenter educational_center left join fetch educational_center.activityAreas left join fetch educational_center.documents")
    List<EducationalCenter> findAllWithEagerRelationships();

    @Query("select educational_center from EducationalCenter educational_center left join fetch educational_center.activityAreas left join fetch educational_center.documents where educational_center.id =:id")
    Optional<EducationalCenter> findOneWithEagerRelationships(@Param("id") Long id);

}
