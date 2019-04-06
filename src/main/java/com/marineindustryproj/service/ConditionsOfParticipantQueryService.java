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

import com.marineindustryproj.domain.ConditionsOfParticipant;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.ConditionsOfParticipantRepository;
import com.marineindustryproj.service.dto.ConditionsOfParticipantCriteria;
import com.marineindustryproj.service.dto.ConditionsOfParticipantDTO;
import com.marineindustryproj.service.mapper.ConditionsOfParticipantMapper;

/**
 * Service for executing complex queries for ConditionsOfParticipant entities in the database.
 * The main input is a {@link ConditionsOfParticipantCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ConditionsOfParticipantDTO} or a {@link Page} of {@link ConditionsOfParticipantDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ConditionsOfParticipantQueryService extends QueryService<ConditionsOfParticipant> {

    private final Logger log = LoggerFactory.getLogger(ConditionsOfParticipantQueryService.class);

    private final ConditionsOfParticipantRepository conditionsOfParticipantRepository;

    private final ConditionsOfParticipantMapper conditionsOfParticipantMapper;

    public ConditionsOfParticipantQueryService(ConditionsOfParticipantRepository conditionsOfParticipantRepository, ConditionsOfParticipantMapper conditionsOfParticipantMapper) {
        this.conditionsOfParticipantRepository = conditionsOfParticipantRepository;
        this.conditionsOfParticipantMapper = conditionsOfParticipantMapper;
    }

    /**
     * Return a {@link List} of {@link ConditionsOfParticipantDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ConditionsOfParticipantDTO> findByCriteria(ConditionsOfParticipantCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ConditionsOfParticipant> specification = createSpecification(criteria);
        return conditionsOfParticipantMapper.toDto(conditionsOfParticipantRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ConditionsOfParticipantDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ConditionsOfParticipantDTO> findByCriteria(ConditionsOfParticipantCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ConditionsOfParticipant> specification = createSpecification(criteria);
        return conditionsOfParticipantRepository.findAll(specification, page)
            .map(conditionsOfParticipantMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ConditionsOfParticipantCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ConditionsOfParticipant> specification = createSpecification(criteria);
        return conditionsOfParticipantRepository.count(specification);
    }

    /**
     * Function to convert ConditionsOfParticipantCriteria to a {@link Specification}
     */
    private Specification<ConditionsOfParticipant> createSpecification(ConditionsOfParticipantCriteria criteria) {
        Specification<ConditionsOfParticipant> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ConditionsOfParticipant_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), ConditionsOfParticipant_.title));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), ConditionsOfParticipant_.code));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), ConditionsOfParticipant_.description));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), ConditionsOfParticipant_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), ConditionsOfParticipant_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), ConditionsOfParticipant_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), ConditionsOfParticipant_.modifyDate));
            }
            if (criteria.getDesignAndPlanningId() != null) {
                specification = specification.and(buildSpecification(criteria.getDesignAndPlanningId(),
                    root -> root.join(ConditionsOfParticipant_.designAndPlannings, JoinType.LEFT).get(DesignAndPlanning_.id)));
            }
        }
        return specification;
    }
}
