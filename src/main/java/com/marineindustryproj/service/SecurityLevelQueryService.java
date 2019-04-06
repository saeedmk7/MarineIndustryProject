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

import com.marineindustryproj.domain.SecurityLevel;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.SecurityLevelRepository;
import com.marineindustryproj.service.dto.SecurityLevelCriteria;
import com.marineindustryproj.service.dto.SecurityLevelDTO;
import com.marineindustryproj.service.mapper.SecurityLevelMapper;

/**
 * Service for executing complex queries for SecurityLevel entities in the database.
 * The main input is a {@link SecurityLevelCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link SecurityLevelDTO} or a {@link Page} of {@link SecurityLevelDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class SecurityLevelQueryService extends QueryService<SecurityLevel> {

    private final Logger log = LoggerFactory.getLogger(SecurityLevelQueryService.class);

    private final SecurityLevelRepository securityLevelRepository;

    private final SecurityLevelMapper securityLevelMapper;

    public SecurityLevelQueryService(SecurityLevelRepository securityLevelRepository, SecurityLevelMapper securityLevelMapper) {
        this.securityLevelRepository = securityLevelRepository;
        this.securityLevelMapper = securityLevelMapper;
    }

    /**
     * Return a {@link List} of {@link SecurityLevelDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<SecurityLevelDTO> findByCriteria(SecurityLevelCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<SecurityLevel> specification = createSpecification(criteria);
        return securityLevelMapper.toDto(securityLevelRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link SecurityLevelDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<SecurityLevelDTO> findByCriteria(SecurityLevelCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<SecurityLevel> specification = createSpecification(criteria);
        return securityLevelRepository.findAll(specification, page)
            .map(securityLevelMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(SecurityLevelCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<SecurityLevel> specification = createSpecification(criteria);
        return securityLevelRepository.count(specification);
    }

    /**
     * Function to convert SecurityLevelCriteria to a {@link Specification}
     */
    private Specification<SecurityLevel> createSpecification(SecurityLevelCriteria criteria) {
        Specification<SecurityLevel> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), SecurityLevel_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), SecurityLevel_.title));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), SecurityLevel_.code));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), SecurityLevel_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), SecurityLevel_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), SecurityLevel_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), SecurityLevel_.modifyDate));
            }
            if (criteria.getEducationalModuleId() != null) {
                specification = specification.and(buildSpecification(criteria.getEducationalModuleId(),
                    root -> root.join(SecurityLevel_.educationalModules, JoinType.LEFT).get(EducationalModule_.id)));
            }
            if (criteria.getRequestEducationalModuleId() != null) {
                specification = specification.and(buildSpecification(criteria.getRequestEducationalModuleId(),
                    root -> root.join(SecurityLevel_.requestEducationalModules, JoinType.LEFT).get(RequestEducationalModule_.id)));
            }
        }
        return specification;
    }
}
