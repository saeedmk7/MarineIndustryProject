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

import com.marineindustryproj.domain.CourseType;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.CourseTypeRepository;
import com.marineindustryproj.service.dto.CourseTypeCriteria;
import com.marineindustryproj.service.dto.CourseTypeDTO;
import com.marineindustryproj.service.mapper.CourseTypeMapper;

/**
 * Service for executing complex queries for CourseType entities in the database.
 * The main input is a {@link CourseTypeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CourseTypeDTO} or a {@link Page} of {@link CourseTypeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CourseTypeQueryService extends QueryService<CourseType> {

    private final Logger log = LoggerFactory.getLogger(CourseTypeQueryService.class);

    private final CourseTypeRepository courseTypeRepository;

    private final CourseTypeMapper courseTypeMapper;

    public CourseTypeQueryService(CourseTypeRepository courseTypeRepository, CourseTypeMapper courseTypeMapper) {
        this.courseTypeRepository = courseTypeRepository;
        this.courseTypeMapper = courseTypeMapper;
    }

    /**
     * Return a {@link List} of {@link CourseTypeDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CourseTypeDTO> findByCriteria(CourseTypeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CourseType> specification = createSpecification(criteria);
        return courseTypeMapper.toDto(courseTypeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CourseTypeDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CourseTypeDTO> findByCriteria(CourseTypeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CourseType> specification = createSpecification(criteria);
        return courseTypeRepository.findAll(specification, page)
            .map(courseTypeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CourseTypeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<CourseType> specification = createSpecification(criteria);
        return courseTypeRepository.count(specification);
    }

    /**
     * Function to convert CourseTypeCriteria to a {@link Specification}
     */
    private Specification<CourseType> createSpecification(CourseTypeCriteria criteria) {
        Specification<CourseType> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), CourseType_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), CourseType_.title));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), CourseType_.code));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), CourseType_.description));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), CourseType_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), CourseType_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), CourseType_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), CourseType_.modifyDate));
            }
            if (criteria.getDesignAndPlanningId() != null) {
                specification = specification.and(buildSpecification(criteria.getDesignAndPlanningId(),
                    root -> root.join(CourseType_.designAndPlannings, JoinType.LEFT).get(DesignAndPlanning_.id)));
            }
        }
        return specification;
    }
}
