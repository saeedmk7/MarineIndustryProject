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

import com.marineindustryproj.domain.EvaluateCriteriaData;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.EvaluateCriteriaDataRepository;
import com.marineindustryproj.service.dto.EvaluateCriteriaDataCriteria;
import com.marineindustryproj.service.dto.EvaluateCriteriaDataDTO;
import com.marineindustryproj.service.mapper.EvaluateCriteriaDataMapper;

/**
 * Service for executing complex queries for EvaluateCriteriaData entities in the database.
 * The main input is a {@link EvaluateCriteriaDataCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link EvaluateCriteriaDataDTO} or a {@link Page} of {@link EvaluateCriteriaDataDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class EvaluateCriteriaDataQueryService extends QueryService<EvaluateCriteriaData> {

    private final Logger log = LoggerFactory.getLogger(EvaluateCriteriaDataQueryService.class);

    private final EvaluateCriteriaDataRepository evaluateCriteriaDataRepository;

    private final EvaluateCriteriaDataMapper evaluateCriteriaDataMapper;

    public EvaluateCriteriaDataQueryService(EvaluateCriteriaDataRepository evaluateCriteriaDataRepository, EvaluateCriteriaDataMapper evaluateCriteriaDataMapper) {
        this.evaluateCriteriaDataRepository = evaluateCriteriaDataRepository;
        this.evaluateCriteriaDataMapper = evaluateCriteriaDataMapper;
    }

    /**
     * Return a {@link List} of {@link EvaluateCriteriaDataDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<EvaluateCriteriaDataDTO> findByCriteria(EvaluateCriteriaDataCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<EvaluateCriteriaData> specification = createSpecification(criteria);
        return evaluateCriteriaDataMapper.toDto(evaluateCriteriaDataRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link EvaluateCriteriaDataDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<EvaluateCriteriaDataDTO> findByCriteria(EvaluateCriteriaDataCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<EvaluateCriteriaData> specification = createSpecification(criteria);
        return evaluateCriteriaDataRepository.findAll(specification, page)
            .map(evaluateCriteriaDataMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(EvaluateCriteriaDataCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<EvaluateCriteriaData> specification = createSpecification(criteria);
        return evaluateCriteriaDataRepository.count(specification);
    }

    /**
     * Function to convert EvaluateCriteriaDataCriteria to a {@link Specification}
     */
    private Specification<EvaluateCriteriaData> createSpecification(EvaluateCriteriaDataCriteria criteria) {
        Specification<EvaluateCriteriaData> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), EvaluateCriteriaData_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), EvaluateCriteriaData_.title));
            }
            if (criteria.getFirstNumber() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFirstNumber(), EvaluateCriteriaData_.firstNumber));
            }
            if (criteria.getSecondNumber() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSecondNumber(), EvaluateCriteriaData_.secondNumber));
            }
            if (criteria.getThirdNumber() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getThirdNumber(), EvaluateCriteriaData_.thirdNumber));
            }
            if (criteria.getMeasureCalc() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMeasureCalc(), EvaluateCriteriaData_.measureCalc));
            }
            if (criteria.getMeasureCalcPercent() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMeasureCalcPercent(), EvaluateCriteriaData_.measureCalcPercent));
            }
            if (criteria.getResult() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getResult(), EvaluateCriteriaData_.result));
            }
            if (criteria.getResultPercent() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getResultPercent(), EvaluateCriteriaData_.resultPercent));
            }
            if (criteria.getYear() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getYear(), EvaluateCriteriaData_.year));
            }
            if (criteria.getMonth() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMonth(), EvaluateCriteriaData_.month));
            }
            if (criteria.getReportTime() != null) {
                specification = specification.and(buildStringSpecification(criteria.getReportTime(), EvaluateCriteriaData_.reportTime));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), EvaluateCriteriaData_.description));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), EvaluateCriteriaData_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), EvaluateCriteriaData_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), EvaluateCriteriaData_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), EvaluateCriteriaData_.modifyDate));
            }
            if (criteria.getGuid() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGuid(), EvaluateCriteriaData_.guid));
            }
            if (criteria.getQualityGoal() != null) {
                specification = specification.and(buildStringSpecification(criteria.getQualityGoal(), EvaluateCriteriaData_.qualityGoal));
            }
            if (criteria.getDocumentId() != null) {
                specification = specification.and(buildSpecification(criteria.getDocumentId(),
                    root -> root.join(EvaluateCriteriaData_.documents, JoinType.LEFT).get(Document_.id)));
            }
            if (criteria.getEvaluateCriteriaTrainingId() != null) {
                specification = specification.and(buildSpecification(criteria.getEvaluateCriteriaTrainingId(),
                    root -> root.join(EvaluateCriteriaData_.evaluateCriteriaTraining, JoinType.LEFT).get(EvaluateCriteriaTraining_.id)));
            }
            if (criteria.getOrganizationChartId() != null) {
                specification = specification.and(buildSpecification(criteria.getOrganizationChartId(),
                    root -> root.join(EvaluateCriteriaData_.organizationChart, JoinType.LEFT).get(OrganizationChart_.id)));
            }
        }
        return specification;
    }
}
