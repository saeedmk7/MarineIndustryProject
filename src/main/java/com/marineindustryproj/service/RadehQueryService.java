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

import com.marineindustryproj.domain.Radeh;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.RadehRepository;
import com.marineindustryproj.service.dto.RadehCriteria;
import com.marineindustryproj.service.dto.RadehDTO;
import com.marineindustryproj.service.mapper.RadehMapper;

/**
 * Service for executing complex queries for Radeh entities in the database.
 * The main input is a {@link RadehCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link RadehDTO} or a {@link Page} of {@link RadehDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class RadehQueryService extends QueryService<Radeh> {

    private final Logger log = LoggerFactory.getLogger(RadehQueryService.class);

    private final RadehRepository radehRepository;

    private final RadehMapper radehMapper;

    public RadehQueryService(RadehRepository radehRepository, RadehMapper radehMapper) {
        this.radehRepository = radehRepository;
        this.radehMapper = radehMapper;
    }

    /**
     * Return a {@link List} of {@link RadehDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<RadehDTO> findByCriteria(RadehCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Radeh> specification = createSpecification(criteria);
        return radehMapper.toDto(radehRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link RadehDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<RadehDTO> findByCriteria(RadehCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Radeh> specification = createSpecification(criteria);
        return radehRepository.findAll(specification, page)
            .map(radehMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(RadehCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Radeh> specification = createSpecification(criteria);
        return radehRepository.count(specification);
    }

    /**
     * Function to convert RadehCriteria to a {@link Specification}
     */
    private Specification<Radeh> createSpecification(RadehCriteria criteria) {
        Specification<Radeh> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Radeh_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), Radeh_.title));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), Radeh_.code));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), Radeh_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), Radeh_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), Radeh_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), Radeh_.modifyDate));
            }
            if (criteria.getJobId() != null) {
                specification = specification.and(buildSpecification(criteria.getJobId(),
                    root -> root.join(Radeh_.jobs, JoinType.LEFT).get(Job_.id)));
            }
        }
        return specification;
    }
}
