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

import com.marineindustryproj.domain.EvaluatorOpinion;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.EvaluatorOpinionRepository;
import com.marineindustryproj.service.dto.EvaluatorOpinionCriteria;
import com.marineindustryproj.service.dto.EvaluatorOpinionDTO;
import com.marineindustryproj.service.mapper.EvaluatorOpinionMapper;

/**
 * Service for executing complex queries for EvaluatorOpinion entities in the database.
 * The main input is a {@link EvaluatorOpinionCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link EvaluatorOpinionDTO} or a {@link Page} of {@link EvaluatorOpinionDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class EvaluatorOpinionQueryService extends QueryService<EvaluatorOpinion> {

    private final Logger log = LoggerFactory.getLogger(EvaluatorOpinionQueryService.class);

    private final EvaluatorOpinionRepository evaluatorOpinionRepository;

    private final EvaluatorOpinionMapper evaluatorOpinionMapper;

    public EvaluatorOpinionQueryService(EvaluatorOpinionRepository evaluatorOpinionRepository, EvaluatorOpinionMapper evaluatorOpinionMapper) {
        this.evaluatorOpinionRepository = evaluatorOpinionRepository;
        this.evaluatorOpinionMapper = evaluatorOpinionMapper;
    }

    /**
     * Return a {@link List} of {@link EvaluatorOpinionDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<EvaluatorOpinionDTO> findByCriteria(EvaluatorOpinionCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<EvaluatorOpinion> specification = createSpecification(criteria);
        return evaluatorOpinionMapper.toDto(evaluatorOpinionRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link EvaluatorOpinionDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<EvaluatorOpinionDTO> findByCriteria(EvaluatorOpinionCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<EvaluatorOpinion> specification = createSpecification(criteria);
        return evaluatorOpinionRepository.findAll(specification, page)
            .map(evaluatorOpinionMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(EvaluatorOpinionCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<EvaluatorOpinion> specification = createSpecification(criteria);
        return evaluatorOpinionRepository.count(specification);
    }

    /**
     * Function to convert EvaluatorOpinionCriteria to a {@link Specification}
     */
    private Specification<EvaluatorOpinion> createSpecification(EvaluatorOpinionCriteria criteria) {
        Specification<EvaluatorOpinion> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), EvaluatorOpinion_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), EvaluatorOpinion_.title));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), EvaluatorOpinion_.description));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), EvaluatorOpinion_.code));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), EvaluatorOpinion_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), EvaluatorOpinion_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), EvaluatorOpinion_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), EvaluatorOpinion_.modifyDate));
            }
            if (criteria.getEducationalCenterGradeId() != null) {
                specification = specification.and(buildSpecification(criteria.getEducationalCenterGradeId(),
                    root -> root.join(EvaluatorOpinion_.educationalCenterGrades, JoinType.LEFT).get(EducationalCenterGrade_.id)));
            }
        }
        return specification;
    }
}
