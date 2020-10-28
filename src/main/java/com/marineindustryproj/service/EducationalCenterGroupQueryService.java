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

import com.marineindustryproj.domain.EducationalCenterGroup;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.EducationalCenterGroupRepository;
import com.marineindustryproj.service.dto.EducationalCenterGroupCriteria;
import com.marineindustryproj.service.dto.EducationalCenterGroupDTO;
import com.marineindustryproj.service.mapper.EducationalCenterGroupMapper;

/**
 * Service for executing complex queries for EducationalCenterGroup entities in the database.
 * The main input is a {@link EducationalCenterGroupCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link EducationalCenterGroupDTO} or a {@link Page} of {@link EducationalCenterGroupDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class EducationalCenterGroupQueryService extends QueryService<EducationalCenterGroup> {

    private final Logger log = LoggerFactory.getLogger(EducationalCenterGroupQueryService.class);

    private final EducationalCenterGroupRepository educationalCenterGroupRepository;

    private final EducationalCenterGroupMapper educationalCenterGroupMapper;

    public EducationalCenterGroupQueryService(EducationalCenterGroupRepository educationalCenterGroupRepository, EducationalCenterGroupMapper educationalCenterGroupMapper) {
        this.educationalCenterGroupRepository = educationalCenterGroupRepository;
        this.educationalCenterGroupMapper = educationalCenterGroupMapper;
    }

    /**
     * Return a {@link List} of {@link EducationalCenterGroupDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<EducationalCenterGroupDTO> findByCriteria(EducationalCenterGroupCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<EducationalCenterGroup> specification = createSpecification(criteria);
        return educationalCenterGroupMapper.toDto(educationalCenterGroupRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link EducationalCenterGroupDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<EducationalCenterGroupDTO> findByCriteria(EducationalCenterGroupCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<EducationalCenterGroup> specification = createSpecification(criteria);
        return educationalCenterGroupRepository.findAll(specification, page)
            .map(educationalCenterGroupMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(EducationalCenterGroupCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<EducationalCenterGroup> specification = createSpecification(criteria);
        return educationalCenterGroupRepository.count(specification);
    }

    /**
     * Function to convert EducationalCenterGroupCriteria to a {@link Specification}
     */
    private Specification<EducationalCenterGroup> createSpecification(EducationalCenterGroupCriteria criteria) {
        Specification<EducationalCenterGroup> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), EducationalCenterGroup_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), EducationalCenterGroup_.title));
            }
            if (criteria.getDisplayOrder() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDisplayOrder(), EducationalCenterGroup_.displayOrder));
            }
            if (criteria.getWeight() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getWeight(), EducationalCenterGroup_.weight));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), EducationalCenterGroup_.description));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), EducationalCenterGroup_.code));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), EducationalCenterGroup_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), EducationalCenterGroup_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), EducationalCenterGroup_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), EducationalCenterGroup_.modifyDate));
            }
            if (criteria.getEducationalCenterGradeId() != null) {
                specification = specification.and(buildSpecification(criteria.getEducationalCenterGradeId(),
                    root -> root.join(EducationalCenterGroup_.educationalCenterGrades, JoinType.LEFT).get(EducationalCenterGrade_.id)));
            }
            if (criteria.getEducationalCenterCriteriaId() != null) {
                specification = specification.and(buildSpecification(criteria.getEducationalCenterCriteriaId(),
                    root -> root.join(EducationalCenterGroup_.educationalCenterCriteria, JoinType.LEFT).get(EducationalCenterCriteria_.id)));
            }
        }
        return specification;
    }
}
