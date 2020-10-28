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

import com.marineindustryproj.domain.TeacherCriteriaGroup;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.TeacherCriteriaGroupRepository;
import com.marineindustryproj.service.dto.TeacherCriteriaGroupCriteria;
import com.marineindustryproj.service.dto.TeacherCriteriaGroupDTO;
import com.marineindustryproj.service.mapper.TeacherCriteriaGroupMapper;

/**
 * Service for executing complex queries for TeacherCriteriaGroup entities in the database.
 * The main input is a {@link TeacherCriteriaGroupCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link TeacherCriteriaGroupDTO} or a {@link Page} of {@link TeacherCriteriaGroupDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class TeacherCriteriaGroupQueryService extends QueryService<TeacherCriteriaGroup> {

    private final Logger log = LoggerFactory.getLogger(TeacherCriteriaGroupQueryService.class);

    private final TeacherCriteriaGroupRepository teacherCriteriaGroupRepository;

    private final TeacherCriteriaGroupMapper teacherCriteriaGroupMapper;

    public TeacherCriteriaGroupQueryService(TeacherCriteriaGroupRepository teacherCriteriaGroupRepository, TeacherCriteriaGroupMapper teacherCriteriaGroupMapper) {
        this.teacherCriteriaGroupRepository = teacherCriteriaGroupRepository;
        this.teacherCriteriaGroupMapper = teacherCriteriaGroupMapper;
    }

    /**
     * Return a {@link List} of {@link TeacherCriteriaGroupDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<TeacherCriteriaGroupDTO> findByCriteria(TeacherCriteriaGroupCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<TeacherCriteriaGroup> specification = createSpecification(criteria);
        return teacherCriteriaGroupMapper.toDto(teacherCriteriaGroupRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link TeacherCriteriaGroupDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<TeacherCriteriaGroupDTO> findByCriteria(TeacherCriteriaGroupCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<TeacherCriteriaGroup> specification = createSpecification(criteria);
        return teacherCriteriaGroupRepository.findAll(specification, page)
            .map(teacherCriteriaGroupMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(TeacherCriteriaGroupCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<TeacherCriteriaGroup> specification = createSpecification(criteria);
        return teacherCriteriaGroupRepository.count(specification);
    }

    /**
     * Function to convert TeacherCriteriaGroupCriteria to a {@link Specification}
     */
    private Specification<TeacherCriteriaGroup> createSpecification(TeacherCriteriaGroupCriteria criteria) {
        Specification<TeacherCriteriaGroup> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), TeacherCriteriaGroup_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), TeacherCriteriaGroup_.title));
            }
            if (criteria.getDisplayOrder() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDisplayOrder(), TeacherCriteriaGroup_.displayOrder));
            }
            if (criteria.getWeight() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getWeight(), TeacherCriteriaGroup_.weight));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), TeacherCriteriaGroup_.description));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), TeacherCriteriaGroup_.code));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), TeacherCriteriaGroup_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), TeacherCriteriaGroup_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), TeacherCriteriaGroup_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), TeacherCriteriaGroup_.modifyDate));
            }
            if (criteria.getTeacherGradeId() != null) {
                specification = specification.and(buildSpecification(criteria.getTeacherGradeId(),
                    root -> root.join(TeacherCriteriaGroup_.teacherGrades, JoinType.LEFT).get(TeacherGrade_.id)));
            }
            if (criteria.getTeacherCriteriaId() != null) {
                specification = specification.and(buildSpecification(criteria.getTeacherCriteriaId(),
                    root -> root.join(TeacherCriteriaGroup_.teacherCriteria, JoinType.LEFT).get(TeacherCriteria_.id)));
            }
        }
        return specification;
    }
}
