package com.marineindustryproj.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import io.github.jhipster.service.filter.LongFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.marineindustryproj.domain.NiazsanjiIntegration;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.NiazsanjiIntegrationRepository;
import com.marineindustryproj.service.dto.NiazsanjiIntegrationCriteria;
import com.marineindustryproj.service.dto.NiazsanjiIntegrationDTO;
import com.marineindustryproj.service.mapper.NiazsanjiIntegrationMapper;

/**
 * Service for executing complex queries for NiazsanjiIntegration entities in the database.
 * The main input is a {@link NiazsanjiIntegrationCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link NiazsanjiIntegrationDTO} or a {@link Page} of {@link NiazsanjiIntegrationDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class NiazsanjiIntegrationQueryService extends QueryService<NiazsanjiIntegration> {

    private final Logger log = LoggerFactory.getLogger(NiazsanjiIntegrationQueryService.class);

    private final NiazsanjiIntegrationRepository niazsanjiIntegrationRepository;

    private final NiazsanjiIntegrationMapper niazsanjiIntegrationMapper;

    public NiazsanjiIntegrationQueryService(NiazsanjiIntegrationRepository niazsanjiIntegrationRepository, NiazsanjiIntegrationMapper niazsanjiIntegrationMapper) {
        this.niazsanjiIntegrationRepository = niazsanjiIntegrationRepository;
        this.niazsanjiIntegrationMapper = niazsanjiIntegrationMapper;
    }

    /**
     * Return a {@link List} of {@link NiazsanjiIntegrationDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<NiazsanjiIntegrationDTO> findByCriteria(NiazsanjiIntegrationCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<NiazsanjiIntegration> specification = createSpecification(criteria);
        return niazsanjiIntegrationMapper.toDto(niazsanjiIntegrationRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link NiazsanjiIntegrationDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<NiazsanjiIntegrationDTO> findByCriteria(NiazsanjiIntegrationCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<NiazsanjiIntegration> specification = createSpecification(criteria);
        return niazsanjiIntegrationRepository.findAll(specification, page)
            .map(niazsanjiIntegrationMapper::toDto);
    }
    @Transactional(readOnly = true)
    public NiazsanjiIntegrationDTO findByPrioritizeRequestNiazsanjiId(long prioritizeRequestNiazsanjiId) {
        NiazsanjiIntegrationCriteria criteria = new NiazsanjiIntegrationCriteria();

        LongFilter prioritizeRequestNiazsanjiIdFilter = new LongFilter();
        prioritizeRequestNiazsanjiIdFilter.setEquals(prioritizeRequestNiazsanjiId);

        criteria.setPrioritizeRequestNiazsanjiId(prioritizeRequestNiazsanjiIdFilter);
        List<NiazsanjiIntegrationDTO> niazsanjiIntegrationDTOS = findByCriteria(criteria);
        if(!niazsanjiIntegrationDTOS.isEmpty())
            return niazsanjiIntegrationDTOS.get(0);
        return null;
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(NiazsanjiIntegrationCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<NiazsanjiIntegration> specification = createSpecification(criteria);
        return niazsanjiIntegrationRepository.count(specification);
    }

    /**
     * Function to convert NiazsanjiIntegrationCriteria to a {@link Specification}
     */
    private Specification<NiazsanjiIntegration> createSpecification(NiazsanjiIntegrationCriteria criteria) {
        Specification<NiazsanjiIntegration> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), NiazsanjiIntegration_.id));
            }
            if (criteria.getNiazsanjiYear() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getNiazsanjiYear(), NiazsanjiIntegration_.niazsanjiYear));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), NiazsanjiIntegration_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), NiazsanjiIntegration_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), NiazsanjiIntegration_.modifyDate));
            }
            if (criteria.getArchived() != null) {
                specification = specification.and(buildSpecification(criteria.getArchived(), NiazsanjiIntegration_.archived));
            }
            if (criteria.getArchivedUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getArchivedUserLogin(), NiazsanjiIntegration_.archivedUserLogin));
            }
            if (criteria.getArchivedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getArchivedDate(), NiazsanjiIntegration_.archivedDate));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStatus(), NiazsanjiIntegration_.status));
            }
            if (criteria.getRequestStatus() != null) {
                specification = specification.and(buildSpecification(criteria.getRequestStatus(), NiazsanjiIntegration_.requestStatus));
            }
            if (criteria.getChangeStatusUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getChangeStatusUserLogin(), NiazsanjiIntegration_.changeStatusUserLogin));
            }
            if (criteria.getGuid() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGuid(), NiazsanjiIntegration_.guid));
            }
            if (criteria.getHasImportantMessage() != null) {
                specification = specification.and(buildSpecification(criteria.getHasImportantMessage(), NiazsanjiIntegration_.hasImportantMessage));
            }
            if (criteria.getRequestNiazsanjiType() != null) {
                specification = specification.and(buildSpecification(criteria.getRequestNiazsanjiType(), NiazsanjiIntegration_.requestNiazsanjiType));
            }
            if (criteria.getPriority() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPriority(), NiazsanjiIntegration_.priority));
            }
            if (criteria.getFinalNiazsanjiReportId() != null) {
                specification = specification.and(buildSpecification(criteria.getFinalNiazsanjiReportId(),
                    root -> root.join(NiazsanjiIntegration_.finalNiazsanjiReports, JoinType.LEFT).get(FinalNiazsanjiReport_.id)));
            }
            if (criteria.getPrioritizeRequestNiazsanjiId() != null) {
                specification = specification.and(buildSpecification(criteria.getPrioritizeRequestNiazsanjiId(),
                    root -> root.join(NiazsanjiIntegration_.prioritizeRequestNiazsanji, JoinType.LEFT).get(PrioritizeRequestNiazsanji_.id)));
            }
            if (criteria.getEducationalModuleCode() != null) {
                specification = specification.and(buildSpecification(criteria.getEducationalModuleCode(),
                    root -> root.join(NiazsanjiIntegration_.prioritizeRequestNiazsanji, JoinType.LEFT)
                        .join(PrioritizeRequestNiazsanji_.educationalModule)
                        .get(EducationalModule_.code)));
            }
            if (criteria.getEducationalModuleTitle() != null) {
                specification = specification.and(buildSpecification(criteria.getEducationalModuleTitle(),
                    root -> root.join(NiazsanjiIntegration_.prioritizeRequestNiazsanji, JoinType.LEFT)
                        .join(PrioritizeRequestNiazsanji_.educationalModule)
                        .get(EducationalModule_.title)));
            }
            if (criteria.getEducationalModuleType() != null) {
                specification = specification.and(buildSpecification(criteria.getEducationalModuleType(),
                    root -> root.join(NiazsanjiIntegration_.prioritizeRequestNiazsanji, JoinType.LEFT)
                        .get(PrioritizeRequestNiazsanji_.educationalModuleType)));
            }
            if (criteria.getCostEducationalModule() != null) {
                specification = specification.and(buildSpecification(criteria.getCostEducationalModule(),
                    root -> root.join(NiazsanjiIntegration_.prioritizeRequestNiazsanji, JoinType.LEFT)
                        .get(PrioritizeRequestNiazsanji_.costEducationalModule)));
            }
            if (criteria.getSkillableLevelOfSkillId() != null) {
                specification = specification.and(buildSpecification(criteria.getSkillableLevelOfSkillId(),
                    root -> root.join(NiazsanjiIntegration_.prioritizeRequestNiazsanji, JoinType.LEFT)
                        .join(PrioritizeRequestNiazsanji_.educationalModule)
                        .join(EducationalModule_.skillableLevelOfSkill)
                        .get(SkillableLevelOfSkill_.id)));
            }
            if (criteria.getCourseTypeId() != null) {
                specification = specification.and(buildSpecification(criteria.getCourseTypeId(),
                    root -> root.join(NiazsanjiIntegration_.prioritizeRequestNiazsanji, JoinType.LEFT)
                        .join(PrioritizeRequestNiazsanji_.courseType)
                        .get(CourseType_.id)));
            }
            if (criteria.getPersonId() != null) {
                specification = specification.and(buildSpecification(criteria.getPersonId(),
                    root -> root.join(NiazsanjiIntegration_.prioritizeRequestNiazsanji, JoinType.LEFT)
                        .join(PrioritizeRequestNiazsanji_.person)
                        .get(Person_.id)));
            }
            if (criteria.getOrganizationChartId() != null) {
                specification = specification.and(buildSpecification(criteria.getOrganizationChartId(),
                    root -> root.join(NiazsanjiIntegration_.prioritizeRequestNiazsanji, JoinType.LEFT)
                        .join(PrioritizeRequestNiazsanji_.organizationChart)
                        .get(OrganizationChart_.id)));
            }
        }
        return specification;
    }
}
