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

import com.marineindustryproj.domain.TeachApproach;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.TeachApproachRepository;
import com.marineindustryproj.service.dto.TeachApproachCriteria;
import com.marineindustryproj.service.dto.TeachApproachDTO;
import com.marineindustryproj.service.mapper.TeachApproachMapper;

/**
 * Service for executing complex queries for TeachApproach entities in the database.
 * The main input is a {@link TeachApproachCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link TeachApproachDTO} or a {@link Page} of {@link TeachApproachDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class TeachApproachQueryService extends QueryService<TeachApproach> {

    private final Logger log = LoggerFactory.getLogger(TeachApproachQueryService.class);

    private final TeachApproachRepository teachApproachRepository;

    private final TeachApproachMapper teachApproachMapper;

    public TeachApproachQueryService(TeachApproachRepository teachApproachRepository, TeachApproachMapper teachApproachMapper) {
        this.teachApproachRepository = teachApproachRepository;
        this.teachApproachMapper = teachApproachMapper;
    }

    /**
     * Return a {@link List} of {@link TeachApproachDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<TeachApproachDTO> findByCriteria(TeachApproachCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<TeachApproach> specification = createSpecification(criteria);
        return teachApproachMapper.toDto(teachApproachRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link TeachApproachDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<TeachApproachDTO> findByCriteria(TeachApproachCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<TeachApproach> specification = createSpecification(criteria);
        return teachApproachRepository.findAll(specification, page)
            .map(teachApproachMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(TeachApproachCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<TeachApproach> specification = createSpecification(criteria);
        return teachApproachRepository.count(specification);
    }

    /**
     * Function to convert TeachApproachCriteria to a {@link Specification}
     */
    private Specification<TeachApproach> createSpecification(TeachApproachCriteria criteria) {
        Specification<TeachApproach> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), TeachApproach_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), TeachApproach_.title));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), TeachApproach_.code));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), TeachApproach_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), TeachApproach_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), TeachApproach_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), TeachApproach_.modifyDate));
            }
            if (criteria.getRequestOrganizationNiazsanjiId() != null) {
                specification = specification.and(buildSpecification(criteria.getRequestOrganizationNiazsanjiId(),
                    root -> root.join(TeachApproach_.requestOrganizationNiazsanjis, JoinType.LEFT).get(RequestOrganizationNiazsanji_.id)));
            }
            if (criteria.getFinalOrganizationNiazsanjiId() != null) {
                specification = specification.and(buildSpecification(criteria.getFinalOrganizationNiazsanjiId(),
                    root -> root.join(TeachApproach_.finalOrganizationNiazsanjis, JoinType.LEFT).get(FinalOrganizationNiazsanji_.id)));
            }
        }
        return specification;
    }
}
