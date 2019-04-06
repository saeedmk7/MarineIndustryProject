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

import com.marineindustryproj.domain.SubTask;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.SubTaskRepository;
import com.marineindustryproj.service.dto.SubTaskCriteria;
import com.marineindustryproj.service.dto.SubTaskDTO;
import com.marineindustryproj.service.mapper.SubTaskMapper;

/**
 * Service for executing complex queries for SubTask entities in the database.
 * The main input is a {@link SubTaskCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link SubTaskDTO} or a {@link Page} of {@link SubTaskDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class SubTaskQueryService extends QueryService<SubTask> {

    private final Logger log = LoggerFactory.getLogger(SubTaskQueryService.class);

    private final SubTaskRepository subTaskRepository;

    private final SubTaskMapper subTaskMapper;

    public SubTaskQueryService(SubTaskRepository subTaskRepository, SubTaskMapper subTaskMapper) {
        this.subTaskRepository = subTaskRepository;
        this.subTaskMapper = subTaskMapper;
    }

    /**
     * Return a {@link List} of {@link SubTaskDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<SubTaskDTO> findByCriteria(SubTaskCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<SubTask> specification = createSpecification(criteria);
        return subTaskMapper.toDto(subTaskRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link SubTaskDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<SubTaskDTO> findByCriteria(SubTaskCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<SubTask> specification = createSpecification(criteria);
        return subTaskRepository.findAll(specification, page)
            .map(subTaskMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(SubTaskCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<SubTask> specification = createSpecification(criteria);
        return subTaskRepository.count(specification);
    }

    /**
     * Function to convert SubTaskCriteria to a {@link Specification}
     */
    private Specification<SubTask> createSpecification(SubTaskCriteria criteria) {
        Specification<SubTask> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), SubTask_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), SubTask_.title));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), SubTask_.description));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), SubTask_.code));
            }
            if (criteria.getSolution() != null) {
                specification = specification.and(buildStringSpecification(criteria.getSolution(), SubTask_.solution));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), SubTask_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), SubTask_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), SubTask_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), SubTask_.modifyDate));
            }
            if (criteria.getArchived() != null) {
                specification = specification.and(buildSpecification(criteria.getArchived(), SubTask_.archived));
            }
            if (criteria.getArchivedUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getArchivedUserLogin(), SubTask_.archivedUserLogin));
            }
            if (criteria.getArchivedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getArchivedDate(), SubTask_.archivedDate));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStatus(), SubTask_.status));
            }
            if (criteria.getMainTaskId() != null) {
                specification = specification.and(buildSpecification(criteria.getMainTaskId(),
                    root -> root.join(SubTask_.mainTasks, JoinType.LEFT).get(MainTask_.id)));
            }
        }
        return specification;
    }
}
