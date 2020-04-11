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

import com.marineindustryproj.domain.LevelFourCriteria;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.LevelFourCriteriaRepository;
import com.marineindustryproj.service.dto.LevelFourCriteriaCriteria;
import com.marineindustryproj.service.dto.LevelFourCriteriaDTO;
import com.marineindustryproj.service.mapper.LevelFourCriteriaMapper;

/**
 * Service for executing complex queries for LevelFourCriteria entities in the database.
 * The main input is a {@link LevelFourCriteriaCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link LevelFourCriteriaDTO} or a {@link Page} of {@link LevelFourCriteriaDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class LevelFourCriteriaQueryService extends QueryService<LevelFourCriteria> {

    private final Logger log = LoggerFactory.getLogger(LevelFourCriteriaQueryService.class);

    private final LevelFourCriteriaRepository levelFourCriteriaRepository;

    private final LevelFourCriteriaMapper levelFourCriteriaMapper;

    public LevelFourCriteriaQueryService(LevelFourCriteriaRepository levelFourCriteriaRepository, LevelFourCriteriaMapper levelFourCriteriaMapper) {
        this.levelFourCriteriaRepository = levelFourCriteriaRepository;
        this.levelFourCriteriaMapper = levelFourCriteriaMapper;
    }

    /**
     * Return a {@link List} of {@link LevelFourCriteriaDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<LevelFourCriteriaDTO> findByCriteria(LevelFourCriteriaCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<LevelFourCriteria> specification = createSpecification(criteria);
        return levelFourCriteriaMapper.toDto(levelFourCriteriaRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link LevelFourCriteriaDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<LevelFourCriteriaDTO> findByCriteria(LevelFourCriteriaCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<LevelFourCriteria> specification = createSpecification(criteria);
        return levelFourCriteriaRepository.findAll(specification, page)
            .map(levelFourCriteriaMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(LevelFourCriteriaCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<LevelFourCriteria> specification = createSpecification(criteria);
        return levelFourCriteriaRepository.count(specification);
    }

    /**
     * Function to convert LevelFourCriteriaCriteria to a {@link Specification}
     */
    private Specification<LevelFourCriteria> createSpecification(LevelFourCriteriaCriteria criteria) {
        Specification<LevelFourCriteria> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), LevelFourCriteria_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), LevelFourCriteria_.title));
            }
            if (criteria.getDisplayOrder() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDisplayOrder(), LevelFourCriteria_.displayOrder));
            }
            if (criteria.getWeight() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getWeight(), LevelFourCriteria_.weight));
            }
            if (criteria.getSecondWeight() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSecondWeight(), LevelFourCriteria_.secondWeight));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), LevelFourCriteria_.description));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), LevelFourCriteria_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), LevelFourCriteria_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), LevelFourCriteria_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), LevelFourCriteria_.modifyDate));
            }
            if (criteria.getBackgroundColor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBackgroundColor(), LevelFourCriteria_.backgroundColor));
            }
            if (criteria.getColorText() != null) {
                specification = specification.and(buildStringSpecification(criteria.getColorText(), LevelFourCriteria_.colorText));
            }
            if (criteria.getLevelFourScoreId() != null) {
                specification = specification.and(buildSpecification(criteria.getLevelFourScoreId(),
                    root -> root.join(LevelFourCriteria_.levelFourScores, JoinType.LEFT).get(LevelFourScore_.id)));
            }
        }
        return specification;
    }
}
