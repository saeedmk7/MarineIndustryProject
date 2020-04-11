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

import com.marineindustryproj.domain.TeacherGradeScore;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.TeacherGradeScoreRepository;
import com.marineindustryproj.service.dto.TeacherGradeScoreCriteria;
import com.marineindustryproj.service.dto.TeacherGradeScoreDTO;
import com.marineindustryproj.service.mapper.TeacherGradeScoreMapper;

/**
 * Service for executing complex queries for TeacherGradeScore entities in the database.
 * The main input is a {@link TeacherGradeScoreCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link TeacherGradeScoreDTO} or a {@link Page} of {@link TeacherGradeScoreDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class TeacherGradeScoreQueryService extends QueryService<TeacherGradeScore> {

    private final Logger log = LoggerFactory.getLogger(TeacherGradeScoreQueryService.class);

    private final TeacherGradeScoreRepository teacherGradeScoreRepository;

    private final TeacherGradeScoreMapper teacherGradeScoreMapper;

    public TeacherGradeScoreQueryService(TeacherGradeScoreRepository teacherGradeScoreRepository, TeacherGradeScoreMapper teacherGradeScoreMapper) {
        this.teacherGradeScoreRepository = teacherGradeScoreRepository;
        this.teacherGradeScoreMapper = teacherGradeScoreMapper;
    }

    /**
     * Return a {@link List} of {@link TeacherGradeScoreDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<TeacherGradeScoreDTO> findByCriteria(TeacherGradeScoreCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<TeacherGradeScore> specification = createSpecification(criteria);
        return teacherGradeScoreMapper.toDto(teacherGradeScoreRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link TeacherGradeScoreDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<TeacherGradeScoreDTO> findByCriteria(TeacherGradeScoreCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<TeacherGradeScore> specification = createSpecification(criteria);
        return teacherGradeScoreRepository.findAll(specification, page)
            .map(teacherGradeScoreMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(TeacherGradeScoreCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<TeacherGradeScore> specification = createSpecification(criteria);
        return teacherGradeScoreRepository.count(specification);
    }

    /**
     * Function to convert TeacherGradeScoreCriteria to a {@link Specification}
     */
    private Specification<TeacherGradeScore> createSpecification(TeacherGradeScoreCriteria criteria) {
        Specification<TeacherGradeScore> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), TeacherGradeScore_.id));
            }
            if (criteria.getScore() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getScore(), TeacherGradeScore_.score));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), TeacherGradeScore_.description));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), TeacherGradeScore_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), TeacherGradeScore_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), TeacherGradeScore_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), TeacherGradeScore_.modifyDate));
            }
            if (criteria.getTeacherCriteriaId() != null) {
                specification = specification.and(buildSpecification(criteria.getTeacherCriteriaId(),
                    root -> root.join(TeacherGradeScore_.teacherCriteria, JoinType.LEFT).get(TeacherCriteria_.id)));
            }
            if (criteria.getTeacherGradeId() != null) {
                specification = specification.and(buildSpecification(criteria.getTeacherGradeId(),
                    root -> root.join(TeacherGradeScore_.teacherGrade, JoinType.LEFT).get(TeacherGrade_.id)));
            }
        }
        return specification;
    }
}
