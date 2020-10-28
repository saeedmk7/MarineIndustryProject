package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.EducationalCenterGroupService;
import com.marineindustryproj.domain.EducationalCenterGroup;
import com.marineindustryproj.repository.EducationalCenterGroupRepository;
import com.marineindustryproj.service.dto.EducationalCenterGroupDTO;
import com.marineindustryproj.service.mapper.EducationalCenterGroupMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing EducationalCenterGroup.
 */
@Service
@Transactional
public class EducationalCenterGroupServiceImpl implements EducationalCenterGroupService {

    private final Logger log = LoggerFactory.getLogger(EducationalCenterGroupServiceImpl.class);

    private final EducationalCenterGroupRepository educationalCenterGroupRepository;

    private final EducationalCenterGroupMapper educationalCenterGroupMapper;

    public EducationalCenterGroupServiceImpl(EducationalCenterGroupRepository educationalCenterGroupRepository, EducationalCenterGroupMapper educationalCenterGroupMapper) {
        this.educationalCenterGroupRepository = educationalCenterGroupRepository;
        this.educationalCenterGroupMapper = educationalCenterGroupMapper;
    }

    /**
     * Save a educationalCenterGroup.
     *
     * @param educationalCenterGroupDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public EducationalCenterGroupDTO save(EducationalCenterGroupDTO educationalCenterGroupDTO) {
        log.debug("Request to save EducationalCenterGroup : {}", educationalCenterGroupDTO);

        EducationalCenterGroup educationalCenterGroup = educationalCenterGroupMapper.toEntity(educationalCenterGroupDTO);
        educationalCenterGroup = educationalCenterGroupRepository.save(educationalCenterGroup);
        return educationalCenterGroupMapper.toDto(educationalCenterGroup);
    }

    /**
     * Get all the educationalCenterGroups.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EducationalCenterGroupDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EducationalCenterGroups");
        return educationalCenterGroupRepository.findAll(pageable)
            .map(educationalCenterGroupMapper::toDto);
    }


    /**
     * Get one educationalCenterGroup by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EducationalCenterGroupDTO> findOne(Long id) {
        log.debug("Request to get EducationalCenterGroup : {}", id);
        return educationalCenterGroupRepository.findById(id)
            .map(educationalCenterGroupMapper::toDto);
    }

    /**
     * Delete the educationalCenterGroup by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EducationalCenterGroup : {}", id);
        educationalCenterGroupRepository.deleteById(id);
    }
}
