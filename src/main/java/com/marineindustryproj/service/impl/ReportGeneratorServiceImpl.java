package com.marineindustryproj.service.impl;

import com.marineindustryproj.domain.ReportGeneratorAuthority;
import com.marineindustryproj.repository.ReportGeneratorAuthorityRepository;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.ReportGeneratorAuthorityService;
import com.marineindustryproj.service.ReportGeneratorQueryService;
import com.marineindustryproj.service.ReportGeneratorService;
import com.marineindustryproj.domain.ReportGenerator;
import com.marineindustryproj.repository.ReportGeneratorRepository;
import com.marineindustryproj.service.dto.ReportGeneratorCriteria;
import com.marineindustryproj.service.dto.ReportGeneratorDTO;
import com.marineindustryproj.service.mapper.ReportGeneratorMapper;
import io.github.jhipster.service.filter.StringFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service Implementation for managing ReportGenerator.
 */
@Service
@Transactional
public class ReportGeneratorServiceImpl implements ReportGeneratorService {

    private final Logger log = LoggerFactory.getLogger(ReportGeneratorServiceImpl.class);

    private final ReportGeneratorRepository reportGeneratorRepository;

    private final ReportGeneratorQueryService reportGeneratorQueryService;

    private final ReportGeneratorMapper reportGeneratorMapper;

    private final ReportGeneratorAuthorityService reportGeneratorAuthorityService;

    private final ReportGeneratorAuthorityRepository reportGeneratorAuthorityRepository;

    public ReportGeneratorServiceImpl(ReportGeneratorRepository reportGeneratorRepository,
                                      ReportGeneratorQueryService reportGeneratorQueryService,
                                      ReportGeneratorMapper reportGeneratorMapper,
                                      ReportGeneratorAuthorityService reportGeneratorAuthorityService,
                                      ReportGeneratorAuthorityRepository reportGeneratorAuthorityRepository) {
        this.reportGeneratorRepository = reportGeneratorRepository;
        this.reportGeneratorQueryService = reportGeneratorQueryService;
        this.reportGeneratorMapper = reportGeneratorMapper;
        this.reportGeneratorAuthorityService = reportGeneratorAuthorityService;
        this.reportGeneratorAuthorityRepository = reportGeneratorAuthorityRepository;
    }

    /**
     * Save a reportGenerator.
     *
     * @param reportGeneratorDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ReportGeneratorDTO save(ReportGeneratorDTO reportGeneratorDTO) {
        log.debug("Request to save ReportGenerator : {}", reportGeneratorDTO);

        boolean isEdit = false;
        if(reportGeneratorDTO.getId() != null)
            isEdit = true;
        ReportGenerator reportGenerator = reportGeneratorMapper.toEntity(reportGeneratorDTO);
        reportGenerator = reportGeneratorRepository.save(reportGenerator);

        if(!reportGeneratorDTO.getAuthorityNames().isEmpty())
        {

            String[] splitedValues = reportGeneratorDTO.getAuthorityNames().split(",");
            if(splitedValues.length > 0){
                if(isEdit)
                    reportGeneratorAuthorityService.deleteByReportGeneratorId(reportGenerator.getId());
                //jamHelpRepository
                for (String authority: splitedValues) {
                    ReportGeneratorAuthority reportGeneratorAuthority = new ReportGeneratorAuthority();
                    reportGeneratorAuthority.setAuthorityName(authority);
                    reportGeneratorAuthority.setCreateDate(ZonedDateTime.now());
                    reportGeneratorAuthority.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
                    reportGeneratorAuthority.setHasEditPermission(true);
                    reportGeneratorAuthority.setReportGenerator(reportGenerator);
                    reportGeneratorAuthorityRepository.save(reportGeneratorAuthority);
                }
            }
        }


        return reportGeneratorMapper.toDto(reportGenerator);
    }

    /**
     * Get all the reportGenerators.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ReportGeneratorDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ReportGenerators");
        return reportGeneratorRepository.findAll(pageable)
            .map(reportGeneratorMapper::toDto);
    }

    /**
     * Get all the ReportGenerator with eager load of many-to-many relationships.
     *
     * @return the list of entities
     */
    public Page<ReportGeneratorDTO> findAllWithEagerRelationships(Pageable pageable) {
        return reportGeneratorRepository.findAllWithEagerRelationships(pageable).map(reportGeneratorMapper::toDto);
    }
    

    /**
     * Get one reportGenerator by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ReportGeneratorDTO> findOne(Long id) {
        log.debug("Request to get ReportGenerator : {}", id);
        return reportGeneratorRepository.findOneWithEagerRelationships(id)
            .map(reportGeneratorMapper::toDto);
    }
    @Override
    @Transactional(readOnly = true)
    public Optional<ReportGeneratorDTO> findOneByGuid(String guid) {
        log.debug("Request to get ReportGenerator : {}", guid);
        ReportGeneratorCriteria reportGeneratorCriteria = new ReportGeneratorCriteria();
        StringFilter guidFilter = new StringFilter();
        guidFilter.setEquals(guid);
        reportGeneratorCriteria.setGuid(guidFilter);
        List<ReportGeneratorDTO> reportGeneratorDTOS =
            reportGeneratorQueryService.findByCriteria(reportGeneratorCriteria);
        if(!reportGeneratorDTOS.isEmpty())
        {
            return Optional.of(reportGeneratorDTOS.get(0));
        }
        return null;
    }

    /**
     * Delete the reportGenerator by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ReportGenerator : {}", id);
        reportGeneratorRepository.deleteById(id);
    }
}
