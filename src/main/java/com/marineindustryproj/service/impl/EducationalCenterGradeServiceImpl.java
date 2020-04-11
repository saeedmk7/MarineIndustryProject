package com.marineindustryproj.service.impl;

import com.marineindustryproj.domain.EducationalCenter;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.EducationalCenterGradeQueryService;
import com.marineindustryproj.service.EducationalCenterGradeScoreService;
import com.marineindustryproj.service.EducationalCenterGradeService;
import com.marineindustryproj.domain.EducationalCenterGrade;
import com.marineindustryproj.repository.EducationalCenterGradeRepository;
import com.marineindustryproj.service.EducationalCenterService;
import com.marineindustryproj.service.dto.EducationalCenterDTO;
import com.marineindustryproj.service.dto.EducationalCenterGradeDTO;
import com.marineindustryproj.service.dto.EducationalCenterGradeScoreDTO;
import com.marineindustryproj.service.mapper.EducationalCenterGradeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.Optional;

/**
 * Service Implementation for managing EducationalCenterGrade.
 */
@Service
@Transactional
public class EducationalCenterGradeServiceImpl implements EducationalCenterGradeService {

    private final Logger log = LoggerFactory.getLogger(EducationalCenterGradeServiceImpl.class);

    private final EducationalCenterGradeRepository educationalCenterGradeRepository;

    private final EducationalCenterGradeMapper educationalCenterGradeMapper;

    private final EducationalCenterGradeScoreService educationalCenterGradeScoreService;

    private final EducationalCenterGradeQueryService educationalCenterGradeQueryService;

    private final EducationalCenterService educationalCenterService;

    public EducationalCenterGradeServiceImpl(EducationalCenterGradeRepository educationalCenterGradeRepository, EducationalCenterGradeMapper educationalCenterGradeMapper, EducationalCenterGradeScoreService educationalCenterGradeScoreService, EducationalCenterGradeQueryService educationalCenterGradeQueryService, EducationalCenterService educationalCenterService) {
        this.educationalCenterGradeRepository = educationalCenterGradeRepository;
        this.educationalCenterGradeMapper = educationalCenterGradeMapper;
        this.educationalCenterGradeScoreService = educationalCenterGradeScoreService;
        this.educationalCenterGradeQueryService = educationalCenterGradeQueryService;
        this.educationalCenterService = educationalCenterService;
    }

    /**
     * Save a educationalCenterGrade.
     *
     * @param educationalCenterGradeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public EducationalCenterGradeDTO save(EducationalCenterGradeDTO educationalCenterGradeDTO) {
        log.debug("Request to save EducationalCenterGrade : {}", educationalCenterGradeDTO);

        EducationalCenterGrade educationalCenterGrade = educationalCenterGradeMapper.toEntity(educationalCenterGradeDTO);
        educationalCenterGrade = educationalCenterGradeRepository.save(educationalCenterGrade);

        return educationalCenterGradeMapper.toDto(educationalCenterGrade);
    }
    @Override
    public EducationalCenterGradeDTO saveWithScore(EducationalCenterGradeDTO educationalCenterGradeDTO) {
        log.debug("Request to save EducationalCenterGrade : {}", educationalCenterGradeDTO);

        EducationalCenterGrade educationalCenterGrade = educationalCenterGradeMapper.toEntity(educationalCenterGradeDTO);
        if(educationalCenterGradeQueryService.isThisBiggestDate(educationalCenterGrade.getYear(), educationalCenterGrade.getMonth(), educationalCenterGrade.getEducationalCenter().getId())) {

            EducationalCenterDTO educationalCenterDTO = educationalCenterService.findOne(educationalCenterGrade.getEducationalCenter().getId()).get();
            educationalCenterDTO.setGrade(educationalCenterGrade.getGrade());
            educationalCenterDTO.setModifyDate(ZonedDateTime.now());
            educationalCenterDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
            educationalCenterService.save(educationalCenterDTO);
        }
        educationalCenterGrade = educationalCenterGradeRepository.save(educationalCenterGrade);

        for (EducationalCenterGradeScoreDTO educationalCenterGradeScore : educationalCenterGradeDTO.getEducationalCenterGradeScores()) {
            educationalCenterGradeScore.setEducationalCenterGradeId(educationalCenterGrade.getId());
            educationalCenterGradeScore.setCreateDate(ZonedDateTime.now());
            educationalCenterGradeScore.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
            educationalCenterGradeScore.setModifyDate(ZonedDateTime.now());
            educationalCenterGradeScore.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
            educationalCenterGradeScoreService.save(educationalCenterGradeScore);
        }
        return educationalCenterGradeMapper.toDto(educationalCenterGrade);
    }

    /**
     * Get all the educationalCenterGrades.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EducationalCenterGradeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EducationalCenterGrades");
        return educationalCenterGradeRepository.findAll(pageable)
            .map(educationalCenterGradeMapper::toDto);
    }

    /**
     * Get all the EducationalCenterGrade with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<EducationalCenterGradeDTO> findAllWithEagerRelationships(Pageable pageable) {
        return educationalCenterGradeRepository.findAllWithEagerRelationships(pageable).map(educationalCenterGradeMapper::toDto);
    }
    

    /**
     * Get one educationalCenterGrade by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EducationalCenterGradeDTO> findOne(Long id) {
        log.debug("Request to get EducationalCenterGrade : {}", id);
        return educationalCenterGradeRepository.findOneWithEagerRelationships(id)
            .map(educationalCenterGradeMapper::toDto);
    }

    /**
     * Delete the educationalCenterGrade by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EducationalCenterGrade : {}", id);
        educationalCenterGradeRepository.deleteById(id);
    }
}
