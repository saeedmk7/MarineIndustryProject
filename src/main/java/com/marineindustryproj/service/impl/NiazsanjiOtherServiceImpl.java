package com.marineindustryproj.service.impl;

import com.marineindustryproj.domain.*;
import com.marineindustryproj.domain.enumeration.NiazSanjiSource;
import com.marineindustryproj.repository.FinalNiazsanjiReportPersonRepository;
import com.marineindustryproj.repository.FinalNiazsanjiReportRepository;
import com.marineindustryproj.repository.RequestOtherNiazsanjiRepository;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.NiazsanjiOtherService;
import com.marineindustryproj.repository.NiazsanjiOtherRepository;
import com.marineindustryproj.service.RequestOtherNiazsanjiService;
import com.marineindustryproj.service.dto.NiazsanjiOtherDTO;
import com.marineindustryproj.service.mapper.NiazsanjiOtherMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.Optional;

/**
 * Service Implementation for managing NiazsanjiOther.
 */
@Service
@Transactional
public class NiazsanjiOtherServiceImpl implements NiazsanjiOtherService {

    private final Logger log = LoggerFactory.getLogger(NiazsanjiOtherServiceImpl.class);

    private final NiazsanjiOtherRepository niazsanjiOtherRepository;

    private final NiazsanjiOtherMapper niazsanjiOtherMapper;

    private final RequestOtherNiazsanjiRepository requestOtherNiazsanjiRepository;

    private final RequestOtherNiazsanjiService requestOtherNiazsanjiService;

    private final FinalNiazsanjiReportRepository finalNiazsanjiReportRepository;

    private final FinalNiazsanjiReportPersonRepository finalNiazsanjiReportPersonRepository;

    public NiazsanjiOtherServiceImpl(NiazsanjiOtherRepository niazsanjiOtherRepository, NiazsanjiOtherMapper niazsanjiOtherMapper, RequestOtherNiazsanjiRepository requestOtherNiazsanjiRepository, RequestOtherNiazsanjiService requestOtherNiazsanjiService, FinalNiazsanjiReportRepository finalNiazsanjiReportRepository, FinalNiazsanjiReportPersonRepository finalNiazsanjiReportPersonRepository) {
        this.niazsanjiOtherRepository = niazsanjiOtherRepository;
        this.niazsanjiOtherMapper = niazsanjiOtherMapper;
        this.requestOtherNiazsanjiRepository = requestOtherNiazsanjiRepository;
        this.requestOtherNiazsanjiService = requestOtherNiazsanjiService;
        this.finalNiazsanjiReportRepository = finalNiazsanjiReportRepository;
        this.finalNiazsanjiReportPersonRepository = finalNiazsanjiReportPersonRepository;
    }

