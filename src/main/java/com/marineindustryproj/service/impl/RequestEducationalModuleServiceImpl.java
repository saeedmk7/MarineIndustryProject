package com.marineindustryproj.service.impl;

import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.EducationalModuleQueryService;
import com.marineindustryproj.service.EducationalModuleService;
import com.marineindustryproj.service.HeadlineService;
import com.marineindustryproj.service.RequestEducationalModuleService;
import com.marineindustryproj.domain.RequestEducationalModule;
import com.marineindustryproj.repository.RequestEducationalModuleRepository;
import com.marineindustryproj.service.dto.EducationalModuleCriteria;
import com.marineindustryproj.service.dto.EducationalModuleDTO;
import com.marineindustryproj.service.dto.HeadlineDTO;
import com.marineindustryproj.service.dto.RequestEducationalModuleDTO;
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

    private final EducationalModuleService educationalModuleService;

    private final HeadlineService headlineService;

    private final EducationalModuleQueryService educationalModuleQueryService;

    public RequestEducationalModuleServiceImpl(RequestEducationalModuleRepository requestEducationalModuleRepository,
                                               RequestEducationalModuleMapper requestEducationalModuleMapper,
                                               EducationalModuleService educationalModuleService,
                                               HeadlineService headlineService, EducationalModuleQueryService educationalModuleQueryService) {
        this.requestEducationalModuleRepository = requestEducationalModuleRepository;
        this.requestEducationalModuleMapper = requestEducationalModuleMapper;
        this.educationalModuleService = educationalModuleService;
        this.headlineService = headlineService;
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
            for (HeadlineDTO headline : requestEducationalModuleDTO.getHeadlines()) {
                headline.setRequestEducationalModuleId(requestEducationalModule.getId());
                headline.setCreateDate(requestEducationalModule.getCreateDate());
                headline.setCreateUserLogin(requestEducationalModule.getCreateUserLogin());
                headline.setModifyDate(ZonedDateTime.now());
                headline.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
                headlineService.save(headline);
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

        EducationalModuleCriteria criteria = new EducationalModuleCriteria();
        LongFilter filter = new LongFilter();
        filter.setEquals(requestEducationalModuleDTO.getId());
        criteria.setRequestEducationalModuleId(filter);

        List<EducationalModuleDTO> educationalModules = educationalModuleQueryService.findByCriteria(criteria);

        if (educationalModules.isEmpty()) {
            EducationalModuleDTO educationalModuleDTO = new EducationalModuleDTO();
            educationalModuleDTO.setLearningTimePractical(requestEducationalModuleDTO.getLearningTimePractical());
            educationalModuleDTO.setLearningTimeTheorical(requestEducationalModuleDTO.getLearningTimeTheorical());
            educationalModuleDTO.setSkillableLevelOfSkillId(requestEducationalModuleDTO.getSkillableLevelOfSkillId());
            educationalModuleDTO.setCode(requestEducationalModuleDTO.getCode());
            educationalModuleDTO.setRecommendedBy(requestEducationalModuleDTO.getRecommendedBy());
            educationalModuleDTO.setId(Long.parseLong(requestEducationalModuleDTO.getCode()));
            educationalModuleDTO.setDocuments(requestEducationalModuleDTO.getDocuments());
            educationalModuleDTO.setArchived(false);
            educationalModuleDTO.setOrganizationId(requestEducationalModuleDTO.getOrganizationId());
            educationalModuleDTO.setScientificWorkGroups(requestEducationalModuleDTO.getScientificWorkGroups());
            educationalModuleDTO.setStatus(0);
            educationalModuleDTO.setTitle(requestEducationalModuleDTO.getTitle());
            educationalModuleDTO.setCredit(requestEducationalModuleDTO.getCredit());
            educationalModuleDTO.setCentralizedCode(requestEducationalModuleDTO.getCentralizedCode());
            educationalModuleDTO.setDrafters(requestEducationalModuleDTO.getDrafters());
            educationalModuleDTO.setEducationalCenters(requestEducationalModuleDTO.getEducationalCenters());
            educationalModuleDTO.setEducationalModuleGroup(requestEducationalModuleDTO.getEducationalModuleGroup());
            educationalModuleDTO.setEducationalModuleHeadlines(requestEducationalModuleDTO.getEducationalModuleHeadlines());
            educationalModuleDTO.setEducationalModuleHour(requestEducationalModuleDTO.getEducationalModuleHour());
            educationalModuleDTO.setEducationalModuleLevel(requestEducationalModuleDTO.getEducationalModuleLevel());
            educationalModuleDTO.setEvaluationMethodId(requestEducationalModuleDTO.getEvaluationMethodId());
            educationalModuleDTO.setGoals(requestEducationalModuleDTO.getGoals());
            educationalModuleDTO.setGoalsText(requestEducationalModuleDTO.getGoalsText());
            educationalModuleDTO.setInnerCode(requestEducationalModuleDTO.getInnerCode());
            educationalModuleDTO.setMoreDescription(requestEducationalModuleDTO.getMoreDescription());
            educationalModuleDTO.setPrerequisite(requestEducationalModuleDTO.getPrerequisite());
            educationalModuleDTO.setRequestEducationalModuleId(requestEducationalModuleDTO.getId());
            educationalModuleDTO.setResources(requestEducationalModuleDTO.getResources());
            educationalModuleDTO.setSecurityLevelId(requestEducationalModuleDTO.getSecurityLevelId());
            educationalModuleDTO.setTeachers(requestEducationalModuleDTO.getTeachers());
            educationalModuleDTO.setTeachersText(requestEducationalModuleDTO.getTeachersText());
            educationalModuleDTO.setTimePassed(requestEducationalModuleDTO.getTimePassed());
            educationalModuleDTO.setVersion(requestEducationalModuleDTO.getVersion());
            educationalModuleDTO.setCreateDate(ZonedDateTime.now());
            educationalModuleDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
            educationalModuleDTO.setModifyDate(ZonedDateTime.now());
            educationalModuleDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
            educationalModuleDTO.setRestrictionDescription(requestEducationalModuleDTO.getRestrictionDescription());
            educationalModuleDTO.setRestrictions(requestEducationalModuleDTO.getRestrictions());

            educationalModuleDTO.setRecommendDate(requestEducationalModuleDTO.getRecommendDate());
            educationalModuleDTO.setGoalsBehavioralText(requestEducationalModuleDTO.getGoalsBehavioralText());
            educationalModuleDTO.setNeededHardware(requestEducationalModuleDTO.getNeededHardware());
            educationalModuleDTO.setNeededSoftwares(requestEducationalModuleDTO.getNeededSoftwares());
            educationalModuleDTO.setCourseContactsTerms(requestEducationalModuleDTO.getCourseContactsTerms());

            educationalModuleDTO.setPeopleUnderTrainings(requestEducationalModuleDTO.getPeopleUnderTrainings());
            educationalModuleDTO.setCompetencyId(requestEducationalModuleDTO.getCompetencyId());
            educationalModuleDTO.setTeachingApproaches(requestEducationalModuleDTO.getTeachingApproaches());
            educationalModuleDTO.setEffectivenessLevels(requestEducationalModuleDTO.getEffectivenessLevels());
            educationalModuleDTO.setEffectivenessIndices(requestEducationalModuleDTO.getEffectivenessIndices());


            EducationalModuleDTO educationalModule = educationalModuleService.save(educationalModuleDTO);

            if(!requestEducationalModuleDTO.getHeadlines().isEmpty()){
                for (HeadlineDTO headline : requestEducationalModuleDTO.getHeadlines()) {
                    headline.setEducationalModuleId(educationalModule.getId());
                    headline.setModifyDate(ZonedDateTime.now());
                    headline.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
                    headlineService.save(headline);
                }
            }
        } else {
            throw new Exception("کد پودمان آموزشی تکراریست و وجود دارد");
        }
        RequestEducationalModule requestEducationalModule = requestEducationalModuleMapper.toEntity(requestEducationalModuleDTO);
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
