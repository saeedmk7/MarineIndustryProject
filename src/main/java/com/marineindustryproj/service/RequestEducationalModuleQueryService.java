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

import com.marineindustryproj.domain.RequestEducationalModule;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.RequestEducationalModuleRepository;
import com.marineindustryproj.service.dto.RequestEducationalModuleCriteria;
import com.marineindustryproj.service.dto.RequestEducationalModuleDTO;
import com.marineindustryproj.service.mapper.RequestEducationalModuleMapper;

/**
 * Service for executing complex queries for RequestEducationalModule entities in the database.
 * The main input is a {@link RequestEducationalModuleCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link RequestEducationalModuleDTO} or a {@link Page} of {@link RequestEducationalModuleDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class RequestEducationalModuleQueryService extends QueryService<RequestEducationalModule> {

    private final Logger log = LoggerFactory.getLogger(RequestEducationalModuleQueryService.class);

    private final RequestEducationalModuleRepository requestEducationalModuleRepository;

    private final RequestEducationalModuleMapper requestEducationalModuleMapper;

    public RequestEducationalModuleQueryService(RequestEducationalModuleRepository requestEducationalModuleRepository, RequestEducationalModuleMapper requestEducationalModuleMapper) {
        this.requestEducationalModuleRepository = requestEducationalModuleRepository;
        this.requestEducationalModuleMapper = requestEducationalModuleMapper;
    }

    /**
     * Return a {@link List} of {@link RequestEducationalModuleDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<RequestEducationalModuleDTO> findByCriteria(RequestEducationalModuleCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<RequestEducationalModule> specification = createSpecification(criteria);
        return requestEducationalModuleMapper.toDto(requestEducationalModuleRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link RequestEducationalModuleDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<RequestEducationalModuleDTO> findByCriteria(RequestEducationalModuleCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<RequestEducationalModule> specification = createSpecification(criteria);
        return requestEducationalModuleRepository.findAll(specification, page)
            .map(requestEducationalModuleMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(RequestEducationalModuleCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<RequestEducationalModule> specification = createSpecification(criteria);
        return requestEducationalModuleRepository.count(specification);
    }

    /**
     * Function to convert RequestEducationalModuleCriteria to a {@link Specification}
     */
    private Specification<RequestEducationalModule> createSpecification(RequestEducationalModuleCriteria criteria) {
        Specification<RequestEducationalModule> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), RequestEducationalModule_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCode(), RequestEducationalModule_.code));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), RequestEducationalModule_.title));
            }
            if (criteria.getLearningTimeTheorical() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLearningTimeTheorical(), RequestEducationalModule_.learningTimeTheorical));
            }
            if (criteria.getLearningTimePractical() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLearningTimePractical(), RequestEducationalModule_.learningTimePractical));
            }
            if (criteria.getVersion() != null) {
                specification = specification.and(buildStringSpecification(criteria.getVersion(), RequestEducationalModule_.version));
            }
            if (criteria.getInnerCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getInnerCode(), RequestEducationalModule_.innerCode));
            }
            if (criteria.getCentralizedCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCentralizedCode(), RequestEducationalModule_.centralizedCode));
            }
            if (criteria.getMoreDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMoreDescription(), RequestEducationalModule_.moreDescription));
            }
            if (criteria.getRecommendedBy() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRecommendedBy(), RequestEducationalModule_.recommendedBy));
            }
            if (criteria.getEducationalModuleHeadlines() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEducationalModuleHeadlines(), RequestEducationalModule_.educationalModuleHeadlines));
            }
            if (criteria.getPrerequisite() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPrerequisite(), RequestEducationalModule_.prerequisite));
            }
            if (criteria.getDrafters() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDrafters(), RequestEducationalModule_.drafters));
            }
            if (criteria.getEducationalModuleLevel() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEducationalModuleLevel(), RequestEducationalModule_.educationalModuleLevel));
            }
            if (criteria.getEducationalModuleGroup() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEducationalModuleGroup(), RequestEducationalModule_.educationalModuleGroup));
            }
            if (criteria.getEducationalModuleHour() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEducationalModuleHour(), RequestEducationalModule_.educationalModuleHour));
            }
            if (criteria.getTimePassed() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTimePassed(), RequestEducationalModule_.timePassed));
            }
            if (criteria.getCredit() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCredit(), RequestEducationalModule_.credit));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), RequestEducationalModule_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), RequestEducationalModule_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), RequestEducationalModule_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), RequestEducationalModule_.modifyDate));
            }
            if (criteria.getArchived() != null) {
                specification = specification.and(buildSpecification(criteria.getArchived(), RequestEducationalModule_.archived));
            }
            if (criteria.getArchivedUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getArchivedUserLogin(), RequestEducationalModule_.archivedUserLogin));
            }
            if (criteria.getArchivedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getArchivedDate(), RequestEducationalModule_.archivedDate));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStatus(), RequestEducationalModule_.status));
            }
            if (criteria.getRequestStatus() != null) {
                specification = specification.and(buildSpecification(criteria.getRequestStatus(), RequestEducationalModule_.requestStatus));
            }
            if (criteria.getChangeStatusUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getChangeStatusUserLogin(), RequestEducationalModule_.changeStatusUserLogin));
            }
            if (criteria.getEducationalModuleId() != null) {
                specification = specification.and(buildSpecification(criteria.getEducationalModuleId(),
                    root -> root.join(RequestEducationalModule_.educationalModules, JoinType.LEFT).get(EducationalModule_.id)));
            }
            if (criteria.getScientificWorkGroupId() != null) {
                specification = specification.and(buildSpecification(criteria.getScientificWorkGroupId(),
                    root -> root.join(RequestEducationalModule_.scientificWorkGroups, JoinType.LEFT).get(ScientificWorkGroup_.id)));
            }
            if (criteria.getDocumentId() != null) {
                specification = specification.and(buildSpecification(criteria.getDocumentId(),
                    root -> root.join(RequestEducationalModule_.documents, JoinType.LEFT).get(Document_.id)));
            }
            if (criteria.getEducationalCenterId() != null) {
                specification = specification.and(buildSpecification(criteria.getEducationalCenterId(),
                    root -> root.join(RequestEducationalModule_.educationalCenters, JoinType.LEFT).get(EducationalCenter_.id)));
            }
            if (criteria.getGoalId() != null) {
                specification = specification.and(buildSpecification(criteria.getGoalId(),
                    root -> root.join(RequestEducationalModule_.goals, JoinType.LEFT).get(Goal_.id)));
            }
            if (criteria.getResourceId() != null) {
                specification = specification.and(buildSpecification(criteria.getResourceId(),
                    root -> root.join(RequestEducationalModule_.resources, JoinType.LEFT).get(Resource_.id)));
            }
            if (criteria.getTeacherId() != null) {
                specification = specification.and(buildSpecification(criteria.getTeacherId(),
                    root -> root.join(RequestEducationalModule_.teachers, JoinType.LEFT).get(Teacher_.id)));
            }
            if (criteria.getSecurityLevelId() != null) {
                specification = specification.and(buildSpecification(criteria.getSecurityLevelId(),
                    root -> root.join(RequestEducationalModule_.securityLevel, JoinType.LEFT).get(SecurityLevel_.id)));
            }
            if (criteria.getSkillableLevelOfSkillId() != null) {
                specification = specification.and(buildSpecification(criteria.getSkillableLevelOfSkillId(),
                    root -> root.join(RequestEducationalModule_.skillableLevelOfSkill, JoinType.LEFT).get(SkillableLevelOfSkill_.id)));
            }
            if (criteria.getEvaluationMethodId() != null) {
                specification = specification.and(buildSpecification(criteria.getEvaluationMethodId(),
                    root -> root.join(RequestEducationalModule_.evaluationMethod, JoinType.LEFT).get(EvaluationMethod_.id)));
            }
            if (criteria.getOrganizationId() != null) {
                specification = specification.and(buildSpecification(criteria.getOrganizationId(),
                    root -> root.join(RequestEducationalModule_.organization, JoinType.LEFT).get(Organization_.id)));
            }
        }
        return specification;
    }
}
