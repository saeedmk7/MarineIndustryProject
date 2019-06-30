package com.marineindustryproj.service.impl;

import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.BeautySpeechAuthorityService;
import com.marineindustryproj.service.BeautySpeechService;
import com.marineindustryproj.domain.BeautySpeech;
import com.marineindustryproj.repository.BeautySpeechRepository;
import com.marineindustryproj.service.dto.BeautySpeechAuthorityDTO;
import com.marineindustryproj.service.dto.BeautySpeechDTO;
import com.marineindustryproj.service.mapper.BeautySpeechMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.Optional;

/**
 * Service Implementation for managing BeautySpeech.
 */
@Service
@Transactional
public class BeautySpeechServiceImpl implements BeautySpeechService {

    private final Logger log = LoggerFactory.getLogger(BeautySpeechServiceImpl.class);

    private final BeautySpeechRepository beautySpeechRepository;

    private final BeautySpeechMapper beautySpeechMapper;
    
    private final BeautySpeechAuthorityService beautySpeechAuthorityService;

    public BeautySpeechServiceImpl(BeautySpeechRepository beautySpeechRepository, BeautySpeechMapper beautySpeechMapper, BeautySpeechAuthorityService beautySpeechAuthorityService) {
        this.beautySpeechRepository = beautySpeechRepository;
        this.beautySpeechMapper = beautySpeechMapper;
        this.beautySpeechAuthorityService = beautySpeechAuthorityService;
    }

    /**
     * Save a beautySpeech.
     *
     * @param beautySpeechDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public BeautySpeechDTO save(BeautySpeechDTO beautySpeechDTO) {
        log.debug("Request to save BeautySpeech : {}", beautySpeechDTO);
        boolean isEdit = false;
        if(beautySpeechDTO.getId() != null)
            isEdit = true;
        BeautySpeech beautySpeech = beautySpeechMapper.toEntity(beautySpeechDTO);
        beautySpeech = beautySpeechRepository.save(beautySpeech);

        if(!beautySpeechDTO.getAuthorityNames().isEmpty())
        {

            String[] splitedValues = beautySpeechDTO.getAuthorityNames().split(",");
            if(splitedValues.length > 0){
                if(isEdit)
                    beautySpeechAuthorityService.deleteByBeautySpeechId(beautySpeech.getId());
                //beautySpeechRepository
                for (String authority: splitedValues) {
                    BeautySpeechAuthorityDTO beautySpeechAuthority = new BeautySpeechAuthorityDTO();
                    beautySpeechAuthority.setAuthorityName(authority);
                    beautySpeechAuthority.setCreateDate(ZonedDateTime.now());
                    beautySpeechAuthority.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
                    beautySpeechAuthority.setHasEditPermission(true);
                    beautySpeechAuthority.setBeautySpeechId(beautySpeech.getId());
                    beautySpeechAuthorityService.save(beautySpeechAuthority);
                }
            }
        }
        
        return beautySpeechMapper.toDto(beautySpeech);
    }

    /**
     * Get all the beautySpeeches.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<BeautySpeechDTO> findAll(Pageable pageable) {
        log.debug("Request to get all BeautySpeeches");
        return beautySpeechRepository.findAll(pageable)
            .map(beautySpeechMapper::toDto);
    }


    /**
     * Get one beautySpeech by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<BeautySpeechDTO> findOne(Long id) {
        log.debug("Request to get BeautySpeech : {}", id);
        return beautySpeechRepository.findById(id)
            .map(beautySpeechMapper::toDto);
    }

    /**
     * Delete the beautySpeech by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete BeautySpeech : {}", id);
        beautySpeechRepository.deleteById(id);
    }
}
