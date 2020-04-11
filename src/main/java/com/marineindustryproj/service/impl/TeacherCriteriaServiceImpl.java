package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.TeacherCriteriaService;
import com.marineindustryproj.domain.TeacherCriteria;
import com.marineindustryproj.repository.TeacherCriteriaRepository;
import com.marineindustryproj.service.dto.TeacherCriteriaDTO;
import com.marineindustryproj.service.mapper.TeacherCriteriaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing TeacherCriteria.
 */
@Service
@Transactional
public class TeacherCriteriaServiceImpl implements TeacherCriteriaService {

    private final Logger log = LoggerFactory.getLogger(TeacherCriteriaServiceImpl.class);

    private final TeacherCriteriaRepository teacherCriteriaRepository;

    private final TeacherCriteriaMapper teacherCriteriaMapper;

    public TeacherCriteriaServiceImpl(TeacherCriteriaRepository teacherCriteriaRepository, TeacherCriteriaMapper teacherCriteriaMapper) {
        this.teacherCriteriaRepository = teacherCriteriaRepository;
        this.teacherCriteriaMapper = teacherCriteriaMapper;
    }

    /**
     * Save a teacherCriteria.
     *
     * @param teacherCriteriaDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public TeacherCriteriaDTO save(TeacherCriteriaDTO teacherCriteriaDTO) {
        log.debug("Request to save TeacherCriteria : {}", teacherCriteriaDTO);

        TeacherCriteria teacherCriteria = teacherCriteriaMapper.toEntity(teacherCriteriaDTO);
        teacherCriteria = teacherCriteriaRepository.save(teacherCriteria);
        return teacherCriteriaMapper.toDto(teacherCriteria);
    }

    /**
     * Get all the teacherCriteria.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TeacherCriteriaDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TeacherCriteria");
        return teacherCriteriaRepository.findAll(pageable)
            .map(teacherCriteriaMapper::toDto);
    }


    /**
     * Get one teacherCriteria by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TeacherCriteriaDTO> findOne(Long id) {
        log.debug("Request to get TeacherCriteria : {}", id);
        return teacherCriteriaRepository.findById(id)
            .map(teacherCriteriaMapper::toDto);
    }

    /**
     * Delete the teacherCriteria by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TeacherCriteria : {}", id);
        teacherCriteriaRepository.deleteById(id);
    }
}
