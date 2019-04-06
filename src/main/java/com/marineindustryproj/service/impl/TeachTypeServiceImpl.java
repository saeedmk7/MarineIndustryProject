package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.TeachTypeService;
import com.marineindustryproj.domain.TeachType;
import com.marineindustryproj.repository.TeachTypeRepository;
import com.marineindustryproj.service.dto.TeachTypeDTO;
import com.marineindustryproj.service.mapper.TeachTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing TeachType.
 */
@Service
@Transactional
public class TeachTypeServiceImpl implements TeachTypeService {

    private final Logger log = LoggerFactory.getLogger(TeachTypeServiceImpl.class);

    private final TeachTypeRepository teachTypeRepository;

    private final TeachTypeMapper teachTypeMapper;

    public TeachTypeServiceImpl(TeachTypeRepository teachTypeRepository, TeachTypeMapper teachTypeMapper) {
        this.teachTypeRepository = teachTypeRepository;
        this.teachTypeMapper = teachTypeMapper;
    }

    /**
     * Save a teachType.
     *
     * @param teachTypeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public TeachTypeDTO save(TeachTypeDTO teachTypeDTO) {
        log.debug("Request to save TeachType : {}", teachTypeDTO);

        TeachType teachType = teachTypeMapper.toEntity(teachTypeDTO);
        teachType = teachTypeRepository.save(teachType);
        return teachTypeMapper.toDto(teachType);
    }

    /**
     * Get all the teachTypes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TeachTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TeachTypes");
        return teachTypeRepository.findAll(pageable)
            .map(teachTypeMapper::toDto);
    }


    /**
     * Get one teachType by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TeachTypeDTO> findOne(Long id) {
        log.debug("Request to get TeachType : {}", id);
        return teachTypeRepository.findById(id)
            .map(teachTypeMapper::toDto);
    }

    /**
     * Delete the teachType by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TeachType : {}", id);
        teachTypeRepository.deleteById(id);
    }
}
