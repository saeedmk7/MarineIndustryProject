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

import com.marineindustryproj.domain.MonitorLearningProcessLevel;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.MonitorLearningProcessLevelRepository;
import com.marineindustryproj.service.dto.MonitorLearningProcessLevelCriteria;
import com.marineindustryproj.service.dto.MonitorLearningProcessLevelDTO;
import com.marineindustryproj.service.mapper.MonitorLearningProcessLevelMapper;

/**
 * Service for executing complex queries for MonitorLearningProcessLevel entities in the database.
 * The main input is a {@link MonitorLearningProcessLevelCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MonitorLearningProcessLevelDTO} or a {@link Page} of {@link MonitorLearningProcessLevelDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MonitorLearningProcessLevelQueryService extends QueryService<MonitorLearningProcessLevel> {

    private final Logger log = LoggerFactory.getLogger(MonitorLearningProcessLevelQueryService.class);

    private final MonitorLearningProcessLevelRepository monitorLearningProcessLevelRepository;

    private final MonitorLearningProcessLevelMapper monitorLearningProcessLevelMapper;

    public MonitorLearningProcessLevelQueryService(MonitorLearningProcessLevelRepository monitorLearningProcessLevelRepository, MonitorLearningProcessLevelMapper monitorLearningProcessLevelMapper) {
        this.monitorLearningProcessLevelRepository = monitorLearningProcessLevelRepository;
        this.monitorLearningProcessLevelMapper = monitorLearningProcessLevelMapper;
    }

    /**
     * Return a {@link List} of {@link MonitorLearningProcessLevelDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MonitorLearningProcessLevelDTO> findByCriteria(MonitorLearningProcessLevelCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MonitorLearningProcessLevel> specification = createSpecification(criteria);
        return monitorLearningProcessLevelMapper.toDto(monitorLearningProcessLevelRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MonitorLearningProcessLevelDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MonitorLearningProcessLevelDTO> findByCriteria(MonitorLearningProcessLevelCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MonitorLearningProcessLevel> specification = createSpecification(criteria);
        return monitorLearningProcessLevelRepository.findAll(specification, page)
            .map(monitorLearningProcessLevelMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MonitorLearningProcessLevelCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MonitorLearningProcessLevel> specification = createSpecification(criteria);
        return monitorLearningProcessLevelRepository.count(specification);
    }

    /**
     * Function to convert MonitorLearningProcessLevelCriteria to a {@link Specification}
     */
    private Specification<MonitorLearningProcessLevel> createSpecification(MonitorLearningProcessLevelCriteria criteria) {
        Specification<MonitorLearningProcessLevel> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MonitorLearningProcessLevel_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), MonitorLearningProcessLevel_.title));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), MonitorLearningProcessLevel_.code));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), MonitorLearningProcessLevel_.description));
            }
            if (criteria.getGoal() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getGoal(), MonitorLearningProcessLevel_.goal));
            }
            if (criteria.getFormula() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFormula(), MonitorLearningProcessLevel_.formula));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), MonitorLearningProcessLevel_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), MonitorLearningProcessLevel_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), MonitorLearningProcessLevel_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), MonitorLearningProcessLevel_.modifyDate));
            }
            if (criteria.getGuid() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGuid(), MonitorLearningProcessLevel_.guid));
            }
            if (criteria.getMonitorLearningProcessGradeId() != null) {
                specification = specification.and(buildSpecification(criteria.getMonitorLearningProcessGradeId(),
                    root -> root.join(MonitorLearningProcessLevel_.monitorLearningProcessGrades, JoinType.LEFT).get(MonitorLearningProcessGrade_.id)));
            }
        }
        return specification;
    }
}
