package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.InvestToGroupTransactionService;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import com.marineindustryproj.web.rest.util.PaginationUtil;
import com.marineindustryproj.service.dto.InvestToGroupTransactionDTO;
import com.marineindustryproj.service.dto.InvestToGroupTransactionCriteria;
import com.marineindustryproj.service.InvestToGroupTransactionQueryService;
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
 * REST controller for managing InvestToGroupTransaction.
 */
@RestController
@RequestMapping("/api")
public class InvestToGroupTransactionResource {

    private final Logger log = LoggerFactory.getLogger(InvestToGroupTransactionResource.class);

    private static final String ENTITY_NAME = "investToGroupTransaction";

    private final InvestToGroupTransactionService investToGroupTransactionService;

    private final InvestToGroupTransactionQueryService investToGroupTransactionQueryService;

    public InvestToGroupTransactionResource(InvestToGroupTransactionService investToGroupTransactionService, InvestToGroupTransactionQueryService investToGroupTransactionQueryService) {
        this.investToGroupTransactionService = investToGroupTransactionService;
        this.investToGroupTransactionQueryService = investToGroupTransactionQueryService;
    }

    /**
     * POST  /invest-to-group-transactions : Create a new investToGroupTransaction.
     *
     * @param investToGroupTransactionDTO the investToGroupTransactionDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new investToGroupTransactionDTO, or with status 400 (Bad Request) if the investToGroupTransaction has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/invest-to-group-transactions")
    @Timed
    public ResponseEntity<InvestToGroupTransactionDTO> createInvestToGroupTransaction(@Valid @RequestBody InvestToGroupTransactionDTO investToGroupTransactionDTO) throws URISyntaxException {
        log.debug("REST request to save InvestToGroupTransaction : {}", investToGroupTransactionDTO);
        if (investToGroupTransactionDTO.getId() != null) {
            throw new BadRequestAlertException("A new investToGroupTransaction cannot already have an ID", ENTITY_NAME, "idexists");
        }
        investToGroupTransactionDTO.setCreateDate(ZonedDateTime.now());
        investToGroupTransactionDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
        investToGroupTransactionDTO.setGuid(UUID.randomUUID().toString());
        InvestToGroupTransactionDTO result = investToGroupTransactionService.save(investToGroupTransactionDTO);
        return ResponseEntity.created(new URI("/api/invest-to-group-transactions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /invest-to-group-transactions : Updates an existing investToGroupTransaction.
     *
     * @param investToGroupTransactionDTO the investToGroupTransactionDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated investToGroupTransactionDTO,
     * or with status 400 (Bad Request) if the investToGroupTransactionDTO is not valid,
     * or with status 500 (Internal Server Error) if the investToGroupTransactionDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/invest-to-group-transactions")
    @Timed
    public ResponseEntity<InvestToGroupTransactionDTO> updateInvestToGroupTransaction(@Valid @RequestBody InvestToGroupTransactionDTO investToGroupTransactionDTO) throws URISyntaxException {
        log.debug("REST request to update InvestToGroupTransaction : {}", investToGroupTransactionDTO);
        if (investToGroupTransactionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InvestToGroupTransactionDTO investToGroupTransaction = investToGroupTransactionService.findOne(investToGroupTransactionDTO.getId()).get();

        investToGroupTransactionDTO.setCreateUserLogin(investToGroupTransaction.getCreateUserLogin());
        investToGroupTransactionDTO.setCreateDate(investToGroupTransaction.getCreateDate());
        investToGroupTransactionDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
        investToGroupTransactionDTO.setModifyDate(ZonedDateTime.now());
        investToGroupTransactionDTO.setGuid(investToGroupTransaction.getGuid());
        InvestToGroupTransactionDTO result = investToGroupTransactionService.save(investToGroupTransactionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, investToGroupTransactionDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /invest-to-group-transactions : get all the investToGroupTransactions.
     *
     * @param pageable the pagination information
     * @param criteria the criterias which the requested entities should match
     * @return the ResponseEntity with status 200 (OK) and the list of investToGroupTransactions in body
     */
    @GetMapping("/invest-to-group-transactions")
    @Timed
    public ResponseEntity<List<InvestToGroupTransactionDTO>> getAllInvestToGroupTransactions(InvestToGroupTransactionCriteria criteria, Pageable pageable) {
        log.debug("REST request to get InvestToGroupTransactions by criteria: {}", criteria);
        Page<InvestToGroupTransactionDTO> page = investToGroupTransactionQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/invest-to-group-transactions");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * GET  /invest-to-group-transactions/count : count all the investToGroupTransactions.
    *
    * @param criteria the criterias which the requested entities should match
    * @return the ResponseEntity with status 200 (OK) and the count in body
    */
    @GetMapping("/invest-to-group-transactions/count")
    @Timed
    public ResponseEntity<Long> countInvestToGroupTransactions(InvestToGroupTransactionCriteria criteria) {
        log.debug("REST request to count InvestToGroupTransactions by criteria: {}", criteria);
        return ResponseEntity.ok().body(investToGroupTransactionQueryService.countByCriteria(criteria));
    }

    /**
     * GET  /invest-to-group-transactions/:id : get the "id" investToGroupTransaction.
     *
     * @param id the id of the investToGroupTransactionDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the investToGroupTransactionDTO, or with status 404 (Not Found)
     */
    @GetMapping("/invest-to-group-transactions/{id}")
    @Timed
    public ResponseEntity<InvestToGroupTransactionDTO> getInvestToGroupTransaction(@PathVariable Long id) {
        log.debug("REST request to get InvestToGroupTransaction : {}", id);
        Optional<InvestToGroupTransactionDTO> investToGroupTransactionDTO = investToGroupTransactionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(investToGroupTransactionDTO);
    }

    /**
     * DELETE  /invest-to-group-transactions/:id : delete the "id" investToGroupTransaction.
     *
     * @param id the id of the investToGroupTransactionDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/invest-to-group-transactions/{id}")
    @Timed
    public ResponseEntity<Void> deleteInvestToGroupTransaction(@PathVariable Long id) {
        log.debug("REST request to delete InvestToGroupTransaction : {}", id);
        investToGroupTransactionService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
