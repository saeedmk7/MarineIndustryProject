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

import com.marineindustryproj.domain.NavBarItem;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.NavBarItemRepository;
import com.marineindustryproj.service.dto.NavBarItemCriteria;
import com.marineindustryproj.service.dto.NavBarItemDTO;
import com.marineindustryproj.service.mapper.NavBarItemMapper;

/**
 * Service for executing complex queries for NavBarItem entities in the database.
 * The main input is a {@link NavBarItemCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link NavBarItemDTO} or a {@link Page} of {@link NavBarItemDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class NavBarItemQueryService extends QueryService<NavBarItem> {

    private final Logger log = LoggerFactory.getLogger(NavBarItemQueryService.class);

    private final NavBarItemRepository navBarItemRepository;

    private final NavBarItemMapper navBarItemMapper;

    public NavBarItemQueryService(NavBarItemRepository navBarItemRepository,
                                  NavBarItemMapper navBarItemMapper) {
        this.navBarItemRepository = navBarItemRepository;
        this.navBarItemMapper = navBarItemMapper;
    }

    /**
     * Return a {@link List} of {@link NavBarItemDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<NavBarItemDTO> findByCriteria(NavBarItemCriteria criteria) {
        log.debug("find by criteria : {}",
                  criteria);
        final Specification<NavBarItem> specification = createSpecification(criteria);
        return navBarItemMapper.toDto(navBarItemRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link NavBarItemDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<NavBarItemDTO> findByCriteria(NavBarItemCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<NavBarItem> specification = createSpecification(criteria);
        return navBarItemRepository.findAll(specification, page)
            .map(navBarItemMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(NavBarItemCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<NavBarItem> specification = createSpecification(criteria);
        return navBarItemRepository.count(specification);
    }

    /**
     * Function to convert NavBarItemCriteria to a {@link Specification}
     */
    private Specification<NavBarItem> createSpecification(NavBarItemCriteria criteria) {
        Specification<NavBarItem> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), NavBarItem_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), NavBarItem_.title));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), NavBarItem_.code));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), NavBarItem_.description));
            }
            if (criteria.getUrl() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUrl(), NavBarItem_.url));
            }
            if (criteria.getFaicon() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFaicon(), NavBarItem_.faicon));
            }
            if (criteria.getIsActive() != null) {
                specification = specification.and(buildSpecification(criteria.getIsActive(), NavBarItem_.isActive));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), NavBarItem_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), NavBarItem_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), NavBarItem_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), NavBarItem_.modifyDate));
            }
            if (criteria.getNavBarItemId() != null) {
                specification = specification.and(buildSpecification(criteria.getNavBarItemId(),
                    root -> root.join(NavBarItem_.navBarItems, JoinType.LEFT).get(NavBarItem_.id)));
            }
            if (criteria.getNavBarItemAuthorityId() != null) {
                specification = specification.and(buildSpecification(criteria.getNavBarItemAuthorityId(),
                    root -> root.join(NavBarItem_.navBarItemAuthorities, JoinType.LEFT).get(NavBarItemAuthority_.id)));
            }
            if (criteria.getParentId() != null) {
                specification = specification.and(buildSpecification(criteria.getParentId(),
                    root -> root.join(NavBarItem_.parent, JoinType.LEFT).get(NavBarItem_.id)));
            }
        }
        return specification;
    }
}
