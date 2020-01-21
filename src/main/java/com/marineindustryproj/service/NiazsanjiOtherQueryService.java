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

import com.marineindustryproj.domain.NiazsanjiOther;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.NiazsanjiOtherRepository;
import com.marineindustryproj.service.dto.NiazsanjiOtherCriteria;
import com.marineindustryproj.service.dto.NiazsanjiOtherDTO;
import com.marineindustryproj.service.mapper.NiazsanjiOtherMapper;

/**
 * Service for executing complex queries for NiazsanjiOther entities in the database.
 * The main input is a {@link NiazsanjiOtherCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link NiazsanjiOtherDTO} or a {@link Page} of {@link NiazsanjiOtherDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class NiazsanjiOtherQueryService extends QueryService<NiazsanjiOther> {

    private final Logger log = LoggerFactory.getLogger(NiazsanjiOtherQueryService.class);

    private final NiazsanjiOtherRepository niazsanjiOtherRepository;

    private final NiazsanjiOtherMapper niazsanjiOtherMapper;

    public NiazsanjiOtherQueryService(NiazsanjiOtherRepository niazsanjiOtherRepository, NiazsanjiOtherMapper niazsanjiOtherMapper) {
        this.niazsanjiOtherRepository = niazsanjiOtherRepository;
        this.niazsanjiOtherMapper = niazsanjiOtherMapper;
    }

    /**
     * Return a {@link List} of {@link NiazsanjiOtherDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<NiazsanjiOtherDTO> findByCriteria(NiazsanjiOtherCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<NiazsanjiOther> specification = createSpecification(criteria);
        return niazsanjiOtherMapper.toDto(niazsanjiOtherRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link NiazsanjiOtherDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<NiazsanjiOtherDTO> findByCriteria(NiazsanjiOtherCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<NiazsanjiOther> specification = createSpecification(criteria);
        return niazsanjiOtherRepository.findAll(specification, page)
            .map(niazsanjiOtherMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(NiazsanjiOtherCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<NiazsanjiOther> specification = createSpecification(criteria);
        return niazsanjiOtherRepository.count(specification);
    }

    /**
     * Function to convert NiazsanjiOtherCriteria to a {@link Specification}
     */
    private Specification<NiazsanjiOther> createSpecification(NiazsanjiOtherCriteria criteria) {
        Specification<NiazsanjiOther> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), NiazsanjiOther_.id));
            }
            if (criteria.getNiazsanjiYear() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNiazsanjiYear(), NiazsanjiOther_.niazsanjiYear));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), NiazsanjiOther_.code));
            }
            if (criteria.getPriceCost() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPriceCost(), NiazsanjiOther_.priceCost));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), NiazsanjiOther_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), NiazsanjiOther_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), NiazsanjiOther_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), NiazsanjiOther_.modifyDate));
            }
            if (criteria.getArchived() != null) {
                specification = specification.and(buildSpecification(criteria.getArchived(), NiazsanjiOther_.archived));
            }
            if (criteria.getArchivedUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getArchivedUserLogin(), NiazsanjiOther_.archivedUserLogin));
            }
            if (criteria.getArchivedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getArchivedDate(), NiazsanjiOther_.archivedDate));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStatus(), NiazsanjiOther_.status));
            }
            if (criteria.getChangeStatusUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getChangeStatusUserLogin(), NiazsanjiOther_.changeStatusUserLogin));
            }
            if (criteria.getGuid() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGuid(), NiazsanjiOther_.guid));
            }
            if (criteria.getHasImportantMessage() != null) {
                specification = specification.and(buildSpecification(criteria.getHasImportantMessage(), NiazsanjiOther_.hasImportantMessage));
            }
            if (criteria.getRestrictionDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRestrictionDescription(), NiazsanjiOther_.restrictionDescription));
            }
            if (criteria.getGoalsText() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGoalsText(), NiazsanjiOther_.goalsText));
            }
            if (criteria.getPrerequisite() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPrerequisite(), NiazsanjiOther_.prerequisite));
            }
            if (criteria.getDocumentId() != null) {
                specification = specification.and(buildSpecification(criteria.getDocumentId(),
                    root -> root.join(NiazsanjiOther_.documents, JoinType.LEFT).get(Document_.id)));
            }
            if (criteria.getRestrictionId() != null) {
                specification = specification.and(buildSpecification(criteria.getRestrictionId(),
                    root -> root.join(NiazsanjiOther_.restrictions, JoinType.LEFT).get(Restriction_.id)));
            }
            if (criteria.getNiazsanjiInputId() != null) {
                specification = specification.and(buildSpecification(criteria.getNiazsanjiInputId(),
                    root -> root.join(NiazsanjiOther_.niazsanjiInput, JoinType.LEFT).get(NiazsanjiInput_.id)));
            }
            if (criteria.getCourseTypeId() != null) {
                specification = specification.and(buildSpecification(criteria.getCourseTypeId(),
                    root -> root.join(NiazsanjiOther_.courseType, JoinType.LEFT).get(CourseType_.id)));
            }
            if (criteria.getRequestOtherNiazsanjiId() != null) {
                specification = specification.and(buildSpecification(criteria.getRequestOtherNiazsanjiId(),
                    root -> root.join(NiazsanjiOther_.requestOtherNiazsanji, JoinType.LEFT).get(RequestOtherNiazsanji_.id)));
            }
            if (criteria.getEducationalModuleId() != null) {
                specification = specification.and(buildSpecification(criteria.getEducationalModuleId(),
                    root -> root.join(NiazsanjiOther_.educationalModule, JoinType.LEFT).get(EducationalModule_.id)));
            }
            if (criteria.getPersonId() != null) {
                specification = specification.and(buildSpecification(criteria.getPersonId(),
                    root -> root.join(NiazsanjiOther_.person, JoinType.LEFT).get(Person_.id)));
            }
            if (criteria.getOrganizationChartId() != null) {
                specification = specification.and(buildSpecification(criteria.getOrganizationChartId(),
                    root -> root.join(NiazsanjiOther_.organizationChart, JoinType.LEFT).get(OrganizationChart_.id)));
            }
            if (criteria.getTeachingApproachId() != null) {
                specification = specification.and(buildSpecification(criteria.getTeachingApproachId(),
                    root -> root.join(NiazsanjiOther_.teachingApproach, JoinType.LEFT).get(TeachingApproach_.id)));
            }
        }
        return specification;
    }
}
