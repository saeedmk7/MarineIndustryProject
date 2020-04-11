package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.NiazsanjiPersonGradeScoreService;
import com.marineindustryproj.domain.NiazsanjiPersonGradeScore;
import com.marineindustryproj.repository.NiazsanjiPersonGradeScoreRepository;
import com.marineindustryproj.service.dto.NiazsanjiPersonGradeScoreDTO;
import com.marineindustryproj.service.mapper.NiazsanjiPersonGradeScoreMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing NiazsanjiPersonGradeScore.
 */
@Service
@Transactional
public class NiazsanjiPersonGradeScoreServiceImpl implements NiazsanjiPersonGradeScoreService {

    private final Logger log = LoggerFactory.getLogger(NiazsanjiPersonGradeScoreServiceImpl.class);

    private final NiazsanjiPersonGradeScoreRepository niazsanjiPersonGradeScoreRepository;

    private final NiazsanjiPersonGradeScoreMapper niazsanjiPersonGradeScoreMapper;

    public NiazsanjiPersonGradeScoreServiceImpl(NiazsanjiPersonGradeScoreRepository niazsanjiPersonGradeScoreRepository, NiazsanjiPersonGradeScoreMapper niazsanjiPersonGradeScoreMapper) {
        this.niazsanjiPersonGradeScoreRepository = niazsanjiPersonGradeScoreRepository;
        this.niazsanjiPersonGradeScoreMapper = niazsanjiPersonGradeScoreMapper;
    }

    /**
     * Save a niazsanjiPersonGradeScore.
     *
     * @param niazsanjiPersonGradeScoreDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public NiazsanjiPersonGradeScoreDTO save(NiazsanjiPersonGradeScoreDTO niazsanjiPersonGradeScoreDTO) {
        log.debug("Request to save NiazsanjiPersonGradeScore : {}", niazsanjiPersonGradeScoreDTO);

        NiazsanjiPersonGradeScore niazsanjiPersonGradeScore = niazsanjiPersonGradeScoreMapper.toEntity(niazsanjiPersonGradeScoreDTO);
        niazsanjiPersonGradeScore = niazsanjiPersonGradeScoreRepository.save(niazsanjiPersonGradeScore);
        return niazsanjiPersonGradeScoreMapper.toDto(niazsanjiPersonGradeScore);
    }

    /**
     * Get all the niazsanjiPersonGradeScores.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<NiazsanjiPersonGradeScoreDTO> findAll(Pageable pageable) {
        log.debug("Request to get all NiazsanjiPersonGradeScores");
        return niazsanjiPersonGradeScoreRepository.findAll(pageable)
            .map(niazsanjiPersonGradeScoreMapper::toDto);
    }


    /**
     * Get one niazsanjiPersonGradeScore by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<NiazsanjiPersonGradeScoreDTO> findOne(Long id) {
        log.debug("Request to get NiazsanjiPersonGradeScore : {}", id);
        return niazsanjiPersonGradeScoreRepository.findById(id)
            .map(niazsanjiPersonGradeScoreMapper::toDto);
    }

    /**
     * Delete the niazsanjiPersonGradeScore by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete NiazsanjiPersonGradeScore : {}", id);
        niazsanjiPersonGradeScoreRepository.deleteById(id);
    }
}
