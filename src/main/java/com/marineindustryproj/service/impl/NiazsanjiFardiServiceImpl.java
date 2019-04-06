package com.marineindustryproj.service.impl;

import com.marineindustryproj.domain.EducationalModule;
import com.marineindustryproj.domain.FinalNiazsanjiReport;
import com.marineindustryproj.domain.FinalNiazsanjiReportPerson;
import com.marineindustryproj.domain.Person;
import com.marineindustryproj.domain.RequestNiazsanjiFardi;
import com.marineindustryproj.domain.enumeration.NiazSanjiSource;
import com.marineindustryproj.repository.EducationalModuleRepository;
import com.marineindustryproj.repository.FinalNiazsanjiReportPersonRepository;
import com.marineindustryproj.repository.FinalNiazsanjiReportRepository;
import com.marineindustryproj.repository.RequestNiazsanjiFardiRepository;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.NiazsanjiFardiService;
import com.marineindustryproj.domain.NiazsanjiFardi;
import com.marineindustryproj.repository.NiazsanjiFardiRepository;
import com.marineindustryproj.service.dto.NiazsanjiFardiDTO;
import com.marineindustryproj.service.mapper.NiazsanjiFardiMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.Optional;

/**
 * Service Implementation for managing NiazsanjiFardi.
 */
@Service
@Transactional
public class NiazsanjiFardiServiceImpl implements NiazsanjiFardiService {

    private final Logger log = LoggerFactory.getLogger(NiazsanjiFardiServiceImpl.class);

    private final NiazsanjiFardiRepository niazsanjiFardiRepository;
    private final RequestNiazsanjiFardiRepository requestNiazsanjiFardiRepository;

    private final NiazsanjiFardiMapper niazsanjiFardiMapper;

    private final EducationalModuleRepository educationalModuleRepository;
    private final FinalNiazsanjiReportRepository finalNiazsanjiReportRepository;
    private final FinalNiazsanjiReportPersonRepository finalNiazsanjiReportPersonRepository;

    public NiazsanjiFardiServiceImpl(NiazsanjiFardiRepository niazsanjiFardiRepository,
                                     RequestNiazsanjiFardiRepository requestNiazsanjiFardiRepository,
                                     NiazsanjiFardiMapper niazsanjiFardiMapper,
                                     EducationalModuleRepository educationalModuleRepository,
                                     FinalNiazsanjiReportRepository finalNiazsanjiReportRepository,
                                     FinalNiazsanjiReportPersonRepository finalNiazsanjiReportPersonRepository) {
        this.niazsanjiFardiRepository = niazsanjiFardiRepository;
        this.requestNiazsanjiFardiRepository = requestNiazsanjiFardiRepository;
        this.niazsanjiFardiMapper = niazsanjiFardiMapper;
        this.educationalModuleRepository = educationalModuleRepository;
        this.finalNiazsanjiReportRepository = finalNiazsanjiReportRepository;
        this.finalNiazsanjiReportPersonRepository = finalNiazsanjiReportPersonRepository;
    }

    /**
     * Save a niazsanjiFardi.
     *
     * @param niazsanjiFardiDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public NiazsanjiFardiDTO save(NiazsanjiFardiDTO niazsanjiFardiDTO) {
        log.debug("Request to save NiazsanjiFardi : {}", niazsanjiFardiDTO);

        NiazsanjiFardi niazsanjiFardi = niazsanjiFardiMapper.toEntity(niazsanjiFardiDTO);
        niazsanjiFardi = niazsanjiFardiRepository.save(niazsanjiFardi);
        return niazsanjiFardiMapper.toDto(niazsanjiFardi);
    }

    /**
     * Finalize a niazsanjiFardi.
     *
     * @param niazsanjiFardiDTO the entity to finalize
     * @return the persisted entity
     */
    @Override
    public NiazsanjiFardiDTO finalize(NiazsanjiFardiDTO niazsanjiFardiDTO) {
        log.debug("Request to finalize NiazsanjiFardi : {}", niazsanjiFardiDTO);

        NiazsanjiFardi niazsanjiFardi = niazsanjiFardiMapper.toEntity(niazsanjiFardiDTO);

        FinalNiazsanjiReport finalNiazsanjiReport = new FinalNiazsanjiReport();

        EducationalModule educationalModule = niazsanjiFardi.getEducationalModule();
        finalNiazsanjiReport.setOrganizationChart(niazsanjiFardi.getOrganizationChart());
        finalNiazsanjiReport.setNiazsanjiYear(niazsanjiFardi.getNiazsanjiYear());
        finalNiazsanjiReport.setEducationalModule(educationalModule);
        finalNiazsanjiReport.setArchived(false);
        finalNiazsanjiReport.setCreateDate(ZonedDateTime.now());
        finalNiazsanjiReport.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
        finalNiazsanjiReport.setDescription(niazsanjiFardi.getDescription());
        finalNiazsanjiReport.setDocuments(niazsanjiFardi.getDocuments());
        finalNiazsanjiReport.setNiazSanjiSource(NiazSanjiSource.FARDI);
        finalNiazsanjiReport.setPriceCost(niazsanjiFardi.getPriceCost().intValue());
        finalNiazsanjiReport.setStatus(0);
        finalNiazsanjiReport = finalNiazsanjiReportRepository.save(finalNiazsanjiReport);

        Person item = niazsanjiFardi.getPerson();
        FinalNiazsanjiReportPerson finalNiazsanjiReportPerson = new FinalNiazsanjiReportPerson();
        finalNiazsanjiReportPerson.setFinalNiazsanjiReport(finalNiazsanjiReport);
        finalNiazsanjiReportPerson.setPerson(item);
        finalNiazsanjiReportPerson.setArchived(false);
        finalNiazsanjiReportPerson.setCreateDate(ZonedDateTime.now());
        finalNiazsanjiReportPerson.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
        finalNiazsanjiReportPerson.setNiazSanjiSource(NiazSanjiSource.FARDI);
        finalNiazsanjiReportPerson.setPriceCost(niazsanjiFardi.getPriceCost().intValue());
        finalNiazsanjiReportPerson.setSourceId(niazsanjiFardi.getId());
        finalNiazsanjiReportPerson.setStatus(0);
        finalNiazsanjiReportPersonRepository.save(finalNiazsanjiReportPerson);


        niazsanjiFardi = niazsanjiFardiRepository.save(niazsanjiFardi);
        return niazsanjiFardiMapper.toDto(niazsanjiFardi);
    }

    /**
     * Get all the niazsanjiFardis.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<NiazsanjiFardiDTO> findAll(Pageable pageable) {
        log.debug("Request to get all NiazsanjiFardis");
        return niazsanjiFardiRepository.findAll(pageable)
            .map(niazsanjiFardiMapper::toDto);
    }

    /**
     * Get all the NiazsanjiFardi with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<NiazsanjiFardiDTO> findAllWithEagerRelationships(Pageable pageable) {
        return niazsanjiFardiRepository.findAllWithEagerRelationships(pageable).map(niazsanjiFardiMapper::toDto);
    }
    

    /**
     * Get one niazsanjiFardi by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<NiazsanjiFardiDTO> findOne(Long id) {
        log.debug("Request to get NiazsanjiFardi : {}", id);
        return niazsanjiFardiRepository.findOneWithEagerRelationships(id)
            .map(niazsanjiFardiMapper::toDto);
    }

    /**
     * Delete the niazsanjiFardi by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete NiazsanjiFardi : {}", id);
        niazsanjiFardiRepository.deleteById(id);
    }
}
