package com.marineindustryproj.service.impl;

import com.marineindustryproj.domain.EducationalModule;
import com.marineindustryproj.domain.Headline;
import com.marineindustryproj.repository.EducationalModuleRepository;
import com.marineindustryproj.repository.HeadlineRepository;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.EducationalModuleQueryService;
import com.marineindustryproj.service.EducationalModuleService;
import com.marineindustryproj.service.RequestEducationalModuleService;
import com.marineindustryproj.domain.RequestEducationalModule;
import com.marineindustryproj.repository.RequestEducationalModuleRepository;
import com.marineindustryproj.service.dto.EducationalModuleCriteria;
import com.marineindustryproj.service.dto.EducationalModuleDTO;
import com.marineindustryproj.service.dto.HeadlineDTO;
import com.marineindustryproj.service.dto.RequestEducationalModuleDTO;
import com.marineindustryproj.service.mapper.HeadlineMapper;
import com.marineindustryproj.service.mapper.RequestEducationalModuleMapper;
import io.github.jhipster.service.filter.LongFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing RequestEducationalModule.
 */
@Service
@Transactional
public class RequestEducationalModuleServiceImpl implements RequestEducationalModuleService {

    private final Logger log = LoggerFactory.getLogger(RequestEducationalModuleServiceImpl.class);

    private final RequestEducationalModuleRepository requestEducationalModuleRepository;

    private final RequestEducationalModuleMapper requestEducationalModuleMapper;

    private final EducationalModuleRepository educationalModuleRepository;

    private final EducationalModuleService educationalModuleService;

    private final HeadlineRepository headlineRepository;

    private final HeadlineMapper headlineMapper;

    private final EducationalModuleQueryService educationalModuleQueryService;

    public RequestEducationalModuleServiceImpl(RequestEducationalModuleRepository requestEducationalModuleRepository,
                                               RequestEducationalModuleMapper requestEducationalModuleMapper,
                                               EducationalModuleRepository educationalModuleRepository, EducationalModuleService educationalModuleService, HeadlineRepository headlineRepository, HeadlineMapper headlineMapper, EducationalModuleQueryService educationalModuleQueryService) {
        this.requestEducationalModuleRepository = requestEducationalModuleRepository;
        this.requestEducationalModuleMapper = requestEducationalModuleMapper;
        this.educationalModuleRepository = educationalModuleRepository;
        this.educationalModuleService = educationalModuleService;
        this.headlineRepository = headlineRepository;
        this.headlineMapper = headlineMapper;
        this.educationalModuleQueryService = educationalModuleQueryService;
    }

