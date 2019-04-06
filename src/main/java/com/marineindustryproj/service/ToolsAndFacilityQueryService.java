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

import com.marineindustryproj.domain.ToolsAndFacility;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.ToolsAndFacilityRepository;
import com.marineindustryproj.service.dto.ToolsAndFacilityCriteria;
import com.marineindustryproj.service.dto.ToolsAndFacilityDTO;
import com.marineindustryproj.service.mapper.ToolsAndFacilityMapper;

/**
 * Service for executing complex queries for ToolsAndFacility entities in the database.
 * The main input is a {@link ToolsAndFacilityCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ToolsAndFacilityDTO} or a {@link Page} of {@link ToolsAndFacilityDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ToolsAndFacilityQueryService extends QueryService<ToolsAndFacility> {

    private final Logger log = LoggerFactory.getLogger(ToolsAndFacilityQueryService.class);

    private final ToolsAndFacilityRepository toolsAndFacilityRepository;

    private final ToolsAndFacilityMapper toolsAndFacilityMapper;

    public ToolsAndFacilityQueryService(ToolsAndFacilityRepository toolsAndFacilityRepository, ToolsAndFacilityMapper toolsAndFacilityMapper) {
        this.toolsAndFacilityRepository = toolsAndFacilityRepository;
        this.toolsAndFacilityMapper = toolsAndFacilityMapper;
    }

    /**
     * Return a {@link List} of {@link ToolsAndFacilityDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ToolsAndFacilityDTO> findByCriteria(ToolsAndFacilityCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ToolsAndFacility> specification = createSpecification(criteria);
        return toolsAndFacilityMapper.toDto(toolsAndFacilityRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ToolsAndFacilityDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ToolsAndFacilityDTO> findByCriteria(ToolsAndFacilityCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ToolsAndFacility> specification = createSpecification(criteria);
        return toolsAndFacilityRepository.findAll(specification, page)
            .map(toolsAndFacilityMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ToolsAndFacilityCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ToolsAndFacility> specification = createSpecification(criteria);
        return toolsAndFacilityRepository.count(specification);
    }

    /**
     * Function to convert ToolsAndFacilityCriteria to a {@link Specification}
     */
    private Specification<ToolsAndFacility> createSpecification(ToolsAndFacilityCriteria criteria) {
        Specification<ToolsAndFacility> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ToolsAndFacility_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), ToolsAndFacility_.title));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), ToolsAndFacility_.code));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), ToolsAndFacility_.description));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), ToolsAndFacility_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), ToolsAndFacility_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), ToolsAndFacility_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), ToolsAndFacility_.modifyDate));
            }
            if (criteria.getDesignAndPlanningId() != null) {
                specification = specification.and(buildSpecification(criteria.getDesignAndPlanningId(),
                    root -> root.join(ToolsAndFacility_.designAndPlannings, JoinType.LEFT).get(DesignAndPlanning_.id)));
            }
        }
        return specification;
    }
}
