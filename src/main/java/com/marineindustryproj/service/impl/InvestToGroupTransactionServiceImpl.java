package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.InvestToGroupTransactionService;
import com.marineindustryproj.domain.InvestToGroupTransaction;
import com.marineindustryproj.repository.InvestToGroupTransactionRepository;
import com.marineindustryproj.service.dto.InvestToGroupTransactionDTO;
import com.marineindustryproj.service.mapper.InvestToGroupTransactionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing InvestToGroupTransaction.
 */
@Service
@Transactional
public class InvestToGroupTransactionServiceImpl implements InvestToGroupTransactionService {

    private final Logger log = LoggerFactory.getLogger(InvestToGroupTransactionServiceImpl.class);

    private final InvestToGroupTransactionRepository investToGroupTransactionRepository;

    private final InvestToGroupTransactionMapper investToGroupTransactionMapper;

    public InvestToGroupTransactionServiceImpl(InvestToGroupTransactionRepository investToGroupTransactionRepository, InvestToGroupTransactionMapper investToGroupTransactionMapper) {
        this.investToGroupTransactionRepository = investToGroupTransactionRepository;
        this.investToGroupTransactionMapper = investToGroupTransactionMapper;
    }

    /**
     * Save a investToGroupTransaction.
     *
     * @param investToGroupTransactionDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public InvestToGroupTransactionDTO save(InvestToGroupTransactionDTO investToGroupTransactionDTO) {
        log.debug("Request to save InvestToGroupTransaction : {}", investToGroupTransactionDTO);

        InvestToGroupTransaction investToGroupTransaction = investToGroupTransactionMapper.toEntity(investToGroupTransactionDTO);
        investToGroupTransaction = investToGroupTransactionRepository.save(investToGroupTransaction);
        return investToGroupTransactionMapper.toDto(investToGroupTransaction);
    }

    /**
     * Get all the investToGroupTransactions.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<InvestToGroupTransactionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all InvestToGroupTransactions");
        return investToGroupTransactionRepository.findAll(pageable)
            .map(investToGroupTransactionMapper::toDto);
    }


    /**
     * Get one investToGroupTransaction by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<InvestToGroupTransactionDTO> findOne(Long id) {
        log.debug("Request to get InvestToGroupTransaction : {}", id);
        return investToGroupTransactionRepository.findById(id)
            .map(investToGroupTransactionMapper::toDto);
    }

    /**
     * Delete the investToGroupTransaction by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete InvestToGroupTransaction : {}", id);
        investToGroupTransactionRepository.deleteById(id);
    }
}
