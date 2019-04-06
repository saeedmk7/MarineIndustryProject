package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.NavBarItemService;
import com.marineindustryproj.domain.NavBarItem;
import com.marineindustryproj.repository.NavBarItemRepository;
import com.marineindustryproj.service.dto.NavBarItemDTO;
import com.marineindustryproj.service.mapper.NavBarItemMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing NavBarItem.
 */
@Service
@Transactional
public class NavBarItemServiceImpl implements NavBarItemService {

    private final Logger log = LoggerFactory.getLogger(NavBarItemServiceImpl.class);

    private final NavBarItemRepository navBarItemRepository;

    private final NavBarItemMapper navBarItemMapper;

    public NavBarItemServiceImpl(NavBarItemRepository navBarItemRepository, NavBarItemMapper navBarItemMapper) {
        this.navBarItemRepository = navBarItemRepository;
        this.navBarItemMapper = navBarItemMapper;
    }

    /**
     * Save a navBarItem.
     *
     * @param navBarItemDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public NavBarItemDTO save(NavBarItemDTO navBarItemDTO) {
        log.debug("Request to save NavBarItem : {}", navBarItemDTO);

        NavBarItem navBarItem = navBarItemMapper.toEntity(navBarItemDTO);
        navBarItem = navBarItemRepository.save(navBarItem);
        return navBarItemMapper.toDto(navBarItem);
    }

    /**
     * Get all the navBarItems.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<NavBarItemDTO> findAll() {
        log.debug("Request to get all NavBarItems");
        return navBarItemRepository.findAll().stream()
            .map(navBarItemMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    /**
     * Get one navBarItem by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<NavBarItemDTO> findOne(Long id) {
        log.debug("Request to get NavBarItem : {}", id);
        return navBarItemRepository.findById(id)
            .map(navBarItemMapper::toDto);
    }

    /**
     * Delete the navBarItem by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete NavBarItem : {}", id);
        navBarItemRepository.deleteById(id);
    }
}
