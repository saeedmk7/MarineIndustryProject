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

import com.marineindustryproj.domain.MonitorLearningProcess;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.MonitorLearningProcessRepository;
import com.marineindustryproj.service.dto.MonitorLearningProcessCriteria;
import com.marineindustryproj.service.dto.MonitorLearningProcessDTO;
import com.marineindustryproj.service.mapper.MonitorLearningProcessMapper;

/**
 * Service for executing complex queries for MonitorLearningProcess entities in the database.
 * The main input is a {@link MonitorLearningProcessCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MonitorLearningProcessDTO} or a {@link Page} of {@link MonitorLearningProcessDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MonitorLearningProcessQueryService extends QueryService<MonitorLearningProcess> {

    private final Logger log = LoggerFactory.getLogger(MonitorLearningProcessQueryService.class);

    private final MonitorLearningProcessRepository monitorLearningProcessRepository;

    private final MonitorLearningProcessMapper monitorLearningProcessMapper;

    public MonitorLearningProcessQueryService(MonitorLearningProcessRepository monitorLearningProcessRepository, MonitorLearningProcessMapper monitorLearningProcessMapper) {
        this.monitorLearningProcessRepository = monitorLearningProcessRepository;
        this.monitorLearningProcessMapper = monitorLearningProcessMapper;
    }

    /**
     * Return a {@link List} of {@link MonitorLearningProcessDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MonitorLearningProcessDTO> findByCriteria(MonitorLearningProcessCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MonitorLearningProcess> specification = createSpecification(criteria);
        return monitorLearningProcessMapper.toDto(monitorLearningProcessRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MonitorLearningProcessDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MonitorLearningProcessDTO> findByCriteria(MonitorLearningProcessCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MonitorLearningProcess> specification = createSpecification(criteria);
        return monitorLearningProcessRepository.findAll(specification, page)
            .map(monitorLearningProcessMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MonitorLearningProcessCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MonitorLearningProcess> specification = createSpecification(criteria);
        return monitorLearningProcessRepository.count(specification);
    }

    /**
     * Function to convert MonitorLearningProcessCriteria to a {@link Specification}
     */
    private Specification<MonitorLearningProcess> createSpecification(MonitorLearningProcessCriteria criteria) {
        Specification<MonitorLearningProcess> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MonitorLearningProcess_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), MonitorLearningProcess_.title));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), MonitorLearningProcess_.code));
            }
            if (criteria.getProcessDate() != null) {
                specification = specification.and(buildStringSpecification(criteria.getProcessDate(), MonitorLearningProcess_.processDate));
            }
            if (criteria.getClassification() != null) {
                specification = specification.and(buildStringSpecification(criteria.getClassification(), MonitorLearningProcess_.classification));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), MonitorLearningProcess_.description));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), MonitorLearningProcess_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), MonitorLearningProcess_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), MonitorLearningProcess_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), MonitorLearningProcess_.modifyDate));
            }
            if (criteria.getGuid() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGuid(), MonitorLearningProcess_.guid));
            }
            if (criteria.getMonitorLearningProcessGradeId() != null) {
                specification = specification.and(buildSpecification(criteria.getMonitorLearningProcessGradeId(),
                    root -> root.join(MonitorLearningProcess_.monitorLearningProcessGrades, JoinType.LEFT).get(MonitorLearningProcessGrade_.id)));
            }
            if (criteria.getDocumentId() != null) {
                specification = specification.and(buildSpecification(criteria.getDocumentId(),
                    root -> root.join(MonitorLearningProcess_.documents, JoinType.LEFT).get(Document_.id)));
            }
            if (criteria.getMonitorProcessDurationId() != null) {
                specification = specification.and(buildSpecification(criteria.getMonitorProcessDurationId(),
                    root -> root.join(MonitorLearningProcess_.monitorProcessDuration, JoinType.LEFT).get(MonitorProcessDuration_.id)));
            }
        }
        return specification;
    }
}
