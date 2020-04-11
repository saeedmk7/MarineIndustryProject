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

import com.marineindustryproj.domain.NiazsanjiPersonGrade;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.NiazsanjiPersonGradeRepository;
import com.marineindustryproj.service.dto.NiazsanjiPersonGradeCriteria;
import com.marineindustryproj.service.dto.NiazsanjiPersonGradeDTO;
import com.marineindustryproj.service.mapper.NiazsanjiPersonGradeMapper;

/**
 * Service for executing complex queries for NiazsanjiPersonGrade entities in the database.
 * The main input is a {@link NiazsanjiPersonGradeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link NiazsanjiPersonGradeDTO} or a {@link Page} of {@link NiazsanjiPersonGradeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class NiazsanjiPersonGradeQueryService extends QueryService<NiazsanjiPersonGrade> {

    private final Logger log = LoggerFactory.getLogger(NiazsanjiPersonGradeQueryService.class);

    private final NiazsanjiPersonGradeRepository niazsanjiPersonGradeRepository;

    private final NiazsanjiPersonGradeMapper niazsanjiPersonGradeMapper;

    public NiazsanjiPersonGradeQueryService(NiazsanjiPersonGradeRepository niazsanjiPersonGradeRepository, NiazsanjiPersonGradeMapper niazsanjiPersonGradeMapper) {
        this.niazsanjiPersonGradeRepository = niazsanjiPersonGradeRepository;
        this.niazsanjiPersonGradeMapper = niazsanjiPersonGradeMapper;
    }

    /**
     * Return a {@link List} of {@link NiazsanjiPersonGradeDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<NiazsanjiPersonGradeDTO> findByCriteria(NiazsanjiPersonGradeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<NiazsanjiPersonGrade> specification = createSpecification(criteria);
        return niazsanjiPersonGradeMapper.toDto(niazsanjiPersonGradeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link NiazsanjiPersonGradeDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<NiazsanjiPersonGradeDTO> findByCriteria(NiazsanjiPersonGradeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<NiazsanjiPersonGrade> specification = createSpecification(criteria);
        return niazsanjiPersonGradeRepository.findAll(specification, page)
            .map(niazsanjiPersonGradeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(NiazsanjiPersonGradeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<NiazsanjiPersonGrade> specification = createSpecification(criteria);
        return niazsanjiPersonGradeRepository.count(specification);
    }

    /**
     * Function to convert NiazsanjiPersonGradeCriteria to a {@link Specification}
     */
    private Specification<NiazsanjiPersonGrade> createSpecification(NiazsanjiPersonGradeCriteria criteria) {
        Specification<NiazsanjiPersonGrade> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), NiazsanjiPersonGrade_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), NiazsanjiPersonGrade_.title));
            }
            if (criteria.getTotalScore() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTotalScore(), NiazsanjiPersonGrade_.totalScore));
            }
            if (criteria.getTotalScorePercent() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTotalScorePercent(), NiazsanjiPersonGrade_.totalScorePercent));
            }
            if (criteria.getGrade() != null) {
                specification = specification.and(buildSpecification(criteria.getGrade(), NiazsanjiPersonGrade_.grade));
            }
            if (criteria.getEvaluateDate() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEvaluateDate(), NiazsanjiPersonGrade_.evaluateDate));
            }
            if (criteria.getYear() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getYear(), NiazsanjiPersonGrade_.year));
            }
            if (criteria.getMonth() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMonth(), NiazsanjiPersonGrade_.month));
            }
            if (criteria.getStrength() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStrength(), NiazsanjiPersonGrade_.strength));
            }
            if (criteria.getImprovement() != null) {
                specification = specification.and(buildStringSpecification(criteria.getImprovement(), NiazsanjiPersonGrade_.improvement));
            }
            if (criteria.getWhatDoYouDo() != null) {
                specification = specification.and(buildStringSpecification(criteria.getWhatDoYouDo(), NiazsanjiPersonGrade_.whatDoYouDo));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), NiazsanjiPersonGrade_.description));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), NiazsanjiPersonGrade_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), NiazsanjiPersonGrade_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), NiazsanjiPersonGrade_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), NiazsanjiPersonGrade_.modifyDate));
            }
            if (criteria.getNiazsanjiPersonGradeScoreId() != null) {
                specification = specification.and(buildSpecification(criteria.getNiazsanjiPersonGradeScoreId(),
                    root -> root.join(NiazsanjiPersonGrade_.niazsanjiPersonGradeScores, JoinType.LEFT).get(NiazsanjiPersonGradeScore_.id)));
            }
            if (criteria.getDocumentId() != null) {
                specification = specification.and(buildSpecification(criteria.getDocumentId(),
                    root -> root.join(NiazsanjiPersonGrade_.documents, JoinType.LEFT).get(Document_.id)));
            }
            if (criteria.getFinalNiazsanjiReportPersonId() != null) {
                specification = specification.and(buildSpecification(criteria.getFinalNiazsanjiReportPersonId(),
                    root -> root.join(NiazsanjiPersonGrade_.finalNiazsanjiReportPerson, JoinType.LEFT).get(FinalNiazsanjiReportPerson_.id)));
            }
        }
        return specification;
    }
}
