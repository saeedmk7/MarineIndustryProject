package com.marineindustryproj.service.impl;

import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.TeacherGradeQueryService;
import com.marineindustryproj.service.TeacherGradeScoreService;
import com.marineindustryproj.service.TeacherGradeService;
import com.marineindustryproj.domain.TeacherGrade;
import com.marineindustryproj.repository.TeacherGradeRepository;
import com.marineindustryproj.service.TeacherService;
import com.marineindustryproj.service.dto.TeacherDTO;
import com.marineindustryproj.service.dto.TeacherGradeDTO;
import com.marineindustryproj.service.dto.TeacherGradeScoreDTO;
import com.marineindustryproj.service.mapper.TeacherGradeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.Optional;

/**
 * Service Implementation for managing TeacherGrade.
 */
@Service
@Transactional
public class TeacherGradeServiceImpl implements TeacherGradeService {

    private final Logger log = LoggerFactory.getLogger(TeacherGradeServiceImpl.class);

    private final TeacherGradeRepository teacherGradeRepository;

    private final TeacherGradeMapper teacherGradeMapper;

    private final TeacherGradeScoreService teacherGradeScoreService;

    private final TeacherGradeQueryService teacherGradeQueryService;

    private final TeacherService teacherService;

    public TeacherGradeServiceImpl(TeacherGradeRepository teacherGradeRepository, TeacherGradeMapper teacherGradeMapper, TeacherGradeScoreService teacherGradeScoreService, TeacherGradeQueryService teacherGradeQueryService, TeacherService teacherService) {
        this.teacherGradeRepository = teacherGradeRepository;
        this.teacherGradeMapper = teacherGradeMapper;
        this.teacherGradeScoreService = teacherGradeScoreService;
        this.teacherGradeQueryService = teacherGradeQueryService;
        this.teacherService = teacherService;
    }

    /**
     * Save a teacherGrade.
     *
     * @param teacherGradeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public TeacherGradeDTO save(TeacherGradeDTO teacherGradeDTO) {
        log.debug("Request to save TeacherGrade : {}", teacherGradeDTO);

        TeacherGrade teacherGrade = teacherGradeMapper.toEntity(teacherGradeDTO);

        teacherGrade = teacherGradeRepository.save(teacherGrade);

        return teacherGradeMapper.toDto(teacherGrade);
    }
    @Override
    public TeacherGradeDTO saveWithScore(TeacherGradeDTO teacherGradeDTO) {
        log.debug("Request to save TeacherGrade : {}", teacherGradeDTO);

        TeacherGrade teacherGrade = teacherGradeMapper.toEntity(teacherGradeDTO);

        if(teacherGradeQueryService.isThisBiggestDate(teacherGrade.getYear(), teacherGrade.getMonth(), teacherGrade.getTeacher().getId())) {

            TeacherDTO teacherDTO = teacherService.findOne(teacherGrade.getTeacher().getId()).get();
            teacherDTO.setGrade(teacherGrade.getGrade());
            teacherDTO.setModifyDate(ZonedDateTime.now());
            teacherDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
            teacherService.save(teacherDTO);
        }

        teacherGrade = teacherGradeRepository.save(teacherGrade);

        for (TeacherGradeScoreDTO teacherGradeScore : teacherGradeDTO.getTeacherGradeScores()) {
            teacherGradeScore.setTeacherGradeId(teacherGrade.getId());
            teacherGradeScore.setCreateDate(ZonedDateTime.now());
            teacherGradeScore.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
            teacherGradeScore.setModifyDate(ZonedDateTime.now());
            teacherGradeScore.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
            teacherGradeScoreService.save(teacherGradeScore);
        }

        return teacherGradeMapper.toDto(teacherGrade);
    }

    /**
     * Get all the teacherGrades.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TeacherGradeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TeacherGrades");
        return teacherGradeRepository.findAll(pageable)
            .map(teacherGradeMapper::toDto);
    }

    /**
     * Get all the TeacherGrade with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<TeacherGradeDTO> findAllWithEagerRelationships(Pageable pageable) {
        return teacherGradeRepository.findAllWithEagerRelationships(pageable).map(teacherGradeMapper::toDto);
    }
    

    /**
     * Get one teacherGrade by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TeacherGradeDTO> findOne(Long id) {
        log.debug("Request to get TeacherGrade : {}", id);
        return teacherGradeRepository.findOneWithEagerRelationships(id)
            .map(teacherGradeMapper::toDto);
    }

    /**
     * Delete the teacherGrade by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TeacherGrade : {}", id);
        teacherGradeRepository.deleteById(id);
    }
}
