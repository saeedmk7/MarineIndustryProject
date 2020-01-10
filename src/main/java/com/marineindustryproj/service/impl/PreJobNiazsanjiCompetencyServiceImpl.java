package com.marineindustryproj.service.impl;

import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.PreJobNiazsanjiCompetencyService;
import com.marineindustryproj.domain.PreJobNiazsanjiCompetency;
import com.marineindustryproj.repository.PreJobNiazsanjiCompetencyRepository;
import com.marineindustryproj.service.dto.PreJobNiazsanjiCompetencyDTO;
import com.marineindustryproj.service.mapper.PreJobNiazsanjiCompetencyMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.Optional;

/**
 * Service Implementation for managing PreJobNiazsanjiCompetency.
 */
@Service
@Transactional
public class PreJobNiazsanjiCompetencyServiceImpl implements PreJobNiazsanjiCompetencyService {

    private final Logger log = LoggerFactory.getLogger(PreJobNiazsanjiCompetencyServiceImpl.class);

    private final PreJobNiazsanjiCompetencyRepository preJobNiazsanjiCompetencyRepository;

    private final PreJobNiazsanjiCompetencyMapper preJobNiazsanjiCompetencyMapper;

    public PreJobNiazsanjiCompetencyServiceImpl(PreJobNiazsanjiCompetencyRepository preJobNiazsanjiCompetencyRepository, PreJobNiazsanjiCompetencyMapper preJobNiazsanjiCompetencyMapper) {
        this.preJobNiazsanjiCompetencyRepository = preJobNiazsanjiCompetencyRepository;
        this.preJobNiazsanjiCompetencyMapper = preJobNiazsanjiCompetencyMapper;
    }

    /**
     * Save a preJobNiazsanjiCompetency.
     *
     * @param preJobNiazsanjiCompetencyDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public PreJobNiazsanjiCompetencyDTO save(PreJobNiazsanjiCompetencyDTO preJobNiazsanjiCompetencyDTO) {
        log.debug("Request to save PreJobNiazsanjiCompetency : {}", preJobNiazsanjiCompetencyDTO);

        PreJobNiazsanjiCompetency preJobNiazsanjiCompetency = preJobNiazsanjiCompetencyMapper.toEntity(preJobNiazsanjiCompetencyDTO);
        preJobNiazsanjiCompetency = preJobNiazsanjiCompetencyRepository.save(preJobNiazsanjiCompetency);
        return preJobNiazsanjiCompetencyMapper.toDto(preJobNiazsanjiCompetency);
    }

    /**
     * Save a preJobNiazsanjiCompetency.
     *
     * @param preJobNiazsanjiCompetencyDTOs the entity to save
     * @return the persisted entity
     */
    @Override
    public PreJobNiazsanjiCompetencyDTO saveBulk(PreJobNiazsanjiCompetencyDTO[] preJobNiazsanjiCompetencyDTOs) {
        PreJobNiazsanjiCompetency preJobNiazsanjiCompetency = new PreJobNiazsanjiCompetency();
        for (PreJobNiazsanjiCompetencyDTO preJobNiazsanjiCompetencyDTO : preJobNiazsanjiCompetencyDTOs) {
            PreJobNiazsanjiCompetencyDTO preJobNiazsanjiCompetencyOld = this.findOne(preJobNiazsanjiCompetencyDTO.getId()).get();
            preJobNiazsanjiCompetencyDTO.setCreateUserLogin(preJobNiazsanjiCompetencyOld.getCreateUserLogin());
            preJobNiazsanjiCompetencyDTO.setCreateDate(preJobNiazsanjiCompetencyOld.getCreateDate());
            preJobNiazsanjiCompetencyDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
            preJobNiazsanjiCompetencyDTO.setModifyDate(ZonedDateTime.now());
            preJobNiazsanjiCompetency = preJobNiazsanjiCompetencyMapper.toEntity(preJobNiazsanjiCompetencyDTO);
            preJobNiazsanjiCompetency = preJobNiazsanjiCompetencyRepository.save(preJobNiazsanjiCompetency);
        }
        return preJobNiazsanjiCompetencyMapper.toDto(preJobNiazsanjiCompetency);
    }

    /**
     * Get all the preJobNiazsanjiCompetencies.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PreJobNiazsanjiCompetencyDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PreJobNiazsanjiCompetencies");
        return preJobNiazsanjiCompetencyRepository.findAll(pageable)
            .map(preJobNiazsanjiCompetencyMapper::toDto);
    }

    /**
     * Get all the PreJobNiazsanjiCompetency with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<PreJobNiazsanjiCompetencyDTO> findAllWithEagerRelationships(Pageable pageable) {
        return preJobNiazsanjiCompetencyRepository.findAllWithEagerRelationships(pageable).map(preJobNiazsanjiCompetencyMapper::toDto);
    }
    

    /**
     * Get one preJobNiazsanjiCompetency by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PreJobNiazsanjiCompetencyDTO> findOne(Long id) {
        log.debug("Request to get PreJobNiazsanjiCompetency : {}", id);
        return preJobNiazsanjiCompetencyRepository.findOneWithEagerRelationships(id)
            .map(preJobNiazsanjiCompetencyMapper::toDto);
    }

    /**
     * Delete the preJobNiazsanjiCompetency by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PreJobNiazsanjiCompetency : {}", id);
        preJobNiazsanjiCompetencyRepository.deleteById(id);
    }
}
