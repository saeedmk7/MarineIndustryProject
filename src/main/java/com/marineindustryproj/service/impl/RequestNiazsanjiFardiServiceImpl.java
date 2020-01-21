package com.marineindustryproj.service.impl;

import com.marineindustryproj.domain.NiazsanjiFardi;
import com.marineindustryproj.domain.Person;
import com.marineindustryproj.domain.PrioritizeRequestNiazsanji;
import com.marineindustryproj.domain.enumeration.EducationalModuleType;
import com.marineindustryproj.domain.enumeration.RequestNiazsanjiType;
import com.marineindustryproj.domain.enumeration.RequestStatus;
import com.marineindustryproj.repository.NiazsanjiFardiRepository;
import com.marineindustryproj.repository.PersonRepository;
import com.marineindustryproj.repository.PrioritizeRequestNiazsanjiRepository;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.RequestNiazsanjiFardiService;
import com.marineindustryproj.domain.RequestNiazsanjiFardi;
import com.marineindustryproj.repository.RequestNiazsanjiFardiRepository;
import com.marineindustryproj.service.dto.RequestNiazsanjiFardiDTO;
import com.marineindustryproj.service.mapper.RequestNiazsanjiFardiMapper;
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
 * Service Implementation for managing RequestNiazsanjiFardi.
 */
@Service
@Transactional
public class RequestNiazsanjiFardiServiceImpl implements RequestNiazsanjiFardiService {

    private final Logger log = LoggerFactory.getLogger(RequestNiazsanjiFardiServiceImpl.class);

    private final RequestNiazsanjiFardiRepository requestNiazsanjiFardiRepository;

    private final NiazsanjiFardiRepository niazsanjiFardiRepository;

    private final PrioritizeRequestNiazsanjiRepository prioritizeRequestNiazsanjiRepository;

    private final PersonRepository personRepository;

    private final RequestNiazsanjiFardiMapper requestNiazsanjiFardiMapper;

    public RequestNiazsanjiFardiServiceImpl(RequestNiazsanjiFardiRepository requestNiazsanjiFardiRepository,
                                            NiazsanjiFardiRepository niazsanjiFardiRepository,
                                            PrioritizeRequestNiazsanjiRepository prioritizeRequestNiazsanjiRepository, PersonRepository personRepository,
                                            RequestNiazsanjiFardiMapper requestNiazsanjiFardiMapper) {
        this.requestNiazsanjiFardiRepository = requestNiazsanjiFardiRepository;
        this.niazsanjiFardiRepository = niazsanjiFardiRepository;
        this.prioritizeRequestNiazsanjiRepository = prioritizeRequestNiazsanjiRepository;
        this.personRepository = personRepository;
        this.requestNiazsanjiFardiMapper = requestNiazsanjiFardiMapper;
    }

