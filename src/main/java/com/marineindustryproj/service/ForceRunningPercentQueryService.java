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

import com.marineindustryproj.domain.ForceRunningPercent;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.ForceRunningPercentRepository;
import com.marineindustryproj.service.dto.ForceRunningPercentCriteria;
import com.marineindustryproj.service.dto.ForceRunningPercentDTO;
import com.marineindustryproj.service.mapper.ForceRunningPercentMapper;

/**
 * Service for executing complex queries for ForceRunningPercent entities in the database.
 * The main input is a {@link ForceRunningPercentCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ForceRunningPercentDTO} or a {@link Page} of {@link ForceRunningPercentDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ForceRunningPercentQueryService extends QueryService<ForceRunningPercent> {

    private final Logger log = LoggerFactory.getLogger(ForceRunningPercentQueryService.class);

    private final ForceRunningPercentRepository forceRunningPercentRepository;

    private final ForceRunningPercentMapper forceRunningPercentMapper;

    public ForceRunningPercentQueryService(ForceRunningPercentRepository forceRunningPercentRepository, ForceRunningPercentMapper forceRunningPercentMapper) {
        this.forceRunningPercentRepository = forceRunningPercentRepository;
        this.forceRunningPercentMapper = forceRunningPercentMapper;
    }

    /**
     * Return a {@link List} of {@link ForceRunningPercentDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ForceRunningPercentDTO> findByCriteria(ForceRunningPercentCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ForceRunningPercent> specification = createSpecification(criteria);
        return forceRunningPercentMapper.toDto(forceRunningPercentRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ForceRunningPercentDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ForceRunningPercentDTO> findByCriteria(ForceRunningPercentCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ForceRunningPercent> specification = createSpecification(criteria);
        return forceRunningPercentRepository.findAll(specification, page)
            .map(forceRunningPercentMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ForceRunningPercentCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ForceRunningPercent> specification = createSpecification(criteria);
        return forceRunningPercentRepository.count(specification);
    }

    /**
     * Function to convert ForceRunningPercentCriteria to a {@link Specification}
     */
    private Specification<ForceRunningPercent> createSpecification(ForceRunningPercentCriteria criteria) {
        Specification<ForceRunningPercent> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ForceRunningPercent_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), ForceRunningPercent_.title));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), ForceRunningPercent_.description));
            }
            if (criteria.getPercentAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPercentAmount(), ForceRunningPercent_.percentAmount));
            }
            if (criteria.getRunMonth() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRunMonth(), ForceRunningPercent_.runMonth));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), ForceRunningPercent_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), ForceRunningPercent_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), ForceRunningPercent_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), ForceRunningPercent_.modifyDate));
            }
            if (criteria.getGuid() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGuid(), ForceRunningPercent_.guid));
            }
            if (criteria.getOrganizationChartId() != null) {
                specification = specification.and(buildSpecification(criteria.getOrganizationChartId(),
                    root -> root.join(ForceRunningPercent_.organizationCharts, JoinType.LEFT).get(OrganizationChart_.id)));
            }
        }
        return specification;
    }
}
