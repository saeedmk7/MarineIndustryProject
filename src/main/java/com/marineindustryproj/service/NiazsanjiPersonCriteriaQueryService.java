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

import com.marineindustryproj.domain.NiazsanjiPersonCriteria;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.NiazsanjiPersonCriteriaRepository;
import com.marineindustryproj.service.dto.NiazsanjiPersonCriteriaCriteria;
import com.marineindustryproj.service.dto.NiazsanjiPersonCriteriaDTO;
import com.marineindustryproj.service.mapper.NiazsanjiPersonCriteriaMapper;

/**
 * Service for executing complex queries for NiazsanjiPersonCriteria entities in the database.
 * The main input is a {@link NiazsanjiPersonCriteriaCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link NiazsanjiPersonCriteriaDTO} or a {@link Page} of {@link NiazsanjiPersonCriteriaDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class NiazsanjiPersonCriteriaQueryService extends QueryService<NiazsanjiPersonCriteria> {

    private final Logger log = LoggerFactory.getLogger(NiazsanjiPersonCriteriaQueryService.class);

    private final NiazsanjiPersonCriteriaRepository niazsanjiPersonCriteriaRepository;

    private final NiazsanjiPersonCriteriaMapper niazsanjiPersonCriteriaMapper;

    public NiazsanjiPersonCriteriaQueryService(NiazsanjiPersonCriteriaRepository niazsanjiPersonCriteriaRepository, NiazsanjiPersonCriteriaMapper niazsanjiPersonCriteriaMapper) {
        this.niazsanjiPersonCriteriaRepository = niazsanjiPersonCriteriaRepository;
        this.niazsanjiPersonCriteriaMapper = niazsanjiPersonCriteriaMapper;
    }

    /**
     * Return a {@link List} of {@link NiazsanjiPersonCriteriaDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<NiazsanjiPersonCriteriaDTO> findByCriteria(NiazsanjiPersonCriteriaCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<NiazsanjiPersonCriteria> specification = createSpecification(criteria);
        return niazsanjiPersonCriteriaMapper.toDto(niazsanjiPersonCriteriaRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link NiazsanjiPersonCriteriaDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<NiazsanjiPersonCriteriaDTO> findByCriteria(NiazsanjiPersonCriteriaCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<NiazsanjiPersonCriteria> specification = createSpecification(criteria);
        return niazsanjiPersonCriteriaRepository.findAll(specification, page)
            .map(niazsanjiPersonCriteriaMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(NiazsanjiPersonCriteriaCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<NiazsanjiPersonCriteria> specification = createSpecification(criteria);
        return niazsanjiPersonCriteriaRepository.count(specification);
    }

    /**
     * Function to convert NiazsanjiPersonCriteriaCriteria to a {@link Specification}
     */
    private Specification<NiazsanjiPersonCriteria> createSpecification(NiazsanjiPersonCriteriaCriteria criteria) {
        Specification<NiazsanjiPersonCriteria> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), NiazsanjiPersonCriteria_.id));
            }
            if (criteria.getCriterionType() != null) {
                specification = specification.and(buildSpecification(criteria.getCriterionType(), NiazsanjiPersonCriteria_.criterionType));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), NiazsanjiPersonCriteria_.title));
            }
            if (criteria.getDisplayOrder() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDisplayOrder(), NiazsanjiPersonCriteria_.displayOrder));
            }
            if (criteria.getWeight() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getWeight(), NiazsanjiPersonCriteria_.weight));
            }
            if (criteria.getSecondWeight() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSecondWeight(), NiazsanjiPersonCriteria_.secondWeight));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), NiazsanjiPersonCriteria_.description));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), NiazsanjiPersonCriteria_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), NiazsanjiPersonCriteria_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), NiazsanjiPersonCriteria_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), NiazsanjiPersonCriteria_.modifyDate));
            }
            if (criteria.getBackgroundColor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBackgroundColor(), NiazsanjiPersonCriteria_.backgroundColor));
            }
            if (criteria.getColorText() != null) {
                specification = specification.and(buildStringSpecification(criteria.getColorText(), NiazsanjiPersonCriteria_.colorText));
            }
            if (criteria.getNiazsanjiPersonGradeScoreId() != null) {
                specification = specification.and(buildSpecification(criteria.getNiazsanjiPersonGradeScoreId(),
                    root -> root.join(NiazsanjiPersonCriteria_.niazsanjiPersonGradeScores, JoinType.LEFT).get(NiazsanjiPersonGradeScore_.id)));
            }
        }
        return specification;
    }
}
