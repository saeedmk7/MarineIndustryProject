package com.marineindustryproj.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import com.marineindustryproj.domain.enumeration.NiazSanjiSource;
import io.github.jhipster.service.filter.StringFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.marineindustryproj.domain.ActivationNiazsanji;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.ActivationNiazsanjiRepository;
import com.marineindustryproj.service.dto.ActivationNiazsanjiCriteria;
import com.marineindustryproj.service.dto.ActivationNiazsanjiDTO;
import com.marineindustryproj.service.mapper.ActivationNiazsanjiMapper;

/**
 * Service for executing complex queries for ActivationNiazsanji entities in the database.
 * The main input is a {@link ActivationNiazsanjiCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ActivationNiazsanjiDTO} or a {@link Page} of {@link ActivationNiazsanjiDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ActivationNiazsanjiQueryService extends QueryService<ActivationNiazsanji> {

    private final Logger log = LoggerFactory.getLogger(ActivationNiazsanjiQueryService.class);

    private final ActivationNiazsanjiRepository activationNiazsanjiRepository;

    private final ActivationNiazsanjiMapper activationNiazsanjiMapper;

    public ActivationNiazsanjiQueryService(ActivationNiazsanjiRepository activationNiazsanjiRepository, ActivationNiazsanjiMapper activationNiazsanjiMapper) {
        this.activationNiazsanjiRepository = activationNiazsanjiRepository;
        this.activationNiazsanjiMapper = activationNiazsanjiMapper;
    }

    /**
     * Return a {@link List} of {@link ActivationNiazsanjiDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ActivationNiazsanjiDTO> findByCriteria(ActivationNiazsanjiCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ActivationNiazsanji> specification = createSpecification(criteria);
        return activationNiazsanjiMapper.toDto(activationNiazsanjiRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ActivationNiazsanjiDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ActivationNiazsanjiDTO> findByCriteria(ActivationNiazsanjiCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ActivationNiazsanji> specification = createSpecification(criteria);
        return activationNiazsanjiRepository.findAll(specification, page)
            .map(activationNiazsanjiMapper::toDto);
    }

    @Transactional(readOnly = true)
    public Boolean niazsanjiIsActive(NiazSanjiSource niazSanjiSource) {

        ActivationNiazsanjiCriteria criteria = new ActivationNiazsanjiCriteria();

        ActivationNiazsanjiCriteria.NiazSanjiSourceFilter enumFilter = new ActivationNiazsanjiCriteria.NiazSanjiSourceFilter();
        enumFilter.setEquals(niazSanjiSource);

        criteria.setNiazSanjiSource(enumFilter);

        List<ActivationNiazsanjiDTO> activationNiazsanjiDTOS = findByCriteria(criteria);
        if(!activationNiazsanjiDTOS.isEmpty()){
            ActivationNiazsanjiDTO activationNiazsanji = activationNiazsanjiDTOS.get(0);
            return activationNiazsanji.isIsActive();
        }
        return false;
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ActivationNiazsanjiCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ActivationNiazsanji> specification = createSpecification(criteria);
        return activationNiazsanjiRepository.count(specification);
    }

    /**
     * Function to convert ActivationNiazsanjiCriteria to a {@link Specification}
     */
    private Specification<ActivationNiazsanji> createSpecification(ActivationNiazsanjiCriteria criteria) {
        Specification<ActivationNiazsanji> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ActivationNiazsanji_.id));
            }
            if (criteria.getNiazSanjiSource() != null) {
                specification = specification.and(buildSpecification(criteria.getNiazSanjiSource(), ActivationNiazsanji_.niazSanjiSource));
            }
            if (criteria.getIsActive() != null) {
                specification = specification.and(buildSpecification(criteria.getIsActive(), ActivationNiazsanji_.isActive));
            }
            if (criteria.getStartDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStartDate(), ActivationNiazsanji_.startDate));
            }
            if (criteria.getEndDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEndDate(), ActivationNiazsanji_.endDate));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), ActivationNiazsanji_.description));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), ActivationNiazsanji_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), ActivationNiazsanji_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), ActivationNiazsanji_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), ActivationNiazsanji_.modifyDate));
            }
        }
        return specification;
    }
}
