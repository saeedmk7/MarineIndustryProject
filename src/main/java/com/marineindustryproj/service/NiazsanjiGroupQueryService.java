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

import com.marineindustryproj.domain.NiazsanjiGroup;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.NiazsanjiGroupRepository;
import com.marineindustryproj.service.dto.NiazsanjiGroupCriteria;
import com.marineindustryproj.service.dto.NiazsanjiGroupDTO;
import com.marineindustryproj.service.mapper.NiazsanjiGroupMapper;

/**
 * Service for executing complex queries for NiazsanjiGroup entities in the database.
 * The main input is a {@link NiazsanjiGroupCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link NiazsanjiGroupDTO} or a {@link Page} of {@link NiazsanjiGroupDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class NiazsanjiGroupQueryService extends QueryService<NiazsanjiGroup> {

    private final Logger log = LoggerFactory.getLogger(NiazsanjiGroupQueryService.class);

    private final NiazsanjiGroupRepository niazsanjiGroupRepository;

    private final NiazsanjiGroupMapper niazsanjiGroupMapper;

    public NiazsanjiGroupQueryService(NiazsanjiGroupRepository niazsanjiGroupRepository, NiazsanjiGroupMapper niazsanjiGroupMapper) {
        this.niazsanjiGroupRepository = niazsanjiGroupRepository;
        this.niazsanjiGroupMapper = niazsanjiGroupMapper;
    }

    /**
     * Return a {@link List} of {@link NiazsanjiGroupDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<NiazsanjiGroupDTO> findByCriteria(NiazsanjiGroupCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<NiazsanjiGroup> specification = createSpecification(criteria);
        return niazsanjiGroupMapper.toDto(niazsanjiGroupRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link NiazsanjiGroupDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<NiazsanjiGroupDTO> findByCriteria(NiazsanjiGroupCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<NiazsanjiGroup> specification = createSpecification(criteria);
        return niazsanjiGroupRepository.findAll(specification, page)
            .map(niazsanjiGroupMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(NiazsanjiGroupCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<NiazsanjiGroup> specification = createSpecification(criteria);
        return niazsanjiGroupRepository.count(specification);
    }

    /**
     * Function to convert NiazsanjiGroupCriteria to a {@link Specification}
     */
    private Specification<NiazsanjiGroup> createSpecification(NiazsanjiGroupCriteria criteria) {
        Specification<NiazsanjiGroup> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), NiazsanjiGroup_.id));
            }
            if (criteria.getEditorPerson() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEditorPerson(), NiazsanjiGroup_.editorPerson));
            }
            if (criteria.getReviewDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getReviewDate(), NiazsanjiGroup_.reviewDate));
            }
            if (criteria.getScheduleDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getScheduleDate(), NiazsanjiGroup_.scheduleDate));
            }
            if (criteria.getFirstThreeJobCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFirstThreeJobCode(), NiazsanjiGroup_.firstThreeJobCode));
            }
            if (criteria.getPriceCost() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPriceCost(), NiazsanjiGroup_.priceCost));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), NiazsanjiGroup_.description));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), NiazsanjiGroup_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), NiazsanjiGroup_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), NiazsanjiGroup_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), NiazsanjiGroup_.modifyDate));
            }
            if (criteria.getArchived() != null) {
                specification = specification.and(buildSpecification(criteria.getArchived(), NiazsanjiGroup_.archived));
            }
            if (criteria.getArchivedUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getArchivedUserLogin(), NiazsanjiGroup_.archivedUserLogin));
            }
            if (criteria.getArchivedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getArchivedDate(), NiazsanjiGroup_.archivedDate));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStatus(), NiazsanjiGroup_.status));
            }
            if (criteria.getJobId() != null) {
                specification = specification.and(buildSpecification(criteria.getJobId(),
                    root -> root.join(NiazsanjiGroup_.jobs, JoinType.LEFT).get(Job_.id)));
            }
            if (criteria.getEducationalModuleId() != null) {
                specification = specification.and(buildSpecification(criteria.getEducationalModuleId(),
                    root -> root.join(NiazsanjiGroup_.educationalModules, JoinType.LEFT).get(EducationalModule_.id)));
            }
            if (criteria.getScientificWorkGroupId() != null) {
                specification = specification.and(buildSpecification(criteria.getScientificWorkGroupId(),
                    root -> root.join(NiazsanjiGroup_.scientificWorkGroup, JoinType.LEFT).get(ScientificWorkGroup_.id)));
            }
        }
        return specification;
    }
}
