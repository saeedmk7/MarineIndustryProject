package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.NavBarItemAuthorityQueryService;
import com.marineindustryproj.service.NavBarItemAuthorityService;
import com.marineindustryproj.domain.NavBarItemAuthority;
import com.marineindustryproj.repository.NavBarItemAuthorityRepository;
import com.marineindustryproj.service.dto.NavBarItemAuthorityCriteria;
import com.marineindustryproj.service.dto.NavBarItemAuthorityDTO;
import com.marineindustryproj.service.mapper.NavBarItemAuthorityMapper;
import io.github.jhipster.service.filter.StringFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing NavBarItemAuthority.
 */
@Service
@Transactional
public class NavBarItemAuthorityServiceImpl implements NavBarItemAuthorityService {

    private final Logger log = LoggerFactory.getLogger(NavBarItemAuthorityServiceImpl.class);

    private final NavBarItemAuthorityRepository navBarItemAuthorityRepository;

    private final NavBarItemAuthorityMapper navBarItemAuthorityMapper;
    private final NavBarItemAuthorityQueryService navBarItemAuthorityQueryService;

    public NavBarItemAuthorityServiceImpl(NavBarItemAuthorityRepository navBarItemAuthorityRepository,
                                          NavBarItemAuthorityMapper navBarItemAuthorityMapper,
                                          NavBarItemAuthorityQueryService navBarItemAuthorityQueryService) {
        this.navBarItemAuthorityRepository = navBarItemAuthorityRepository;
        this.navBarItemAuthorityMapper = navBarItemAuthorityMapper;
        this.navBarItemAuthorityQueryService = navBarItemAuthorityQueryService;
    }

    /**
     * Save a navBarItemAuthority.
     *
     * @param navBarItemAuthorityDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public NavBarItemAuthorityDTO save(NavBarItemAuthorityDTO navBarItemAuthorityDTO) {
        log.debug("Request to save NavBarItemAuthority : {}", navBarItemAuthorityDTO);

        NavBarItemAuthority navBarItemAuthority = navBarItemAuthorityMapper.toEntity(navBarItemAuthorityDTO);
        navBarItemAuthority = navBarItemAuthorityRepository.save(navBarItemAuthority);
        return navBarItemAuthorityMapper.toDto(navBarItemAuthority);
    }

    @Override
    public NavBarItemAuthorityDTO bulkSave(List<NavBarItemAuthorityDTO> navBarItemAuthorityDTOs) {
        NavBarItemAuthority navBarItemAuthorityReturn = new NavBarItemAuthority();
        for (int i = 0; i < navBarItemAuthorityDTOs.size(); i++) {
            NavBarItemAuthority navBarItemAuthority = navBarItemAuthorityMapper.toEntity(navBarItemAuthorityDTOs.get(i));
            navBarItemAuthorityReturn = navBarItemAuthorityRepository.save(navBarItemAuthority);
        }
        return navBarItemAuthorityMapper.toDto(navBarItemAuthorityReturn);
    }

    /**
     * Get all the navBarItemAuthorities.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<NavBarItemAuthorityDTO> findAll(Pageable pageable) {
        log.debug("Request to get all NavBarItemAuthorities");
        return navBarItemAuthorityRepository.findAll(pageable)
            .map(navBarItemAuthorityMapper::toDto);
    }


    /**
     * Get one navBarItemAuthority by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<NavBarItemAuthorityDTO> findOne(Long id) {
        log.debug("Request to get NavBarItemAuthority : {}", id);
        return navBarItemAuthorityRepository.findById(id)
            .map(navBarItemAuthorityMapper::toDto);
    }

    /**
     * Delete the navBarItemAuthority by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete NavBarItemAuthority : {}", id);
        navBarItemAuthorityRepository.deleteById(id);
    }
    /**
     * Delete the list of navBarItemAuthority by authority.
     *
     * @param authority the id of the entity
     */

    public void delete(String authority){
        NavBarItemAuthorityCriteria criteria = new NavBarItemAuthorityCriteria();
        StringFilter stringFilter = new StringFilter();
        stringFilter.setEquals(authority);
        criteria.setAuthorityName(stringFilter);

        final Specification<NavBarItemAuthority> specification = navBarItemAuthorityQueryService.createSpecification(criteria);
        List<NavBarItemAuthority> navBarItemAuthorities = navBarItemAuthorityRepository.findAll(specification,
                                                                                                PageRequest.of(0, Integer.MAX_VALUE)).getContent();
        navBarItemAuthorities.forEach((a) -> {
            navBarItemAuthorityRepository.delete(a);
        });
    }
}
