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

import com.marineindustryproj.domain.Competency;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.CompetencyRepository;
import com.marineindustryproj.service.dto.CompetencyCriteria;
import com.marineindustryproj.service.dto.CompetencyDTO;
import com.marineindustryproj.service.mapper.CompetencyMapper;

/**
 * Service for executing complex queries for Competency entities in the database.
 * The main input is a {@link CompetencyCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CompetencyDTO} or a {@link Page} of {@link CompetencyDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CompetencyQueryService extends QueryService<Competency> {

    private final Logger log = LoggerFactory.getLogger(CompetencyQueryService.class);

    private final CompetencyRepository competencyRepository;

    private final CompetencyMapper competencyMapper;

    public CompetencyQueryService(CompetencyRepository competencyRepository, CompetencyMapper competencyMapper) {
        this.competencyRepository = competencyRepository;
        this.competencyMapper = competencyMapper;
    }

    /**
     * Return a {@link List} of {@link CompetencyDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CompetencyDTO> findByCriteria(CompetencyCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Competency> specification = createSpecification(criteria);
        return competencyMapper.toDto(competencyRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link CompetencyDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CompetencyDTO> findByCriteria(CompetencyCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Competency> specification = createSpecification(criteria);
        return competencyRepository.findAll(specification, page)
            .map(competencyMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CompetencyCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Competency> specification = createSpecification(criteria);
        return competencyRepository.count(specification);
    }

    /**
     * Function to convert CompetencyCriteria to a {@link Specification}
     */
    private Specification<Competency> createSpecification(CompetencyCriteria criteria) {
        Specification<Competency> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Competency_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), Competency_.title));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), Competency_.description));
            }
            if (criteria.getDisplayOrder() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDisplayOrder(), Competency_.displayOrder));
            }
            if (criteria.getCompetencyType() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCompetencyType(), Competency_.competencyType));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), Competency_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), Competency_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), Competency_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), Competency_.modifyDate));
            }
            if (criteria.getPreJobNiazsanjiCompetencyId() != null) {
                specification = specification.and(buildSpecification(criteria.getPreJobNiazsanjiCompetencyId(),
                    root -> root.join(Competency_.preJobNiazsanjiCompetencies, JoinType.LEFT).get(PreJobNiazsanjiCompetency_.id)));
            }
        }
        return specification;
    }
}
