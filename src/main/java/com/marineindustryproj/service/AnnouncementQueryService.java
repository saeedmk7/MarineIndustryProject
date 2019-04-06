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

import com.marineindustryproj.domain.Announcement;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.AnnouncementRepository;
import com.marineindustryproj.service.dto.AnnouncementCriteria;
import com.marineindustryproj.service.dto.AnnouncementDTO;
import com.marineindustryproj.service.mapper.AnnouncementMapper;

/**
 * Service for executing complex queries for Announcement entities in the database.
 * The main input is a {@link AnnouncementCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link AnnouncementDTO} or a {@link Page} of {@link AnnouncementDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class AnnouncementQueryService extends QueryService<Announcement> {

    private final Logger log = LoggerFactory.getLogger(AnnouncementQueryService.class);

    private final AnnouncementRepository announcementRepository;

    private final AnnouncementMapper announcementMapper;

    public AnnouncementQueryService(AnnouncementRepository announcementRepository, AnnouncementMapper announcementMapper) {
        this.announcementRepository = announcementRepository;
        this.announcementMapper = announcementMapper;
    }

    /**
     * Return a {@link List} of {@link AnnouncementDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<AnnouncementDTO> findByCriteria(AnnouncementCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Announcement> specification = createSpecification(criteria);
        return announcementMapper.toDto(announcementRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link AnnouncementDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<AnnouncementDTO> findByCriteria(AnnouncementCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Announcement> specification = createSpecification(criteria);
        return announcementRepository.findAll(specification, page)
            .map(announcementMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(AnnouncementCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Announcement> specification = createSpecification(criteria);
        return announcementRepository.count(specification);
    }

    /**
     * Function to convert AnnouncementCriteria to a {@link Specification}
     */
    private Specification<Announcement> createSpecification(AnnouncementCriteria criteria) {
        Specification<Announcement> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Announcement_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), Announcement_.title));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), Announcement_.code));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), Announcement_.description));
            }
            if (criteria.getIsActive() != null) {
                specification = specification.and(buildSpecification(criteria.getIsActive(), Announcement_.isActive));
            }
            if (criteria.getAnnouncementLevel() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAnnouncementLevel(), Announcement_.announcementLevel));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), Announcement_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), Announcement_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), Announcement_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), Announcement_.modifyDate));
            }
            if (criteria.getDocumentId() != null) {
                specification = specification.and(buildSpecification(criteria.getDocumentId(),
                    root -> root.join(Announcement_.documents, JoinType.LEFT).get(Document_.id)));
            }
        }
        return specification;
    }
}
