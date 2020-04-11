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

import com.marineindustryproj.domain.TeacherCriteria;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.TeacherCriteriaRepository;
import com.marineindustryproj.service.dto.TeacherCriteriaCriteria;
import com.marineindustryproj.service.dto.TeacherCriteriaDTO;
import com.marineindustryproj.service.mapper.TeacherCriteriaMapper;

/**
 * Service for executing complex queries for TeacherCriteria entities in the database.
 * The main input is a {@link TeacherCriteriaCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link TeacherCriteriaDTO} or a {@link Page} of {@link TeacherCriteriaDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class TeacherCriteriaQueryService extends QueryService<TeacherCriteria> {

    private final Logger log = LoggerFactory.getLogger(TeacherCriteriaQueryService.class);

    private final TeacherCriteriaRepository teacherCriteriaRepository;

    private final TeacherCriteriaMapper teacherCriteriaMapper;

    public TeacherCriteriaQueryService(TeacherCriteriaRepository teacherCriteriaRepository, TeacherCriteriaMapper teacherCriteriaMapper) {
        this.teacherCriteriaRepository = teacherCriteriaRepository;
        this.teacherCriteriaMapper = teacherCriteriaMapper;
    }

    /**
     * Return a {@link List} of {@link TeacherCriteriaDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<TeacherCriteriaDTO> findByCriteria(TeacherCriteriaCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<TeacherCriteria> specification = createSpecification(criteria);
        return teacherCriteriaMapper.toDto(teacherCriteriaRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link TeacherCriteriaDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<TeacherCriteriaDTO> findByCriteria(TeacherCriteriaCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<TeacherCriteria> specification = createSpecification(criteria);
        return teacherCriteriaRepository.findAll(specification, page)
            .map(teacherCriteriaMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(TeacherCriteriaCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<TeacherCriteria> specification = createSpecification(criteria);
        return teacherCriteriaRepository.count(specification);
    }

    /**
     * Function to convert TeacherCriteriaCriteria to a {@link Specification}
     */
    private Specification<TeacherCriteria> createSpecification(TeacherCriteriaCriteria criteria) {
        Specification<TeacherCriteria> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), TeacherCriteria_.id));
            }
            if (criteria.getGroup() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGroup(), TeacherCriteria_.group));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), TeacherCriteria_.title));
            }
            if (criteria.getDisplayOrder() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDisplayOrder(), TeacherCriteria_.displayOrder));
            }
            if (criteria.getWeight() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getWeight(), TeacherCriteria_.weight));
            }
            if (criteria.getSecondWeight() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSecondWeight(), TeacherCriteria_.secondWeight));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), TeacherCriteria_.description));
            }
            if (criteria.getPeopleCount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPeopleCount(), TeacherCriteria_.peopleCount));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), TeacherCriteria_.code));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), TeacherCriteria_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), TeacherCriteria_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), TeacherCriteria_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), TeacherCriteria_.modifyDate));
            }
            if (criteria.getTeacherGradeScoreId() != null) {
                specification = specification.and(buildSpecification(criteria.getTeacherGradeScoreId(),
                    root -> root.join(TeacherCriteria_.teacherGradeScores, JoinType.LEFT).get(TeacherGradeScore_.id)));
            }
        }
        return specification;
    }
}
