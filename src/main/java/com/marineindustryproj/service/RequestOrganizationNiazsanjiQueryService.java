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

import com.marineindustryproj.domain.RequestOrganizationNiazsanji;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.RequestOrganizationNiazsanjiRepository;
import com.marineindustryproj.service.dto.RequestOrganizationNiazsanjiCriteria;
import com.marineindustryproj.service.dto.RequestOrganizationNiazsanjiDTO;
import com.marineindustryproj.service.mapper.RequestOrganizationNiazsanjiMapper;

/**
 * Service for executing complex queries for RequestOrganizationNiazsanji entities in the database.
 * The main input is a {@link RequestOrganizationNiazsanjiCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link RequestOrganizationNiazsanjiDTO} or a {@link Page} of {@link RequestOrganizationNiazsanjiDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class RequestOrganizationNiazsanjiQueryService extends QueryService<RequestOrganizationNiazsanji> {

    private final Logger log = LoggerFactory.getLogger(RequestOrganizationNiazsanjiQueryService.class);

    private final RequestOrganizationNiazsanjiRepository requestOrganizationNiazsanjiRepository;

    private final RequestOrganizationNiazsanjiMapper requestOrganizationNiazsanjiMapper;

    public RequestOrganizationNiazsanjiQueryService(RequestOrganizationNiazsanjiRepository requestOrganizationNiazsanjiRepository, RequestOrganizationNiazsanjiMapper requestOrganizationNiazsanjiMapper) {
        this.requestOrganizationNiazsanjiRepository = requestOrganizationNiazsanjiRepository;
        this.requestOrganizationNiazsanjiMapper = requestOrganizationNiazsanjiMapper;
    }

    /**
     * Return a {@link List} of {@link RequestOrganizationNiazsanjiDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<RequestOrganizationNiazsanjiDTO> findByCriteria(RequestOrganizationNiazsanjiCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<RequestOrganizationNiazsanji> specification = createSpecification(criteria);
        return requestOrganizationNiazsanjiMapper.toDto(requestOrganizationNiazsanjiRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link RequestOrganizationNiazsanjiDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<RequestOrganizationNiazsanjiDTO> findByCriteria(RequestOrganizationNiazsanjiCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<RequestOrganizationNiazsanji> specification = createSpecification(criteria);
        return requestOrganizationNiazsanjiRepository.findAll(specification, page)
            .map(requestOrganizationNiazsanjiMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(RequestOrganizationNiazsanjiCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<RequestOrganizationNiazsanji> specification = createSpecification(criteria);
        return requestOrganizationNiazsanjiRepository.count(specification);
    }

    /**
     * Function to convert RequestOrganizationNiazsanjiCriteria to a {@link Specification}
     */
    private Specification<RequestOrganizationNiazsanji> createSpecification(RequestOrganizationNiazsanjiCriteria criteria) {
        Specification<RequestOrganizationNiazsanji> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), RequestOrganizationNiazsanji_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), RequestOrganizationNiazsanji_.code));
            }
            if (criteria.getRecommendedByOrgchart() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRecommendedByOrgchart(), RequestOrganizationNiazsanji_.recommendedByOrgchart));
            }
            if (criteria.getNeededSoftwares() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNeededSoftwares(), RequestOrganizationNiazsanji_.neededSoftwares));
            }
            if (criteria.getNeededHardware() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNeededHardware(), RequestOrganizationNiazsanji_.neededHardware));
            }
            if (criteria.getStudentsType() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStudentsType(), RequestOrganizationNiazsanji_.studentsType));
            }
            if (criteria.getTeacherNotFound() != null) {
                specification = specification.and(buildSpecification(criteria.getTeacherNotFound(), RequestOrganizationNiazsanji_.teacherNotFound));
            }
            if (criteria.getTeacherName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTeacherName(), RequestOrganizationNiazsanji_.teacherName));
            }
            if (criteria.getTeacherMobile() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTeacherMobile(), RequestOrganizationNiazsanji_.teacherMobile));
            }
            if (criteria.getRequestStatus() != null) {
                specification = specification.and(buildSpecification(criteria.getRequestStatus(), RequestOrganizationNiazsanji_.requestStatus));
            }
            if (criteria.getChangeStatusUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getChangeStatusUserLogin(), RequestOrganizationNiazsanji_.changeStatusUserLogin));
            }
            if (criteria.getTrainingGoals() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTrainingGoals(), RequestOrganizationNiazsanji_.trainingGoals));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), RequestOrganizationNiazsanji_.description));
            }
            if (criteria.getPriceCost() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPriceCost(), RequestOrganizationNiazsanji_.priceCost));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), RequestOrganizationNiazsanji_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), RequestOrganizationNiazsanji_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), RequestOrganizationNiazsanji_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), RequestOrganizationNiazsanji_.modifyDate));
            }
            if (criteria.getArchived() != null) {
                specification = specification.and(buildSpecification(criteria.getArchived(), RequestOrganizationNiazsanji_.archived));
            }
            if (criteria.getArchivedUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getArchivedUserLogin(), RequestOrganizationNiazsanji_.archivedUserLogin));
            }
            if (criteria.getArchivedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getArchivedDate(), RequestOrganizationNiazsanji_.archivedDate));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStatus(), RequestOrganizationNiazsanji_.status));
            }
            if (criteria.getFinalOrganizationNiazsanjiId() != null) {
                specification = specification.and(buildSpecification(criteria.getFinalOrganizationNiazsanjiId(),
                    root -> root.join(RequestOrganizationNiazsanji_.finalOrganizationNiazsanjis, JoinType.LEFT).get(FinalOrganizationNiazsanji_.id)));
            }
            if (criteria.getPersonId() != null) {
                specification = specification.and(buildSpecification(criteria.getPersonId(),
                    root -> root.join(RequestOrganizationNiazsanji_.people, JoinType.LEFT).get(Person_.id)));
            }
            if (criteria.getDocumentId() != null) {
                specification = specification.and(buildSpecification(criteria.getDocumentId(),
                    root -> root.join(RequestOrganizationNiazsanji_.documents, JoinType.LEFT).get(Document_.id)));
            }
            if (criteria.getOrganizationChartId() != null) {
                specification = specification.and(buildSpecification(criteria.getOrganizationChartId(),
                    root -> root.join(RequestOrganizationNiazsanji_.organizationChart, JoinType.LEFT).get(OrganizationChart_.id)));
            }
            if (criteria.getTeacherId() != null) {
                specification = specification.and(buildSpecification(criteria.getTeacherId(),
                    root -> root.join(RequestOrganizationNiazsanji_.teacher, JoinType.LEFT).get(Teacher_.id)));
            }
            if (criteria.getEducationalModuleId() != null) {
                specification = specification.and(buildSpecification(criteria.getEducationalModuleId(),
                    root -> root.join(RequestOrganizationNiazsanji_.educationalModule, JoinType.LEFT).get(EducationalModule_.id)));
            }
            if (criteria.getTeachApproachId() != null) {
                specification = specification.and(buildSpecification(criteria.getTeachApproachId(),
                    root -> root.join(RequestOrganizationNiazsanji_.teachApproach, JoinType.LEFT).get(TeachApproach_.id)));
            }
        }
        return specification;
    }
}
