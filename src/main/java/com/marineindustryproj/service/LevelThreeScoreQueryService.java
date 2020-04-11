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

import com.marineindustryproj.domain.LevelThreeScore;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.LevelThreeScoreRepository;
import com.marineindustryproj.service.dto.LevelThreeScoreCriteria;
import com.marineindustryproj.service.dto.LevelThreeScoreDTO;
import com.marineindustryproj.service.mapper.LevelThreeScoreMapper;

/**
 * Service for executing complex queries for LevelThreeScore entities in the database.
 * The main input is a {@link LevelThreeScoreCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link LevelThreeScoreDTO} or a {@link Page} of {@link LevelThreeScoreDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class LevelThreeScoreQueryService extends QueryService<LevelThreeScore> {

    private final Logger log = LoggerFactory.getLogger(LevelThreeScoreQueryService.class);

    private final LevelThreeScoreRepository levelThreeScoreRepository;

    private final LevelThreeScoreMapper levelThreeScoreMapper;

    public LevelThreeScoreQueryService(LevelThreeScoreRepository levelThreeScoreRepository, LevelThreeScoreMapper levelThreeScoreMapper) {
        this.levelThreeScoreRepository = levelThreeScoreRepository;
        this.levelThreeScoreMapper = levelThreeScoreMapper;
    }

    /**
     * Return a {@link List} of {@link LevelThreeScoreDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<LevelThreeScoreDTO> findByCriteria(LevelThreeScoreCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<LevelThreeScore> specification = createSpecification(criteria);
        return levelThreeScoreMapper.toDto(levelThreeScoreRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link LevelThreeScoreDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<LevelThreeScoreDTO> findByCriteria(LevelThreeScoreCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<LevelThreeScore> specification = createSpecification(criteria);
        return levelThreeScoreRepository.findAll(specification, page)
            .map(levelThreeScoreMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(LevelThreeScoreCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<LevelThreeScore> specification = createSpecification(criteria);
        return levelThreeScoreRepository.count(specification);
    }

    /**
     * Function to convert LevelThreeScoreCriteria to a {@link Specification}
     */
    private Specification<LevelThreeScore> createSpecification(LevelThreeScoreCriteria criteria) {
        Specification<LevelThreeScore> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), LevelThreeScore_.id));
            }
            if (criteria.getScore() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getScore(), LevelThreeScore_.score));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), LevelThreeScore_.description));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), LevelThreeScore_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), LevelThreeScore_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), LevelThreeScore_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), LevelThreeScore_.modifyDate));
            }
            if (criteria.getLevelThreeCriteriaId() != null) {
                specification = specification.and(buildSpecification(criteria.getLevelThreeCriteriaId(),
                    root -> root.join(LevelThreeScore_.levelThreeCriteria, JoinType.LEFT).get(LevelThreeCriteria_.id)));
            }
            if (criteria.getLevelThreeEffectivenessId() != null) {
                specification = specification.and(buildSpecification(criteria.getLevelThreeEffectivenessId(),
                    root -> root.join(LevelThreeScore_.levelThreeEffectiveness, JoinType.LEFT).get(LevelThreeEffectiveness_.id)));
            }
        }
        return specification;
    }
}