    /**
     * Save a requestNiazsanjiFardi.
     *
     * @param requestNiazsanjiFardiDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public RequestNiazsanjiFardiDTO save(RequestNiazsanjiFardiDTO requestNiazsanjiFardiDTO) {
        log.debug("Request to save RequestNiazsanjiFardi : {}", requestNiazsanjiFardiDTO);

        RequestNiazsanjiFardi requestNiazsanjiFardi = requestNiazsanjiFardiMapper.toEntity(requestNiazsanjiFardiDTO);
        requestNiazsanjiFardi = requestNiazsanjiFardiRepository.save(requestNiazsanjiFardi);
        return requestNiazsanjiFardiMapper.toDto(requestNiazsanjiFardi);
    }

    /**
     * Finalize a requestNiazsanjiFardi.
     *
     * @param requestNiazsanjiFardiDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public RequestNiazsanjiFardiDTO finalize(RequestNiazsanjiFardiDTO requestNiazsanjiFardiDTO) {
        log.debug("Request to save RequestNiazsanjiFardi : {}", requestNiazsanjiFardiDTO);

        RequestNiazsanjiFardi requestNiazsanjiFardi = requestNiazsanjiFardiMapper.toEntity(requestNiazsanjiFardiDTO);
        Optional<Person> person = personRepository.findById(requestNiazsanjiFardi.getPerson().getId());
        if(person.isPresent()){
            requestNiazsanjiFardi.setPerson(person.get());
        }

        if(requestNiazsanjiFardi.getAllEducationalModule() != null)
        {
            PrioritizeRequestNiazsanji prioritizeRequestNiazsanji = new PrioritizeRequestNiazsanji();
            prioritizeRequestNiazsanji.setCreateDate(ZonedDateTime.now());
            prioritizeRequestNiazsanji.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
            prioritizeRequestNiazsanji.setModifyDate(ZonedDateTime.now());
            prioritizeRequestNiazsanji.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
            prioritizeRequestNiazsanji.setDocuments(requestNiazsanjiFardi.getDocuments());
            prioritizeRequestNiazsanji.setArchived(false);

            prioritizeRequestNiazsanji.setOrganizationChart(requestNiazsanjiFardi.getOrganizationChart());
            prioritizeRequestNiazsanji.setPerson(requestNiazsanjiFardi.getPerson());
            prioritizeRequestNiazsanji.setRequestNiazsanjiFardi(requestNiazsanjiFardi);
            prioritizeRequestNiazsanji.setDescription(requestNiazsanjiFardi.getDescription());
            prioritizeRequestNiazsanji.setCode(requestNiazsanjiFardi.getCode());
            prioritizeRequestNiazsanji.setStatus(0);

            prioritizeRequestNiazsanji.setEducationalModuleType(EducationalModuleType.ALL);
            prioritizeRequestNiazsanji.setEducationalModule(requestNiazsanjiFardi.getAllEducationalModule());
            prioritizeRequestNiazsanji.setCostEducationalModule(requestNiazsanjiFardi.getCostAllEducationalModule());
            prioritizeRequestNiazsanji.setCourseType(requestNiazsanjiFardi.getCourseType());
            prioritizeRequestNiazsanji.setHasImportantMessage(requestNiazsanjiFardi.isHasImportantMessage());
            prioritizeRequestNiazsanji.setRequestNiazsanjiFardi(requestNiazsanjiFardi);
            prioritizeRequestNiazsanji.setConversation(requestNiazsanjiFardi.getConversation());
            prioritizeRequestNiazsanji.setGuid(UUID.randomUUID().toString());
            prioritizeRequestNiazsanji.setRequestNiazsanjiType(RequestNiazsanjiType.FARDI);
            prioritizeRequestNiazsanji.setRequestStatus(RequestStatus.NEW);

            prioritizeRequestNiazsanjiRepository.save(prioritizeRequestNiazsanji);
        }
        if(requestNiazsanjiFardi.getApprovedEducationalModule() != null)
        {
            PrioritizeRequestNiazsanji prioritizeRequestNiazsanji = new PrioritizeRequestNiazsanji();
            prioritizeRequestNiazsanji.setCreateDate(ZonedDateTime.now());
            prioritizeRequestNiazsanji.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
            prioritizeRequestNiazsanji.setModifyDate(ZonedDateTime.now());
            prioritizeRequestNiazsanji.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
            prioritizeRequestNiazsanji.setDocuments(requestNiazsanjiFardi.getDocuments());
            prioritizeRequestNiazsanji.setArchived(false);

            prioritizeRequestNiazsanji.setOrganizationChart(requestNiazsanjiFardi.getOrganizationChart());
            prioritizeRequestNiazsanji.setPerson(requestNiazsanjiFardi.getPerson());
            prioritizeRequestNiazsanji.setRequestNiazsanjiFardi(requestNiazsanjiFardi);
            prioritizeRequestNiazsanji.setDescription(requestNiazsanjiFardi.getDescription());
            prioritizeRequestNiazsanji.setCode(requestNiazsanjiFardi.getCode());
            prioritizeRequestNiazsanji.setStatus(0);

            prioritizeRequestNiazsanji.setEducationalModuleType(EducationalModuleType.APPROVED);
            prioritizeRequestNiazsanji.setEducationalModule(requestNiazsanjiFardi.getApprovedEducationalModule());
            prioritizeRequestNiazsanji.setCostEducationalModule(requestNiazsanjiFardi.getCostApprovedEducationalModule());
            prioritizeRequestNiazsanji.setCourseType(requestNiazsanjiFardi.getCourseType());
            prioritizeRequestNiazsanji.setHasImportantMessage(requestNiazsanjiFardi.isHasImportantMessage());
            prioritizeRequestNiazsanji.setRequestNiazsanjiFardi(requestNiazsanjiFardi);
            prioritizeRequestNiazsanji.setConversation(requestNiazsanjiFardi.getConversation());
            prioritizeRequestNiazsanji.setGuid(UUID.randomUUID().toString());
            prioritizeRequestNiazsanji.setRequestNiazsanjiType(RequestNiazsanjiType.FARDI);

            prioritizeRequestNiazsanji.setRequestStatus(RequestStatus.NEW);

            prioritizeRequestNiazsanjiRepository.save(prioritizeRequestNiazsanji);
        }

        requestNiazsanjiFardi = requestNiazsanjiFardiRepository.save(requestNiazsanjiFardi);
         return requestNiazsanjiFardiMapper.toDto(requestNiazsanjiFardi);
    }

    /**
     * Get all the requestNiazsanjiFardis.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<RequestNiazsanjiFardiDTO> findAll(Pageable pageable) {
        log.debug("Request to get all RequestNiazsanjiFardis");
        return requestNiazsanjiFardiRepository.findAll(pageable)
            .map(requestNiazsanjiFardiMapper::toDto);
    }

    /**
     * Get all the RequestNiazsanjiFardi with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<RequestNiazsanjiFardiDTO> findAllWithEagerRelationships(Pageable pageable) {
        return requestNiazsanjiFardiRepository.findAllWithEagerRelationships(pageable).map(requestNiazsanjiFardiMapper::toDto);
    }
    

    /**
     * Get one requestNiazsanjiFardi by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<RequestNiazsanjiFardiDTO> findOne(Long id) {
        log.debug("Request to get RequestNiazsanjiFardi : {}", id);
        return requestNiazsanjiFardiRepository.findOneWithEagerRelationships(id)
            .map(requestNiazsanjiFardiMapper::toDto);
    }

    /**
     * Delete the requestNiazsanjiFardi by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete RequestNiazsanjiFardi : {}", id);
        requestNiazsanjiFardiRepository.deleteById(id);
    }
    /**
     * Delete the requestNiazsanjiFardi by niazsanjiFardiId.
     *
     * @param niazsanjiFardiId the id of the entity
     */
    @Override
    public void deleteByFinalNiazsanjiFardi(Long niazsanjiFardiId) {
        log.debug("Request to delete RequestNiazsanjiFardi by niazsanjiFardiId : {}", niazsanjiFardiId);
        Optional<NiazsanjiFardi> niazsanjiFardi = niazsanjiFardiRepository.findOneWithEagerRelationships(niazsanjiFardiId);
        if(niazsanjiFardi.isPresent()) {
            Optional<RequestNiazsanjiFardiDTO> requestNiazsanjiFardi = this.findOne(niazsanjiFardi.get().getId());
            if (requestNiazsanjiFardi.isPresent()) {
                requestNiazsanjiFardiRepository.deleteById(requestNiazsanjiFardi.get().getId());
            }
        }
    }
}
