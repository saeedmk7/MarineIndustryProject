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

import com.marineindustryproj.domain.UsersRequest;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.UsersRequestRepository;
import com.marineindustryproj.service.dto.UsersRequestCriteria;
import com.marineindustryproj.service.dto.UsersRequestDTO;
import com.marineindustryproj.service.mapper.UsersRequestMapper;

/**
 * Service for executing complex queries for UsersRequest entities in the database.
 * The main input is a {@link UsersRequestCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link UsersRequestDTO} or a {@link Page} of {@link UsersRequestDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class UsersRequestQueryService extends QueryService<UsersRequest> {

    private final Logger log = LoggerFactory.getLogger(UsersRequestQueryService.class);

    private final UsersRequestRepository usersRequestRepository;

    private final UsersRequestMapper usersRequestMapper;

    public UsersRequestQueryService(UsersRequestRepository usersRequestRepository, UsersRequestMapper usersRequestMapper) {
        this.usersRequestRepository = usersRequestRepository;
        this.usersRequestMapper = usersRequestMapper;
    }

    /**
     * Return a {@link List} of {@link UsersRequestDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<UsersRequestDTO> findByCriteria(UsersRequestCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<UsersRequest> specification = createSpecification(criteria);
        return usersRequestMapper.toDto(usersRequestRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link UsersRequestDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<UsersRequestDTO> findByCriteria(UsersRequestCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<UsersRequest> specification = createSpecification(criteria);
        return usersRequestRepository.findAll(specification, page)
            .map(usersRequestMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(UsersRequestCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<UsersRequest> specification = createSpecification(criteria);
        return usersRequestRepository.count(specification);
    }

    /**
     * Function to convert UsersRequestCriteria to a {@link Specification}
     */
    private Specification<UsersRequest> createSpecification(UsersRequestCriteria criteria) {
        Specification<UsersRequest> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), UsersRequest_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), UsersRequest_.title));
            }
            if (criteria.getTelephone() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTelephone(), UsersRequest_.telephone));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), UsersRequest_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), UsersRequest_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), UsersRequest_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), UsersRequest_.modifyDate));
            }
            if (criteria.getRequestStatus() != null) {
                specification = specification.and(buildSpecification(criteria.getRequestStatus(), UsersRequest_.requestStatus));
            }
            if (criteria.getChangeStatusUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getChangeStatusUserLogin(), UsersRequest_.changeStatusUserLogin));
            }
            if (criteria.getDocumentId() != null) {
                specification = specification.and(buildSpecification(criteria.getDocumentId(),
                    root -> root.join(UsersRequest_.documents, JoinType.LEFT).get(Document_.id)));
            }
        }
        return specification;
    }
}
