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

import com.marineindustryproj.domain.DesignNiazsanji;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.DesignNiazsanjiRepository;
import com.marineindustryproj.service.dto.DesignNiazsanjiCriteria;
import com.marineindustryproj.service.dto.DesignNiazsanjiDTO;
import com.marineindustryproj.service.mapper.DesignNiazsanjiMapper;

/**
 * Service for executing complex queries for DesignNiazsanji entities in the database.
 * The main input is a {@link DesignNiazsanjiCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DesignNiazsanjiDTO} or a {@link Page} of {@link DesignNiazsanjiDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DesignNiazsanjiQueryService extends QueryService<DesignNiazsanji> {

    private final Logger log = LoggerFactory.getLogger(DesignNiazsanjiQueryService.class);

    private final DesignNiazsanjiRepository designNiazsanjiRepository;

    private final DesignNiazsanjiMapper designNiazsanjiMapper;

    public DesignNiazsanjiQueryService(DesignNiazsanjiRepository designNiazsanjiRepository, DesignNiazsanjiMapper designNiazsanjiMapper) {
        this.designNiazsanjiRepository = designNiazsanjiRepository;
        this.designNiazsanjiMapper = designNiazsanjiMapper;
    }

    /**
     * Return a {@link List} of {@link DesignNiazsanjiDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DesignNiazsanjiDTO> findByCriteria(DesignNiazsanjiCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<DesignNiazsanji> specification = createSpecification(criteria);
        return designNiazsanjiMapper.toDto(designNiazsanjiRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DesignNiazsanjiDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DesignNiazsanjiDTO> findByCriteria(DesignNiazsanjiCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DesignNiazsanji> specification = createSpecification(criteria);
        return designNiazsanjiRepository.findAll(specification, page)
            .map(designNiazsanjiMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DesignNiazsanjiCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<DesignNiazsanji> specification = createSpecification(criteria);
        return designNiazsanjiRepository.count(specification);
    }

    /**
     * Function to convert DesignNiazsanjiCriteria to a {@link Specification}
     */
    private Specification<DesignNiazsanji> createSpecification(DesignNiazsanjiCriteria criteria) {
        Specification<DesignNiazsanji> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), DesignNiazsanji_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), DesignNiazsanji_.title));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), DesignNiazsanji_.code));
            }
            if (criteria.getCostEducationalModule() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCostEducationalModule(), DesignNiazsanji_.costEducationalModule));
            }
            if (criteria.getRestrictionDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getRestrictionDescription(), DesignNiazsanji_.restrictionDescription));
            }
            if (criteria.getGoalsText() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGoalsText(), DesignNiazsanji_.goalsText));
            }
            if (criteria.getPrerequisite() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPrerequisite(), DesignNiazsanji_.prerequisite));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), DesignNiazsanji_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), DesignNiazsanji_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), DesignNiazsanji_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), DesignNiazsanji_.modifyDate));
            }
            if (criteria.getArchived() != null) {
                specification = specification.and(buildSpecification(criteria.getArchived(), DesignNiazsanji_.archived));
            }
            if (criteria.getArchivedUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getArchivedUserLogin(), DesignNiazsanji_.archivedUserLogin));
            }
            if (criteria.getArchivedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getArchivedDate(), DesignNiazsanji_.archivedDate));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStatus(), DesignNiazsanji_.status));
            }
            if (criteria.getRestrictionId() != null) {
                specification = specification.and(buildSpecification(criteria.getRestrictionId(),
                    root -> root.join(DesignNiazsanji_.restrictions, JoinType.LEFT).get(Restriction_.id)));
            }
            if (criteria.getPreJobNiazsanjiId() != null) {
                specification = specification.and(buildSpecification(criteria.getPreJobNiazsanjiId(),
                    root -> root.join(DesignNiazsanji_.preJobNiazsanji, JoinType.LEFT).get(PreJobNiazsanji_.id)));
            }
            if (criteria.getCourseTypeId() != null) {
                specification = specification.and(buildSpecification(criteria.getCourseTypeId(),
                    root -> root.join(DesignNiazsanji_.courseType, JoinType.LEFT).get(CourseType_.id)));
            }
            if (criteria.getEducationalModuleId() != null) {
                specification = specification.and(buildSpecification(criteria.getEducationalModuleId(),
                    root -> root.join(DesignNiazsanji_.educationalModule, JoinType.LEFT).get(EducationalModule_.id)));
            }
            if (criteria.getTeachingApproachId() != null) {
                specification = specification.and(buildSpecification(criteria.getTeachingApproachId(),
                    root -> root.join(DesignNiazsanji_.teachingApproach, JoinType.LEFT).get(TeachingApproach_.id)));
            }
        }
        return specification;
    }
}
