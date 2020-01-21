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

import com.marineindustryproj.domain.NiazsanjiInput;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.NiazsanjiInputRepository;
import com.marineindustryproj.service.dto.NiazsanjiInputCriteria;
import com.marineindustryproj.service.dto.NiazsanjiInputDTO;
import com.marineindustryproj.service.mapper.NiazsanjiInputMapper;

/**
 * Service for executing complex queries for NiazsanjiInput entities in the database.
 * The main input is a {@link NiazsanjiInputCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link NiazsanjiInputDTO} or a {@link Page} of {@link NiazsanjiInputDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class NiazsanjiInputQueryService extends QueryService<NiazsanjiInput> {

    private final Logger log = LoggerFactory.getLogger(NiazsanjiInputQueryService.class);

    private final NiazsanjiInputRepository niazsanjiInputRepository;

    private final NiazsanjiInputMapper niazsanjiInputMapper;

    public NiazsanjiInputQueryService(NiazsanjiInputRepository niazsanjiInputRepository, NiazsanjiInputMapper niazsanjiInputMapper) {
        this.niazsanjiInputRepository = niazsanjiInputRepository;
        this.niazsanjiInputMapper = niazsanjiInputMapper;
    }

    /**
     * Return a {@link List} of {@link NiazsanjiInputDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<NiazsanjiInputDTO> findByCriteria(NiazsanjiInputCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<NiazsanjiInput> specification = createSpecification(criteria);
        return niazsanjiInputMapper.toDto(niazsanjiInputRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link NiazsanjiInputDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<NiazsanjiInputDTO> findByCriteria(NiazsanjiInputCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<NiazsanjiInput> specification = createSpecification(criteria);
        return niazsanjiInputRepository.findAll(specification, page)
            .map(niazsanjiInputMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(NiazsanjiInputCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<NiazsanjiInput> specification = createSpecification(criteria);
        return niazsanjiInputRepository.count(specification);
    }

    /**
     * Function to convert NiazsanjiInputCriteria to a {@link Specification}
     */
    private Specification<NiazsanjiInput> createSpecification(NiazsanjiInputCriteria criteria) {
        Specification<NiazsanjiInput> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), NiazsanjiInput_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), NiazsanjiInput_.title));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), NiazsanjiInput_.description));
            }
            if (criteria.getDisplayOrder() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDisplayOrder(), NiazsanjiInput_.displayOrder));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), NiazsanjiInput_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), NiazsanjiInput_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), NiazsanjiInput_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), NiazsanjiInput_.modifyDate));
            }
            if (criteria.getFinalNiazsanjiReportId() != null) {
                specification = specification.and(buildSpecification(criteria.getFinalNiazsanjiReportId(),
                    root -> root.join(NiazsanjiInput_.finalNiazsanjiReports, JoinType.LEFT).get(FinalNiazsanjiReport_.id)));
            }
            if (criteria.getNiazsanjiOtherId() != null) {
                specification = specification.and(buildSpecification(criteria.getNiazsanjiOtherId(),
                    root -> root.join(NiazsanjiInput_.niazsanjiOthers, JoinType.LEFT).get(NiazsanjiOther_.id)));
            }
            if (criteria.getRequestOtherNiazsanjiId() != null) {
                specification = specification.and(buildSpecification(criteria.getRequestOtherNiazsanjiId(),
                    root -> root.join(NiazsanjiInput_.requestOtherNiazsanjis, JoinType.LEFT).get(RequestOtherNiazsanji_.id)));
            }
            if (criteria.getPrioritizeRequestNiazsanjiId() != null) {
                specification = specification.and(buildSpecification(criteria.getPrioritizeRequestNiazsanjiId(),
                    root -> root.join(NiazsanjiInput_.prioritizeRequestNiazsanjis, JoinType.LEFT).get(PrioritizeRequestNiazsanji_.id)));
            }
        }
        return specification;
    }
}
