package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.MahiatCourseService;
import com.marineindustryproj.domain.MahiatCourse;
import com.marineindustryproj.repository.MahiatCourseRepository;
import com.marineindustryproj.service.dto.MahiatCourseDTO;
import com.marineindustryproj.service.mapper.MahiatCourseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing MahiatCourse.
 */
@Service
@Transactional
public class MahiatCourseServiceImpl implements MahiatCourseService {

    private final Logger log = LoggerFactory.getLogger(MahiatCourseServiceImpl.class);

    private final MahiatCourseRepository mahiatCourseRepository;

    private final MahiatCourseMapper mahiatCourseMapper;

    public MahiatCourseServiceImpl(MahiatCourseRepository mahiatCourseRepository, MahiatCourseMapper mahiatCourseMapper) {
        this.mahiatCourseRepository = mahiatCourseRepository;
        this.mahiatCourseMapper = mahiatCourseMapper;
    }

    /**
     * Save a mahiatCourse.
     *
     * @param mahiatCourseDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public MahiatCourseDTO save(MahiatCourseDTO mahiatCourseDTO) {
        log.debug("Request to save MahiatCourse : {}", mahiatCourseDTO);

        MahiatCourse mahiatCourse = mahiatCourseMapper.toEntity(mahiatCourseDTO);
        mahiatCourse = mahiatCourseRepository.save(mahiatCourse);
        return mahiatCourseMapper.toDto(mahiatCourse);
    }

    /**
     * Get all the mahiatCourses.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MahiatCourseDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MahiatCourses");
        return mahiatCourseRepository.findAll(pageable)
            .map(mahiatCourseMapper::toDto);
    }


    /**
     * Get one mahiatCourse by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<MahiatCourseDTO> findOne(Long id) {
        log.debug("Request to get MahiatCourse : {}", id);
        return mahiatCourseRepository.findById(id)
            .map(mahiatCourseMapper::toDto);
    }

    /**
     * Delete the mahiatCourse by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MahiatCourse : {}", id);
        mahiatCourseRepository.deleteById(id);
    }
}
