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

import com.marineindustryproj.domain.Criterion;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.CriterionRepository;
import com.marineindustryproj.service.dto.CriterionCriteria;
import com.marineindustryproj.service.dto.CriterionDTO;
import com.marineindustryproj.service.mapper.CriterionMapper;

/**
 * Service for executing complex queries for Criterion entities in the database.
 * The main input is a {@link CriterionCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CriterionDTO} or a {@link Page} of {@link CriterionDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CriterionQueryService extends QueryService<Criterion> {

    private final Logger log = LoggerFactory.getLogger(CriterionQueryService.class);

    private final CriterionRepository criterionRepository;

    private final CriterionMapper criterionMapper;

    public CriterionQueryService(CriterionRepository criterionRepository, CriterionMapper criterionMapper) {
        this.criterionRepository = criterionRepository;
        this.criterionMapper = criterionMapper;
    }

    /**
     * Return a {@link List} of {@link CriterionDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CriterionDTO> findByCriteria(CriterionCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Criterion> specification = createSpecification(criteria);
        return criterionMapper.toDto(criterionRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CriterionDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CriterionDTO> findByCriteria(CriterionCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Criterion> specification = createSpecification(criteria);
        return criterionRepository.findAll(specification, page)
            .map(criterionMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CriterionCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Criterion> specification = createSpecification(criteria);
        return criterionRepository.count(specification);
    }

    /**
     * Function to convert CriterionCriteria to a {@link Specification}
     */
    private Specification<Criterion> createSpecification(CriterionCriteria criteria) {
        Specification<Criterion> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Criterion_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), Criterion_.title));
            }
            if (criteria.getDisplayOrder() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDisplayOrder(), Criterion_.displayOrder));
            }
            if (criteria.getCoefficient() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCoefficient(), Criterion_.coefficient));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), Criterion_.description));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), Criterion_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), Criterion_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), Criterion_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), Criterion_.modifyDate));
            }
            if (criteria.getPollItemId() != null) {
                specification = specification.and(buildSpecification(criteria.getPollItemId(),
                    root -> root.join(Criterion_.pollItems, JoinType.LEFT).get(PollItem_.id)));
            }
        }
        return specification;
    }
}
