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

import com.marineindustryproj.domain.JamHelpAuthority;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.JamHelpAuthorityRepository;
import com.marineindustryproj.service.dto.JamHelpAuthorityCriteria;
import com.marineindustryproj.service.dto.JamHelpAuthorityDTO;
import com.marineindustryproj.service.mapper.JamHelpAuthorityMapper;

/**
 * Service for executing complex queries for JamHelpAuthority entities in the database.
 * The main input is a {@link JamHelpAuthorityCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link JamHelpAuthorityDTO} or a {@link Page} of {@link JamHelpAuthorityDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class JamHelpAuthorityQueryService extends QueryService<JamHelpAuthority> {

    private final Logger log = LoggerFactory.getLogger(JamHelpAuthorityQueryService.class);

    private final JamHelpAuthorityRepository jamHelpAuthorityRepository;

    private final JamHelpAuthorityMapper jamHelpAuthorityMapper;

    public JamHelpAuthorityQueryService(JamHelpAuthorityRepository jamHelpAuthorityRepository, JamHelpAuthorityMapper jamHelpAuthorityMapper) {
        this.jamHelpAuthorityRepository = jamHelpAuthorityRepository;
        this.jamHelpAuthorityMapper = jamHelpAuthorityMapper;
    }

    /**
     * Return a {@link List} of {@link JamHelpAuthorityDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<JamHelpAuthorityDTO> findByCriteria(JamHelpAuthorityCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<JamHelpAuthority> specification = createSpecification(criteria);
        return jamHelpAuthorityMapper.toDto(jamHelpAuthorityRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link JamHelpAuthorityDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<JamHelpAuthorityDTO> findByCriteria(JamHelpAuthorityCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<JamHelpAuthority> specification = createSpecification(criteria);
        return jamHelpAuthorityRepository.findAll(specification, page)
            .map(jamHelpAuthorityMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(JamHelpAuthorityCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<JamHelpAuthority> specification = createSpecification(criteria);
        return jamHelpAuthorityRepository.count(specification);
    }

    /**
     * Function to convert JamHelpAuthorityCriteria to a {@link Specification}
     */
    private Specification<JamHelpAuthority> createSpecification(JamHelpAuthorityCriteria criteria) {
        Specification<JamHelpAuthority> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), JamHelpAuthority_.id));
            }
            if (criteria.getAuthorityName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAuthorityName(), JamHelpAuthority_.authorityName));
            }
            if (criteria.getHasEditPermission() != null) {
                specification = specification.and(buildSpecification(criteria.getHasEditPermission(), JamHelpAuthority_.hasEditPermission));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), JamHelpAuthority_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), JamHelpAuthority_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), JamHelpAuthority_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), JamHelpAuthority_.modifyDate));
            }
            if (criteria.getJamHelpId() != null) {
                specification = specification.and(buildSpecification(criteria.getJamHelpId(),
                    root -> root.join(JamHelpAuthority_.jamHelp, JoinType.LEFT).get(JamHelp_.id)));
            }
        }
        return specification;
    }
}
