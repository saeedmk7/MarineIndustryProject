package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.DesignAndPlanningService;
import com.marineindustryproj.service.FinalNiazsanjiReportPersonQueryService;
import com.marineindustryproj.service.FinalNiazsanjiReportPersonService;
import com.marineindustryproj.domain.FinalNiazsanjiReportPerson;
import com.marineindustryproj.repository.FinalNiazsanjiReportPersonRepository;
import com.marineindustryproj.service.FinalNiazsanjiReportService;
import com.marineindustryproj.service.PersonService;
import com.marineindustryproj.service.RunPhaseService;
import com.marineindustryproj.service.dto.DesignAndPlanningDTO;
import com.marineindustryproj.service.dto.FinalNiazsanjiReportPersonCriteria;
import com.marineindustryproj.service.dto.FinalNiazsanjiReportPersonDTO;
import com.marineindustryproj.service.dto.PersonDTO;
import com.marineindustryproj.service.dto.RunPhaseDTO;
import com.marineindustryproj.service.mapper.FinalNiazsanjiReportPersonMapper;
import io.github.jhipster.service.filter.LongFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Service Implementation for managing FinalNiazsanjiReportPerson.
 */
@Service
@Transactional
public class FinalNiazsanjiReportPersonServiceImpl implements FinalNiazsanjiReportPersonService {

    private final Logger log = LoggerFactory.getLogger(FinalNiazsanjiReportPersonServiceImpl.class);

    private final FinalNiazsanjiReportPersonRepository finalNiazsanjiReportPersonRepository;

    private final FinalNiazsanjiReportPersonMapper finalNiazsanjiReportPersonMapper;

    private final FinalNiazsanjiReportPersonQueryService finalNiazsanjiReportPersonQueryService;

    private final RunPhaseService runPhaseService;

    private final DesignAndPlanningService designAndPlanningService;

    private final PersonService personService;

    public FinalNiazsanjiReportPersonServiceImpl(FinalNiazsanjiReportPersonRepository finalNiazsanjiReportPersonRepository,
                                                 FinalNiazsanjiReportPersonMapper finalNiazsanjiReportPersonMapper,
                                                 FinalNiazsanjiReportPersonQueryService finalNiazsanjiReportPersonQueryService,
                                                 RunPhaseService runPhaseService,
                                                 DesignAndPlanningService designAndPlanningService,
                                                 PersonService personService) {
        this.finalNiazsanjiReportPersonRepository = finalNiazsanjiReportPersonRepository;
        this.finalNiazsanjiReportPersonMapper = finalNiazsanjiReportPersonMapper;
        this.finalNiazsanjiReportPersonQueryService = finalNiazsanjiReportPersonQueryService;
        this.runPhaseService = runPhaseService;
        this.designAndPlanningService = designAndPlanningService;
        this.personService = personService;
    }

