package com.marineindustryproj.service.impl;

import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.PriorityCriteriaValueService;
import com.marineindustryproj.domain.PriorityCriteriaValue;
import com.marineindustryproj.repository.PriorityCriteriaValueRepository;
import com.marineindustryproj.service.dto.PriorityCriteriaValueDTO;
import com.marineindustryproj.service.mapper.PriorityCriteriaValueMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Optional;

/**
 * Service Implementation for managing PriorityCriteriaValue.
 */
@Service
@Transactional
public class PriorityCriteriaValueServiceImpl implements PriorityCriteriaValueService {

    private final Logger log = LoggerFactory.getLogger(PriorityCriteriaValueServiceImpl.class);

    private final PriorityCriteriaValueRepository priorityCriteriaValueRepository;

    private final PriorityCriteriaValueMapper priorityCriteriaValueMapper;

    public PriorityCriteriaValueServiceImpl(PriorityCriteriaValueRepository priorityCriteriaValueRepository, PriorityCriteriaValueMapper priorityCriteriaValueMapper) {
        this.priorityCriteriaValueRepository = priorityCriteriaValueRepository;
        this.priorityCriteriaValueMapper = priorityCriteriaValueMapper;
    }

    /**
     * Save a priorityCriteriaValue.
     *
     * @param priorityCriteriaValueDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public PriorityCriteriaValueDTO save(PriorityCriteriaValueDTO priorityCriteriaValueDTO) {
        log.debug("Request to save PriorityCriteriaValue : {}", priorityCriteriaValueDTO);

        PriorityCriteriaValue priorityCriteriaValue = priorityCriteriaValueMapper.toEntity(priorityCriteriaValueDTO);
        priorityCriteriaValue = priorityCriteriaValueRepository.save(priorityCriteriaValue);
        return priorityCriteriaValueMapper.toDto(priorityCriteriaValue);
    }

    @Override
    public PriorityCriteriaValueDTO saveBulk(PriorityCriteriaValueDTO[] priorityCriteriaValueDTOS) {
        PriorityCriteriaValue priorityCriteriaValue = new PriorityCriteriaValue();
        for (PriorityCriteriaValueDTO priorityCriteriaValueDTO : priorityCriteriaValueDTOS) {
            if(priorityCriteriaValueDTO.getScore() != null) {
                if (priorityCriteriaValueDTO.getId() != null && this.findOne(priorityCriteriaValueDTO.getId()).isPresent()) {
                    PriorityCriteriaValueDTO priorityCriteriaValueOld = this.findOne(priorityCriteriaValueDTO.getId()).get();
                    priorityCriteriaValueDTO.setCreateDate(priorityCriteriaValueOld.getCreateDate());
                    priorityCriteriaValueDTO.setCreateUserLogin(priorityCriteriaValueOld.getCreateUserLogin());
                    priorityCriteriaValueDTO.setModifyDate(ZonedDateTime.now());
                    priorityCriteriaValueDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
                } else {
                    priorityCriteriaValueDTO.setCreateDate(ZonedDateTime.now());
                    priorityCriteriaValueDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
                }
                priorityCriteriaValue = priorityCriteriaValueMapper.toEntity(priorityCriteriaValueDTO);
                priorityCriteriaValue = priorityCriteriaValueRepository.save(priorityCriteriaValue);
            }
        }


        return Arrays.stream(priorityCriteriaValueDTOS).filter(w -> w.getScore() != null).findFirst().get();
    }

    /**
     * Get all the priorityCriteriaValues.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PriorityCriteriaValueDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PriorityCriteriaValues");
        return priorityCriteriaValueRepository.findAll(pageable)
            .map(priorityCriteriaValueMapper::toDto);
    }


    /**
     * Get one priorityCriteriaValue by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PriorityCriteriaValueDTO> findOne(Long id) {
        log.debug("Request to get PriorityCriteriaValue : {}", id);
        return priorityCriteriaValueRepository.findById(id)
            .map(priorityCriteriaValueMapper::toDto);
    }

    /**
     * Delete the priorityCriteriaValue by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PriorityCriteriaValue : {}", id);
        priorityCriteriaValueRepository.deleteById(id);
    }
}
