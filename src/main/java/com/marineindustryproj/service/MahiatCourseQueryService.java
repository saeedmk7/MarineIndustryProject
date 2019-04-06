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

import com.marineindustryproj.domain.MahiatCourse;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.MahiatCourseRepository;
import com.marineindustryproj.service.dto.MahiatCourseCriteria;
import com.marineindustryproj.service.dto.MahiatCourseDTO;
import com.marineindustryproj.service.mapper.MahiatCourseMapper;

/**
 * Service for executing complex queries for MahiatCourse entities in the database.
 * The main input is a {@link MahiatCourseCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MahiatCourseDTO} or a {@link Page} of {@link MahiatCourseDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MahiatCourseQueryService extends QueryService<MahiatCourse> {

    private final Logger log = LoggerFactory.getLogger(MahiatCourseQueryService.class);

    private final MahiatCourseRepository mahiatCourseRepository;

    private final MahiatCourseMapper mahiatCourseMapper;

    public MahiatCourseQueryService(MahiatCourseRepository mahiatCourseRepository, MahiatCourseMapper mahiatCourseMapper) {
        this.mahiatCourseRepository = mahiatCourseRepository;
        this.mahiatCourseMapper = mahiatCourseMapper;
    }

    /**
     * Return a {@link List} of {@link MahiatCourseDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MahiatCourseDTO> findByCriteria(MahiatCourseCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MahiatCourse> specification = createSpecification(criteria);
        return mahiatCourseMapper.toDto(mahiatCourseRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MahiatCourseDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MahiatCourseDTO> findByCriteria(MahiatCourseCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MahiatCourse> specification = createSpecification(criteria);
        return mahiatCourseRepository.findAll(specification, page)
            .map(mahiatCourseMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MahiatCourseCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MahiatCourse> specification = createSpecification(criteria);
        return mahiatCourseRepository.count(specification);
    }

    /**
     * Function to convert MahiatCourseCriteria to a {@link Specification}
     */
    private Specification<MahiatCourse> createSpecification(MahiatCourseCriteria criteria) {
        Specification<MahiatCourse> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MahiatCourse_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), MahiatCourse_.title));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), MahiatCourse_.code));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), MahiatCourse_.description));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), MahiatCourse_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), MahiatCourse_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), MahiatCourse_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), MahiatCourse_.modifyDate));
            }
            if (criteria.getDesignAndPlanningId() != null) {
                specification = specification.and(buildSpecification(criteria.getDesignAndPlanningId(),
                    root -> root.join(MahiatCourse_.designAndPlannings, JoinType.LEFT).get(DesignAndPlanning_.id)));
            }
        }
        return specification;
    }
}
