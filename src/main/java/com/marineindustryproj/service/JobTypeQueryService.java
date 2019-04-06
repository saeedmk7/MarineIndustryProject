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

import com.marineindustryproj.domain.JobType;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.JobTypeRepository;
import com.marineindustryproj.service.dto.JobTypeCriteria;
import com.marineindustryproj.service.dto.JobTypeDTO;
import com.marineindustryproj.service.mapper.JobTypeMapper;

/**
 * Service for executing complex queries for JobType entities in the database.
 * The main input is a {@link JobTypeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link JobTypeDTO} or a {@link Page} of {@link JobTypeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class JobTypeQueryService extends QueryService<JobType> {

    private final Logger log = LoggerFactory.getLogger(JobTypeQueryService.class);

    private final JobTypeRepository jobTypeRepository;

    private final JobTypeMapper jobTypeMapper;

    public JobTypeQueryService(JobTypeRepository jobTypeRepository, JobTypeMapper jobTypeMapper) {
        this.jobTypeRepository = jobTypeRepository;
        this.jobTypeMapper = jobTypeMapper;
    }

    /**
     * Return a {@link List} of {@link JobTypeDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<JobTypeDTO> findByCriteria(JobTypeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<JobType> specification = createSpecification(criteria);
        return jobTypeMapper.toDto(jobTypeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link JobTypeDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<JobTypeDTO> findByCriteria(JobTypeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<JobType> specification = createSpecification(criteria);
        return jobTypeRepository.findAll(specification, page)
            .map(jobTypeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(JobTypeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<JobType> specification = createSpecification(criteria);
        return jobTypeRepository.count(specification);
    }

    /**
     * Function to convert JobTypeCriteria to a {@link Specification}
     */
    private Specification<JobType> createSpecification(JobTypeCriteria criteria) {
        Specification<JobType> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), JobType_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), JobType_.title));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), JobType_.code));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), JobType_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), JobType_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), JobType_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), JobType_.modifyDate));
            }
            if (criteria.getJobId() != null) {
                specification = specification.and(buildSpecification(criteria.getJobId(),
                    root -> root.join(JobType_.jobs, JoinType.LEFT).get(Job_.id)));
            }
        }
        return specification;
    }
}