    /**
     * Save a requestEducationalModule.
     *
     * @param requestEducationalModuleDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public RequestEducationalModuleDTO save(RequestEducationalModuleDTO requestEducationalModuleDTO) {
        log.debug("Request to save RequestEducationalModule : {}", requestEducationalModuleDTO);

        RequestEducationalModule requestEducationalModule = requestEducationalModuleMapper.toEntity(requestEducationalModuleDTO);
        requestEducationalModule = requestEducationalModuleRepository.save(requestEducationalModule);

        if (!requestEducationalModuleDTO.getHeadlines().isEmpty()) {
            for (HeadlineDTO headlineDTO : requestEducationalModuleDTO.getHeadlines()) {
                headlineDTO.setRequestEducationalModuleId(requestEducationalModule.getId());
                headlineDTO.setCreateDate(requestEducationalModule.getCreateDate());
                headlineDTO.setCreateUserLogin(requestEducationalModule.getCreateUserLogin());
                headlineDTO.setModifyDate(ZonedDateTime.now());
                headlineDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
                Headline headline = headlineMapper.toEntity(headlineDTO);
                headlineRepository.save(headline);
            }
        }

        return requestEducationalModuleMapper.toDto(requestEducationalModule);
    }

    /**
     * Finalize a requestEducationalModule.
     *
     * @param requestEducationalModuleDTO the entity to finalize
     * @return the persisted entity
     */
    @Override
    public RequestEducationalModuleDTO finalize(RequestEducationalModuleDTO requestEducationalModuleDTO) throws Exception {
        log.debug("Request to save RequestEducationalModule : {}", requestEducationalModuleDTO);

        RequestEducationalModule requestEducationalModule = requestEducationalModuleMapper.toEntity(requestEducationalModuleDTO);

        EducationalModuleCriteria criteria = new EducationalModuleCriteria();
        LongFilter filter = new LongFilter();
        filter.setEquals(requestEducationalModuleDTO.getId());
        criteria.setRequestEducationalModuleId(filter);

        List<EducationalModuleDTO> educationalModules = educationalModuleQueryService.findByCriteria(criteria);

        if (educationalModules.isEmpty()) {
            EducationalModule educationalModule = new EducationalModule();
            educationalModule.setLearningTimePractical(requestEducationalModule.getLearningTimePractical());
            educationalModule.setLearningTimeTheorical(requestEducationalModule.getLearningTimeTheorical());
            educationalModule.setSkillableLevelOfSkill(requestEducationalModule.getSkillableLevelOfSkill());
            educationalModule.setCode(requestEducationalModule.getCode());
            educationalModule.setRecommendedBy(requestEducationalModule.getRecommendedBy());
            educationalModule.setId(Long.parseLong(requestEducationalModule.getCode()));
            educationalModule.setDocuments(requestEducationalModule.getDocuments());
            educationalModule.setArchived(false);
            educationalModule.setOrganization(requestEducationalModule.getOrganization());
            educationalModule.setScientificWorkGroups(requestEducationalModule.getScientificWorkGroups());
            educationalModule.setStatus(0);
            educationalModule.setTitle(requestEducationalModule.getTitle());
            educationalModule.setCredit(requestEducationalModule.getCredit());
            educationalModule.setCentralizedCode(requestEducationalModule.getCentralizedCode());
            educationalModule.setDrafters(requestEducationalModule.getDrafters());
            educationalModule.setEducationalCenters(requestEducationalModule.getEducationalCenters());
            educationalModule.setEducationalModuleGroup(requestEducationalModule.getEducationalModuleGroup());
            educationalModule.setEducationalModuleHeadlines(requestEducationalModule.getEducationalModuleHeadlines());
            educationalModule.setEducationalModuleHour(requestEducationalModule.getEducationalModuleHour());
            educationalModule.setEducationalModuleLevel(requestEducationalModule.getEducationalModuleLevel());
            educationalModule.setEvaluationMethod(requestEducationalModule.getEvaluationMethod());
            educationalModule.setGoals(requestEducationalModule.getGoals());
            educationalModule.setGoalsText(requestEducationalModule.getGoalsText());
            educationalModule.setInnerCode(requestEducationalModule.getInnerCode());
            educationalModule.setMoreDescription(requestEducationalModule.getMoreDescription());
            educationalModule.setPrerequisite(requestEducationalModule.getPrerequisite());
            educationalModule.setRequestEducationalModule(requestEducationalModule);
            educationalModule.setResources(requestEducationalModule.getResources());
            educationalModule.setSecurityLevel(requestEducationalModule.getSecurityLevel());
            educationalModule.setTeachers(requestEducationalModule.getTeachers());
            educationalModule.setTeachersText(requestEducationalModule.getTeachersText());
            educationalModule.setTimePassed(requestEducationalModule.getTimePassed());
            educationalModule.setVersion(requestEducationalModule.getVersion());
            educationalModule.setCreateDate(ZonedDateTime.now());
            educationalModule.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
            educationalModule.setModifyDate(ZonedDateTime.now());
            educationalModule.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
            educationalModule.setRestrictionDescription(requestEducationalModule.getRestrictionDescription());
            educationalModule.setRestrictions(requestEducationalModule.getRestrictions());

            educationalModule.setRecommendDate(requestEducationalModule.getRecommendDate());
            educationalModule.setGoalsBehavioralText(requestEducationalModule.getGoalsBehavioralText());
            educationalModule.setNeededHardware(requestEducationalModule.getNeededHardware());
            educationalModule.setNeededSoftwares(requestEducationalModule.getNeededSoftwares());
            educationalModule.setCourseContactsTerms(requestEducationalModule.getCourseContactsTerms());

            educationalModule.setPeopleUnderTrainings(requestEducationalModule.getPeopleUnderTrainings());
            educationalModule.setCompetency(requestEducationalModule.getCompetency());
            educationalModule.setTeachingApproaches(requestEducationalModule.getTeachingApproaches());
            educationalModule.setEffectivenessLevels(requestEducationalModule.getEffectivenessLevels());
            educationalModule.setEffectivenessIndices(requestEducationalModule.getEffectivenessIndices());

            educationalModule.setAssessmentMethods(requestEducationalModule.getAssessmentMethods());


            educationalModule = educationalModuleRepository.save(educationalModule);

            educationalModuleService.clearEducationalModuleCaches();

            if(!requestEducationalModule.getHeadlines().isEmpty()){
                for (Headline headline : requestEducationalModule.getHeadlines()) {
                    headline.setEducationalModule(educationalModule);
                    headline.setModifyDate(ZonedDateTime.now());
                    headline.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
                    headlineRepository.save(headline);
                }
            }
        } else {
            throw new Exception("کد پودمان آموزشی تکراریست و وجود دارد");
        }

        requestEducationalModule = requestEducationalModuleRepository.save(requestEducationalModule);
        return requestEducationalModuleMapper.toDto(requestEducationalModule);
    }

    /**
     * Get all the requestEducationalModules.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RequestEducationalModuleDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RequestEducationalModules");
        return requestEducationalModuleRepository.findAll(pageable)
            .map(requestEducationalModuleMapper::toDto);
    }

    /**
     * Get all the RequestEducationalModule with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<RequestEducationalModuleDTO> findAllWithEagerRelationships(Pageable pageable) {
        return requestEducationalModuleRepository.findAllWithEagerRelationships(pageable).map(requestEducationalModuleMapper::toDto);
    }


    /**
     * Get one requestEducationalModule by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RequestEducationalModuleDTO> findOne(Long id) {
        log.debug("Request to get RequestEducationalModule : {}", id);
        return requestEducationalModuleRepository.findOneWithEagerRelationships(id)
            .map(requestEducationalModuleMapper::toDto);
    }

    /**
     * Delete the requestEducationalModule by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RequestEducationalModule : {}", id);
        requestEducationalModuleRepository.deleteById(id);
    }
}
