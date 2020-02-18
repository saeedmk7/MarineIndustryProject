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

import com.marineindustryproj.domain.EvaluateCriteriaTraining;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.EvaluateCriteriaTrainingRepository;
import com.marineindustryproj.service.dto.EvaluateCriteriaTrainingCriteria;
import com.marineindustryproj.service.dto.EvaluateCriteriaTrainingDTO;
import com.marineindustryproj.service.mapper.EvaluateCriteriaTrainingMapper;

/**
 * Service for executing complex queries for EvaluateCriteriaTraining entities in the database.
 * The main input is a {@link EvaluateCriteriaTrainingCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link EvaluateCriteriaTrainingDTO} or a {@link Page} of {@link EvaluateCriteriaTrainingDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class EvaluateCriteriaTrainingQueryService extends QueryService<EvaluateCriteriaTraining> {

    private final Logger log = LoggerFactory.getLogger(EvaluateCriteriaTrainingQueryService.class);

    private final EvaluateCriteriaTrainingRepository evaluateCriteriaTrainingRepository;

    private final EvaluateCriteriaTrainingMapper evaluateCriteriaTrainingMapper;

    public EvaluateCriteriaTrainingQueryService(EvaluateCriteriaTrainingRepository evaluateCriteriaTrainingRepository, EvaluateCriteriaTrainingMapper evaluateCriteriaTrainingMapper) {
        this.evaluateCriteriaTrainingRepository = evaluateCriteriaTrainingRepository;
        this.evaluateCriteriaTrainingMapper = evaluateCriteriaTrainingMapper;
    }

    /**
     * Return a {@link List} of {@link EvaluateCriteriaTrainingDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<EvaluateCriteriaTrainingDTO> findByCriteria(EvaluateCriteriaTrainingCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<EvaluateCriteriaTraining> specification = createSpecification(criteria);
        return evaluateCriteriaTrainingMapper.toDto(evaluateCriteriaTrainingRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link EvaluateCriteriaTrainingDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<EvaluateCriteriaTrainingDTO> findByCriteria(EvaluateCriteriaTrainingCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<EvaluateCriteriaTraining> specification = createSpecification(criteria);
        return evaluateCriteriaTrainingRepository.findAll(specification, page)
            .map(evaluateCriteriaTrainingMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(EvaluateCriteriaTrainingCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<EvaluateCriteriaTraining> specification = createSpecification(criteria);
        return evaluateCriteriaTrainingRepository.count(specification);
    }

    /**
     * Function to convert EvaluateCriteriaTrainingCriteria to a {@link Specification}
     */
    private Specification<EvaluateCriteriaTraining> createSpecification(EvaluateCriteriaTrainingCriteria criteria) {
        Specification<EvaluateCriteriaTraining> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), EvaluateCriteriaTraining_.id));
            }
            if (criteria.getProcessTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getProcessTitle(), EvaluateCriteriaTraining_.processTitle));
            }
            if (criteria.getProcessWeight() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getProcessWeight(), EvaluateCriteriaTraining_.processWeight));
            }
            if (criteria.getActivityDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getActivityDescription(), EvaluateCriteriaTraining_.activityDescription));
            }
            if (criteria.getCriteriaWeight() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCriteriaWeight(), EvaluateCriteriaTraining_.criteriaWeight));
            }
            if (criteria.getMeasureDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMeasureDescription(), EvaluateCriteriaTraining_.measureDescription));
            }
            if (criteria.getFirstNumber() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFirstNumber(), EvaluateCriteriaTraining_.firstNumber));
            }
            if (criteria.getSecondNumber() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSecondNumber(), EvaluateCriteriaTraining_.secondNumber));
            }
            if (criteria.getThirdNumber() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getThirdNumber(), EvaluateCriteriaTraining_.thirdNumber));
            }
            if (criteria.getMeasureCalc() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMeasureCalc(), EvaluateCriteriaTraining_.measureCalc));
            }
            if (criteria.getMeasureCalcPercent() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMeasureCalcPercent(), EvaluateCriteriaTraining_.measureCalcPercent));
            }
            if (criteria.getResult() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getResult(), EvaluateCriteriaTraining_.result));
            }
            if (criteria.getResultPercent() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getResultPercent(), EvaluateCriteriaTraining_.resultPercent));
            }
            if (criteria.getYear() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getYear(), EvaluateCriteriaTraining_.year));
            }
            if (criteria.getMonth() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMonth(), EvaluateCriteriaTraining_.month));
            }
            if (criteria.getReportTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getReportTime(), EvaluateCriteriaTraining_.reportTime));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), EvaluateCriteriaTraining_.description));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), EvaluateCriteriaTraining_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), EvaluateCriteriaTraining_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), EvaluateCriteriaTraining_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), EvaluateCriteriaTraining_.modifyDate));
            }
            if (criteria.getGuid() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGuid(), EvaluateCriteriaTraining_.guid));
            }
            if (criteria.getEvaluateCriteriaDataId() != null) {
                specification = specification.and(buildSpecification(criteria.getEvaluateCriteriaDataId(),
                    root -> root.join(EvaluateCriteriaTraining_.evaluateCriteriaData, JoinType.LEFT).get(EvaluateCriteriaData_.id)));
            }
            if (criteria.getDocumentId() != null) {
                specification = specification.and(buildSpecification(criteria.getDocumentId(),
                    root -> root.join(EvaluateCriteriaTraining_.documents, JoinType.LEFT).get(Document_.id)));
            }
            if (criteria.getOrganizationChartId() != null) {
                specification = specification.and(buildSpecification(criteria.getOrganizationChartId(),
                    root -> root.join(EvaluateCriteriaTraining_.organizationChart, JoinType.LEFT).get(OrganizationChart_.id)));
            }
        }
        return specification;
    }
}
