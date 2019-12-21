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

import com.marineindustryproj.domain.MediaAwarenessReport;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.MediaAwarenessReportRepository;
import com.marineindustryproj.service.dto.MediaAwarenessReportCriteria;
import com.marineindustryproj.service.dto.MediaAwarenessReportDTO;
import com.marineindustryproj.service.mapper.MediaAwarenessReportMapper;

/**
 * Service for executing complex queries for MediaAwarenessReport entities in the database.
 * The main input is a {@link MediaAwarenessReportCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MediaAwarenessReportDTO} or a {@link Page} of {@link MediaAwarenessReportDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MediaAwarenessReportQueryService extends QueryService<MediaAwarenessReport> {

    private final Logger log = LoggerFactory.getLogger(MediaAwarenessReportQueryService.class);

    private final MediaAwarenessReportRepository mediaAwarenessReportRepository;

    private final MediaAwarenessReportMapper mediaAwarenessReportMapper;

    public MediaAwarenessReportQueryService(MediaAwarenessReportRepository mediaAwarenessReportRepository, MediaAwarenessReportMapper mediaAwarenessReportMapper) {
        this.mediaAwarenessReportRepository = mediaAwarenessReportRepository;
        this.mediaAwarenessReportMapper = mediaAwarenessReportMapper;
    }

    /**
     * Return a {@link List} of {@link MediaAwarenessReportDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MediaAwarenessReportDTO> findByCriteria(MediaAwarenessReportCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MediaAwarenessReport> specification = createSpecification(criteria);
        return mediaAwarenessReportMapper.toDto(mediaAwarenessReportRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MediaAwarenessReportDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MediaAwarenessReportDTO> findByCriteria(MediaAwarenessReportCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MediaAwarenessReport> specification = createSpecification(criteria);
        return mediaAwarenessReportRepository.findAll(specification, page)
            .map(mediaAwarenessReportMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MediaAwarenessReportCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MediaAwarenessReport> specification = createSpecification(criteria);
        return mediaAwarenessReportRepository.count(specification);
    }

    /**
     * Function to convert MediaAwarenessReportCriteria to a {@link Specification}
     */
    private Specification<MediaAwarenessReport> createSpecification(MediaAwarenessReportCriteria criteria) {
        Specification<MediaAwarenessReport> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MediaAwarenessReport_.id));
            }
            if (criteria.getDesigned() != null) {
                specification = specification.and(buildSpecification(criteria.getDesigned(), MediaAwarenessReport_.designed));
            }
            if (criteria.getDesignedNumber() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDesignedNumber(), MediaAwarenessReport_.designedNumber));
            }
            if (criteria.getSubject() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSubject(), MediaAwarenessReport_.subject));
            }
            if (criteria.getPublishDate() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPublishDate(), MediaAwarenessReport_.publishDate));
            }
            if (criteria.getNumberOfViewers() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNumberOfViewers(), MediaAwarenessReport_.numberOfViewers));
            }
            if (criteria.getDurationOfOperation() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDurationOfOperation(), MediaAwarenessReport_.durationOfOperation));
            }
            if (criteria.getReportTime() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getReportTime(), MediaAwarenessReport_.reportTime));
            }
            if (criteria.getPersonHour() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPersonHour(), MediaAwarenessReport_.personHour));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), MediaAwarenessReport_.description));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), MediaAwarenessReport_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), MediaAwarenessReport_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), MediaAwarenessReport_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), MediaAwarenessReport_.modifyDate));
            }
            if (criteria.getGuid() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGuid(), MediaAwarenessReport_.guid));
            }
            if (criteria.getOrganizationChartId() != null) {
                specification = specification.and(buildSpecification(criteria.getOrganizationChartId(),
                    root -> root.join(MediaAwarenessReport_.organizationChart, JoinType.LEFT).get(OrganizationChart_.id)));
            }
            if (criteria.getMediaProductTypeId() != null) {
                specification = specification.and(buildSpecification(criteria.getMediaProductTypeId(),
                    root -> root.join(MediaAwarenessReport_.mediaProductType, JoinType.LEFT).get(MediaProductType_.id)));
            }
        }
        return specification;
    }
}
