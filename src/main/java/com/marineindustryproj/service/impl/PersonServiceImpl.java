package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.PersonService;
import com.marineindustryproj.domain.Person;
import com.marineindustryproj.repository.PersonRepository;
import com.marineindustryproj.service.dto.PersonDTO;
import com.marineindustryproj.service.dto.customs.PersonMinDTO;
import com.marineindustryproj.service.mapper.PersonMapper;
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
 * Service Implementation for managing Person.
 */
@Service
@Transactional
public class PersonServiceImpl implements PersonService {

    private final Logger log = LoggerFactory.getLogger(PersonServiceImpl.class);

    private final PersonRepository personRepository;

    private final PersonMapper personMapper;

    private final CacheManager cacheManager;

    public PersonServiceImpl(PersonRepository personRepository,
                             PersonMapper personMapper,
                             CacheManager cacheManager) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
        this.cacheManager = cacheManager;
    }

    /**
     * Save a person.
     *
     * @param personDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public PersonDTO save(PersonDTO personDTO) {
        log.debug("Request to save Person : {}", personDTO);

        Person person = personMapper.toEntity(personDTO);
        person = personRepository.save(person);
        clearPersonCaches();
        return personMapper.toDto(person);
    }

    /**
     * Get all the people.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PersonDTO> findAll(Pageable pageable) {
        log.debug("Request to get all People");
        return personRepository.findAll(pageable)
            .map(personMapper::toDto);
    }

    @Override
    public List<PersonMinDTO> findAllFromCache() {
        return personRepository.findAllFromCache();
    }

    /**
     * Get all the Person with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<PersonDTO> findAllWithEagerRelationships(Pageable pageable) {
        return personRepository.findAllWithEagerRelationships(pageable).map(personMapper::toDto);
    }
    

    /**
     * Get one person by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PersonDTO> findOne(Long id) {
        log.debug("Request to get Person : {}", id);
        return personRepository.findOneWithEagerRelationships(id)
            .map(personMapper::toDto);
    }

    /**
     * Delete the person by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Person : {}", id);
        personRepository.deleteById(id);
        clearPersonCaches();
    }
    private void clearPersonCaches() {
        Objects.requireNonNull(cacheManager.getCache(personRepository.ALL_PERSON_CACHE)).clear();
    }
}
