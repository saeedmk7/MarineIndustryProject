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

import com.marineindustryproj.domain.Capitation;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.CapitationRepository;
import com.marineindustryproj.service.dto.CapitationCriteria;
import com.marineindustryproj.service.dto.CapitationDTO;
import com.marineindustryproj.service.mapper.CapitationMapper;

/**
 * Service for executing complex queries for Capitation entities in the database.
 * The main input is a {@link CapitationCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CapitationDTO} or a {@link Page} of {@link CapitationDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CapitationQueryService extends QueryService<Capitation> {

    private final Logger log = LoggerFactory.getLogger(CapitationQueryService.class);

    private final CapitationRepository capitationRepository;

    private final CapitationMapper capitationMapper;

    public CapitationQueryService(CapitationRepository capitationRepository, CapitationMapper capitationMapper) {
        this.capitationRepository = capitationRepository;
        this.capitationMapper = capitationMapper;
    }

    /**
     * Return a {@link List} of {@link CapitationDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CapitationDTO> findByCriteria(CapitationCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Capitation> specification = createSpecification(criteria);
        return capitationMapper.toDto(capitationRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CapitationDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CapitationDTO> findByCriteria(CapitationCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Capitation> specification = createSpecification(criteria);
        return capitationRepository.findAll(specification, page)
            .map(capitationMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CapitationCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Capitation> specification = createSpecification(criteria);
        return capitationRepository.count(specification);
    }

    /**
     * Function to convert CapitationCriteria to a {@link Specification}
     */
    private Specification<Capitation> createSpecification(CapitationCriteria criteria) {
        Specification<Capitation> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Capitation_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), Capitation_.title));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), Capitation_.code));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), Capitation_.description));
            }
            if (criteria.getEmployeeMaximumAllowablePersonHours() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEmployeeMaximumAllowablePersonHours(), Capitation_.employeeMaximumAllowablePersonHours));
            }
            if (criteria.getBossMaximumAllowablePersonHours() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBossMaximumAllowablePersonHours(), Capitation_.bossMaximumAllowablePersonHours));
            }
            if (criteria.getEmployeeMaximumAllowablePersonCosts() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEmployeeMaximumAllowablePersonCosts(), Capitation_.employeeMaximumAllowablePersonCosts));
            }
            if (criteria.getBossMaximumAllowablePersonCosts() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBossMaximumAllowablePersonCosts(), Capitation_.bossMaximumAllowablePersonCosts));
            }
            if (criteria.getCapitationYear() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCapitationYear(), Capitation_.capitationYear));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), Capitation_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), Capitation_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), Capitation_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), Capitation_.modifyDate));
            }
        }
        return specification;
    }
}
