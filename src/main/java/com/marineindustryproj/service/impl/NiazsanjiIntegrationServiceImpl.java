package com.marineindustryproj.service.impl;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.domain.enumeration.NiazSanjiSource;
import com.marineindustryproj.domain.enumeration.RequestStatus;
import com.marineindustryproj.repository.FinalNiazsanjiReportPersonRepository;
import com.marineindustryproj.repository.FinalNiazsanjiReportRepository;
import com.marineindustryproj.repository.PrioritizeRequestNiazsanjiRepository;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.NiazsanjiIntegrationService;
import com.marineindustryproj.repository.NiazsanjiIntegrationRepository;
import com.marineindustryproj.service.dto.NiazsanjiIntegrationDTO;
import com.marineindustryproj.service.mapper.NiazsanjiIntegrationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.Optional;

/**
 * Service Implementation for managing NiazsanjiIntegration.
 */
@Service
@Transactional
public class NiazsanjiIntegrationServiceImpl implements NiazsanjiIntegrationService {

    private final Logger log = LoggerFactory.getLogger(NiazsanjiIntegrationServiceImpl.class);

    private final NiazsanjiIntegrationRepository niazsanjiIntegrationRepository;

    private final PrioritizeRequestNiazsanjiRepository prioritizeRequestNiazsanjiRepository;

    private final FinalNiazsanjiReportRepository finalNiazsanjiReportRepository;

    private final FinalNiazsanjiReportPersonRepository finalNiazsanjiReportPersonRepository;

    private final NiazsanjiIntegrationMapper niazsanjiIntegrationMapper;

    public NiazsanjiIntegrationServiceImpl(NiazsanjiIntegrationRepository niazsanjiIntegrationRepository, PrioritizeRequestNiazsanjiRepository prioritizeRequestNiazsanjiRepository, FinalNiazsanjiReportRepository finalNiazsanjiReportRepository, FinalNiazsanjiReportPersonRepository finalNiazsanjiReportPersonRepository, NiazsanjiIntegrationMapper niazsanjiIntegrationMapper) {
        this.niazsanjiIntegrationRepository = niazsanjiIntegrationRepository;
        this.prioritizeRequestNiazsanjiRepository = prioritizeRequestNiazsanjiRepository;
        this.finalNiazsanjiReportRepository = finalNiazsanjiReportRepository;
        this.finalNiazsanjiReportPersonRepository = finalNiazsanjiReportPersonRepository;
        this.niazsanjiIntegrationMapper = niazsanjiIntegrationMapper;
    }

