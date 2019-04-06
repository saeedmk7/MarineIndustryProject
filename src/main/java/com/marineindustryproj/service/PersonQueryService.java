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

import com.marineindustryproj.domain.Person;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.PersonRepository;
import com.marineindustryproj.service.dto.PersonCriteria;
import com.marineindustryproj.service.dto.PersonDTO;
import com.marineindustryproj.service.mapper.PersonMapper;

/**
 * Service for executing complex queries for Person entities in the database.
 * The main input is a {@link PersonCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PersonDTO} or a {@link Page} of {@link PersonDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PersonQueryService extends QueryService<Person> {

    private final Logger log = LoggerFactory.getLogger(PersonQueryService.class);

    private final PersonRepository personRepository;

    private final PersonMapper personMapper;

    public PersonQueryService(PersonRepository personRepository, PersonMapper personMapper) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
    }

    /**
     * Return a {@link List} of {@link PersonDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<PersonDTO> findByCriteria(PersonCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Person> specification = createSpecification(criteria);
        return personMapper.toDto(personRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link PersonDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<PersonDTO> findByCriteria(PersonCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Person> specification = createSpecification(criteria);
        return personRepository.findAll(specification, page)
            .map(personMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(PersonCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Person> specification = createSpecification(criteria);
        return personRepository.count(specification);
    }

    /**
     * Function to convert PersonCriteria to a {@link Specification}
     */
    private Specification<Person> createSpecification(PersonCriteria criteria) {
        Specification<Person> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Person_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), Person_.name));
            }
            if (criteria.getFamily() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFamily(), Person_.family));
            }
            if (criteria.getFatherName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFatherName(), Person_.fatherName));
            }
            if (criteria.getCertificateNumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCertificateNumber(), Person_.certificateNumber));
            }
            if (criteria.getNationalId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNationalId(), Person_.nationalId));
            }
            if (criteria.getBirthDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBirthDate(), Person_.birthDate));
            }
            if (criteria.getPersonelCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPersonelCode(), Person_.personelCode));
            }
            if (criteria.getEmploymentDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEmploymentDate(), Person_.employmentDate));
            }
            if (criteria.getYearOfService() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getYearOfService(), Person_.yearOfService));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), Person_.code));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), Person_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), Person_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), Person_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), Person_.modifyDate));
            }
            if (criteria.getArchived() != null) {
                specification = specification.and(buildSpecification(criteria.getArchived(), Person_.archived));
            }
            if (criteria.getArchivedUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getArchivedUserLogin(), Person_.archivedUserLogin));
            }
            if (criteria.getArchivedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getArchivedDate(), Person_.archivedDate));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStatus(), Person_.status));
            }
            if (criteria.getFinalNiazsanjiReportPersonId() != null) {
                specification = specification.and(buildSpecification(criteria.getFinalNiazsanjiReportPersonId(),
                    root -> root.join(Person_.finalNiazsanjiReportPeople, JoinType.LEFT).get(FinalNiazsanjiReportPerson_.id)));
            }
            if (criteria.getPollScoreId() != null) {
                specification = specification.and(buildSpecification(criteria.getPollScoreId(),
                    root -> root.join(Person_.pollScores, JoinType.LEFT).get(PollScore_.id)));
            }
            if (criteria.getNiazsanjiFardiId() != null) {
                specification = specification.and(buildSpecification(criteria.getNiazsanjiFardiId(),
                    root -> root.join(Person_.niazsanjiFardis, JoinType.LEFT).get(NiazsanjiFardi_.id)));
            }
            if (criteria.getRequestNiazsanjiFardiId() != null) {
                specification = specification.and(buildSpecification(criteria.getRequestNiazsanjiFardiId(),
                    root -> root.join(Person_.requestNiazsanjiFardis, JoinType.LEFT).get(RequestNiazsanjiFardi_.id)));
            }
            if (criteria.getDocumentId() != null) {
                specification = specification.and(buildSpecification(criteria.getDocumentId(),
                    root -> root.join(Person_.documents, JoinType.LEFT).get(Document_.id)));
            }
            if (criteria.getScientificWorkGroupId() != null) {
                specification = specification.and(buildSpecification(criteria.getScientificWorkGroupId(),
                    root -> root.join(Person_.scientificWorkGroups, JoinType.LEFT).get(ScientificWorkGroup_.id)));
            }
            if (criteria.getLastQualificationId() != null) {
                specification = specification.and(buildSpecification(criteria.getLastQualificationId(),
                    root -> root.join(Person_.lastQualification, JoinType.LEFT).get(Qualification_.id)));
            }
            if (criteria.getLastFieldOfStudyId() != null) {
                specification = specification.and(buildSpecification(criteria.getLastFieldOfStudyId(),
                    root -> root.join(Person_.lastFieldOfStudy, JoinType.LEFT).get(FieldOfStudy_.id)));
            }
            if (criteria.getEmploymentTypeId() != null) {
                specification = specification.and(buildSpecification(criteria.getEmploymentTypeId(),
                    root -> root.join(Person_.employmentType, JoinType.LEFT).get(EmploymentType_.id)));
            }
            if (criteria.getWorkGroupId() != null) {
                specification = specification.and(buildSpecification(criteria.getWorkGroupId(),
                    root -> root.join(Person_.workGroup, JoinType.LEFT).get(WorkGroup_.id)));
            }
            if (criteria.getWorkIndustryId() != null) {
                specification = specification.and(buildSpecification(criteria.getWorkIndustryId(),
                    root -> root.join(Person_.workIndustry, JoinType.LEFT).get(WorkIndustry_.id)));
            }
            if (criteria.getJobId() != null) {
                specification = specification.and(buildSpecification(criteria.getJobId(),
                    root -> root.join(Person_.job, JoinType.LEFT).get(Job_.id)));
            }
            if (criteria.getPracticaljobId() != null) {
                specification = specification.and(buildSpecification(criteria.getPracticaljobId(),
                    root -> root.join(Person_.practicaljob, JoinType.LEFT).get(Job_.id)));
            }
            if (criteria.getOrganizationChartId() != null) {
                specification = specification.and(buildSpecification(criteria.getOrganizationChartId(),
                    root -> root.join(Person_.organizationChart, JoinType.LEFT).get(OrganizationChart_.id)));
            }
            if (criteria.getMainTaskId() != null) {
                specification = specification.and(buildSpecification(criteria.getMainTaskId(),
                    root -> root.join(Person_.mainTasks, JoinType.LEFT).get(MainTask_.id)));
            }
            if (criteria.getRequestOrganizationNiazsanjiId() != null) {
                specification = specification.and(buildSpecification(criteria.getRequestOrganizationNiazsanjiId(),
                    root -> root.join(Person_.requestOrganizationNiazsanjis, JoinType.LEFT).get(RequestOrganizationNiazsanji_.id)));
            }
            if (criteria.getFinalOrganizationNiazsanjiId() != null) {
                specification = specification.and(buildSpecification(criteria.getFinalOrganizationNiazsanjiId(),
                    root -> root.join(Person_.finalOrganizationNiazsanjis, JoinType.LEFT).get(FinalOrganizationNiazsanji_.id)));
            }
            if (criteria.getDesignAndPlanningId() != null) {
                specification = specification.and(buildSpecification(criteria.getDesignAndPlanningId(),
                    root -> root.join(Person_.designAndPlannings, JoinType.LEFT).get(DesignAndPlanning_.id)));
            }
            if (criteria.getRunPhaseId() != null) {
                specification = specification.and(buildSpecification(criteria.getRunPhaseId(),
                    root -> root.join(Person_.runPhases, JoinType.LEFT).get(RunPhase_.id)));
            }
        }
        return specification;
    }
}
