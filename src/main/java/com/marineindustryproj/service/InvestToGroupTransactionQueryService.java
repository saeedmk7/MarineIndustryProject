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

import com.marineindustryproj.domain.InvestToGroupTransaction;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.InvestToGroupTransactionRepository;
import com.marineindustryproj.service.dto.InvestToGroupTransactionCriteria;
import com.marineindustryproj.service.dto.InvestToGroupTransactionDTO;
import com.marineindustryproj.service.mapper.InvestToGroupTransactionMapper;

/**
 * Service for executing complex queries for InvestToGroupTransaction entities in the database.
 * The main input is a {@link InvestToGroupTransactionCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link InvestToGroupTransactionDTO} or a {@link Page} of {@link InvestToGroupTransactionDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class InvestToGroupTransactionQueryService extends QueryService<InvestToGroupTransaction> {

    private final Logger log = LoggerFactory.getLogger(InvestToGroupTransactionQueryService.class);

    private final InvestToGroupTransactionRepository investToGroupTransactionRepository;

    private final InvestToGroupTransactionMapper investToGroupTransactionMapper;

    public InvestToGroupTransactionQueryService(InvestToGroupTransactionRepository investToGroupTransactionRepository, InvestToGroupTransactionMapper investToGroupTransactionMapper) {
        this.investToGroupTransactionRepository = investToGroupTransactionRepository;
        this.investToGroupTransactionMapper = investToGroupTransactionMapper;
    }

    /**
     * Return a {@link List} of {@link InvestToGroupTransactionDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<InvestToGroupTransactionDTO> findByCriteria(InvestToGroupTransactionCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<InvestToGroupTransaction> specification = createSpecification(criteria);
        return investToGroupTransactionMapper.toDto(investToGroupTransactionRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link InvestToGroupTransactionDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<InvestToGroupTransactionDTO> findByCriteria(InvestToGroupTransactionCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<InvestToGroupTransaction> specification = createSpecification(criteria);
        return investToGroupTransactionRepository.findAll(specification, page)
            .map(investToGroupTransactionMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(InvestToGroupTransactionCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<InvestToGroupTransaction> specification = createSpecification(criteria);
        return investToGroupTransactionRepository.count(specification);
    }

    /**
     * Function to convert InvestToGroupTransactionCriteria to a {@link Specification}
     */
    private Specification<InvestToGroupTransaction> createSpecification(InvestToGroupTransactionCriteria criteria) {
        Specification<InvestToGroupTransaction> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), InvestToGroupTransaction_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), InvestToGroupTransaction_.title));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), InvestToGroupTransaction_.description));
            }
            if (criteria.getInvestDate() != null) {
                specification = specification.and(buildStringSpecification(criteria.getInvestDate(), InvestToGroupTransaction_.investDate));
            }
            if (criteria.getInvestYear() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getInvestYear(), InvestToGroupTransaction_.investYear));
            }
            if (criteria.getInvestAmount() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getInvestAmount(), InvestToGroupTransaction_.investAmount));
            }
            if (criteria.getCheckNumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCheckNumber(), InvestToGroupTransaction_.checkNumber));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), InvestToGroupTransaction_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), InvestToGroupTransaction_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), InvestToGroupTransaction_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), InvestToGroupTransaction_.modifyDate));
            }
            if (criteria.getGuid() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGuid(), InvestToGroupTransaction_.guid));
            }
            if (criteria.getOrganizationChartId() != null) {
                specification = specification.and(buildSpecification(criteria.getOrganizationChartId(),
                    root -> root.join(InvestToGroupTransaction_.organizationChart, JoinType.LEFT).get(OrganizationChart_.id)));
            }
        }
        return specification;
    }
}
