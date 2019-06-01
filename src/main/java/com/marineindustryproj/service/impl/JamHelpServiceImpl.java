package com.marineindustryproj.service.impl;

import com.marineindustryproj.domain.JamHelpAuthority;
import com.marineindustryproj.service.JamHelpAuthorityService;
import com.marineindustryproj.service.JamHelpService;
import com.marineindustryproj.domain.JamHelp;
import com.marineindustryproj.repository.JamHelpRepository;
import com.marineindustryproj.service.dto.JamHelpAuthorityDTO;
import com.marineindustryproj.service.dto.JamHelpDTO;
import com.marineindustryproj.service.mapper.JamHelpMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing JamHelp.
 */
@Service
@Transactional
public class JamHelpServiceImpl implements JamHelpService {

    private final Logger log = LoggerFactory.getLogger(JamHelpServiceImpl.class);

    private final JamHelpRepository jamHelpRepository;

    private final JamHelpMapper jamHelpMapper;

    private final JamHelpAuthorityService jamHelpAuthorityService;

    public JamHelpServiceImpl(JamHelpRepository jamHelpRepository,
                              JamHelpMapper jamHelpMapper,
                              JamHelpAuthorityService jamHelpAuthorityService) {
        this.jamHelpRepository = jamHelpRepository;
        this.jamHelpMapper = jamHelpMapper;
        this.jamHelpAuthorityService = jamHelpAuthorityService;
    }

    /**
     * Save a jamHelp.
     *
     * @param jamHelpDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public JamHelpDTO save(JamHelpDTO jamHelpDTO) {
        log.debug("Request to save JamHelp : {}", jamHelpDTO);

        /*if(!jamHelpDTO.getAuthorityNames().isEmpty())
        {
            String[] splitedValues = jamHelpDTO.getAuthorityNames().split(",");
            if(splitedValues.length > 0){
                //jamHelpRepository
                JamHelpAuthorityDTO jamHelpAuthority = new JamHelpAuthorityDTO();
            }
        }*/

        JamHelp jamHelp = jamHelpMapper.toEntity(jamHelpDTO);
        jamHelp = jamHelpRepository.save(jamHelp);
        return jamHelpMapper.toDto(jamHelp);
    }

    /**
     * Get all the jamHelps.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<JamHelpDTO> findAll(Pageable pageable) {
        log.debug("Request to get all JamHelps");
        return jamHelpRepository.findAll(pageable)
            .map(jamHelpMapper::toDto);
    }


    /**
     * Get one jamHelp by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<JamHelpDTO> findOne(Long id) {
        log.debug("Request to get JamHelp : {}", id);
        return jamHelpRepository.findById(id)
            .map(jamHelpMapper::toDto);
    }

    /**
     * Delete the jamHelp by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete JamHelp : {}", id);
        jamHelpRepository.deleteById(id);
    }
}
