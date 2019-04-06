package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.ServiceUnitService;
import com.marineindustryproj.domain.ServiceUnit;
import com.marineindustryproj.repository.ServiceUnitRepository;
import com.marineindustryproj.service.dto.ServiceUnitDTO;
import com.marineindustryproj.service.mapper.ServiceUnitMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing ServiceUnit.
 */
@Service
@Transactional
public class ServiceUnitServiceImpl implements ServiceUnitService {

    private final Logger log = LoggerFactory.getLogger(ServiceUnitServiceImpl.class);

    private final ServiceUnitRepository serviceUnitRepository;

    private final ServiceUnitMapper serviceUnitMapper;

    public ServiceUnitServiceImpl(ServiceUnitRepository serviceUnitRepository, ServiceUnitMapper serviceUnitMapper) {
        this.serviceUnitRepository = serviceUnitRepository;
        this.serviceUnitMapper = serviceUnitMapper;
    }

    /**
     * Save a serviceUnit.
     *
     * @param serviceUnitDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ServiceUnitDTO save(ServiceUnitDTO serviceUnitDTO) {
        log.debug("Request to save ServiceUnit : {}", serviceUnitDTO);

        ServiceUnit serviceUnit = serviceUnitMapper.toEntity(serviceUnitDTO);
        serviceUnit = serviceUnitRepository.save(serviceUnit);
        return serviceUnitMapper.toDto(serviceUnit);
    }

    /**
     * Get all the serviceUnits.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ServiceUnitDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ServiceUnits");
        return serviceUnitRepository.findAll(pageable)
            .map(serviceUnitMapper::toDto);
    }


    /**
     * Get one serviceUnit by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ServiceUnitDTO> findOne(Long id) {
        log.debug("Request to get ServiceUnit : {}", id);
        return serviceUnitRepository.findById(id)
            .map(serviceUnitMapper::toDto);
    }

    /**
     * Delete the serviceUnit by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ServiceUnit : {}", id);
        serviceUnitRepository.deleteById(id);
    }
}
