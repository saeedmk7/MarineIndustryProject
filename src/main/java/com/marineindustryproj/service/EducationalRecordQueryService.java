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

import com.marineindustryproj.domain.EducationalRecord;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.EducationalRecordRepository;
import com.marineindustryproj.service.dto.EducationalRecordCriteria;
import com.marineindustryproj.service.dto.EducationalRecordDTO;
import com.marineindustryproj.service.mapper.EducationalRecordMapper;

/**
 * Service for executing complex queries for EducationalRecord entities in the database.
 * The main input is a {@link EducationalRecordCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link EducationalRecordDTO} or a {@link Page} of {@link EducationalRecordDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class EducationalRecordQueryService extends QueryService<EducationalRecord> {

    private final Logger log = LoggerFactory.getLogger(EducationalRecordQueryService.class);

    private final EducationalRecordRepository educationalRecordRepository;

    private final EducationalRecordMapper educationalRecordMapper;

    public EducationalRecordQueryService(EducationalRecordRepository educationalRecordRepository, EducationalRecordMapper educationalRecordMapper) {
        this.educationalRecordRepository = educationalRecordRepository;
        this.educationalRecordMapper = educationalRecordMapper;
    }

    /**
     * Return a {@link List} of {@link EducationalRecordDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<EducationalRecordDTO> findByCriteria(EducationalRecordCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<EducationalRecord> specification = createSpecification(criteria);
        return educationalRecordMapper.toDto(educationalRecordRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link EducationalRecordDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<EducationalRecordDTO> findByCriteria(EducationalRecordCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<EducationalRecord> specification = createSpecification(criteria);
        return educationalRecordRepository.findAll(specification, page)
            .map(educationalRecordMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(EducationalRecordCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<EducationalRecord> specification = createSpecification(criteria);
        return educationalRecordRepository.count(specification);
    }

    /**
     * Function to convert EducationalRecordCriteria to a {@link Specification}
     */
    private Specification<EducationalRecord> createSpecification(EducationalRecordCriteria criteria) {
        Specification<EducationalRecord> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), EducationalRecord_.id));
            }
            if (criteria.getQualificationText() != null) {
                specification = specification.and(buildStringSpecification(criteria.getQualificationText(), EducationalRecord_.qualificationText));
            }
            if (criteria.getFieldOfStudyText() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFieldOfStudyText(), EducationalRecord_.fieldOfStudyText));
            }
            if (criteria.getEducationalCenterText() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEducationalCenterText(), EducationalRecord_.educationalCenterText));
            }
            if (criteria.getTotalAverage() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTotalAverage(), EducationalRecord_.totalAverage));
            }
            if (criteria.getTotalHour() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTotalHour(), EducationalRecord_.totalHour));
            }
            if (criteria.getStartDate() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStartDate(), EducationalRecord_.startDate));
            }
            if (criteria.getEndDate() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEndDate(), EducationalRecord_.endDate));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), EducationalRecord_.description));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), EducationalRecord_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), EducationalRecord_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), EducationalRecord_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), EducationalRecord_.modifyDate));
            }
            if (criteria.getGuid() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGuid(), EducationalRecord_.guid));
            }
            if (criteria.getQualificationId() != null) {
                specification = specification.and(buildSpecification(criteria.getQualificationId(),
                    root -> root.join(EducationalRecord_.qualification, JoinType.LEFT).get(Qualification_.id)));
            }
            if (criteria.getFieldOfStudyId() != null) {
                specification = specification.and(buildSpecification(criteria.getFieldOfStudyId(),
                    root -> root.join(EducationalRecord_.fieldOfStudy, JoinType.LEFT).get(FieldOfStudy_.id)));
            }
            if (criteria.getPersonId() != null) {
                specification = specification.and(buildSpecification(criteria.getPersonId(),
                    root -> root.join(EducationalRecord_.person, JoinType.LEFT).get(Person_.id)));
            }
        }
        return specification;
    }
}
