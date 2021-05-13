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

import com.marineindustryproj.domain.ApplicationProcessStep;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.ApplicationProcessStepRepository;
import com.marineindustryproj.service.dto.ApplicationProcessStepCriteria;
import com.marineindustryproj.service.dto.ApplicationProcessStepDTO;
import com.marineindustryproj.service.mapper.ApplicationProcessStepMapper;

/**
 * Service for executing complex queries for ApplicationProcessStep entities in the database.
 * The main input is a {@link ApplicationProcessStepCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ApplicationProcessStepDTO} or a {@link Page} of {@link ApplicationProcessStepDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ApplicationProcessStepQueryService extends QueryService<ApplicationProcessStep> {

    private final Logger log = LoggerFactory.getLogger(ApplicationProcessStepQueryService.class);

    private final ApplicationProcessStepRepository applicationProcessStepRepository;

    private final ApplicationProcessStepMapper applicationProcessStepMapper;

    public ApplicationProcessStepQueryService(ApplicationProcessStepRepository applicationProcessStepRepository, ApplicationProcessStepMapper applicationProcessStepMapper) {
        this.applicationProcessStepRepository = applicationProcessStepRepository;
        this.applicationProcessStepMapper = applicationProcessStepMapper;
    }

    /**
     * Return a {@link List} of {@link ApplicationProcessStepDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ApplicationProcessStepDTO> findByCriteria(ApplicationProcessStepCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ApplicationProcessStep> specification = createSpecification(criteria);
        return applicationProcessStepMapper.toDto(applicationProcessStepRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ApplicationProcessStepDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ApplicationProcessStepDTO> findByCriteria(ApplicationProcessStepCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ApplicationProcessStep> specification = createSpecification(criteria);
        return applicationProcessStepRepository.findAll(specification, page)
            .map(applicationProcessStepMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ApplicationProcessStepCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ApplicationProcessStep> specification = createSpecification(criteria);
        return applicationProcessStepRepository.count(specification);
    }

    /**
     * Function to convert ApplicationProcessStepCriteria to a {@link Specification}
     */
    private Specification<ApplicationProcessStep> createSpecification(ApplicationProcessStepCriteria criteria) {
        Specification<ApplicationProcessStep> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ApplicationProcessStep_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), ApplicationProcessStep_.title));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), ApplicationProcessStep_.description));
            }
            if (criteria.getStepNumber() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStepNumber(), ApplicationProcessStep_.stepNumber));
            }
            if (criteria.getStepRequired() != null) {
                specification = specification.and(buildSpecification(criteria.getStepRequired(), ApplicationProcessStep_.stepRequired));
            }
            if (criteria.getFileDocRequired() != null) {
                specification = specification.and(buildSpecification(criteria.getFileDocRequired(), ApplicationProcessStep_.fileDocRequired));
            }
            if (criteria.getColorText() != null) {
                specification = specification.and(buildStringSpecification(criteria.getColorText(), ApplicationProcessStep_.colorText));
            }
            if (criteria.getIsHeader() != null) {
                specification = specification.and(buildSpecification(criteria.getIsHeader(), ApplicationProcessStep_.isHeader));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), ApplicationProcessStep_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), ApplicationProcessStep_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), ApplicationProcessStep_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), ApplicationProcessStep_.modifyDate));
            }
            if (criteria.getArchived() != null) {
                specification = specification.and(buildSpecification(criteria.getArchived(), ApplicationProcessStep_.archived));
            }
            if (criteria.getArchivedUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getArchivedUserLogin(), ApplicationProcessStep_.archivedUserLogin));
            }
            if (criteria.getArchivedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getArchivedDate(), ApplicationProcessStep_.archivedDate));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStatus(), ApplicationProcessStep_.status));
            }
            if (criteria.getApplicationProcessRunStepId() != null) {
                specification = specification.and(buildSpecification(criteria.getApplicationProcessRunStepId(),
                    root -> root.join(ApplicationProcessStep_.applicationProcessRunSteps, JoinType.LEFT).get(ApplicationProcessRunStep_.id)));
            }
        }
        return specification;
    }
}
