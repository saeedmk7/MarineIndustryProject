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

import com.marineindustryproj.domain.EmploymentType;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.EmploymentTypeRepository;
import com.marineindustryproj.service.dto.EmploymentTypeCriteria;
import com.marineindustryproj.service.dto.EmploymentTypeDTO;
import com.marineindustryproj.service.mapper.EmploymentTypeMapper;

/**
 * Service for executing complex queries for EmploymentType entities in the database.
 * The main input is a {@link EmploymentTypeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link EmploymentTypeDTO} or a {@link Page} of {@link EmploymentTypeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class EmploymentTypeQueryService extends QueryService<EmploymentType> {

    private final Logger log = LoggerFactory.getLogger(EmploymentTypeQueryService.class);

    private final EmploymentTypeRepository employmentTypeRepository;

    private final EmploymentTypeMapper employmentTypeMapper;

    public EmploymentTypeQueryService(EmploymentTypeRepository employmentTypeRepository, EmploymentTypeMapper employmentTypeMapper) {
        this.employmentTypeRepository = employmentTypeRepository;
        this.employmentTypeMapper = employmentTypeMapper;
    }

    /**
     * Return a {@link List} of {@link EmploymentTypeDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<EmploymentTypeDTO> findByCriteria(EmploymentTypeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<EmploymentType> specification = createSpecification(criteria);
        return employmentTypeMapper.toDto(employmentTypeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link EmploymentTypeDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<EmploymentTypeDTO> findByCriteria(EmploymentTypeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<EmploymentType> specification = createSpecification(criteria);
        return employmentTypeRepository.findAll(specification, page)
            .map(employmentTypeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(EmploymentTypeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<EmploymentType> specification = createSpecification(criteria);
        return employmentTypeRepository.count(specification);
    }

    /**
     * Function to convert EmploymentTypeCriteria to a {@link Specification}
     */
    private Specification<EmploymentType> createSpecification(EmploymentTypeCriteria criteria) {
        Specification<EmploymentType> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), EmploymentType_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), EmploymentType_.title));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), EmploymentType_.code));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), EmploymentType_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), EmploymentType_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), EmploymentType_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), EmploymentType_.modifyDate));
            }
            if (criteria.getPersonId() != null) {
                specification = specification.and(buildSpecification(criteria.getPersonId(),
                    root -> root.join(EmploymentType_.people, JoinType.LEFT).get(Person_.id)));
            }
        }
        return specification;
    }
}
