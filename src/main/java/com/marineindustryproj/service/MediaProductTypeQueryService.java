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

import com.marineindustryproj.domain.MediaProductType;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.MediaProductTypeRepository;
import com.marineindustryproj.service.dto.MediaProductTypeCriteria;
import com.marineindustryproj.service.dto.MediaProductTypeDTO;
import com.marineindustryproj.service.mapper.MediaProductTypeMapper;

/**
 * Service for executing complex queries for MediaProductType entities in the database.
 * The main input is a {@link MediaProductTypeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MediaProductTypeDTO} or a {@link Page} of {@link MediaProductTypeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MediaProductTypeQueryService extends QueryService<MediaProductType> {

    private final Logger log = LoggerFactory.getLogger(MediaProductTypeQueryService.class);

    private final MediaProductTypeRepository mediaProductTypeRepository;

    private final MediaProductTypeMapper mediaProductTypeMapper;

    public MediaProductTypeQueryService(MediaProductTypeRepository mediaProductTypeRepository, MediaProductTypeMapper mediaProductTypeMapper) {
        this.mediaProductTypeRepository = mediaProductTypeRepository;
        this.mediaProductTypeMapper = mediaProductTypeMapper;
    }

    /**
     * Return a {@link List} of {@link MediaProductTypeDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MediaProductTypeDTO> findByCriteria(MediaProductTypeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MediaProductType> specification = createSpecification(criteria);
        return mediaProductTypeMapper.toDto(mediaProductTypeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MediaProductTypeDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MediaProductTypeDTO> findByCriteria(MediaProductTypeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MediaProductType> specification = createSpecification(criteria);
        return mediaProductTypeRepository.findAll(specification, page)
            .map(mediaProductTypeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MediaProductTypeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MediaProductType> specification = createSpecification(criteria);
        return mediaProductTypeRepository.count(specification);
    }

    /**
     * Function to convert MediaProductTypeCriteria to a {@link Specification}
     */
    private Specification<MediaProductType> createSpecification(MediaProductTypeCriteria criteria) {
        Specification<MediaProductType> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MediaProductType_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), MediaProductType_.title));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), MediaProductType_.description));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), MediaProductType_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), MediaProductType_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), MediaProductType_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), MediaProductType_.modifyDate));
            }
            if (criteria.getMediaAwarenessReportId() != null) {
                specification = specification.and(buildSpecification(criteria.getMediaAwarenessReportId(),
                    root -> root.join(MediaProductType_.mediaAwarenessReports, JoinType.LEFT).get(MediaAwarenessReport_.id)));
            }
        }
        return specification;
    }
}
