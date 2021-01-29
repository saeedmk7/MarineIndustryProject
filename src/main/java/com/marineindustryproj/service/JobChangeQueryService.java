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

import com.marineindustryproj.domain.JobChange;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.JobChangeRepository;
import com.marineindustryproj.service.dto.JobChangeCriteria;
import com.marineindustryproj.service.dto.JobChangeDTO;
import com.marineindustryproj.service.mapper.JobChangeMapper;

/**
 * Service for executing complex queries for JobChange entities in the database.
 * The main input is a {@link JobChangeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link JobChangeDTO} or a {@link Page} of {@link JobChangeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class JobChangeQueryService extends QueryService<JobChange> {

    private final Logger log = LoggerFactory.getLogger(JobChangeQueryService.class);

    private final JobChangeRepository jobChangeRepository;

    private final JobChangeMapper jobChangeMapper;

    public JobChangeQueryService(JobChangeRepository jobChangeRepository, JobChangeMapper jobChangeMapper) {
        this.jobChangeRepository = jobChangeRepository;
        this.jobChangeMapper = jobChangeMapper;
    }

    /**
     * Return a {@link List} of {@link JobChangeDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<JobChangeDTO> findByCriteria(JobChangeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<JobChange> specification = createSpecification(criteria);
        return jobChangeMapper.toDto(jobChangeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link JobChangeDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<JobChangeDTO> findByCriteria(JobChangeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<JobChange> specification = createSpecification(criteria);
        return jobChangeRepository.findAll(specification, page)
            .map(jobChangeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(JobChangeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<JobChange> specification = createSpecification(criteria);
        return jobChangeRepository.count(specification);
    }

    /**
     * Function to convert JobChangeCriteria to a {@link Specification}
     */
    private Specification<JobChange> createSpecification(JobChangeCriteria criteria) {
        Specification<JobChange> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), JobChange_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), JobChange_.title));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStatus(), JobChange_.status));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), JobChange_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), JobChange_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), JobChange_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), JobChange_.modifyDate));
            }
            if (criteria.getPersonId() != null) {
                specification = specification.and(buildSpecification(criteria.getPersonId(),
                    root -> root.join(JobChange_.person, JoinType.LEFT).get(Person_.id)));
            }
            if (criteria.getOldJobId() != null) {
                specification = specification.and(buildSpecification(criteria.getOldJobId(),
                    root -> root.join(JobChange_.oldJob, JoinType.LEFT).get(Job_.id)));
            }
            if (criteria.getNewJobId() != null) {
                specification = specification.and(buildSpecification(criteria.getNewJobId(),
                    root -> root.join(JobChange_.newJob, JoinType.LEFT).get(Job_.id)));
            }
        }
        return specification;
    }
}
