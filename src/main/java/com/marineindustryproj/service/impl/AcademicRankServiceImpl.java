package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.AcademicRankService;
import com.marineindustryproj.domain.AcademicRank;
import com.marineindustryproj.repository.AcademicRankRepository;
import com.marineindustryproj.service.dto.AcademicRankDTO;
import com.marineindustryproj.service.mapper.AcademicRankMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing AcademicRank.
 */
@Service
@Transactional
public class AcademicRankServiceImpl implements AcademicRankService {

    private final Logger log = LoggerFactory.getLogger(AcademicRankServiceImpl.class);

    private final AcademicRankRepository academicRankRepository;

    private final AcademicRankMapper academicRankMapper;

    public AcademicRankServiceImpl(AcademicRankRepository academicRankRepository, AcademicRankMapper academicRankMapper) {
        this.academicRankRepository = academicRankRepository;
        this.academicRankMapper = academicRankMapper;
    }

    /**
     * Save a academicRank.
     *
     * @param academicRankDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public AcademicRankDTO save(AcademicRankDTO academicRankDTO) {
        log.debug("Request to save AcademicRank : {}", academicRankDTO);

        AcademicRank academicRank = academicRankMapper.toEntity(academicRankDTO);
        academicRank = academicRankRepository.save(academicRank);
        return academicRankMapper.toDto(academicRank);
    }

    /**
     * Get all the academicRanks.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<AcademicRankDTO> findAll(Pageable pageable) {
        log.debug("Request to get all AcademicRanks");
        return academicRankRepository.findAll(pageable)
            .map(academicRankMapper::toDto);
    }


    /**
     * Get one academicRank by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<AcademicRankDTO> findOne(Long id) {
        log.debug("Request to get AcademicRank : {}", id);
        return academicRankRepository.findById(id)
            .map(academicRankMapper::toDto);
    }

    /**
     * Delete the academicRank by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete AcademicRank : {}", id);
        academicRankRepository.deleteById(id);
    }
}
