package com.marineindustryproj.repository;

import com.marineindustryproj.domain.SkillableLevelOfSkill;
import com.marineindustryproj.domain.UsersRequest;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * Spring Data  repository for the SkillableLevelOfSkill entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SkillableLevelOfSkillRepository extends JpaRepository<SkillableLevelOfSkill, Long>, JpaSpecificationExecutor<SkillableLevelOfSkill> {

    @Query("select skillableLevelOfSkill from SkillableLevelOfSkill skillableLevelOfSkill where skillableLevelOfSkill.title =:title")
    Optional<SkillableLevelOfSkill> findByTitle(@Param("title") String title);

}
