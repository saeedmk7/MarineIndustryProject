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

import com.marineindustryproj.domain.FixCompetencyDeficiency;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.FixCompetencyDeficiencyRepository;
import com.marineindustryproj.service.dto.FixCompetencyDeficiencyCriteria;
import com.marineindustryproj.service.dto.FixCompetencyDeficiencyDTO;
import com.marineindustryproj.service.mapper.FixCompetencyDeficiencyMapper;

/**
 * Service for executing complex queries for FixCompetencyDeficiency entities in the database.
 * The main input is a {@link FixCompetencyDeficiencyCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link FixCompetencyDeficiencyDTO} or a {@link Page} of {@link FixCompetencyDeficiencyDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class FixCompetencyDeficiencyQueryService extends QueryService<FixCompetencyDeficiency> {

    private final Logger log = LoggerFactory.getLogger(FixCompetencyDeficiencyQueryService.class);

    private final FixCompetencyDeficiencyRepository fixCompetencyDeficiencyRepository;

    private final FixCompetencyDeficiencyMapper fixCompetencyDeficiencyMapper;

    public FixCompetencyDeficiencyQueryService(FixCompetencyDeficiencyRepository fixCompetencyDeficiencyRepository, FixCompetencyDeficiencyMapper fixCompetencyDeficiencyMapper) {
        this.fixCompetencyDeficiencyRepository = fixCompetencyDeficiencyRepository;
        this.fixCompetencyDeficiencyMapper = fixCompetencyDeficiencyMapper;
    }

    /**
     * Return a {@link List} of {@link FixCompetencyDeficiencyDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<FixCompetencyDeficiencyDTO> findByCriteria(FixCompetencyDeficiencyCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<FixCompetencyDeficiency> specification = createSpecification(criteria);
        return fixCompetencyDeficiencyMapper.toDto(fixCompetencyDeficiencyRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link FixCompetencyDeficiencyDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<FixCompetencyDeficiencyDTO> findByCriteria(FixCompetencyDeficiencyCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<FixCompetencyDeficiency> specification = createSpecification(criteria);
        return fixCompetencyDeficiencyRepository.findAll(specification, page)
            .map(fixCompetencyDeficiencyMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(FixCompetencyDeficiencyCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<FixCompetencyDeficiency> specification = createSpecification(criteria);
        return fixCompetencyDeficiencyRepository.count(specification);
    }

    /**
     * Function to convert FixCompetencyDeficiencyCriteria to a {@link Specification}
     */
    private Specification<FixCompetencyDeficiency> createSpecification(FixCompetencyDeficiencyCriteria criteria) {
        Specification<FixCompetencyDeficiency> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), FixCompetencyDeficiency_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), FixCompetencyDeficiency_.title));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), FixCompetencyDeficiency_.description));
            }
            if (criteria.getDisplayOrder() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDisplayOrder(), FixCompetencyDeficiency_.displayOrder));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), FixCompetencyDeficiency_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), FixCompetencyDeficiency_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), FixCompetencyDeficiency_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), FixCompetencyDeficiency_.modifyDate));
            }
            if (criteria.getPreJobNiazsanjiCompetencyId() != null) {
                specification = specification.and(buildSpecification(criteria.getPreJobNiazsanjiCompetencyId(),
                    root -> root.join(FixCompetencyDeficiency_.preJobNiazsanjiCompetencies, JoinType.LEFT).get(PreJobNiazsanjiCompetency_.id)));
            }
        }
        return specification;
    }
}
