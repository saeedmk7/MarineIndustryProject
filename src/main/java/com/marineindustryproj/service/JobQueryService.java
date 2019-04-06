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

import com.marineindustryproj.domain.Job;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.JobRepository;
import com.marineindustryproj.service.dto.JobCriteria;
import com.marineindustryproj.service.dto.JobDTO;
import com.marineindustryproj.service.mapper.JobMapper;

/**
 * Service for executing complex queries for Job entities in the database.
 * The main input is a {@link JobCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link JobDTO} or a {@link Page} of {@link JobDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class JobQueryService extends QueryService<Job> {

    private final Logger log = LoggerFactory.getLogger(JobQueryService.class);

    private final JobRepository jobRepository;

    private final JobMapper jobMapper;

    public JobQueryService(JobRepository jobRepository, JobMapper jobMapper) {
        this.jobRepository = jobRepository;
        this.jobMapper = jobMapper;
    }

    /**
     * Return a {@link List} of {@link JobDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<JobDTO> findByCriteria(JobCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Job> specification = createSpecification(criteria);
        return jobMapper.toDto(jobRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link JobDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<JobDTO> findByCriteria(JobCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Job> specification = createSpecification(criteria);
        return jobRepository.findAll(specification, page)
            .map(jobMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(JobCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Job> specification = createSpecification(criteria);
        return jobRepository.count(specification);
    }

    /**
     * Function to convert JobCriteria to a {@link Specification}
     */
    private Specification<Job> createSpecification(JobCriteria criteria) {
        Specification<Job> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Job_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), Job_.title));
            }
            if (criteria.getJobKey() != null) {
                specification = specification.and(buildStringSpecification(criteria.getJobKey(), Job_.jobKey));
            }
            if (criteria.getJobCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getJobCode(), Job_.jobCode));
            }
            if (criteria.getFirst3JobCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFirst3JobCode(), Job_.first3JobCode));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), Job_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), Job_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), Job_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), Job_.modifyDate));
            }
            if (criteria.getArchived() != null) {
                specification = specification.and(buildSpecification(criteria.getArchived(), Job_.archived));
            }
            if (criteria.getArchivedUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getArchivedUserLogin(), Job_.archivedUserLogin));
            }
            if (criteria.getArchivedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getArchivedDate(), Job_.archivedDate));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStatus(), Job_.status));
            }
            if (criteria.getJobPersonId() != null) {
                specification = specification.and(buildSpecification(criteria.getJobPersonId(),
                    root -> root.join(Job_.jobPeople, JoinType.LEFT).get(Person_.id)));
            }
            if (criteria.getPracticaljobPersonId() != null) {
                specification = specification.and(buildSpecification(criteria.getPracticaljobPersonId(),
                    root -> root.join(Job_.practicaljobPeople, JoinType.LEFT).get(Person_.id)));
            }
            if (criteria.getJobId() != null) {
                specification = specification.and(buildSpecification(criteria.getJobId(),
                    root -> root.join(Job_.jobs, JoinType.LEFT).get(Job_.id)));
            }
            if (criteria.getEducationalModuleJobId() != null) {
                specification = specification.and(buildSpecification(criteria.getEducationalModuleJobId(),
                    root -> root.join(Job_.educationalModuleJobs, JoinType.LEFT).get(EducationalModuleJob_.id)));
            }
            if (criteria.getDocumentId() != null) {
                specification = specification.and(buildSpecification(criteria.getDocumentId(),
                    root -> root.join(Job_.documents, JoinType.LEFT).get(Document_.id)));
            }
            if (criteria.getRasteId() != null) {
                specification = specification.and(buildSpecification(criteria.getRasteId(),
                    root -> root.join(Job_.raste, JoinType.LEFT).get(Raste_.id)));
            }
            if (criteria.getRadehId() != null) {
                specification = specification.and(buildSpecification(criteria.getRadehId(),
                    root -> root.join(Job_.radeh, JoinType.LEFT).get(Radeh_.id)));
            }
            if (criteria.getJobTypeId() != null) {
                specification = specification.and(buildSpecification(criteria.getJobTypeId(),
                    root -> root.join(Job_.jobType, JoinType.LEFT).get(JobType_.id)));
            }
            if (criteria.getScientificWorkGroupId() != null) {
                specification = specification.and(buildSpecification(criteria.getScientificWorkGroupId(),
                    root -> root.join(Job_.scientificWorkGroup, JoinType.LEFT).get(ScientificWorkGroup_.id)));
            }
            if (criteria.getParentId() != null) {
                specification = specification.and(buildSpecification(criteria.getParentId(),
                    root -> root.join(Job_.parent, JoinType.LEFT).get(Job_.id)));
            }
            if (criteria.getMainTaskId() != null) {
                specification = specification.and(buildSpecification(criteria.getMainTaskId(),
                    root -> root.join(Job_.mainTasks, JoinType.LEFT).get(MainTask_.id)));
            }
            if (criteria.getNiazsanjiGroupId() != null) {
                specification = specification.and(buildSpecification(criteria.getNiazsanjiGroupId(),
                    root -> root.join(Job_.niazsanjiGroups, JoinType.LEFT).get(NiazsanjiGroup_.id)));
            }
        }
        return specification;
    }
}
