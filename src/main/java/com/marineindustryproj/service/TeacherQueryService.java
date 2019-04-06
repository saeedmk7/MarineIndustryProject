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

import com.marineindustryproj.domain.Teacher;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.TeacherRepository;
import com.marineindustryproj.service.dto.TeacherCriteria;
import com.marineindustryproj.service.dto.TeacherDTO;
import com.marineindustryproj.service.mapper.TeacherMapper;

/**
 * Service for executing complex queries for Teacher entities in the database.
 * The main input is a {@link TeacherCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link TeacherDTO} or a {@link Page} of {@link TeacherDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class TeacherQueryService extends QueryService<Teacher> {

    private final Logger log = LoggerFactory.getLogger(TeacherQueryService.class);

    private final TeacherRepository teacherRepository;

    private final TeacherMapper teacherMapper;

    public TeacherQueryService(TeacherRepository teacherRepository, TeacherMapper teacherMapper) {
        this.teacherRepository = teacherRepository;
        this.teacherMapper = teacherMapper;
    }

    /**
     * Return a {@link List} of {@link TeacherDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<TeacherDTO> findByCriteria(TeacherCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Teacher> specification = createSpecification(criteria);
        return teacherMapper.toDto(teacherRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link TeacherDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<TeacherDTO> findByCriteria(TeacherCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Teacher> specification = createSpecification(criteria);
        return teacherRepository.findAll(specification, page)
            .map(teacherMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(TeacherCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Teacher> specification = createSpecification(criteria);
        return teacherRepository.count(specification);
    }

    /**
     * Function to convert TeacherCriteria to a {@link Specification}
     */
    private Specification<Teacher> createSpecification(TeacherCriteria criteria) {
        Specification<Teacher> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Teacher_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), Teacher_.name));
            }
            if (criteria.getFamily() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFamily(), Teacher_.family));
            }
            if (criteria.getFatherName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFatherName(), Teacher_.fatherName));
            }
            if (criteria.getScientificBasis() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getScientificBasis(), Teacher_.scientificBasis));
            }
            if (criteria.getInquiry() != null) {
                specification = specification.and(buildSpecification(criteria.getInquiry(), Teacher_.inquiry));
            }
            if (criteria.getSchoolConfirmation() != null) {
                specification = specification.and(buildSpecification(criteria.getSchoolConfirmation(), Teacher_.schoolConfirmation));
            }
            if (criteria.getProtectiveApproval() != null) {
                specification = specification.and(buildSpecification(criteria.getProtectiveApproval(), Teacher_.protectiveApproval));
            }
            if (criteria.getTeachingSubject() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTeachingSubject(), Teacher_.teachingSubject));
            }
            if (criteria.getIssueDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getIssueDate(), Teacher_.issueDate));
            }
            if (criteria.getExpirationDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getExpirationDate(), Teacher_.expirationDate));
            }
            if (criteria.getLicenseNumber() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLicenseNumber(), Teacher_.licenseNumber));
            }
            if (criteria.getSessionNumber() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSessionNumber(), Teacher_.sessionNumber));
            }
            if (criteria.getPhoneNumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPhoneNumber(), Teacher_.phoneNumber));
            }
            if (criteria.getLicenseRenewalDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLicenseRenewalDate(), Teacher_.licenseRenewalDate));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCode(), Teacher_.code));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), Teacher_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), Teacher_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), Teacher_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), Teacher_.modifyDate));
            }
            if (criteria.getArchived() != null) {
                specification = specification.and(buildSpecification(criteria.getArchived(), Teacher_.archived));
            }
            if (criteria.getArchivedUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getArchivedUserLogin(), Teacher_.archivedUserLogin));
            }
            if (criteria.getArchivedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getArchivedDate(), Teacher_.archivedDate));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStatus(), Teacher_.status));
            }
            if (criteria.getRequestOrganizationNiazsanjiId() != null) {
                specification = specification.and(buildSpecification(criteria.getRequestOrganizationNiazsanjiId(),
                    root -> root.join(Teacher_.requestOrganizationNiazsanjis, JoinType.LEFT).get(RequestOrganizationNiazsanji_.id)));
            }
            if (criteria.getFinalOrganizationNiazsanjiId() != null) {
                specification = specification.and(buildSpecification(criteria.getFinalOrganizationNiazsanjiId(),
                    root -> root.join(Teacher_.finalOrganizationNiazsanjis, JoinType.LEFT).get(FinalOrganizationNiazsanji_.id)));
            }
            if (criteria.getDocumentId() != null) {
                specification = specification.and(buildSpecification(criteria.getDocumentId(),
                    root -> root.join(Teacher_.documents, JoinType.LEFT).get(Document_.id)));
            }
            if (criteria.getLastQualificationId() != null) {
                specification = specification.and(buildSpecification(criteria.getLastQualificationId(),
                    root -> root.join(Teacher_.lastQualification, JoinType.LEFT).get(Qualification_.id)));
            }
            if (criteria.getLastFieldOfStudyId() != null) {
                specification = specification.and(buildSpecification(criteria.getLastFieldOfStudyId(),
                    root -> root.join(Teacher_.lastFieldOfStudy, JoinType.LEFT).get(FieldOfStudy_.id)));
            }
            if (criteria.getServiceUnitId() != null) {
                specification = specification.and(buildSpecification(criteria.getServiceUnitId(),
                    root -> root.join(Teacher_.serviceUnit, JoinType.LEFT).get(ServiceUnit_.id)));
            }
            if (criteria.getAcademicRankId() != null) {
                specification = specification.and(buildSpecification(criteria.getAcademicRankId(),
                    root -> root.join(Teacher_.academicRank, JoinType.LEFT).get(AcademicRank_.id)));
            }
            if (criteria.getEducationalModuleId() != null) {
                specification = specification.and(buildSpecification(criteria.getEducationalModuleId(),
                    root -> root.join(Teacher_.educationalModules, JoinType.LEFT).get(EducationalModule_.id)));
            }
            if (criteria.getRequestEducationalModuleId() != null) {
                specification = specification.and(buildSpecification(criteria.getRequestEducationalModuleId(),
                    root -> root.join(Teacher_.requestEducationalModules, JoinType.LEFT).get(RequestEducationalModule_.id)));
            }
        }
        return specification;
    }
}
