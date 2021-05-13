package com.marineindustryproj.service.impl;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.repository.OrganizationChartRepository;
import com.marineindustryproj.repository.PersonRepository;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.MatchingEducationalRecordService;
import com.marineindustryproj.repository.MatchingEducationalRecordRepository;
import com.marineindustryproj.service.MatchingRunRunningStepQueryService;
import com.marineindustryproj.service.MatchingRunRunningStepService;
import com.marineindustryproj.service.dto.MatchingEducationalRecordDTO;
import com.marineindustryproj.service.dto.MatchingRunRunningStepCriteria;
import com.marineindustryproj.service.dto.MatchingRunRunningStepDTO;
import com.marineindustryproj.service.dto.customs.MatchingEducationalRecordModels.MatchingEducationalRecordSaveDataItemModel;
import com.marineindustryproj.service.dto.customs.MatchingEducationalRecordModels.MatchingEducationalRecordSaveDataModel;
import com.marineindustryproj.service.mapper.MatchingEducationalRecordMapper;
import com.marineindustryproj.service.mapper.SkillableLevelOfSkillMapper;
import io.github.jhipster.service.filter.LongFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing MatchingEducationalRecord.
 */
@Service
@Transactional
public class MatchingEducationalRecordServiceImpl implements MatchingEducationalRecordService {

    private final Logger log = LoggerFactory.getLogger(MatchingEducationalRecordServiceImpl.class);

    private final MatchingEducationalRecordRepository matchingEducationalRecordRepository;

    private final MatchingEducationalRecordMapper matchingEducationalRecordMapper;

    private final SkillableLevelOfSkillMapper skillableLevelOfSkillMapper;

    private final PersonRepository personRepository;

    private final OrganizationChartRepository organizationChartRepository;

    private final MatchingRunRunningStepQueryService matchingRunRunningStepQueryService;

    private final MatchingRunRunningStepService matchingRunRunningStepService;

    public MatchingEducationalRecordServiceImpl(MatchingEducationalRecordRepository matchingEducationalRecordRepository, MatchingEducationalRecordMapper matchingEducationalRecordMapper, SkillableLevelOfSkillMapper skillableLevelOfSkillMapper, PersonRepository personRepository, OrganizationChartRepository organizationChartRepository, MatchingRunRunningStepQueryService matchingRunRunningStepQueryService, MatchingRunRunningStepService matchingRunRunningStepService) {
        this.matchingEducationalRecordRepository = matchingEducationalRecordRepository;
        this.matchingEducationalRecordMapper = matchingEducationalRecordMapper;
        this.skillableLevelOfSkillMapper = skillableLevelOfSkillMapper;
        this.personRepository = personRepository;
        this.organizationChartRepository = organizationChartRepository;
        this.matchingRunRunningStepQueryService = matchingRunRunningStepQueryService;
        this.matchingRunRunningStepService = matchingRunRunningStepService;
    }

