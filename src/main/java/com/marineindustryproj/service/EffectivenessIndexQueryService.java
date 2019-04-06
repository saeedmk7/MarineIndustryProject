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

import com.marineindustryproj.domain.EffectivenessIndex;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.EffectivenessIndexRepository;
import com.marineindustryproj.service.dto.EffectivenessIndexCriteria;
import com.marineindustryproj.service.dto.EffectivenessIndexDTO;
import com.marineindustryproj.service.mapper.EffectivenessIndexMapper;

/**
 * Service for executing complex queries for EffectivenessIndex entities in the database.
 * The main input is a {@link EffectivenessIndexCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link EffectivenessIndexDTO} or a {@link Page} of {@link EffectivenessIndexDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class EffectivenessIndexQueryService extends QueryService<EffectivenessIndex> {

    private final Logger log = LoggerFactory.getLogger(EffectivenessIndexQueryService.class);

    private final EffectivenessIndexRepository effectivenessIndexRepository;

    private final EffectivenessIndexMapper effectivenessIndexMapper;

    public EffectivenessIndexQueryService(EffectivenessIndexRepository effectivenessIndexRepository, EffectivenessIndexMapper effectivenessIndexMapper) {
        this.effectivenessIndexRepository = effectivenessIndexRepository;
        this.effectivenessIndexMapper = effectivenessIndexMapper;
    }

    /**
     * Return a {@link List} of {@link EffectivenessIndexDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<EffectivenessIndexDTO> findByCriteria(EffectivenessIndexCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<EffectivenessIndex> specification = createSpecification(criteria);
        return effectivenessIndexMapper.toDto(effectivenessIndexRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link EffectivenessIndexDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<EffectivenessIndexDTO> findByCriteria(EffectivenessIndexCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<EffectivenessIndex> specification = createSpecification(criteria);
        return effectivenessIndexRepository.findAll(specification, page)
            .map(effectivenessIndexMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(EffectivenessIndexCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<EffectivenessIndex> specification = createSpecification(criteria);
        return effectivenessIndexRepository.count(specification);
    }

    /**
     * Function to convert EffectivenessIndexCriteria to a {@link Specification}
     */
    private Specification<EffectivenessIndex> createSpecification(EffectivenessIndexCriteria criteria) {
        Specification<EffectivenessIndex> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), EffectivenessIndex_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), EffectivenessIndex_.title));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), EffectivenessIndex_.code));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), EffectivenessIndex_.description));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), EffectivenessIndex_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), EffectivenessIndex_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), EffectivenessIndex_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), EffectivenessIndex_.modifyDate));
            }
            if (criteria.getDesignAndPlanningId() != null) {
                specification = specification.and(buildSpecification(criteria.getDesignAndPlanningId(),
                    root -> root.join(EffectivenessIndex_.designAndPlannings, JoinType.LEFT).get(DesignAndPlanning_.id)));
            }
        }
        return specification;
    }
}
