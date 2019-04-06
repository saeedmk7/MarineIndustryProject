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

import com.marineindustryproj.domain.Raste;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.RasteRepository;
import com.marineindustryproj.service.dto.RasteCriteria;
import com.marineindustryproj.service.dto.RasteDTO;
import com.marineindustryproj.service.mapper.RasteMapper;

/**
 * Service for executing complex queries for Raste entities in the database.
 * The main input is a {@link RasteCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link RasteDTO} or a {@link Page} of {@link RasteDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class RasteQueryService extends QueryService<Raste> {

    private final Logger log = LoggerFactory.getLogger(RasteQueryService.class);

    private final RasteRepository rasteRepository;

    private final RasteMapper rasteMapper;

    public RasteQueryService(RasteRepository rasteRepository, RasteMapper rasteMapper) {
        this.rasteRepository = rasteRepository;
        this.rasteMapper = rasteMapper;
    }

    /**
     * Return a {@link List} of {@link RasteDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<RasteDTO> findByCriteria(RasteCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Raste> specification = createSpecification(criteria);
        return rasteMapper.toDto(rasteRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link RasteDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<RasteDTO> findByCriteria(RasteCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Raste> specification = createSpecification(criteria);
        return rasteRepository.findAll(specification, page)
            .map(rasteMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(RasteCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Raste> specification = createSpecification(criteria);
        return rasteRepository.count(specification);
    }

    /**
     * Function to convert RasteCriteria to a {@link Specification}
     */
    private Specification<Raste> createSpecification(RasteCriteria criteria) {
        Specification<Raste> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Raste_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), Raste_.title));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), Raste_.code));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), Raste_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), Raste_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), Raste_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), Raste_.modifyDate));
            }
            if (criteria.getJobId() != null) {
                specification = specification.and(buildSpecification(criteria.getJobId(),
                    root -> root.join(Raste_.jobs, JoinType.LEFT).get(Job_.id)));
            }
        }
        return specification;
    }
}
