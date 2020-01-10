package com.marineindustryproj.service.impl;

import com.marineindustryproj.domain.enumeration.EducationalModuleType;
import com.marineindustryproj.domain.enumeration.RequestStatus;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.*;
import com.marineindustryproj.domain.PreJobNiazsanji;
import com.marineindustryproj.repository.PreJobNiazsanjiRepository;
import com.marineindustryproj.service.dto.DesignNiazsanjiCriteria;
import com.marineindustryproj.service.dto.DesignNiazsanjiDTO;
import com.marineindustryproj.service.dto.JobNiazsanjiDTO;
import com.marineindustryproj.service.dto.PreJobNiazsanjiDTO;
import com.marineindustryproj.service.mapper.PreJobNiazsanjiCompetencyMapper;
import com.marineindustryproj.service.mapper.PreJobNiazsanjiMapper;
import io.github.jhipster.service.filter.LongFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing PreJobNiazsanji.
 */
@Service
@Transactional
public class PreJobNiazsanjiServiceImpl implements PreJobNiazsanjiService {

    private final Logger log = LoggerFactory.getLogger(PreJobNiazsanjiServiceImpl.class);

    private final PreJobNiazsanjiRepository preJobNiazsanjiRepository;

    private final PreJobNiazsanjiMapper preJobNiazsanjiMapper;

    private final DesignNiazsanjiServiceImpl designNiazsanjiService;
    private final DesignNiazsanjiQueryService designNiazsanjiQueryService;

    private final NiazsanjiFardiService niazsanjiFardiService;

    private final JobNiazsanjiService jobNiazsanjiService;

    public PreJobNiazsanjiServiceImpl(PreJobNiazsanjiRepository preJobNiazsanjiRepository, PreJobNiazsanjiCompetencyService preJobNiazsanjiCompetencyService, PreJobNiazsanjiCompetencyMapper preJobNiazsanjiCompetencyMapper, PreJobNiazsanjiMapper preJobNiazsanjiMapper, DesignNiazsanjiServiceImpl designNiazsanjiService, DesignNiazsanjiQueryService designNiazsanjiQueryService, NiazsanjiFardiService niazsanjiFardiService, JobNiazsanjiService jobNiazsanjiService) {
        this.preJobNiazsanjiRepository = preJobNiazsanjiRepository;
        this.preJobNiazsanjiMapper = preJobNiazsanjiMapper;
        this.designNiazsanjiService = designNiazsanjiService;
        this.designNiazsanjiQueryService = designNiazsanjiQueryService;
        this.niazsanjiFardiService = niazsanjiFardiService;
        this.jobNiazsanjiService = jobNiazsanjiService;
    }

