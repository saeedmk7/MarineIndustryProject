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

import com.marineindustryproj.domain.LevelThreeCriteriaGroup;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.LevelThreeCriteriaGroupRepository;
import com.marineindustryproj.service.dto.LevelThreeCriteriaGroupCriteria;
import com.marineindustryproj.service.dto.LevelThreeCriteriaGroupDTO;
import com.marineindustryproj.service.mapper.LevelThreeCriteriaGroupMapper;

/**
 * Service for executing complex queries for LevelThreeCriteriaGroup entities in the database.
 * The main input is a {@link LevelThreeCriteriaGroupCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link LevelThreeCriteriaGroupDTO} or a {@link Page} of {@link LevelThreeCriteriaGroupDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class LevelThreeCriteriaGroupQueryService extends QueryService<LevelThreeCriteriaGroup> {

    private final Logger log = LoggerFactory.getLogger(LevelThreeCriteriaGroupQueryService.class);

    private final LevelThreeCriteriaGroupRepository levelThreeCriteriaGroupRepository;

    private final LevelThreeCriteriaGroupMapper levelThreeCriteriaGroupMapper;

    public LevelThreeCriteriaGroupQueryService(LevelThreeCriteriaGroupRepository levelThreeCriteriaGroupRepository, LevelThreeCriteriaGroupMapper levelThreeCriteriaGroupMapper) {
        this.levelThreeCriteriaGroupRepository = levelThreeCriteriaGroupRepository;
        this.levelThreeCriteriaGroupMapper = levelThreeCriteriaGroupMapper;
    }

    /**
     * Return a {@link List} of {@link LevelThreeCriteriaGroupDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<LevelThreeCriteriaGroupDTO> findByCriteria(LevelThreeCriteriaGroupCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<LevelThreeCriteriaGroup> specification = createSpecification(criteria);
        return levelThreeCriteriaGroupMapper.toDto(levelThreeCriteriaGroupRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link LevelThreeCriteriaGroupDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<LevelThreeCriteriaGroupDTO> findByCriteria(LevelThreeCriteriaGroupCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<LevelThreeCriteriaGroup> specification = createSpecification(criteria);
        return levelThreeCriteriaGroupRepository.findAll(specification, page)
            .map(levelThreeCriteriaGroupMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(LevelThreeCriteriaGroupCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<LevelThreeCriteriaGroup> specification = createSpecification(criteria);
        return levelThreeCriteriaGroupRepository.count(specification);
    }

    /**
     * Function to convert LevelThreeCriteriaGroupCriteria to a {@link Specification}
     */
    private Specification<LevelThreeCriteriaGroup> createSpecification(LevelThreeCriteriaGroupCriteria criteria) {
        Specification<LevelThreeCriteriaGroup> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), LevelThreeCriteriaGroup_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), LevelThreeCriteriaGroup_.title));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), LevelThreeCriteriaGroup_.description));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), LevelThreeCriteriaGroup_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), LevelThreeCriteriaGroup_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), LevelThreeCriteriaGroup_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), LevelThreeCriteriaGroup_.modifyDate));
            }
            if (criteria.getLevelThreeCriteriaId() != null) {
                specification = specification.and(buildSpecification(criteria.getLevelThreeCriteriaId(),
                    root -> root.join(LevelThreeCriteriaGroup_.levelThreeCriteria, JoinType.LEFT).get(LevelThreeCriteria_.id)));
            }
        }
        return specification;
    }
}
