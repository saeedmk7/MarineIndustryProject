package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.TeachTechniqueService;
import com.marineindustryproj.domain.TeachTechnique;
import com.marineindustryproj.repository.TeachTechniqueRepository;
import com.marineindustryproj.service.dto.TeachTechniqueDTO;
import com.marineindustryproj.service.mapper.TeachTechniqueMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing TeachTechnique.
 */
@Service
@Transactional
public class TeachTechniqueServiceImpl implements TeachTechniqueService {

    private final Logger log = LoggerFactory.getLogger(TeachTechniqueServiceImpl.class);

    private final TeachTechniqueRepository teachTechniqueRepository;

    private final TeachTechniqueMapper teachTechniqueMapper;

    public TeachTechniqueServiceImpl(TeachTechniqueRepository teachTechniqueRepository, TeachTechniqueMapper teachTechniqueMapper) {
        this.teachTechniqueRepository = teachTechniqueRepository;
        this.teachTechniqueMapper = teachTechniqueMapper;
    }

    /**
     * Save a teachTechnique.
     *
     * @param teachTechniqueDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public TeachTechniqueDTO save(TeachTechniqueDTO teachTechniqueDTO) {
        log.debug("Request to save TeachTechnique : {}", teachTechniqueDTO);

        TeachTechnique teachTechnique = teachTechniqueMapper.toEntity(teachTechniqueDTO);
        teachTechnique = teachTechniqueRepository.save(teachTechnique);
        return teachTechniqueMapper.toDto(teachTechnique);
    }

    /**
     * Get all the teachTechniques.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TeachTechniqueDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TeachTechniques");
        return teachTechniqueRepository.findAll(pageable)
            .map(teachTechniqueMapper::toDto);
    }


    /**
     * Get one teachTechnique by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TeachTechniqueDTO> findOne(Long id) {
        log.debug("Request to get TeachTechnique : {}", id);
        return teachTechniqueRepository.findById(id)
            .map(teachTechniqueMapper::toDto);
    }

    /**
     * Delete the teachTechnique by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TeachTechnique : {}", id);
        teachTechniqueRepository.deleteById(id);
    }
}
