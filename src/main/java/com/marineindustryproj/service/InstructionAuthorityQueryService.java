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

import com.marineindustryproj.domain.InstructionAuthority;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.InstructionAuthorityRepository;
import com.marineindustryproj.service.dto.InstructionAuthorityCriteria;
import com.marineindustryproj.service.dto.InstructionAuthorityDTO;
import com.marineindustryproj.service.mapper.InstructionAuthorityMapper;

/**
 * Service for executing complex queries for InstructionAuthority entities in the database.
 * The main input is a {@link InstructionAuthorityCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link InstructionAuthorityDTO} or a {@link Page} of {@link InstructionAuthorityDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class InstructionAuthorityQueryService extends QueryService<InstructionAuthority> {

    private final Logger log = LoggerFactory.getLogger(InstructionAuthorityQueryService.class);

    private final InstructionAuthorityRepository instructionAuthorityRepository;

    private final InstructionAuthorityMapper instructionAuthorityMapper;

    public InstructionAuthorityQueryService(InstructionAuthorityRepository instructionAuthorityRepository, InstructionAuthorityMapper instructionAuthorityMapper) {
        this.instructionAuthorityRepository = instructionAuthorityRepository;
        this.instructionAuthorityMapper = instructionAuthorityMapper;
    }

    /**
     * Return a {@link List} of {@link InstructionAuthorityDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<InstructionAuthorityDTO> findByCriteria(InstructionAuthorityCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<InstructionAuthority> specification = createSpecification(criteria);
        return instructionAuthorityMapper.toDto(instructionAuthorityRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link InstructionAuthorityDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<InstructionAuthorityDTO> findByCriteria(InstructionAuthorityCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<InstructionAuthority> specification = createSpecification(criteria);
        return instructionAuthorityRepository.findAll(specification, page)
            .map(instructionAuthorityMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(InstructionAuthorityCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<InstructionAuthority> specification = createSpecification(criteria);
        return instructionAuthorityRepository.count(specification);
    }

    /**
     * Function to convert InstructionAuthorityCriteria to a {@link Specification}
     */
    private Specification<InstructionAuthority> createSpecification(InstructionAuthorityCriteria criteria) {
        Specification<InstructionAuthority> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), InstructionAuthority_.id));
            }
            if (criteria.getAuthorityName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAuthorityName(), InstructionAuthority_.authorityName));
            }
            if (criteria.getHasEditPermission() != null) {
                specification = specification.and(buildSpecification(criteria.getHasEditPermission(), InstructionAuthority_.hasEditPermission));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), InstructionAuthority_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), InstructionAuthority_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), InstructionAuthority_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), InstructionAuthority_.modifyDate));
            }
            if (criteria.getInstructionId() != null) {
                specification = specification.and(buildSpecification(criteria.getInstructionId(),
                    root -> root.join(InstructionAuthority_.instruction, JoinType.LEFT).get(Instruction_.id)));
            }
        }
        return specification;
    }
}
