package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.FixCompetencyDeficiencyService;
import com.marineindustryproj.domain.FixCompetencyDeficiency;
import com.marineindustryproj.repository.FixCompetencyDeficiencyRepository;
import com.marineindustryproj.service.dto.FixCompetencyDeficiencyDTO;
import com.marineindustryproj.service.mapper.FixCompetencyDeficiencyMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing FixCompetencyDeficiency.
 */
@Service
@Transactional
public class FixCompetencyDeficiencyServiceImpl implements FixCompetencyDeficiencyService {

    private final Logger log = LoggerFactory.getLogger(FixCompetencyDeficiencyServiceImpl.class);

    private final FixCompetencyDeficiencyRepository fixCompetencyDeficiencyRepository;

    private final FixCompetencyDeficiencyMapper fixCompetencyDeficiencyMapper;

    public FixCompetencyDeficiencyServiceImpl(FixCompetencyDeficiencyRepository fixCompetencyDeficiencyRepository, FixCompetencyDeficiencyMapper fixCompetencyDeficiencyMapper) {
        this.fixCompetencyDeficiencyRepository = fixCompetencyDeficiencyRepository;
        this.fixCompetencyDeficiencyMapper = fixCompetencyDeficiencyMapper;
    }

    /**
     * Save a fixCompetencyDeficiency.
     *
     * @param fixCompetencyDeficiencyDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public FixCompetencyDeficiencyDTO save(FixCompetencyDeficiencyDTO fixCompetencyDeficiencyDTO) {
        log.debug("Request to save FixCompetencyDeficiency : {}", fixCompetencyDeficiencyDTO);

        FixCompetencyDeficiency fixCompetencyDeficiency = fixCompetencyDeficiencyMapper.toEntity(fixCompetencyDeficiencyDTO);
        fixCompetencyDeficiency = fixCompetencyDeficiencyRepository.save(fixCompetencyDeficiency);
        return fixCompetencyDeficiencyMapper.toDto(fixCompetencyDeficiency);
    }

    /**
     * Get all the fixCompetencyDeficiencies.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<FixCompetencyDeficiencyDTO> findAll(Pageable pageable) {
        log.debug("Request to get all FixCompetencyDeficiencies");
        return fixCompetencyDeficiencyRepository.findAll(pageable)
            .map(fixCompetencyDeficiencyMapper::toDto);
    }


    /**
     * Get one fixCompetencyDeficiency by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<FixCompetencyDeficiencyDTO> findOne(Long id) {
        log.debug("Request to get FixCompetencyDeficiency : {}", id);
        return fixCompetencyDeficiencyRepository.findById(id)
            .map(fixCompetencyDeficiencyMapper::toDto);
    }

    /**
     * Delete the fixCompetencyDeficiency by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete FixCompetencyDeficiency : {}", id);
        fixCompetencyDeficiencyRepository.deleteById(id);
    }
}
