package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.PeopleUnderTrainingService;
import com.marineindustryproj.domain.PeopleUnderTraining;
import com.marineindustryproj.repository.PeopleUnderTrainingRepository;
import com.marineindustryproj.service.dto.PeopleUnderTrainingDTO;
import com.marineindustryproj.service.mapper.PeopleUnderTrainingMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing PeopleUnderTraining.
 */
@Service
@Transactional
public class PeopleUnderTrainingServiceImpl implements PeopleUnderTrainingService {

    private final Logger log = LoggerFactory.getLogger(PeopleUnderTrainingServiceImpl.class);

    private final PeopleUnderTrainingRepository peopleUnderTrainingRepository;

    private final PeopleUnderTrainingMapper peopleUnderTrainingMapper;

    public PeopleUnderTrainingServiceImpl(PeopleUnderTrainingRepository peopleUnderTrainingRepository, PeopleUnderTrainingMapper peopleUnderTrainingMapper) {
        this.peopleUnderTrainingRepository = peopleUnderTrainingRepository;
        this.peopleUnderTrainingMapper = peopleUnderTrainingMapper;
    }

    /**
     * Save a peopleUnderTraining.
     *
     * @param peopleUnderTrainingDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public PeopleUnderTrainingDTO save(PeopleUnderTrainingDTO peopleUnderTrainingDTO) {
        log.debug("Request to save PeopleUnderTraining : {}", peopleUnderTrainingDTO);

        PeopleUnderTraining peopleUnderTraining = peopleUnderTrainingMapper.toEntity(peopleUnderTrainingDTO);
        peopleUnderTraining = peopleUnderTrainingRepository.save(peopleUnderTraining);
        return peopleUnderTrainingMapper.toDto(peopleUnderTraining);
    }

    /**
     * Get all the peopleUnderTrainings.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PeopleUnderTrainingDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PeopleUnderTrainings");
        return peopleUnderTrainingRepository.findAll(pageable)
            .map(peopleUnderTrainingMapper::toDto);
    }


    /**
     * Get one peopleUnderTraining by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PeopleUnderTrainingDTO> findOne(Long id) {
        log.debug("Request to get PeopleUnderTraining : {}", id);
        return peopleUnderTrainingRepository.findById(id)
            .map(peopleUnderTrainingMapper::toDto);
    }

    /**
     * Delete the peopleUnderTraining by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PeopleUnderTraining : {}", id);
        peopleUnderTrainingRepository.deleteById(id);
    }
}