    /**
     * Save a niazsanjiIntegration.
     *
     * @param niazsanjiIntegrationDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public NiazsanjiIntegrationDTO save(NiazsanjiIntegrationDTO niazsanjiIntegrationDTO) {
        log.debug("Request to save NiazsanjiIntegration : {}", niazsanjiIntegrationDTO);

        NiazsanjiIntegration niazsanjiIntegration = niazsanjiIntegrationMapper.toEntity(niazsanjiIntegrationDTO);
        niazsanjiIntegration = niazsanjiIntegrationRepository.save(niazsanjiIntegration);
        return niazsanjiIntegrationMapper.toDto(niazsanjiIntegration);
    }

    /**
     * Save a niazsanjiIntegration.
     *
     * @param niazsanjiIntegrationDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public NiazsanjiIntegrationDTO finalize(NiazsanjiIntegrationDTO niazsanjiIntegrationDTO) {
        log.debug("Request to save NiazsanjiIntegration : {}", niazsanjiIntegrationDTO);

        NiazsanjiIntegration niazsanjiIntegration = niazsanjiIntegrationMapper.toEntity(niazsanjiIntegrationDTO);

        PrioritizeRequestNiazsanji prioritizeRequestNiazsanji = niazsanjiIntegration.getPrioritizeRequestNiazsanji();
        prioritizeRequestNiazsanji.setRequestStatus(RequestStatus.ACCEPT);
        FinalNiazsanjiReport finalNiazsanjiReport = new FinalNiazsanjiReport();

        EducationalModule educationalModule = prioritizeRequestNiazsanji.getEducationalModule();
        finalNiazsanjiReport.setOrganizationChart(prioritizeRequestNiazsanji.getOrganizationChart());
        finalNiazsanjiReport.setNiazsanjiYear(niazsanjiIntegration.getNiazsanjiYear());
        finalNiazsanjiReport.setEducationalModule(educationalModule);
        finalNiazsanjiReport.setArchived(false);
        finalNiazsanjiReport.setCreateDate(ZonedDateTime.now());
        finalNiazsanjiReport.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
        finalNiazsanjiReport.setModifyDate(ZonedDateTime.now());
        finalNiazsanjiReport.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        finalNiazsanjiReport.setDescription(prioritizeRequestNiazsanji.getDescription());
        finalNiazsanjiReport.setDocuments(prioritizeRequestNiazsanji.getDocuments());
        finalNiazsanjiReport.setNiazSanjiSource(getNiazsanjiSource(prioritizeRequestNiazsanji));
        finalNiazsanjiReport.setPriceCost(prioritizeRequestNiazsanji.getCostEducationalModule().intValue());
        finalNiazsanjiReport.setFinalizeCost(0);
        finalNiazsanjiReport.setCourseType(prioritizeRequestNiazsanji.getCourseType());
        finalNiazsanjiReport.setStatus(0);
        finalNiazsanjiReport.setHasImportantMessage(niazsanjiIntegration.isHasImportantMessage());
        finalNiazsanjiReport.setTeachingApproach(prioritizeRequestNiazsanji.getTeachingApproach());
        finalNiazsanjiReport.setGoalsText(prioritizeRequestNiazsanji.getGoalsText());
        finalNiazsanjiReport.setPrerequisite(prioritizeRequestNiazsanji.getPrerequisite());
        finalNiazsanjiReport.setRestrictionDescription(prioritizeRequestNiazsanji.getRestrictionDescription());
        finalNiazsanjiReport.setRestrictions(prioritizeRequestNiazsanji.getRestrictions());
        finalNiazsanjiReport.setPriority(prioritizeRequestNiazsanji.getPriority());
        finalNiazsanjiReport = finalNiazsanjiReportRepository.save(finalNiazsanjiReport);

        Person item = prioritizeRequestNiazsanji.getPerson();
        FinalNiazsanjiReportPerson finalNiazsanjiReportPerson = new FinalNiazsanjiReportPerson();
        finalNiazsanjiReportPerson.setFinalNiazsanjiReport(finalNiazsanjiReport);
        finalNiazsanjiReportPerson.setPerson(item);
        finalNiazsanjiReportPerson.setArchived(false);
        finalNiazsanjiReportPerson.setCreateDate(ZonedDateTime.now());
        finalNiazsanjiReportPerson.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
        finalNiazsanjiReportPerson.setModifyDate(ZonedDateTime.now());
        finalNiazsanjiReportPerson.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        finalNiazsanjiReportPerson.setNiazSanjiSource(getNiazsanjiSource(prioritizeRequestNiazsanji));
        finalNiazsanjiReportPerson.setPriceCost(prioritizeRequestNiazsanji.getCostEducationalModule().intValue());
        finalNiazsanjiReportPerson.setSourceId(niazsanjiIntegration.getId());
        finalNiazsanjiReportPerson.setStatus(0);
        finalNiazsanjiReportPersonRepository.save(finalNiazsanjiReportPerson);

        //NiazsanjiIntegration niazsanjiIntegration = niazsanjiIntegrationMapper.toEntity(niazsanjiIntegrationDTO);
        prioritizeRequestNiazsanji = prioritizeRequestNiazsanjiRepository.save(prioritizeRequestNiazsanji);
        niazsanjiIntegration = niazsanjiIntegrationRepository.save(niazsanjiIntegration);
        return niazsanjiIntegrationMapper.toDto(niazsanjiIntegration);
    }
    private NiazSanjiSource getNiazsanjiSource(PrioritizeRequestNiazsanji prioritizeRequestNiazsanji){
        NiazSanjiSource niazSanjiSource;
        switch (prioritizeRequestNiazsanji.getRequestNiazsanjiType()) {
            case FARDI:
                niazSanjiSource =  NiazSanjiSource.FARDI;
                break;
            case JOB:
                niazSanjiSource = NiazSanjiSource.JOB;
                break;
            case OTHER:
                niazSanjiSource = NiazSanjiSource.OTHER;
                break;
            default:
                niazSanjiSource =  NiazSanjiSource.FARDI;
                break;
        }
        return niazSanjiSource;
    }
    /**
     * Get all the niazsanjiIntegrations.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<NiazsanjiIntegrationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all NiazsanjiIntegrations");
        return niazsanjiIntegrationRepository.findAll(pageable)
            .map(niazsanjiIntegrationMapper::toDto);
    }


    /**
     * Get one niazsanjiIntegration by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<NiazsanjiIntegrationDTO> findOne(Long id) {
        log.debug("Request to get NiazsanjiIntegration : {}", id);
        return niazsanjiIntegrationRepository.findById(id)
            .map(niazsanjiIntegrationMapper::toDto);
    }

    /**
     * Delete the niazsanjiIntegration by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete NiazsanjiIntegration : {}", id);
        niazsanjiIntegrationRepository.deleteById(id);
    }
}
