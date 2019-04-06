package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.UsersRequestService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.UsersRequestDTO;
import com.marineindustryproj.service.dto.UsersRequestCriteria;
import com.marineindustryproj.service.UsersRequestQueryService;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

/**
 * REST controller for managing UsersRequest.
 */
@RestController
@RequestMapping("/api")
public class UsersRequestResource {

    private final Logger log = LoggerFactory.getLogger(UsersRequestResource.class);

    private static final String ENTITY_NAME = "usersRequest";

    private final UsersRequestService usersRequestService;

    private final UsersRequestQueryService usersRequestQueryService;

    public UsersRequestResource(UsersRequestService usersRequestService, UsersRequestQueryService usersRequestQueryService) {
        this.usersRequestService = usersRequestService;
        this.usersRequestQueryService = usersRequestQueryService;
    }

    /**
     * POST  /users-requests : Create a new usersRequest.
     *
     * @param usersRequestDTO the usersRequestDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new usersRequestDTO, or with status 400 (Bad Request) if the usersRequest has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/users-requests")
    @Timed
    public ResponseEntity<UsersRequestDTO> createUsersRequest(@Valid @RequestBody UsersRequestDTO usersRequestDTO) throws URISyntaxException {
        log.debug("REST request to save UsersRequest : {}", usersRequestDTO);
        if (usersRequestDTO.getId() != null) {
            throw new BadRequestAlertException("A new usersRequest cannot already have an ID", ENTITY_NAME, "idexists");
        }

        usersRequestDTO.setCreateDate(ZonedDateTime.now());
        usersRequestDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());

        UsersRequestDTO result = usersRequestService.save(usersRequestDTO);
        return ResponseEntity.created(new URI("/api/users-requests/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /users-requests : Updates an existing usersRequest.
     *
     * @param usersRequestDTO the usersRequestDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated usersRequestDTO,
     * or with status 400 (Bad Request) if the usersRequestDTO is not valid,
     * or with status 500 (Internal Server Error) if the usersRequestDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/users-requests")
    @Timed
    public ResponseEntity<UsersRequestDTO> updateUsersRequest(@Valid @RequestBody UsersRequestDTO usersRequestDTO) throws URISyntaxException {
        log.debug("REST request to update UsersRequest : {}", usersRequestDTO);
        if (usersRequestDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        UsersRequestDTO usersRequest = usersRequestService.findOne(usersRequestDTO.getId()).get();

        usersRequestDTO.setCreateUserLogin(usersRequest.getCreateUserLogin());
        usersRequestDTO.setCreateDate(usersRequest.getCreateDate());
        usersRequestDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        usersRequestDTO.setModifyDate(ZonedDateTime.now());

        UsersRequestDTO result = usersRequestService.save(usersRequestDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, usersRequestDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /users-requests : get all the usersRequests.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of usersRequests in body
     */
    @GetMapping("/users-requests")
    @Timed
    public ResponseEntity<List<UsersRequestDTO>> getAllUsersRequests(UsersRequestCriteria criteria, Pageable pageable) {
        log.debug("REST request to get UsersRequests by criteria: {}", criteria);
        Page<UsersRequestDTO> page = usersRequestQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/users-requests");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
    * GET  /users-requests/count : count all the usersRequests.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/users-requests/count")
    @Timed
    public ResponseEntity<Long> countUsersRequests (UsersRequestCriteria criteria) {
        log.debug("REST request to count UsersRequests by criteria: {}", criteria);
        return ResponseEntity.ok().body(usersRequestQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /users-requests/:id : get the "id" usersRequest.
     *
     * @param id the id of the usersRequestDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the usersRequestDTO, or with status 404 (Not Found)
     */
    @GetMapping("/users-requests/{id}")
    @Timed
    public ResponseEntity<UsersRequestDTO> getUsersRequest(@PathVariable Long id) {
        log.debug("REST request to get UsersRequest : {}", id);
        Optional<UsersRequestDTO> usersRequestDTO = usersRequestService.findOne(id);
        return ResponseUtil.wrapOrNotFound(usersRequestDTO);
    }

    /**
     * DELETE  /users-requests/:id : delete the "id" usersRequest.
     *
     * @param id the id of the usersRequestDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/users-requests/{id}")
    @Timed
    public ResponseEntity<Void> deleteUsersRequest(@PathVariable Long id) {
        log.debug("REST request to delete UsersRequest : {}", id);
        usersRequestService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
