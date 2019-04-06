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

import com.marineindustryproj.domain.Poll;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.PollRepository;
import com.marineindustryproj.service.dto.PollCriteria;
import com.marineindustryproj.service.dto.PollDTO;
import com.marineindustryproj.service.mapper.PollMapper;

/**
 * Service for executing complex queries for Poll entities in the database.
 * The main input is a {@link PollCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PollDTO} or a {@link Page} of {@link PollDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PollQueryService extends QueryService<Poll> {

    private final Logger log = LoggerFactory.getLogger(PollQueryService.class);

    private final PollRepository pollRepository;

    private final PollMapper pollMapper;

    public PollQueryService(PollRepository pollRepository, PollMapper pollMapper) {
        this.pollRepository = pollRepository;
        this.pollMapper = pollMapper;
    }

    /**
     * Return a {@link List} of {@link PollDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<PollDTO> findByCriteria(PollCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Poll> specification = createSpecification(criteria);
        return pollMapper.toDto(pollRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link PollDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<PollDTO> findByCriteria(PollCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Poll> specification = createSpecification(criteria);
        return pollRepository.findAll(specification, page)
            .map(pollMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(PollCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Poll> specification = createSpecification(criteria);
        return pollRepository.count(specification);
    }

    /**
     * Function to convert PollCriteria to a {@link Specification}
     */
    private Specification<Poll> createSpecification(PollCriteria criteria) {
        Specification<Poll> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Poll_.id));
            }
            if (criteria.getMoreRecommendation() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMoreRecommendation(), Poll_.moreRecommendation));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), Poll_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), Poll_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), Poll_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), Poll_.modifyDate));
            }
            if (criteria.getArchived() != null) {
                specification = specification.and(buildSpecification(criteria.getArchived(), Poll_.archived));
            }
            if (criteria.getArchivedUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getArchivedUserLogin(), Poll_.archivedUserLogin));
            }
            if (criteria.getArchivedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getArchivedDate(), Poll_.archivedDate));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStatus(), Poll_.status));
            }
            if (criteria.getPollScoreId() != null) {
                specification = specification.and(buildSpecification(criteria.getPollScoreId(),
                    root -> root.join(Poll_.pollScores, JoinType.LEFT).get(PollScore_.id)));
            }
            if (criteria.getFinalNiazsanjiReportId() != null) {
                specification = specification.and(buildSpecification(criteria.getFinalNiazsanjiReportId(),
                    root -> root.join(Poll_.finalNiazsanjiReport, JoinType.LEFT).get(FinalNiazsanjiReport_.id)));
            }
        }
        return specification;
    }
}
