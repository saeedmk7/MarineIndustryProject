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

import com.marineindustryproj.domain.PollScore;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.PollScoreRepository;
import com.marineindustryproj.service.dto.PollScoreCriteria;
import com.marineindustryproj.service.dto.PollScoreDTO;
import com.marineindustryproj.service.mapper.PollScoreMapper;

/**
 * Service for executing complex queries for PollScore entities in the database.
 * The main input is a {@link PollScoreCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PollScoreDTO} or a {@link Page} of {@link PollScoreDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PollScoreQueryService extends QueryService<PollScore> {

    private final Logger log = LoggerFactory.getLogger(PollScoreQueryService.class);

    private final PollScoreRepository pollScoreRepository;

    private final PollScoreMapper pollScoreMapper;

    public PollScoreQueryService(PollScoreRepository pollScoreRepository, PollScoreMapper pollScoreMapper) {
        this.pollScoreRepository = pollScoreRepository;
        this.pollScoreMapper = pollScoreMapper;
    }

    /**
     * Return a {@link List} of {@link PollScoreDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<PollScoreDTO> findByCriteria(PollScoreCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<PollScore> specification = createSpecification(criteria);
        return pollScoreMapper.toDto(pollScoreRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link PollScoreDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<PollScoreDTO> findByCriteria(PollScoreCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<PollScore> specification = createSpecification(criteria);
        return pollScoreRepository.findAll(specification, page)
            .map(pollScoreMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(PollScoreCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<PollScore> specification = createSpecification(criteria);
        return pollScoreRepository.count(specification);
    }

    /**
     * Function to convert PollScoreCriteria to a {@link Specification}
     */
    private Specification<PollScore> createSpecification(PollScoreCriteria criteria) {
        Specification<PollScore> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), PollScore_.id));
            }
            if (criteria.getRecommendation() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRecommendation(), PollScore_.recommendation));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), PollScore_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), PollScore_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), PollScore_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), PollScore_.modifyDate));
            }
            if (criteria.getPollItemId() != null) {
                specification = specification.and(buildSpecification(criteria.getPollItemId(),
                    root -> root.join(PollScore_.pollItem, JoinType.LEFT).get(PollItem_.id)));
            }
            if (criteria.getScoreItemId() != null) {
                specification = specification.and(buildSpecification(criteria.getScoreItemId(),
                    root -> root.join(PollScore_.scoreItem, JoinType.LEFT).get(ScoreItem_.id)));
            }
            if (criteria.getPollId() != null) {
                specification = specification.and(buildSpecification(criteria.getPollId(),
                    root -> root.join(PollScore_.poll, JoinType.LEFT).get(Poll_.id)));
            }
            if (criteria.getPersonId() != null) {
                specification = specification.and(buildSpecification(criteria.getPersonId(),
                    root -> root.join(PollScore_.person, JoinType.LEFT).get(Person_.id)));
            }
        }
        return specification;
    }
}
