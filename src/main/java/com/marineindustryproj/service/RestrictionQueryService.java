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

import com.marineindustryproj.domain.Restriction;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.RestrictionRepository;
import com.marineindustryproj.service.dto.RestrictionCriteria;
import com.marineindustryproj.service.dto.RestrictionDTO;
import com.marineindustryproj.service.mapper.RestrictionMapper;

/**
 * Service for executing complex queries for Restriction entities in the database.
 * The main input is a {@link RestrictionCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link RestrictionDTO} or a {@link Page} of {@link RestrictionDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class RestrictionQueryService extends QueryService<Restriction> {

    private final Logger log = LoggerFactory.getLogger(RestrictionQueryService.class);

    private final RestrictionRepository restrictionRepository;

    private final RestrictionMapper restrictionMapper;

    public RestrictionQueryService(RestrictionRepository restrictionRepository, RestrictionMapper restrictionMapper) {
        this.restrictionRepository = restrictionRepository;
        this.restrictionMapper = restrictionMapper;
    }

    /**
     * Return a {@link List} of {@link RestrictionDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<RestrictionDTO> findByCriteria(RestrictionCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Restriction> specification = createSpecification(criteria);
        return restrictionMapper.toDto(restrictionRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link RestrictionDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<RestrictionDTO> findByCriteria(RestrictionCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Restriction> specification = createSpecification(criteria);
        return restrictionRepository.findAll(specification, page)
            .map(restrictionMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(RestrictionCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Restriction> specification = createSpecification(criteria);
        return restrictionRepository.count(specification);
    }

    /**
     * Function to convert RestrictionCriteria to a {@link Specification}
     */
    private Specification<Restriction> createSpecification(RestrictionCriteria criteria) {
        Specification<Restriction> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Restriction_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), Restriction_.title));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), Restriction_.description));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), Restriction_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), Restriction_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), Restriction_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), Restriction_.modifyDate));
            }
            if (criteria.getGuid() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGuid(), Restriction_.guid));
            }
            if (criteria.getRequestEducationalModuleId() != null) {
                specification = specification.and(buildSpecification(criteria.getRequestEducationalModuleId(),
                    root -> root.join(Restriction_.requestEducationalModules, JoinType.LEFT).get(RequestEducationalModule_.id)));
            }
            if (criteria.getEducationalModuleId() != null) {
                specification = specification.and(buildSpecification(criteria.getEducationalModuleId(),
                    root -> root.join(Restriction_.educationalModules, JoinType.LEFT).get(EducationalModule_.id)));
            }
        }
        return specification;
    }
}
