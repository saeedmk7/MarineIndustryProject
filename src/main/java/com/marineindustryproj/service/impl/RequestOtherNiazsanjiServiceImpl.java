package com.marineindustryproj.service.impl;

import com.marineindustryproj.domain.Person;
import com.marineindustryproj.domain.PrioritizeRequestNiazsanji;
import com.marineindustryproj.domain.enumeration.RequestNiazsanjiType;
import com.marineindustryproj.domain.enumeration.RequestStatus;
import com.marineindustryproj.repository.NiazsanjiOtherRepository;
import com.marineindustryproj.repository.PersonRepository;
import com.marineindustryproj.repository.PrioritizeRequestNiazsanjiRepository;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.RequestOtherNiazsanjiService;
import com.marineindustryproj.domain.RequestOtherNiazsanji;
import com.marineindustryproj.repository.RequestOtherNiazsanjiRepository;
import com.marineindustryproj.service.dto.RequestOtherNiazsanjiDTO;
import com.marineindustryproj.service.mapper.RequestOtherNiazsanjiMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

/**
 * Service Implementation for managing RequestOtherNiazsanji.
 */
@Service
@Transactional
public class RequestOtherNiazsanjiServiceImpl implements RequestOtherNiazsanjiService {

    private final Logger log = LoggerFactory.getLogger(RequestOtherNiazsanjiServiceImpl.class);

    private final RequestOtherNiazsanjiRepository requestOtherNiazsanjiRepository;

    private final RequestOtherNiazsanjiMapper requestOtherNiazsanjiMapper;

    private final PersonRepository personRepository;

    private final NiazsanjiOtherRepository niazsanjiOtherRepository;

    private final PrioritizeRequestNiazsanjiRepository prioritizeRequestNiazsanjiRepository;

    public RequestOtherNiazsanjiServiceImpl(RequestOtherNiazsanjiRepository requestOtherNiazsanjiRepository, RequestOtherNiazsanjiMapper requestOtherNiazsanjiMapper, PersonRepository personRepository, NiazsanjiOtherRepository niazsanjiOtherRepository, PrioritizeRequestNiazsanjiRepository prioritizeRequestNiazsanjiRepository) {
        this.requestOtherNiazsanjiRepository = requestOtherNiazsanjiRepository;
        this.requestOtherNiazsanjiMapper = requestOtherNiazsanjiMapper;
        this.personRepository = personRepository;
        this.niazsanjiOtherRepository = niazsanjiOtherRepository;
        this.prioritizeRequestNiazsanjiRepository = prioritizeRequestNiazsanjiRepository;
    }

