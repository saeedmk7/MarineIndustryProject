package com.marineindustryproj.service.impl;

import com.marineindustryproj.domain.FinalNiazsanjiReport;
import com.marineindustryproj.domain.Person;
import com.marineindustryproj.repository.FinalNiazsanjiReportRepository;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.DesignAndPlanningService;
import com.marineindustryproj.domain.DesignAndPlanning;
import com.marineindustryproj.repository.DesignAndPlanningRepository;
import com.marineindustryproj.service.dto.DesignAndPlanningDTO;
import com.marineindustryproj.service.mapper.DesignAndPlanningMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Service Implementation for managing DesignAndPlanning.
 */
@Service
@Transactional
public class DesignAndPlanningServiceImpl implements DesignAndPlanningService {

    private final Logger log = LoggerFactory.getLogger(DesignAndPlanningServiceImpl.class);

    private final DesignAndPlanningRepository designAndPlanningRepository;

    private final FinalNiazsanjiReportRepository finalNiazsanjiReportRepository;

    private final DesignAndPlanningMapper designAndPlanningMapper;

    public DesignAndPlanningServiceImpl(DesignAndPlanningRepository designAndPlanningRepository, FinalNiazsanjiReportRepository finalNiazsanjiReportRepository, DesignAndPlanningMapper designAndPlanningMapper) {
        this.designAndPlanningRepository = designAndPlanningRepository;
        this.finalNiazsanjiReportRepository = finalNiazsanjiReportRepository;
        this.designAndPlanningMapper = designAndPlanningMapper;
    }

    /**
     * Save a designAndPlanning.
     *
     * @param designAndPlanningDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public DesignAndPlanningDTO save(DesignAndPlanningDTO designAndPlanningDTO) {
        log.debug("Request to save DesignAndPlanning : {}", designAndPlanningDTO);

        FinalNiazsanjiReport finalNiazsanjiReport = finalNiazsanjiReportRepository.getOne(designAndPlanningDTO.getFinalNiazsanjiReportId());
        //finalNiazsanjiReport.setRunMonth(designAndPlanningDTO.getRunMonth());
        if(designAndPlanningDTO.isFinished() && designAndPlanningDTO.getStatus() < 10)
        {
            designAndPlanningDTO.setFinishedDate(ZonedDateTime.now());
            designAndPlanningDTO.setFinishedUserLogin(SecurityUtils.getCurrentUserLogin().get());

            designAndPlanningDTO.setStatus(10);


            finalNiazsanjiReport.setStatus(10);
        }

        finalNiazsanjiReportRepository.save(finalNiazsanjiReport);
        DesignAndPlanning designAndPlanning = designAndPlanningMapper.toEntity(designAndPlanningDTO);
        designAndPlanning.setEducationalModule(finalNiazsanjiReport.getEducationalModule());
        if(designAndPlanning.getPeople().isEmpty()) {
            long[] personIds = finalNiazsanjiReport.getFinalNiazsanjiReportPeople().stream().mapToLong(a ->a.getPerson().getId()).toArray();
            Set<Person> people = new HashSet<>();
            for (long personId : personIds) {
                Person person = new Person();
                person.setId(personId);
                people.add(person);
            }
            designAndPlanning.setPeople(people);
        }
        designAndPlanning = designAndPlanningRepository.save(designAndPlanning);
        return designAndPlanningMapper.toDto(designAndPlanning);
    }

    /**
     * Get all the designAndPlannings.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DesignAndPlanningDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DesignAndPlannings");
        return designAndPlanningRepository.findAll(pageable)
            .map(designAndPlanningMapper::toDto);
    }

    /**
     * Get all the DesignAndPlanning with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<DesignAndPlanningDTO> findAllWithEagerRelationships(Pageable pageable) {
        return designAndPlanningRepository.findAllWithEagerRelationships(pageable).map(designAndPlanningMapper::toDto);
    }
    

    /**
     * Get one designAndPlanning by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DesignAndPlanningDTO> findOne(Long id) {
        log.debug("Request to get DesignAndPlanning : {}", id);
        return designAndPlanningRepository.findOneWithEagerRelationships(id)
            .map(designAndPlanningMapper::toDto);
    }

    /**
     * Delete the designAndPlanning by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DesignAndPlanning : {}", id);
        designAndPlanningRepository.deleteById(id);
    }
}
