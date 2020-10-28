package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.TeacherCriteriaGroupService;
import com.marineindustryproj.domain.TeacherCriteriaGroup;
import com.marineindustryproj.repository.TeacherCriteriaGroupRepository;
import com.marineindustryproj.service.dto.TeacherCriteriaGroupDTO;
import com.marineindustryproj.service.mapper.TeacherCriteriaGroupMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing TeacherCriteriaGroup.
 */
@Service
@Transactional
public class TeacherCriteriaGroupServiceImpl implements TeacherCriteriaGroupService {

    private final Logger log = LoggerFactory.getLogger(TeacherCriteriaGroupServiceImpl.class);

    private final TeacherCriteriaGroupRepository teacherCriteriaGroupRepository;

    private final TeacherCriteriaGroupMapper teacherCriteriaGroupMapper;

    public TeacherCriteriaGroupServiceImpl(TeacherCriteriaGroupRepository teacherCriteriaGroupRepository, TeacherCriteriaGroupMapper teacherCriteriaGroupMapper) {
        this.teacherCriteriaGroupRepository = teacherCriteriaGroupRepository;
        this.teacherCriteriaGroupMapper = teacherCriteriaGroupMapper;
    }

    /**
     * Save a teacherCriteriaGroup.
     *
     * @param teacherCriteriaGroupDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public TeacherCriteriaGroupDTO save(TeacherCriteriaGroupDTO teacherCriteriaGroupDTO) {
        log.debug("Request to save TeacherCriteriaGroup : {}", teacherCriteriaGroupDTO);

        TeacherCriteriaGroup teacherCriteriaGroup = teacherCriteriaGroupMapper.toEntity(teacherCriteriaGroupDTO);
        teacherCriteriaGroup = teacherCriteriaGroupRepository.save(teacherCriteriaGroup);
        return teacherCriteriaGroupMapper.toDto(teacherCriteriaGroup);
    }

    /**
     * Get all the teacherCriteriaGroups.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TeacherCriteriaGroupDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TeacherCriteriaGroups");
        return teacherCriteriaGroupRepository.findAll(pageable)
            .map(teacherCriteriaGroupMapper::toDto);
    }


    /**
     * Get one teacherCriteriaGroup by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TeacherCriteriaGroupDTO> findOne(Long id) {
        log.debug("Request to get TeacherCriteriaGroup : {}", id);
        return teacherCriteriaGroupRepository.findById(id)
            .map(teacherCriteriaGroupMapper::toDto);
    }

    /**
     * Delete the teacherCriteriaGroup by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TeacherCriteriaGroup : {}", id);
        teacherCriteriaGroupRepository.deleteById(id);
    }
}
