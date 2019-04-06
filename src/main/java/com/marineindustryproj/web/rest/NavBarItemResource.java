package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.service.NavBarItemService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.service.dto.NavBarItemDTO;
import com.marineindustryproj.service.dto.NavBarItemCriteria;
import com.marineindustryproj.service.NavBarItemQueryService;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing NavBarItem.
 */
@RestController
@RequestMapping("/api")
public class NavBarItemResource {

    private final Logger log = LoggerFactory.getLogger(NavBarItemResource.class);

    private static final String ENTITY_NAME = "navBarItem";

    private final NavBarItemService navBarItemService;

    private final NavBarItemQueryService navBarItemQueryService;

    public NavBarItemResource(NavBarItemService navBarItemService, NavBarItemQueryService navBarItemQueryService) {
        this.navBarItemService = navBarItemService;
        this.navBarItemQueryService = navBarItemQueryService;
    }

    /**
     * POST  /nav-bar-items : Create a new navBarItem.
     *
     * @param navBarItemDTO the navBarItemDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new navBarItemDTO, or with status 400 (Bad Request) if the navBarItem has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/nav-bar-items")
    @Timed
    public ResponseEntity<NavBarItemDTO> createNavBarItem(@Valid @RequestBody NavBarItemDTO navBarItemDTO) throws URISyntaxException {
        log.debug("REST request to save NavBarItem : {}", navBarItemDTO);
        if (navBarItemDTO.getId() != null) {
            throw new BadRequestAlertException("A new navBarItem cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NavBarItemDTO result = navBarItemService.save(navBarItemDTO);
        return ResponseEntity.created(new URI("/api/nav-bar-items/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /nav-bar-items : Updates an existing navBarItem.
     *
     * @param navBarItemDTO the navBarItemDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated navBarItemDTO,
     * or with status 400 (Bad Request) if the navBarItemDTO is not valid,
     * or with status 500 (Internal Server Error) if the navBarItemDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/nav-bar-items")
    @Timed
    public ResponseEntity<NavBarItemDTO> updateNavBarItem(@Valid @RequestBody NavBarItemDTO navBarItemDTO) throws URISyntaxException {
        log.debug("REST request to update NavBarItem : {}", navBarItemDTO);
        if (navBarItemDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        NavBarItemDTO result = navBarItemService.save(navBarItemDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, navBarItemDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /nav-bar-items : get all the navBarItems.
     *
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of navBarItems in body
     */
    @GetMapping("/nav-bar-items")
    @Timed
    public ResponseEntity<List<NavBarItemDTO>> getAllNavBarItems(NavBarItemCriteria criteria) {
        log.debug("REST request to get NavBarItems by criteria: {}", criteria);
        List<NavBarItemDTO> entityList = navBarItemQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }

    /**
    * GET  /nav-bar-items/count : count all the navBarItems.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/nav-bar-items/count")
    @Timed
    public ResponseEntity<Long> countNavBarItems (NavBarItemCriteria criteria) {
        log.debug("REST request to count NavBarItems by criteria: {}", criteria);
        return ResponseEntity.ok().body(navBarItemQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /nav-bar-items/:id : get the "id" navBarItem.
     *
     * @param id the id of the navBarItemDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the navBarItemDTO, or with status 404 (Not Found)
     */
    @GetMapping("/nav-bar-items/{id}")
    @Timed
    public ResponseEntity<NavBarItemDTO> getNavBarItem(@PathVariable Long id) {
        log.debug("REST request to get NavBarItem : {}", id);
        Optional<NavBarItemDTO> navBarItemDTO = navBarItemService.findOne(id);
        return ResponseUtil.wrapOrNotFound(navBarItemDTO);
    }

    /**
     * DELETE  /nav-bar-items/:id : delete the "id" navBarItem.
     *
     * @param id the id of the navBarItemDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/nav-bar-items/{id}")
    @Timed
    public ResponseEntity<Void> deleteNavBarItem(@PathVariable Long id) {
        log.debug("REST request to delete NavBarItem : {}", id);
        navBarItemService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
