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

import com.marineindustryproj.domain.MatchingEducationalRecord;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.MatchingEducationalRecordRepository;
import com.marineindustryproj.service.dto.MatchingEducationalRecordCriteria;
import com.marineindustryproj.service.dto.MatchingEducationalRecordDTO;
import com.marineindustryproj.service.mapper.MatchingEducationalRecordMapper;

/**
 * Service for executing complex queries for MatchingEducationalRecord entities in the database.
 * The main input is a {@link MatchingEducationalRecordCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MatchingEducationalRecordDTO} or a {@link Page} of {@link MatchingEducationalRecordDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MatchingEducationalRecordQueryService extends QueryService<MatchingEducationalRecord> {

    private final Logger log = LoggerFactory.getLogger(MatchingEducationalRecordQueryService.class);

    private final MatchingEducationalRecordRepository matchingEducationalRecordRepository;

    private final MatchingEducationalRecordMapper matchingEducationalRecordMapper;

    public MatchingEducationalRecordQueryService(MatchingEducationalRecordRepository matchingEducationalRecordRepository, MatchingEducationalRecordMapper matchingEducationalRecordMapper) {
        this.matchingEducationalRecordRepository = matchingEducationalRecordRepository;
        this.matchingEducationalRecordMapper = matchingEducationalRecordMapper;
    }

    /**
     * Return a {@link List} of {@link MatchingEducationalRecordDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MatchingEducationalRecordDTO> findByCriteria(MatchingEducationalRecordCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MatchingEducationalRecord> specification = createSpecification(criteria);
        return matchingEducationalRecordMapper.toDto(matchingEducationalRecordRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MatchingEducationalRecordDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MatchingEducationalRecordDTO> findByCriteria(MatchingEducationalRecordCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MatchingEducationalRecord> specification = createSpecification(criteria);
        return matchingEducationalRecordRepository.findAll(specification, page)
            .map(matchingEducationalRecordMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MatchingEducationalRecordCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MatchingEducationalRecord> specification = createSpecification(criteria);
        return matchingEducationalRecordRepository.count(specification);
    }

    /**
     * Function to convert MatchingEducationalRecordCriteria to a {@link Specification}
     */
    private Specification<MatchingEducationalRecord> createSpecification(MatchingEducationalRecordCriteria criteria) {
        Specification<MatchingEducationalRecord> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MatchingEducationalRecord_.id));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), MatchingEducationalRecord_.code));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), MatchingEducationalRecord_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), MatchingEducationalRecord_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), MatchingEducationalRecord_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), MatchingEducationalRecord_.modifyDate));
            }
            if (criteria.getArchived() != null) {
                specification = specification.and(buildSpecification(criteria.getArchived(), MatchingEducationalRecord_.archived));
            }
            if (criteria.getArchivedUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getArchivedUserLogin(), MatchingEducationalRecord_.archivedUserLogin));
            }
            if (criteria.getArchivedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getArchivedDate(), MatchingEducationalRecord_.archivedDate));
            }
            if (criteria.getChartStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getChartStatus(), MatchingEducationalRecord_.chartStatus));
            }
            if (criteria.getBossStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBossStatus(), MatchingEducationalRecord_.bossStatus));
            }
            if (criteria.getRequestStatus() != null) {
                specification = specification.and(buildSpecification(criteria.getRequestStatus(), MatchingEducationalRecord_.requestStatus));
            }
            if (criteria.getChangeStatusUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getChangeStatusUserLogin(), MatchingEducationalRecord_.changeStatusUserLogin));
            }
            if (criteria.getGuid() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGuid(), MatchingEducationalRecord_.guid));
            }
            if (criteria.getHasImportantMessage() != null) {
                specification = specification.and(buildSpecification(criteria.getHasImportantMessage(), MatchingEducationalRecord_.hasImportantMessage));
            }
            if (criteria.getSelectedModuleIds() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSelectedModuleIds(), MatchingEducationalRecord_.selectedModuleIds));
            }
            if (criteria.getMatchingRunRunningStepId() != null) {
                specification = specification.and(buildSpecification(criteria.getMatchingRunRunningStepId(),
                    root -> root.join(MatchingEducationalRecord_.matchingRunRunningSteps, JoinType.LEFT).get(MatchingRunRunningStep_.id)));
            }
            if (criteria.getSkillableLevelOfSkillId() != null) {
                specification = specification.and(buildSpecification(criteria.getSkillableLevelOfSkillId(),
                    root -> root.join(MatchingEducationalRecord_.skillableLevelOfSkills, JoinType.LEFT).get(SkillableLevelOfSkill_.id)));
            }
            if (criteria.getDocumentId() != null) {
                specification = specification.and(buildSpecification(criteria.getDocumentId(),
                    root -> root.join(MatchingEducationalRecord_.documents, JoinType.LEFT).get(Document_.id)));
            }
            if (criteria.getPersonId() != null) {
                specification = specification.and(buildSpecification(criteria.getPersonId(),
                    root -> root.join(MatchingEducationalRecord_.person, JoinType.LEFT).get(Person_.id)));
            }
            if (criteria.getPersonEmploymentTypeId() != null) {
                specification = specification.and(buildSpecification(criteria.getPersonEmploymentTypeId(),
                    root -> root.join(MatchingEducationalRecord_.person, JoinType.LEFT).join(Person_.employmentType).get(EmploymentType_.id)));
            }
            if (criteria.getOrganizationChartId() != null) {
                specification = specification.and(buildSpecification(criteria.getOrganizationChartId(),
                    root -> root.join(MatchingEducationalRecord_.organizationChart, JoinType.LEFT).get(OrganizationChart_.id)));
            }
        }
        return specification;
    }
}
