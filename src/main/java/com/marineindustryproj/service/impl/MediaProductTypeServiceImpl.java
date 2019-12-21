package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.MediaProductTypeService;
import com.marineindustryproj.domain.MediaProductType;
import com.marineindustryproj.repository.MediaProductTypeRepository;
import com.marineindustryproj.service.dto.MediaProductTypeDTO;
import com.marineindustryproj.service.mapper.MediaProductTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing MediaProductType.
 */
@Service
@Transactional
public class MediaProductTypeServiceImpl implements MediaProductTypeService {

    private final Logger log = LoggerFactory.getLogger(MediaProductTypeServiceImpl.class);

    private final MediaProductTypeRepository mediaProductTypeRepository;

    private final MediaProductTypeMapper mediaProductTypeMapper;

    public MediaProductTypeServiceImpl(MediaProductTypeRepository mediaProductTypeRepository, MediaProductTypeMapper mediaProductTypeMapper) {
        this.mediaProductTypeRepository = mediaProductTypeRepository;
        this.mediaProductTypeMapper = mediaProductTypeMapper;
    }

    /**
     * Save a mediaProductType.
     *
     * @param mediaProductTypeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public MediaProductTypeDTO save(MediaProductTypeDTO mediaProductTypeDTO) {
        log.debug("Request to save MediaProductType : {}", mediaProductTypeDTO);

        MediaProductType mediaProductType = mediaProductTypeMapper.toEntity(mediaProductTypeDTO);
        mediaProductType = mediaProductTypeRepository.save(mediaProductType);
        return mediaProductTypeMapper.toDto(mediaProductType);
    }

    /**
     * Get all the mediaProductTypes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MediaProductTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MediaProductTypes");
        return mediaProductTypeRepository.findAll(pageable)
            .map(mediaProductTypeMapper::toDto);
    }


    /**
     * Get one mediaProductType by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MediaProductTypeDTO> findOne(Long id) {
        log.debug("Request to get MediaProductType : {}", id);
        return mediaProductTypeRepository.findById(id)
            .map(mediaProductTypeMapper::toDto);
    }

    /**
     * Delete the mediaProductType by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MediaProductType : {}", id);
        mediaProductTypeRepository.deleteById(id);
    }
}
