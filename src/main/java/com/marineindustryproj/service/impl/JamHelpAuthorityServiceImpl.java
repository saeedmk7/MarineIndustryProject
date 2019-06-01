package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.JamHelpAuthorityQueryService;
import com.marineindustryproj.service.JamHelpAuthorityService;
import com.marineindustryproj.domain.JamHelpAuthority;
import com.marineindustryproj.repository.JamHelpAuthorityRepository;
import com.marineindustryproj.service.dto.JamHelpAuthorityCriteria;
import com.marineindustryproj.service.dto.JamHelpAuthorityDTO;
import com.marineindustryproj.service.mapper.JamHelpAuthorityMapper;
import io.github.jhipster.service.filter.LongFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing JamHelpAuthority.
 */
@Service
@Transactional
public class JamHelpAuthorityServiceImpl implements JamHelpAuthorityService {

    private final Logger log = LoggerFactory.getLogger(JamHelpAuthorityServiceImpl.class);

    private final JamHelpAuthorityRepository jamHelpAuthorityRepository;

    private final JamHelpAuthorityMapper jamHelpAuthorityMapper;

    private final JamHelpAuthorityQueryService jamHelpAuthorityQueryService;

    public JamHelpAuthorityServiceImpl(JamHelpAuthorityRepository jamHelpAuthorityRepository,
                                       JamHelpAuthorityMapper jamHelpAuthorityMapper,
                                       JamHelpAuthorityQueryService jamHelpAuthorityQueryService) {
        this.jamHelpAuthorityRepository = jamHelpAuthorityRepository;
        this.jamHelpAuthorityMapper = jamHelpAuthorityMapper;
        this.jamHelpAuthorityQueryService = jamHelpAuthorityQueryService;
    }

    /**
     * Save a jamHelpAuthority.
     *
     * @param jamHelpAuthorityDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public JamHelpAuthorityDTO save(JamHelpAuthorityDTO jamHelpAuthorityDTO) {
        log.debug("Request to save JamHelpAuthority : {}", jamHelpAuthorityDTO);

        JamHelpAuthority jamHelpAuthority = jamHelpAuthorityMapper.toEntity(jamHelpAuthorityDTO);
        jamHelpAuthority = jamHelpAuthorityRepository.save(jamHelpAuthority);
        return jamHelpAuthorityMapper.toDto(jamHelpAuthority);
    }

    /**
     * Get all the jamHelpAuthorities.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<JamHelpAuthorityDTO> findAll(Pageable pageable) {
        log.debug("Request to get all JamHelpAuthorities");
        return jamHelpAuthorityRepository.findAll(pageable)
            .map(jamHelpAuthorityMapper::toDto);
    }


    /**
     * Get one jamHelpAuthority by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<JamHelpAuthorityDTO> findOne(Long id) {
        log.debug("Request to get JamHelpAuthority : {}", id);
        return jamHelpAuthorityRepository.findById(id)
            .map(jamHelpAuthorityMapper::toDto);
    }

    /**
     * Delete the jamHelpAuthority by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete JamHelpAuthority : {}", id);
        jamHelpAuthorityRepository.deleteById(id);
    }

    /**
     * Delete the jamHelpAuthority by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void deleteByJamHelpId(Long id) {
        log.debug("Request to delete JamHelpAuthority : {}", id);

        JamHelpAuthorityCriteria jamHelpAuthorityCriteria = new JamHelpAuthorityCriteria();
        LongFilter longFilter = new LongFilter();
        longFilter.setEquals(id);
        jamHelpAuthorityCriteria.setJamHelpId(longFilter);
        List<JamHelpAuthorityDTO> jamHelpAuthorityList = jamHelpAuthorityQueryService.findByCriteria(jamHelpAuthorityCriteria);
        if(!jamHelpAuthorityList.isEmpty())
        {
            for (JamHelpAuthorityDTO jamHelpAuthority: jamHelpAuthorityList) {
                jamHelpAuthorityRepository.deleteById(jamHelpAuthority.getId());
            }
        }
    }
}
