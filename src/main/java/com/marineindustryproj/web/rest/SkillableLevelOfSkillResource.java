package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.domain.SkillableLevelOfSkill;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.SkillableLevelOfSkillService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.SkillableLevelOfSkillDTO;
import com.marineindustryproj.service.dto.SkillableLevelOfSkillCriteria;
import com.marineindustryproj.service.SkillableLevelOfSkillQueryService;
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
 * REST controller for managing SkillableLevelOfSkill.
 */
@RestController
@RequestMapping("/api")
public class SkillableLevelOfSkillResource {

    private final Logger log = LoggerFactory.getLogger(SkillableLevelOfSkillResource.class);

    private static final String ENTITY_NAME = "skillableLevelOfSkill";

    private final SkillableLevelOfSkillService skillableLevelOfSkillService;

    private final SkillableLevelOfSkillQueryService skillableLevelOfSkillQueryService;

    public SkillableLevelOfSkillResource(SkillableLevelOfSkillService skillableLevelOfSkillService, SkillableLevelOfSkillQueryService skillableLevelOfSkillQueryService) {
        this.skillableLevelOfSkillService = skillableLevelOfSkillService;
        this.skillableLevelOfSkillQueryService = skillableLevelOfSkillQueryService;
    }

    /**
     * POST  /skillable-level-of-skills : Create a new skillableLevelOfSkill.
     *
     * @param skillableLevelOfSkillDTO the skillableLevelOfSkillDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new skillableLevelOfSkillDTO, or with status 400 (Bad Request) if the skillableLevelOfSkill has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/skillable-level-of-skills")
    @Timed
    public ResponseEntity<SkillableLevelOfSkillDTO> createSkillableLevelOfSkill(@Valid @RequestBody SkillableLevelOfSkillDTO skillableLevelOfSkillDTO) throws URISyntaxException {
        log.debug("REST request to save SkillableLevelOfSkill : {}", skillableLevelOfSkillDTO);
        if (skillableLevelOfSkillDTO.getId() != null) {
            throw new BadRequestAlertException("A new skillableLevelOfSkill cannot already have an ID", ENTITY_NAME, "idexists");
        }
        skillableLevelOfSkillDTO.setCreateDate(ZonedDateTime.now());
        skillableLevelOfSkillDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
        SkillableLevelOfSkillDTO result = skillableLevelOfSkillService.save(skillableLevelOfSkillDTO);
        return ResponseEntity.created(new URI("/api/skillable-level-of-skills/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /skillable-level-of-skills : Updates an existing skillableLevelOfSkill.
     *
     * @param skillableLevelOfSkillDTO the skillableLevelOfSkillDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated skillableLevelOfSkillDTO,
     * or with status 400 (Bad Request) if the skillableLevelOfSkillDTO is not valid,
     * or with status 500 (Internal Server Error) if the skillableLevelOfSkillDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/skillable-level-of-skills")
    @Timed
    public ResponseEntity<SkillableLevelOfSkillDTO> updateSkillableLevelOfSkill(@Valid @RequestBody SkillableLevelOfSkillDTO skillableLevelOfSkillDTO) throws URISyntaxException {
        log.debug("REST request to update SkillableLevelOfSkill : {}", skillableLevelOfSkillDTO);
        if (skillableLevelOfSkillDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SkillableLevelOfSkillDTO skillableLevelOfSkill = skillableLevelOfSkillService.findOne(skillableLevelOfSkillDTO.getId()).get();

        skillableLevelOfSkillDTO.setCreateUserLogin(skillableLevelOfSkill.getCreateUserLogin());
        skillableLevelOfSkillDTO.setCreateDate(skillableLevelOfSkill.getCreateDate());
        skillableLevelOfSkillDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        skillableLevelOfSkillDTO.setModifyDate(ZonedDateTime.now());
        SkillableLevelOfSkillDTO result = skillableLevelOfSkillService.save(skillableLevelOfSkillDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, skillableLevelOfSkillDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /skillable-level-of-skills : get all the skillableLevelOfSkills.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of skillableLevelOfSkills in body
     */
    @GetMapping("/skillable-level-of-skills")
    @Timed
    public ResponseEntity<List<SkillableLevelOfSkillDTO>> getAllSkillableLevelOfSkills(SkillableLevelOfSkillCriteria criteria, Pageable pageable) {
        log.debug("REST request to get SkillableLevelOfSkills by criteria: {}", criteria);
        Page<SkillableLevelOfSkillDTO> page = skillableLevelOfSkillQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/skillable-level-of-skills");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
    * GET  /skillable-level-of-skills/count : count all the skillableLevelOfSkills.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/skillable-level-of-skills/count")
    @Timed
    public ResponseEntity<Long> countSkillableLevelOfSkills (SkillableLevelOfSkillCriteria criteria) {
        log.debug("REST request to count SkillableLevelOfSkills by criteria: {}", criteria);
        return ResponseEntity.ok().body(skillableLevelOfSkillQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /skillable-level-of-skills/:id : get the "id" skillableLevelOfSkill.
     *
     * @param id the id of the skillableLevelOfSkillDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the skillableLevelOfSkillDTO, or with status 404 (Not Found)
     */
    @GetMapping("/skillable-level-of-skills/{id}")
    @Timed
    public ResponseEntity<SkillableLevelOfSkillDTO> getSkillableLevelOfSkill(@PathVariable Long id) {
        log.debug("REST request to get SkillableLevelOfSkill : {}", id);
        Optional<SkillableLevelOfSkillDTO> skillableLevelOfSkillDTO = skillableLevelOfSkillService.findOne(id);
        return ResponseUtil.wrapOrNotFound(skillableLevelOfSkillDTO);
    }

    /**
     * DELETE  /skillable-level-of-skills/:id : delete the "id" skillableLevelOfSkill.
     *
     * @param id the id of the skillableLevelOfSkillDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/skillable-level-of-skills/{id}")
    @Timed
    public ResponseEntity<Void> deleteSkillableLevelOfSkill(@PathVariable Long id) {
        log.debug("REST request to delete SkillableLevelOfSkill : {}", id);
        skillableLevelOfSkillService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
