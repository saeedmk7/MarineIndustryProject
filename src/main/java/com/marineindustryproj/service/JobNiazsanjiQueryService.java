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

import com.marineindustryproj.domain.JobNiazsanji;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.JobNiazsanjiRepository;
import com.marineindustryproj.service.dto.JobNiazsanjiCriteria;
import com.marineindustryproj.service.dto.JobNiazsanjiDTO;
import com.marineindustryproj.service.mapper.JobNiazsanjiMapper;

/**
 * Service for executing complex queries for JobNiazsanji entities in the database.
 * The main input is a {@link JobNiazsanjiCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link JobNiazsanjiDTO} or a {@link Page} of {@link JobNiazsanjiDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class JobNiazsanjiQueryService extends QueryService<JobNiazsanji> {

    private final Logger log = LoggerFactory.getLogger(JobNiazsanjiQueryService.class);

    private final JobNiazsanjiRepository jobNiazsanjiRepository;

    private final JobNiazsanjiMapper jobNiazsanjiMapper;

    public JobNiazsanjiQueryService(JobNiazsanjiRepository jobNiazsanjiRepository, JobNiazsanjiMapper jobNiazsanjiMapper) {
        this.jobNiazsanjiRepository = jobNiazsanjiRepository;
        this.jobNiazsanjiMapper = jobNiazsanjiMapper;
    }

    /**
     * Return a {@link List} of {@link JobNiazsanjiDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<JobNiazsanjiDTO> findByCriteria(JobNiazsanjiCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<JobNiazsanji> specification = createSpecification(criteria);
        return jobNiazsanjiMapper.toDto(jobNiazsanjiRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link JobNiazsanjiDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<JobNiazsanjiDTO> findByCriteria(JobNiazsanjiCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<JobNiazsanji> specification = createSpecification(criteria);
        return jobNiazsanjiRepository.findAll(specification, page)
            .map(jobNiazsanjiMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(JobNiazsanjiCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<JobNiazsanji> specification = createSpecification(criteria);
        return jobNiazsanjiRepository.count(specification);
    }

    /**
     * Function to convert JobNiazsanjiCriteria to a {@link Specification}
     */
    private Specification<JobNiazsanji> createSpecification(JobNiazsanjiCriteria criteria) {
        Specification<JobNiazsanji> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), JobNiazsanji_.id));
            }
            if (criteria.getNiazsanjiYear() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNiazsanjiYear(), JobNiazsanji_.niazsanjiYear));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), JobNiazsanji_.code));
            }
            if (criteria.getPriceCost() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPriceCost(), JobNiazsanji_.priceCost));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), JobNiazsanji_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), JobNiazsanji_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), JobNiazsanji_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), JobNiazsanji_.modifyDate));
            }
            if (criteria.getArchived() != null) {
                specification = specification.and(buildSpecification(criteria.getArchived(), JobNiazsanji_.archived));
            }
            if (criteria.getArchivedUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getArchivedUserLogin(), JobNiazsanji_.archivedUserLogin));
            }
            if (criteria.getArchivedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getArchivedDate(), JobNiazsanji_.archivedDate));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStatus(), JobNiazsanji_.status));
            }
            if (criteria.getChangeStatusUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getChangeStatusUserLogin(), JobNiazsanji_.changeStatusUserLogin));
            }
            if (criteria.getGuid() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGuid(), JobNiazsanji_.guid));
            }
            if (criteria.getHasImportantMessage() != null) {
                specification = specification.and(buildSpecification(criteria.getHasImportantMessage(), JobNiazsanji_.hasImportantMessage));
            }
            if (criteria.getRestrictionDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRestrictionDescription(), JobNiazsanji_.restrictionDescription));
            }
            if (criteria.getGoalsText() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGoalsText(), JobNiazsanji_.goalsText));
            }
            if (criteria.getPrerequisite() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPrerequisite(), JobNiazsanji_.prerequisite));
            }
            if (criteria.getDocumentId() != null) {
                specification = specification.and(buildSpecification(criteria.getDocumentId(),
                    root -> root.join(JobNiazsanji_.documents, JoinType.LEFT).get(Document_.id)));
            }
            if (criteria.getRestrictionId() != null) {
                specification = specification.and(buildSpecification(criteria.getRestrictionId(),
                    root -> root.join(JobNiazsanji_.restrictions, JoinType.LEFT).get(Restriction_.id)));
            }
            if (criteria.getCourseTypeId() != null) {
                specification = specification.and(buildSpecification(criteria.getCourseTypeId(),
                    root -> root.join(JobNiazsanji_.courseType, JoinType.LEFT).get(CourseType_.id)));
            }
            if (criteria.getPreJobNiazsanjiId() != null) {
                specification = specification.and(buildSpecification(criteria.getPreJobNiazsanjiId(),
                    root -> root.join(JobNiazsanji_.preJobNiazsanji, JoinType.LEFT).get(PreJobNiazsanji_.id)));
            }
            if (criteria.getEducationalModuleId() != null) {
                specification = specification.and(buildSpecification(criteria.getEducationalModuleId(),
                    root -> root.join(JobNiazsanji_.educationalModule, JoinType.LEFT).get(EducationalModule_.id)));
            }
            if (criteria.getPersonId() != null) {
                specification = specification.and(buildSpecification(criteria.getPersonId(),
                    root -> root.join(JobNiazsanji_.person, JoinType.LEFT).get(Person_.id)));
            }
            if (criteria.getOrganizationChartId() != null) {
                specification = specification.and(buildSpecification(criteria.getOrganizationChartId(),
                    root -> root.join(JobNiazsanji_.organizationChart, JoinType.LEFT).get(OrganizationChart_.id)));
            }
            if (criteria.getTeachingApproachId() != null) {
                specification = specification.and(buildSpecification(criteria.getTeachingApproachId(),
                    root -> root.join(JobNiazsanji_.teachingApproach, JoinType.LEFT).get(TeachingApproach_.id)));
            }
        }
        return specification;
    }
}
