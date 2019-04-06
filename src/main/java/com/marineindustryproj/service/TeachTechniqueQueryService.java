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

import com.marineindustryproj.domain.TeachTechnique;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.TeachTechniqueRepository;
import com.marineindustryproj.service.dto.TeachTechniqueCriteria;
import com.marineindustryproj.service.dto.TeachTechniqueDTO;
import com.marineindustryproj.service.mapper.TeachTechniqueMapper;

/**
 * Service for executing complex queries for TeachTechnique entities in the database.
 * The main input is a {@link TeachTechniqueCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link TeachTechniqueDTO} or a {@link Page} of {@link TeachTechniqueDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class TeachTechniqueQueryService extends QueryService<TeachTechnique> {

    private final Logger log = LoggerFactory.getLogger(TeachTechniqueQueryService.class);

    private final TeachTechniqueRepository teachTechniqueRepository;

    private final TeachTechniqueMapper teachTechniqueMapper;

    public TeachTechniqueQueryService(TeachTechniqueRepository teachTechniqueRepository, TeachTechniqueMapper teachTechniqueMapper) {
        this.teachTechniqueRepository = teachTechniqueRepository;
        this.teachTechniqueMapper = teachTechniqueMapper;
    }

    /**
     * Return a {@link List} of {@link TeachTechniqueDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<TeachTechniqueDTO> findByCriteria(TeachTechniqueCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<TeachTechnique> specification = createSpecification(criteria);
        return teachTechniqueMapper.toDto(teachTechniqueRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link TeachTechniqueDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<TeachTechniqueDTO> findByCriteria(TeachTechniqueCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<TeachTechnique> specification = createSpecification(criteria);
        return teachTechniqueRepository.findAll(specification, page)
            .map(teachTechniqueMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(TeachTechniqueCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<TeachTechnique> specification = createSpecification(criteria);
        return teachTechniqueRepository.count(specification);
    }

    /**
     * Function to convert TeachTechniqueCriteria to a {@link Specification}
     */
    private Specification<TeachTechnique> createSpecification(TeachTechniqueCriteria criteria) {
        Specification<TeachTechnique> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), TeachTechnique_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), TeachTechnique_.title));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), TeachTechnique_.code));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), TeachTechnique_.description));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), TeachTechnique_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), TeachTechnique_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), TeachTechnique_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), TeachTechnique_.modifyDate));
            }
            if (criteria.getDesignAndPlanningId() != null) {
                specification = specification.and(buildSpecification(criteria.getDesignAndPlanningId(),
                    root -> root.join(TeachTechnique_.designAndPlannings, JoinType.LEFT).get(DesignAndPlanning_.id)));
            }
        }
        return specification;
    }
}
