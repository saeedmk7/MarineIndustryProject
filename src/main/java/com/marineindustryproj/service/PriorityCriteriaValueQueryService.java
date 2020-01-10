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

import com.marineindustryproj.domain.PriorityCriteriaValue;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.PriorityCriteriaValueRepository;
import com.marineindustryproj.service.dto.PriorityCriteriaValueCriteria;
import com.marineindustryproj.service.dto.PriorityCriteriaValueDTO;
import com.marineindustryproj.service.mapper.PriorityCriteriaValueMapper;

/**
 * Service for executing complex queries for PriorityCriteriaValue entities in the database.
 * The main input is a {@link PriorityCriteriaValueCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PriorityCriteriaValueDTO} or a {@link Page} of {@link PriorityCriteriaValueDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PriorityCriteriaValueQueryService extends QueryService<PriorityCriteriaValue> {

    private final Logger log = LoggerFactory.getLogger(PriorityCriteriaValueQueryService.class);

    private final PriorityCriteriaValueRepository priorityCriteriaValueRepository;

    private final PriorityCriteriaValueMapper priorityCriteriaValueMapper;

    public PriorityCriteriaValueQueryService(PriorityCriteriaValueRepository priorityCriteriaValueRepository, PriorityCriteriaValueMapper priorityCriteriaValueMapper) {
        this.priorityCriteriaValueRepository = priorityCriteriaValueRepository;
        this.priorityCriteriaValueMapper = priorityCriteriaValueMapper;
    }

    /**
     * Return a {@link List} of {@link PriorityCriteriaValueDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<PriorityCriteriaValueDTO> findByCriteria(PriorityCriteriaValueCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<PriorityCriteriaValue> specification = createSpecification(criteria);
        return priorityCriteriaValueMapper.toDto(priorityCriteriaValueRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link PriorityCriteriaValueDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<PriorityCriteriaValueDTO> findByCriteria(PriorityCriteriaValueCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<PriorityCriteriaValue> specification = createSpecification(criteria);
        return priorityCriteriaValueRepository.findAll(specification, page)
            .map(priorityCriteriaValueMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(PriorityCriteriaValueCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<PriorityCriteriaValue> specification = createSpecification(criteria);
        return priorityCriteriaValueRepository.count(specification);
    }

    /**
     * Function to convert PriorityCriteriaValueCriteria to a {@link Specification}
     */
    private Specification<PriorityCriteriaValue> createSpecification(PriorityCriteriaValueCriteria criteria) {
        Specification<PriorityCriteriaValue> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), PriorityCriteriaValue_.id));
            }
            if (criteria.getScore() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getScore(), PriorityCriteriaValue_.score));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), PriorityCriteriaValue_.description));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), PriorityCriteriaValue_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), PriorityCriteriaValue_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), PriorityCriteriaValue_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), PriorityCriteriaValue_.modifyDate));
            }
            if (criteria.getPriorityCriteriaId() != null) {
                specification = specification.and(buildSpecification(criteria.getPriorityCriteriaId(),
                    root -> root.join(PriorityCriteriaValue_.priorityCriteria, JoinType.LEFT).get(PriorityCriteria_.id)));
            }
            if (criteria.getPreJobNiazsanjiCompetencyId() != null) {
                specification = specification.and(buildSpecification(criteria.getPreJobNiazsanjiCompetencyId(),
                    root -> root.join(PriorityCriteriaValue_.preJobNiazsanjiCompetency, JoinType.LEFT).get(PreJobNiazsanjiCompetency_.id)));
            }
        }
        return specification;
    }
}
