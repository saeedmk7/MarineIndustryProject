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

import com.marineindustryproj.domain.TeacherGrade;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.TeacherGradeRepository;
import com.marineindustryproj.service.dto.TeacherGradeCriteria;
import com.marineindustryproj.service.dto.TeacherGradeDTO;
import com.marineindustryproj.service.mapper.TeacherGradeMapper;

/**
 * Service for executing complex queries for TeacherGrade entities in the database.
 * The main input is a {@link TeacherGradeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link TeacherGradeDTO} or a {@link Page} of {@link TeacherGradeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class TeacherGradeQueryService extends QueryService<TeacherGrade> {

    private final Logger log = LoggerFactory.getLogger(TeacherGradeQueryService.class);

    private final TeacherGradeRepository teacherGradeRepository;

    private final TeacherGradeMapper teacherGradeMapper;

    public TeacherGradeQueryService(TeacherGradeRepository teacherGradeRepository, TeacherGradeMapper teacherGradeMapper) {
        this.teacherGradeRepository = teacherGradeRepository;
        this.teacherGradeMapper = teacherGradeMapper;
    }

    /**
     * Return a {@link List} of {@link TeacherGradeDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<TeacherGradeDTO> findByCriteria(TeacherGradeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<TeacherGrade> specification = createSpecification(criteria);
        return teacherGradeMapper.toDto(teacherGradeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link TeacherGradeDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<TeacherGradeDTO> findByCriteria(TeacherGradeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<TeacherGrade> specification = createSpecification(criteria);
        return teacherGradeRepository.findAll(specification, page)
            .map(teacherGradeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(TeacherGradeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<TeacherGrade> specification = createSpecification(criteria);
        return teacherGradeRepository.count(specification);
    }

    @Transactional(readOnly = true)
    public Boolean isThisBiggestDate(Integer year, Integer month, long teacherId) {
        TeacherGradeCriteria criteria = new TeacherGradeCriteria();

        LongFilter teacherFilter = new LongFilter();
        teacherFilter.setEquals(teacherId);

        IntegerFilter yearFilter = new IntegerFilter();
        yearFilter.setGreaterOrEqualThan(year);

        IntegerFilter monthFilter = new IntegerFilter();
        monthFilter.setGreaterOrEqualThan(month);

        criteria.setTeacherId(teacherFilter);
        criteria.setYear(yearFilter);
        criteria.setMonth(monthFilter);

        final Specification<TeacherGrade> specification = createSpecification(criteria);
        return teacherGradeRepository.count(specification) > 0 ? false : true;

    }
    /**
     * Function to convert TeacherGradeCriteria to a {@link Specification}
     */
    private Specification<TeacherGrade> createSpecification(TeacherGradeCriteria criteria) {
        Specification<TeacherGrade> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), TeacherGrade_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), TeacherGrade_.title));
            }
            if (criteria.getVersion() != null) {
                specification = specification.and(buildStringSpecification(criteria.getVersion(), TeacherGrade_.version));
            }
            if (criteria.getTotalScore() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTotalScore(), TeacherGrade_.totalScore));
            }
            if (criteria.getTotalScorePercent() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTotalScorePercent(), TeacherGrade_.totalScorePercent));
            }
            if (criteria.getGrade() != null) {
                specification = specification.and(buildSpecification(criteria.getGrade(), TeacherGrade_.grade));
            }
            if (criteria.getEvaluateDate() != null) {
                specification = specification.and(buildStringSpecification(criteria.getEvaluateDate(), TeacherGrade_.evaluateDate));
            }
            if (criteria.getYear() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getYear(), TeacherGrade_.year));
            }
            if (criteria.getMonth() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getMonth(), TeacherGrade_.month));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), TeacherGrade_.description));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), TeacherGrade_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), TeacherGrade_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), TeacherGrade_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), TeacherGrade_.modifyDate));
            }
            if (criteria.getTeacherGradeScoreId() != null) {
                specification = specification.and(buildSpecification(criteria.getTeacherGradeScoreId(),
                    root -> root.join(TeacherGrade_.teacherGradeScores, JoinType.LEFT).get(TeacherGradeScore_.id)));
            }
            if (criteria.getDocumentId() != null) {
                specification = specification.and(buildSpecification(criteria.getDocumentId(),
                    root -> root.join(TeacherGrade_.documents, JoinType.LEFT).get(Document_.id)));
            }
            if (criteria.getTeacherId() != null) {
                specification = specification.and(buildSpecification(criteria.getTeacherId(),
                    root -> root.join(TeacherGrade_.teacher, JoinType.LEFT).get(Teacher_.id)));
            }
            if (criteria.getTeacherCriteriaGroupId() != null) {
                specification = specification.and(buildSpecification(criteria.getTeacherCriteriaGroupId(),
                    root -> root.join(TeacherGrade_.teacherCriteriaGroup, JoinType.LEFT).get(TeacherCriteriaGroup_.id)));
            }
        }
        return specification;
    }
}
