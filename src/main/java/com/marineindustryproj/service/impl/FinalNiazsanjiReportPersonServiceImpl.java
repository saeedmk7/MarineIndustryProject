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
import com.marineindustryproj.service.dto.customs.CountListModel;
import com.marineindustryproj.service.dto.customs.FinalNiazsanjiPeopleListModel;
import com.marineindustryproj.service.mapper.FinalNiazsanjiReportPersonMapper;
import io.github.jhipster.service.filter.LongFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
     * Get all the FinalNiazsanjiReportPerson with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<FinalNiazsanjiReportPersonDTO> findAllWithEagerRelationships(Pageable pageable) {
        return finalNiazsanjiReportPersonRepository.findAllWithEagerRelationships(pageable).map(finalNiazsanjiReportPersonMapper::toDto);
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

    @Override
    public List<CountListModel> countListFinalNiazsanjiReportPeople(long[] finalNiazsanjiReportIds) {
        List<CountListModel> countListModels = new ArrayList<>();
        FinalNiazsanjiReportPersonCriteria criteria = new FinalNiazsanjiReportPersonCriteria();
        LongFilter finalNiazsanjiReportIdFilter = new LongFilter();
        for (Long finalNiazsanjiReportId : finalNiazsanjiReportIds) {
            finalNiazsanjiReportIdFilter.setEquals(finalNiazsanjiReportId);
            criteria.setFinalNiazsanjiReportId(finalNiazsanjiReportIdFilter);
            long count = finalNiazsanjiReportPersonQueryService.countByCriteria(criteria);

            countListModels.add(new CountListModel(finalNiazsanjiReportId, count));
        }
        return countListModels;
    }

    @Override
    public List<FinalNiazsanjiPeopleListModel> getFinalNiazsanjiReportPeopleList(long[] finalNiazsanjiReportIds) {
        List<FinalNiazsanjiPeopleListModel> finalNiazsanjiPeopleListModels = new ArrayList<>();
        FinalNiazsanjiReportPersonCriteria criteria = new FinalNiazsanjiReportPersonCriteria();
        LongFilter finalNiazsanjiReportIdFilter = new LongFilter();
        for (Long finalNiazsanjiReportId : finalNiazsanjiReportIds) {
            finalNiazsanjiReportIdFilter.setEquals(finalNiazsanjiReportId);
            criteria.setFinalNiazsanjiReportId(finalNiazsanjiReportIdFilter);
            List<FinalNiazsanjiReportPersonDTO> finalNiazsanjiReportPeople = finalNiazsanjiReportPersonQueryService.findByCriteria(criteria);
            List<String> peopleFullNames = new ArrayList<>();
            for (FinalNiazsanjiReportPersonDTO finalNiazsanjiReportPerson : finalNiazsanjiReportPeople) {
                    peopleFullNames.add(finalNiazsanjiReportPerson.getPersonName() + " " + finalNiazsanjiReportPerson.getPersonFamily());
            }
            finalNiazsanjiPeopleListModels.add(
                new FinalNiazsanjiPeopleListModel(finalNiazsanjiReportId, peopleFullNames));
        }
        return finalNiazsanjiPeopleListModels;
    }
}
