package com.marineindustryproj.service.impl;

import com.marineindustryproj.domain.User;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.PersonService;
import com.marineindustryproj.domain.Person;
import com.marineindustryproj.repository.PersonRepository;
import com.marineindustryproj.service.UserService;
import com.marineindustryproj.service.dto.PersonDTO;
import com.marineindustryproj.service.dto.UserDTO;
import com.marineindustryproj.service.dto.customs.PersonMinDTO;
import com.marineindustryproj.service.mapper.PersonMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 * Service Implementation for managing Person.
 */
@Service
@Transactional
public class PersonServiceImpl implements PersonService {

    private final Logger log = LoggerFactory.getLogger(PersonServiceImpl.class);

    private final PersonRepository personRepository;

    private final PersonMapper personMapper;

    private final UserService userService;

    private final CacheManager cacheManager;

    public PersonServiceImpl(PersonRepository personRepository,
                             PersonMapper personMapper,
                             UserService userService,
                             CacheManager cacheManager) {
        this.personRepository = personRepository;
        this.personMapper = personMapper;
        this.userService = userService;
        this.cacheManager = cacheManager;
    }

    /**
     * Save a person.
     *
     * @param personDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public PersonDTO save(PersonDTO personDTO, Boolean isEit) {
        log.debug("Request to save Person : {}", personDTO);

        Person person = personMapper.toEntity(personDTO);
        person = personRepository.save(person);
        clearPersonCaches();

        if(!isEit){
            User newUser = saveNewUser(person,
                        userService);
            if(newUser != null) {
                userService.clearUserCaches(newUser);
            }
        }
        return personMapper.toDto(person);
    }

    public static User saveNewUser(Person person,
                                   UserService userService
                                   ) {
        try {
            if (!userService.getUserWithAuthoritiesByLogin(person.getNationalId()).isPresent()) {
                UserDTO userDTO = new UserDTO();
                userDTO.setActivated(true);
                userDTO.setCreatedBy(SecurityUtils.getCurrentUserLogin().get());
                userDTO.setLastModifiedBy(SecurityUtils.getCurrentUserLogin().get());
                userDTO.setCreatedDate(Instant.now());
                userDTO.setLastModifiedDate(Instant.now());
                userDTO.setEmail(person.getNationalId() + "@amoozesh.com");
                userDTO.setLangKey("fa");
                userDTO.setLogin(person.getNationalId());
                userDTO.setPassword("123456");
                userDTO.setPersonId(person.getId());
                Set<String> authorities = new HashSet<>();
                authorities.add("ROLE_USER");
                userDTO.setAuthorities(authorities);

                User newUser = userService.createUser(userDTO);
                return newUser;
            }
            return null;
        }
        catch(Exception ex) {
            return null;
        }
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
