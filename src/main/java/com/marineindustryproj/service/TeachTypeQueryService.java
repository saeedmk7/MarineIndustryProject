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

import com.marineindustryproj.domain.TeachType;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.TeachTypeRepository;
import com.marineindustryproj.service.dto.TeachTypeCriteria;
import com.marineindustryproj.service.dto.TeachTypeDTO;
import com.marineindustryproj.service.mapper.TeachTypeMapper;

/**
 * Service for executing complex queries for TeachType entities in the database.
 * The main input is a {@link TeachTypeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link TeachTypeDTO} or a {@link Page} of {@link TeachTypeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class TeachTypeQueryService extends QueryService<TeachType> {

    private final Logger log = LoggerFactory.getLogger(TeachTypeQueryService.class);

    private final TeachTypeRepository teachTypeRepository;

    private final TeachTypeMapper teachTypeMapper;

    public TeachTypeQueryService(TeachTypeRepository teachTypeRepository, TeachTypeMapper teachTypeMapper) {
        this.teachTypeRepository = teachTypeRepository;
        this.teachTypeMapper = teachTypeMapper;
    }

    /**
     * Return a {@link List} of {@link TeachTypeDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<TeachTypeDTO> findByCriteria(TeachTypeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<TeachType> specification = createSpecification(criteria);
        return teachTypeMapper.toDto(teachTypeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link TeachTypeDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<TeachTypeDTO> findByCriteria(TeachTypeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<TeachType> specification = createSpecification(criteria);
        return teachTypeRepository.findAll(specification, page)
            .map(teachTypeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(TeachTypeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<TeachType> specification = createSpecification(criteria);
        return teachTypeRepository.count(specification);
    }

    /**
     * Function to convert TeachTypeCriteria to a {@link Specification}
     */
    private Specification<TeachType> createSpecification(TeachTypeCriteria criteria) {
        Specification<TeachType> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), TeachType_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), TeachType_.title));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), TeachType_.code));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), TeachType_.description));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), TeachType_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), TeachType_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), TeachType_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), TeachType_.modifyDate));
            }
            if (criteria.getDesignAndPlanningId() != null) {
                specification = specification.and(buildSpecification(criteria.getDesignAndPlanningId(),
                    root -> root.join(TeachType_.designAndPlannings, JoinType.LEFT).get(DesignAndPlanning_.id)));
            }
        }
        return specification;
    }
}
