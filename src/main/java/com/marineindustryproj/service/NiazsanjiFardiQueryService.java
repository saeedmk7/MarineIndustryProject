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

import com.marineindustryproj.domain.NiazsanjiFardi;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.NiazsanjiFardiRepository;
import com.marineindustryproj.service.dto.NiazsanjiFardiCriteria;
import com.marineindustryproj.service.dto.NiazsanjiFardiDTO;
import com.marineindustryproj.service.mapper.NiazsanjiFardiMapper;

/**
 * Service for executing complex queries for NiazsanjiFardi entities in the database.
 * The main input is a {@link NiazsanjiFardiCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link NiazsanjiFardiDTO} or a {@link Page} of {@link NiazsanjiFardiDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class NiazsanjiFardiQueryService extends QueryService<NiazsanjiFardi> {

    private final Logger log = LoggerFactory.getLogger(NiazsanjiFardiQueryService.class);

    private final NiazsanjiFardiRepository niazsanjiFardiRepository;

    private final NiazsanjiFardiMapper niazsanjiFardiMapper;

    public NiazsanjiFardiQueryService(NiazsanjiFardiRepository niazsanjiFardiRepository, NiazsanjiFardiMapper niazsanjiFardiMapper) {
        this.niazsanjiFardiRepository = niazsanjiFardiRepository;
        this.niazsanjiFardiMapper = niazsanjiFardiMapper;
    }

    /**
     * Return a {@link List} of {@link NiazsanjiFardiDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<NiazsanjiFardiDTO> findByCriteria(NiazsanjiFardiCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<NiazsanjiFardi> specification = createSpecification(criteria);
        return niazsanjiFardiMapper.toDto(niazsanjiFardiRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link NiazsanjiFardiDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<NiazsanjiFardiDTO> findByCriteria(NiazsanjiFardiCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<NiazsanjiFardi> specification = createSpecification(criteria);
        return niazsanjiFardiRepository.findAll(specification, page)
            .map(niazsanjiFardiMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(NiazsanjiFardiCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<NiazsanjiFardi> specification = createSpecification(criteria);
        return niazsanjiFardiRepository.count(specification);
    }

    /**
     * Function to convert NiazsanjiFardiCriteria to a {@link Specification}
     */
    private Specification<NiazsanjiFardi> createSpecification(NiazsanjiFardiCriteria criteria) {
        Specification<NiazsanjiFardi> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), NiazsanjiFardi_.id));
            }
            if (criteria.getNiazsanjiYear() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNiazsanjiYear(), NiazsanjiFardi_.niazsanjiYear));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), NiazsanjiFardi_.code));
            }
            if (criteria.getEducationalModuleType() != null) {
                specification = specification.and(buildSpecification(criteria.getEducationalModuleType(), NiazsanjiFardi_.educationalModuleType));
            }
            if (criteria.getPriceCost() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPriceCost(), NiazsanjiFardi_.priceCost));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), NiazsanjiFardi_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), NiazsanjiFardi_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), NiazsanjiFardi_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), NiazsanjiFardi_.modifyDate));
            }
            if (criteria.getArchived() != null) {
                specification = specification.and(buildSpecification(criteria.getArchived(), NiazsanjiFardi_.archived));
            }
            if (criteria.getArchivedUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getArchivedUserLogin(), NiazsanjiFardi_.archivedUserLogin));
            }
            if (criteria.getArchivedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getArchivedDate(), NiazsanjiFardi_.archivedDate));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStatus(), NiazsanjiFardi_.status));
            }
            if (criteria.getChangeStatusUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getChangeStatusUserLogin(), NiazsanjiFardi_.changeStatusUserLogin));
            }
            if (criteria.getDocumentId() != null) {
                specification = specification.and(buildSpecification(criteria.getDocumentId(),
                    root -> root.join(NiazsanjiFardi_.documents, JoinType.LEFT).get(Document_.id)));
            }
            if (criteria.getCourseTypeId() != null) {
                specification = specification.and(buildSpecification(criteria.getCourseTypeId(),
                    root -> root.join(NiazsanjiFardi_.courseType, JoinType.LEFT).get(CourseType_.id)));
            }
            if (criteria.getRequestNiazsanjiFardiId() != null) {
                specification = specification.and(buildSpecification(criteria.getRequestNiazsanjiFardiId(),
                    root -> root.join(NiazsanjiFardi_.requestNiazsanjiFardi, JoinType.LEFT).get(RequestNiazsanjiFardi_.id)));
            }
            if (criteria.getEducationalModuleId() != null) {
                specification = specification.and(buildSpecification(criteria.getEducationalModuleId(),
                    root -> root.join(NiazsanjiFardi_.educationalModule, JoinType.LEFT).get(EducationalModule_.id)));
            }
            if (criteria.getPersonId() != null) {
                specification = specification.and(buildSpecification(criteria.getPersonId(),
                    root -> root.join(NiazsanjiFardi_.person, JoinType.LEFT).get(Person_.id)));
            }
            if (criteria.getOrganizationChartId() != null) {
                specification = specification.and(buildSpecification(criteria.getOrganizationChartId(),
                    root -> root.join(NiazsanjiFardi_.organizationChart, JoinType.LEFT).get(OrganizationChart_.id)));
            }
        }
        return specification;
    }
}
