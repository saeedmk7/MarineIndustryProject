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

import com.marineindustryproj.domain.Soldier;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.SoldierRepository;
import com.marineindustryproj.service.dto.SoldierCriteria;
import com.marineindustryproj.service.dto.SoldierDTO;
import com.marineindustryproj.service.mapper.SoldierMapper;

/**
 * Service for executing complex queries for Soldier entities in the database.
 * The main input is a {@link SoldierCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link SoldierDTO} or a {@link Page} of {@link SoldierDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class SoldierQueryService extends QueryService<Soldier> {

    private final Logger log = LoggerFactory.getLogger(SoldierQueryService.class);

    private final SoldierRepository soldierRepository;

    private final SoldierMapper soldierMapper;

    public SoldierQueryService(SoldierRepository soldierRepository, SoldierMapper soldierMapper) {
        this.soldierRepository = soldierRepository;
        this.soldierMapper = soldierMapper;
    }

    /**
     * Return a {@link List} of {@link SoldierDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<SoldierDTO> findByCriteria(SoldierCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Soldier> specification = createSpecification(criteria);
        return soldierMapper.toDto(soldierRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link SoldierDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<SoldierDTO> findByCriteria(SoldierCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Soldier> specification = createSpecification(criteria);
        return soldierRepository.findAll(specification, page)
            .map(soldierMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(SoldierCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Soldier> specification = createSpecification(criteria);
        return soldierRepository.count(specification);
    }

    /**
     * Function to convert SoldierCriteria to a {@link Specification}
     */
    private Specification<Soldier> createSpecification(SoldierCriteria criteria) {
        Specification<Soldier> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Soldier_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), Soldier_.name));
            }
            if (criteria.getFamily() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFamily(), Soldier_.family));
            }
            if (criteria.getFatherName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFatherName(), Soldier_.fatherName));
            }
            if (criteria.getCertificateNumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCertificateNumber(), Soldier_.certificateNumber));
            }
            if (criteria.getNationalId() != null) {
                specification = specification.and(buildStringSpecification(criteria.getNationalId(), Soldier_.nationalId));
            }
            if (criteria.getBirthDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getBirthDate(), Soldier_.birthDate));
            }
            if (criteria.getReleaseDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getReleaseDate(), Soldier_.releaseDate));
            }
            if (criteria.getPersonelCode() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPersonelCode(), Soldier_.personelCode));
            }
            if (criteria.getEmploymentDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEmploymentDate(), Soldier_.employmentDate));
            }
            if (criteria.getPhoneNumber() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPhoneNumber(), Soldier_.phoneNumber));
            }
            if (criteria.getMobile() != null) {
                specification = specification.and(buildStringSpecification(criteria.getMobile(), Soldier_.mobile));
            }
            if (criteria.getAddress() != null) {
                specification = specification.and(buildStringSpecification(criteria.getAddress(), Soldier_.address));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), Soldier_.description));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), Soldier_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), Soldier_.createDate));
            }
            if (criteria.getModifyUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getModifyUserLogin(), Soldier_.modifyUserLogin));
            }
            if (criteria.getModifyDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getModifyDate(), Soldier_.modifyDate));
            }
            if (criteria.getArchived() != null) {
                specification = specification.and(buildSpecification(criteria.getArchived(), Soldier_.archived));
            }
            if (criteria.getArchivedUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getArchivedUserLogin(), Soldier_.archivedUserLogin));
            }
            if (criteria.getArchivedDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getArchivedDate(), Soldier_.archivedDate));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStatus(), Soldier_.status));
            }
            if (criteria.getGuid() != null) {
                specification = specification.and(buildStringSpecification(criteria.getGuid(), Soldier_.guid));
            }
            if (criteria.getSoldierTrainingReportId() != null) {
                specification = specification.and(buildSpecification(criteria.getSoldierTrainingReportId(),
                    root -> root.join(Soldier_.soldierTrainingReports, JoinType.LEFT).get(SoldierTrainingReport_.id)));
            }
            if (criteria.getSoldierMediaAwarenessReportId() != null) {
                specification = specification.and(buildSpecification(criteria.getSoldierMediaAwarenessReportId(),
                    root -> root.join(Soldier_.soldierMediaAwarenessReports, JoinType.LEFT).get(SoldierMediaAwarenessReport_.id)));
            }
            if (criteria.getDocumentId() != null) {
                specification = specification.and(buildSpecification(criteria.getDocumentId(),
                    root -> root.join(Soldier_.documents, JoinType.LEFT).get(Document_.id)));
            }
            if (criteria.getLastQualificationId() != null) {
                specification = specification.and(buildSpecification(criteria.getLastQualificationId(),
                    root -> root.join(Soldier_.lastQualification, JoinType.LEFT).get(Qualification_.id)));
            }
            if (criteria.getLastFieldOfStudyId() != null) {
                specification = specification.and(buildSpecification(criteria.getLastFieldOfStudyId(),
                    root -> root.join(Soldier_.lastFieldOfStudy, JoinType.LEFT).get(FieldOfStudy_.id)));
            }
            if (criteria.getJobId() != null) {
                specification = specification.and(buildSpecification(criteria.getJobId(),
                    root -> root.join(Soldier_.job, JoinType.LEFT).get(Job_.id)));
            }
            if (criteria.getOrganizationChartId() != null) {
                specification = specification.and(buildSpecification(criteria.getOrganizationChartId(),
                    root -> root.join(Soldier_.organizationChart, JoinType.LEFT).get(OrganizationChart_.id)));
            }
        }
        return specification;
    }
}
