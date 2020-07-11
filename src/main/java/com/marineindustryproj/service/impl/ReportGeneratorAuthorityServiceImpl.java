package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.ReportGeneratorAuthorityQueryService;
import com.marineindustryproj.service.ReportGeneratorAuthorityService;
import com.marineindustryproj.domain.ReportGeneratorAuthority;
import com.marineindustryproj.repository.ReportGeneratorAuthorityRepository;
import com.marineindustryproj.service.dto.ReportGeneratorAuthorityCriteria;
import com.marineindustryproj.service.dto.ReportGeneratorAuthorityDTO;
import com.marineindustryproj.service.mapper.ReportGeneratorAuthorityMapper;
import io.github.jhipster.service.filter.LongFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing ReportGeneratorAuthority.
 */
@Service
@Transactional
public class ReportGeneratorAuthorityServiceImpl implements ReportGeneratorAuthorityService {

    private final Logger log = LoggerFactory.getLogger(ReportGeneratorAuthorityServiceImpl.class);

    private final ReportGeneratorAuthorityRepository reportGeneratorAuthorityRepository;

    private final ReportGeneratorAuthorityMapper reportGeneratorAuthorityMapper;

    private final ReportGeneratorAuthorityQueryService reportGeneratorAuthorityQueryService;

    public ReportGeneratorAuthorityServiceImpl(ReportGeneratorAuthorityRepository reportGeneratorAuthorityRepository, ReportGeneratorAuthorityMapper reportGeneratorAuthorityMapper, ReportGeneratorAuthorityQueryService reportGeneratorAuthorityQueryService) {
        this.reportGeneratorAuthorityRepository = reportGeneratorAuthorityRepository;
        this.reportGeneratorAuthorityMapper = reportGeneratorAuthorityMapper;
        this.reportGeneratorAuthorityQueryService = reportGeneratorAuthorityQueryService;
    }

    /**
     * Save a reportGeneratorAuthority.
     *
     * @param reportGeneratorAuthorityDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public ReportGeneratorAuthorityDTO save(ReportGeneratorAuthorityDTO reportGeneratorAuthorityDTO) {
        log.debug("Request to save ReportGeneratorAuthority : {}", reportGeneratorAuthorityDTO);

        ReportGeneratorAuthority reportGeneratorAuthority = reportGeneratorAuthorityMapper.toEntity(reportGeneratorAuthorityDTO);
        reportGeneratorAuthority = reportGeneratorAuthorityRepository.save(reportGeneratorAuthority);
        return reportGeneratorAuthorityMapper.toDto(reportGeneratorAuthority);
    }

    /**
     * Get all the reportGeneratorAuthorities.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<ReportGeneratorAuthorityDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ReportGeneratorAuthorities");
        return reportGeneratorAuthorityRepository.findAll(pageable)
            .map(reportGeneratorAuthorityMapper::toDto);
    }


    /**
     * Get one reportGeneratorAuthority by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<ReportGeneratorAuthorityDTO> findOne(Long id) {
        log.debug("Request to get ReportGeneratorAuthority : {}", id);
        return reportGeneratorAuthorityRepository.findById(id)
            .map(reportGeneratorAuthorityMapper::toDto);
    }

    /**
     * Delete the reportGeneratorAuthority by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete ReportGeneratorAuthority : {}", id);
        reportGeneratorAuthorityRepository.deleteById(id);
    }

    @Override
    public void deleteByReportGeneratorId(Long id) {
        log.debug("Request to delete ReportGeneratorAuthority : {}", id);

        ReportGeneratorAuthorityCriteria reportGeneratorAuthorityCriteria = new ReportGeneratorAuthorityCriteria();
        LongFilter longFilter = new LongFilter();
        longFilter.setEquals(id);
        reportGeneratorAuthorityCriteria.setReportGeneratorId(longFilter);
        List<ReportGeneratorAuthorityDTO> reportGeneratorAuthorityDTOList =
            reportGeneratorAuthorityQueryService.findByCriteria(reportGeneratorAuthorityCriteria);
        if (!reportGeneratorAuthorityDTOList.isEmpty()) {
            for (ReportGeneratorAuthorityDTO reportGeneratorAuthority : reportGeneratorAuthorityDTOList) {
                reportGeneratorAuthorityRepository.deleteById(reportGeneratorAuthority.getId());
            }
        }
    }
}
