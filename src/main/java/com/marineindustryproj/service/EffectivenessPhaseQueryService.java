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

import com.marineindustryproj.domain.EffectivenessPhase;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.EffectivenessPhaseRepository;
import com.marineindustryproj.service.dto.EffectivenessPhaseCriteria;
import com.marineindustryproj.service.dto.EffectivenessPhaseDTO;
import com.marineindustryproj.service.mapper.EffectivenessPhaseMapper;

/**
 * Service for executing complex queries for EffectivenessPhase entities in the database.
 * The main input is a {@link EffectivenessPhaseCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link EffectivenessPhaseDTO} or a {@link Page} of {@link EffectivenessPhaseDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class EffectivenessPhaseQueryService extends QueryService<EffectivenessPhase> {

    private final Logger log = LoggerFactory.getLogger(EffectivenessPhaseQueryService.class);

    private final EffectivenessPhaseRepository effectivenessPhaseRepository;

    private final EffectivenessPhaseMapper effectivenessPhaseMapper;

    public EffectivenessPhaseQueryService(EffectivenessPhaseRepository effectivenessPhaseRepository, EffectivenessPhaseMapper effectivenessPhaseMapper) {
        this.effectivenessPhaseRepository = effectivenessPhaseRepository;
        this.effectivenessPhaseMapper = effectivenessPhaseMapper;
    }

    /**
     * Return a {@link List} of {@link EffectivenessPhaseDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<EffectivenessPhaseDTO> findByCriteria(EffectivenessPhaseCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<EffectivenessPhase> specification = createSpecification(criteria);
        return effectivenessPhaseMapper.toDto(effectivenessPhaseRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link EffectivenessPhaseDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<EffectivenessPhaseDTO> findByCriteria(EffectivenessPhaseCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<EffectivenessPhase> specification = createSpecification(criteria);
        return effectivenessPhaseRepository.findAll(specification, page)
            .map(effectivenessPhaseMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(EffectivenessPhaseCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<EffectivenessPhase> specification = createSpecification(criteria);
        return effectivenessPhaseRepository.count(specification);
    }

    /**
     * Function to convert EffectivenessPhaseCriteria to a {@link Specification}
     */
    private Specification<EffectivenessPhase> createSpecification(EffectivenessPhaseCriteria criteria) {
        Specification<EffectivenessPhase> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), EffectivenessPhase_.id));
            }
            if (criteria.getFinishPhaseDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFinishPhaseDate(), EffectivenessPhase_.finishPhaseDate));
            }
            if (criteria.getStartPhaseDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStartPhaseDate(), EffectivenessPhase_.startPhaseDate));
            }
            if (criteria.getFirstScore() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFirstScore(), EffectivenessPhase_.firstScore));
            }
            if (criteria.getSecondScore() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSecondScore(), EffectivenessPhase_.secondScore));
            }
            if (criteria.getFinalScore() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFinalScore(), EffectivenessPhase_.finalScore));
            }
            if (criteria.getWeightedPoints() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getWeightedPoints(), EffectivenessPhase_.weightedPoints));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), EffectivenessPhase_.description));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), EffectivenessPhase_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), EffectivenessPhase_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), EffectivenessPhase_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), EffectivenessPhase_.modifyDate));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStatus(), EffectivenessPhase_.status));
            }
            if (criteria.getStartPhaseUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStartPhaseUserLogin(), EffectivenessPhase_.startPhaseUserLogin));
            }
            if (criteria.getFinishPhaseUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFinishPhaseUserLogin(), EffectivenessPhase_.finishPhaseUserLogin));
            }
            if (criteria.getDocumentId() != null) {
                specification = specification.and(buildSpecification(criteria.getDocumentId(),
                    root -> root.join(EffectivenessPhase_.documents, JoinType.LEFT).get(Document_.id)));
            }
            if (criteria.getFinalNiazsanjiReportId() != null) {
                specification = specification.and(buildSpecification(criteria.getFinalNiazsanjiReportId(),
                    root -> root.join(EffectivenessPhase_.finalNiazsanjiReport, JoinType.LEFT).get(FinalNiazsanjiReport_.id)));
            }
            if (criteria.getEffectivenessPhaseLevelId() != null) {
                specification = specification.and(buildSpecification(criteria.getEffectivenessPhaseLevelId(),
                    root -> root.join(EffectivenessPhase_.effectivenessPhaseLevel, JoinType.LEFT).get(EffectivenessPhaseLevel_.id)));
            }
            if (criteria.getOrganizationChartId() != null) {
                specification = specification.and(buildSpecification(criteria.getOrganizationChartId(),
                    root -> root.join(EffectivenessPhase_.finalNiazsanjiReport, JoinType.LEFT)
                        .join(FinalNiazsanjiReport_.organizationChart).get(OrganizationChart_.id)));
            }
            if (criteria.getCourseTypeId() != null) {
                specification = specification.and(buildSpecification(criteria.getCourseTypeId(),
                    root -> root.join(EffectivenessPhase_.finalNiazsanjiReport, JoinType.LEFT)
                        .join(FinalNiazsanjiReport_.courseType).get(CourseType_.id)));
            }
            if (criteria.getEducationalModuleCode() != null) {
                specification = specification.and(buildSpecification(criteria.getEducationalModuleCode(),
                    root -> root.join(EffectivenessPhase_.finalNiazsanjiReport, JoinType.LEFT)
                        .join(FinalNiazsanjiReport_.educationalModule).get(EducationalModule_.code)));
            }
            if (criteria.getEducationalModuleTitle() != null) {
                specification = specification.and(buildSpecification(criteria.getEducationalModuleTitle(),
                    root -> root.join(EffectivenessPhase_.finalNiazsanjiReport, JoinType.LEFT)
                        .join(FinalNiazsanjiReport_.educationalModule).get(EducationalModule_.title)));
            }
            if (criteria.getNiazsanjiYear() != null) {
                specification = specification.and(buildSpecification(criteria.getNiazsanjiYear(),
                    root -> root.join(EffectivenessPhase_.finalNiazsanjiReport, JoinType.LEFT).get(FinalNiazsanjiReport_.niazsanjiYear)));
            }
            if (criteria.getCurrentEffectivenessPhaseLevel() != null) {
                specification = specification.and(buildSpecification(criteria.getCurrentEffectivenessPhaseLevel(),
                    root -> root.join(EffectivenessPhase_.effectivenessPhaseLevel, JoinType.LEFT).get(EffectivenessPhaseLevel_.effectivenessLevel)));
            }
            if (criteria.getSelectedEffectivenessPhaseLevel() != null) {
                specification = specification.and(buildSpecification(criteria.getSelectedEffectivenessPhaseLevel(),
                    root -> root.join(EffectivenessPhase_.finalNiazsanjiReport, JoinType.LEFT).get(FinalNiazsanjiReport_.selectedEffectivenessPhaseLevel)));
            }
        }
        return specification;
    }
}
