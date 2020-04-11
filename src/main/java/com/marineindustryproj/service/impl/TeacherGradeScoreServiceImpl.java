package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.TeacherGradeScoreService;
import com.marineindustryproj.domain.TeacherGradeScore;
import com.marineindustryproj.repository.TeacherGradeScoreRepository;
import com.marineindustryproj.service.dto.TeacherGradeScoreDTO;
import com.marineindustryproj.service.mapper.TeacherGradeScoreMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing TeacherGradeScore.
 */
@Service
@Transactional
public class TeacherGradeScoreServiceImpl implements TeacherGradeScoreService {

    private final Logger log = LoggerFactory.getLogger(TeacherGradeScoreServiceImpl.class);

    private final TeacherGradeScoreRepository teacherGradeScoreRepository;

    private final TeacherGradeScoreMapper teacherGradeScoreMapper;

    public TeacherGradeScoreServiceImpl(TeacherGradeScoreRepository teacherGradeScoreRepository, TeacherGradeScoreMapper teacherGradeScoreMapper) {
        this.teacherGradeScoreRepository = teacherGradeScoreRepository;
        this.teacherGradeScoreMapper = teacherGradeScoreMapper;
    }

    /**
     * Save a teacherGradeScore.
     *
     * @param teacherGradeScoreDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public TeacherGradeScoreDTO save(TeacherGradeScoreDTO teacherGradeScoreDTO) {
        log.debug("Request to save TeacherGradeScore : {}", teacherGradeScoreDTO);

        TeacherGradeScore teacherGradeScore = teacherGradeScoreMapper.toEntity(teacherGradeScoreDTO);
        teacherGradeScore = teacherGradeScoreRepository.save(teacherGradeScore);
        return teacherGradeScoreMapper.toDto(teacherGradeScore);
    }

    /**
     * Get all the teacherGradeScores.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TeacherGradeScoreDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TeacherGradeScores");
        return teacherGradeScoreRepository.findAll(pageable)
            .map(teacherGradeScoreMapper::toDto);
    }


    /**
     * Get one teacherGradeScore by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TeacherGradeScoreDTO> findOne(Long id) {
        log.debug("Request to get TeacherGradeScore : {}", id);
        return teacherGradeScoreRepository.findById(id)
            .map(teacherGradeScoreMapper::toDto);
    }

    /**
     * Delete the teacherGradeScore by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TeacherGradeScore : {}", id);
        teacherGradeScoreRepository.deleteById(id);
    }
}
