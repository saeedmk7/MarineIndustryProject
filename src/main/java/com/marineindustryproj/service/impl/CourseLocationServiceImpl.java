package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.CourseLocationService;
import com.marineindustryproj.domain.CourseLocation;
import com.marineindustryproj.repository.CourseLocationRepository;
import com.marineindustryproj.service.dto.CourseLocationDTO;
import com.marineindustryproj.service.mapper.CourseLocationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing CourseLocation.
 */
@Service
@Transactional
public class CourseLocationServiceImpl implements CourseLocationService {

    private final Logger log = LoggerFactory.getLogger(CourseLocationServiceImpl.class);

    private final CourseLocationRepository courseLocationRepository;

    private final CourseLocationMapper courseLocationMapper;

    public CourseLocationServiceImpl(CourseLocationRepository courseLocationRepository, CourseLocationMapper courseLocationMapper) {
        this.courseLocationRepository = courseLocationRepository;
        this.courseLocationMapper = courseLocationMapper;
    }

    /**
     * Save a courseLocation.
     *
     * @param courseLocationDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CourseLocationDTO save(CourseLocationDTO courseLocationDTO) {
        log.debug("Request to save CourseLocation : {}", courseLocationDTO);

        CourseLocation courseLocation = courseLocationMapper.toEntity(courseLocationDTO);
        courseLocation = courseLocationRepository.save(courseLocation);
        return courseLocationMapper.toDto(courseLocation);
    }

    /**
     * Get all the courseLocations.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CourseLocationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CourseLocations");
        return courseLocationRepository.findAll(pageable)
            .map(courseLocationMapper::toDto);
    }


    /**
     * Get one courseLocation by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CourseLocationDTO> findOne(Long id) {
        log.debug("Request to get CourseLocation : {}", id);
        return courseLocationRepository.findById(id)
            .map(courseLocationMapper::toDto);
    }

    /**
     * Delete the courseLocation by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CourseLocation : {}", id);
        courseLocationRepository.deleteById(id);
    }
}
