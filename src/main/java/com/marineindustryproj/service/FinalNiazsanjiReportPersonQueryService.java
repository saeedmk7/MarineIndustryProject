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

import com.marineindustryproj.domain.FinalNiazsanjiReportPerson;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.FinalNiazsanjiReportPersonRepository;
import com.marineindustryproj.service.dto.FinalNiazsanjiReportPersonCriteria;
import com.marineindustryproj.service.dto.FinalNiazsanjiReportPersonDTO;
import com.marineindustryproj.service.mapper.FinalNiazsanjiReportPersonMapper;

/**
 * Service for executing complex queries for FinalNiazsanjiReportPerson entities in the database.
 * The main input is a {@link FinalNiazsanjiReportPersonCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link FinalNiazsanjiReportPersonDTO} or a {@link Page} of {@link FinalNiazsanjiReportPersonDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class FinalNiazsanjiReportPersonQueryService extends QueryService<FinalNiazsanjiReportPerson> {

    private final Logger log = LoggerFactory.getLogger(FinalNiazsanjiReportPersonQueryService.class);

    private final FinalNiazsanjiReportPersonRepository finalNiazsanjiReportPersonRepository;

    private final FinalNiazsanjiReportPersonMapper finalNiazsanjiReportPersonMapper;

    public FinalNiazsanjiReportPersonQueryService(FinalNiazsanjiReportPersonRepository finalNiazsanjiReportPersonRepository, FinalNiazsanjiReportPersonMapper finalNiazsanjiReportPersonMapper) {
        this.finalNiazsanjiReportPersonRepository = finalNiazsanjiReportPersonRepository;
        this.finalNiazsanjiReportPersonMapper = finalNiazsanjiReportPersonMapper;
    }

    /**
     * Return a {@link List} of {@link FinalNiazsanjiReportPersonDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<FinalNiazsanjiReportPersonDTO> findByCriteria(FinalNiazsanjiReportPersonCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<FinalNiazsanjiReportPerson> specification = createSpecification(criteria);
        return finalNiazsanjiReportPersonMapper.toDto(finalNiazsanjiReportPersonRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link FinalNiazsanjiReportPersonDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<FinalNiazsanjiReportPersonDTO> findByCriteria(FinalNiazsanjiReportPersonCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<FinalNiazsanjiReportPerson> specification = createSpecification(criteria);
        return finalNiazsanjiReportPersonRepository.findAll(specification, page)
            .map(finalNiazsanjiReportPersonMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(FinalNiazsanjiReportPersonCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<FinalNiazsanjiReportPerson> specification = createSpecification(criteria);
        return finalNiazsanjiReportPersonRepository.count(specification);
    }

    @Transactional(readOnly = true)
    public boolean allThePeopleVote(long finalNiazsanjiReportId) {

        FinalNiazsanjiReportPersonCriteria finalNiazsanjiReportPersonCriteria = new FinalNiazsanjiReportPersonCriteria();

        LongFilter finalNiazsanjiReportIdFilter = new LongFilter();
        finalNiazsanjiReportIdFilter.setEquals(finalNiazsanjiReportId);

        finalNiazsanjiReportPersonCriteria.setFinalNiazsanjiReportId(finalNiazsanjiReportIdFilter);

        long totalPeople = countByCriteria(finalNiazsanjiReportPersonCriteria);

        IntegerFilter statusFilter = new IntegerFilter();
        statusFilter.setGreaterOrEqualThan(20);

        finalNiazsanjiReportPersonCriteria.setStatus(statusFilter);

        long totalPeopleVoted = countByCriteria(finalNiazsanjiReportPersonCriteria);

        if(totalPeople == totalPeopleVoted)
            return true;
        return  false;
    }
    /**
     * Function to convert FinalNiazsanjiReportPersonCriteria to a {@link Specification}
     */
    private Specification<FinalNiazsanjiReportPerson> createSpecification(FinalNiazsanjiReportPersonCriteria criteria) {
        Specification<FinalNiazsanjiReportPerson> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), FinalNiazsanjiReportPerson_.id));
            }
            if (criteria.getNiazSanjiSource() != null) {
                specification = specification.and(buildSpecification(criteria.getNiazSanjiSource(), FinalNiazsanjiReportPerson_.niazSanjiSource));
            }
            if (criteria.getPriceCost() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getPriceCost(), FinalNiazsanjiReportPerson_.priceCost));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), FinalNiazsanjiReportPerson_.description));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), FinalNiazsanjiReportPerson_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), FinalNiazsanjiReportPerson_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), FinalNiazsanjiReportPerson_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), FinalNiazsanjiReportPerson_.modifyDate));
            }
            if (criteria.getArchived() != null) {
                specification = specification.and(buildSpecification(criteria.getArchived(), FinalNiazsanjiReportPerson_.archived));
            }
            if (criteria.getArchivedUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getArchivedUserLogin(), FinalNiazsanjiReportPerson_.archivedUserLogin));
            }
            if (criteria.getArchivedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getArchivedDate(), FinalNiazsanjiReportPerson_.archivedDate));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStatus(), FinalNiazsanjiReportPerson_.status));
            }
            if (criteria.getSourceId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSourceId(), FinalNiazsanjiReportPerson_.sourceId));
            }
            if (criteria.getScoreBeforeTest() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getScoreBeforeTest(), FinalNiazsanjiReportPerson_.scoreBeforeTest));
            }
            if (criteria.getScoreAfterTest() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getScoreAfterTest(), FinalNiazsanjiReportPerson_.scoreAfterTest));
            }
            if (criteria.getAverageScore() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getAverageScore(), FinalNiazsanjiReportPerson_.averageScore));
            }
            if (criteria.getLevelOneScore() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLevelOneScore(), FinalNiazsanjiReportPerson_.levelOneScore));
            }
            if (criteria.getLevelThreeScore() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLevelThreeScore(), FinalNiazsanjiReportPerson_.levelThreeScore));
            }
            if (criteria.getLevelFourScore() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLevelFourScore(), FinalNiazsanjiReportPerson_.levelFourScore));
            }
            if (criteria.getNiazsanjiPersonGradeId() != null) {
                specification = specification.and(buildSpecification(criteria.getNiazsanjiPersonGradeId(),
                    root -> root.join(FinalNiazsanjiReportPerson_.niazsanjiPersonGrades, JoinType.LEFT).get(NiazsanjiPersonGrade_.id)));
            }
            if (criteria.getLevelThreeEffectivenessId() != null) {
                specification = specification.and(buildSpecification(criteria.getLevelThreeEffectivenessId(),
                    root -> root.join(FinalNiazsanjiReportPerson_.levelThreeEffectivenesses, JoinType.LEFT).get(LevelThreeEffectiveness_.id)));
            }
            if (criteria.getLevelFourEffectivenessId() != null) {
                specification = specification.and(buildSpecification(criteria.getLevelFourEffectivenessId(),
                    root -> root.join(FinalNiazsanjiReportPerson_.levelFourEffectivenesses, JoinType.LEFT).get(LevelFourEffectiveness_.id)));
            }
            if (criteria.getDocumentId() != null) {
                specification = specification.and(buildSpecification(criteria.getDocumentId(),
                    root -> root.join(FinalNiazsanjiReportPerson_.documents, JoinType.LEFT).get(Document_.id)));
            }
            if (criteria.getPersonId() != null) {
                specification = specification.and(buildSpecification(criteria.getPersonId(),
                    root -> root.join(FinalNiazsanjiReportPerson_.person, JoinType.LEFT).get(Person_.id)));
            }
            if (criteria.getFinalNiazsanjiReportId() != null) {
                specification = specification.and(buildSpecification(criteria.getFinalNiazsanjiReportId(),
                    root -> root.join(FinalNiazsanjiReportPerson_.finalNiazsanjiReport, JoinType.LEFT).get(FinalNiazsanjiReport_.id)));
            }
        }
        return specification;
    }
}
