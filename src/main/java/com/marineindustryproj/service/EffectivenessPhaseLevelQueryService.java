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

import com.marineindustryproj.domain.EffectivenessPhaseLevel;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.EffectivenessPhaseLevelRepository;
import com.marineindustryproj.service.dto.EffectivenessPhaseLevelCriteria;
import com.marineindustryproj.service.dto.EffectivenessPhaseLevelDTO;
import com.marineindustryproj.service.mapper.EffectivenessPhaseLevelMapper;

/**
 * Service for executing complex queries for EffectivenessPhaseLevel entities in the database.
 * The main input is a {@link EffectivenessPhaseLevelCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link EffectivenessPhaseLevelDTO} or a {@link Page} of {@link EffectivenessPhaseLevelDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class EffectivenessPhaseLevelQueryService extends QueryService<EffectivenessPhaseLevel> {

    private final Logger log = LoggerFactory.getLogger(EffectivenessPhaseLevelQueryService.class);

    private final EffectivenessPhaseLevelRepository effectivenessPhaseLevelRepository;

    private final EffectivenessPhaseLevelMapper effectivenessPhaseLevelMapper;

    public EffectivenessPhaseLevelQueryService(EffectivenessPhaseLevelRepository effectivenessPhaseLevelRepository, EffectivenessPhaseLevelMapper effectivenessPhaseLevelMapper) {
        this.effectivenessPhaseLevelRepository = effectivenessPhaseLevelRepository;
        this.effectivenessPhaseLevelMapper = effectivenessPhaseLevelMapper;
    }

    /**
     * Return a {@link List} of {@link EffectivenessPhaseLevelDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<EffectivenessPhaseLevelDTO> findByCriteria(EffectivenessPhaseLevelCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<EffectivenessPhaseLevel> specification = createSpecification(criteria);
        return effectivenessPhaseLevelMapper.toDto(effectivenessPhaseLevelRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link EffectivenessPhaseLevelDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<EffectivenessPhaseLevelDTO> findByCriteria(EffectivenessPhaseLevelCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<EffectivenessPhaseLevel> specification = createSpecification(criteria);
        return effectivenessPhaseLevelRepository.findAll(specification, page)
            .map(effectivenessPhaseLevelMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(EffectivenessPhaseLevelCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<EffectivenessPhaseLevel> specification = createSpecification(criteria);
        return effectivenessPhaseLevelRepository.count(specification);
    }

    /**
     * Function to convert EffectivenessPhaseLevelCriteria to a {@link Specification}
     */
    private Specification<EffectivenessPhaseLevel> createSpecification(EffectivenessPhaseLevelCriteria criteria) {
        Specification<EffectivenessPhaseLevel> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), EffectivenessPhaseLevel_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), EffectivenessPhaseLevel_.title));
            }
            if (criteria.getEffectivenessLevel() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEffectivenessLevel(), EffectivenessPhaseLevel_.effectivenessLevel));
            }
            if (criteria.getEffectivenessLevelUse() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEffectivenessLevelUse(), EffectivenessPhaseLevel_.effectivenessLevelUse));
            }
            if (criteria.getWeight() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getWeight(), EffectivenessPhaseLevel_.weight));
            }
            if (criteria.getGoal() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGoal(), EffectivenessPhaseLevel_.goal));
            }
            if (criteria.getUnitOfMeasurement() != null) {
                specification = specification.and(buildSpecification(criteria.getUnitOfMeasurement(), EffectivenessPhaseLevel_.unitOfMeasurement));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), EffectivenessPhaseLevel_.description));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), EffectivenessPhaseLevel_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), EffectivenessPhaseLevel_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), EffectivenessPhaseLevel_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), EffectivenessPhaseLevel_.modifyDate));
            }
            if (criteria.getEffectivenessPhaseId() != null) {
                specification = specification.and(buildSpecification(criteria.getEffectivenessPhaseId(),
                    root -> root.join(EffectivenessPhaseLevel_.effectivenessPhases, JoinType.LEFT).get(EffectivenessPhase_.id)));
            }
        }
        return specification;
    }
}
