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

import com.marineindustryproj.domain.FinalOrganizationNiazsanji;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.FinalOrganizationNiazsanjiRepository;
import com.marineindustryproj.service.dto.FinalOrganizationNiazsanjiCriteria;
import com.marineindustryproj.service.dto.FinalOrganizationNiazsanjiDTO;
import com.marineindustryproj.service.mapper.FinalOrganizationNiazsanjiMapper;

/**
 * Service for executing complex queries for FinalOrganizationNiazsanji entities in the database.
 * The main input is a {@link FinalOrganizationNiazsanjiCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link FinalOrganizationNiazsanjiDTO} or a {@link Page} of {@link FinalOrganizationNiazsanjiDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class FinalOrganizationNiazsanjiQueryService extends QueryService<FinalOrganizationNiazsanji> {

    private final Logger log = LoggerFactory.getLogger(FinalOrganizationNiazsanjiQueryService.class);

    private final FinalOrganizationNiazsanjiRepository finalOrganizationNiazsanjiRepository;

    private final FinalOrganizationNiazsanjiMapper finalOrganizationNiazsanjiMapper;

    public FinalOrganizationNiazsanjiQueryService(FinalOrganizationNiazsanjiRepository finalOrganizationNiazsanjiRepository, FinalOrganizationNiazsanjiMapper finalOrganizationNiazsanjiMapper) {
        this.finalOrganizationNiazsanjiRepository = finalOrganizationNiazsanjiRepository;
        this.finalOrganizationNiazsanjiMapper = finalOrganizationNiazsanjiMapper;
    }

    /**
     * Return a {@link List} of {@link FinalOrganizationNiazsanjiDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<FinalOrganizationNiazsanjiDTO> findByCriteria(FinalOrganizationNiazsanjiCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<FinalOrganizationNiazsanji> specification = createSpecification(criteria);
        return finalOrganizationNiazsanjiMapper.toDto(finalOrganizationNiazsanjiRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link FinalOrganizationNiazsanjiDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<FinalOrganizationNiazsanjiDTO> findByCriteria(FinalOrganizationNiazsanjiCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<FinalOrganizationNiazsanji> specification = createSpecification(criteria);
        return finalOrganizationNiazsanjiRepository.findAll(specification, page)
            .map(finalOrganizationNiazsanjiMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(FinalOrganizationNiazsanjiCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<FinalOrganizationNiazsanji> specification = createSpecification(criteria);
        return finalOrganizationNiazsanjiRepository.count(specification);
    }

    /**
     * Function to convert FinalOrganizationNiazsanjiCriteria to a {@link Specification}
     */
    private Specification<FinalOrganizationNiazsanji> createSpecification(FinalOrganizationNiazsanjiCriteria criteria) {
        Specification<FinalOrganizationNiazsanji> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), FinalOrganizationNiazsanji_.id));
            }
            if (criteria.getNiazsanjiYear() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNiazsanjiYear(), FinalOrganizationNiazsanji_.niazsanjiYear));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), FinalOrganizationNiazsanji_.code));
            }
            if (criteria.getRecommendedByOrgchart() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRecommendedByOrgchart(), FinalOrganizationNiazsanji_.recommendedByOrgchart));
            }
            if (criteria.getNeededSoftwares() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNeededSoftwares(), FinalOrganizationNiazsanji_.neededSoftwares));
            }
            if (criteria.getNeededHardware() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNeededHardware(), FinalOrganizationNiazsanji_.neededHardware));
            }
            if (criteria.getStudentsType() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStudentsType(), FinalOrganizationNiazsanji_.studentsType));
            }
            if (criteria.getTeacherName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTeacherName(), FinalOrganizationNiazsanji_.teacherName));
            }
            if (criteria.getTeacherMobile() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTeacherMobile(), FinalOrganizationNiazsanji_.teacherMobile));
            }
            if (criteria.getRequestStatus() != null) {
                specification = specification.and(buildSpecification(criteria.getRequestStatus(), FinalOrganizationNiazsanji_.requestStatus));
            }
            if (criteria.getChangeStatusUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getChangeStatusUserLogin(), FinalOrganizationNiazsanji_.changeStatusUserLogin));
            }
            if (criteria.getTrainingGoals() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTrainingGoals(), FinalOrganizationNiazsanji_.trainingGoals));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), FinalOrganizationNiazsanji_.description));
            }
            if (criteria.getPriceCost() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPriceCost(), FinalOrganizationNiazsanji_.priceCost));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), FinalOrganizationNiazsanji_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), FinalOrganizationNiazsanji_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), FinalOrganizationNiazsanji_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), FinalOrganizationNiazsanji_.modifyDate));
            }
            if (criteria.getArchived() != null) {
                specification = specification.and(buildSpecification(criteria.getArchived(), FinalOrganizationNiazsanji_.archived));
            }
            if (criteria.getArchivedUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getArchivedUserLogin(), FinalOrganizationNiazsanji_.archivedUserLogin));
            }
            if (criteria.getArchivedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getArchivedDate(), FinalOrganizationNiazsanji_.archivedDate));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStatus(), FinalOrganizationNiazsanji_.status));
            }
            if (criteria.getGuid() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGuid(), FinalOrganizationNiazsanji_.guid));
            }
            if (criteria.getPersonId() != null) {
                specification = specification.and(buildSpecification(criteria.getPersonId(),
                    root -> root.join(FinalOrganizationNiazsanji_.people, JoinType.LEFT).get(Person_.id)));
            }
            if (criteria.getDocumentId() != null) {
                specification = specification.and(buildSpecification(criteria.getDocumentId(),
                    root -> root.join(FinalOrganizationNiazsanji_.documents, JoinType.LEFT).get(Document_.id)));
            }
            if (criteria.getCourseTypeId() != null) {
                specification = specification.and(buildSpecification(criteria.getCourseTypeId(),
                    root -> root.join(FinalOrganizationNiazsanji_.courseType, JoinType.LEFT).get(CourseType_.id)));
            }
            if (criteria.getOrganizationChartId() != null) {
                specification = specification.and(buildSpecification(criteria.getOrganizationChartId(),
                    root -> root.join(FinalOrganizationNiazsanji_.organizationChart, JoinType.LEFT).get(OrganizationChart_.id)));
            }
            if (criteria.getTeacherId() != null) {
                specification = specification.and(buildSpecification(criteria.getTeacherId(),
                    root -> root.join(FinalOrganizationNiazsanji_.teacher, JoinType.LEFT).get(Teacher_.id)));
            }
            if (criteria.getEducationalModuleId() != null) {
                specification = specification.and(buildSpecification(criteria.getEducationalModuleId(),
                    root -> root.join(FinalOrganizationNiazsanji_.educationalModule, JoinType.LEFT).get(EducationalModule_.id)));
            }
            if (criteria.getEducationalModuleTitle() != null) {
                specification = specification.and(buildSpecification(criteria.getEducationalModuleTitle(),
                    root -> root.join(FinalOrganizationNiazsanji_.educationalModule, JoinType.LEFT).get(EducationalModule_.title)));
            }
            if (criteria.getSkillableLevelOfSkillId() != null) {
                specification = specification.and(buildSpecification(criteria.getSkillableLevelOfSkillId(),
                                                                     root -> root.join(FinalOrganizationNiazsanji_.educationalModule, JoinType.LEFT)
                                                                         .join(EducationalModule_.skillableLevelOfSkill, JoinType.LEFT)
                                                                         .get(SkillableLevelOfSkill_.id)));
            }
            if (criteria.getTeachApproachId() != null) {
                specification = specification.and(buildSpecification(criteria.getTeachApproachId(),
                    root -> root.join(FinalOrganizationNiazsanji_.teachApproach, JoinType.LEFT).get(TeachApproach_.id)));
            }
            if (criteria.getRequestOrganizationNiazsanjiId() != null) {
                specification = specification.and(buildSpecification(criteria.getRequestOrganizationNiazsanjiId(),
                    root -> root.join(FinalOrganizationNiazsanji_.requestOrganizationNiazsanji, JoinType.LEFT).get(RequestOrganizationNiazsanji_.id)));
            }
        }
        return specification;
    }
}
