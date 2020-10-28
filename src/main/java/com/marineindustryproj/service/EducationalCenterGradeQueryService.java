package com.marineindustryproj.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.marineindustryproj.domain.EducationalCenterGrade;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.EducationalCenterGradeRepository;
import com.marineindustryproj.service.dto.EducationalCenterGradeCriteria;
import com.marineindustryproj.service.dto.EducationalCenterGradeDTO;
import com.marineindustryproj.service.mapper.EducationalCenterGradeMapper;

/**
 * Service for executing complex queries for EducationalCenterGrade entities in the database.
 * The main input is a {@link EducationalCenterGradeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link EducationalCenterGradeDTO} or a {@link Page} of {@link EducationalCenterGradeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class EducationalCenterGradeQueryService extends QueryService<EducationalCenterGrade> {

    private final Logger log = LoggerFactory.getLogger(EducationalCenterGradeQueryService.class);

    private final EducationalCenterGradeRepository educationalCenterGradeRepository;

    private final EducationalCenterGradeMapper educationalCenterGradeMapper;

    public EducationalCenterGradeQueryService(EducationalCenterGradeRepository educationalCenterGradeRepository, EducationalCenterGradeMapper educationalCenterGradeMapper) {
        this.educationalCenterGradeRepository = educationalCenterGradeRepository;
        this.educationalCenterGradeMapper = educationalCenterGradeMapper;
    }

    /**
     * Return a {@link List} of {@link EducationalCenterGradeDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<EducationalCenterGradeDTO> findByCriteria(EducationalCenterGradeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<EducationalCenterGrade> specification = createSpecification(criteria);
        return educationalCenterGradeMapper.toDto(educationalCenterGradeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link EducationalCenterGradeDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<EducationalCenterGradeDTO> findByCriteria(EducationalCenterGradeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<EducationalCenterGrade> specification = createSpecification(criteria);
        return educationalCenterGradeRepository.findAll(specification, page)
            .map(educationalCenterGradeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(EducationalCenterGradeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<EducationalCenterGrade> specification = createSpecification(criteria);
        return educationalCenterGradeRepository.count(specification);
    }

    @Transactional(readOnly = true)
    public Boolean isThisBiggestDate(Integer year, Integer month, long educationalCenterId) {
        EducationalCenterGradeCriteria criteria = new EducationalCenterGradeCriteria();

        LongFilter educationalCenterFilter = new LongFilter();
        educationalCenterFilter.setEquals(educationalCenterId);

        IntegerFilter yearFilter = new IntegerFilter();
        yearFilter.setGreaterOrEqualThan(year);

        IntegerFilter monthFilter = new IntegerFilter();
        monthFilter.setGreaterOrEqualThan(month);

        criteria.setEducationalCenterId(educationalCenterFilter);
        criteria.setYear(yearFilter);
        criteria.setMonth(monthFilter);

        final Specification<EducationalCenterGrade> specification = createSpecification(criteria);
        return educationalCenterGradeRepository.count(specification) > 0 ? false : true;

    }

    /**
     * Function to convert EducationalCenterGradeCriteria to a {@link Specification}
     */
    private Specification<EducationalCenterGrade> createSpecification(EducationalCenterGradeCriteria criteria) {
        Specification<EducationalCenterGrade> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), EducationalCenterGrade_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), EducationalCenterGrade_.title));
            }
            if (criteria.getTotalScore() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTotalScore(), EducationalCenterGrade_.totalScore));
            }
            if (criteria.getTotalScorePercent() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTotalScorePercent(), EducationalCenterGrade_.totalScorePercent));
            }
            if (criteria.getGrade() != null) {
                specification = specification.and(buildSpecification(criteria.getGrade(), EducationalCenterGrade_.grade));
            }
            if (criteria.getEvaluateDate() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEvaluateDate(), EducationalCenterGrade_.evaluateDate));
            }
            if (criteria.getYear() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getYear(), EducationalCenterGrade_.year));
            }
            if (criteria.getMonth() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMonth(), EducationalCenterGrade_.month));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), EducationalCenterGrade_.description));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), EducationalCenterGrade_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), EducationalCenterGrade_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), EducationalCenterGrade_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), EducationalCenterGrade_.modifyDate));
            }
            if (criteria.getEducationalCenterGradeScoreId() != null) {
                specification = specification.and(buildSpecification(criteria.getEducationalCenterGradeScoreId(),
                    root -> root.join(EducationalCenterGrade_.educationalCenterGradeScores, JoinType.LEFT).get(EducationalCenterGradeScore_.id)));
            }
            if (criteria.getEvaluatorOpinionId() != null) {
                specification = specification.and(buildSpecification(criteria.getEvaluatorOpinionId(),
                    root -> root.join(EducationalCenterGrade_.evaluatorOpinions, JoinType.LEFT).get(EvaluatorOpinion_.id)));
            }
            if (criteria.getDocumentId() != null) {
                specification = specification.and(buildSpecification(criteria.getDocumentId(),
                    root -> root.join(EducationalCenterGrade_.documents, JoinType.LEFT).get(Document_.id)));
            }
            if (criteria.getEducationalCenterServiceId() != null) {
                specification = specification.and(buildSpecification(criteria.getEducationalCenterServiceId(),
                    root -> root.join(EducationalCenterGrade_.educationalCenterService, JoinType.LEFT).get(EducationalCenterService_.id)));
            }
            if (criteria.getEducationalCenterId() != null) {
                specification = specification.and(buildSpecification(criteria.getEducationalCenterId(),
                    root -> root.join(EducationalCenterGrade_.educationalCenter, JoinType.LEFT).get(EducationalCenter_.id)));
            }
            if (criteria.getEducationalCenterGroupId() != null) {
                specification = specification.and(buildSpecification(criteria.getEducationalCenterGroupId(),
                    root -> root.join(EducationalCenterGrade_.educationalCenterGroup, JoinType.LEFT).get(EducationalCenterGroup_.id)));
            }
        }
        return specification;
    }
}
