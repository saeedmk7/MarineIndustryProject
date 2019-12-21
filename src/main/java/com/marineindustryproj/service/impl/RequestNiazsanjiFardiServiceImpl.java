package com.marineindustryproj.service.impl;

import com.marineindustryproj.domain.NiazsanjiFardi;
import com.marineindustryproj.domain.Person;
import com.marineindustryproj.domain.enumeration.EducationalModuleType;
import com.marineindustryproj.repository.NiazsanjiFardiRepository;
import com.marineindustryproj.repository.PersonRepository;
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

/**
 * Service Implementation for managing RequestNiazsanjiFardi.
 */
@Service
@Transactional
public class RequestNiazsanjiFardiServiceImpl implements RequestNiazsanjiFardiService {

    private final Logger log = LoggerFactory.getLogger(RequestNiazsanjiFardiServiceImpl.class);

    private final RequestNiazsanjiFardiRepository requestNiazsanjiFardiRepository;

    private final NiazsanjiFardiRepository niazsanjiFardiRepository;

    private final PersonRepository personRepository;

    private final RequestNiazsanjiFardiMapper requestNiazsanjiFardiMapper;

    public RequestNiazsanjiFardiServiceImpl(RequestNiazsanjiFardiRepository requestNiazsanjiFardiRepository,
                                            NiazsanjiFardiRepository niazsanjiFardiRepository,
                                            PersonRepository personRepository,
                                            RequestNiazsanjiFardiMapper requestNiazsanjiFardiMapper) {
        this.requestNiazsanjiFardiRepository = requestNiazsanjiFardiRepository;
        this.niazsanjiFardiRepository = niazsanjiFardiRepository;
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
            NiazsanjiFardi niazsanjiFardi = new NiazsanjiFardi();
            niazsanjiFardi.setCreateDate(ZonedDateTime.now());
            niazsanjiFardi.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
            niazsanjiFardi.setModifyDate(ZonedDateTime.now());
            niazsanjiFardi.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
            niazsanjiFardi.setDocuments(requestNiazsanjiFardi.getDocuments());
            niazsanjiFardi.setArchived(false);

            niazsanjiFardi.setOrganizationChart(requestNiazsanjiFardi.getOrganizationChart());
            niazsanjiFardi.setPerson(requestNiazsanjiFardi.getPerson());
            niazsanjiFardi.setRequestNiazsanjiFardi(requestNiazsanjiFardi);
            niazsanjiFardi.setDescription(requestNiazsanjiFardi.getDescription());
            niazsanjiFardi.setCode(requestNiazsanjiFardi.getCode());
            niazsanjiFardi.setStatus(0);

            niazsanjiFardi.setEducationalModuleType(EducationalModuleType.ALL);
            niazsanjiFardi.setEducationalModule(requestNiazsanjiFardi.getAllEducationalModule());
            niazsanjiFardi.setPriceCost(requestNiazsanjiFardi.getCostAllEducationalModule());
            niazsanjiFardi.setCourseType(requestNiazsanjiFardi.getCourseType());
            niazsanjiFardi.setHasImportantMessage(requestNiazsanjiFardi.isHasImportantMessage());
            niazsanjiFardiRepository.save(niazsanjiFardi);
        }
        if(requestNiazsanjiFardi.getApprovedEducationalModule() != null)
        {
            NiazsanjiFardi niazsanjiFardi = new NiazsanjiFardi();
            niazsanjiFardi.setCreateDate(ZonedDateTime.now());
            niazsanjiFardi.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
            niazsanjiFardi.setModifyDate(ZonedDateTime.now());
            niazsanjiFardi.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
            niazsanjiFardi.setDocuments(requestNiazsanjiFardi.getDocuments());
            niazsanjiFardi.setArchived(false);

            niazsanjiFardi.setOrganizationChart(requestNiazsanjiFardi.getOrganizationChart());
            niazsanjiFardi.setPerson(requestNiazsanjiFardi.getPerson());
            niazsanjiFardi.setRequestNiazsanjiFardi(requestNiazsanjiFardi);
            niazsanjiFardi.setDescription(requestNiazsanjiFardi.getDescription());
            niazsanjiFardi.setCode(requestNiazsanjiFardi.getCode());
            niazsanjiFardi.setStatus(0);

            niazsanjiFardi.setEducationalModuleType(EducationalModuleType.APPROVED);
            niazsanjiFardi.setEducationalModule(requestNiazsanjiFardi.getApprovedEducationalModule());
            niazsanjiFardi.setPriceCost(requestNiazsanjiFardi.getCostApprovedEducationalModule());
            niazsanjiFardi.setCourseType(requestNiazsanjiFardi.getCourseType());
            niazsanjiFardi.setHasImportantMessage(requestNiazsanjiFardi.isHasImportantMessage());
            niazsanjiFardiRepository.save(niazsanjiFardi);
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
