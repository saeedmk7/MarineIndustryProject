package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.RequestEducationalModuleService;
import com.marineindustryproj.domain.RequestEducationalModule;
import com.marineindustryproj.repository.RequestEducationalModuleRepository;
import com.marineindustryproj.service.dto.RequestEducationalModuleDTO;
import com.marineindustryproj.service.mapper.RequestEducationalModuleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing RequestEducationalModule.
 */
@Service
@Transactional
public class RequestEducationalModuleServiceImpl implements RequestEducationalModuleService {

    private final Logger log = LoggerFactory.getLogger(RequestEducationalModuleServiceImpl.class);

    private final RequestEducationalModuleRepository requestEducationalModuleRepository;

    private final RequestEducationalModuleMapper requestEducationalModuleMapper;

    public RequestEducationalModuleServiceImpl(RequestEducationalModuleRepository requestEducationalModuleRepository, RequestEducationalModuleMapper requestEducationalModuleMapper) {
        this.requestEducationalModuleRepository = requestEducationalModuleRepository;
        this.requestEducationalModuleMapper = requestEducationalModuleMapper;
    }

    /**
     * Save a requestEducationalModule.
     *
     * @param requestEducationalModuleDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public RequestEducationalModuleDTO save(RequestEducationalModuleDTO requestEducationalModuleDTO) {
        log.debug("Request to save RequestEducationalModule : {}", requestEducationalModuleDTO);

        RequestEducationalModule requestEducationalModule = requestEducationalModuleMapper.toEntity(requestEducationalModuleDTO);
        requestEducationalModule = requestEducationalModuleRepository.save(requestEducationalModule);
        return requestEducationalModuleMapper.toDto(requestEducationalModule);
    }

    /**
     * Get all the requestEducationalModules.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RequestEducationalModuleDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RequestEducationalModules");
        return requestEducationalModuleRepository.findAll(pageable)
            .map(requestEducationalModuleMapper::toDto);
    }

    /**
     * Get all the RequestEducationalModule with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<RequestEducationalModuleDTO> findAllWithEagerRelationships(Pageable pageable) {
        return requestEducationalModuleRepository.findAllWithEagerRelationships(pageable).map(requestEducationalModuleMapper::toDto);
    }
    

    /**
     * Get one requestEducationalModule by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RequestEducationalModuleDTO> findOne(Long id) {
        log.debug("Request to get RequestEducationalModule : {}", id);
        return requestEducationalModuleRepository.findOneWithEagerRelationships(id)
            .map(requestEducationalModuleMapper::toDto);
    }

    /**
     * Delete the requestEducationalModule by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RequestEducationalModule : {}", id);
        requestEducationalModuleRepository.deleteById(id);
    }
}
