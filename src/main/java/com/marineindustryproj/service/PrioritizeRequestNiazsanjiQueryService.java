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

import com.marineindustryproj.domain.PrioritizeRequestNiazsanji;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.PrioritizeRequestNiazsanjiRepository;
import com.marineindustryproj.service.dto.PrioritizeRequestNiazsanjiCriteria;
import com.marineindustryproj.service.dto.PrioritizeRequestNiazsanjiDTO;
import com.marineindustryproj.service.mapper.PrioritizeRequestNiazsanjiMapper;

/**
 * Service for executing complex queries for PrioritizeRequestNiazsanji entities in the database.
 * The main input is a {@link PrioritizeRequestNiazsanjiCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PrioritizeRequestNiazsanjiDTO} or a {@link Page} of {@link PrioritizeRequestNiazsanjiDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PrioritizeRequestNiazsanjiQueryService extends QueryService<PrioritizeRequestNiazsanji> {

    private final Logger log = LoggerFactory.getLogger(PrioritizeRequestNiazsanjiQueryService.class);

    private final PrioritizeRequestNiazsanjiRepository prioritizeRequestNiazsanjiRepository;

    private final PrioritizeRequestNiazsanjiMapper prioritizeRequestNiazsanjiMapper;

    public PrioritizeRequestNiazsanjiQueryService(PrioritizeRequestNiazsanjiRepository prioritizeRequestNiazsanjiRepository, PrioritizeRequestNiazsanjiMapper prioritizeRequestNiazsanjiMapper) {
        this.prioritizeRequestNiazsanjiRepository = prioritizeRequestNiazsanjiRepository;
        this.prioritizeRequestNiazsanjiMapper = prioritizeRequestNiazsanjiMapper;
    }

    /**
     * Return a {@link List} of {@link PrioritizeRequestNiazsanjiDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<PrioritizeRequestNiazsanjiDTO> findByCriteria(PrioritizeRequestNiazsanjiCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<PrioritizeRequestNiazsanji> specification = createSpecification(criteria);
        return prioritizeRequestNiazsanjiMapper.toDto(prioritizeRequestNiazsanjiRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link PrioritizeRequestNiazsanjiDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<PrioritizeRequestNiazsanjiDTO> findByCriteria(PrioritizeRequestNiazsanjiCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<PrioritizeRequestNiazsanji> specification = createSpecification(criteria);
        return prioritizeRequestNiazsanjiRepository.findAll(specification, page)
            .map(prioritizeRequestNiazsanjiMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(PrioritizeRequestNiazsanjiCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<PrioritizeRequestNiazsanji> specification = createSpecification(criteria);
        return prioritizeRequestNiazsanjiRepository.count(specification);
    }

    /**
     * Function to convert PrioritizeRequestNiazsanjiCriteria to a {@link Specification}
     */
    private Specification<PrioritizeRequestNiazsanji> createSpecification(PrioritizeRequestNiazsanjiCriteria criteria) {
        Specification<PrioritizeRequestNiazsanji> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), PrioritizeRequestNiazsanji_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), PrioritizeRequestNiazsanji_.code));
            }
            if (criteria.getCostEducationalModule() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCostEducationalModule(), PrioritizeRequestNiazsanji_.costEducationalModule));
            }
            if (criteria.getEducationalModuleType() != null) {
                specification = specification.and(buildSpecification(criteria.getEducationalModuleType(), PrioritizeRequestNiazsanji_.educationalModuleType));
            }
            if (criteria.getRestrictionDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRestrictionDescription(), PrioritizeRequestNiazsanji_.restrictionDescription));
            }
            if (criteria.getGoalsText() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGoalsText(), PrioritizeRequestNiazsanji_.goalsText));
            }
            if (criteria.getPrerequisite() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPrerequisite(), PrioritizeRequestNiazsanji_.prerequisite));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), PrioritizeRequestNiazsanji_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), PrioritizeRequestNiazsanji_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), PrioritizeRequestNiazsanji_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), PrioritizeRequestNiazsanji_.modifyDate));
            }
            if (criteria.getArchived() != null) {
                specification = specification.and(buildSpecification(criteria.getArchived(), PrioritizeRequestNiazsanji_.archived));
            }
            if (criteria.getArchivedUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getArchivedUserLogin(), PrioritizeRequestNiazsanji_.archivedUserLogin));
            }
            if (criteria.getArchivedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getArchivedDate(), PrioritizeRequestNiazsanji_.archivedDate));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStatus(), PrioritizeRequestNiazsanji_.status));
            }
            if (criteria.getRequestStatus() != null) {
                specification = specification.and(buildSpecification(criteria.getRequestStatus(), PrioritizeRequestNiazsanji_.requestStatus));
            }
            if (criteria.getChangeStatusUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getChangeStatusUserLogin(), PrioritizeRequestNiazsanji_.changeStatusUserLogin));
            }
            if (criteria.getGuid() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGuid(), PrioritizeRequestNiazsanji_.guid));
            }
            if (criteria.getHasImportantMessage() != null) {
                specification = specification.and(buildSpecification(criteria.getHasImportantMessage(), PrioritizeRequestNiazsanji_.hasImportantMessage));
            }
            if (criteria.getRequestNiazsanjiType() != null) {
                specification = specification.and(buildSpecification(criteria.getRequestNiazsanjiType(), PrioritizeRequestNiazsanji_.requestNiazsanjiType));
            }
            if (criteria.getPriority() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPriority(), PrioritizeRequestNiazsanji_.priority));
            }
            if (criteria.getNiazsanjiIntegrationId() != null) {
                specification = specification.and(buildSpecification(criteria.getNiazsanjiIntegrationId(),
                    root -> root.join(PrioritizeRequestNiazsanji_.niazsanjiIntegrations, JoinType.LEFT).get(NiazsanjiIntegration_.id)));
            }
            if (criteria.getDocumentId() != null) {
                specification = specification.and(buildSpecification(criteria.getDocumentId(),
                    root -> root.join(PrioritizeRequestNiazsanji_.documents, JoinType.LEFT).get(Document_.id)));
            }
            if (criteria.getRestrictionId() != null) {
                specification = specification.and(buildSpecification(criteria.getRestrictionId(),
                    root -> root.join(PrioritizeRequestNiazsanji_.restrictions, JoinType.LEFT).get(Restriction_.id)));
            }
            if (criteria.getRequestNiazsanjiFardiId() != null) {
                specification = specification.and(buildSpecification(criteria.getRequestNiazsanjiFardiId(),
                    root -> root.join(PrioritizeRequestNiazsanji_.requestNiazsanjiFardi, JoinType.LEFT).get(RequestNiazsanjiFardi_.id)));
            }
            if (criteria.getPreJobNiazsanjiId() != null) {
                specification = specification.and(buildSpecification(criteria.getPreJobNiazsanjiId(),
                    root -> root.join(PrioritizeRequestNiazsanji_.preJobNiazsanji, JoinType.LEFT).get(PreJobNiazsanji_.id)));
            }
            if (criteria.getRequestOtherNiazsanjiId() != null) {
                specification = specification.and(buildSpecification(criteria.getRequestOtherNiazsanjiId(),
                    root -> root.join(PrioritizeRequestNiazsanji_.requestOtherNiazsanji, JoinType.LEFT).get(RequestOtherNiazsanji_.id)));
            }
            if (criteria.getNiazsanjiInputId() != null) {
                specification = specification.and(buildSpecification(criteria.getNiazsanjiInputId(),
                    root -> root.join(PrioritizeRequestNiazsanji_.niazsanjiInput, JoinType.LEFT).get(NiazsanjiInput_.id)));
            }
            if (criteria.getCourseTypeId() != null) {
                specification = specification.and(buildSpecification(criteria.getCourseTypeId(),
                    root -> root.join(PrioritizeRequestNiazsanji_.courseType, JoinType.LEFT).get(CourseType_.id)));
            }
            if (criteria.getEducationalModuleId() != null) {
                specification = specification.and(buildSpecification(criteria.getEducationalModuleId(),
                    root -> root.join(PrioritizeRequestNiazsanji_.educationalModule, JoinType.LEFT).get(EducationalModule_.id)));
            }
            if (criteria.getEducationalModuleTitle() != null) {
                specification = specification.and(buildSpecification(criteria.getEducationalModuleTitle(),
                    root -> root.join(PrioritizeRequestNiazsanji_.educationalModule, JoinType.LEFT).get(EducationalModule_.title)));
            }
            if (criteria.getEducationalModuleCode() != null) {
                specification = specification.and(buildSpecification(criteria.getEducationalModuleCode(),
                    root -> root.join(PrioritizeRequestNiazsanji_.educationalModule, JoinType.LEFT).get(EducationalModule_.code)));
            }
            if (criteria.getPersonId() != null) {
                specification = specification.and(buildSpecification(criteria.getPersonId(),
                    root -> root.join(PrioritizeRequestNiazsanji_.person, JoinType.LEFT).get(Person_.id)));
            }
            if (criteria.getOrganizationChartId() != null) {
                specification = specification.and(buildSpecification(criteria.getOrganizationChartId(),
                    root -> root.join(PrioritizeRequestNiazsanji_.organizationChart, JoinType.LEFT).get(OrganizationChart_.id)));
            }
            if (criteria.getTeachingApproachId() != null) {
                specification = specification.and(buildSpecification(criteria.getTeachingApproachId(),
                    root -> root.join(PrioritizeRequestNiazsanji_.teachingApproach, JoinType.LEFT).get(TeachingApproach_.id)));
            }
        }
        return specification;
    }
}
