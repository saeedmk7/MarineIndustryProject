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

import com.marineindustryproj.domain.PollItem;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.PollItemRepository;
import com.marineindustryproj.service.dto.PollItemCriteria;
import com.marineindustryproj.service.dto.PollItemDTO;
import com.marineindustryproj.service.mapper.PollItemMapper;

/**
 * Service for executing complex queries for PollItem entities in the database.
 * The main input is a {@link PollItemCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PollItemDTO} or a {@link Page} of {@link PollItemDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PollItemQueryService extends QueryService<PollItem> {

    private final Logger log = LoggerFactory.getLogger(PollItemQueryService.class);

    private final PollItemRepository pollItemRepository;

    private final PollItemMapper pollItemMapper;

    public PollItemQueryService(PollItemRepository pollItemRepository, PollItemMapper pollItemMapper) {
        this.pollItemRepository = pollItemRepository;
        this.pollItemMapper = pollItemMapper;
    }

    /**
     * Return a {@link List} of {@link PollItemDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<PollItemDTO> findByCriteria(PollItemCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<PollItem> specification = createSpecification(criteria);
        return pollItemMapper.toDto(pollItemRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link PollItemDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<PollItemDTO> findByCriteria(PollItemCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<PollItem> specification = createSpecification(criteria);
        return pollItemRepository.findAll(specification, page)
            .map(pollItemMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(PollItemCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<PollItem> specification = createSpecification(criteria);
        return pollItemRepository.count(specification);
    }

    /**
     * Function to convert PollItemCriteria to a {@link Specification}
     */
    private Specification<PollItem> createSpecification(PollItemCriteria criteria) {
        Specification<PollItem> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), PollItem_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), PollItem_.title));
            }
            if (criteria.getDisplayOrder() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDisplayOrder(), PollItem_.displayOrder));
            }
            if (criteria.getCoefficient() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCoefficient(), PollItem_.coefficient));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), PollItem_.description));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), PollItem_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), PollItem_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), PollItem_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), PollItem_.modifyDate));
            }
            if (criteria.getPollScoreId() != null) {
                specification = specification.and(buildSpecification(criteria.getPollScoreId(),
                    root -> root.join(PollItem_.pollScores, JoinType.LEFT).get(PollScore_.id)));
            }
            if (criteria.getCriterionId() != null) {
                specification = specification.and(buildSpecification(criteria.getCriterionId(),
                    root -> root.join(PollItem_.criterion, JoinType.LEFT).get(Criterion_.id)));
            }
        }
        return specification;
    }
}
