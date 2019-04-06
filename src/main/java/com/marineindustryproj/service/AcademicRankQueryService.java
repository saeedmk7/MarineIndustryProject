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

import com.marineindustryproj.domain.AcademicRank;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.AcademicRankRepository;
import com.marineindustryproj.service.dto.AcademicRankCriteria;
import com.marineindustryproj.service.dto.AcademicRankDTO;
import com.marineindustryproj.service.mapper.AcademicRankMapper;

/**
 * Service for executing complex queries for AcademicRank entities in the database.
 * The main input is a {@link AcademicRankCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link AcademicRankDTO} or a {@link Page} of {@link AcademicRankDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class AcademicRankQueryService extends QueryService<AcademicRank> {

    private final Logger log = LoggerFactory.getLogger(AcademicRankQueryService.class);

    private final AcademicRankRepository academicRankRepository;

    private final AcademicRankMapper academicRankMapper;

    public AcademicRankQueryService(AcademicRankRepository academicRankRepository, AcademicRankMapper academicRankMapper) {
        this.academicRankRepository = academicRankRepository;
        this.academicRankMapper = academicRankMapper;
    }

    /**
     * Return a {@link List} of {@link AcademicRankDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<AcademicRankDTO> findByCriteria(AcademicRankCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<AcademicRank> specification = createSpecification(criteria);
        return academicRankMapper.toDto(academicRankRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link AcademicRankDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<AcademicRankDTO> findByCriteria(AcademicRankCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<AcademicRank> specification = createSpecification(criteria);
        return academicRankRepository.findAll(specification, page)
            .map(academicRankMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(AcademicRankCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<AcademicRank> specification = createSpecification(criteria);
        return academicRankRepository.count(specification);
    }

    /**
     * Function to convert AcademicRankCriteria to a {@link Specification}
     */
    private Specification<AcademicRank> createSpecification(AcademicRankCriteria criteria) {
        Specification<AcademicRank> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), AcademicRank_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), AcademicRank_.title));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), AcademicRank_.code));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), AcademicRank_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), AcademicRank_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), AcademicRank_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), AcademicRank_.modifyDate));
            }
            if (criteria.getTeacherId() != null) {
                specification = specification.and(buildSpecification(criteria.getTeacherId(),
                    root -> root.join(AcademicRank_.teachers, JoinType.LEFT).get(Teacher_.id)));
            }
        }
        return specification;
    }
}
