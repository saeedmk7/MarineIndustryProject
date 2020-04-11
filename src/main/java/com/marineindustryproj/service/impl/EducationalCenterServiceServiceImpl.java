package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.EducationalCenterServiceService;
import com.marineindustryproj.domain.EducationalCenterService;
import com.marineindustryproj.repository.EducationalCenterServiceRepository;
import com.marineindustryproj.service.dto.EducationalCenterServiceDTO;
import com.marineindustryproj.service.mapper.EducationalCenterServiceMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing EducationalCenterService.
 */
@Service
@Transactional
public class EducationalCenterServiceServiceImpl implements EducationalCenterServiceService {

    private final Logger log = LoggerFactory.getLogger(EducationalCenterServiceServiceImpl.class);

    private final EducationalCenterServiceRepository educationalCenterServiceRepository;

    private final EducationalCenterServiceMapper educationalCenterServiceMapper;

    public EducationalCenterServiceServiceImpl(EducationalCenterServiceRepository educationalCenterServiceRepository, EducationalCenterServiceMapper educationalCenterServiceMapper) {
        this.educationalCenterServiceRepository = educationalCenterServiceRepository;
        this.educationalCenterServiceMapper = educationalCenterServiceMapper;
    }

    /**
     * Save a educationalCenterService.
     *
     * @param educationalCenterServiceDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public EducationalCenterServiceDTO save(EducationalCenterServiceDTO educationalCenterServiceDTO) {
        log.debug("Request to save EducationalCenterService : {}", educationalCenterServiceDTO);

        EducationalCenterService educationalCenterService = educationalCenterServiceMapper.toEntity(educationalCenterServiceDTO);
        educationalCenterService = educationalCenterServiceRepository.save(educationalCenterService);
        return educationalCenterServiceMapper.toDto(educationalCenterService);
    }

    /**
     * Get all the educationalCenterServices.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EducationalCenterServiceDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EducationalCenterServices");
        return educationalCenterServiceRepository.findAll(pageable)
            .map(educationalCenterServiceMapper::toDto);
    }


    /**
     * Get one educationalCenterService by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EducationalCenterServiceDTO> findOne(Long id) {
        log.debug("Request to get EducationalCenterService : {}", id);
        return educationalCenterServiceRepository.findById(id)
            .map(educationalCenterServiceMapper::toDto);
    }

    /**
     * Delete the educationalCenterService by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EducationalCenterService : {}", id);
        educationalCenterServiceRepository.deleteById(id);
    }
}
