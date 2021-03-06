package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.CourseTypeService;
import com.marineindustryproj.domain.CourseType;
import com.marineindustryproj.repository.CourseTypeRepository;
import com.marineindustryproj.service.dto.CourseTypeDTO;
import com.marineindustryproj.service.mapper.CourseTypeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing CourseType.
 */
@Service
@Transactional
public class CourseTypeServiceImpl implements CourseTypeService {

    private final Logger log = LoggerFactory.getLogger(CourseTypeServiceImpl.class);

    private final CourseTypeRepository courseTypeRepository;

    private final CourseTypeMapper courseTypeMapper;

    private final CacheManager cacheManager;

    public CourseTypeServiceImpl(CourseTypeRepository courseTypeRepository, CourseTypeMapper courseTypeMapper, CacheManager cacheManager) {
        this.courseTypeRepository = courseTypeRepository;
        this.courseTypeMapper = courseTypeMapper;
        this.cacheManager = cacheManager;
    }

    /**
     * Save a courseType.
     *
     * @param courseTypeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public CourseTypeDTO save(CourseTypeDTO courseTypeDTO) {
        log.debug("Request to save CourseType : {}", courseTypeDTO);

        CourseType courseType = courseTypeMapper.toEntity(courseTypeDTO);
        courseType = courseTypeRepository.save(courseType);
        clearJobCaches();
        return courseTypeMapper.toDto(courseType);
    }

    /**
     * Get all the courseTypes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<CourseTypeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CourseTypes");
        return courseTypeRepository.findAll(pageable)
            .map(courseTypeMapper::toDto);
    }

    @Override
    public List<CourseTypeDTO> findAll() {
        log.debug("Request to get all CourseTypes");
        return courseTypeRepository.findAll().stream()
            .map(courseTypeMapper::toDto)
            .collect(Collectors.toList());
    }


    /**
     * Get one courseType by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<CourseTypeDTO> findOne(Long id) {
        log.debug("Request to get CourseType : {}", id);
        return courseTypeRepository.findById(id)
            .map(courseTypeMapper::toDto);
    }

    /**
     * Delete the courseType by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete CourseType : {}", id);
        courseTypeRepository.deleteById(id);
        clearJobCaches();
    }
    private void clearJobCaches() {
        Objects.requireNonNull(cacheManager.getCache(courseTypeRepository.ALL_COURSETYPE_CACHE)).clear();
    }
}
