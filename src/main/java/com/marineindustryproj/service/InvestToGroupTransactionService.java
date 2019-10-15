package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.InvestToGroupTransactionDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing InvestToGroupTransaction.
 */
public interface InvestToGroupTransactionService {

    /**
     * Save a investToGroupTransaction.
     *
     * @param investToGroupTransactionDTO the entity to save
     * @return the persisted entity
     */
    InvestToGroupTransactionDTO save(InvestToGroupTransactionDTO investToGroupTransactionDTO);

    /**
     * Get all the investToGroupTransactions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<InvestToGroupTransactionDTO> findAll(Pageable pageable);


    /**
     * Get the "id" investToGroupTransaction.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<InvestToGroupTransactionDTO> findOne(Long id);

    /**
     * Delete the "id" investToGroupTransaction.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
