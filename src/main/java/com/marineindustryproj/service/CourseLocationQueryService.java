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

import com.marineindustryproj.domain.CourseLocation;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.CourseLocationRepository;
import com.marineindustryproj.service.dto.CourseLocationCriteria;
import com.marineindustryproj.service.dto.CourseLocationDTO;
import com.marineindustryproj.service.mapper.CourseLocationMapper;

/**
 * Service for executing complex queries for CourseLocation entities in the database.
 * The main input is a {@link CourseLocationCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CourseLocationDTO} or a {@link Page} of {@link CourseLocationDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CourseLocationQueryService extends QueryService<CourseLocation> {

    private final Logger log = LoggerFactory.getLogger(CourseLocationQueryService.class);

    private final CourseLocationRepository courseLocationRepository;

    private final CourseLocationMapper courseLocationMapper;

    public CourseLocationQueryService(CourseLocationRepository courseLocationRepository, CourseLocationMapper courseLocationMapper) {
        this.courseLocationRepository = courseLocationRepository;
        this.courseLocationMapper = courseLocationMapper;
    }

    /**
     * Return a {@link List} of {@link CourseLocationDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CourseLocationDTO> findByCriteria(CourseLocationCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<CourseLocation> specification = createSpecification(criteria);
        return courseLocationMapper.toDto(courseLocationRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CourseLocationDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CourseLocationDTO> findByCriteria(CourseLocationCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CourseLocation> specification = createSpecification(criteria);
        return courseLocationRepository.findAll(specification, page)
            .map(courseLocationMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CourseLocationCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<CourseLocation> specification = createSpecification(criteria);
        return courseLocationRepository.count(specification);
    }

    /**
     * Function to convert CourseLocationCriteria to a {@link Specification}
     */
    private Specification<CourseLocation> createSpecification(CourseLocationCriteria criteria) {
        Specification<CourseLocation> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), CourseLocation_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), CourseLocation_.title));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), CourseLocation_.code));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), CourseLocation_.description));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), CourseLocation_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), CourseLocation_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), CourseLocation_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), CourseLocation_.modifyDate));
            }
            if (criteria.getDesignAndPlanningId() != null) {
                specification = specification.and(buildSpecification(criteria.getDesignAndPlanningId(),
                    root -> root.join(CourseLocation_.designAndPlannings, JoinType.LEFT).get(DesignAndPlanning_.id)));
            }
        }
        return specification;
    }
}
