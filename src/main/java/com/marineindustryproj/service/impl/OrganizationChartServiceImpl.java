package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.OrganizationChartService;
import com.marineindustryproj.domain.OrganizationChart;
import com.marineindustryproj.repository.OrganizationChartRepository;
import com.marineindustryproj.service.PersonQueryService;
import com.marineindustryproj.service.PersonService;
import com.marineindustryproj.service.dto.OrganizationChartDTO;
import com.marineindustryproj.service.dto.PersonCriteria;
import com.marineindustryproj.service.dto.PersonDTO;
import com.marineindustryproj.service.mapper.OrganizationChartMapper;
import io.github.jhipster.service.filter.LongFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing OrganizationChart.
 */
@Service
@Transactional
public class OrganizationChartServiceImpl implements OrganizationChartService {

    private final Logger log = LoggerFactory.getLogger(OrganizationChartServiceImpl.class);

    private final OrganizationChartRepository organizationChartRepository;

    private final OrganizationChartMapper organizationChartMapper;

    private final PersonQueryService personQueryService;

    private final PersonService personService;

    private final CacheManager cacheManager;

    public OrganizationChartServiceImpl(OrganizationChartRepository organizationChartRepository,
                                        OrganizationChartMapper organizationChartMapper,
                                        PersonQueryService personQueryService, PersonService personService, CacheManager cacheManager) {
        this.organizationChartRepository = organizationChartRepository;
        this.organizationChartMapper = organizationChartMapper;
        this.personQueryService = personQueryService;
        this.personService = personService;
        this.cacheManager = cacheManager;
    }

    /**
     * Save a organizationChart.
     *
     * @param organizationChartDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public OrganizationChartDTO save(OrganizationChartDTO organizationChartDTO) {
        log.debug("Request to save OrganizationChart : {}", organizationChartDTO);

        OrganizationChart organizationChart = organizationChartMapper.toEntity(organizationChartDTO);
        organizationChart = organizationChartRepository.save(organizationChart);
        clearOrganizationChartCaches();
        return organizationChartMapper.toDto(organizationChart);
    }

    /**
     * Get all the organizationCharts.
     *
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<OrganizationChartDTO> findAll() {
        log.debug("Request to get all OrganizationCharts");
        return organizationChartRepository.findAll().stream()
            .map(organizationChartMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one organizationChart by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<OrganizationChartDTO> findOne(Long id) {
        log.debug("Request to get OrganizationChart : {}", id);
        return organizationChartRepository.findById(id)
            .map(organizationChartMapper::toDto);
    }

    /**
     * Delete the organizationChart by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete OrganizationChart : {}", id);
        PersonCriteria personCriteria = new PersonCriteria();
        LongFilter orgFilter = new LongFilter();
        orgFilter.setEquals(id);
        personCriteria.setOrganizationChartId(orgFilter);
        List<PersonDTO> personDTOS = personQueryService.findByCriteria(personCriteria);
        if(!personDTOS.isEmpty())
        {
            for (PersonDTO person: personDTOS) {
                person.setOrganizationChartId(null);
                personService.save(person, true);
            }
        }
        organizationChartRepository.deleteById(id);
        clearOrganizationChartCaches();
    }
    private void clearOrganizationChartCaches() {
        Objects.requireNonNull(cacheManager.getCache(organizationChartRepository.ALL_ORGANIZATIONCHART_CACHE)).clear();
    }

}
