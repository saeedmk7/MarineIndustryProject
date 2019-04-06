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

import com.marineindustryproj.domain.WorkUnit;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.WorkUnitRepository;
import com.marineindustryproj.service.dto.WorkUnitCriteria;
import com.marineindustryproj.service.dto.WorkUnitDTO;
import com.marineindustryproj.service.mapper.WorkUnitMapper;

/**
 * Service for executing complex queries for WorkUnit entities in the database.
 * The main input is a {@link WorkUnitCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link WorkUnitDTO} or a {@link Page} of {@link WorkUnitDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class WorkUnitQueryService extends QueryService<WorkUnit> {

    private final Logger log = LoggerFactory.getLogger(WorkUnitQueryService.class);

    private final WorkUnitRepository workUnitRepository;

    private final WorkUnitMapper workUnitMapper;

    public WorkUnitQueryService(WorkUnitRepository workUnitRepository, WorkUnitMapper workUnitMapper) {
        this.workUnitRepository = workUnitRepository;
        this.workUnitMapper = workUnitMapper;
    }

    /**
     * Return a {@link List} of {@link WorkUnitDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<WorkUnitDTO> findByCriteria(WorkUnitCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<WorkUnit> specification = createSpecification(criteria);
        return workUnitMapper.toDto(workUnitRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link WorkUnitDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<WorkUnitDTO> findByCriteria(WorkUnitCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<WorkUnit> specification = createSpecification(criteria);
        return workUnitRepository.findAll(specification, page)
            .map(workUnitMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(WorkUnitCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<WorkUnit> specification = createSpecification(criteria);
        return workUnitRepository.count(specification);
    }

    /**
     * Function to convert WorkUnitCriteria to a {@link Specification}
     */
    private Specification<WorkUnit> createSpecification(WorkUnitCriteria criteria) {
        Specification<WorkUnit> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), WorkUnit_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCode(), WorkUnit_.code));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), WorkUnit_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), WorkUnit_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), WorkUnit_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), WorkUnit_.modifyDate));
            }
            if (criteria.getWorkIndustryId() != null) {
                specification = specification.and(buildSpecification(criteria.getWorkIndustryId(),
                    root -> root.join(WorkUnit_.workIndustry, JoinType.LEFT).get(WorkIndustry_.id)));
            }
            if (criteria.getWorkGroupId() != null) {
                specification = specification.and(buildSpecification(criteria.getWorkGroupId(),
                    root -> root.join(WorkUnit_.workGroup, JoinType.LEFT).get(WorkGroup_.id)));
            }
        }
        return specification;
    }
}
