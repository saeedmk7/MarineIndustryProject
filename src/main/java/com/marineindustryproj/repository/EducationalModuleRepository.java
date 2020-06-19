package com.marineindustryproj.repository;

import com.marineindustryproj.domain.EducationalModule;
import com.marineindustryproj.service.dto.customs.EducationalModuleMinDTO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the EducationalModule entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EducationalModuleRepository extends JpaRepository<EducationalModule, Long>, JpaSpecificationExecutor<EducationalModule> {

    String ALL_EDUCATIONALMODULE_CACHE = "allEducationalModule";

    @Query(value = "select distinct educational_module from EducationalModule educational_module left join fetch educational_module.scientificWorkGroups left join fetch educational_module.documents left join fetch educational_module.educationalCenters left join fetch educational_module.goals left join fetch educational_module.resources left join fetch educational_module.teachers left join fetch educational_module.restrictions  left join fetch educational_module.peopleUnderTrainings left join fetch educational_module.teachingApproaches left join fetch educational_module.effectivenessLevels left join fetch educational_module.effectivenessIndices left join fetch educational_module.assessmentMethods",
        countQuery = "select count(distinct educational_module) from EducationalModule educational_module")
    Page<EducationalModule> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct educational_module from EducationalModule educational_module left join fetch educational_module.scientificWorkGroups left join fetch educational_module.documents left join fetch educational_module.educationalCenters left join fetch educational_module.goals left join fetch educational_module.resources left join fetch educational_module.teachers left join fetch educational_module.restrictions left join fetch educational_module.peopleUnderTrainings left join fetch educational_module.teachingApproaches left join fetch educational_module.effectivenessLevels left join fetch educational_module.effectivenessIndices left join fetch educational_module.assessmentMethods")
    List<EducationalModule> findAllWithEagerRelationships();

    @Query("select educational_module from EducationalModule educational_module left join fetch educational_module.scientificWorkGroups left join fetch educational_module.documents left join fetch educational_module.educationalCenters left join fetch educational_module.goals left join fetch educational_module.resources left join fetch educational_module.teachers left join fetch educational_module.restrictions left join fetch educational_module.peopleUnderTrainings left join fetch educational_module.teachingApproaches left join fetch educational_module.effectivenessLevels left join fetch educational_module.effectivenessIndices left join fetch educational_module.assessmentMethods where educational_module.id =:id")
    Optional<EducationalModule> findOneWithEagerRelationships(@Param("id") Long id);

    @Cacheable(cacheNames = ALL_EDUCATIONALMODULE_CACHE)
    @Query(value = "select new com.marineindustryproj.service.dto.customs.EducationalModuleMinDTO(educational_module.id, educational_module.code, educational_module.title, educational_module.learningTimeTheorical, educational_module.learningTimePractical, educational_module.skillableLevelOfSkill) from EducationalModule educational_module")
    List<EducationalModuleMinDTO> findAllFromCache();

}
