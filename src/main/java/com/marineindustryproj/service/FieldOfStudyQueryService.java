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

import com.marineindustryproj.domain.FieldOfStudy;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.FieldOfStudyRepository;
import com.marineindustryproj.service.dto.FieldOfStudyCriteria;
import com.marineindustryproj.service.dto.FieldOfStudyDTO;
import com.marineindustryproj.service.mapper.FieldOfStudyMapper;

/**
 * Service for executing complex queries for FieldOfStudy entities in the database.
 * The main input is a {@link FieldOfStudyCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link FieldOfStudyDTO} or a {@link Page} of {@link FieldOfStudyDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class FieldOfStudyQueryService extends QueryService<FieldOfStudy> {

    private final Logger log = LoggerFactory.getLogger(FieldOfStudyQueryService.class);

    private final FieldOfStudyRepository fieldOfStudyRepository;

    private final FieldOfStudyMapper fieldOfStudyMapper;

    public FieldOfStudyQueryService(FieldOfStudyRepository fieldOfStudyRepository, FieldOfStudyMapper fieldOfStudyMapper) {
        this.fieldOfStudyRepository = fieldOfStudyRepository;
        this.fieldOfStudyMapper = fieldOfStudyMapper;
    }

    /**
     * Return a {@link List} of {@link FieldOfStudyDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<FieldOfStudyDTO> findByCriteria(FieldOfStudyCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<FieldOfStudy> specification = createSpecification(criteria);
        return fieldOfStudyMapper.toDto(fieldOfStudyRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link FieldOfStudyDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<FieldOfStudyDTO> findByCriteria(FieldOfStudyCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<FieldOfStudy> specification = createSpecification(criteria);
        return fieldOfStudyRepository.findAll(specification, page)
            .map(fieldOfStudyMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(FieldOfStudyCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<FieldOfStudy> specification = createSpecification(criteria);
        return fieldOfStudyRepository.count(specification);
    }

    /**
     * Function to convert FieldOfStudyCriteria to a {@link Specification}
     */
    private Specification<FieldOfStudy> createSpecification(FieldOfStudyCriteria criteria) {
        Specification<FieldOfStudy> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), FieldOfStudy_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), FieldOfStudy_.title));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), FieldOfStudy_.code));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), FieldOfStudy_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), FieldOfStudy_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), FieldOfStudy_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), FieldOfStudy_.modifyDate));
            }
            if (criteria.getPersonId() != null) {
                specification = specification.and(buildSpecification(criteria.getPersonId(),
                    root -> root.join(FieldOfStudy_.people, JoinType.LEFT).get(Person_.id)));
            }
            if (criteria.getTeacherId() != null) {
                specification = specification.and(buildSpecification(criteria.getTeacherId(),
                    root -> root.join(FieldOfStudy_.teachers, JoinType.LEFT).get(Teacher_.id)));
            }
        }
        return specification;
    }
}
