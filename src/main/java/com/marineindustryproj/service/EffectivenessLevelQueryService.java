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

import com.marineindustryproj.domain.EffectivenessLevel;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.EffectivenessLevelRepository;
import com.marineindustryproj.service.dto.EffectivenessLevelCriteria;
import com.marineindustryproj.service.dto.EffectivenessLevelDTO;
import com.marineindustryproj.service.mapper.EffectivenessLevelMapper;

/**
 * Service for executing complex queries for EffectivenessLevel entities in the database.
 * The main input is a {@link EffectivenessLevelCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link EffectivenessLevelDTO} or a {@link Page} of {@link EffectivenessLevelDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class EffectivenessLevelQueryService extends QueryService<EffectivenessLevel> {

    private final Logger log = LoggerFactory.getLogger(EffectivenessLevelQueryService.class);

    private final EffectivenessLevelRepository effectivenessLevelRepository;

    private final EffectivenessLevelMapper effectivenessLevelMapper;

    public EffectivenessLevelQueryService(EffectivenessLevelRepository effectivenessLevelRepository, EffectivenessLevelMapper effectivenessLevelMapper) {
        this.effectivenessLevelRepository = effectivenessLevelRepository;
        this.effectivenessLevelMapper = effectivenessLevelMapper;
    }

    /**
     * Return a {@link List} of {@link EffectivenessLevelDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<EffectivenessLevelDTO> findByCriteria(EffectivenessLevelCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<EffectivenessLevel> specification = createSpecification(criteria);
        return effectivenessLevelMapper.toDto(effectivenessLevelRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link EffectivenessLevelDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<EffectivenessLevelDTO> findByCriteria(EffectivenessLevelCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<EffectivenessLevel> specification = createSpecification(criteria);
        return effectivenessLevelRepository.findAll(specification, page)
            .map(effectivenessLevelMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(EffectivenessLevelCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<EffectivenessLevel> specification = createSpecification(criteria);
        return effectivenessLevelRepository.count(specification);
    }

    /**
     * Function to convert EffectivenessLevelCriteria to a {@link Specification}
     */
    private Specification<EffectivenessLevel> createSpecification(EffectivenessLevelCriteria criteria) {
        Specification<EffectivenessLevel> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), EffectivenessLevel_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), EffectivenessLevel_.title));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), EffectivenessLevel_.code));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), EffectivenessLevel_.description));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), EffectivenessLevel_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), EffectivenessLevel_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), EffectivenessLevel_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), EffectivenessLevel_.modifyDate));
            }
            if (criteria.getDesignAndPlanningId() != null) {
                specification = specification.and(buildSpecification(criteria.getDesignAndPlanningId(),
                    root -> root.join(EffectivenessLevel_.designAndPlannings, JoinType.LEFT).get(DesignAndPlanning_.id)));
            }
        }
        return specification;
    }
}
