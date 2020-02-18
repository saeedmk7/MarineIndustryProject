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

import com.marineindustryproj.domain.Headline;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.HeadlineRepository;
import com.marineindustryproj.service.dto.HeadlineCriteria;
import com.marineindustryproj.service.dto.HeadlineDTO;
import com.marineindustryproj.service.mapper.HeadlineMapper;

/**
 * Service for executing complex queries for Headline entities in the database.
 * The main input is a {@link HeadlineCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link HeadlineDTO} or a {@link Page} of {@link HeadlineDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class HeadlineQueryService extends QueryService<Headline> {

    private final Logger log = LoggerFactory.getLogger(HeadlineQueryService.class);

    private final HeadlineRepository headlineRepository;

    private final HeadlineMapper headlineMapper;

    public HeadlineQueryService(HeadlineRepository headlineRepository, HeadlineMapper headlineMapper) {
        this.headlineRepository = headlineRepository;
        this.headlineMapper = headlineMapper;
    }

    /**
     * Return a {@link List} of {@link HeadlineDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<HeadlineDTO> findByCriteria(HeadlineCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Headline> specification = createSpecification(criteria);
        return headlineMapper.toDto(headlineRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link HeadlineDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<HeadlineDTO> findByCriteria(HeadlineCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Headline> specification = createSpecification(criteria);
        return headlineRepository.findAll(specification, page)
            .map(headlineMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(HeadlineCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Headline> specification = createSpecification(criteria);
        return headlineRepository.count(specification);
    }

    /**
     * Function to convert HeadlineCriteria to a {@link Specification}
     */
    private Specification<Headline> createSpecification(HeadlineCriteria criteria) {
        Specification<Headline> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Headline_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), Headline_.title));
            }
            if (criteria.getHeadlineLevel() != null) {
                specification = specification.and(buildSpecification(criteria.getHeadlineLevel(), Headline_.headlineLevel));
            }
            if (criteria.getHeadlineScope() != null) {
                specification = specification.and(buildSpecification(criteria.getHeadlineScope(), Headline_.headlineScope));
            }
            if (criteria.getTotalHour() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTotalHour(), Headline_.totalHour));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), Headline_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), Headline_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), Headline_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), Headline_.modifyDate));
            }
            if (criteria.getRequestEducationalModuleId() != null) {
                specification = specification.and(buildSpecification(criteria.getRequestEducationalModuleId(),
                    root -> root.join(Headline_.requestEducationalModule, JoinType.LEFT).get(RequestEducationalModule_.id)));
            }
            if (criteria.getEducationalModuleId() != null) {
                specification = specification.and(buildSpecification(criteria.getEducationalModuleId(),
                    root -> root.join(Headline_.educationalModule, JoinType.LEFT).get(EducationalModule_.id)));
            }
        }
        return specification;
    }
}
