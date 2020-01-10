package com.marineindustryproj.service.impl;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.domain.enumeration.NiazSanjiSource;
import com.marineindustryproj.repository.FinalNiazsanjiReportPersonRepository;
import com.marineindustryproj.repository.FinalNiazsanjiReportRepository;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.JobNiazsanjiService;
import com.marineindustryproj.repository.JobNiazsanjiRepository;
import com.marineindustryproj.service.dto.JobNiazsanjiDTO;
import com.marineindustryproj.service.mapper.JobNiazsanjiMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.Optional;

/**
 * Service Implementation for managing JobNiazsanji.
 */
@Service
@Transactional
public class JobNiazsanjiServiceImpl implements JobNiazsanjiService {

    private final Logger log = LoggerFactory.getLogger(JobNiazsanjiServiceImpl.class);

    private final JobNiazsanjiRepository jobNiazsanjiRepository;

    private final JobNiazsanjiMapper jobNiazsanjiMapper;

    private final FinalNiazsanjiReportRepository finalNiazsanjiReportRepository;

    private final FinalNiazsanjiReportPersonRepository finalNiazsanjiReportPersonRepository;

    public JobNiazsanjiServiceImpl(JobNiazsanjiRepository jobNiazsanjiRepository, JobNiazsanjiMapper jobNiazsanjiMapper, FinalNiazsanjiReportRepository finalNiazsanjiReportRepository, FinalNiazsanjiReportPersonRepository finalNiazsanjiReportPersonRepository) {
        this.jobNiazsanjiRepository = jobNiazsanjiRepository;
        this.jobNiazsanjiMapper = jobNiazsanjiMapper;
        this.finalNiazsanjiReportRepository = finalNiazsanjiReportRepository;
        this.finalNiazsanjiReportPersonRepository = finalNiazsanjiReportPersonRepository;
    }

    /**
     * Save a jobNiazsanji.
     *
     * @param jobNiazsanjiDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public JobNiazsanjiDTO save(JobNiazsanjiDTO jobNiazsanjiDTO) {
        log.debug("Request to save JobNiazsanji : {}", jobNiazsanjiDTO);

        JobNiazsanji jobNiazsanji = jobNiazsanjiMapper.toEntity(jobNiazsanjiDTO);
        jobNiazsanji = jobNiazsanjiRepository.save(jobNiazsanji);
        return jobNiazsanjiMapper.toDto(jobNiazsanji);
    }

    /**
     * Finalize a jobNiazsanji.
     *
     * @param jobNiazsanjiDTO the entity to finalize
     * @return the persisted entity
     */
    @Override
    public JobNiazsanjiDTO finalize(JobNiazsanjiDTO jobNiazsanjiDTO) {
        log.debug("Request to finalize JobNiazsanji : {}", jobNiazsanjiDTO);

        JobNiazsanji jobNiazsanji = jobNiazsanjiMapper.toEntity(jobNiazsanjiDTO);

        FinalNiazsanjiReport finalNiazsanjiReport = new FinalNiazsanjiReport();

        EducationalModule educationalModule = jobNiazsanji.getEducationalModule();
        finalNiazsanjiReport.setOrganizationChart(jobNiazsanji.getOrganizationChart());
        finalNiazsanjiReport.setNiazsanjiYear(jobNiazsanji.getNiazsanjiYear());
        finalNiazsanjiReport.setEducationalModule(educationalModule);
        finalNiazsanjiReport.setArchived(false);
        finalNiazsanjiReport.setCreateDate(ZonedDateTime.now());
        finalNiazsanjiReport.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
        finalNiazsanjiReport.setModifyDate(ZonedDateTime.now());
        finalNiazsanjiReport.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        finalNiazsanjiReport.setDescription(jobNiazsanji.getDescription());
        finalNiazsanjiReport.setDocuments(jobNiazsanji.getDocuments());
        finalNiazsanjiReport.setNiazSanjiSource(NiazSanjiSource.JOB);
        finalNiazsanjiReport.setPriceCost(jobNiazsanji.getPriceCost().intValue());
        finalNiazsanjiReport.setFinalizeCost(0);
        finalNiazsanjiReport.setCourseType(jobNiazsanji.getCourseType());
        finalNiazsanjiReport.setStatus(0);
        finalNiazsanjiReport.setHasImportantMessage(jobNiazsanji.isHasImportantMessage());
        finalNiazsanjiReport.setTeachingApproach(jobNiazsanji.getTeachingApproach());
        finalNiazsanjiReport.setGoalsText(jobNiazsanji.getGoalsText());
        finalNiazsanjiReport.setPrerequisite(jobNiazsanji.getPrerequisite());
        finalNiazsanjiReport.setRestrictionDescription(jobNiazsanji.getRestrictionDescription());
        finalNiazsanjiReport.setRestrictions(jobNiazsanji.getRestrictions());
        finalNiazsanjiReport = finalNiazsanjiReportRepository.save(finalNiazsanjiReport);

        Person item = jobNiazsanji.getPerson();
        FinalNiazsanjiReportPerson finalNiazsanjiReportPerson = new FinalNiazsanjiReportPerson();
        finalNiazsanjiReportPerson.setFinalNiazsanjiReport(finalNiazsanjiReport);
        finalNiazsanjiReportPerson.setPerson(item);
        finalNiazsanjiReportPerson.setArchived(false);
        finalNiazsanjiReportPerson.setCreateDate(ZonedDateTime.now());
        finalNiazsanjiReportPerson.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
        finalNiazsanjiReportPerson.setModifyDate(ZonedDateTime.now());
        finalNiazsanjiReportPerson.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        finalNiazsanjiReportPerson.setNiazSanjiSource(NiazSanjiSource.JOB);
        finalNiazsanjiReportPerson.setPriceCost(jobNiazsanji.getPriceCost().intValue());
        finalNiazsanjiReportPerson.setSourceId(jobNiazsanji.getId());
        finalNiazsanjiReportPerson.setStatus(0);
        finalNiazsanjiReportPersonRepository.save(finalNiazsanjiReportPerson);


        jobNiazsanji = jobNiazsanjiRepository.save(jobNiazsanji);
        return jobNiazsanjiMapper.toDto(jobNiazsanji);
    }

    /**
     * Get all the jobNiazsanjis.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<JobNiazsanjiDTO> findAll(Pageable pageable) {
        log.debug("Request to get all JobNiazsanjis");
        return jobNiazsanjiRepository.findAll(pageable)
            .map(jobNiazsanjiMapper::toDto);
    }

    /**
     * Get all the JobNiazsanji with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<JobNiazsanjiDTO> findAllWithEagerRelationships(Pageable pageable) {
        return jobNiazsanjiRepository.findAllWithEagerRelationships(pageable).map(jobNiazsanjiMapper::toDto);
    }
    

    /**
     * Get one jobNiazsanji by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<JobNiazsanjiDTO> findOne(Long id) {
        log.debug("Request to get JobNiazsanji : {}", id);
        return jobNiazsanjiRepository.findOneWithEagerRelationships(id)
            .map(jobNiazsanjiMapper::toDto);
    }

    /**
     * Delete the jobNiazsanji by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete JobNiazsanji : {}", id);
        jobNiazsanjiRepository.deleteById(id);
    }
}
