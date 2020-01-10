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

import com.marineindustryproj.domain.PriorityCriteria;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.PriorityCriteriaRepository;
import com.marineindustryproj.service.dto.PriorityCriteriaCriteria;
import com.marineindustryproj.service.dto.PriorityCriteriaDTO;
import com.marineindustryproj.service.mapper.PriorityCriteriaMapper;

/**
 * Service for executing complex queries for PriorityCriteria entities in the database.
 * The main input is a {@link PriorityCriteriaCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PriorityCriteriaDTO} or a {@link Page} of {@link PriorityCriteriaDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PriorityCriteriaQueryService extends QueryService<PriorityCriteria> {

    private final Logger log = LoggerFactory.getLogger(PriorityCriteriaQueryService.class);

    private final PriorityCriteriaRepository priorityCriteriaRepository;

    private final PriorityCriteriaMapper priorityCriteriaMapper;

    public PriorityCriteriaQueryService(PriorityCriteriaRepository priorityCriteriaRepository, PriorityCriteriaMapper priorityCriteriaMapper) {
        this.priorityCriteriaRepository = priorityCriteriaRepository;
        this.priorityCriteriaMapper = priorityCriteriaMapper;
    }

    /**
     * Return a {@link List} of {@link PriorityCriteriaDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<PriorityCriteriaDTO> findByCriteria(PriorityCriteriaCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<PriorityCriteria> specification = createSpecification(criteria);
        return priorityCriteriaMapper.toDto(priorityCriteriaRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link PriorityCriteriaDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<PriorityCriteriaDTO> findByCriteria(PriorityCriteriaCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<PriorityCriteria> specification = createSpecification(criteria);
        return priorityCriteriaRepository.findAll(specification, page)
            .map(priorityCriteriaMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(PriorityCriteriaCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<PriorityCriteria> specification = createSpecification(criteria);
        return priorityCriteriaRepository.count(specification);
    }

    /**
     * Function to convert PriorityCriteriaCriteria to a {@link Specification}
     */
    private Specification<PriorityCriteria> createSpecification(PriorityCriteriaCriteria criteria) {
        Specification<PriorityCriteria> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), PriorityCriteria_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), PriorityCriteria_.title));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), PriorityCriteria_.description));
            }
            if (criteria.getDisplayOrder() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDisplayOrder(), PriorityCriteria_.displayOrder));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), PriorityCriteria_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), PriorityCriteria_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), PriorityCriteria_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), PriorityCriteria_.modifyDate));
            }
            if (criteria.getPriorityCriteriaValueId() != null) {
                specification = specification.and(buildSpecification(criteria.getPriorityCriteriaValueId(),
                    root -> root.join(PriorityCriteria_.priorityCriteriaValues, JoinType.LEFT).get(PriorityCriteriaValue_.id)));
            }
        }
        return specification;
    }
}