    /**
     * Save a finalNiazsanjiReportPerson.
     *
     * @param finalNiazsanjiReportPersonDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public FinalNiazsanjiReportPersonDTO save(FinalNiazsanjiReportPersonDTO finalNiazsanjiReportPersonDTO) {
        log.debug("Request to save FinalNiazsanjiReportPerson : {}", finalNiazsanjiReportPersonDTO);
        FinalNiazsanjiReportPerson finalNiazsanjiReportPerson = finalNiazsanjiReportPersonMapper.toEntity(finalNiazsanjiReportPersonDTO);
        Optional<RunPhaseDTO> runPhaseDTO = runPhaseService.findByFinalNiazsanjiReportId(finalNiazsanjiReportPerson.getFinalNiazsanjiReport().getId());
        if(runPhaseDTO.isPresent()){
            RunPhaseDTO runPhase = runPhaseDTO.get();
            Set<PersonDTO> runPhasePeople = runPhase.getPeople();
            Optional<PersonDTO> runPhasePersonDTO = personService.findOne(finalNiazsanjiReportPerson.getPerson().getId());
            if(runPhasePersonDTO.isPresent()) {
                runPhasePeople.add(runPhasePersonDTO.get());
            }
            runPhase.setPeople(runPhasePeople);
            runPhase = runPhaseService.save(runPhase);
        }
        Optional<DesignAndPlanningDTO> designAndPlanningDTO = designAndPlanningService.findByFinalNiazsanjiReportId(finalNiazsanjiReportPerson.getFinalNiazsanjiReport().getId());
        if(designAndPlanningDTO.isPresent()){
            DesignAndPlanningDTO designAndPlanning = designAndPlanningDTO.get();
            Set<PersonDTO> people = designAndPlanning.getPeople();
            Optional<PersonDTO> personDTO = personService.findOne(finalNiazsanjiReportPerson.getPerson().getId());
            if(personDTO.isPresent()) {
                people.add(personDTO.get());
            }
            designAndPlanning.setPeople(people);
            designAndPlanningService.save(designAndPlanning);
        }

        finalNiazsanjiReportPerson = finalNiazsanjiReportPersonRepository.save(finalNiazsanjiReportPerson);
        return finalNiazsanjiReportPersonMapper.toDto(finalNiazsanjiReportPerson);
    }

    /**
     * Get all the finalNiazsanjiReportPeople.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<FinalNiazsanjiReportPersonDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FinalNiazsanjiReportPeople");
        return finalNiazsanjiReportPersonRepository.findAll(pageable)
            .map(finalNiazsanjiReportPersonMapper::toDto);
    }


    /**
     * Get one finalNiazsanjiReportPerson by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<FinalNiazsanjiReportPersonDTO> findOne(Long id) {
        log.debug("Request to get FinalNiazsanjiReportPerson : {}", id);
        return finalNiazsanjiReportPersonRepository.findById(id)
            .map(finalNiazsanjiReportPersonMapper::toDto);
    }

    /**
     * Delete the finalNiazsanjiReportPerson by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete FinalNiazsanjiReportPerson : {}", id);
        Optional<FinalNiazsanjiReportPersonDTO> finalNiazsanjiReportPersonDTO1 = findOne(id);
        if(finalNiazsanjiReportPersonDTO1.isPresent()) {
            FinalNiazsanjiReportPersonDTO finalNiazsanjiReportPersonDTO = finalNiazsanjiReportPersonDTO1.get();
            Optional<RunPhaseDTO> runPhaseDTO = runPhaseService.findByFinalNiazsanjiReportId(finalNiazsanjiReportPersonDTO.getFinalNiazsanjiReportId());
            if (runPhaseDTO.isPresent()) {
                RunPhaseDTO runPhase = runPhaseDTO.get();
                Set<PersonDTO> runPhasePeople = runPhase.getPeople();
                Optional<PersonDTO> runPhasePersonDTO = personService.findOne(finalNiazsanjiReportPersonDTO.getPersonId());
                if (runPhasePersonDTO.isPresent()) {
                    runPhasePeople.remove(runPhasePersonDTO.get());
                }
                runPhase.setPeople(runPhasePeople);
                runPhaseService.save(runPhase);
            }
            Optional<DesignAndPlanningDTO> designAndPlanningDTO = designAndPlanningService.findByFinalNiazsanjiReportId(finalNiazsanjiReportPersonDTO.getFinalNiazsanjiReportId());
            if (designAndPlanningDTO.isPresent()) {
                DesignAndPlanningDTO designAndPlanning = designAndPlanningDTO.get();
                Set<PersonDTO> people = designAndPlanning.getPeople();
                Optional<PersonDTO> personDTO = personService.findOne(finalNiazsanjiReportPersonDTO.getPersonId());
                if (personDTO.isPresent()) {
                    people.remove(personDTO.get());
                }
                designAndPlanning.setPeople(people);
                designAndPlanningService.save(designAndPlanning);
            }
        }
        finalNiazsanjiReportPersonRepository.deleteById(id);
    }

    @Override
    public void deleteByFinalNiazsanjiReportId(Long finalNiazsanjiReportId) {
        log.debug("Request to delete FinalNiazsanjiReportPerson by finalNiazsanjiReportId: {}", finalNiazsanjiReportId);
        LongFilter filter = new LongFilter();
        filter.setEquals(finalNiazsanjiReportId);

        FinalNiazsanjiReportPersonCriteria finalNiazsanjiReportPersonCriteria = new FinalNiazsanjiReportPersonCriteria();
        finalNiazsanjiReportPersonCriteria.setFinalNiazsanjiReportId(filter);
        List<FinalNiazsanjiReportPersonDTO> finalNiazsanjiReportPeople =  finalNiazsanjiReportPersonQueryService.findByCriteria(finalNiazsanjiReportPersonCriteria);
        if(!finalNiazsanjiReportPeople.isEmpty()){
            for (FinalNiazsanjiReportPersonDTO finalNiazsanjiReportPerson : finalNiazsanjiReportPeople) {
                finalNiazsanjiReportPersonRepository.deleteById(finalNiazsanjiReportPerson.getId());
            }
        }
    }
}
