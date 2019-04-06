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

import com.marineindustryproj.domain.DesignAndPlanning;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.DesignAndPlanningRepository;
import com.marineindustryproj.service.dto.DesignAndPlanningCriteria;
import com.marineindustryproj.service.dto.DesignAndPlanningDTO;
import com.marineindustryproj.service.mapper.DesignAndPlanningMapper;

/**
 * Service for executing complex queries for DesignAndPlanning entities in the database.
 * The main input is a {@link DesignAndPlanningCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DesignAndPlanningDTO} or a {@link Page} of {@link DesignAndPlanningDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DesignAndPlanningQueryService extends QueryService<DesignAndPlanning> {

    private final Logger log = LoggerFactory.getLogger(DesignAndPlanningQueryService.class);

    private final DesignAndPlanningRepository designAndPlanningRepository;

    private final DesignAndPlanningMapper designAndPlanningMapper;

    public DesignAndPlanningQueryService(DesignAndPlanningRepository designAndPlanningRepository, DesignAndPlanningMapper designAndPlanningMapper) {
        this.designAndPlanningRepository = designAndPlanningRepository;
        this.designAndPlanningMapper = designAndPlanningMapper;
    }

    /**
     * Return a {@link List} of {@link DesignAndPlanningDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DesignAndPlanningDTO> findByCriteria(DesignAndPlanningCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<DesignAndPlanning> specification = createSpecification(criteria);
        return designAndPlanningMapper.toDto(designAndPlanningRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DesignAndPlanningDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DesignAndPlanningDTO> findByCriteria(DesignAndPlanningCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DesignAndPlanning> specification = createSpecification(criteria);
        return designAndPlanningRepository.findAll(specification, page)
            .map(designAndPlanningMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DesignAndPlanningCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<DesignAndPlanning> specification = createSpecification(criteria);
        return designAndPlanningRepository.count(specification);
    }

    /**
     * Function to convert DesignAndPlanningCriteria to a {@link Specification}
     */
    private Specification<DesignAndPlanning> createSpecification(DesignAndPlanningCriteria criteria) {
        Specification<DesignAndPlanning> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), DesignAndPlanning_.id));
            }
            if (criteria.getDirectCost() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDirectCost(), DesignAndPlanning_.directCost));
            }
            if (criteria.getDirectCostDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDirectCostDescription(), DesignAndPlanning_.directCostDescription));
            }
            if (criteria.getUndirectCost() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getUndirectCost(), DesignAndPlanning_.undirectCost));
            }
            if (criteria.getUndirectCostDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getUndirectCostDescription(), DesignAndPlanning_.undirectCostDescription));
            }
            if (criteria.getStep() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStep(), DesignAndPlanning_.step));
            }
            if (criteria.getFinished() != null) {
                specification = specification.and(buildSpecification(criteria.getFinished(), DesignAndPlanning_.finished));
            }
            if (criteria.getFinishedUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFinishedUserLogin(), DesignAndPlanning_.finishedUserLogin));
            }
            if (criteria.getFinishedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getFinishedDate(), DesignAndPlanning_.finishedDate));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), DesignAndPlanning_.description));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), DesignAndPlanning_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), DesignAndPlanning_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), DesignAndPlanning_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), DesignAndPlanning_.modifyDate));
            }
            if (criteria.getArchived() != null) {
                specification = specification.and(buildSpecification(criteria.getArchived(), DesignAndPlanning_.archived));
            }
            if (criteria.getArchivedUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getArchivedUserLogin(), DesignAndPlanning_.archivedUserLogin));
            }
            if (criteria.getArchivedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getArchivedDate(), DesignAndPlanning_.archivedDate));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStatus(), DesignAndPlanning_.status));
            }
            if (criteria.getPersonId() != null) {
                specification = specification.and(buildSpecification(criteria.getPersonId(),
                    root -> root.join(DesignAndPlanning_.people, JoinType.LEFT).get(Person_.id)));
            }
            if (criteria.getDocumentId() != null) {
                specification = specification.and(buildSpecification(criteria.getDocumentId(),
                    root -> root.join(DesignAndPlanning_.documents, JoinType.LEFT).get(Document_.id)));
            }
            if (criteria.getFinalNiazsanjiReportId() != null) {
                specification = specification.and(buildSpecification(criteria.getFinalNiazsanjiReportId(),
                    root -> root.join(DesignAndPlanning_.finalNiazsanjiReport, JoinType.LEFT).get(FinalNiazsanjiReport_.id)));
            }
            if (criteria.getMahiatCourseId() != null) {
                specification = specification.and(buildSpecification(criteria.getMahiatCourseId(),
                    root -> root.join(DesignAndPlanning_.mahiatCourse, JoinType.LEFT).get(MahiatCourse_.id)));
            }
            if (criteria.getCourseTypeId() != null) {
                specification = specification.and(buildSpecification(criteria.getCourseTypeId(),
                    root -> root.join(DesignAndPlanning_.courseType, JoinType.LEFT).get(CourseType_.id)));
            }
            if (criteria.getTeachTypeId() != null) {
                specification = specification.and(buildSpecification(criteria.getTeachTypeId(),
                    root -> root.join(DesignAndPlanning_.teachType, JoinType.LEFT).get(TeachType_.id)));
            }
            if (criteria.getCourseLocationId() != null) {
                specification = specification.and(buildSpecification(criteria.getCourseLocationId(),
                    root -> root.join(DesignAndPlanning_.courseLocation, JoinType.LEFT).get(CourseLocation_.id)));
            }
            if (criteria.getConditionsOfParticipantId() != null) {
                specification = specification.and(buildSpecification(criteria.getConditionsOfParticipantId(),
                    root -> root.join(DesignAndPlanning_.conditionsOfParticipant, JoinType.LEFT).get(ConditionsOfParticipant_.id)));
            }
            if (criteria.getEffectivenessLevelId() != null) {
                specification = specification.and(buildSpecification(criteria.getEffectivenessLevelId(),
                    root -> root.join(DesignAndPlanning_.effectivenessLevel, JoinType.LEFT).get(EffectivenessLevel_.id)));
            }
            if (criteria.getToolsAndFacilityId() != null) {
                specification = specification.and(buildSpecification(criteria.getToolsAndFacilityId(),
                    root -> root.join(DesignAndPlanning_.toolsAndFacility, JoinType.LEFT).get(ToolsAndFacility_.id)));
            }
            if (criteria.getTeachingApproachId() != null) {
                specification = specification.and(buildSpecification(criteria.getTeachingApproachId(),
                    root -> root.join(DesignAndPlanning_.teachingApproach, JoinType.LEFT).get(TeachingApproach_.id)));
            }
            if (criteria.getTeachTechniqueId() != null) {
                specification = specification.and(buildSpecification(criteria.getTeachTechniqueId(),
                    root -> root.join(DesignAndPlanning_.teachTechnique, JoinType.LEFT).get(TeachTechnique_.id)));
            }
            if (criteria.getEffectivenessIndexId() != null) {
                specification = specification.and(buildSpecification(criteria.getEffectivenessIndexId(),
                    root -> root.join(DesignAndPlanning_.effectivenessIndex, JoinType.LEFT).get(EffectivenessIndex_.id)));
            }
        }
        return specification;
    }
}
