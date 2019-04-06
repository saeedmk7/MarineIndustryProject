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

import com.marineindustryproj.domain.WorkIndustry;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.WorkIndustryRepository;
import com.marineindustryproj.service.dto.WorkIndustryCriteria;
import com.marineindustryproj.service.dto.WorkIndustryDTO;
import com.marineindustryproj.service.mapper.WorkIndustryMapper;

/**
 * Service for executing complex queries for WorkIndustry entities in the database.
 * The main input is a {@link WorkIndustryCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link WorkIndustryDTO} or a {@link Page} of {@link WorkIndustryDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class WorkIndustryQueryService extends QueryService<WorkIndustry> {

    private final Logger log = LoggerFactory.getLogger(WorkIndustryQueryService.class);

    private final WorkIndustryRepository workIndustryRepository;

    private final WorkIndustryMapper workIndustryMapper;

    public WorkIndustryQueryService(WorkIndustryRepository workIndustryRepository, WorkIndustryMapper workIndustryMapper) {
        this.workIndustryRepository = workIndustryRepository;
        this.workIndustryMapper = workIndustryMapper;
    }

    /**
     * Return a {@link List} of {@link WorkIndustryDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<WorkIndustryDTO> findByCriteria(WorkIndustryCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<WorkIndustry> specification = createSpecification(criteria);
        return workIndustryMapper.toDto(workIndustryRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link WorkIndustryDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<WorkIndustryDTO> findByCriteria(WorkIndustryCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<WorkIndustry> specification = createSpecification(criteria);
        return workIndustryRepository.findAll(specification, page)
            .map(workIndustryMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(WorkIndustryCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<WorkIndustry> specification = createSpecification(criteria);
        return workIndustryRepository.count(specification);
    }

    /**
     * Function to convert WorkIndustryCriteria to a {@link Specification}
     */
    private Specification<WorkIndustry> createSpecification(WorkIndustryCriteria criteria) {
        Specification<WorkIndustry> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), WorkIndustry_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), WorkIndustry_.title));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), WorkIndustry_.code));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), WorkIndustry_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), WorkIndustry_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), WorkIndustry_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), WorkIndustry_.modifyDate));
            }
            if (criteria.getPersonId() != null) {
                specification = specification.and(buildSpecification(criteria.getPersonId(),
                    root -> root.join(WorkIndustry_.people, JoinType.LEFT).get(Person_.id)));
            }
            if (criteria.getWorkUnitId() != null) {
                specification = specification.and(buildSpecification(criteria.getWorkUnitId(),
                    root -> root.join(WorkIndustry_.workUnits, JoinType.LEFT).get(WorkUnit_.id)));
            }
        }
        return specification;
    }
}
