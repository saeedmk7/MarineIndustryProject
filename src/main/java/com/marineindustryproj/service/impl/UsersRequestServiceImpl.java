package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.UsersRequestService;
import com.marineindustryproj.domain.UsersRequest;
import com.marineindustryproj.repository.UsersRequestRepository;
import com.marineindustryproj.service.dto.UsersRequestDTO;
import com.marineindustryproj.service.mapper.UsersRequestMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing UsersRequest.
 */
@Service
@Transactional
public class UsersRequestServiceImpl implements UsersRequestService {

    private final Logger log = LoggerFactory.getLogger(UsersRequestServiceImpl.class);

    private final UsersRequestRepository usersRequestRepository;

    private final UsersRequestMapper usersRequestMapper;

    public UsersRequestServiceImpl(UsersRequestRepository usersRequestRepository, UsersRequestMapper usersRequestMapper) {
        this.usersRequestRepository = usersRequestRepository;
        this.usersRequestMapper = usersRequestMapper;
    }

    /**
     * Save a usersRequest.
     *
     * @param usersRequestDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public UsersRequestDTO save(UsersRequestDTO usersRequestDTO) {
        log.debug("Request to save UsersRequest : {}", usersRequestDTO);

        UsersRequest usersRequest = usersRequestMapper.toEntity(usersRequestDTO);
        usersRequest = usersRequestRepository.save(usersRequest);
        return usersRequestMapper.toDto(usersRequest);
    }

    /**
     * Get all the usersRequests.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<UsersRequestDTO> findAll(Pageable pageable) {
        log.debug("Request to get all UsersRequests");
        return usersRequestRepository.findAll(pageable)
            .map(usersRequestMapper::toDto);
    }

    /**
     * Get all the UsersRequest with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<UsersRequestDTO> findAllWithEagerRelationships(Pageable pageable) {
        return usersRequestRepository.findAllWithEagerRelationships(pageable).map(usersRequestMapper::toDto);
    }
    

    /**
     * Get one usersRequest by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<UsersRequestDTO> findOne(Long id) {
        log.debug("Request to get UsersRequest : {}", id);
        return usersRequestRepository.findOneWithEagerRelationships(id)
            .map(usersRequestMapper::toDto);
    }

    /**
     * Delete the usersRequest by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete UsersRequest : {}", id);
        usersRequestRepository.deleteById(id);
    }
}
