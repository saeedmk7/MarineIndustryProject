package com.marineindustryproj.repository;

import com.marineindustryproj.domain.SkillableLevelOfSkill;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the SkillableLevelOfSkill entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SkillableLevelOfSkillRepository extends JpaRepository<SkillableLevelOfSkill, Long>, JpaSpecificationExecutor<SkillableLevelOfSkill> {

}