    /**
     * Save a matchingEducationalRecord.
     *
     * @param matchingEducationalRecordDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public MatchingEducationalRecordDTO save(MatchingEducationalRecordDTO matchingEducationalRecordDTO) {
        log.debug("Request to save MatchingEducationalRecord : {}", matchingEducationalRecordDTO);

        MatchingEducationalRecord matchingEducationalRecord = matchingEducationalRecordMapper.toEntity(matchingEducationalRecordDTO);
        matchingEducationalRecord = matchingEducationalRecordRepository.save(matchingEducationalRecord);
        return matchingEducationalRecordMapper.toDto(matchingEducationalRecord);
    }

    @Override
    public MatchingEducationalRecordDTO saveDataModel(MatchingEducationalRecordSaveDataModel matchingEducationalRecordSaveDataModel) {
        log.debug("Request to save MatchingEducationalRecord : {}", matchingEducationalRecordSaveDataModel);

        MatchingEducationalRecord matchingEducationalRecord = new MatchingEducationalRecord();
        if(matchingEducationalRecordSaveDataModel.getMatchingEducationalRecordId() != null)
        {
            matchingEducationalRecord = matchingEducationalRecordRepository.getOne(matchingEducationalRecordSaveDataModel.getMatchingEducationalRecordId());
        }
        else{
            Person person = personRepository.getOne(matchingEducationalRecordSaveDataModel.getPersonId());
            matchingEducationalRecord.setPerson(person);

            OrganizationChart organizationChart = organizationChartRepository.getOne(matchingEducationalRecordSaveDataModel.getOrganizationChartId());
            matchingEducationalRecord.setOrganizationChart(organizationChart);

            matchingEducationalRecord.setArchived(false);
            matchingEducationalRecord.setGuid(UUID.randomUUID().toString());
            matchingEducationalRecord.setCreateDate(ZonedDateTime.now());
            matchingEducationalRecord.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
            matchingEducationalRecord.setConversation(matchingEducationalRecordSaveDataModel.getConversion());
            matchingEducationalRecord.setChartStatus(matchingEducationalRecordSaveDataModel.getChartStatus());
            matchingEducationalRecord.setBossStatus(matchingEducationalRecordSaveDataModel.getBossStatus());
            matchingEducationalRecord.setRequestStatus(matchingEducationalRecordSaveDataModel.getRequestStatus());
            matchingEducationalRecord.setHasImportantMessage(false);
        }


        matchingEducationalRecord.setDescription(matchingEducationalRecordSaveDataModel.getDescription());
        matchingEducationalRecord.setSelectedModuleIds(matchingEducationalRecordSaveDataModel.getSelectedModuleIds());
        matchingEducationalRecord.setModifyDate(ZonedDateTime.now());
        matchingEducationalRecord.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        matchingEducationalRecord.setChangeStatusUserLogin(SecurityUtils.getCurrentUserLogin().get());

        Set<SkillableLevelOfSkill> skillableLevelOfSkills = matchingEducationalRecordSaveDataModel.getSkillableLevelOfSkills().stream()
            .map(skillableLevelOfSkillMapper::toEntity).collect(Collectors.toSet());
        matchingEducationalRecord.setSkillableLevelOfSkills(skillableLevelOfSkills);

        matchingEducationalRecord = matchingEducationalRecordRepository.save(matchingEducationalRecord);

        MatchingRunRunningStepCriteria matchingRunRunningStepCriteria = new MatchingRunRunningStepCriteria();

        LongFilter matchingEducationalRecordIdFilter = new LongFilter();
        matchingEducationalRecordIdFilter.setEquals(matchingEducationalRecord.getId());
        matchingRunRunningStepCriteria.setMatchingEducationalRecordId(matchingEducationalRecordIdFilter);

        List<MatchingRunRunningStepDTO> matchingRunRunningSteps = matchingRunRunningStepQueryService.findByCriteria(matchingRunRunningStepCriteria);

        for (MatchingEducationalRecordSaveDataItemModel item: matchingEducationalRecordSaveDataModel.getMatchingEducationalRecordSaveDataItemModels()) {
            MatchingRunRunningStepDTO matchingRunRunningStep = new MatchingRunRunningStepDTO();
            List<MatchingRunRunningStepDTO> selectedRunRunningSteps = matchingRunRunningSteps.stream()
                .filter(run -> run.getMatchingRunningStepId().equals(item.getRunningStepId())).collect(Collectors.toList());
            if(!selectedRunRunningSteps.isEmpty())
            {
                matchingRunRunningStep = selectedRunRunningSteps.get(0); //matchingRunRunningSteps.stream().filter(a -> a.getRunningStep().getId() == item.getRunningStepId()).collect(Collectors.toList()).get(0);
                if(matchingRunRunningStep.isDone() == false && item.getDone()) {
                    matchingRunRunningStep.setDoneDate(ZonedDateTime.now());
                    matchingRunRunningStep.setDoneUserLogin(SecurityUtils.getCurrentUserLogin().get());
                    matchingRunRunningStep.setDone(true);
                }
                else{
                    matchingRunRunningStep.setDone(item.getDone());
                }
                matchingRunRunningStep.setDescription(item.getDescription());
                matchingRunRunningStep.setFileDoc(item.getFileDoc());
            }
            else{
                if(item.getDone()) {
                    matchingRunRunningStep.setDoneDate(ZonedDateTime.now());
                    matchingRunRunningStep.setDoneUserLogin(SecurityUtils.getCurrentUserLogin().get());
                    matchingRunRunningStep.setDone(true);
                }
                else
                    matchingRunRunningStep.setDone(false);

                matchingRunRunningStep.setDescription(item.getDescription());
                matchingRunRunningStep.setFileDoc(item.getFileDoc());
                matchingRunRunningStep.setCreateDate(ZonedDateTime.now());
                matchingRunRunningStep.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
                //RunningStep runningStep = runningStepRepository.getOne(item.getRunningStepId());
                matchingRunRunningStep.setMatchingRunningStepId(item.getRunningStepId());
                matchingRunRunningStep.setMatchingEducationalRecordId(matchingEducationalRecord.getId());
            }
            matchingRunRunningStepService.save(matchingRunRunningStep);
        }

        return matchingEducationalRecordMapper.toDto(matchingEducationalRecord);
    }

    /**
     * Get all the matchingEducationalRecords.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MatchingEducationalRecordDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MatchingEducationalRecords");
        return matchingEducationalRecordRepository.findAll(pageable)
            .map(matchingEducationalRecordMapper::toDto);
    }

    /**
     * Get all the MatchingEducationalRecord with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<MatchingEducationalRecordDTO> findAllWithEagerRelationships(Pageable pageable) {
        return matchingEducationalRecordRepository.findAllWithEagerRelationships(pageable).map(matchingEducationalRecordMapper::toDto);
    }
    

    /**
     * Get one matchingEducationalRecord by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MatchingEducationalRecordDTO> findOne(Long id) {
        log.debug("Request to get MatchingEducationalRecord : {}", id);
        return matchingEducationalRecordRepository.findOneWithEagerRelationships(id)
            .map(matchingEducationalRecordMapper::toDto);
    }

    /**
     * Delete the matchingEducationalRecord by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MatchingEducationalRecord : {}", id);
        matchingEducationalRecordRepository.deleteById(id);
    }
}
