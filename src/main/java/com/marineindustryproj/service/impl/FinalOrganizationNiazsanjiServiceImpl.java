package com.marineindustryproj.service.impl;

import com.marineindustryproj.domain.EducationalModule;
import com.marineindustryproj.domain.FinalNiazsanjiReport;
import com.marineindustryproj.domain.FinalNiazsanjiReportPerson;
import com.marineindustryproj.domain.Person;
import com.marineindustryproj.domain.enumeration.NiazSanjiSource;
import com.marineindustryproj.repository.*;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.FinalOrganizationNiazsanjiService;
import com.marineindustryproj.domain.FinalOrganizationNiazsanji;
import com.marineindustryproj.service.RequestOrganizationNiazsanjiService;
import com.marineindustryproj.service.dto.FinalOrganizationNiazsanjiDTO;
import com.marineindustryproj.service.mapper.FinalOrganizationNiazsanjiMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.Optional;

/**
 * Service Implementation for managing FinalOrganizationNiazsanji.
 */
@Service
@Transactional
public class FinalOrganizationNiazsanjiServiceImpl implements FinalOrganizationNiazsanjiService {

    private final Logger log = LoggerFactory.getLogger(FinalOrganizationNiazsanjiServiceImpl.class);

    private final FinalOrganizationNiazsanjiRepository finalOrganizationNiazsanjiRepository;
    private final RequestOrganizationNiazsanjiService requestOrganizationNiazsanjiService;

    private final EducationalModuleRepository educationalModuleRepository;
    private final FinalNiazsanjiReportRepository finalNiazsanjiReportRepository;
    private final FinalNiazsanjiReportPersonRepository finalNiazsanjiReportPersonRepository;

    private final FinalOrganizationNiazsanjiMapper finalOrganizationNiazsanjiMapper;

    public FinalOrganizationNiazsanjiServiceImpl(FinalOrganizationNiazsanjiRepository finalOrganizationNiazsanjiRepository,
                                                 RequestOrganizationNiazsanjiService requestOrganizationNiazsanjiService, EducationalModuleRepository educationalModuleRepository,
                                                 FinalNiazsanjiReportRepository finalNiazsanjiReportRepository,
                                                 FinalNiazsanjiReportPersonRepository finalNiazsanjiReportPersonRepository,
                                                 FinalOrganizationNiazsanjiMapper finalOrganizationNiazsanjiMapper) {
        this.finalOrganizationNiazsanjiRepository = finalOrganizationNiazsanjiRepository;
        this.requestOrganizationNiazsanjiService = requestOrganizationNiazsanjiService;
        this.finalNiazsanjiReportRepository = finalNiazsanjiReportRepository;
        this.finalNiazsanjiReportPersonRepository = finalNiazsanjiReportPersonRepository;
        this.finalOrganizationNiazsanjiMapper = finalOrganizationNiazsanjiMapper;
        this.educationalModuleRepository = educationalModuleRepository;
    }

