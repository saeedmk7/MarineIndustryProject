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

import com.marineindustryproj.domain.EducationalCenterService;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.EducationalCenterServiceRepository;
import com.marineindustryproj.service.dto.EducationalCenterServiceCriteria;
import com.marineindustryproj.service.dto.EducationalCenterServiceDTO;
import com.marineindustryproj.service.mapper.EducationalCenterServiceMapper;

/**
 * Service for executing complex queries for EducationalCenterService entities in the database.
 * The main input is a {@link EducationalCenterServiceCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link EducationalCenterServiceDTO} or a {@link Page} of {@link EducationalCenterServiceDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class EducationalCenterServiceQueryService extends QueryService<EducationalCenterService> {

    private final Logger log = LoggerFactory.getLogger(EducationalCenterServiceQueryService.class);

    private final EducationalCenterServiceRepository educationalCenterServiceRepository;

    private final EducationalCenterServiceMapper educationalCenterServiceMapper;

    public EducationalCenterServiceQueryService(EducationalCenterServiceRepository educationalCenterServiceRepository, EducationalCenterServiceMapper educationalCenterServiceMapper) {
        this.educationalCenterServiceRepository = educationalCenterServiceRepository;
        this.educationalCenterServiceMapper = educationalCenterServiceMapper;
    }

    /**
     * Return a {@link List} of {@link EducationalCenterServiceDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<EducationalCenterServiceDTO> findByCriteria(EducationalCenterServiceCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<EducationalCenterService> specification = createSpecification(criteria);
        return educationalCenterServiceMapper.toDto(educationalCenterServiceRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link EducationalCenterServiceDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<EducationalCenterServiceDTO> findByCriteria(EducationalCenterServiceCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<EducationalCenterService> specification = createSpecification(criteria);
        return educationalCenterServiceRepository.findAll(specification, page)
            .map(educationalCenterServiceMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(EducationalCenterServiceCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<EducationalCenterService> specification = createSpecification(criteria);
        return educationalCenterServiceRepository.count(specification);
    }

    /**
     * Function to convert EducationalCenterServiceCriteria to a {@link Specification}
     */
    private Specification<EducationalCenterService> createSpecification(EducationalCenterServiceCriteria criteria) {
        Specification<EducationalCenterService> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), EducationalCenterService_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), EducationalCenterService_.title));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), EducationalCenterService_.description));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), EducationalCenterService_.code));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), EducationalCenterService_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), EducationalCenterService_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), EducationalCenterService_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), EducationalCenterService_.modifyDate));
            }
            if (criteria.getEducationalCenterGradeId() != null) {
                specification = specification.and(buildSpecification(criteria.getEducationalCenterGradeId(),
                    root -> root.join(EducationalCenterService_.educationalCenterGrades, JoinType.LEFT).get(EducationalCenterGrade_.id)));
            }
        }
        return specification;
    }
}
