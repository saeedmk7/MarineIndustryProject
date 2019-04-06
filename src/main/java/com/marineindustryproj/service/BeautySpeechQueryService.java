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

import com.marineindustryproj.domain.BeautySpeech;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.BeautySpeechRepository;
import com.marineindustryproj.service.dto.BeautySpeechCriteria;
import com.marineindustryproj.service.dto.BeautySpeechDTO;
import com.marineindustryproj.service.mapper.BeautySpeechMapper;

/**
 * Service for executing complex queries for BeautySpeech entities in the database.
 * The main input is a {@link BeautySpeechCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link BeautySpeechDTO} or a {@link Page} of {@link BeautySpeechDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class BeautySpeechQueryService extends QueryService<BeautySpeech> {

    private final Logger log = LoggerFactory.getLogger(BeautySpeechQueryService.class);

    private final BeautySpeechRepository beautySpeechRepository;

    private final BeautySpeechMapper beautySpeechMapper;

    public BeautySpeechQueryService(BeautySpeechRepository beautySpeechRepository, BeautySpeechMapper beautySpeechMapper) {
        this.beautySpeechRepository = beautySpeechRepository;
        this.beautySpeechMapper = beautySpeechMapper;
    }

    /**
     * Return a {@link List} of {@link BeautySpeechDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<BeautySpeechDTO> findByCriteria(BeautySpeechCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<BeautySpeech> specification = createSpecification(criteria);
        return beautySpeechMapper.toDto(beautySpeechRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link BeautySpeechDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<BeautySpeechDTO> findByCriteria(BeautySpeechCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<BeautySpeech> specification = createSpecification(criteria);
        return beautySpeechRepository.findAll(specification, page)
            .map(beautySpeechMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(BeautySpeechCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<BeautySpeech> specification = createSpecification(criteria);
        return beautySpeechRepository.count(specification);
    }

    /**
     * Function to convert BeautySpeechCriteria to a {@link Specification}
     */
    private Specification<BeautySpeech> createSpecification(BeautySpeechCriteria criteria) {
        Specification<BeautySpeech> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), BeautySpeech_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), BeautySpeech_.title));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), BeautySpeech_.description));
            }
            if (criteria.getIsActive() != null) {
                specification = specification.and(buildSpecification(criteria.getIsActive(), BeautySpeech_.isActive));
            }
            if (criteria.getShowDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getShowDate(), BeautySpeech_.showDate));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), BeautySpeech_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), BeautySpeech_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), BeautySpeech_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), BeautySpeech_.modifyDate));
            }
        }
        return specification;
    }
}
