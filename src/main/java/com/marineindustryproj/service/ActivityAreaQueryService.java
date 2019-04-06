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

import com.marineindustryproj.domain.ActivityArea;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.ActivityAreaRepository;
import com.marineindustryproj.service.dto.ActivityAreaCriteria;
import com.marineindustryproj.service.dto.ActivityAreaDTO;
import com.marineindustryproj.service.mapper.ActivityAreaMapper;

/**
 * Service for executing complex queries for ActivityArea entities in the database.
 * The main input is a {@link ActivityAreaCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ActivityAreaDTO} or a {@link Page} of {@link ActivityAreaDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ActivityAreaQueryService extends QueryService<ActivityArea> {

    private final Logger log = LoggerFactory.getLogger(ActivityAreaQueryService.class);

    private final ActivityAreaRepository activityAreaRepository;

    private final ActivityAreaMapper activityAreaMapper;

    public ActivityAreaQueryService(ActivityAreaRepository activityAreaRepository, ActivityAreaMapper activityAreaMapper) {
        this.activityAreaRepository = activityAreaRepository;
        this.activityAreaMapper = activityAreaMapper;
    }

    /**
     * Return a {@link List} of {@link ActivityAreaDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ActivityAreaDTO> findByCriteria(ActivityAreaCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ActivityArea> specification = createSpecification(criteria);
        return activityAreaMapper.toDto(activityAreaRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ActivityAreaDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ActivityAreaDTO> findByCriteria(ActivityAreaCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ActivityArea> specification = createSpecification(criteria);
        return activityAreaRepository.findAll(specification, page)
            .map(activityAreaMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ActivityAreaCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ActivityArea> specification = createSpecification(criteria);
        return activityAreaRepository.count(specification);
    }

    /**
     * Function to convert ActivityAreaCriteria to a {@link Specification}
     */
    private Specification<ActivityArea> createSpecification(ActivityAreaCriteria criteria) {
        Specification<ActivityArea> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ActivityArea_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), ActivityArea_.title));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), ActivityArea_.code));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), ActivityArea_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), ActivityArea_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), ActivityArea_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), ActivityArea_.modifyDate));
            }
            if (criteria.getEducationalCenterId() != null) {
                specification = specification.and(buildSpecification(criteria.getEducationalCenterId(),
                    root -> root.join(ActivityArea_.educationalCenters, JoinType.LEFT).get(EducationalCenter_.id)));
            }
        }
        return specification;
    }
}
