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

import com.marineindustryproj.domain.MainTask;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.MainTaskRepository;
import com.marineindustryproj.service.dto.MainTaskCriteria;
import com.marineindustryproj.service.dto.MainTaskDTO;
import com.marineindustryproj.service.mapper.MainTaskMapper;

/**
 * Service for executing complex queries for MainTask entities in the database.
 * The main input is a {@link MainTaskCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MainTaskDTO} or a {@link Page} of {@link MainTaskDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MainTaskQueryService extends QueryService<MainTask> {

    private final Logger log = LoggerFactory.getLogger(MainTaskQueryService.class);

    private final MainTaskRepository mainTaskRepository;

    private final MainTaskMapper mainTaskMapper;

    public MainTaskQueryService(MainTaskRepository mainTaskRepository, MainTaskMapper mainTaskMapper) {
        this.mainTaskRepository = mainTaskRepository;
        this.mainTaskMapper = mainTaskMapper;
    }

    /**
     * Return a {@link List} of {@link MainTaskDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MainTaskDTO> findByCriteria(MainTaskCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MainTask> specification = createSpecification(criteria);
        return mainTaskMapper.toDto(mainTaskRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MainTaskDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MainTaskDTO> findByCriteria(MainTaskCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MainTask> specification = createSpecification(criteria);
        return mainTaskRepository.findAll(specification, page)
            .map(mainTaskMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MainTaskCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MainTask> specification = createSpecification(criteria);
        return mainTaskRepository.count(specification);
    }

    /**
     * Function to convert MainTaskCriteria to a {@link Specification}
     */
    private Specification<MainTask> createSpecification(MainTaskCriteria criteria) {
        Specification<MainTask> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), MainTask_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), MainTask_.title));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), MainTask_.description));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), MainTask_.code));
            }
            if (criteria.getSolution() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSolution(), MainTask_.solution));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), MainTask_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), MainTask_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), MainTask_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), MainTask_.modifyDate));
            }
            if (criteria.getArchived() != null) {
                specification = specification.and(buildSpecification(criteria.getArchived(), MainTask_.archived));
            }
            if (criteria.getArchivedUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getArchivedUserLogin(), MainTask_.archivedUserLogin));
            }
            if (criteria.getArchivedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getArchivedDate(), MainTask_.archivedDate));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStatus(), MainTask_.status));
            }
            if (criteria.getSubTaskId() != null) {
                specification = specification.and(buildSpecification(criteria.getSubTaskId(),
                    root -> root.join(MainTask_.subTasks, JoinType.LEFT).get(SubTask_.id)));
            }
            if (criteria.getJobId() != null) {
                specification = specification.and(buildSpecification(criteria.getJobId(),
                    root -> root.join(MainTask_.jobs, JoinType.LEFT).get(Job_.id)));
            }
            if (criteria.getPersonId() != null) {
                specification = specification.and(buildSpecification(criteria.getPersonId(),
                    root -> root.join(MainTask_.people, JoinType.LEFT).get(Person_.id)));
            }
        }
        return specification;
    }
}
