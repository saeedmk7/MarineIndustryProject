package com.marineindustryproj.repository;

import com.marineindustryproj.domain.ScientificWorkGroup;
import com.marineindustryproj.domain.SkillableLevelOfSkill;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * Spring Data  repository for the ScientificWorkGroup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ScientificWorkGroupRepository extends JpaRepository<ScientificWorkGroup, Long>, JpaSpecificationExecutor<ScientificWorkGroup> {

    @Query("select scientificWorkGroup from ScientificWorkGroup scientificWorkGroup where scientificWorkGroup.title =:title")
    Optional<ScientificWorkGroup> findByTitle(@Param("title") String title);

}
