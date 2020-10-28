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

import com.marineindustryproj.domain.MonitorLearningProcessGrade;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.MonitorLearningProcessGradeRepository;
import com.marineindustryproj.service.dto.MonitorLearningProcessGradeCriteria;
import com.marineindustryproj.service.dto.MonitorLearningProcessGradeDTO;
import com.marineindustryproj.service.mapper.MonitorLearningProcessGradeMapper;

/**
 * Service for executing complex queries for MonitorLearningProcessGrade entities in the database.
 * The main input is a {@link MonitorLearningProcessGradeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MonitorLearningProcessGradeDTO} or a {@link Page} of {@link MonitorLearningProcessGradeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MonitorLearningProcessGradeQueryService extends QueryService<MonitorLearningProcessGrade> {

    private final Logger log = LoggerFactory.getLogger(MonitorLearningProcessGradeQueryService.class);

    private final MonitorLearningProcessGradeRepository monitorLearningProcessGradeRepository;

    private final MonitorLearningProcessGradeMapper monitorLearningProcessGradeMapper;

    public MonitorLearningProcessGradeQueryService(MonitorLearningProcessGradeRepository monitorLearningProcessGradeRepository, MonitorLearningProcessGradeMapper monitorLearningProcessGradeMapper) {
        this.monitorLearningProcessGradeRepository = monitorLearningProcessGradeRepository;
        this.monitorLearningProcessGradeMapper = monitorLearningProcessGradeMapper;
    }

    /**
     * Return a {@link List} of {@link MonitorLearningProcessGradeDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MonitorLearningProcessGradeDTO> findByCriteria(MonitorLearningProcessGradeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MonitorLearningProcessGrade> specification = createSpecification(criteria);
        return monitorLearningProcessGradeMapper.toDto(monitorLearningProcessGradeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MonitorLearningProcessGradeDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MonitorLearningProcessGradeDTO> findByCriteria(MonitorLearningProcessGradeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MonitorLearningProcessGrade> specification = createSpecification(criteria);
        return monitorLearningProcessGradeRepository.findAll(specification, page)
            .map(monitorLearningProcessGradeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MonitorLearningProcessGradeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MonitorLearningProcessGrade> specification = createSpecification(criteria);
        return monitorLearningProcessGradeRepository.count(specification);
    }

    /**
     * Function to convert MonitorLearningProcessGradeCriteria to a {@link Specification}
     */
    private Specification<MonitorLearningProcessGrade> createSpecification(MonitorLearningProcessGradeCriteria criteria) {
        Specification<MonitorLearningProcessGrade> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MonitorLearningProcessGrade_.id));
            }
            if (criteria.getFirstNumber() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFirstNumber(), MonitorLearningProcessGrade_.firstNumber));
            }
            if (criteria.getSecondNumber() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSecondNumber(), MonitorLearningProcessGrade_.secondNumber));
            }
            if (criteria.getThirdNumber() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getThirdNumber(), MonitorLearningProcessGrade_.thirdNumber));
            }
            if (criteria.getResult() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getResult(), MonitorLearningProcessGrade_.result));
            }
            if (criteria.getGoal() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGoal(), MonitorLearningProcessGrade_.goal));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), MonitorLearningProcessGrade_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), MonitorLearningProcessGrade_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), MonitorLearningProcessGrade_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), MonitorLearningProcessGrade_.modifyDate));
            }
            if (criteria.getGuid() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGuid(), MonitorLearningProcessGrade_.guid));
            }
            if (criteria.getMonitorLearningProcessLevelId() != null) {
                specification = specification.and(buildSpecification(criteria.getMonitorLearningProcessLevelId(),
                    root -> root.join(MonitorLearningProcessGrade_.monitorLearningProcessLevel, JoinType.LEFT).get(MonitorLearningProcessLevel_.id)));
            }
            if (criteria.getMonitorLearningProcessId() != null) {
                specification = specification.and(buildSpecification(criteria.getMonitorLearningProcessId(),
                    root -> root.join(MonitorLearningProcessGrade_.monitorLearningProcess, JoinType.LEFT).get(MonitorLearningProcess_.id)));
            }
            if (criteria.getMonitorProcessDurationId() != null) {
                specification = specification.and(buildSpecification(criteria.getMonitorProcessDurationId(),
                    root -> root.join(MonitorLearningProcessGrade_.monitorProcessDuration, JoinType.LEFT).get(MonitorProcessDuration_.id)));
            }
        }
        return specification;
    }
}
