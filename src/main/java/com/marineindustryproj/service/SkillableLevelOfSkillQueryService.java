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

import com.marineindustryproj.domain.SkillableLevelOfSkill;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.SkillableLevelOfSkillRepository;
import com.marineindustryproj.service.dto.SkillableLevelOfSkillCriteria;
import com.marineindustryproj.service.dto.SkillableLevelOfSkillDTO;
import com.marineindustryproj.service.mapper.SkillableLevelOfSkillMapper;

/**
 * Service for executing complex queries for SkillableLevelOfSkill entities in the database.
 * The main input is a {@link SkillableLevelOfSkillCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link SkillableLevelOfSkillDTO} or a {@link Page} of {@link SkillableLevelOfSkillDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class SkillableLevelOfSkillQueryService extends QueryService<SkillableLevelOfSkill> {

    private final Logger log = LoggerFactory.getLogger(SkillableLevelOfSkillQueryService.class);

    private final SkillableLevelOfSkillRepository skillableLevelOfSkillRepository;

    private final SkillableLevelOfSkillMapper skillableLevelOfSkillMapper;

    public SkillableLevelOfSkillQueryService(SkillableLevelOfSkillRepository skillableLevelOfSkillRepository, SkillableLevelOfSkillMapper skillableLevelOfSkillMapper) {
        this.skillableLevelOfSkillRepository = skillableLevelOfSkillRepository;
        this.skillableLevelOfSkillMapper = skillableLevelOfSkillMapper;
    }

    /**
     * Return a {@link List} of {@link SkillableLevelOfSkillDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<SkillableLevelOfSkillDTO> findByCriteria(SkillableLevelOfSkillCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<SkillableLevelOfSkill> specification = createSpecification(criteria);
        return skillableLevelOfSkillMapper.toDto(skillableLevelOfSkillRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link SkillableLevelOfSkillDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<SkillableLevelOfSkillDTO> findByCriteria(SkillableLevelOfSkillCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<SkillableLevelOfSkill> specification = createSpecification(criteria);
        return skillableLevelOfSkillRepository.findAll(specification, page)
            .map(skillableLevelOfSkillMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(SkillableLevelOfSkillCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<SkillableLevelOfSkill> specification = createSpecification(criteria);
        return skillableLevelOfSkillRepository.count(specification);
    }

    /**
     * Function to convert SkillableLevelOfSkillCriteria to a {@link Specification}
     */
    private Specification<SkillableLevelOfSkill> createSpecification(SkillableLevelOfSkillCriteria criteria) {
        Specification<SkillableLevelOfSkill> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), SkillableLevelOfSkill_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), SkillableLevelOfSkill_.title));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), SkillableLevelOfSkill_.code));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), SkillableLevelOfSkill_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), SkillableLevelOfSkill_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), SkillableLevelOfSkill_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), SkillableLevelOfSkill_.modifyDate));
            }
            if (criteria.getEducationalModuleId() != null) {
                specification = specification.and(buildSpecification(criteria.getEducationalModuleId(),
                    root -> root.join(SkillableLevelOfSkill_.educationalModules, JoinType.LEFT).get(EducationalModule_.id)));
            }
            if (criteria.getRequestEducationalModuleId() != null) {
                specification = specification.and(buildSpecification(criteria.getRequestEducationalModuleId(),
                    root -> root.join(SkillableLevelOfSkill_.requestEducationalModules, JoinType.LEFT).get(RequestEducationalModule_.id)));
            }
        }
        return specification;
    }
}
