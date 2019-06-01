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

import com.marineindustryproj.domain.JamHelp;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.JamHelpRepository;
import com.marineindustryproj.service.dto.JamHelpCriteria;
import com.marineindustryproj.service.dto.JamHelpDTO;
import com.marineindustryproj.service.mapper.JamHelpMapper;

/**
 * Service for executing complex queries for JamHelp entities in the database.
 * The main input is a {@link JamHelpCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link JamHelpDTO} or a {@link Page} of {@link JamHelpDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class JamHelpQueryService extends QueryService<JamHelp> {

    private final Logger log = LoggerFactory.getLogger(JamHelpQueryService.class);

    private final JamHelpRepository jamHelpRepository;

    private final JamHelpMapper jamHelpMapper;

    public JamHelpQueryService(JamHelpRepository jamHelpRepository, JamHelpMapper jamHelpMapper) {
        this.jamHelpRepository = jamHelpRepository;
        this.jamHelpMapper = jamHelpMapper;
    }

    /**
     * Return a {@link List} of {@link JamHelpDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<JamHelpDTO> findByCriteria(JamHelpCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<JamHelp> specification = createSpecification(criteria);
        return jamHelpMapper.toDto(jamHelpRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link JamHelpDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<JamHelpDTO> findByCriteria(JamHelpCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<JamHelp> specification = createSpecification(criteria);
        return jamHelpRepository.findAll(specification, page)
            .map(jamHelpMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(JamHelpCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<JamHelp> specification = createSpecification(criteria);
        return jamHelpRepository.count(specification);
    }

    /**
     * Function to convert JamHelpCriteria to a {@link Specification}
     */
    private Specification<JamHelp> createSpecification(JamHelpCriteria criteria) {
        Specification<JamHelp> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), JamHelp_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), JamHelp_.title));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), JamHelp_.description));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), JamHelp_.code));
            }
            if (criteria.getPageUrl() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPageUrl(), JamHelp_.pageUrl));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), JamHelp_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), JamHelp_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), JamHelp_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), JamHelp_.modifyDate));
            }
            if (criteria.getJamHelpAuthorityId() != null) {
                specification = specification.and(buildSpecification(criteria.getJamHelpAuthorityId(),
                    root -> root.join(JamHelp_.jamHelpAuthorities, JoinType.LEFT).get(JamHelpAuthority_.id)));
            }
        }
        return specification;
    }
}
