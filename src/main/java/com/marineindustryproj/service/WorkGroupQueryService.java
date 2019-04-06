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

import com.marineindustryproj.domain.WorkGroup;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.WorkGroupRepository;
import com.marineindustryproj.service.dto.WorkGroupCriteria;
import com.marineindustryproj.service.dto.WorkGroupDTO;
import com.marineindustryproj.service.mapper.WorkGroupMapper;

/**
 * Service for executing complex queries for WorkGroup entities in the database.
 * The main input is a {@link WorkGroupCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link WorkGroupDTO} or a {@link Page} of {@link WorkGroupDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class WorkGroupQueryService extends QueryService<WorkGroup> {

    private final Logger log = LoggerFactory.getLogger(WorkGroupQueryService.class);

    private final WorkGroupRepository workGroupRepository;

    private final WorkGroupMapper workGroupMapper;

    public WorkGroupQueryService(WorkGroupRepository workGroupRepository, WorkGroupMapper workGroupMapper) {
        this.workGroupRepository = workGroupRepository;
        this.workGroupMapper = workGroupMapper;
    }

    /**
     * Return a {@link List} of {@link WorkGroupDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<WorkGroupDTO> findByCriteria(WorkGroupCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<WorkGroup> specification = createSpecification(criteria);
        return workGroupMapper.toDto(workGroupRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link WorkGroupDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<WorkGroupDTO> findByCriteria(WorkGroupCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<WorkGroup> specification = createSpecification(criteria);
        return workGroupRepository.findAll(specification, page)
            .map(workGroupMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(WorkGroupCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<WorkGroup> specification = createSpecification(criteria);
        return workGroupRepository.count(specification);
    }

    /**
     * Function to convert WorkGroupCriteria to a {@link Specification}
     */
    private Specification<WorkGroup> createSpecification(WorkGroupCriteria criteria) {
        Specification<WorkGroup> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), WorkGroup_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), WorkGroup_.title));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), WorkGroup_.code));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), WorkGroup_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), WorkGroup_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), WorkGroup_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), WorkGroup_.modifyDate));
            }
            if (criteria.getPersonId() != null) {
                specification = specification.and(buildSpecification(criteria.getPersonId(),
                    root -> root.join(WorkGroup_.people, JoinType.LEFT).get(Person_.id)));
            }
            if (criteria.getWorkUnitId() != null) {
                specification = specification.and(buildSpecification(criteria.getWorkUnitId(),
                    root -> root.join(WorkGroup_.workUnits, JoinType.LEFT).get(WorkUnit_.id)));
            }
        }
        return specification;
    }
}
