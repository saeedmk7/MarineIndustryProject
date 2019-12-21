package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.MediaProductTypeService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.MediaProductTypeDTO;
import com.marineindustryproj.service.dto.MediaProductTypeCriteria;
import com.marineindustryproj.service.MediaProductTypeQueryService;
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
import java.util.UUID;

/**
 * REST controller for managing MediaProductType.
 */
@RestController
@RequestMapping("/api")
public class MediaProductTypeResource {

    private final Logger log = LoggerFactory.getLogger(MediaProductTypeResource.class);

    private static final String ENTITY_NAME = "mediaProductType";

    private final MediaProductTypeService mediaProductTypeService;

    private final MediaProductTypeQueryService mediaProductTypeQueryService;

    public MediaProductTypeResource(MediaProductTypeService mediaProductTypeService, MediaProductTypeQueryService mediaProductTypeQueryService) {
        this.mediaProductTypeService = mediaProductTypeService;
        this.mediaProductTypeQueryService = mediaProductTypeQueryService;
    }

    /**
     * POST  /media-product-types : Create a new mediaProductType.
     *
     * @param mediaProductTypeDTO the mediaProductTypeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new mediaProductTypeDTO, or with status 400 (Bad Request) if the mediaProductType has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/media-product-types")
    @Timed
    public ResponseEntity<MediaProductTypeDTO> createMediaProductType(@Valid @RequestBody MediaProductTypeDTO mediaProductTypeDTO) throws URISyntaxException {
        log.debug("REST request to save MediaProductType : {}", mediaProductTypeDTO);
        if (mediaProductTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new mediaProductType cannot already have an ID", ENTITY_NAME, "idexists");
        }

        mediaProductTypeDTO.setCreateDate(ZonedDateTime.now());
        mediaProductTypeDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());

        MediaProductTypeDTO result = mediaProductTypeService.save(mediaProductTypeDTO);
        return ResponseEntity.created(new URI("/api/media-product-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /media-product-types : Updates an existing mediaProductType.
     *
     * @param mediaProductTypeDTO the mediaProductTypeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated mediaProductTypeDTO,
     * or with status 400 (Bad Request) if the mediaProductTypeDTO is not valid,
     * or with status 500 (Internal Server Error) if the mediaProductTypeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/media-product-types")
    @Timed
    public ResponseEntity<MediaProductTypeDTO> updateMediaProductType(@Valid @RequestBody MediaProductTypeDTO mediaProductTypeDTO) throws URISyntaxException {
        log.debug("REST request to update MediaProductType : {}", mediaProductTypeDTO);
        if (mediaProductTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        MediaProductTypeDTO mediaProductType = mediaProductTypeService.findOne(mediaProductTypeDTO.getId()).get();

        mediaProductTypeDTO.setCreateUserLogin(mediaProductType.getCreateUserLogin());
        mediaProductTypeDTO.setCreateDate(mediaProductType.getCreateDate());
        mediaProductTypeDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        mediaProductTypeDTO.setModifyDate(ZonedDateTime.now());

        MediaProductTypeDTO result = mediaProductTypeService.save(mediaProductTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, mediaProductTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /media-product-types : get all the mediaProductTypes.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of mediaProductTypes in body
     */
    @GetMapping("/media-product-types")
    @Timed
    public ResponseEntity<List<MediaProductTypeDTO>> getAllMediaProductTypes(MediaProductTypeCriteria criteria, Pageable pageable) {
        log.debug("REST request to get MediaProductTypes by criteria: {}", criteria);
        Page<MediaProductTypeDTO> page = mediaProductTypeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/media-product-types");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /media-product-types/count : count all the mediaProductTypes.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/media-product-types/count")
    @Timed
    public ResponseEntity<Long> countMediaProductTypes(MediaProductTypeCriteria criteria) {
        log.debug("REST request to count MediaProductTypes by criteria: {}", criteria);
        return ResponseEntity.ok().body(mediaProductTypeQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /media-product-types/:id : get the "id" mediaProductType.
     *
     * @param id the id of the mediaProductTypeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the mediaProductTypeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/media-product-types/{id}")
    @Timed
    public ResponseEntity<MediaProductTypeDTO> getMediaProductType(@PathVariable Long id) {
        log.debug("REST request to get MediaProductType : {}", id);
        Optional<MediaProductTypeDTO> mediaProductTypeDTO = mediaProductTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(mediaProductTypeDTO);
    }

    /**
     * DELETE  /media-product-types/:id : delete the "id" mediaProductType.
     *
     * @param id the id of the mediaProductTypeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/media-product-types/{id}")
    @Timed
    public ResponseEntity<Void> deleteMediaProductType(@PathVariable Long id) {
        log.debug("REST request to delete MediaProductType : {}", id);
        mediaProductTypeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
