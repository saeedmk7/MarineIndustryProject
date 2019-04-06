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

import com.marineindustryproj.domain.EducationalHistory;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.EducationalHistoryRepository;
import com.marineindustryproj.service.dto.EducationalHistoryCriteria;
import com.marineindustryproj.service.dto.EducationalHistoryDTO;
import com.marineindustryproj.service.mapper.EducationalHistoryMapper;

/**
 * Service for executing complex queries for EducationalHistory entities in the database.
 * The main input is a {@link EducationalHistoryCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link EducationalHistoryDTO} or a {@link Page} of {@link EducationalHistoryDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class EducationalHistoryQueryService extends QueryService<EducationalHistory> {

    private final Logger log = LoggerFactory.getLogger(EducationalHistoryQueryService.class);

    private final EducationalHistoryRepository educationalHistoryRepository;

    private final EducationalHistoryMapper educationalHistoryMapper;

    public EducationalHistoryQueryService(EducationalHistoryRepository educationalHistoryRepository, EducationalHistoryMapper educationalHistoryMapper) {
        this.educationalHistoryRepository = educationalHistoryRepository;
        this.educationalHistoryMapper = educationalHistoryMapper;
    }

    /**
     * Return a {@link List} of {@link EducationalHistoryDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<EducationalHistoryDTO> findByCriteria(EducationalHistoryCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<EducationalHistory> specification = createSpecification(criteria);
        return educationalHistoryMapper.toDto(educationalHistoryRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link EducationalHistoryDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<EducationalHistoryDTO> findByCriteria(EducationalHistoryCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<EducationalHistory> specification = createSpecification(criteria);
        return educationalHistoryRepository.findAll(specification, page)
            .map(educationalHistoryMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(EducationalHistoryCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<EducationalHistory> specification = createSpecification(criteria);
        return educationalHistoryRepository.count(specification);
    }

    /**
     * Function to convert EducationalHistoryCriteria to a {@link Specification}
     */
    private Specification<EducationalHistory> createSpecification(EducationalHistoryCriteria criteria) {
        Specification<EducationalHistory> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), EducationalHistory_.id));
            }
            if (criteria.getEducationalModuleTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEducationalModuleTitle(), EducationalHistory_.educationalModuleTitle));
            }
            if (criteria.getLearningTimeTheorical() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLearningTimeTheorical(), EducationalHistory_.learningTimeTheorical));
            }
            if (criteria.getLearningTimePractical() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLearningTimePractical(), EducationalHistory_.learningTimePractical));
            }
            if (criteria.getTotalTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTotalTime(), EducationalHistory_.totalTime));
            }
            if (criteria.getEducationalCenter() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEducationalCenter(), EducationalHistory_.educationalCenter));
            }
            if (criteria.getDateOfStart() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDateOfStart(), EducationalHistory_.dateOfStart));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), EducationalHistory_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), EducationalHistory_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), EducationalHistory_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), EducationalHistory_.modifyDate));
            }
            if (criteria.getArchived() != null) {
                specification = specification.and(buildSpecification(criteria.getArchived(), EducationalHistory_.archived));
            }
            if (criteria.getArchivedUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getArchivedUserLogin(), EducationalHistory_.archivedUserLogin));
            }
            if (criteria.getArchivedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getArchivedDate(), EducationalHistory_.archivedDate));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStatus(), EducationalHistory_.status));
            }
            if (criteria.getRequestStatus() != null) {
                specification = specification.and(buildSpecification(criteria.getRequestStatus(), EducationalHistory_.requestStatus));
            }
            if (criteria.getChangeStatusUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getChangeStatusUserLogin(), EducationalHistory_.changeStatusUserLogin));
            }
            if (criteria.getPersonId() != null) {
                specification = specification.and(buildSpecification(criteria.getPersonId(),
                    root -> root.join(EducationalHistory_.person, JoinType.LEFT).get(Person_.id)));
            }
            if (criteria.getOrganizationChartId() != null) {
                specification = specification.and(buildSpecification(criteria.getOrganizationChartId(),
                    root -> root.join(EducationalHistory_.organizationChart, JoinType.LEFT).get(OrganizationChart_.id)));
            }
        }
        return specification;
    }
}
