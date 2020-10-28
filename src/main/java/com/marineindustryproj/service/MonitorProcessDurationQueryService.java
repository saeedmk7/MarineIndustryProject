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

import com.marineindustryproj.domain.MonitorProcessDuration;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.MonitorProcessDurationRepository;
import com.marineindustryproj.service.dto.MonitorProcessDurationCriteria;
import com.marineindustryproj.service.dto.MonitorProcessDurationDTO;
import com.marineindustryproj.service.mapper.MonitorProcessDurationMapper;

/**
 * Service for executing complex queries for MonitorProcessDuration entities in the database.
 * The main input is a {@link MonitorProcessDurationCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MonitorProcessDurationDTO} or a {@link Page} of {@link MonitorProcessDurationDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MonitorProcessDurationQueryService extends QueryService<MonitorProcessDuration> {

    private final Logger log = LoggerFactory.getLogger(MonitorProcessDurationQueryService.class);

    private final MonitorProcessDurationRepository monitorProcessDurationRepository;

    private final MonitorProcessDurationMapper monitorProcessDurationMapper;

    public MonitorProcessDurationQueryService(MonitorProcessDurationRepository monitorProcessDurationRepository, MonitorProcessDurationMapper monitorProcessDurationMapper) {
        this.monitorProcessDurationRepository = monitorProcessDurationRepository;
        this.monitorProcessDurationMapper = monitorProcessDurationMapper;
    }

    /**
     * Return a {@link List} of {@link MonitorProcessDurationDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MonitorProcessDurationDTO> findByCriteria(MonitorProcessDurationCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MonitorProcessDuration> specification = createSpecification(criteria);
        return monitorProcessDurationMapper.toDto(monitorProcessDurationRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MonitorProcessDurationDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MonitorProcessDurationDTO> findByCriteria(MonitorProcessDurationCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MonitorProcessDuration> specification = createSpecification(criteria);
        return monitorProcessDurationRepository.findAll(specification, page)
            .map(monitorProcessDurationMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MonitorProcessDurationCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MonitorProcessDuration> specification = createSpecification(criteria);
        return monitorProcessDurationRepository.count(specification);
    }

    /**
     * Function to convert MonitorProcessDurationCriteria to a {@link Specification}
     */
    private Specification<MonitorProcessDuration> createSpecification(MonitorProcessDurationCriteria criteria) {
        Specification<MonitorProcessDuration> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MonitorProcessDuration_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), MonitorProcessDuration_.title));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), MonitorProcessDuration_.code));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), MonitorProcessDuration_.description));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), MonitorProcessDuration_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), MonitorProcessDuration_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), MonitorProcessDuration_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), MonitorProcessDuration_.modifyDate));
            }
            if (criteria.getGuid() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGuid(), MonitorProcessDuration_.guid));
            }
            if (criteria.getMonitorLearningProcessId() != null) {
                specification = specification.and(buildSpecification(criteria.getMonitorLearningProcessId(),
                    root -> root.join(MonitorProcessDuration_.monitorLearningProcesses, JoinType.LEFT).get(MonitorLearningProcess_.id)));
            }
            if (criteria.getMonitorLearningProcessGradeId() != null) {
                specification = specification.and(buildSpecification(criteria.getMonitorLearningProcessGradeId(),
                    root -> root.join(MonitorProcessDuration_.monitorLearningProcessGrades, JoinType.LEFT).get(MonitorLearningProcessGrade_.id)));
            }
        }
        return specification;
    }
}
