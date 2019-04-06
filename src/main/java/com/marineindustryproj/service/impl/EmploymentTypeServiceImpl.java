package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.EmploymentTypeService;
import com.marineindustryproj.domain.EmploymentType;
import com.marineindustryproj.repository.EmploymentTypeRepository;
import com.marineindustryproj.service.dto.EmploymentTypeDTO;
import com.marineindustryproj.service.mapper.EmploymentTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing EmploymentType.
 */
@Service
@Transactional
public class EmploymentTypeServiceImpl implements EmploymentTypeService {

    private final Logger log = LoggerFactory.getLogger(EmploymentTypeServiceImpl.class);

    private final EmploymentTypeRepository employmentTypeRepository;

    private final EmploymentTypeMapper employmentTypeMapper;

    public EmploymentTypeServiceImpl(EmploymentTypeRepository employmentTypeRepository, EmploymentTypeMapper employmentTypeMapper) {
        this.employmentTypeRepository = employmentTypeRepository;
        this.employmentTypeMapper = employmentTypeMapper;
    }

    /**
     * Save a employmentType.
     *
     * @param employmentTypeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public EmploymentTypeDTO save(EmploymentTypeDTO employmentTypeDTO) {
        log.debug("Request to save EmploymentType : {}", employmentTypeDTO);

        EmploymentType employmentType = employmentTypeMapper.toEntity(employmentTypeDTO);
        employmentType = employmentTypeRepository.save(employmentType);
        return employmentTypeMapper.toDto(employmentType);
    }

    /**
     * Get all the employmentTypes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EmploymentTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EmploymentTypes");
        return employmentTypeRepository.findAll(pageable)
            .map(employmentTypeMapper::toDto);
    }


    /**
     * Get one employmentType by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EmploymentTypeDTO> findOne(Long id) {
        log.debug("Request to get EmploymentType : {}", id);
        return employmentTypeRepository.findById(id)
            .map(employmentTypeMapper::toDto);
    }

    /**
     * Delete the employmentType by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EmploymentType : {}", id);
        employmentTypeRepository.deleteById(id);
    }
}
