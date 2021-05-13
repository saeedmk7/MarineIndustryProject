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

import com.marineindustryproj.domain.ApplicationProcessRunStep;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.ApplicationProcessRunStepRepository;
import com.marineindustryproj.service.dto.ApplicationProcessRunStepCriteria;
import com.marineindustryproj.service.dto.ApplicationProcessRunStepDTO;
import com.marineindustryproj.service.mapper.ApplicationProcessRunStepMapper;

/**
 * Service for executing complex queries for ApplicationProcessRunStep entities in the database.
 * The main input is a {@link ApplicationProcessRunStepCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ApplicationProcessRunStepDTO} or a {@link Page} of {@link ApplicationProcessRunStepDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ApplicationProcessRunStepQueryService extends QueryService<ApplicationProcessRunStep> {

    private final Logger log = LoggerFactory.getLogger(ApplicationProcessRunStepQueryService.class);

    private final ApplicationProcessRunStepRepository applicationProcessRunStepRepository;

    private final ApplicationProcessRunStepMapper applicationProcessRunStepMapper;

    public ApplicationProcessRunStepQueryService(ApplicationProcessRunStepRepository applicationProcessRunStepRepository, ApplicationProcessRunStepMapper applicationProcessRunStepMapper) {
        this.applicationProcessRunStepRepository = applicationProcessRunStepRepository;
        this.applicationProcessRunStepMapper = applicationProcessRunStepMapper;
    }

    /**
     * Return a {@link List} of {@link ApplicationProcessRunStepDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ApplicationProcessRunStepDTO> findByCriteria(ApplicationProcessRunStepCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ApplicationProcessRunStep> specification = createSpecification(criteria);
        return applicationProcessRunStepMapper.toDto(applicationProcessRunStepRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ApplicationProcessRunStepDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ApplicationProcessRunStepDTO> findByCriteria(ApplicationProcessRunStepCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ApplicationProcessRunStep> specification = createSpecification(criteria);
        return applicationProcessRunStepRepository.findAll(specification, page)
            .map(applicationProcessRunStepMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ApplicationProcessRunStepCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ApplicationProcessRunStep> specification = createSpecification(criteria);
        return applicationProcessRunStepRepository.count(specification);
    }

    /**
     * Function to convert ApplicationProcessRunStepCriteria to a {@link Specification}
     */
    private Specification<ApplicationProcessRunStep> createSpecification(ApplicationProcessRunStepCriteria criteria) {
        Specification<ApplicationProcessRunStep> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ApplicationProcessRunStep_.id));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), ApplicationProcessRunStep_.description));
            }
            if (criteria.getDone() != null) {
                specification = specification.and(buildSpecification(criteria.getDone(), ApplicationProcessRunStep_.done));
            }
            if (criteria.getDoneUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDoneUserLogin(), ApplicationProcessRunStep_.doneUserLogin));
            }
            if (criteria.getDoneDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDoneDate(), ApplicationProcessRunStep_.doneDate));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), ApplicationProcessRunStep_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), ApplicationProcessRunStep_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), ApplicationProcessRunStep_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), ApplicationProcessRunStep_.modifyDate));
            }
            if (criteria.getApplicationProcessId() != null) {
                specification = specification.and(buildSpecification(criteria.getApplicationProcessId(),
                    root -> root.join(ApplicationProcessRunStep_.applicationProcess, JoinType.LEFT).get(ApplicationProcess_.id)));
            }
            if (criteria.getApplicationProcessStepId() != null) {
                specification = specification.and(buildSpecification(criteria.getApplicationProcessStepId(),
                    root -> root.join(ApplicationProcessRunStep_.applicationProcessStep, JoinType.LEFT).get(ApplicationProcessStep_.id)));
            }
        }
        return specification;
    }
}
