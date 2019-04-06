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

import com.marineindustryproj.domain.TeachingApproach;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.TeachingApproachRepository;
import com.marineindustryproj.service.dto.TeachingApproachCriteria;
import com.marineindustryproj.service.dto.TeachingApproachDTO;
import com.marineindustryproj.service.mapper.TeachingApproachMapper;

/**
 * Service for executing complex queries for TeachingApproach entities in the database.
 * The main input is a {@link TeachingApproachCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link TeachingApproachDTO} or a {@link Page} of {@link TeachingApproachDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class TeachingApproachQueryService extends QueryService<TeachingApproach> {

    private final Logger log = LoggerFactory.getLogger(TeachingApproachQueryService.class);

    private final TeachingApproachRepository teachingApproachRepository;

    private final TeachingApproachMapper teachingApproachMapper;

    public TeachingApproachQueryService(TeachingApproachRepository teachingApproachRepository, TeachingApproachMapper teachingApproachMapper) {
        this.teachingApproachRepository = teachingApproachRepository;
        this.teachingApproachMapper = teachingApproachMapper;
    }

    /**
     * Return a {@link List} of {@link TeachingApproachDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<TeachingApproachDTO> findByCriteria(TeachingApproachCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<TeachingApproach> specification = createSpecification(criteria);
        return teachingApproachMapper.toDto(teachingApproachRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link TeachingApproachDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<TeachingApproachDTO> findByCriteria(TeachingApproachCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<TeachingApproach> specification = createSpecification(criteria);
        return teachingApproachRepository.findAll(specification, page)
            .map(teachingApproachMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(TeachingApproachCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<TeachingApproach> specification = createSpecification(criteria);
        return teachingApproachRepository.count(specification);
    }

    /**
     * Function to convert TeachingApproachCriteria to a {@link Specification}
     */
    private Specification<TeachingApproach> createSpecification(TeachingApproachCriteria criteria) {
        Specification<TeachingApproach> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), TeachingApproach_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), TeachingApproach_.title));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), TeachingApproach_.code));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), TeachingApproach_.description));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), TeachingApproach_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), TeachingApproach_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), TeachingApproach_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), TeachingApproach_.modifyDate));
            }
            if (criteria.getDesignAndPlanningId() != null) {
                specification = specification.and(buildSpecification(criteria.getDesignAndPlanningId(),
                    root -> root.join(TeachingApproach_.designAndPlannings, JoinType.LEFT).get(DesignAndPlanning_.id)));
            }
        }
        return specification;
    }
}