    /**
     * Save a requestOtherNiazsanji.
     *
     * @param requestOtherNiazsanjiDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public RequestOtherNiazsanjiDTO save(RequestOtherNiazsanjiDTO requestOtherNiazsanjiDTO) {
        log.debug("Request to save RequestOtherNiazsanji : {}", requestOtherNiazsanjiDTO);

        RequestOtherNiazsanji requestOtherNiazsanji = requestOtherNiazsanjiMapper.toEntity(requestOtherNiazsanjiDTO);
        requestOtherNiazsanji = requestOtherNiazsanjiRepository.save(requestOtherNiazsanji);
        return requestOtherNiazsanjiMapper.toDto(requestOtherNiazsanji);
    }

    /**
     * Finalize a requestOtherNiazsanji.
     *
     * @param requestOtherNiazsanjiDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public RequestOtherNiazsanjiDTO finalize(RequestOtherNiazsanjiDTO requestOtherNiazsanjiDTO) {
        log.debug("Request to save RequestOtherNiazsanji : {}", requestOtherNiazsanjiDTO);

        RequestOtherNiazsanji requestOtherNiazsanji = requestOtherNiazsanjiMapper.toEntity(requestOtherNiazsanjiDTO);
        Optional<Person> person = personRepository.findById(requestOtherNiazsanji.getPerson().getId());
        if (person.isPresent()) {
            requestOtherNiazsanji.setPerson(person.get());
        }

        PrioritizeRequestNiazsanji prioritizeRequestNiazsanji = new PrioritizeRequestNiazsanji();
        prioritizeRequestNiazsanji.setCreateDate(ZonedDateTime.now());
        prioritizeRequestNiazsanji.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
        prioritizeRequestNiazsanji.setModifyDate(ZonedDateTime.now());
        prioritizeRequestNiazsanji.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        prioritizeRequestNiazsanji.setDocuments(requestOtherNiazsanji.getDocuments());
        prioritizeRequestNiazsanji.setArchived(false);

        prioritizeRequestNiazsanji.setOrganizationChart(requestOtherNiazsanji.getOrganizationChart());
        prioritizeRequestNiazsanji.setPerson(requestOtherNiazsanji.getPerson());
        prioritizeRequestNiazsanji.setRequestOtherNiazsanji(requestOtherNiazsanji);
        prioritizeRequestNiazsanji.setDescription(requestOtherNiazsanji.getDescription());
        prioritizeRequestNiazsanji.setCode(requestOtherNiazsanji.getCode());
        prioritizeRequestNiazsanji.setStatus(0);

        prioritizeRequestNiazsanji.setEducationalModule(requestOtherNiazsanji.getEducationalModule());
        prioritizeRequestNiazsanji.setCostEducationalModule(requestOtherNiazsanji.getCostEducationalModule());
        prioritizeRequestNiazsanji.setCourseType(requestOtherNiazsanji.getCourseType());
        prioritizeRequestNiazsanji.setHasImportantMessage(requestOtherNiazsanji.isHasImportantMessage());
        prioritizeRequestNiazsanji.setGoalsText(requestOtherNiazsanji.getGoalsText());
        prioritizeRequestNiazsanji.setNiazsanjiInput(requestOtherNiazsanji.getNiazsanjiInput());
        prioritizeRequestNiazsanji.setPrerequisite(requestOtherNiazsanji.getPrerequisite());
        prioritizeRequestNiazsanji.setRestrictionDescription(requestOtherNiazsanji.getRestrictionDescription());
        prioritizeRequestNiazsanji.setRestrictions(requestOtherNiazsanji.getRestrictions());
        prioritizeRequestNiazsanji.setTeachingApproach(requestOtherNiazsanji.getTeachingApproach());

        prioritizeRequestNiazsanji.setGuid(UUID.randomUUID().toString());
        prioritizeRequestNiazsanji.setConversation(requestOtherNiazsanji.getConversation());
        prioritizeRequestNiazsanji.setNiazsanjiInput(requestOtherNiazsanji.getNiazsanjiInput());
        prioritizeRequestNiazsanji.setRequestOtherNiazsanji(requestOtherNiazsanji);
        prioritizeRequestNiazsanji.setRequestNiazsanjiType(RequestNiazsanjiType.OTHER);
        prioritizeRequestNiazsanji.setRequestStatus(RequestStatus.NEW);

        prioritizeRequestNiazsanjiRepository.save(prioritizeRequestNiazsanji);

        requestOtherNiazsanji = requestOtherNiazsanjiRepository.save(requestOtherNiazsanji);
        return requestOtherNiazsanjiMapper.toDto(requestOtherNiazsanji);
    }

    /**
     * Get all the requestOtherNiazsanjis.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RequestOtherNiazsanjiDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RequestOtherNiazsanjis");
        return requestOtherNiazsanjiRepository.findAll(pageable)
            .map(requestOtherNiazsanjiMapper::toDto);
    }

    /**
     * Get all the RequestOtherNiazsanji with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<RequestOtherNiazsanjiDTO> findAllWithEagerRelationships(Pageable pageable) {
        return requestOtherNiazsanjiRepository.findAllWithEagerRelationships(pageable).map(requestOtherNiazsanjiMapper::toDto);
    }


    /**
     * Get one requestOtherNiazsanji by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RequestOtherNiazsanjiDTO> findOne(Long id) {
        log.debug("Request to get RequestOtherNiazsanji : {}", id);
        return requestOtherNiazsanjiRepository.findOneWithEagerRelationships(id)
            .map(requestOtherNiazsanjiMapper::toDto);
    }

    /**
     * Delete the requestOtherNiazsanji by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RequestOtherNiazsanji : {}", id);
        requestOtherNiazsanjiRepository.deleteById(id);
    }
}
