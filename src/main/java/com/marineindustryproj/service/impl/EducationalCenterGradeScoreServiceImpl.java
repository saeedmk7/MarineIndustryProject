package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.EducationalCenterGradeScoreService;
import com.marineindustryproj.domain.EducationalCenterGradeScore;
import com.marineindustryproj.repository.EducationalCenterGradeScoreRepository;
import com.marineindustryproj.service.dto.EducationalCenterGradeScoreDTO;
import com.marineindustryproj.service.mapper.EducationalCenterGradeScoreMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing EducationalCenterGradeScore.
 */
@Service
@Transactional
public class EducationalCenterGradeScoreServiceImpl implements EducationalCenterGradeScoreService {

    private final Logger log = LoggerFactory.getLogger(EducationalCenterGradeScoreServiceImpl.class);

    private final EducationalCenterGradeScoreRepository educationalCenterGradeScoreRepository;

    private final EducationalCenterGradeScoreMapper educationalCenterGradeScoreMapper;

    public EducationalCenterGradeScoreServiceImpl(EducationalCenterGradeScoreRepository educationalCenterGradeScoreRepository, EducationalCenterGradeScoreMapper educationalCenterGradeScoreMapper) {
        this.educationalCenterGradeScoreRepository = educationalCenterGradeScoreRepository;
        this.educationalCenterGradeScoreMapper = educationalCenterGradeScoreMapper;
    }

    /**
     * Save a educationalCenterGradeScore.
     *
     * @param educationalCenterGradeScoreDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public EducationalCenterGradeScoreDTO save(EducationalCenterGradeScoreDTO educationalCenterGradeScoreDTO) {
        log.debug("Request to save EducationalCenterGradeScore : {}", educationalCenterGradeScoreDTO);

        EducationalCenterGradeScore educationalCenterGradeScore = educationalCenterGradeScoreMapper.toEntity(educationalCenterGradeScoreDTO);
        educationalCenterGradeScore = educationalCenterGradeScoreRepository.save(educationalCenterGradeScore);
        return educationalCenterGradeScoreMapper.toDto(educationalCenterGradeScore);
    }

    /**
     * Get all the educationalCenterGradeScores.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EducationalCenterGradeScoreDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EducationalCenterGradeScores");
        return educationalCenterGradeScoreRepository.findAll(pageable)
            .map(educationalCenterGradeScoreMapper::toDto);
    }


    /**
     * Get one educationalCenterGradeScore by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EducationalCenterGradeScoreDTO> findOne(Long id) {
        log.debug("Request to get EducationalCenterGradeScore : {}", id);
        return educationalCenterGradeScoreRepository.findById(id)
            .map(educationalCenterGradeScoreMapper::toDto);
    }

    /**
     * Delete the educationalCenterGradeScore by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EducationalCenterGradeScore : {}", id);
        educationalCenterGradeScoreRepository.deleteById(id);
    }
}