    /**
     * Save a finalOrganizationNiazsanji.
     *
     * @param finalOrganizationNiazsanjiDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public FinalOrganizationNiazsanjiDTO finalize(FinalOrganizationNiazsanjiDTO finalOrganizationNiazsanjiDTO) {
        log.debug("Request to Sinalize FinalOrganizationNiazsanji : {}", finalOrganizationNiazsanjiDTO);

        FinalOrganizationNiazsanji finalOrganizationNiazsanji = finalOrganizationNiazsanjiMapper.toEntity(finalOrganizationNiazsanjiDTO);

        FinalNiazsanjiReport finalNiazsanjiReport = new FinalNiazsanjiReport();

        EducationalModule educationalModule = finalOrganizationNiazsanji.getEducationalModule();
        finalNiazsanjiReport.setNiazsanjiYear(finalOrganizationNiazsanji.getNiazsanjiYear());
        finalNiazsanjiReport.setOrganizationChart(finalOrganizationNiazsanji.getOrganizationChart());
        finalNiazsanjiReport.setEducationalModule(educationalModule);
        finalNiazsanjiReport.setArchived(false);
        finalNiazsanjiReport.setCreateDate(ZonedDateTime.now());
        finalNiazsanjiReport.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
        finalNiazsanjiReport.setDescription(finalOrganizationNiazsanjiDTO.getDescription());
        finalNiazsanjiReport.setDocuments(finalOrganizationNiazsanji.getDocuments());
        finalNiazsanjiReport.setNiazSanjiSource(NiazSanjiSource.ORGANIZATION);
        finalNiazsanjiReport.setPriceCost(finalOrganizationNiazsanjiDTO.getPriceCost());
        finalNiazsanjiReport.setStatus(0);
        finalNiazsanjiReport.setFinalizeCost(0);
        finalNiazsanjiReport.setCourseType(finalOrganizationNiazsanji.getCourseType());
        finalNiazsanjiReport = finalNiazsanjiReportRepository.save(finalNiazsanjiReport);

        for (Person item: finalOrganizationNiazsanji.getPeople()) {
            FinalNiazsanjiReportPerson finalNiazsanjiReportPerson = new FinalNiazsanjiReportPerson();
            finalNiazsanjiReportPerson.setFinalNiazsanjiReport(finalNiazsanjiReport);
            finalNiazsanjiReportPerson.setPerson(item);
            finalNiazsanjiReportPerson.setArchived(false);
            finalNiazsanjiReportPerson.setCreateDate(ZonedDateTime.now());
            finalNiazsanjiReportPerson.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
            finalNiazsanjiReportPerson.setNiazSanjiSource(NiazSanjiSource.ORGANIZATION);
            finalNiazsanjiReportPerson.setPriceCost(finalOrganizationNiazsanji.getPriceCost());
            finalNiazsanjiReportPerson.setSourceId(finalOrganizationNiazsanjiDTO.getId());
            finalNiazsanjiReportPerson.setStatus(0);
            finalNiazsanjiReportPersonRepository.save(finalNiazsanjiReportPerson);
        }

        //FinalOrganizationNiazsanji finalOrganizationNiazsanji = finalOrganizationNiazsanjiMapper.toEntity(finalOrganizationNiazsanjiDTO);
        finalOrganizationNiazsanji = finalOrganizationNiazsanjiRepository.save(finalOrganizationNiazsanji);
        return finalOrganizationNiazsanjiMapper.toDto(finalOrganizationNiazsanji);
    }
    /**
     * Save a finalOrganizationNiazsanji.
     *
     * @param finalOrganizationNiazsanjiDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public FinalOrganizationNiazsanjiDTO save(FinalOrganizationNiazsanjiDTO finalOrganizationNiazsanjiDTO) {
        log.debug("Request to save FinalOrganizationNiazsanji : {}", finalOrganizationNiazsanjiDTO);

        FinalOrganizationNiazsanji finalOrganizationNiazsanji = finalOrganizationNiazsanjiMapper.toEntity(finalOrganizationNiazsanjiDTO);
        finalOrganizationNiazsanji = finalOrganizationNiazsanjiRepository.save(finalOrganizationNiazsanji);
        return finalOrganizationNiazsanjiMapper.toDto(finalOrganizationNiazsanji);
    }
    /**
     * Get all the finalOrganizationNiazsanjis.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<FinalOrganizationNiazsanjiDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FinalOrganizationNiazsanjis");
        return finalOrganizationNiazsanjiRepository.findAll(pageable)
            .map(finalOrganizationNiazsanjiMapper::toDto);
    }

    /**
     * Get all the FinalOrganizationNiazsanji with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<FinalOrganizationNiazsanjiDTO> findAllWithEagerRelationships(Pageable pageable) {
        return finalOrganizationNiazsanjiRepository.findAllWithEagerRelationships(pageable).map(finalOrganizationNiazsanjiMapper::toDto);
    }
    

    /**
     * Get one finalOrganizationNiazsanji by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<FinalOrganizationNiazsanjiDTO> findOne(Long id) {
        log.debug("Request to get FinalOrganizationNiazsanji : {}", id);
        return finalOrganizationNiazsanjiRepository.findOneWithEagerRelationships(id)
            .map(finalOrganizationNiazsanjiMapper::toDto);
    }

    /**
     * Delete the finalOrganizationNiazsanji by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete FinalOrganizationNiazsanji : {}", id);
        requestOrganizationNiazsanjiService.deleteByFinalOrganizationNiazsanji(id);
        finalOrganizationNiazsanjiRepository.deleteById(id);
    }
}
