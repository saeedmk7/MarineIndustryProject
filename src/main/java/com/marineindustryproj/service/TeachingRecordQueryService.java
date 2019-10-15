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

import com.marineindustryproj.domain.TeachingRecord;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.TeachingRecordRepository;
import com.marineindustryproj.service.dto.TeachingRecordCriteria;
import com.marineindustryproj.service.dto.TeachingRecordDTO;
import com.marineindustryproj.service.mapper.TeachingRecordMapper;

/**
 * Service for executing complex queries for TeachingRecord entities in the database.
 * The main input is a {@link TeachingRecordCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link TeachingRecordDTO} or a {@link Page} of {@link TeachingRecordDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class TeachingRecordQueryService extends QueryService<TeachingRecord> {

    private final Logger log = LoggerFactory.getLogger(TeachingRecordQueryService.class);

    private final TeachingRecordRepository teachingRecordRepository;

    private final TeachingRecordMapper teachingRecordMapper;

    public TeachingRecordQueryService(TeachingRecordRepository teachingRecordRepository, TeachingRecordMapper teachingRecordMapper) {
        this.teachingRecordRepository = teachingRecordRepository;
        this.teachingRecordMapper = teachingRecordMapper;
    }

    /**
     * Return a {@link List} of {@link TeachingRecordDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<TeachingRecordDTO> findByCriteria(TeachingRecordCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<TeachingRecord> specification = createSpecification(criteria);
        return teachingRecordMapper.toDto(teachingRecordRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link TeachingRecordDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<TeachingRecordDTO> findByCriteria(TeachingRecordCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<TeachingRecord> specification = createSpecification(criteria);
        return teachingRecordRepository.findAll(specification, page)
            .map(teachingRecordMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(TeachingRecordCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<TeachingRecord> specification = createSpecification(criteria);
        return teachingRecordRepository.count(specification);
    }

    /**
     * Function to convert TeachingRecordCriteria to a {@link Specification}
     */
    private Specification<TeachingRecord> createSpecification(TeachingRecordCriteria criteria) {
        Specification<TeachingRecord> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), TeachingRecord_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), TeachingRecord_.title));
            }
            if (criteria.getRecord() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRecord(), TeachingRecord_.record));
            }
            if (criteria.getTeachLocation() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTeachLocation(), TeachingRecord_.teachLocation));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), TeachingRecord_.description));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), TeachingRecord_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), TeachingRecord_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), TeachingRecord_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), TeachingRecord_.modifyDate));
            }
            if (criteria.getGuid() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGuid(), TeachingRecord_.guid));
            }
            if (criteria.getPersonId() != null) {
                specification = specification.and(buildSpecification(criteria.getPersonId(),
                    root -> root.join(TeachingRecord_.person, JoinType.LEFT).get(Person_.id)));
            }
        }
        return specification;
    }
}
