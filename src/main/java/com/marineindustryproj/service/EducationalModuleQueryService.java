package com.marineindustryproj.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.marineindustryproj.domain.EducationalModule;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.EducationalModuleRepository;
import com.marineindustryproj.service.dto.EducationalModuleCriteria;
import com.marineindustryproj.service.dto.EducationalModuleDTO;
import com.marineindustryproj.service.mapper.EducationalModuleMapper;

/**
 * Service for executing complex queries for EducationalModule entities in the database.
 * The main input is a {@link EducationalModuleCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link EducationalModuleDTO} or a {@link Page} of {@link EducationalModuleDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class EducationalModuleQueryService extends QueryService<EducationalModule> {

    private final Logger log = LoggerFactory.getLogger(EducationalModuleQueryService.class);

    private final EducationalModuleRepository educationalModuleRepository;

    private final EducationalModuleMapper educationalModuleMapper;

    public EducationalModuleQueryService(EducationalModuleRepository educationalModuleRepository, EducationalModuleMapper educationalModuleMapper) {
        this.educationalModuleRepository = educationalModuleRepository;
        this.educationalModuleMapper = educationalModuleMapper;
    }

    /**
     * Return a {@link List} of {@link EducationalModuleDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<EducationalModuleDTO> findByCriteria(EducationalModuleCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<EducationalModule> specification = createSpecification(criteria);
        return educationalModuleMapper.toDto(educationalModuleRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link EducationalModuleDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<EducationalModuleDTO> findByCriteria(EducationalModuleCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<EducationalModule> specification = createSpecification(criteria);
        return educationalModuleRepository.findAll(specification, page)
            .map(educationalModuleMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(EducationalModuleCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<EducationalModule> specification = createSpecification(criteria);
        return educationalModuleRepository.count(specification);
    }

    /**
     * Function to convert EducationalModuleCriteria to a {@link Specification}
     */
    private Specification<EducationalModule> createSpecification(EducationalModuleCriteria criteria) {
        Specification<EducationalModule> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), EducationalModule_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), EducationalModule_.code));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), EducationalModule_.title));
            }
            if (criteria.getLearningTimeTheorical() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLearningTimeTheorical(), EducationalModule_.learningTimeTheorical));
            }
            if (criteria.getLearningTimePractical() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLearningTimePractical(), EducationalModule_.learningTimePractical));
            }
            if (criteria.getVersion() != null) {
                specification = specification.and(buildStringSpecification(criteria.getVersion(), EducationalModule_.version));
            }
            if (criteria.getInnerCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getInnerCode(), EducationalModule_.innerCode));
            }
            if (criteria.getCentralizedCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCentralizedCode(), EducationalModule_.centralizedCode));
            }
            if (criteria.getMoreDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMoreDescription(), EducationalModule_.moreDescription));
            }
            if (criteria.getRecommendedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRecommendedBy(), EducationalModule_.recommendedBy));
            }
            if (criteria.getEducationalModuleHeadlines() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEducationalModuleHeadlines(), EducationalModule_.educationalModuleHeadlines));
            }
            if (criteria.getPrerequisite() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPrerequisite(), EducationalModule_.prerequisite));
            }
            if (criteria.getDrafters() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDrafters(), EducationalModule_.drafters));
            }
            if (criteria.getEducationalModuleLevel() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEducationalModuleLevel(), EducationalModule_.educationalModuleLevel));
            }
            if (criteria.getEducationalModuleGroup() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEducationalModuleGroup(), EducationalModule_.educationalModuleGroup));
            }
            if (criteria.getEducationalModuleHour() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEducationalModuleHour(), EducationalModule_.educationalModuleHour));
            }
            if (criteria.getTimePassed() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTimePassed(), EducationalModule_.timePassed));
            }
            if (criteria.getCredit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCredit(), EducationalModule_.credit));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), EducationalModule_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), EducationalModule_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), EducationalModule_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), EducationalModule_.modifyDate));
            }
            if (criteria.getArchived() != null) {
                specification = specification.and(buildSpecification(criteria.getArchived(), EducationalModule_.archived));
            }
            if (criteria.getArchivedUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getArchivedUserLogin(), EducationalModule_.archivedUserLogin));
            }
            if (criteria.getArchivedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getArchivedDate(), EducationalModule_.archivedDate));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStatus(), EducationalModule_.status));
            }
            if (criteria.getEducationalModuleJobId() != null) {
                specification = specification.and(buildSpecification(criteria.getEducationalModuleJobId(),
                    root -> root.join(EducationalModule_.educationalModuleJobs, JoinType.LEFT).get(EducationalModuleJob_.id)));
            }
            if (criteria.getRequestOrganizationNiazsanjiId() != null) {
                specification = specification.and(buildSpecification(criteria.getRequestOrganizationNiazsanjiId(),
                    root -> root.join(EducationalModule_.requestOrganizationNiazsanjis, JoinType.LEFT).get(RequestOrganizationNiazsanji_.id)));
            }
            if (criteria.getFinalOrganizationNiazsanjiId() != null) {
                specification = specification.and(buildSpecification(criteria.getFinalOrganizationNiazsanjiId(),
                    root -> root.join(EducationalModule_.finalOrganizationNiazsanjis, JoinType.LEFT).get(FinalOrganizationNiazsanji_.id)));
            }
            if (criteria.getFinalNiazsanjiReportId() != null) {
                specification = specification.and(buildSpecification(criteria.getFinalNiazsanjiReportId(),
                    root -> root.join(EducationalModule_.finalNiazsanjiReports, JoinType.LEFT).get(FinalNiazsanjiReport_.id)));
            }
            if (criteria.getNiazsanjiFardiId() != null) {
                specification = specification.and(buildSpecification(criteria.getNiazsanjiFardiId(),
                    root -> root.join(EducationalModule_.niazsanjiFardis, JoinType.LEFT).get(NiazsanjiFardi_.id)));
            }
            if (criteria.getApprovedRequestNiazsanjiFardiId() != null) {
                specification = specification.and(buildSpecification(criteria.getApprovedRequestNiazsanjiFardiId(),
                    root -> root.join(EducationalModule_.approvedRequestNiazsanjiFardis, JoinType.LEFT).get(RequestNiazsanjiFardi_.id)));
            }
            if (criteria.getAllRequestNiazsanjiFardiId() != null) {
                specification = specification.and(buildSpecification(criteria.getAllRequestNiazsanjiFardiId(),
                    root -> root.join(EducationalModule_.allRequestNiazsanjiFardis, JoinType.LEFT).get(RequestNiazsanjiFardi_.id)));
            }
            if (criteria.getScientificWorkGroupId() != null) {
                specification = specification.and(buildSpecification(criteria.getScientificWorkGroupId(),
                    root -> root.join(EducationalModule_.scientificWorkGroups, JoinType.LEFT).get(ScientificWorkGroup_.id)));
            }
            if (criteria.getDocumentId() != null) {
                specification = specification.and(buildSpecification(criteria.getDocumentId(),
                    root -> root.join(EducationalModule_.documents, JoinType.LEFT).get(Document_.id)));
            }
            if (criteria.getEducationalCenterId() != null) {
                specification = specification.and(buildSpecification(criteria.getEducationalCenterId(),
                    root -> root.join(EducationalModule_.educationalCenters, JoinType.LEFT).get(EducationalCenter_.id)));
            }
            if (criteria.getGoalId() != null) {
                specification = specification.and(buildSpecification(criteria.getGoalId(),
                    root -> root.join(EducationalModule_.goals, JoinType.LEFT).get(Goal_.id)));
            }
            if (criteria.getResourceId() != null) {
                specification = specification.and(buildSpecification(criteria.getResourceId(),
                    root -> root.join(EducationalModule_.resources, JoinType.LEFT).get(Resource_.id)));
            }
            if (criteria.getTeacherId() != null) {
                specification = specification.and(buildSpecification(criteria.getTeacherId(),
                    root -> root.join(EducationalModule_.teachers, JoinType.LEFT).get(Teacher_.id)));
            }
            if (criteria.getRequestEducationalModuleId() != null) {
                specification = specification.and(buildSpecification(criteria.getRequestEducationalModuleId(),
                    root -> root.join(EducationalModule_.requestEducationalModule, JoinType.LEFT).get(RequestEducationalModule_.id)));
            }
            if (criteria.getSecurityLevelId() != null) {
                specification = specification.and(buildSpecification(criteria.getSecurityLevelId(),
                    root -> root.join(EducationalModule_.securityLevel, JoinType.LEFT).get(SecurityLevel_.id)));
            }
            if (criteria.getSkillableLevelOfSkillId() != null) {
                specification = specification.and(buildSpecification(criteria.getSkillableLevelOfSkillId(),
                    root -> root.join(EducationalModule_.skillableLevelOfSkill, JoinType.LEFT).get(SkillableLevelOfSkill_.id)));
            }
            if (criteria.getEvaluationMethodId() != null) {
                specification = specification.and(buildSpecification(criteria.getEvaluationMethodId(),
                    root -> root.join(EducationalModule_.evaluationMethod, JoinType.LEFT).get(EvaluationMethod_.id)));
            }
            if (criteria.getOrganizationId() != null) {
                specification = specification.and(buildSpecification(criteria.getOrganizationId(),
                    root -> root.join(EducationalModule_.organization, JoinType.LEFT).get(Organization_.id)));
            }
            if (criteria.getNiazsanjiGroupId() != null) {
                specification = specification.and(buildSpecification(criteria.getNiazsanjiGroupId(),
                    root -> root.join(EducationalModule_.niazsanjiGroups, JoinType.LEFT).get(NiazsanjiGroup_.id)));
            }
        }
        return specification;
    }
}
