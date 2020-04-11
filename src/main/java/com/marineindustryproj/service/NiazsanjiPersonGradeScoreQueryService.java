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

import com.marineindustryproj.domain.NiazsanjiPersonGradeScore;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.NiazsanjiPersonGradeScoreRepository;
import com.marineindustryproj.service.dto.NiazsanjiPersonGradeScoreCriteria;
import com.marineindustryproj.service.dto.NiazsanjiPersonGradeScoreDTO;
import com.marineindustryproj.service.mapper.NiazsanjiPersonGradeScoreMapper;

/**
 * Service for executing complex queries for NiazsanjiPersonGradeScore entities in the database.
 * The main input is a {@link NiazsanjiPersonGradeScoreCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link NiazsanjiPersonGradeScoreDTO} or a {@link Page} of {@link NiazsanjiPersonGradeScoreDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class NiazsanjiPersonGradeScoreQueryService extends QueryService<NiazsanjiPersonGradeScore> {

    private final Logger log = LoggerFactory.getLogger(NiazsanjiPersonGradeScoreQueryService.class);

    private final NiazsanjiPersonGradeScoreRepository niazsanjiPersonGradeScoreRepository;

    private final NiazsanjiPersonGradeScoreMapper niazsanjiPersonGradeScoreMapper;

    public NiazsanjiPersonGradeScoreQueryService(NiazsanjiPersonGradeScoreRepository niazsanjiPersonGradeScoreRepository, NiazsanjiPersonGradeScoreMapper niazsanjiPersonGradeScoreMapper) {
        this.niazsanjiPersonGradeScoreRepository = niazsanjiPersonGradeScoreRepository;
        this.niazsanjiPersonGradeScoreMapper = niazsanjiPersonGradeScoreMapper;
    }

    /**
     * Return a {@link List} of {@link NiazsanjiPersonGradeScoreDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<NiazsanjiPersonGradeScoreDTO> findByCriteria(NiazsanjiPersonGradeScoreCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<NiazsanjiPersonGradeScore> specification = createSpecification(criteria);
        return niazsanjiPersonGradeScoreMapper.toDto(niazsanjiPersonGradeScoreRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link NiazsanjiPersonGradeScoreDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<NiazsanjiPersonGradeScoreDTO> findByCriteria(NiazsanjiPersonGradeScoreCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<NiazsanjiPersonGradeScore> specification = createSpecification(criteria);
        return niazsanjiPersonGradeScoreRepository.findAll(specification, page)
            .map(niazsanjiPersonGradeScoreMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(NiazsanjiPersonGradeScoreCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<NiazsanjiPersonGradeScore> specification = createSpecification(criteria);
        return niazsanjiPersonGradeScoreRepository.count(specification);
    }

    /**
     * Function to convert NiazsanjiPersonGradeScoreCriteria to a {@link Specification}
     */
    private Specification<NiazsanjiPersonGradeScore> createSpecification(NiazsanjiPersonGradeScoreCriteria criteria) {
        Specification<NiazsanjiPersonGradeScore> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), NiazsanjiPersonGradeScore_.id));
            }
            if (criteria.getScore() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getScore(), NiazsanjiPersonGradeScore_.score));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), NiazsanjiPersonGradeScore_.description));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), NiazsanjiPersonGradeScore_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), NiazsanjiPersonGradeScore_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), NiazsanjiPersonGradeScore_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), NiazsanjiPersonGradeScore_.modifyDate));
            }
            if (criteria.getNiazsanjiPersonCriteriaId() != null) {
                specification = specification.and(buildSpecification(criteria.getNiazsanjiPersonCriteriaId(),
                    root -> root.join(NiazsanjiPersonGradeScore_.niazsanjiPersonCriteria, JoinType.LEFT).get(NiazsanjiPersonCriteria_.id)));
            }
            if (criteria.getNiazsanjiPersonGradeId() != null) {
                specification = specification.and(buildSpecification(criteria.getNiazsanjiPersonGradeId(),
                    root -> root.join(NiazsanjiPersonGradeScore_.niazsanjiPersonGrade, JoinType.LEFT).get(NiazsanjiPersonGrade_.id)));
            }
        }
        return specification;
    }
}
