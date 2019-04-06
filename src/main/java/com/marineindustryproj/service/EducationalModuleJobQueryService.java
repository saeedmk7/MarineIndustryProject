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

import com.marineindustryproj.domain.EducationalModuleJob;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.EducationalModuleJobRepository;
import com.marineindustryproj.service.dto.EducationalModuleJobCriteria;
import com.marineindustryproj.service.dto.EducationalModuleJobDTO;
import com.marineindustryproj.service.mapper.EducationalModuleJobMapper;

/**
 * Service for executing complex queries for EducationalModuleJob entities in the database.
 * The main input is a {@link EducationalModuleJobCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link EducationalModuleJobDTO} or a {@link Page} of {@link EducationalModuleJobDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class EducationalModuleJobQueryService extends QueryService<EducationalModuleJob> {

    private final Logger log = LoggerFactory.getLogger(EducationalModuleJobQueryService.class);

    private final EducationalModuleJobRepository educationalModuleJobRepository;

    private final EducationalModuleJobMapper educationalModuleJobMapper;

    public EducationalModuleJobQueryService(EducationalModuleJobRepository educationalModuleJobRepository, EducationalModuleJobMapper educationalModuleJobMapper) {
        this.educationalModuleJobRepository = educationalModuleJobRepository;
        this.educationalModuleJobMapper = educationalModuleJobMapper;
    }

    /**
     * Return a {@link List} of {@link EducationalModuleJobDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<EducationalModuleJobDTO> findByCriteria(EducationalModuleJobCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<EducationalModuleJob> specification = createSpecification(criteria);
        return educationalModuleJobMapper.toDto(educationalModuleJobRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link EducationalModuleJobDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<EducationalModuleJobDTO> findByCriteria(EducationalModuleJobCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<EducationalModuleJob> specification = createSpecification(criteria);
        return educationalModuleJobRepository.findAll(specification, page)
            .map(educationalModuleJobMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(EducationalModuleJobCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<EducationalModuleJob> specification = createSpecification(criteria);
        return educationalModuleJobRepository.count(specification);
    }

    /**
     * Function to convert EducationalModuleJobCriteria to a {@link Specification}
     */
    public Specification<EducationalModuleJob> createSpecification(EducationalModuleJobCriteria criteria) {
        Specification<EducationalModuleJob> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), EducationalModuleJob_.id));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), EducationalModuleJob_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), EducationalModuleJob_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), EducationalModuleJob_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), EducationalModuleJob_.modifyDate));
            }
            if (criteria.getEducationalModuleId() != null) {
                specification = specification.and(buildSpecification(criteria.getEducationalModuleId(),
                    root -> root.join(EducationalModuleJob_.educationalModule, JoinType.LEFT).get(EducationalModule_.id)));
            }
            if (criteria.getJobId() != null) {
                specification = specification.and(buildSpecification(criteria.getJobId(),
                    root -> root.join(EducationalModuleJob_.job, JoinType.LEFT).get(Job_.id)));
            }
        }
        return specification;
    }
}
