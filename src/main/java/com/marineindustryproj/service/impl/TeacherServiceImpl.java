package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.TeacherService;
import com.marineindustryproj.domain.Teacher;
import com.marineindustryproj.repository.TeacherRepository;
import com.marineindustryproj.service.dto.TeacherDTO;
import com.marineindustryproj.service.dto.customs.TeacherMinDTO;
import com.marineindustryproj.service.mapper.TeacherMapper;
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

/**
 * Service Implementation for managing Teacher.
 */
@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {

    private final Logger log = LoggerFactory.getLogger(TeacherServiceImpl.class);

    private final TeacherRepository teacherRepository;

    private final TeacherMapper teacherMapper;

    private final CacheManager cacheManager;

    public TeacherServiceImpl(TeacherRepository teacherRepository,
                              TeacherMapper teacherMapper,
                              CacheManager cacheManager) {
        this.teacherRepository = teacherRepository;
        this.teacherMapper = teacherMapper;
        this.cacheManager = cacheManager;
    }

    /**
     * Save a teacher.
     *
     * @param teacherDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public TeacherDTO save(TeacherDTO teacherDTO) {
        log.debug("Request to save Teacher : {}", teacherDTO);

        Teacher teacher = teacherMapper.toEntity(teacherDTO);
        teacher = teacherRepository.save(teacher);
        clearTeacherCaches();
        return teacherMapper.toDto(teacher);
    }

    /**
     * Get all the teachers.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TeacherDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Teachers");
        return teacherRepository.findAll(pageable)
            .map(teacherMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TeacherMinDTO> findAllFromCache() {
        log.debug("Request to get all Teachers");
        return teacherRepository.findAllFromCache();
    }

    /**
     * Get all the Teacher with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<TeacherDTO> findAllWithEagerRelationships(Pageable pageable) {
        return teacherRepository.findAllWithEagerRelationships(pageable).map(teacherMapper::toDto);
    }
    

    /**
     * Get one teacher by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TeacherDTO> findOne(Long id) {
        log.debug("Request to get Teacher : {}", id);
        return teacherRepository.findOneWithEagerRelationships(id)
            .map(teacherMapper::toDto);
    }

    /**
     * Delete the teacher by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Teacher : {}", id);
        teacherRepository.deleteById(id);
        clearTeacherCaches();
    }
    private void clearTeacherCaches() {
        Objects.requireNonNull(cacheManager.getCache(teacherRepository.ALL_TEACHER_CACHE)).clear();
    }
}
