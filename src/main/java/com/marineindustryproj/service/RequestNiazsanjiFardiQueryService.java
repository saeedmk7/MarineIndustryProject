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

import com.marineindustryproj.domain.RequestNiazsanjiFardi;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.RequestNiazsanjiFardiRepository;
import com.marineindustryproj.service.dto.RequestNiazsanjiFardiCriteria;
import com.marineindustryproj.service.dto.RequestNiazsanjiFardiDTO;
import com.marineindustryproj.service.mapper.RequestNiazsanjiFardiMapper;

/**
 * Service for executing complex queries for RequestNiazsanjiFardi entities in the database.
 * The main input is a {@link RequestNiazsanjiFardiCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link RequestNiazsanjiFardiDTO} or a {@link Page} of {@link RequestNiazsanjiFardiDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class RequestNiazsanjiFardiQueryService extends QueryService<RequestNiazsanjiFardi> {

    private final Logger log = LoggerFactory.getLogger(RequestNiazsanjiFardiQueryService.class);

    private final RequestNiazsanjiFardiRepository requestNiazsanjiFardiRepository;

    private final RequestNiazsanjiFardiMapper requestNiazsanjiFardiMapper;

    public RequestNiazsanjiFardiQueryService(RequestNiazsanjiFardiRepository requestNiazsanjiFardiRepository, RequestNiazsanjiFardiMapper requestNiazsanjiFardiMapper) {
        this.requestNiazsanjiFardiRepository = requestNiazsanjiFardiRepository;
        this.requestNiazsanjiFardiMapper = requestNiazsanjiFardiMapper;
    }

    /**
     * Return a {@link List} of {@link RequestNiazsanjiFardiDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<RequestNiazsanjiFardiDTO> findByCriteria(RequestNiazsanjiFardiCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<RequestNiazsanjiFardi> specification = createSpecification(criteria);
        return requestNiazsanjiFardiMapper.toDto(requestNiazsanjiFardiRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link RequestNiazsanjiFardiDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<RequestNiazsanjiFardiDTO> findByCriteria(RequestNiazsanjiFardiCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<RequestNiazsanjiFardi> specification = createSpecification(criteria);
        return requestNiazsanjiFardiRepository.findAll(specification, page)
            .map(requestNiazsanjiFardiMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(RequestNiazsanjiFardiCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<RequestNiazsanjiFardi> specification = createSpecification(criteria);
        return requestNiazsanjiFardiRepository.count(specification);
    }

    /**
     * Function to convert RequestNiazsanjiFardiCriteria to a {@link Specification}
     */
    private Specification<RequestNiazsanjiFardi> createSpecification(RequestNiazsanjiFardiCriteria criteria) {
        Specification<RequestNiazsanjiFardi> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), RequestNiazsanjiFardi_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), RequestNiazsanjiFardi_.code));
            }
            if (criteria.getCostApprovedEducationalModule() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCostApprovedEducationalModule(), RequestNiazsanjiFardi_.costApprovedEducationalModule));
            }
            if (criteria.getCostAllEducationalModule() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCostAllEducationalModule(), RequestNiazsanjiFardi_.costAllEducationalModule));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), RequestNiazsanjiFardi_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), RequestNiazsanjiFardi_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), RequestNiazsanjiFardi_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), RequestNiazsanjiFardi_.modifyDate));
            }
            if (criteria.getArchived() != null) {
                specification = specification.and(buildSpecification(criteria.getArchived(), RequestNiazsanjiFardi_.archived));
            }
            if (criteria.getArchivedUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getArchivedUserLogin(), RequestNiazsanjiFardi_.archivedUserLogin));
            }
            if (criteria.getArchivedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getArchivedDate(), RequestNiazsanjiFardi_.archivedDate));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStatus(), RequestNiazsanjiFardi_.status));
            }
            if (criteria.getRequestStatus() != null) {
                specification = specification.and(buildSpecification(criteria.getRequestStatus(), RequestNiazsanjiFardi_.requestStatus));
            }
            if (criteria.getChangeStatusUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getChangeStatusUserLogin(), RequestNiazsanjiFardi_.changeStatusUserLogin));
            }
            if (criteria.getNiazsanjiFardiId() != null) {
                specification = specification.and(buildSpecification(criteria.getNiazsanjiFardiId(),
                    root -> root.join(RequestNiazsanjiFardi_.niazsanjiFardis, JoinType.LEFT).get(NiazsanjiFardi_.id)));
            }
            if (criteria.getDocumentId() != null) {
                specification = specification.and(buildSpecification(criteria.getDocumentId(),
                    root -> root.join(RequestNiazsanjiFardi_.documents, JoinType.LEFT).get(Document_.id)));
            }
            if (criteria.getApprovedEducationalModuleId() != null) {
                specification = specification.and(buildSpecification(criteria.getApprovedEducationalModuleId(),
                    root -> root.join(RequestNiazsanjiFardi_.approvedEducationalModule, JoinType.LEFT).get(EducationalModule_.id)));
            }
            if (criteria.getAllEducationalModuleId() != null) {
                specification = specification.and(buildSpecification(criteria.getAllEducationalModuleId(),
                    root -> root.join(RequestNiazsanjiFardi_.allEducationalModule, JoinType.LEFT).get(EducationalModule_.id)));
            }
            if (criteria.getPersonId() != null) {
                specification = specification.and(buildSpecification(criteria.getPersonId(),
                    root -> root.join(RequestNiazsanjiFardi_.person, JoinType.LEFT).get(Person_.id)));
            }
            if (criteria.getOrganizationChartId() != null) {
                specification = specification.and(buildSpecification(criteria.getOrganizationChartId(),
                    root -> root.join(RequestNiazsanjiFardi_.organizationChart, JoinType.LEFT).get(OrganizationChart_.id)));
            }
        }
        return specification;
    }
}
