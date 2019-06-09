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

import com.marineindustryproj.domain.Instruction;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.InstructionRepository;
import com.marineindustryproj.service.dto.InstructionCriteria;
import com.marineindustryproj.service.dto.InstructionDTO;
import com.marineindustryproj.service.mapper.InstructionMapper;

/**
 * Service for executing complex queries for Instruction entities in the database.
 * The main input is a {@link InstructionCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link InstructionDTO} or a {@link Page} of {@link InstructionDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class InstructionQueryService extends QueryService<Instruction> {

    private final Logger log = LoggerFactory.getLogger(InstructionQueryService.class);

    private final InstructionRepository instructionRepository;

    private final InstructionMapper instructionMapper;

    public InstructionQueryService(InstructionRepository instructionRepository, InstructionMapper instructionMapper) {
        this.instructionRepository = instructionRepository;
        this.instructionMapper = instructionMapper;
    }

    /**
     * Return a {@link List} of {@link InstructionDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<InstructionDTO> findByCriteria(InstructionCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Instruction> specification = createSpecification(criteria);
        return instructionMapper.toDto(instructionRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link InstructionDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<InstructionDTO> findByCriteria(InstructionCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Instruction> specification = createSpecification(criteria);
        return instructionRepository.findAll(specification, page)
            .map(instructionMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(InstructionCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Instruction> specification = createSpecification(criteria);
        return instructionRepository.count(specification);
    }

    /**
     * Function to convert InstructionCriteria to a {@link Specification}
     */
    private Specification<Instruction> createSpecification(InstructionCriteria criteria) {
        Specification<Instruction> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Instruction_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), Instruction_.title));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), Instruction_.description));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), Instruction_.code));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), Instruction_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), Instruction_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), Instruction_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), Instruction_.modifyDate));
            }
            if (criteria.getGuid() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGuid(), Instruction_.guid));
            }
            if (criteria.getDocumentId() != null) {
                specification = specification.and(buildSpecification(criteria.getDocumentId(),
                    root -> root.join(Instruction_.documents, JoinType.LEFT).get(Document_.id)));
            }
        }
        return specification;
    }
}
