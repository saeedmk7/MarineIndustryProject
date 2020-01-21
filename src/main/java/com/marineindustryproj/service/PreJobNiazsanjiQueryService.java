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

import com.marineindustryproj.domain.PreJobNiazsanji;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.PreJobNiazsanjiRepository;
import com.marineindustryproj.service.dto.PreJobNiazsanjiCriteria;
import com.marineindustryproj.service.dto.PreJobNiazsanjiDTO;
import com.marineindustryproj.service.mapper.PreJobNiazsanjiMapper;

/**
 * Service for executing complex queries for PreJobNiazsanji entities in the database.
 * The main input is a {@link PreJobNiazsanjiCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PreJobNiazsanjiDTO} or a {@link Page} of {@link PreJobNiazsanjiDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PreJobNiazsanjiQueryService extends QueryService<PreJobNiazsanji> {

    private final Logger log = LoggerFactory.getLogger(PreJobNiazsanjiQueryService.class);

    private final PreJobNiazsanjiRepository preJobNiazsanjiRepository;

    private final PreJobNiazsanjiMapper preJobNiazsanjiMapper;

    public PreJobNiazsanjiQueryService(PreJobNiazsanjiRepository preJobNiazsanjiRepository, PreJobNiazsanjiMapper preJobNiazsanjiMapper) {
        this.preJobNiazsanjiRepository = preJobNiazsanjiRepository;
        this.preJobNiazsanjiMapper = preJobNiazsanjiMapper;
    }

    /**
     * Return a {@link List} of {@link PreJobNiazsanjiDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<PreJobNiazsanjiDTO> findByCriteria(PreJobNiazsanjiCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<PreJobNiazsanji> specification = createSpecification(criteria);
        return preJobNiazsanjiMapper.toDto(preJobNiazsanjiRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link PreJobNiazsanjiDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<PreJobNiazsanjiDTO> findByCriteria(PreJobNiazsanjiCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<PreJobNiazsanji> specification = createSpecification(criteria);
        return preJobNiazsanjiRepository.findAll(specification, page)
            .map(preJobNiazsanjiMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(PreJobNiazsanjiCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<PreJobNiazsanji> specification = createSpecification(criteria);
        return preJobNiazsanjiRepository.count(specification);
    }

    /**
     * Function to convert PreJobNiazsanjiCriteria to a {@link Specification}
     */
    private Specification<PreJobNiazsanji> createSpecification(PreJobNiazsanjiCriteria criteria) {
        Specification<PreJobNiazsanji> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), PreJobNiazsanji_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), PreJobNiazsanji_.title));
            }
            if (criteria.getReviewDate() != null) {
                specification = specification.and(buildStringSpecification(criteria.getReviewDate(), PreJobNiazsanji_.reviewDate));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), PreJobNiazsanji_.code));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), PreJobNiazsanji_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), PreJobNiazsanji_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), PreJobNiazsanji_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), PreJobNiazsanji_.modifyDate));
            }
            if (criteria.getArchived() != null) {
                specification = specification.and(buildSpecification(criteria.getArchived(), PreJobNiazsanji_.archived));
            }
            if (criteria.getArchivedUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getArchivedUserLogin(), PreJobNiazsanji_.archivedUserLogin));
            }
            if (criteria.getArchivedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getArchivedDate(), PreJobNiazsanji_.archivedDate));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStatus(), PreJobNiazsanji_.status));
            }
            if (criteria.getRequestStatus() != null) {
                specification = specification.and(buildSpecification(criteria.getRequestStatus(), PreJobNiazsanji_.requestStatus));
            }
            if (criteria.getChangeStatusUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getChangeStatusUserLogin(), PreJobNiazsanji_.changeStatusUserLogin));
            }
            if (criteria.getGuid() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGuid(), PreJobNiazsanji_.guid));
            }
            if (criteria.getHasImportantMessage() != null) {
                specification = specification.and(buildSpecification(criteria.getHasImportantMessage(), PreJobNiazsanji_.hasImportantMessage));
            }
            if (criteria.getStep() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStep(), PreJobNiazsanji_.step));
            }
            if (criteria.getNiazsanjiFardiId() != null) {
                specification = specification.and(buildSpecification(criteria.getNiazsanjiFardiId(),
                    root -> root.join(PreJobNiazsanji_.niazsanjiFardis, JoinType.LEFT).get(NiazsanjiFardi_.id)));
            }
            if (criteria.getDesignNiazsanjiId() != null) {
                specification = specification.and(buildSpecification(criteria.getDesignNiazsanjiId(),
                    root -> root.join(PreJobNiazsanji_.designNiazsanjis, JoinType.LEFT).get(DesignNiazsanji_.id)));
            }
            if (criteria.getPreJobNiazsanjiCompetencyId() != null) {
                specification = specification.and(buildSpecification(criteria.getPreJobNiazsanjiCompetencyId(),
                    root -> root.join(PreJobNiazsanji_.preJobNiazsanjiCompetencies, JoinType.LEFT).get(PreJobNiazsanjiCompetency_.id)));
            }
            if (criteria.getJobNiazsanjiId() != null) {
                specification = specification.and(buildSpecification(criteria.getJobNiazsanjiId(),
                    root -> root.join(PreJobNiazsanji_.jobNiazsanjis, JoinType.LEFT).get(JobNiazsanji_.id)));
            }
            if (criteria.getPrioritizeRequestNiazsanjiId() != null) {
                specification = specification.and(buildSpecification(criteria.getPrioritizeRequestNiazsanjiId(),
                    root -> root.join(PreJobNiazsanji_.prioritizeRequestNiazsanjis, JoinType.LEFT).get(PrioritizeRequestNiazsanji_.id)));
            }
            if (criteria.getDocumentId() != null) {
                specification = specification.and(buildSpecification(criteria.getDocumentId(),
                    root -> root.join(PreJobNiazsanji_.documents, JoinType.LEFT).get(Document_.id)));
            }
            if (criteria.getPeopleId() != null) {
                specification = specification.and(buildSpecification(criteria.getPeopleId(),
                    root -> root.join(PreJobNiazsanji_.people, JoinType.LEFT).get(Person_.id)));
            }
            if (criteria.getOrganizationChartId() != null) {
                specification = specification.and(buildSpecification(criteria.getOrganizationChartId(),
                    root -> root.join(PreJobNiazsanji_.organizationChart, JoinType.LEFT).get(OrganizationChart_.id)));
            }
            if (criteria.getPersonId() != null) {
                specification = specification.and(buildSpecification(criteria.getPersonId(),
                    root -> root.join(PreJobNiazsanji_.person, JoinType.LEFT).get(Person_.id)));
            }
        }
        return specification;
    }
}
