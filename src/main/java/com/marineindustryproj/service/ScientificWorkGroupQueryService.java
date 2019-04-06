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

import com.marineindustryproj.domain.ScientificWorkGroup;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.ScientificWorkGroupRepository;
import com.marineindustryproj.service.dto.ScientificWorkGroupCriteria;
import com.marineindustryproj.service.dto.ScientificWorkGroupDTO;
import com.marineindustryproj.service.mapper.ScientificWorkGroupMapper;

/**
 * Service for executing complex queries for ScientificWorkGroup entities in the database.
 * The main input is a {@link ScientificWorkGroupCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ScientificWorkGroupDTO} or a {@link Page} of {@link ScientificWorkGroupDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ScientificWorkGroupQueryService extends QueryService<ScientificWorkGroup> {

    private final Logger log = LoggerFactory.getLogger(ScientificWorkGroupQueryService.class);

    private final ScientificWorkGroupRepository scientificWorkGroupRepository;

    private final ScientificWorkGroupMapper scientificWorkGroupMapper;

    public ScientificWorkGroupQueryService(ScientificWorkGroupRepository scientificWorkGroupRepository, ScientificWorkGroupMapper scientificWorkGroupMapper) {
        this.scientificWorkGroupRepository = scientificWorkGroupRepository;
        this.scientificWorkGroupMapper = scientificWorkGroupMapper;
    }

    /**
     * Return a {@link List} of {@link ScientificWorkGroupDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ScientificWorkGroupDTO> findByCriteria(ScientificWorkGroupCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ScientificWorkGroup> specification = createSpecification(criteria);
        return scientificWorkGroupMapper.toDto(scientificWorkGroupRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ScientificWorkGroupDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ScientificWorkGroupDTO> findByCriteria(ScientificWorkGroupCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ScientificWorkGroup> specification = createSpecification(criteria);
        return scientificWorkGroupRepository.findAll(specification, page)
            .map(scientificWorkGroupMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ScientificWorkGroupCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ScientificWorkGroup> specification = createSpecification(criteria);
        return scientificWorkGroupRepository.count(specification);
    }

    /**
     * Function to convert ScientificWorkGroupCriteria to a {@link Specification}
     */
    private Specification<ScientificWorkGroup> createSpecification(ScientificWorkGroupCriteria criteria) {
        Specification<ScientificWorkGroup> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ScientificWorkGroup_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), ScientificWorkGroup_.title));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), ScientificWorkGroup_.code));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), ScientificWorkGroup_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), ScientificWorkGroup_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), ScientificWorkGroup_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), ScientificWorkGroup_.modifyDate));
            }
            if (criteria.getJobId() != null) {
                specification = specification.and(buildSpecification(criteria.getJobId(),
                    root -> root.join(ScientificWorkGroup_.jobs, JoinType.LEFT).get(Job_.id)));
            }
            if (criteria.getNiazsanjiGroupId() != null) {
                specification = specification.and(buildSpecification(criteria.getNiazsanjiGroupId(),
                    root -> root.join(ScientificWorkGroup_.niazsanjiGroups, JoinType.LEFT).get(NiazsanjiGroup_.id)));
            }
            if (criteria.getPersonId() != null) {
                specification = specification.and(buildSpecification(criteria.getPersonId(),
                    root -> root.join(ScientificWorkGroup_.people, JoinType.LEFT).get(Person_.id)));
            }
            if (criteria.getEducationalModuleId() != null) {
                specification = specification.and(buildSpecification(criteria.getEducationalModuleId(),
                    root -> root.join(ScientificWorkGroup_.educationalModules, JoinType.LEFT).get(EducationalModule_.id)));
            }
            if (criteria.getRequestEducationalModuleId() != null) {
                specification = specification.and(buildSpecification(criteria.getRequestEducationalModuleId(),
                    root -> root.join(ScientificWorkGroup_.requestEducationalModules, JoinType.LEFT).get(RequestEducationalModule_.id)));
            }
        }
        return specification;
    }
}
