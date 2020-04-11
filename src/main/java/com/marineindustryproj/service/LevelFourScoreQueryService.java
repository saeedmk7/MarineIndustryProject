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

import com.marineindustryproj.domain.LevelFourScore;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.LevelFourScoreRepository;
import com.marineindustryproj.service.dto.LevelFourScoreCriteria;
import com.marineindustryproj.service.dto.LevelFourScoreDTO;
import com.marineindustryproj.service.mapper.LevelFourScoreMapper;

/**
 * Service for executing complex queries for LevelFourScore entities in the database.
 * The main input is a {@link LevelFourScoreCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link LevelFourScoreDTO} or a {@link Page} of {@link LevelFourScoreDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class LevelFourScoreQueryService extends QueryService<LevelFourScore> {

    private final Logger log = LoggerFactory.getLogger(LevelFourScoreQueryService.class);

    private final LevelFourScoreRepository levelFourScoreRepository;

    private final LevelFourScoreMapper levelFourScoreMapper;

    public LevelFourScoreQueryService(LevelFourScoreRepository levelFourScoreRepository, LevelFourScoreMapper levelFourScoreMapper) {
        this.levelFourScoreRepository = levelFourScoreRepository;
        this.levelFourScoreMapper = levelFourScoreMapper;
    }

    /**
     * Return a {@link List} of {@link LevelFourScoreDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<LevelFourScoreDTO> findByCriteria(LevelFourScoreCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<LevelFourScore> specification = createSpecification(criteria);
        return levelFourScoreMapper.toDto(levelFourScoreRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link LevelFourScoreDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<LevelFourScoreDTO> findByCriteria(LevelFourScoreCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<LevelFourScore> specification = createSpecification(criteria);
        return levelFourScoreRepository.findAll(specification, page)
            .map(levelFourScoreMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(LevelFourScoreCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<LevelFourScore> specification = createSpecification(criteria);
        return levelFourScoreRepository.count(specification);
    }

    /**
     * Function to convert LevelFourScoreCriteria to a {@link Specification}
     */
    private Specification<LevelFourScore> createSpecification(LevelFourScoreCriteria criteria) {
        Specification<LevelFourScore> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), LevelFourScore_.id));
            }
            if (criteria.getScore() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getScore(), LevelFourScore_.score));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), LevelFourScore_.description));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), LevelFourScore_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), LevelFourScore_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), LevelFourScore_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), LevelFourScore_.modifyDate));
            }
            if (criteria.getLevelFourCriteriaId() != null) {
                specification = specification.and(buildSpecification(criteria.getLevelFourCriteriaId(),
                    root -> root.join(LevelFourScore_.levelFourCriteria, JoinType.LEFT).get(LevelFourCriteria_.id)));
            }
            if (criteria.getLevelFourEffectivenessId() != null) {
                specification = specification.and(buildSpecification(criteria.getLevelFourEffectivenessId(),
                    root -> root.join(LevelFourScore_.levelFourEffectiveness, JoinType.LEFT).get(LevelFourEffectiveness_.id)));
            }
        }
        return specification;
    }
}
