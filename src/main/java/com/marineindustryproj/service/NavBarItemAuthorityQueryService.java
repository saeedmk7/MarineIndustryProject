package com.marineindustryproj.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import com.marineindustryproj.service.dto.NavBarItemCriteria;
import io.github.jhipster.service.filter.StringFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.marineindustryproj.domain.NavBarItemAuthority;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.NavBarItemAuthorityRepository;
import com.marineindustryproj.service.dto.NavBarItemAuthorityCriteria;
import com.marineindustryproj.service.dto.NavBarItemAuthorityDTO;
import com.marineindustryproj.service.mapper.NavBarItemAuthorityMapper;

/**
 * Service for executing complex queries for NavBarItemAuthority entities in the database.
 * The main input is a {@link NavBarItemAuthorityCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link NavBarItemAuthorityDTO} or a {@link Page} of {@link NavBarItemAuthorityDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class NavBarItemAuthorityQueryService extends QueryService<NavBarItemAuthority> {

    private final Logger log = LoggerFactory.getLogger(NavBarItemAuthorityQueryService.class);

    private final NavBarItemAuthorityRepository navBarItemAuthorityRepository;

    private final NavBarItemAuthorityMapper navBarItemAuthorityMapper;

    public NavBarItemAuthorityQueryService(NavBarItemAuthorityRepository navBarItemAuthorityRepository, NavBarItemAuthorityMapper navBarItemAuthorityMapper) {
        this.navBarItemAuthorityRepository = navBarItemAuthorityRepository;
        this.navBarItemAuthorityMapper = navBarItemAuthorityMapper;
    }

    /**
     * Return a {@link List} of {@link NavBarItemAuthorityDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<NavBarItemAuthorityDTO> findByCriteria(NavBarItemAuthorityCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<NavBarItemAuthority> specification = createSpecification(criteria);
        return navBarItemAuthorityMapper.toDto(navBarItemAuthorityRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link NavBarItemAuthorityDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<NavBarItemAuthorityDTO> findByCriteria(NavBarItemAuthorityCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<NavBarItemAuthority> specification = createSpecification(criteria);
        return navBarItemAuthorityRepository.findAll(specification, page)
            .map(navBarItemAuthorityMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(NavBarItemAuthorityCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<NavBarItemAuthority> specification = createSpecification(criteria);
        return navBarItemAuthorityRepository.count(specification);
    }

    /**
     * Function to convert NavBarItemAuthorityCriteria to a {@link Specification}
     */
    public Specification<NavBarItemAuthority> createSpecification(NavBarItemAuthorityCriteria criteria) {
        Specification<NavBarItemAuthority> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), NavBarItemAuthority_.id));
            }
            if (criteria.getAuthorityName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAuthorityName(), NavBarItemAuthority_.authorityName));
            }
            if (criteria.getHasEditPermission() != null) {
                specification = specification.and(buildSpecification(criteria.getHasEditPermission(), NavBarItemAuthority_.hasEditPermission));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), NavBarItemAuthority_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), NavBarItemAuthority_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), NavBarItemAuthority_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), NavBarItemAuthority_.modifyDate));
            }
            if (criteria.getNavBarItemId() != null) {
                specification = specification.and(buildSpecification(criteria.getNavBarItemId(),
                    root -> root.join(NavBarItemAuthority_.navBarItem, JoinType.LEFT).get(NavBarItem_.id)));
            }
        }
        return specification;
    }
}
