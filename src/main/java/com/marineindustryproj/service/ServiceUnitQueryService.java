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

import com.marineindustryproj.domain.ServiceUnit;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.ServiceUnitRepository;
import com.marineindustryproj.service.dto.ServiceUnitCriteria;
import com.marineindustryproj.service.dto.ServiceUnitDTO;
import com.marineindustryproj.service.mapper.ServiceUnitMapper;

/**
 * Service for executing complex queries for ServiceUnit entities in the database.
 * The main input is a {@link ServiceUnitCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ServiceUnitDTO} or a {@link Page} of {@link ServiceUnitDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ServiceUnitQueryService extends QueryService<ServiceUnit> {

    private final Logger log = LoggerFactory.getLogger(ServiceUnitQueryService.class);

    private final ServiceUnitRepository serviceUnitRepository;

    private final ServiceUnitMapper serviceUnitMapper;

    public ServiceUnitQueryService(ServiceUnitRepository serviceUnitRepository, ServiceUnitMapper serviceUnitMapper) {
        this.serviceUnitRepository = serviceUnitRepository;
        this.serviceUnitMapper = serviceUnitMapper;
    }

    /**
     * Return a {@link List} of {@link ServiceUnitDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ServiceUnitDTO> findByCriteria(ServiceUnitCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ServiceUnit> specification = createSpecification(criteria);
        return serviceUnitMapper.toDto(serviceUnitRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link ServiceUnitDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ServiceUnitDTO> findByCriteria(ServiceUnitCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ServiceUnit> specification = createSpecification(criteria);
        return serviceUnitRepository.findAll(specification, page)
            .map(serviceUnitMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ServiceUnitCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ServiceUnit> specification = createSpecification(criteria);
        return serviceUnitRepository.count(specification);
    }

    /**
     * Function to convert ServiceUnitCriteria to a {@link Specification}
     */
    private Specification<ServiceUnit> createSpecification(ServiceUnitCriteria criteria) {
        Specification<ServiceUnit> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ServiceUnit_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), ServiceUnit_.title));
            }
            if (criteria.getCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCode(), ServiceUnit_.code));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), ServiceUnit_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), ServiceUnit_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), ServiceUnit_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), ServiceUnit_.modifyDate));
            }
            if (criteria.getTeacherId() != null) {
                specification = specification.and(buildSpecification(criteria.getTeacherId(),
                    root -> root.join(ServiceUnit_.teachers, JoinType.LEFT).get(Teacher_.id)));
            }
        }
        return specification;
    }
}
