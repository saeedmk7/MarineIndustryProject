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

import com.marineindustryproj.domain.LevelThreeEffectiveness;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.LevelThreeEffectivenessRepository;
import com.marineindustryproj.service.dto.LevelThreeEffectivenessCriteria;
import com.marineindustryproj.service.dto.LevelThreeEffectivenessDTO;
import com.marineindustryproj.service.mapper.LevelThreeEffectivenessMapper;

/**
 * Service for executing complex queries for LevelThreeEffectiveness entities in the database.
 * The main input is a {@link LevelThreeEffectivenessCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link LevelThreeEffectivenessDTO} or a {@link Page} of {@link LevelThreeEffectivenessDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class LevelThreeEffectivenessQueryService extends QueryService<LevelThreeEffectiveness> {

    private final Logger log = LoggerFactory.getLogger(LevelThreeEffectivenessQueryService.class);

    private final LevelThreeEffectivenessRepository levelThreeEffectivenessRepository;

    private final LevelThreeEffectivenessMapper levelThreeEffectivenessMapper;

    public LevelThreeEffectivenessQueryService(LevelThreeEffectivenessRepository levelThreeEffectivenessRepository, LevelThreeEffectivenessMapper levelThreeEffectivenessMapper) {
        this.levelThreeEffectivenessRepository = levelThreeEffectivenessRepository;
        this.levelThreeEffectivenessMapper = levelThreeEffectivenessMapper;
    }

    /**
     * Return a {@link List} of {@link LevelThreeEffectivenessDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<LevelThreeEffectivenessDTO> findByCriteria(LevelThreeEffectivenessCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<LevelThreeEffectiveness> specification = createSpecification(criteria);
        return levelThreeEffectivenessMapper.toDto(levelThreeEffectivenessRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link LevelThreeEffectivenessDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<LevelThreeEffectivenessDTO> findByCriteria(LevelThreeEffectivenessCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<LevelThreeEffectiveness> specification = createSpecification(criteria);
        return levelThreeEffectivenessRepository.findAll(specification, page)
            .map(levelThreeEffectivenessMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(LevelThreeEffectivenessCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<LevelThreeEffectiveness> specification = createSpecification(criteria);
        return levelThreeEffectivenessRepository.count(specification);
    }

    /**
     * Function to convert LevelThreeEffectivenessCriteria to a {@link Specification}
     */
    private Specification<LevelThreeEffectiveness> createSpecification(LevelThreeEffectivenessCriteria criteria) {
        Specification<LevelThreeEffectiveness> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), LevelThreeEffectiveness_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), LevelThreeEffectiveness_.title));
            }
            if (criteria.getTotalScore() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTotalScore(), LevelThreeEffectiveness_.totalScore));
            }
            if (criteria.getTotalScorePercent() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTotalScorePercent(), LevelThreeEffectiveness_.totalScorePercent));
            }
            if (criteria.getGrade() != null) {
                specification = specification.and(buildSpecification(criteria.getGrade(), LevelThreeEffectiveness_.grade));
            }
            if (criteria.getEvaluateDate() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEvaluateDate(), LevelThreeEffectiveness_.evaluateDate));
            }
            if (criteria.getYear() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getYear(), LevelThreeEffectiveness_.year));
            }
            if (criteria.getMonth() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMonth(), LevelThreeEffectiveness_.month));
            }
            if (criteria.getStrength() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStrength(), LevelThreeEffectiveness_.strength));
            }
            if (criteria.getImprovement() != null) {
                specification = specification.and(buildStringSpecification(criteria.getImprovement(), LevelThreeEffectiveness_.improvement));
            }
            if (criteria.getWhatDoYouDo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getWhatDoYouDo(), LevelThreeEffectiveness_.whatDoYouDo));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), LevelThreeEffectiveness_.description));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), LevelThreeEffectiveness_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), LevelThreeEffectiveness_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), LevelThreeEffectiveness_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), LevelThreeEffectiveness_.modifyDate));
            }
            if (criteria.getLevelThreeScoreId() != null) {
                specification = specification.and(buildSpecification(criteria.getLevelThreeScoreId(),
                    root -> root.join(LevelThreeEffectiveness_.levelThreeScores, JoinType.LEFT).get(LevelThreeScore_.id)));
            }
            if (criteria.getDocumentId() != null) {
                specification = specification.and(buildSpecification(criteria.getDocumentId(),
                    root -> root.join(LevelThreeEffectiveness_.documents, JoinType.LEFT).get(Document_.id)));
            }
            if (criteria.getFinalNiazsanjiReportPersonId() != null) {
                specification = specification.and(buildSpecification(criteria.getFinalNiazsanjiReportPersonId(),
                    root -> root.join(LevelThreeEffectiveness_.finalNiazsanjiReportPerson, JoinType.LEFT).get(FinalNiazsanjiReportPerson_.id)));
            }
        }
        return specification;
    }
}
