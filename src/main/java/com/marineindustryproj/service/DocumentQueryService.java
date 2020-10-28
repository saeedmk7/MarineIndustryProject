package com.marineindustryproj.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import io.github.jhipster.service.filter.LongFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.marineindustryproj.domain.Document;
import com.marineindustryproj.domain.*; // for static metamodels
import com.marineindustryproj.repository.DocumentRepository;
import com.marineindustryproj.service.dto.DocumentCriteria;
import com.marineindustryproj.service.dto.DocumentDTO;
import com.marineindustryproj.service.mapper.DocumentMapper;

/**
 * Service for executing complex queries for Document entities in the database.
 * The main input is a {@link DocumentCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DocumentDTO} or a {@link Page} of {@link DocumentDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DocumentQueryService extends QueryService<Document> {

    private final Logger log = LoggerFactory.getLogger(DocumentQueryService.class);

    private final DocumentRepository documentRepository;

    private final DocumentMapper documentMapper;

    public DocumentQueryService(DocumentRepository documentRepository, DocumentMapper documentMapper) {
        this.documentRepository = documentRepository;
        this.documentMapper = documentMapper;
    }

    /**
     * Return a {@link List} of {@link DocumentDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DocumentDTO> findByCriteria(DocumentCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Document> specification = createSpecification(criteria);
        return documentMapper.toDto(documentRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DocumentDTO} which matches the criteria from the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DocumentDTO> findByCriteria(DocumentCriteria criteria, Pageable page, String entityName, Long entityId) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        LongFilter longFilter = new LongFilter();
        longFilter.setEquals(entityId);
        getLongFilter(criteria,
                      entityName,
                      longFilter);
//         final Specifications<Document> specification = createSpecification(criteria);
//        Page<Document> result = null;
//        try {
//
//            result = documentRepository.findAll(specification, page);
//        } catch (Exception ex) {
//            log.debug(ex.getMessage());
//        }
//        return result.map(documentMapper::toDto);
        final Specification<Document> specification = createSpecification(criteria);
        return documentRepository.findAll(specification, page)
            .map(documentMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DocumentCriteria criteria, String entityName, Long entityId) {
        log.debug("count by criteria : {}", criteria);
        LongFilter longFilter = new LongFilter();
        longFilter.setEquals(entityId);
        getLongFilter(criteria,
                      entityName,
                      longFilter);

        final Specification<Document> specification = createSpecification(criteria);
        return documentRepository.count(specification);
    }

    private void getLongFilter(DocumentCriteria criteria,
                               String entityName,
                               LongFilter longFilter) {
        if(entityName.toLowerCase().equals("person")) {
            criteria.setPersonId(longFilter);
        }
        else if(entityName.toLowerCase().equals("educationalcenter")) {
            criteria.setEducationalCenterId(longFilter);
        }
        else if(entityName.toLowerCase().equals("teacher")) {
            criteria.setTeacherId(longFilter);
        }
        else if(entityName.toLowerCase().equals("job")) {
            criteria.setJobId(longFilter);
        }
        else if(entityName.toLowerCase().equals("educationalmodule")) {
            criteria.setEducationalModuleId(longFilter);
        }
        else if(entityName.toLowerCase().equals("resource")) {
            criteria.setResourceId(longFilter);
        }
        else if(entityName.toLowerCase().equals("instruction")) {
            criteria.setInstructionId(longFilter);
        }
        else if(entityName.toLowerCase().equals("runphase")) {
            criteria.setRunPhaseId(longFilter);
        }
        else if(entityName.toLowerCase().equals("finalniazsanjireport")) {
            criteria.setFinalNiazsanjiReportId(longFilter);
        }
        else if(entityName.toLowerCase().equals("designandplanning")) {
            criteria.setDesignAndPlanningId(longFilter);
        }
        else if(entityName.toLowerCase().equals("requestorganizationniazsanji")) {
            criteria.setRequestOrganizationNiazsanjiId(longFilter);
        }
        else if(entityName.toLowerCase().equals("finalorganizationniazsanji")) {
            criteria.setFinalOrganizationNiazsanjiId(longFilter);
        }
        else if(entityName.toLowerCase().equals("announcement")) {
            criteria.setAnnouncementId(longFilter);
        }
        else if(entityName.toLowerCase().equals("requesteducationalmodule")) {
            criteria.setRequestEducationalModuleId(longFilter);
        }
        else if(entityName.toLowerCase().equals("usersrequest")) {
            criteria.setUsersRequestId(longFilter);
        }
        else if(entityName.toLowerCase().equals("requestniazsanjifardi")) {
            criteria.setRequestNiazsanjiFardiId(longFilter);
        }
        else if(entityName.toLowerCase().equals("niazsanjifardi")) {
            criteria.setNiazsanjiFardiId(longFilter);
        }
        else if(entityName.toLowerCase().equals("investtogrouptransaction")) {
            criteria.setInvestToGroupTransactionId(longFilter);
        }
        else if(entityName.toLowerCase().equals("mediaawarenessreport")) {
            criteria.setMediaAwarenessReportId(longFilter);
        }
        else if(entityName.toLowerCase().equals("prejobniazsanji")) {
            criteria.setPreJobNiazsanjiId(longFilter);
        }
        else if(entityName.toLowerCase().equals("evaluatecriteriadata")) {
            criteria.setEvaluateCriteriaDataId(longFilter);
        }
        else if(entityName.toLowerCase().equals("requestotherniazsanji")) {
            criteria.setRequestOtherNiazsanjiId(longFilter);
        }
        else if(entityName.toLowerCase().equals("levelthreeeffectiveness")) {
            criteria.setLevelThreeEffectivenessId(longFilter);
        }
        else if(entityName.toLowerCase().equals("effectivenessphase")) {
            criteria.setEffectivenessPhaseId(longFilter);
        }
        else if(entityName.toLowerCase().equals("finalniazsanjireportperson")) {
            criteria.setFinalNiazsanjiReportPersonId(longFilter);
        }
        else if(entityName.toLowerCase().equals("levelfoureffectiveness")) {
            criteria.setLevelFourEffectivenessId(longFilter);
        }
        else if(entityName.toLowerCase().equals("teachergrade")) {
            criteria.setTeacherGradeId(longFilter);
        }
        else if(entityName.toLowerCase().equals("educationalcentergrade")) {
            criteria.setEducationalCenterGradeId(longFilter);
        }
        else if(entityName.toLowerCase().equals("prioritizerequestniazsanji")) {
            criteria.setPrioritizeRequestNiazsanjiId(longFilter);
        }
        else if(entityName.toLowerCase().equals("soldiertrainingreport")) {
            criteria.setSoldierTrainingReportId(longFilter);
        }
        else if(entityName.toLowerCase().equals("evaluatecriteriatraining")) {
            criteria.setEvaluateCriteriaTrainingId(longFilter);
        }
        else if(entityName.toLowerCase().equals("soldier")) {
            criteria.setSoldierId(longFilter);
        }
        else if(entityName.toLowerCase().equals("niazsanjipersongrade")) {
            criteria.setNiazsanjiPersonGradeId(longFilter);
        }
        else
        {
            LongFilter emptyLongFilter = new LongFilter();
            emptyLongFilter.setEquals(Long.MAX_VALUE);
            criteria.setId(emptyLongFilter);
        }
    }

    /**
     * Function to convert DocumentCriteria to a {@link Specification}
     */
    private Specification<Document> createSpecification(DocumentCriteria criteria) {
        Specification<Document> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), Document_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), Document_.title));
            }
            if (criteria.getCreateUserLogin() != null) {
                specification = specification.and(buildStringSpecification(criteria.getCreateUserLogin(), Document_.createUserLogin));
            }
            if (criteria.getCreateDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCreateDate(), Document_.createDate));
            }
            if (criteria.getPersonId() != null) {
                specification = specification.and(buildSpecification(criteria.getPersonId(),
                    root -> root.join(Document_.people, JoinType.LEFT).get(Person_.id)));
            }
            if (criteria.getTeacherId() != null) {
                specification = specification.and(buildSpecification(criteria.getTeacherId(),
                    root -> root.join(Document_.teachers, JoinType.LEFT).get(Teacher_.id)));
            }
            if (criteria.getTeacherGradeId() != null) {
                specification = specification.and(buildSpecification(criteria.getTeacherGradeId(),
                    root -> root.join(Document_.teacherGrades, JoinType.LEFT).get(TeacherGrade_.id)));
            }
            if (criteria.getJobId() != null) {
                specification = specification.and(buildSpecification(criteria.getJobId(),
                    root -> root.join(Document_.jobs, JoinType.LEFT).get(Job_.id)));
            }
            if (criteria.getEducationalModuleId() != null) {
                specification = specification.and(buildSpecification(criteria.getEducationalModuleId(),
                    root -> root.join(Document_.educationalModules, JoinType.LEFT).get(EducationalModule_.id)));
            }
            if (criteria.getRequestEducationalModuleId() != null) {
                specification = specification.and(buildSpecification(criteria.getRequestEducationalModuleId(),
                    root -> root.join(Document_.requestEducationalModules, JoinType.LEFT).get(RequestEducationalModule_.id)));
            }
            if (criteria.getEducationalCenterId() != null) {
                specification = specification.and(buildSpecification(criteria.getEducationalCenterId(),
                    root -> root.join(Document_.educationalCenters, JoinType.LEFT).get(EducationalCenter_.id)));
            }
            if (criteria.getEducationalCenterGradeId() != null) {
                specification = specification.and(buildSpecification(criteria.getEducationalCenterGradeId(),
                    root -> root.join(Document_.educationalCenterGrades, JoinType.LEFT).get(EducationalCenterGrade_.id)));
            }
            if (criteria.getResourceId() != null) {
                specification = specification.and(buildSpecification(criteria.getResourceId(),
                    root -> root.join(Document_.resources, JoinType.LEFT).get(Resource_.id)));
            }
            if (criteria.getRequestOrganizationNiazsanjiId() != null) {
                specification = specification.and(buildSpecification(criteria.getRequestOrganizationNiazsanjiId(),
                    root -> root.join(Document_.requestOrganizationNiazsanjis, JoinType.LEFT).get(RequestOrganizationNiazsanji_.id)));
            }
            if (criteria.getFinalOrganizationNiazsanjiId() != null) {
                specification = specification.and(buildSpecification(criteria.getFinalOrganizationNiazsanjiId(),
                    root -> root.join(Document_.finalOrganizationNiazsanjis, JoinType.LEFT).get(FinalOrganizationNiazsanji_.id)));
            }
            if (criteria.getFinalNiazsanjiReportId() != null) {
                specification = specification.and(buildSpecification(criteria.getFinalNiazsanjiReportId(),
                    root -> root.join(Document_.finalNiazsanjiReports, JoinType.LEFT).get(FinalNiazsanjiReport_.id)));
            }
            if (criteria.getFinalNiazsanjiReportPersonId() != null) {
                specification = specification.and(buildSpecification(criteria.getFinalNiazsanjiReportPersonId(),
                    root -> root.join(Document_.finalNiazsanjiReportPeople, JoinType.LEFT).get(FinalNiazsanjiReportPerson_.id)));
            }
            if (criteria.getNiazsanjiPersonGradeId() != null) {
                specification = specification.and(buildSpecification(criteria.getNiazsanjiPersonGradeId(),
                    root -> root.join(Document_.niazsanjiPersonGrades, JoinType.LEFT).get(NiazsanjiPersonGrade_.id)));
            }
            if (criteria.getLevelThreeEffectivenessId() != null) {
                specification = specification.and(buildSpecification(criteria.getLevelThreeEffectivenessId(),
                    root -> root.join(Document_.levelThreeEffectivenesses, JoinType.LEFT).get(LevelThreeEffectiveness_.id)));
            }
            if (criteria.getLevelFourEffectivenessId() != null) {
                specification = specification.and(buildSpecification(criteria.getLevelFourEffectivenessId(),
                    root -> root.join(Document_.levelFourEffectivenesses, JoinType.LEFT).get(LevelFourEffectiveness_.id)));
            }
            if (criteria.getDesignAndPlanningId() != null) {
                specification = specification.and(buildSpecification(criteria.getDesignAndPlanningId(),
                    root -> root.join(Document_.designAndPlannings, JoinType.LEFT).get(DesignAndPlanning_.id)));
            }
            if (criteria.getRunPhaseId() != null) {
                specification = specification.and(buildSpecification(criteria.getRunPhaseId(),
                    root -> root.join(Document_.runPhases, JoinType.LEFT).get(RunPhase_.id)));
            }
            if (criteria.getAnnouncementId() != null) {
                specification = specification.and(buildSpecification(criteria.getAnnouncementId(),
                    root -> root.join(Document_.announcements, JoinType.LEFT).get(Announcement_.id)));
            }
            if (criteria.getUsersRequestId() != null) {
                specification = specification.and(buildSpecification(criteria.getUsersRequestId(),
                    root -> root.join(Document_.usersRequests, JoinType.LEFT).get(UsersRequest_.id)));
            }
            if (criteria.getNiazsanjiFardiId() != null) {
                specification = specification.and(buildSpecification(criteria.getNiazsanjiFardiId(),
                    root -> root.join(Document_.niazsanjiFardis, JoinType.LEFT).get(NiazsanjiFardi_.id)));
            }
            if (criteria.getRequestNiazsanjiFardiId() != null) {
                specification = specification.and(buildSpecification(criteria.getRequestNiazsanjiFardiId(),
                    root -> root.join(Document_.requestNiazsanjiFardis, JoinType.LEFT).get(RequestNiazsanjiFardi_.id)));
            }
            if (criteria.getInstructionId() != null) {
                specification = specification.and(buildSpecification(criteria.getInstructionId(),
                    root -> root.join(Document_.instructions, JoinType.LEFT).get(Instruction_.id)));
            }
            if (criteria.getInvestToGroupTransactionId() != null) {
                specification = specification.and(buildSpecification(criteria.getInvestToGroupTransactionId(),
                    root -> root.join(Document_.investToGroupTransactions, JoinType.LEFT).get(InvestToGroupTransaction_.id)));
            }
            if (criteria.getMediaAwarenessReportId() != null) {
                specification = specification.and(buildSpecification(criteria.getMediaAwarenessReportId(),
                    root -> root.join(Document_.mediaAwarenessReports, JoinType.LEFT).get(MediaAwarenessReport_.id)));
            }
            if (criteria.getPreJobNiazsanjiId() != null) {
                specification = specification.and(buildSpecification(criteria.getPreJobNiazsanjiId(),
                    root -> root.join(Document_.preJobNiazsanjis, JoinType.LEFT).get(PreJobNiazsanji_.id)));
            }
            if (criteria.getJobNiazsanjiId() != null) {
                specification = specification.and(buildSpecification(criteria.getJobNiazsanjiId(),
                    root -> root.join(Document_.jobNiazsanjis, JoinType.LEFT).get(JobNiazsanji_.id)));
            }
            if (criteria.getNiazsanjiOtherId() != null) {
                specification = specification.and(buildSpecification(criteria.getNiazsanjiOtherId(),
                    root -> root.join(Document_.niazsanjiOthers, JoinType.LEFT).get(NiazsanjiOther_.id)));
            }
            if (criteria.getRequestOtherNiazsanjiId() != null) {
                specification = specification.and(buildSpecification(criteria.getRequestOtherNiazsanjiId(),
                    root -> root.join(Document_.requestOtherNiazsanjis, JoinType.LEFT).get(RequestOtherNiazsanji_.id)));
            }
            if (criteria.getPrioritizeRequestNiazsanjiId() != null) {
                specification = specification.and(buildSpecification(criteria.getPrioritizeRequestNiazsanjiId(),
                    root -> root.join(Document_.prioritizeRequestNiazsanjis, JoinType.LEFT).get(PrioritizeRequestNiazsanji_.id)));
            }
            if (criteria.getSoldierId() != null) {
                specification = specification.and(buildSpecification(criteria.getSoldierId(),
                    root -> root.join(Document_.soldiers, JoinType.LEFT).get(Soldier_.id)));
            }
            if (criteria.getSoldierTrainingReportId() != null) {
                specification = specification.and(buildSpecification(criteria.getSoldierTrainingReportId(),
                    root -> root.join(Document_.soldierTrainingReports, JoinType.LEFT).get(SoldierTrainingReport_.id)));
            }
            if (criteria.getSoldierMediaAwarenessReportId() != null) {
                specification = specification.and(buildSpecification(criteria.getSoldierMediaAwarenessReportId(),
                    root -> root.join(Document_.soldierMediaAwarenessReports, JoinType.LEFT).get(SoldierMediaAwarenessReport_.id)));
            }
            if (criteria.getEvaluateCriteriaTrainingId() != null) {
                specification = specification.and(buildSpecification(criteria.getEvaluateCriteriaTrainingId(),
                    root -> root.join(Document_.evaluateCriteriaTrainings, JoinType.LEFT).get(EvaluateCriteriaTraining_.id)));
            }
            if (criteria.getEvaluateCriteriaDataId() != null) {
                specification = specification.and(buildSpecification(criteria.getEvaluateCriteriaDataId(),
                    root -> root.join(Document_.evaluateCriteriaData, JoinType.LEFT).get(EvaluateCriteriaData_.id)));
            }
            if (criteria.getEffectivenessPhaseId() != null) {
                specification = specification.and(buildSpecification(criteria.getEffectivenessPhaseId(),
                    root -> root.join(Document_.effectivenessPhases, JoinType.LEFT).get(EffectivenessPhase_.id)));
            }
            if (criteria.getMonitorLearningProcessId() != null) {
                specification = specification.and(buildSpecification(criteria.getMonitorLearningProcessId(),
                    root -> root.join(Document_.monitorLearningProcesses, JoinType.LEFT).get(MonitorLearningProcess_.id)));
            }
        }
        return specification;
    }
}
