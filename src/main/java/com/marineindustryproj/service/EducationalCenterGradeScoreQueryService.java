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

import com.marineindustryproj.domain.EducationalCenterGradeScore;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.EducationalCenterGradeScoreRepository;
import com.marineindustryproj.service.dto.EducationalCenterGradeScoreCriteria;
import com.marineindustryproj.service.dto.EducationalCenterGradeScoreDTO;
import com.marineindustryproj.service.mapper.EducationalCenterGradeScoreMapper;

/**
 * Service for executing complex queries for EducationalCenterGradeScore entities in the database.
 * The main input is a {@link EducationalCenterGradeScoreCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link EducationalCenterGradeScoreDTO} or a {@link Page} of {@link EducationalCenterGradeScoreDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class EducationalCenterGradeScoreQueryService extends QueryService<EducationalCenterGradeScore> {

    private final Logger log = LoggerFactory.getLogger(EducationalCenterGradeScoreQueryService.class);

    private final EducationalCenterGradeScoreRepository educationalCenterGradeScoreRepository;

    private final EducationalCenterGradeScoreMapper educationalCenterGradeScoreMapper;

    public EducationalCenterGradeScoreQueryService(EducationalCenterGradeScoreRepository educationalCenterGradeScoreRepository, EducationalCenterGradeScoreMapper educationalCenterGradeScoreMapper) {
        this.educationalCenterGradeScoreRepository = educationalCenterGradeScoreRepository;
        this.educationalCenterGradeScoreMapper = educationalCenterGradeScoreMapper;
    }

    /**
     * Return a {@link List} of {@link EducationalCenterGradeScoreDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<EducationalCenterGradeScoreDTO> findByCriteria(EducationalCenterGradeScoreCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<EducationalCenterGradeScore> specification = createSpecification(criteria);
        return educationalCenterGradeScoreMapper.toDto(educationalCenterGradeScoreRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link EducationalCenterGradeScoreDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<EducationalCenterGradeScoreDTO> findByCriteria(EducationalCenterGradeScoreCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<EducationalCenterGradeScore> specification = createSpecification(criteria);
        return educationalCenterGradeScoreRepository.findAll(specification, page)
            .map(educationalCenterGradeScoreMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(EducationalCenterGradeScoreCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<EducationalCenterGradeScore> specification = createSpecification(criteria);
        return educationalCenterGradeScoreRepository.count(specification);
    }

    /**
     * Function to convert EducationalCenterGradeScoreCriteria to a {@link Specification}
     */
    private Specification<EducationalCenterGradeScore> createSpecification(EducationalCenterGradeScoreCriteria criteria) {
        Specification<EducationalCenterGradeScore> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), EducationalCenterGradeScore_.id));
            }
            if (criteria.getScore() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getScore(), EducationalCenterGradeScore_.score));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), EducationalCenterGradeScore_.description));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), EducationalCenterGradeScore_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), EducationalCenterGradeScore_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), EducationalCenterGradeScore_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), EducationalCenterGradeScore_.modifyDate));
            }
            if (criteria.getEducationalCenterCriteriaId() != null) {
                specification = specification.and(buildSpecification(criteria.getEducationalCenterCriteriaId(),
                    root -> root.join(EducationalCenterGradeScore_.educationalCenterCriteria, JoinType.LEFT).get(EducationalCenterCriteria_.id)));
            }
            if (criteria.getEducationalCenterGradeId() != null) {
                specification = specification.and(buildSpecification(criteria.getEducationalCenterGradeId(),
                    root -> root.join(EducationalCenterGradeScore_.educationalCenterGrade, JoinType.LEFT).get(EducationalCenterGrade_.id)));
            }
        }
        return specification;
    }
}
