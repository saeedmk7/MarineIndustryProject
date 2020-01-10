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

import com.marineindustryproj.domain.PreJobNiazsanjiCompetency;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.PreJobNiazsanjiCompetencyRepository;
import com.marineindustryproj.service.dto.PreJobNiazsanjiCompetencyCriteria;
import com.marineindustryproj.service.dto.PreJobNiazsanjiCompetencyDTO;
import com.marineindustryproj.service.mapper.PreJobNiazsanjiCompetencyMapper;

/**
 * Service for executing complex queries for PreJobNiazsanjiCompetency entities in the database.
 * The main input is a {@link PreJobNiazsanjiCompetencyCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PreJobNiazsanjiCompetencyDTO} or a {@link Page} of {@link PreJobNiazsanjiCompetencyDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PreJobNiazsanjiCompetencyQueryService extends QueryService<PreJobNiazsanjiCompetency> {

    private final Logger log = LoggerFactory.getLogger(PreJobNiazsanjiCompetencyQueryService.class);

    private final PreJobNiazsanjiCompetencyRepository preJobNiazsanjiCompetencyRepository;

    private final PreJobNiazsanjiCompetencyMapper preJobNiazsanjiCompetencyMapper;

    public PreJobNiazsanjiCompetencyQueryService(PreJobNiazsanjiCompetencyRepository preJobNiazsanjiCompetencyRepository, PreJobNiazsanjiCompetencyMapper preJobNiazsanjiCompetencyMapper) {
        this.preJobNiazsanjiCompetencyRepository = preJobNiazsanjiCompetencyRepository;
        this.preJobNiazsanjiCompetencyMapper = preJobNiazsanjiCompetencyMapper;
    }

    /**
     * Return a {@link List} of {@link PreJobNiazsanjiCompetencyDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<PreJobNiazsanjiCompetencyDTO> findByCriteria(PreJobNiazsanjiCompetencyCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<PreJobNiazsanjiCompetency> specification = createSpecification(criteria);
        return preJobNiazsanjiCompetencyMapper.toDto(preJobNiazsanjiCompetencyRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link PreJobNiazsanjiCompetencyDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<PreJobNiazsanjiCompetencyDTO> findByCriteria(PreJobNiazsanjiCompetencyCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<PreJobNiazsanjiCompetency> specification = createSpecification(criteria);
        return preJobNiazsanjiCompetencyRepository.findAll(specification, page)
            .map(preJobNiazsanjiCompetencyMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(PreJobNiazsanjiCompetencyCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<PreJobNiazsanjiCompetency> specification = createSpecification(criteria);
        return preJobNiazsanjiCompetencyRepository.count(specification);
    }

    /**
     * Function to convert PreJobNiazsanjiCompetencyCriteria to a {@link Specification}
     */
    private Specification<PreJobNiazsanjiCompetency> createSpecification(PreJobNiazsanjiCompetencyCriteria criteria) {
        Specification<PreJobNiazsanjiCompetency> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), PreJobNiazsanjiCompetency_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), PreJobNiazsanjiCompetency_.title));
            }
            if (criteria.getNeedToImprove() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNeedToImprove(), PreJobNiazsanjiCompetency_.needToImprove));
            }
            if (criteria.getNeedToImproveDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNeedToImproveDescription(), PreJobNiazsanjiCompetency_.needToImproveDescription));
            }
            if (criteria.getFixCompetencyDeficiencyDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFixCompetencyDeficiencyDescription(), PreJobNiazsanjiCompetency_.fixCompetencyDeficiencyDescription));
            }
            if (criteria.getEducationalModuleText() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEducationalModuleText(), PreJobNiazsanjiCompetency_.educationalModuleText));
            }
            if (criteria.getSumScore() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSumScore(), PreJobNiazsanjiCompetency_.sumScore));
            }
            if (criteria.getPriority() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPriority(), PreJobNiazsanjiCompetency_.priority));
            }
            if (criteria.getSelected() != null) {
                specification = specification.and(buildSpecification(criteria.getSelected(), PreJobNiazsanjiCompetency_.selected));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), PreJobNiazsanjiCompetency_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), PreJobNiazsanjiCompetency_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), PreJobNiazsanjiCompetency_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), PreJobNiazsanjiCompetency_.modifyDate));
            }
            if (criteria.getPriorityCriteriaValueId() != null) {
                specification = specification.and(buildSpecification(criteria.getPriorityCriteriaValueId(),
                    root -> root.join(PreJobNiazsanjiCompetency_.priorityCriteriaValues, JoinType.LEFT).get(PriorityCriteriaValue_.id)));
            }
            if (criteria.getTeachingApproachId() != null) {
                specification = specification.and(buildSpecification(criteria.getTeachingApproachId(),
                    root -> root.join(PreJobNiazsanjiCompetency_.teachingApproaches, JoinType.LEFT).get(TeachingApproach_.id)));
            }
            if (criteria.getFixCompetencyDeficiencyId() != null) {
                specification = specification.and(buildSpecification(criteria.getFixCompetencyDeficiencyId(),
                    root -> root.join(PreJobNiazsanjiCompetency_.fixCompetencyDeficiency, JoinType.LEFT).get(FixCompetencyDeficiency_.id)));
            }
            if (criteria.getEducationalModuleId() != null) {
                specification = specification.and(buildSpecification(criteria.getEducationalModuleId(),
                    root -> root.join(PreJobNiazsanjiCompetency_.educationalModule, JoinType.LEFT).get(EducationalModule_.id)));
            }
            if (criteria.getPreJobNiazsanjiId() != null) {
                specification = specification.and(buildSpecification(criteria.getPreJobNiazsanjiId(),
                    root -> root.join(PreJobNiazsanjiCompetency_.preJobNiazsanji, JoinType.LEFT).get(PreJobNiazsanji_.id)));
            }
            if (criteria.getCompetencyId() != null) {
                specification = specification.and(buildSpecification(criteria.getCompetencyId(),
                    root -> root.join(PreJobNiazsanjiCompetency_.competency, JoinType.LEFT).get(Competency_.id)));
            }
        }
        return specification;
    }
}
