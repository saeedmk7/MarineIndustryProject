package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.EducationalModuleService;
import com.marineindustryproj.domain.EducationalModule;
import com.marineindustryproj.repository.EducationalModuleRepository;
import com.marineindustryproj.service.dto.EducationalModuleDTO;
import com.marineindustryproj.service.dto.customs.EducationalModuleMinDTO;
import com.marineindustryproj.service.mapper.EducationalModuleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Service Implementation for managing EducationalModule.
 */
@Service
@Transactional
public class EducationalModuleServiceImpl implements EducationalModuleService {

    private final Logger log = LoggerFactory.getLogger(EducationalModuleServiceImpl.class);

    private final EducationalModuleRepository educationalModuleRepository;

    private final EducationalModuleMapper educationalModuleMapper;

    private final CacheManager cacheManager;

    public EducationalModuleServiceImpl(EducationalModuleRepository educationalModuleRepository,
                                        EducationalModuleMapper educationalModuleMapper,
                                        CacheManager cacheManager) {
        this.educationalModuleRepository = educationalModuleRepository;
        this.educationalModuleMapper = educationalModuleMapper;
        this.cacheManager = cacheManager;
    }

    /**
     * Save a educationalModule.
     *
     * @param educationalModuleDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public EducationalModuleDTO save(EducationalModuleDTO educationalModuleDTO) {
        log.debug("Request to save EducationalModule : {}", educationalModuleDTO);

        EducationalModule educationalModule = educationalModuleMapper.toEntity(educationalModuleDTO);
        educationalModule = educationalModuleRepository.save(educationalModule);
        clearEducationalModuleCaches();
        return educationalModuleMapper.toDto(educationalModule);
    }

    /**
     * Get all the educationalModules.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EducationalModuleDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EducationalModules");
        return educationalModuleRepository.findAll(pageable)
            .map(educationalModuleMapper::toDto);
    }


    /**
     * Get all the educationalModules.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<EducationalModuleMinDTO> findAllFromCache() {
        log.debug("Request to get all EducationalModules");
        return educationalModuleRepository.findAllFromCache();
    }

    /**
     * Get all the EducationalModule with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<EducationalModuleDTO> findAllWithEagerRelationships(Pageable pageable) {
        return educationalModuleRepository.findAllWithEagerRelationships(pageable).map(educationalModuleMapper::toDto);
    }
    

    /**
     * Get one educationalModule by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EducationalModuleDTO> findOne(Long id) {
        log.debug("Request to get EducationalModule : {}", id);
        return educationalModuleRepository.findOneWithEagerRelationships(id)
            .map(educationalModuleMapper::toDto);
    }

    /**
     * Delete the educationalModule by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EducationalModule : {}", id);
        educationalModuleRepository.deleteById(id);
        clearEducationalModuleCaches();
    }

    private void clearEducationalModuleCaches() {
        Objects.requireNonNull(cacheManager.getCache(educationalModuleRepository.ALL_EDUCATIONALMODULE_CACHE)).clear();
    }
}
