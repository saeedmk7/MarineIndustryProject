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

import com.marineindustryproj.domain.ResearchRecord;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.ResearchRecordRepository;
import com.marineindustryproj.service.dto.ResearchRecordCriteria;
import com.marineindustryproj.service.dto.ResearchRecordDTO;
import com.marineindustryproj.service.mapper.ResearchRecordMapper;

/**
 * Service for executing complex queries for ResearchRecord entities in the database.
 * The main input is a {@link ResearchRecordCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ResearchRecordDTO} or a {@link Page} of {@link ResearchRecordDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ResearchRecordQueryService extends QueryService<ResearchRecord> {

    private final Logger log = LoggerFactory.getLogger(ResearchRecordQueryService.class);

    private final ResearchRecordRepository researchRecordRepository;

    private final ResearchRecordMapper researchRecordMapper;

    public ResearchRecordQueryService(ResearchRecordRepository researchRecordRepository, ResearchRecordMapper researchRecordMapper) {
        this.researchRecordRepository = researchRecordRepository;
        this.researchRecordMapper = researchRecordMapper;
    }

    /**
     * Return a {@link List} of {@link ResearchRecordDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ResearchRecordDTO> findByCriteria(ResearchRecordCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ResearchRecord> specification = createSpecification(criteria);
        return researchRecordMapper.toDto(researchRecordRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ResearchRecordDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ResearchRecordDTO> findByCriteria(ResearchRecordCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ResearchRecord> specification = createSpecification(criteria);
        return researchRecordRepository.findAll(specification, page)
            .map(researchRecordMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ResearchRecordCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ResearchRecord> specification = createSpecification(criteria);
        return researchRecordRepository.count(specification);
    }

    /**
     * Function to convert ResearchRecordCriteria to a {@link Specification}
     */
    private Specification<ResearchRecord> createSpecification(ResearchRecordCriteria criteria) {
        Specification<ResearchRecord> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ResearchRecord_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), ResearchRecord_.title));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), ResearchRecord_.description));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), ResearchRecord_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), ResearchRecord_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), ResearchRecord_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), ResearchRecord_.modifyDate));
            }
            if (criteria.getGuid() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGuid(), ResearchRecord_.guid));
            }
            if (criteria.getPersonId() != null) {
                specification = specification.and(buildSpecification(criteria.getPersonId(),
                    root -> root.join(ResearchRecord_.person, JoinType.LEFT).get(Person_.id)));
            }
        }
        return specification;
    }
}