    /**
     * Save a preJobNiazsanji.
     *
     * @param preJobNiazsanjiDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public PreJobNiazsanjiDTO save(PreJobNiazsanjiDTO preJobNiazsanjiDTO) {
        log.debug("Request to save PreJobNiazsanji : {}", preJobNiazsanjiDTO);

        PreJobNiazsanji preJobNiazsanji = preJobNiazsanjiMapper.toEntity(preJobNiazsanjiDTO);
        preJobNiazsanji = preJobNiazsanjiRepository.save(preJobNiazsanji);
        return preJobNiazsanjiMapper.toDto(preJobNiazsanji);
    }
    @Override
    public PreJobNiazsanjiDTO finalize(PreJobNiazsanjiDTO preJobNiazsanjiDTO) throws Exception {
        log.debug("Request to save PreJobNiazsanji : {}", preJobNiazsanjiDTO);

        DesignNiazsanjiCriteria designNiazsanjiCriteria = new DesignNiazsanjiCriteria();
        LongFilter longFilter = new LongFilter();
        longFilter.setEquals(preJobNiazsanjiDTO.getId());
        designNiazsanjiCriteria.setPreJobNiazsanjiId(longFilter);

        List<DesignNiazsanjiDTO> designNiazsanjiDTOS = designNiazsanjiQueryService.findByCriteria(designNiazsanjiCriteria);
        if(designNiazsanjiDTOS.size() == 0){
            throw new Exception("حتما حداقل باید یک دوره طراحی شده باشد برای نهایی کردن پیش نیازسنجی.");
        }
        for (DesignNiazsanjiDTO designNiazsanjiDTO : designNiazsanjiDTOS) {
            JobNiazsanjiDTO jobNiazsanjiDTO = new JobNiazsanjiDTO();
            jobNiazsanjiDTO.setDocuments(preJobNiazsanjiDTO.getDocuments());
            jobNiazsanjiDTO.setPreJobNiazsanjiId(preJobNiazsanjiDTO.getId());
            jobNiazsanjiDTO.setHasImportantMessage(false);
            jobNiazsanjiDTO.setCreateDate(ZonedDateTime.now());
            jobNiazsanjiDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
            jobNiazsanjiDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
            jobNiazsanjiDTO.setModifyDate(ZonedDateTime.now());
            jobNiazsanjiDTO.setArchived(false);

            jobNiazsanjiDTO.setOrganizationChartId(preJobNiazsanjiDTO.getOrganizationChartId());
            jobNiazsanjiDTO.setPersonId(preJobNiazsanjiDTO.getPersonId());
            jobNiazsanjiDTO.setDescription(preJobNiazsanjiDTO.getDescription());
            jobNiazsanjiDTO.setCode(preJobNiazsanjiDTO.getCode());
            jobNiazsanjiDTO.setStatus(0);

            jobNiazsanjiDTO.setEducationalModuleId(designNiazsanjiDTO.getEducationalModuleId());
            jobNiazsanjiDTO.setPriceCost(designNiazsanjiDTO.getCostEducationalModule());
            jobNiazsanjiDTO.setCourseTypeId(designNiazsanjiDTO.getCourseTypeId());
            jobNiazsanjiDTO.setConversation(preJobNiazsanjiDTO.getConversation());
            jobNiazsanjiDTO.setGoalsText(designNiazsanjiDTO.getGoalsText());
            jobNiazsanjiDTO.setGuid(preJobNiazsanjiDTO.getGuid());
            jobNiazsanjiDTO.setPrerequisite(designNiazsanjiDTO.getPrerequisite());
            jobNiazsanjiDTO.setRestrictionDescription(designNiazsanjiDTO.getRestrictionDescription());
            jobNiazsanjiDTO.setRestrictions(designNiazsanjiDTO.getRestrictions());
            jobNiazsanjiDTO.setTeachingApproachId(designNiazsanjiDTO.getTeachingApproachId());

            jobNiazsanjiService.save(jobNiazsanjiDTO);
        }
        preJobNiazsanjiDTO.setRequestStatus(RequestStatus.NEW);
        preJobNiazsanjiDTO.setStatus(0);
        preJobNiazsanjiDTO.setChangeStatusUserLogin(SecurityUtils.getCurrentUserLogin().get());
        PreJobNiazsanji preJobNiazsanji = preJobNiazsanjiMapper.toEntity(preJobNiazsanjiDTO);
        preJobNiazsanji = preJobNiazsanjiRepository.save(preJobNiazsanji);
        return preJobNiazsanjiMapper.toDto(preJobNiazsanji);
    }

    /**
     * Get all the preJobNiazsanjis.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PreJobNiazsanjiDTO> findAll(Pageable pageable) {
        log.debug("Request to get all PreJobNiazsanjis");
        return preJobNiazsanjiRepository.findAll(pageable)
            .map(preJobNiazsanjiMapper::toDto);
    }

    /**
     * Get all the PreJobNiazsanji with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<PreJobNiazsanjiDTO> findAllWithEagerRelationships(Pageable pageable) {
        return preJobNiazsanjiRepository.findAllWithEagerRelationships(pageable).map(preJobNiazsanjiMapper::toDto);
    }
    

    /**
     * Get one preJobNiazsanji by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PreJobNiazsanjiDTO> findOne(Long id) {
        log.debug("Request to get PreJobNiazsanji : {}", id);
        return preJobNiazsanjiRepository.findOneWithEagerRelationships(id)
            .map(preJobNiazsanjiMapper::toDto);
    }

    /**
     * Delete the preJobNiazsanji by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PreJobNiazsanji : {}", id);
        preJobNiazsanjiRepository.deleteById(id);
    }
}
