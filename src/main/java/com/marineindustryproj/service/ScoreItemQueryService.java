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

import com.marineindustryproj.domain.ScoreItem;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.ScoreItemRepository;
import com.marineindustryproj.service.dto.ScoreItemCriteria;
import com.marineindustryproj.service.dto.ScoreItemDTO;
import com.marineindustryproj.service.mapper.ScoreItemMapper;

/**
 * Service for executing complex queries for ScoreItem entities in the database.
 * The main input is a {@link ScoreItemCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ScoreItemDTO} or a {@link Page} of {@link ScoreItemDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ScoreItemQueryService extends QueryService<ScoreItem> {

    private final Logger log = LoggerFactory.getLogger(ScoreItemQueryService.class);

    private final ScoreItemRepository scoreItemRepository;

    private final ScoreItemMapper scoreItemMapper;

    public ScoreItemQueryService(ScoreItemRepository scoreItemRepository, ScoreItemMapper scoreItemMapper) {
        this.scoreItemRepository = scoreItemRepository;
        this.scoreItemMapper = scoreItemMapper;
    }

    /**
     * Return a {@link List} of {@link ScoreItemDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ScoreItemDTO> findByCriteria(ScoreItemCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ScoreItem> specification = createSpecification(criteria);
        return scoreItemMapper.toDto(scoreItemRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ScoreItemDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ScoreItemDTO> findByCriteria(ScoreItemCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ScoreItem> specification = createSpecification(criteria);
        return scoreItemRepository.findAll(specification, page)
            .map(scoreItemMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ScoreItemCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ScoreItem> specification = createSpecification(criteria);
        return scoreItemRepository.count(specification);
    }

    /**
     * Function to convert ScoreItemCriteria to a {@link Specification}
     */
    private Specification<ScoreItem> createSpecification(ScoreItemCriteria criteria) {
        Specification<ScoreItem> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ScoreItem_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), ScoreItem_.title));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), ScoreItem_.description));
            }
            if (criteria.getScore() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getScore(), ScoreItem_.score));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), ScoreItem_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), ScoreItem_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), ScoreItem_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), ScoreItem_.modifyDate));
            }
            if (criteria.getPollScoreId() != null) {
                specification = specification.and(buildSpecification(criteria.getPollScoreId(),
                    root -> root.join(ScoreItem_.pollScores, JoinType.LEFT).get(PollScore_.id)));
            }
        }
        return specification;
    }
}
