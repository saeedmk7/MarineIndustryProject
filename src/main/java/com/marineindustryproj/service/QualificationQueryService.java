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

import com.marineindustryproj.domain.Qualification;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.QualificationRepository;
import com.marineindustryproj.service.dto.QualificationCriteria;
import com.marineindustryproj.service.dto.QualificationDTO;
import com.marineindustryproj.service.mapper.QualificationMapper;

/**
 * Service for executing complex queries for Qualification entities in the database.
 * The main input is a {@link QualificationCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link QualificationDTO} or a {@link Page} of {@link QualificationDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class QualificationQueryService extends QueryService<Qualification> {

    private final Logger log = LoggerFactory.getLogger(QualificationQueryService.class);

    private final QualificationRepository qualificationRepository;

    private final QualificationMapper qualificationMapper;

    public QualificationQueryService(QualificationRepository qualificationRepository, QualificationMapper qualificationMapper) {
        this.qualificationRepository = qualificationRepository;
        this.qualificationMapper = qualificationMapper;
    }

    /**
     * Return a {@link List} of {@link QualificationDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<QualificationDTO> findByCriteria(QualificationCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Qualification> specification = createSpecification(criteria);
        return qualificationMapper.toDto(qualificationRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link QualificationDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<QualificationDTO> findByCriteria(QualificationCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Qualification> specification = createSpecification(criteria);
        return qualificationRepository.findAll(specification, page)
            .map(qualificationMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(QualificationCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Qualification> specification = createSpecification(criteria);
        return qualificationRepository.count(specification);
    }

    /**
     * Function to convert QualificationCriteria to a {@link Specification}
     */
    private Specification<Qualification> createSpecification(QualificationCriteria criteria) {
        Specification<Qualification> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Qualification_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), Qualification_.title));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), Qualification_.code));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), Qualification_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), Qualification_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), Qualification_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), Qualification_.modifyDate));
            }
            if (criteria.getPersonId() != null) {
                specification = specification.and(buildSpecification(criteria.getPersonId(),
                    root -> root.join(Qualification_.people, JoinType.LEFT).get(Person_.id)));
            }
            if (criteria.getTeacherId() != null) {
                specification = specification.and(buildSpecification(criteria.getTeacherId(),
                    root -> root.join(Qualification_.teachers, JoinType.LEFT).get(Teacher_.id)));
            }
        }
        return specification;
    }
}
