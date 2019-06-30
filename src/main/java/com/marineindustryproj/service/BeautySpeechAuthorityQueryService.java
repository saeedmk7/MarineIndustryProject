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

import com.marineindustryproj.domain.BeautySpeechAuthority;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.BeautySpeechAuthorityRepository;
import com.marineindustryproj.service.dto.BeautySpeechAuthorityCriteria;
import com.marineindustryproj.service.dto.BeautySpeechAuthorityDTO;
import com.marineindustryproj.service.mapper.BeautySpeechAuthorityMapper;

/**
 * Service for executing complex queries for BeautySpeechAuthority entities in the database.
 * The main input is a {@link BeautySpeechAuthorityCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link BeautySpeechAuthorityDTO} or a {@link Page} of {@link BeautySpeechAuthorityDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class BeautySpeechAuthorityQueryService extends QueryService<BeautySpeechAuthority> {

    private final Logger log = LoggerFactory.getLogger(BeautySpeechAuthorityQueryService.class);

    private final BeautySpeechAuthorityRepository beautySpeechAuthorityRepository;

    private final BeautySpeechAuthorityMapper beautySpeechAuthorityMapper;

    public BeautySpeechAuthorityQueryService(BeautySpeechAuthorityRepository beautySpeechAuthorityRepository, BeautySpeechAuthorityMapper beautySpeechAuthorityMapper) {
        this.beautySpeechAuthorityRepository = beautySpeechAuthorityRepository;
        this.beautySpeechAuthorityMapper = beautySpeechAuthorityMapper;
    }

    /**
     * Return a {@link List} of {@link BeautySpeechAuthorityDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<BeautySpeechAuthorityDTO> findByCriteria(BeautySpeechAuthorityCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<BeautySpeechAuthority> specification = createSpecification(criteria);
        return beautySpeechAuthorityMapper.toDto(beautySpeechAuthorityRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link BeautySpeechAuthorityDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<BeautySpeechAuthorityDTO> findByCriteria(BeautySpeechAuthorityCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<BeautySpeechAuthority> specification = createSpecification(criteria);
        return beautySpeechAuthorityRepository.findAll(specification, page)
            .map(beautySpeechAuthorityMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(BeautySpeechAuthorityCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<BeautySpeechAuthority> specification = createSpecification(criteria);
        return beautySpeechAuthorityRepository.count(specification);
    }

    /**
     * Function to convert BeautySpeechAuthorityCriteria to a {@link Specification}
     */
    private Specification<BeautySpeechAuthority> createSpecification(BeautySpeechAuthorityCriteria criteria) {
        Specification<BeautySpeechAuthority> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), BeautySpeechAuthority_.id));
            }
            if (criteria.getAuthorityName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAuthorityName(), BeautySpeechAuthority_.authorityName));
            }
            if (criteria.getHasEditPermission() != null) {
                specification = specification.and(buildSpecification(criteria.getHasEditPermission(), BeautySpeechAuthority_.hasEditPermission));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), BeautySpeechAuthority_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), BeautySpeechAuthority_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), BeautySpeechAuthority_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), BeautySpeechAuthority_.modifyDate));
            }
            if (criteria.getBeautySpeechId() != null) {
                specification = specification.and(buildSpecification(criteria.getBeautySpeechId(),
                    root -> root.join(BeautySpeechAuthority_.beautySpeech, JoinType.LEFT).get(BeautySpeech_.id)));
            }
        }
        return specification;
    }
}
