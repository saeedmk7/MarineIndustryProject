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

import com.marineindustryproj.domain.RequestOtherNiazsanji;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.RequestOtherNiazsanjiRepository;
import com.marineindustryproj.service.dto.RequestOtherNiazsanjiCriteria;
import com.marineindustryproj.service.dto.RequestOtherNiazsanjiDTO;
import com.marineindustryproj.service.mapper.RequestOtherNiazsanjiMapper;

/**
 * Service for executing complex queries for RequestOtherNiazsanji entities in the database.
 * The main input is a {@link RequestOtherNiazsanjiCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link RequestOtherNiazsanjiDTO} or a {@link Page} of {@link RequestOtherNiazsanjiDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class RequestOtherNiazsanjiQueryService extends QueryService<RequestOtherNiazsanji> {

    private final Logger log = LoggerFactory.getLogger(RequestOtherNiazsanjiQueryService.class);

    private final RequestOtherNiazsanjiRepository requestOtherNiazsanjiRepository;

    private final RequestOtherNiazsanjiMapper requestOtherNiazsanjiMapper;

    public RequestOtherNiazsanjiQueryService(RequestOtherNiazsanjiRepository requestOtherNiazsanjiRepository, RequestOtherNiazsanjiMapper requestOtherNiazsanjiMapper) {
        this.requestOtherNiazsanjiRepository = requestOtherNiazsanjiRepository;
        this.requestOtherNiazsanjiMapper = requestOtherNiazsanjiMapper;
    }

    /**
     * Return a {@link List} of {@link RequestOtherNiazsanjiDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<RequestOtherNiazsanjiDTO> findByCriteria(RequestOtherNiazsanjiCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<RequestOtherNiazsanji> specification = createSpecification(criteria);
        return requestOtherNiazsanjiMapper.toDto(requestOtherNiazsanjiRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link RequestOtherNiazsanjiDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<RequestOtherNiazsanjiDTO> findByCriteria(RequestOtherNiazsanjiCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<RequestOtherNiazsanji> specification = createSpecification(criteria);
        return requestOtherNiazsanjiRepository.findAll(specification, page)
            .map(requestOtherNiazsanjiMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(RequestOtherNiazsanjiCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<RequestOtherNiazsanji> specification = createSpecification(criteria);
        return requestOtherNiazsanjiRepository.count(specification);
    }

    /**
     * Function to convert RequestOtherNiazsanjiCriteria to a {@link Specification}
     */
    private Specification<RequestOtherNiazsanji> createSpecification(RequestOtherNiazsanjiCriteria criteria) {
        Specification<RequestOtherNiazsanji> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), RequestOtherNiazsanji_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), RequestOtherNiazsanji_.code));
            }
            if (criteria.getCostEducationalModule() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCostEducationalModule(), RequestOtherNiazsanji_.costEducationalModule));
            }
            if (criteria.getRestrictionDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRestrictionDescription(), RequestOtherNiazsanji_.restrictionDescription));
            }
            if (criteria.getGoalsText() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGoalsText(), RequestOtherNiazsanji_.goalsText));
            }
            if (criteria.getPrerequisite() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPrerequisite(), RequestOtherNiazsanji_.prerequisite));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), RequestOtherNiazsanji_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), RequestOtherNiazsanji_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), RequestOtherNiazsanji_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), RequestOtherNiazsanji_.modifyDate));
            }
            if (criteria.getArchived() != null) {
                specification = specification.and(buildSpecification(criteria.getArchived(), RequestOtherNiazsanji_.archived));
            }
            if (criteria.getArchivedUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getArchivedUserLogin(), RequestOtherNiazsanji_.archivedUserLogin));
            }
            if (criteria.getArchivedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getArchivedDate(), RequestOtherNiazsanji_.archivedDate));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStatus(), RequestOtherNiazsanji_.status));
            }
            if (criteria.getRequestStatus() != null) {
                specification = specification.and(buildSpecification(criteria.getRequestStatus(), RequestOtherNiazsanji_.requestStatus));
            }
            if (criteria.getChangeStatusUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getChangeStatusUserLogin(), RequestOtherNiazsanji_.changeStatusUserLogin));
            }
            if (criteria.getGuid() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGuid(), RequestOtherNiazsanji_.guid));
            }
            if (criteria.getHasImportantMessage() != null) {
                specification = specification.and(buildSpecification(criteria.getHasImportantMessage(), RequestOtherNiazsanji_.hasImportantMessage));
            }
            if (criteria.getNiazsanjiOtherId() != null) {
                specification = specification.and(buildSpecification(criteria.getNiazsanjiOtherId(),
                    root -> root.join(RequestOtherNiazsanji_.niazsanjiOthers, JoinType.LEFT).get(NiazsanjiOther_.id)));
            }
            if (criteria.getPrioritizeRequestNiazsanjiId() != null) {
                specification = specification.and(buildSpecification(criteria.getPrioritizeRequestNiazsanjiId(),
                    root -> root.join(RequestOtherNiazsanji_.prioritizeRequestNiazsanjis, JoinType.LEFT).get(PrioritizeRequestNiazsanji_.id)));
            }
            if (criteria.getDocumentId() != null) {
                specification = specification.and(buildSpecification(criteria.getDocumentId(),
                    root -> root.join(RequestOtherNiazsanji_.documents, JoinType.LEFT).get(Document_.id)));
            }
            if (criteria.getRestrictionId() != null) {
                specification = specification.and(buildSpecification(criteria.getRestrictionId(),
                    root -> root.join(RequestOtherNiazsanji_.restrictions, JoinType.LEFT).get(Restriction_.id)));
            }
            if (criteria.getNiazsanjiInputId() != null) {
                specification = specification.and(buildSpecification(criteria.getNiazsanjiInputId(),
                    root -> root.join(RequestOtherNiazsanji_.niazsanjiInput, JoinType.LEFT).get(NiazsanjiInput_.id)));
            }
            if (criteria.getCourseTypeId() != null) {
                specification = specification.and(buildSpecification(criteria.getCourseTypeId(),
                    root -> root.join(RequestOtherNiazsanji_.courseType, JoinType.LEFT).get(CourseType_.id)));
            }
            if (criteria.getEducationalModuleId() != null) {
                specification = specification.and(buildSpecification(criteria.getEducationalModuleId(),
                    root -> root.join(RequestOtherNiazsanji_.educationalModule, JoinType.LEFT).get(EducationalModule_.id)));
            }
            if (criteria.getEducationalModuleTitle() != null) {
                specification = specification.and(buildSpecification(criteria.getEducationalModuleTitle(),
                    root -> root.join(RequestOtherNiazsanji_.educationalModule, JoinType.LEFT).get(EducationalModule_.title)));
            }
            if (criteria.getPersonId() != null) {
                specification = specification.and(buildSpecification(criteria.getPersonId(),
                    root -> root.join(RequestOtherNiazsanji_.person, JoinType.LEFT).get(Person_.id)));
            }
            if (criteria.getOrganizationChartId() != null) {
                specification = specification.and(buildSpecification(criteria.getOrganizationChartId(),
                    root -> root.join(RequestOtherNiazsanji_.organizationChart, JoinType.LEFT).get(OrganizationChart_.id)));
            }
            if (criteria.getTeachingApproachId() != null) {
                specification = specification.and(buildSpecification(criteria.getTeachingApproachId(),
                    root -> root.join(RequestOtherNiazsanji_.teachingApproach, JoinType.LEFT).get(TeachingApproach_.id)));
            }
        }
        return specification;
    }
}
