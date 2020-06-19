package com.marineindustryproj.service.impl;

import com.marineindustryproj.domain.PrioritizeRequestNiazsanji;
import com.marineindustryproj.domain.enumeration.RequestNiazsanjiType;
import com.marineindustryproj.domain.enumeration.RequestStatus;
import com.marineindustryproj.repository.PrioritizeRequestNiazsanjiRepository;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.*;
import com.marineindustryproj.domain.PreJobNiazsanji;
import com.marineindustryproj.repository.PreJobNiazsanjiRepository;
import com.marineindustryproj.service.dto.*;
import com.marineindustryproj.service.mapper.PreJobNiazsanjiCompetencyMapper;
import com.marineindustryproj.service.mapper.PreJobNiazsanjiMapper;
import com.marineindustryproj.service.mapper.PrioritizeRequestNiazsanjiMapper;
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
import java.util.UUID;

/**
 * Service Implementation for managing PreJobNiazsanji.
 */
@Service
@Transactional
public class PreJobNiazsanjiServiceImpl implements PreJobNiazsanjiService {

    private final Logger log = LoggerFactory.getLogger(PreJobNiazsanjiServiceImpl.class);

    private final PreJobNiazsanjiRepository preJobNiazsanjiRepository;

    private final PreJobNiazsanjiMapper preJobNiazsanjiMapper;

    private final DesignNiazsanjiQueryService designNiazsanjiQueryService;

    private final PrioritizeRequestNiazsanjiMapper prioritizeRequestNiazsanjiMapper;

    private final PrioritizeRequestNiazsanjiRepository prioritizeRequestNiazsanjiRepository;

    public PreJobNiazsanjiServiceImpl(PreJobNiazsanjiRepository preJobNiazsanjiRepository,
                                      PreJobNiazsanjiMapper preJobNiazsanjiMapper,
                                      DesignNiazsanjiQueryService designNiazsanjiQueryService,
                                      PrioritizeRequestNiazsanjiMapper prioritizeRequestNiazsanjiMapper,
                                      PrioritizeRequestNiazsanjiRepository prioritizeRequestNiazsanjiRepository) {
        this.preJobNiazsanjiRepository = preJobNiazsanjiRepository;
        this.preJobNiazsanjiMapper = preJobNiazsanjiMapper;
        this.designNiazsanjiQueryService = designNiazsanjiQueryService;
        this.prioritizeRequestNiazsanjiMapper = prioritizeRequestNiazsanjiMapper;
        this.prioritizeRequestNiazsanjiRepository = prioritizeRequestNiazsanjiRepository;
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
        if (designNiazsanjiDTOS.size() == 0) {
            throw new Exception("حتما حداقل باید یک دوره طراحی شده باشد برای نهایی کردن پیش نیازسنجی.");
        }
        for (DesignNiazsanjiDTO designNiazsanjiDTO : designNiazsanjiDTOS) {
            PrioritizeRequestNiazsanjiDTO prioritizeRequestNiazsanjiDTO = new PrioritizeRequestNiazsanjiDTO();
            prioritizeRequestNiazsanjiDTO.setDocuments(preJobNiazsanjiDTO.getDocuments());
            prioritizeRequestNiazsanjiDTO.setPreJobNiazsanjiId(preJobNiazsanjiDTO.getId());
            prioritizeRequestNiazsanjiDTO.setHasImportantMessage(false);
            prioritizeRequestNiazsanjiDTO.setCreateDate(ZonedDateTime.now());
            prioritizeRequestNiazsanjiDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
            prioritizeRequestNiazsanjiDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
            prioritizeRequestNiazsanjiDTO.setModifyDate(ZonedDateTime.now());
            prioritizeRequestNiazsanjiDTO.setArchived(false);

            prioritizeRequestNiazsanjiDTO.setOrganizationChartId(preJobNiazsanjiDTO.getOrganizationChartId());
            prioritizeRequestNiazsanjiDTO.setPersonId(preJobNiazsanjiDTO.getPersonId());
            prioritizeRequestNiazsanjiDTO.setDescription(preJobNiazsanjiDTO.getDescription());
            prioritizeRequestNiazsanjiDTO.setCode(preJobNiazsanjiDTO.getCode());
            prioritizeRequestNiazsanjiDTO.setStatus(0);

            prioritizeRequestNiazsanjiDTO.setEducationalModuleId(designNiazsanjiDTO.getEducationalModuleId());
            prioritizeRequestNiazsanjiDTO.setCostEducationalModule(designNiazsanjiDTO.getCostEducationalModule());
            prioritizeRequestNiazsanjiDTO.setCourseTypeId(designNiazsanjiDTO.getCourseTypeId());
            prioritizeRequestNiazsanjiDTO.setConversation(preJobNiazsanjiDTO.getConversation());
            prioritizeRequestNiazsanjiDTO.setGoalsText(designNiazsanjiDTO.getGoalsText());
            prioritizeRequestNiazsanjiDTO.setGuid(preJobNiazsanjiDTO.getGuid());
            prioritizeRequestNiazsanjiDTO.setPrerequisite(designNiazsanjiDTO.getPrerequisite());
            prioritizeRequestNiazsanjiDTO.setRestrictionDescription(designNiazsanjiDTO.getRestrictionDescription());
            prioritizeRequestNiazsanjiDTO.setRestrictions(designNiazsanjiDTO.getRestrictions());
            prioritizeRequestNiazsanjiDTO.setTeachingApproachId(designNiazsanjiDTO.getTeachingApproachId());
            prioritizeRequestNiazsanjiDTO.setRequestStatus(RequestStatus.NEW);

            prioritizeRequestNiazsanjiDTO.setGuid(UUID.randomUUID().toString());
            prioritizeRequestNiazsanjiDTO.setConversation(preJobNiazsanjiDTO.getConversation());
            prioritizeRequestNiazsanjiDTO.setPreJobNiazsanjiId(preJobNiazsanjiDTO.getId());
            prioritizeRequestNiazsanjiDTO.setRequestNiazsanjiType(RequestNiazsanjiType.JOB);

            PrioritizeRequestNiazsanji prioritizeRequestNiazsanji =
                prioritizeRequestNiazsanjiMapper.toEntity(prioritizeRequestNiazsanjiDTO);
            prioritizeRequestNiazsanjiRepository.save(prioritizeRequestNiazsanji);
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