    /**
     * Save a niazsanjiOther.
     *
     * @param niazsanjiOtherDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public NiazsanjiOtherDTO save(NiazsanjiOtherDTO niazsanjiOtherDTO) {
        log.debug("Request to save NiazsanjiOther : {}", niazsanjiOtherDTO);

        NiazsanjiOther niazsanjiOther = niazsanjiOtherMapper.toEntity(niazsanjiOtherDTO);
        niazsanjiOther = niazsanjiOtherRepository.save(niazsanjiOther);
        return niazsanjiOtherMapper.toDto(niazsanjiOther);
    }

    /**
     * Finalize a niazsanjiOther.
     *
     * @param niazsanjiOtherDTO the entity to finalize
     * @return the persisted entity
     */
    @Override
    public NiazsanjiOtherDTO finalize(NiazsanjiOtherDTO niazsanjiOtherDTO) {
        log.debug("Request to finalize NiazsanjiFardi : {}", niazsanjiOtherDTO);

        NiazsanjiOther niazsanjiOther = niazsanjiOtherMapper.toEntity(niazsanjiOtherDTO);

        FinalNiazsanjiReport finalNiazsanjiReport = new FinalNiazsanjiReport();

        EducationalModule educationalModule = niazsanjiOther.getEducationalModule();
        finalNiazsanjiReport.setOrganizationChart(niazsanjiOther.getOrganizationChart());
        finalNiazsanjiReport.setNiazsanjiYear(niazsanjiOther.getNiazsanjiYear());
        finalNiazsanjiReport.setEducationalModule(educationalModule);
        finalNiazsanjiReport.setArchived(false);
        finalNiazsanjiReport.setCreateDate(ZonedDateTime.now());
        finalNiazsanjiReport.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
        finalNiazsanjiReport.setModifyDate(ZonedDateTime.now());
        finalNiazsanjiReport.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        finalNiazsanjiReport.setDescription(niazsanjiOther.getDescription());
        finalNiazsanjiReport.setDocuments(niazsanjiOther.getDocuments());
        finalNiazsanjiReport.setNiazSanjiSource(NiazSanjiSource.OTHER);
        finalNiazsanjiReport.setPriceCost(niazsanjiOther.getPriceCost().intValue());
        finalNiazsanjiReport.setFinalizeCost(0);
        finalNiazsanjiReport.setCourseType(niazsanjiOther.getCourseType());
        finalNiazsanjiReport.setStatus(0);
        finalNiazsanjiReport.setHasImportantMessage(niazsanjiOther.isHasImportantMessage());
        finalNiazsanjiReport.setRestrictions(niazsanjiOther.getRestrictions());
        finalNiazsanjiReport.setRestrictionDescription(niazsanjiOther.getRestrictionDescription());
        finalNiazsanjiReport.setPrerequisite(niazsanjiOther.getPrerequisite());
        finalNiazsanjiReport.setGoalsText(niazsanjiOther.getGoalsText());
        finalNiazsanjiReport.setTeachingApproach(niazsanjiOther.getTeachingApproach());
        finalNiazsanjiReport.setNiazsanjiInput(niazsanjiOther.getNiazsanjiInput());
        finalNiazsanjiReport = finalNiazsanjiReportRepository.save(finalNiazsanjiReport);

        Person item = niazsanjiOther.getPerson();
        FinalNiazsanjiReportPerson finalNiazsanjiReportPerson = new FinalNiazsanjiReportPerson();
        finalNiazsanjiReportPerson.setFinalNiazsanjiReport(finalNiazsanjiReport);
        finalNiazsanjiReportPerson.setPerson(item);
        finalNiazsanjiReportPerson.setArchived(false);
        finalNiazsanjiReportPerson.setCreateDate(ZonedDateTime.now());
        finalNiazsanjiReportPerson.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
        finalNiazsanjiReportPerson.setModifyDate(ZonedDateTime.now());
        finalNiazsanjiReportPerson.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        finalNiazsanjiReportPerson.setNiazSanjiSource(NiazSanjiSource.FARDI);
        finalNiazsanjiReportPerson.setPriceCost(niazsanjiOther.getPriceCost().intValue());
        finalNiazsanjiReportPerson.setSourceId(niazsanjiOther.getId());
        finalNiazsanjiReportPerson.setStatus(0);
        finalNiazsanjiReportPersonRepository.save(finalNiazsanjiReportPerson);


        niazsanjiOther = niazsanjiOtherRepository.save(niazsanjiOther);
        return niazsanjiOtherMapper.toDto(niazsanjiOther);
    }

    /**
     * Get all the niazsanjiOthers.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<NiazsanjiOtherDTO> findAll(Pageable pageable) {
        log.debug("Request to get all NiazsanjiOthers");
        return niazsanjiOtherRepository.findAll(pageable)
            .map(niazsanjiOtherMapper::toDto);
    }

    /**
     * Get all the NiazsanjiOther with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<NiazsanjiOtherDTO> findAllWithEagerRelationships(Pageable pageable) {
        return niazsanjiOtherRepository.findAllWithEagerRelationships(pageable).map(niazsanjiOtherMapper::toDto);
    }
    

    /**
     * Get one niazsanjiOther by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<NiazsanjiOtherDTO> findOne(Long id) {
        log.debug("Request to get NiazsanjiOther : {}", id);
        return niazsanjiOtherRepository.findOneWithEagerRelationships(id)
            .map(niazsanjiOtherMapper::toDto);
    }

    /**
     * Delete the niazsanjiOther by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete NiazsanjiOther : {}", id);
        niazsanjiOtherRepository.deleteById(id);
    }
}
