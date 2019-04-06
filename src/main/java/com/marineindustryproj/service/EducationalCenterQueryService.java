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

import com.marineindustryproj.domain.EducationalCenter;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.EducationalCenterRepository;
import com.marineindustryproj.service.dto.EducationalCenterCriteria;
import com.marineindustryproj.service.dto.EducationalCenterDTO;
import com.marineindustryproj.service.mapper.EducationalCenterMapper;

/**
 * Service for executing complex queries for EducationalCenter entities in the database.
 * The main input is a {@link EducationalCenterCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link EducationalCenterDTO} or a {@link Page} of {@link EducationalCenterDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class EducationalCenterQueryService extends QueryService<EducationalCenter> {

    private final Logger log = LoggerFactory.getLogger(EducationalCenterQueryService.class);

    private final EducationalCenterRepository educationalCenterRepository;

    private final EducationalCenterMapper educationalCenterMapper;

    public EducationalCenterQueryService(EducationalCenterRepository educationalCenterRepository, EducationalCenterMapper educationalCenterMapper) {
        this.educationalCenterRepository = educationalCenterRepository;
        this.educationalCenterMapper = educationalCenterMapper;
    }

    /**
     * Return a {@link List} of {@link EducationalCenterDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<EducationalCenterDTO> findByCriteria(EducationalCenterCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<EducationalCenter> specification = createSpecification(criteria);
        return educationalCenterMapper.toDto(educationalCenterRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link EducationalCenterDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<EducationalCenterDTO> findByCriteria(EducationalCenterCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<EducationalCenter> specification = createSpecification(criteria);
        return educationalCenterRepository.findAll(specification, page)
            .map(educationalCenterMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(EducationalCenterCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<EducationalCenter> specification = createSpecification(criteria);
        return educationalCenterRepository.count(specification);
    }

    /**
     * Function to convert EducationalCenterCriteria to a {@link Specification}
     */
    private Specification<EducationalCenter> createSpecification(EducationalCenterCriteria criteria) {
        Specification<EducationalCenter> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), EducationalCenter_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), EducationalCenter_.name));
            }
            if (criteria.getCeo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCeo(), EducationalCenter_.ceo));
            }
            if (criteria.getConnectionPerson() != null) {
                specification = specification.and(buildStringSpecification(criteria.getConnectionPerson(), EducationalCenter_.connectionPerson));
            }
            if (criteria.getTelephone() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTelephone(), EducationalCenter_.telephone));
            }
            if (criteria.getFax() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFax(), EducationalCenter_.fax));
            }
            if (criteria.getAddress() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAddress(), EducationalCenter_.address));
            }
            if (criteria.getEmail() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEmail(), EducationalCenter_.email));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), EducationalCenter_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), EducationalCenter_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), EducationalCenter_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), EducationalCenter_.modifyDate));
            }
            if (criteria.getArchived() != null) {
                specification = specification.and(buildSpecification(criteria.getArchived(), EducationalCenter_.archived));
            }
            if (criteria.getArchivedUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getArchivedUserLogin(), EducationalCenter_.archivedUserLogin));
            }
            if (criteria.getArchivedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getArchivedDate(), EducationalCenter_.archivedDate));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStatus(), EducationalCenter_.status));
            }
            if (criteria.getActivityAreaId() != null) {
                specification = specification.and(buildSpecification(criteria.getActivityAreaId(),
                    root -> root.join(EducationalCenter_.activityAreas, JoinType.LEFT).get(ActivityArea_.id)));
            }
            if (criteria.getDocumentId() != null) {
                specification = specification.and(buildSpecification(criteria.getDocumentId(),
                    root -> root.join(EducationalCenter_.documents, JoinType.LEFT).get(Document_.id)));
            }
            if (criteria.getEducationalModuleId() != null) {
                specification = specification.and(buildSpecification(criteria.getEducationalModuleId(),
                    root -> root.join(EducationalCenter_.educationalModules, JoinType.LEFT).get(EducationalModule_.id)));
            }
            if (criteria.getRequestEducationalModuleId() != null) {
                specification = specification.and(buildSpecification(criteria.getRequestEducationalModuleId(),
                    root -> root.join(EducationalCenter_.requestEducationalModules, JoinType.LEFT).get(RequestEducationalModule_.id)));
            }
        }
        return specification;
    }
}
