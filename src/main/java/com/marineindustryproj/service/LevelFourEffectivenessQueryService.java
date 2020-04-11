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

import com.marineindustryproj.domain.LevelFourEffectiveness;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.LevelFourEffectivenessRepository;
import com.marineindustryproj.service.dto.LevelFourEffectivenessCriteria;
import com.marineindustryproj.service.dto.LevelFourEffectivenessDTO;
import com.marineindustryproj.service.mapper.LevelFourEffectivenessMapper;

/**
 * Service for executing complex queries for LevelFourEffectiveness entities in the database.
 * The main input is a {@link LevelFourEffectivenessCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link LevelFourEffectivenessDTO} or a {@link Page} of {@link LevelFourEffectivenessDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class LevelFourEffectivenessQueryService extends QueryService<LevelFourEffectiveness> {

    private final Logger log = LoggerFactory.getLogger(LevelFourEffectivenessQueryService.class);

    private final LevelFourEffectivenessRepository levelFourEffectivenessRepository;

    private final LevelFourEffectivenessMapper levelFourEffectivenessMapper;

    public LevelFourEffectivenessQueryService(LevelFourEffectivenessRepository levelFourEffectivenessRepository, LevelFourEffectivenessMapper levelFourEffectivenessMapper) {
        this.levelFourEffectivenessRepository = levelFourEffectivenessRepository;
        this.levelFourEffectivenessMapper = levelFourEffectivenessMapper;
    }

    /**
     * Return a {@link List} of {@link LevelFourEffectivenessDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<LevelFourEffectivenessDTO> findByCriteria(LevelFourEffectivenessCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<LevelFourEffectiveness> specification = createSpecification(criteria);
        return levelFourEffectivenessMapper.toDto(levelFourEffectivenessRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link LevelFourEffectivenessDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<LevelFourEffectivenessDTO> findByCriteria(LevelFourEffectivenessCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<LevelFourEffectiveness> specification = createSpecification(criteria);
        return levelFourEffectivenessRepository.findAll(specification, page)
            .map(levelFourEffectivenessMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(LevelFourEffectivenessCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<LevelFourEffectiveness> specification = createSpecification(criteria);
        return levelFourEffectivenessRepository.count(specification);
    }

    /**
     * Function to convert LevelFourEffectivenessCriteria to a {@link Specification}
     */
    private Specification<LevelFourEffectiveness> createSpecification(LevelFourEffectivenessCriteria criteria) {
        Specification<LevelFourEffectiveness> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), LevelFourEffectiveness_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), LevelFourEffectiveness_.title));
            }
            if (criteria.getTotalScore() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTotalScore(), LevelFourEffectiveness_.totalScore));
            }
            if (criteria.getTotalScorePercent() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTotalScorePercent(), LevelFourEffectiveness_.totalScorePercent));
            }
            if (criteria.getGrade() != null) {
                specification = specification.and(buildSpecification(criteria.getGrade(), LevelFourEffectiveness_.grade));
            }
            if (criteria.getEvaluateDate() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEvaluateDate(), LevelFourEffectiveness_.evaluateDate));
            }
            if (criteria.getYear() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getYear(), LevelFourEffectiveness_.year));
            }
            if (criteria.getMonth() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMonth(), LevelFourEffectiveness_.month));
            }
            if (criteria.getStrength() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStrength(), LevelFourEffectiveness_.strength));
            }
            if (criteria.getImprovement() != null) {
                specification = specification.and(buildStringSpecification(criteria.getImprovement(), LevelFourEffectiveness_.improvement));
            }
            if (criteria.getWhatDoYouDo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getWhatDoYouDo(), LevelFourEffectiveness_.whatDoYouDo));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), LevelFourEffectiveness_.description));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), LevelFourEffectiveness_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), LevelFourEffectiveness_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), LevelFourEffectiveness_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), LevelFourEffectiveness_.modifyDate));
            }
            if (criteria.getLevelFourScoreId() != null) {
                specification = specification.and(buildSpecification(criteria.getLevelFourScoreId(),
                    root -> root.join(LevelFourEffectiveness_.levelFourScores, JoinType.LEFT).get(LevelFourScore_.id)));
            }
            if (criteria.getDocumentId() != null) {
                specification = specification.and(buildSpecification(criteria.getDocumentId(),
                    root -> root.join(LevelFourEffectiveness_.documents, JoinType.LEFT).get(Document_.id)));
            }
            if (criteria.getFinalNiazsanjiReportPersonId() != null) {
                specification = specification.and(buildSpecification(criteria.getFinalNiazsanjiReportPersonId(),
                    root -> root.join(LevelFourEffectiveness_.finalNiazsanjiReportPerson, JoinType.LEFT).get(FinalNiazsanjiReportPerson_.id)));
            }
        }
        return specification;
    }
}
