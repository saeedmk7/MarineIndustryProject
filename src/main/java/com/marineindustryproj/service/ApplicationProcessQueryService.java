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

import com.marineindustryproj.domain.ApplicationProcess;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.ApplicationProcessRepository;
import com.marineindustryproj.service.dto.ApplicationProcessCriteria;
import com.marineindustryproj.service.dto.ApplicationProcessDTO;
import com.marineindustryproj.service.mapper.ApplicationProcessMapper;

/**
 * Service for executing complex queries for ApplicationProcess entities in the database.
 * The main input is a {@link ApplicationProcessCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ApplicationProcessDTO} or a {@link Page} of {@link ApplicationProcessDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ApplicationProcessQueryService extends QueryService<ApplicationProcess> {

    private final Logger log = LoggerFactory.getLogger(ApplicationProcessQueryService.class);

    private final ApplicationProcessRepository applicationProcessRepository;

    private final ApplicationProcessMapper applicationProcessMapper;

    public ApplicationProcessQueryService(ApplicationProcessRepository applicationProcessRepository, ApplicationProcessMapper applicationProcessMapper) {
        this.applicationProcessRepository = applicationProcessRepository;
        this.applicationProcessMapper = applicationProcessMapper;
    }

    /**
     * Return a {@link List} of {@link ApplicationProcessDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ApplicationProcessDTO> findByCriteria(ApplicationProcessCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ApplicationProcess> specification = createSpecification(criteria);
        return applicationProcessMapper.toDto(applicationProcessRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ApplicationProcessDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ApplicationProcessDTO> findByCriteria(ApplicationProcessCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ApplicationProcess> specification = createSpecification(criteria);
        return applicationProcessRepository.findAll(specification, page)
            .map(applicationProcessMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ApplicationProcessCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ApplicationProcess> specification = createSpecification(criteria);
        return applicationProcessRepository.count(specification);
    }

    /**
     * Function to convert ApplicationProcessCriteria to a {@link Specification}
     */
    private Specification<ApplicationProcess> createSpecification(ApplicationProcessCriteria criteria) {
        Specification<ApplicationProcess> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ApplicationProcess_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), ApplicationProcess_.code));
            }
            if (criteria.getJobAfterProcess() != null) {
                specification = specification.and(buildStringSpecification(criteria.getJobAfterProcess(), ApplicationProcess_.jobAfterProcess));
            }
            if (criteria.getAcceptedUniversityAndDegree() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAcceptedUniversityAndDegree(), ApplicationProcess_.acceptedUniversityAndDegree));
            }
            if (criteria.getStartStudyDate() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStartStudyDate(), ApplicationProcess_.startStudyDate));
            }
            if (criteria.getGraduateDateOfNewCourse() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGraduateDateOfNewCourse(), ApplicationProcess_.graduateDateOfNewCourse));
            }
            if (criteria.getAveragePointOfNewCourse() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAveragePointOfNewCourse(), ApplicationProcess_.averagePointOfNewCourse));
            }
            if (criteria.getAcceptedMajorAndField() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAcceptedMajorAndField(), ApplicationProcess_.acceptedMajorAndField));
            }
            if (criteria.getHaveUsedEducationalFacilities() != null) {
                specification = specification.and(buildSpecification(criteria.getHaveUsedEducationalFacilities(), ApplicationProcess_.haveUsedEducationalFacilities));
            }
            if (criteria.getHaveUsedEducationalFacilitiesDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getHaveUsedEducationalFacilitiesDescription(), ApplicationProcess_.haveUsedEducationalFacilitiesDescription));
            }
            if (criteria.getDateOfAcceptanceOfDegree() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDateOfAcceptanceOfDegree(), ApplicationProcess_.dateOfAcceptanceOfDegree));
            }
            if (criteria.getTypeAndAmountOfFacilities() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTypeAndAmountOfFacilities(), ApplicationProcess_.typeAndAmountOfFacilities));
            }
            if (criteria.getAcademicCommitmentsFulfilled() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAcademicCommitmentsFulfilled(), ApplicationProcess_.academicCommitmentsFulfilled));
            }
            if (criteria.getRemainingAcademicCommitments() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRemainingAcademicCommitments(), ApplicationProcess_.remainingAcademicCommitments));
            }
            if (criteria.getRequestedFacilitiesText() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRequestedFacilitiesText(), ApplicationProcess_.requestedFacilitiesText));
            }
            if (criteria.getRequestedFacilitiesDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRequestedFacilitiesDescription(), ApplicationProcess_.requestedFacilitiesDescription));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), ApplicationProcess_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), ApplicationProcess_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), ApplicationProcess_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), ApplicationProcess_.modifyDate));
            }
            if (criteria.getArchived() != null) {
                specification = specification.and(buildSpecification(criteria.getArchived(), ApplicationProcess_.archived));
            }
            if (criteria.getArchivedUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getArchivedUserLogin(), ApplicationProcess_.archivedUserLogin));
            }
            if (criteria.getArchivedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getArchivedDate(), ApplicationProcess_.archivedDate));
            }
            if (criteria.getChartStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getChartStatus(), ApplicationProcess_.chartStatus));
            }
            if (criteria.getBossStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBossStatus(), ApplicationProcess_.bossStatus));
            }
            if (criteria.getRequestStatus() != null) {
                specification = specification.and(buildSpecification(criteria.getRequestStatus(), ApplicationProcess_.requestStatus));
            }
            if (criteria.getChangeStatusUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getChangeStatusUserLogin(), ApplicationProcess_.changeStatusUserLogin));
            }
            if (criteria.getGuid() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGuid(), ApplicationProcess_.guid));
            }
            if (criteria.getHasImportantMessage() != null) {
                specification = specification.and(buildSpecification(criteria.getHasImportantMessage(), ApplicationProcess_.hasImportantMessage));
            }
            if (criteria.getSelectedModuleIds() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSelectedModuleIds(), ApplicationProcess_.selectedModuleIds));
            }
            if (criteria.getApplicationProcessRunStepId() != null) {
                specification = specification.and(buildSpecification(criteria.getApplicationProcessRunStepId(),
                    root -> root.join(ApplicationProcess_.applicationProcessRunSteps, JoinType.LEFT).get(ApplicationProcessRunStep_.id)));
            }
            if (criteria.getDocumentId() != null) {
                specification = specification.and(buildSpecification(criteria.getDocumentId(),
                    root -> root.join(ApplicationProcess_.documents, JoinType.LEFT).get(Document_.id)));
            }
            if (criteria.getEducationalRecordId() != null) {
                specification = specification.and(buildSpecification(criteria.getEducationalRecordId(),
                    root -> root.join(ApplicationProcess_.educationalRecord, JoinType.LEFT).get(EducationalRecord_.id)));
            }
            if (criteria.getPersonId() != null) {
                specification = specification.and(buildSpecification(criteria.getPersonId(),
                    root -> root.join(ApplicationProcess_.person, JoinType.LEFT).get(Person_.id)));
            }
            if (criteria.getPersonEmploymentTypeId() != null) {
                specification = specification.and(buildSpecification(criteria.getPersonEmploymentTypeId(),
                    root -> root.join(ApplicationProcess_.person, JoinType.LEFT).join(Person_.employmentType).get(EmploymentType_.id)));
            }
            if (criteria.getOrganizationChartId() != null) {
                specification = specification.and(buildSpecification(criteria.getOrganizationChartId(),
                    root -> root.join(ApplicationProcess_.organizationChart, JoinType.LEFT).get(OrganizationChart_.id)));
            }
            if (criteria.getQualificationId() != null) {
                specification = specification.and(buildSpecification(criteria.getQualificationId(),
                    root -> root.join(ApplicationProcess_.qualification, JoinType.LEFT).get(Qualification_.id)));
            }
        }
        return specification;
    }
}
