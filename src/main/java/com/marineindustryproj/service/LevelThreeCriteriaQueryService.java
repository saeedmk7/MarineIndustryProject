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

import com.marineindustryproj.domain.LevelThreeCriteria;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.LevelThreeCriteriaRepository;
import com.marineindustryproj.service.dto.LevelThreeCriteriaCriteria;
import com.marineindustryproj.service.dto.LevelThreeCriteriaDTO;
import com.marineindustryproj.service.mapper.LevelThreeCriteriaMapper;

/**
 * Service for executing complex queries for LevelThreeCriteria entities in the database.
 * The main input is a {@link LevelThreeCriteriaCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link LevelThreeCriteriaDTO} or a {@link Page} of {@link LevelThreeCriteriaDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class LevelThreeCriteriaQueryService extends QueryService<LevelThreeCriteria> {

    private final Logger log = LoggerFactory.getLogger(LevelThreeCriteriaQueryService.class);

    private final LevelThreeCriteriaRepository levelThreeCriteriaRepository;

    private final LevelThreeCriteriaMapper levelThreeCriteriaMapper;

    public LevelThreeCriteriaQueryService(LevelThreeCriteriaRepository levelThreeCriteriaRepository, LevelThreeCriteriaMapper levelThreeCriteriaMapper) {
        this.levelThreeCriteriaRepository = levelThreeCriteriaRepository;
        this.levelThreeCriteriaMapper = levelThreeCriteriaMapper;
    }

    /**
     * Return a {@link List} of {@link LevelThreeCriteriaDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<LevelThreeCriteriaDTO> findByCriteria(LevelThreeCriteriaCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<LevelThreeCriteria> specification = createSpecification(criteria);
        return levelThreeCriteriaMapper.toDto(levelThreeCriteriaRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link LevelThreeCriteriaDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<LevelThreeCriteriaDTO> findByCriteria(LevelThreeCriteriaCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<LevelThreeCriteria> specification = createSpecification(criteria);
        return levelThreeCriteriaRepository.findAll(specification, page)
            .map(levelThreeCriteriaMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(LevelThreeCriteriaCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<LevelThreeCriteria> specification = createSpecification(criteria);
        return levelThreeCriteriaRepository.count(specification);
    }

    /**
     * Function to convert LevelThreeCriteriaCriteria to a {@link Specification}
     */
    private Specification<LevelThreeCriteria> createSpecification(LevelThreeCriteriaCriteria criteria) {
        Specification<LevelThreeCriteria> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), LevelThreeCriteria_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), LevelThreeCriteria_.title));
            }
            if (criteria.getDisplayOrder() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDisplayOrder(), LevelThreeCriteria_.displayOrder));
            }
            if (criteria.getWeight() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getWeight(), LevelThreeCriteria_.weight));
            }
            if (criteria.getSecondWeight() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSecondWeight(), LevelThreeCriteria_.secondWeight));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), LevelThreeCriteria_.description));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), LevelThreeCriteria_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), LevelThreeCriteria_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), LevelThreeCriteria_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), LevelThreeCriteria_.modifyDate));
            }
            if (criteria.getBackgroundColor() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBackgroundColor(), LevelThreeCriteria_.backgroundColor));
            }
            if (criteria.getColorText() != null) {
                specification = specification.and(buildStringSpecification(criteria.getColorText(), LevelThreeCriteria_.colorText));
            }
            if (criteria.getLevelThreeScoreId() != null) {
                specification = specification.and(buildSpecification(criteria.getLevelThreeScoreId(),
                    root -> root.join(LevelThreeCriteria_.levelThreeScores, JoinType.LEFT).get(LevelThreeScore_.id)));
            }
            if (criteria.getMahiatCourseId() != null) {
                specification = specification.and(buildSpecification(criteria.getMahiatCourseId(),
                    root -> root.join(LevelThreeCriteria_.mahiatCourse, JoinType.LEFT).get(MahiatCourse_.id)));
            }
            if (criteria.getLevelThreeCriteriaGroupId() != null) {
                specification = specification.and(buildSpecification(criteria.getLevelThreeCriteriaGroupId(),
                    root -> root.join(LevelThreeCriteria_.levelThreeCriteriaGroup, JoinType.LEFT).get(LevelThreeCriteriaGroup_.id)));
            }
        }
        return specification;
    }
}
