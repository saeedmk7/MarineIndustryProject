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

import com.marineindustryproj.domain.Resource;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.ResourceRepository;
import com.marineindustryproj.service.dto.ResourceCriteria;
import com.marineindustryproj.service.dto.ResourceDTO;
import com.marineindustryproj.service.mapper.ResourceMapper;

/**
 * Service for executing complex queries for Resource entities in the database.
 * The main input is a {@link ResourceCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ResourceDTO} or a {@link Page} of {@link ResourceDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ResourceQueryService extends QueryService<Resource> {

    private final Logger log = LoggerFactory.getLogger(ResourceQueryService.class);

    private final ResourceRepository resourceRepository;

    private final ResourceMapper resourceMapper;

    public ResourceQueryService(ResourceRepository resourceRepository, ResourceMapper resourceMapper) {
        this.resourceRepository = resourceRepository;
        this.resourceMapper = resourceMapper;
    }

    /**
     * Return a {@link List} of {@link ResourceDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ResourceDTO> findByCriteria(ResourceCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Resource> specification = createSpecification(criteria);
        return resourceMapper.toDto(resourceRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ResourceDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ResourceDTO> findByCriteria(ResourceCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Resource> specification = createSpecification(criteria);
        return resourceRepository.findAll(specification, page)
            .map(resourceMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ResourceCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Resource> specification = createSpecification(criteria);
        return resourceRepository.count(specification);
    }

    /**
     * Function to convert ResourceCriteria to a {@link Specification}
     */
    private Specification<Resource> createSpecification(ResourceCriteria criteria) {
        Specification<Resource> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Resource_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), Resource_.title));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), Resource_.description));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), Resource_.code));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), Resource_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), Resource_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), Resource_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), Resource_.modifyDate));
            }
            if (criteria.getDocumentId() != null) {
                specification = specification.and(buildSpecification(criteria.getDocumentId(),
                    root -> root.join(Resource_.documents, JoinType.LEFT).get(Document_.id)));
            }
            if (criteria.getEducationalModuleId() != null) {
                specification = specification.and(buildSpecification(criteria.getEducationalModuleId(),
                    root -> root.join(Resource_.educationalModules, JoinType.LEFT).get(EducationalModule_.id)));
            }
            if (criteria.getRequestEducationalModuleId() != null) {
                specification = specification.and(buildSpecification(criteria.getRequestEducationalModuleId(),
                    root -> root.join(Resource_.requestEducationalModules, JoinType.LEFT).get(RequestEducationalModule_.id)));
            }
        }
        return specification;
    }
}
