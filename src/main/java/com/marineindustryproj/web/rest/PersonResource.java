package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.domain.Person;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.PersonService;
import com.marineindustryproj.service.dto.customs.PersonMinDTO;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.errors.ErrorConstants;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.PersonDTO;
import com.marineindustryproj.service.dto.PersonCriteria;
import com.marineindustryproj.service.PersonQueryService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.ZonedDateTimeFilter;
import io.github.jhipster.web.util.ResponseUtil;
import org.hibernate.id.GUIDGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * REST controller for managing Person.
 */
@RestController
@RequestMapping("/api")
public class PersonResource {

    private final Logger log = LoggerFactory.getLogger(PersonResource.class);

    private static final String ENTITY_NAME = "person";

    private final PersonService personService;

    private final PersonQueryService personQueryService;

    public PersonResource(PersonService personService, PersonQueryService personQueryService) {
        this.personService = personService;
        this.personQueryService = personQueryService;
    }

    /**
     * POST  /people : Create a new person.
     *
     * @param personDTO the personDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new personDTO, or with status 400 (Bad Request) if the person has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/people")
    @Timed
    public ResponseEntity<PersonDTO> createPerson(@Valid @RequestBody PersonDTO personDTO) throws URISyntaxException {
        log.debug("REST request to save Person : {}", personDTO);
        if (personDTO.getId() != null) {
            throw new BadRequestAlertException("A new person cannot already have an ID", ENTITY_NAME, "idexists");
        }

        try {
            personDTO.setGuid(UUID.randomUUID().toString());
            personDTO.setId(Long.parseLong(personDTO.getNationalId()));
            personDTO.setCreateDate(ZonedDateTime.now());
            personDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());

            PersonDTO result = personService.save(personDTO, false);
            return ResponseEntity.created(new URI("/api/people/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME,
                                                              result.getId().toString()))
                .body(result);
        }
        catch (Exception ex){
            throw new BadRequestAlertException(ErrorConstants.PERSONELCODE_ALREADY_USED_TYPE,
                                               "PersonelCode already used!",
                                               "person",
                                               "personexists");
        }
    }

    /**
     * PUT  /people : Updates an existing person.
     *
     * @param personDTO the personDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated personDTO,
     * or with status 400 (Bad Request) if the personDTO is not valid,
     * or with status 500 (Internal Server Error) if the personDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/people")
    @Timed
    public ResponseEntity<PersonDTO> updatePerson(@Valid @RequestBody PersonDTO personDTO) throws URISyntaxException {
        log.debug("REST request to update Person : {}", personDTO);
        if (personDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        PersonDTO person = personService.findOne(personDTO.getId()).get();
        personDTO.setGuid(person.getGuid());
        personDTO.setCreateUserLogin(person.getCreateUserLogin());
        personDTO.setCreateDate(person.getCreateDate());
        personDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        personDTO.setModifyDate(ZonedDateTime.now());

        PersonDTO result = personService.save(personDTO, true);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, personDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /people : get all the people.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of people in body
     */
    @GetMapping("/people")
    @Timed
    public ResponseEntity<List<PersonDTO>> getAllPeople(PersonCriteria criteria, Pageable pageable) {
        log.debug("REST request to get People by criteria: {}, pageable: {}", criteria, pageable);
        if(criteria.getArchived() == null)
        {
            BooleanFilter booleanFilter = new BooleanFilter();
            booleanFilter.setEquals(false);
            criteria.setArchived(booleanFilter);

            //new ZonedDateTimeFilter().setGreaterOrEqualThan()
        }
        if(pageable != null && pageable.getPageSize() < 2000) {
            Page<PersonDTO> page = personQueryService.findByCriteria(criteria,
                                                                     pageable);
            HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page,
                                                                               "/api/people");
            return new ResponseEntity<>(page.getContent(),
                                        headers,
                                        HttpStatus.OK);
        }
        else{
            List<PersonDTO> personList = personQueryService.findByCriteria(criteria);
            return new ResponseEntity<>(personList, null, HttpStatus.OK);
        }
    }

    /**
     * GET  /people : get all the people.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of people in body
     */
    @GetMapping("/people/all")
    @Timed
    public ResponseEntity<List<PersonMinDTO>> getAllPeople() {
        log.debug("REST request to get People All");
        List<PersonMinDTO> personList = personService.findAllFromCache();
        return new ResponseEntity<>(personList, null, HttpStatus.OK);
    }
    /**
     * GET  /people : get all the un chart people.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of people in body
     */
    @GetMapping("/people/allUnChart")
    @Timed
    public ResponseEntity<List<PersonDTO>> getUnChartPeople() {
        log.debug("REST request to get People All");
        List<PersonDTO> personList = personService.findAllUnChart();
        return new ResponseEntity<>(personList, null, HttpStatus.OK);
    }
    /**
    * GET  /people/count : count all the people.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/people/count")
    @Timed
    public ResponseEntity<Long> countPeople (PersonCriteria criteria) {
        log.debug("REST request to count People by criteria: {}", criteria);
        return ResponseEntity.ok().body(personQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /people/:id : get the "id" person.
     *
     * @param id the id of the personDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the personDTO, or with status 404 (Not Found)
     */
    @GetMapping("/people/{id}")
    @Timed
    public ResponseEntity<PersonDTO> getPerson(@PathVariable Long id) {
        log.debug("REST request to get Person : {}", id);
        Optional<PersonDTO> personDTO = personService.findOne(id);
        return ResponseUtil.wrapOrNotFound(personDTO);
    }

    /**
     * DELETE  /people/:id : delete the "id" person.
     *
     * @param id the id of the personDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/people/{id}")
    @Timed
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        log.debug("REST request to delete Person : {}", id);
        personService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
