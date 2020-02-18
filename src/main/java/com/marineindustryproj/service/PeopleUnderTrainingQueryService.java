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

import com.marineindustryproj.domain.PeopleUnderTraining;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.PeopleUnderTrainingRepository;
import com.marineindustryproj.service.dto.PeopleUnderTrainingCriteria;
import com.marineindustryproj.service.dto.PeopleUnderTrainingDTO;
import com.marineindustryproj.service.mapper.PeopleUnderTrainingMapper;

/**
 * Service for executing complex queries for PeopleUnderTraining entities in the database.
 * The main input is a {@link PeopleUnderTrainingCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PeopleUnderTrainingDTO} or a {@link Page} of {@link PeopleUnderTrainingDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PeopleUnderTrainingQueryService extends QueryService<PeopleUnderTraining> {

    private final Logger log = LoggerFactory.getLogger(PeopleUnderTrainingQueryService.class);

    private final PeopleUnderTrainingRepository peopleUnderTrainingRepository;

    private final PeopleUnderTrainingMapper peopleUnderTrainingMapper;

    public PeopleUnderTrainingQueryService(PeopleUnderTrainingRepository peopleUnderTrainingRepository, PeopleUnderTrainingMapper peopleUnderTrainingMapper) {
        this.peopleUnderTrainingRepository = peopleUnderTrainingRepository;
        this.peopleUnderTrainingMapper = peopleUnderTrainingMapper;
    }

    /**
     * Return a {@link List} of {@link PeopleUnderTrainingDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<PeopleUnderTrainingDTO> findByCriteria(PeopleUnderTrainingCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<PeopleUnderTraining> specification = createSpecification(criteria);
        return peopleUnderTrainingMapper.toDto(peopleUnderTrainingRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link PeopleUnderTrainingDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<PeopleUnderTrainingDTO> findByCriteria(PeopleUnderTrainingCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<PeopleUnderTraining> specification = createSpecification(criteria);
        return peopleUnderTrainingRepository.findAll(specification, page)
            .map(peopleUnderTrainingMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(PeopleUnderTrainingCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<PeopleUnderTraining> specification = createSpecification(criteria);
        return peopleUnderTrainingRepository.count(specification);
    }

    /**
     * Function to convert PeopleUnderTrainingCriteria to a {@link Specification}
     */
    private Specification<PeopleUnderTraining> createSpecification(PeopleUnderTrainingCriteria criteria) {
        Specification<PeopleUnderTraining> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), PeopleUnderTraining_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), PeopleUnderTraining_.title));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), PeopleUnderTraining_.description));
            }
            if (criteria.getPeopleCount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPeopleCount(), PeopleUnderTraining_.peopleCount));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), PeopleUnderTraining_.code));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), PeopleUnderTraining_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), PeopleUnderTraining_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), PeopleUnderTraining_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), PeopleUnderTraining_.modifyDate));
            }
            if (criteria.getEducationalModuleId() != null) {
                specification = specification.and(buildSpecification(criteria.getEducationalModuleId(),
                    root -> root.join(PeopleUnderTraining_.educationalModules, JoinType.LEFT).get(EducationalModule_.id)));
            }
            if (criteria.getRequestEducationalModuleId() != null) {
                specification = specification.and(buildSpecification(criteria.getRequestEducationalModuleId(),
                    root -> root.join(PeopleUnderTraining_.requestEducationalModules, JoinType.LEFT).get(RequestEducationalModule_.id)));
            }
        }
        return specification;
    }
}
