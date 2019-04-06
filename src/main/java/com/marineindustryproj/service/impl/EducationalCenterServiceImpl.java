package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.EducationalCenterService;
import com.marineindustryproj.domain.EducationalCenter;
import com.marineindustryproj.repository.EducationalCenterRepository;
import com.marineindustryproj.service.dto.EducationalCenterDTO;
import com.marineindustryproj.service.mapper.EducationalCenterMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing EducationalCenter.
 */
@Service
@Transactional
public class EducationalCenterServiceImpl implements EducationalCenterService {

    private final Logger log = LoggerFactory.getLogger(EducationalCenterServiceImpl.class);

    private final EducationalCenterRepository educationalCenterRepository;

    private final EducationalCenterMapper educationalCenterMapper;

    public EducationalCenterServiceImpl(EducationalCenterRepository educationalCenterRepository, EducationalCenterMapper educationalCenterMapper) {
        this.educationalCenterRepository = educationalCenterRepository;
        this.educationalCenterMapper = educationalCenterMapper;
    }

    /**
     * Save a educationalCenter.
     *
     * @param educationalCenterDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public EducationalCenterDTO save(EducationalCenterDTO educationalCenterDTO) {
        log.debug("Request to save EducationalCenter : {}", educationalCenterDTO);

        EducationalCenter educationalCenter = educationalCenterMapper.toEntity(educationalCenterDTO);
        educationalCenter = educationalCenterRepository.save(educationalCenter);
        return educationalCenterMapper.toDto(educationalCenter);
    }

    /**
     * Get all the educationalCenters.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EducationalCenterDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EducationalCenters");
        return educationalCenterRepository.findAll(pageable)
            .map(educationalCenterMapper::toDto);
    }

    /**
     * Get all the EducationalCenter with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<EducationalCenterDTO> findAllWithEagerRelationships(Pageable pageable) {
        return educationalCenterRepository.findAllWithEagerRelationships(pageable).map(educationalCenterMapper::toDto);
    }
    

    /**
     * Get one educationalCenter by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EducationalCenterDTO> findOne(Long id) {
        log.debug("Request to get EducationalCenter : {}", id);
        return educationalCenterRepository.findOneWithEagerRelationships(id)
            .map(educationalCenterMapper::toDto);
    }

    /**
     * Delete the educationalCenter by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EducationalCenter : {}", id);
        educationalCenterRepository.deleteById(id);
    }
}
