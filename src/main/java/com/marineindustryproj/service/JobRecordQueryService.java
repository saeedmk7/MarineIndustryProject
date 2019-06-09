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

import com.marineindustryproj.domain.JobRecord;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.JobRecordRepository;
import com.marineindustryproj.service.dto.JobRecordCriteria;
import com.marineindustryproj.service.dto.JobRecordDTO;
import com.marineindustryproj.service.mapper.JobRecordMapper;

/**
 * Service for executing complex queries for JobRecord entities in the database.
 * The main input is a {@link JobRecordCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link JobRecordDTO} or a {@link Page} of {@link JobRecordDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class JobRecordQueryService extends QueryService<JobRecord> {

    private final Logger log = LoggerFactory.getLogger(JobRecordQueryService.class);

    private final JobRecordRepository jobRecordRepository;

    private final JobRecordMapper jobRecordMapper;

    public JobRecordQueryService(JobRecordRepository jobRecordRepository, JobRecordMapper jobRecordMapper) {
        this.jobRecordRepository = jobRecordRepository;
        this.jobRecordMapper = jobRecordMapper;
    }

    /**
     * Return a {@link List} of {@link JobRecordDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<JobRecordDTO> findByCriteria(JobRecordCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<JobRecord> specification = createSpecification(criteria);
        return jobRecordMapper.toDto(jobRecordRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link JobRecordDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<JobRecordDTO> findByCriteria(JobRecordCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<JobRecord> specification = createSpecification(criteria);
        return jobRecordRepository.findAll(specification, page)
            .map(jobRecordMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(JobRecordCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<JobRecord> specification = createSpecification(criteria);
        return jobRecordRepository.count(specification);
    }

    /**
     * Function to convert JobRecordCriteria to a {@link Specification}
     */
    private Specification<JobRecord> createSpecification(JobRecordCriteria criteria) {
        Specification<JobRecord> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), JobRecord_.id));
            }
            if (criteria.getWorkOrganization() != null) {
                specification = specification.and(buildStringSpecification(criteria.getWorkOrganization(), JobRecord_.workOrganization));
            }
            if (criteria.getWorkSection() != null) {
                specification = specification.and(buildStringSpecification(criteria.getWorkSection(), JobRecord_.workSection));
            }
            if (criteria.getJobResponsibility() != null) {
                specification = specification.and(buildStringSpecification(criteria.getJobResponsibility(), JobRecord_.jobResponsibility));
            }
            if (criteria.getJobPosition() != null) {
                specification = specification.and(buildStringSpecification(criteria.getJobPosition(), JobRecord_.jobPosition));
            }
            if (criteria.getTotalHour() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTotalHour(), JobRecord_.totalHour));
            }
            if (criteria.getStartDate() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStartDate(), JobRecord_.startDate));
            }
            if (criteria.getEndDate() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEndDate(), JobRecord_.endDate));
            }
            if (criteria.getLeaveReason() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLeaveReason(), JobRecord_.leaveReason));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), JobRecord_.description));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), JobRecord_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), JobRecord_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), JobRecord_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), JobRecord_.modifyDate));
            }
            if (criteria.getGuid() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGuid(), JobRecord_.guid));
            }
            if (criteria.getPersonId() != null) {
                specification = specification.and(buildSpecification(criteria.getPersonId(),
                    root -> root.join(JobRecord_.person, JoinType.LEFT).get(Person_.id)));
            }
        }
        return specification;
    }
}
