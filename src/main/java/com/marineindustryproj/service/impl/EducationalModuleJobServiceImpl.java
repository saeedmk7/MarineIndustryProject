package com.marineindustryproj.service.impl;

import com.marineindustryproj.service.EducationalModuleJobQueryService;
import com.marineindustryproj.service.EducationalModuleJobService;
import com.marineindustryproj.domain.EducationalModuleJob;
import com.marineindustryproj.repository.EducationalModuleJobRepository;
import com.marineindustryproj.service.dto.EducationalModuleJobCriteria;
import com.marineindustryproj.service.dto.EducationalModuleJobDTO;
import com.marineindustryproj.service.mapper.EducationalModuleJobMapper;
import io.github.jhipster.service.filter.LongFilter;
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
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.FlushModeType;
import javax.swing.text.html.parser.Entity;

/**
 * Service Implementation for managing EducationalModuleJob.
 */
@Service
@Transactional
public class EducationalModuleJobServiceImpl implements EducationalModuleJobService {

    private final Logger log = LoggerFactory.getLogger(EducationalModuleJobServiceImpl.class);

    private final EducationalModuleJobRepository educationalModuleJobRepository;

    private final EducationalModuleJobMapper educationalModuleJobMapper;

    private final EducationalModuleJobQueryService educationalModuleJobQueryService;

    public EducationalModuleJobServiceImpl(EducationalModuleJobRepository educationalModuleJobRepository,
                                           EducationalModuleJobMapper educationalModuleJobMapper,
                                           EducationalModuleJobQueryService educationalModuleJobQueryService) {
        this.educationalModuleJobRepository = educationalModuleJobRepository;
        this.educationalModuleJobMapper = educationalModuleJobMapper;
        this.educationalModuleJobQueryService = educationalModuleJobQueryService;
    }

    /**
     * Save a educationalModuleJob.
     *
     * @param educationalModuleJobDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public EducationalModuleJobDTO save(EducationalModuleJobDTO educationalModuleJobDTO) {
        log.debug("Request to save EducationalModuleJob : {}", educationalModuleJobDTO);

        EducationalModuleJob educationalModuleJob = educationalModuleJobMapper.toEntity(educationalModuleJobDTO);
        educationalModuleJob = educationalModuleJobRepository.save(educationalModuleJob);
        return educationalModuleJobMapper.toDto(educationalModuleJob);
    }
    /**
     * Save a educationalModuleJob.
     *
     * @param educationalModuleJobDTOS the entity to save
     * @return the persisted entity
     */
    @Override
    public EducationalModuleJobDTO bulkSave(List<EducationalModuleJobDTO> educationalModuleJobDTOS) {

        List<Long> jobIds = educationalModuleJobDTOS.stream().map(a -> a.getJobId()).distinct().collect(Collectors.toList());
        for (Long jobId : jobIds) {
            EducationalModuleJobCriteria criteria = new EducationalModuleJobCriteria();
            LongFilter longFilter = new LongFilter();
            longFilter.setEquals(jobId);
            criteria.setJobId(longFilter);

            final Specification<EducationalModuleJob> specification = educationalModuleJobQueryService.createSpecification(criteria);
            List<EducationalModuleJob> educationalModuleJobs = educationalModuleJobRepository.findAll(specification,
                                                                                                      PageRequest.of(0, Integer.MAX_VALUE)).getContent();
            //List<EducationalModuleJobDTO> educationalModuleJobDTOS1 = educationalModuleJobDTOS.stream().filter(a -> a.getJobId().equals(jobId)).collect(Collectors.toList());
            for (EducationalModuleJob educationalModuleJob : educationalModuleJobs){
                if(educationalModuleJobDTOS.stream().anyMatch(a ->
                                                                  a.getEducationalModuleId().equals(educationalModuleJob.getEducationalModule().getId())
                                                                    && a.getJobId().equals(educationalModuleJob.getJob().getId())))
                {
                    educationalModuleJobDTOS.remove(
                        educationalModuleJobDTOS.stream().filter(a -> a.getEducationalModuleId().equals(educationalModuleJob.getEducationalModule().getId())
                            && a.getJobId().equals(educationalModuleJob.getJob().getId())).findFirst().get());
                }
            }
        }

        EducationalModuleJobDTO returnEducationalModuleJobDTO = new EducationalModuleJobDTO();
        for (EducationalModuleJobDTO educationalModuleJobDTO : educationalModuleJobDTOS) {
            returnEducationalModuleJobDTO = save(educationalModuleJobDTO);
        }

        return returnEducationalModuleJobDTO;
    }

    /**
     * Get all the educationalModuleJobs.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<EducationalModuleJobDTO> findAll(Pageable pageable) {
        log.debug("Request to get all EducationalModuleJobs");
        return educationalModuleJobRepository.findAll(pageable)
            .map(educationalModuleJobMapper::toDto);
    }


    /**
     * Get one educationalModuleJob by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<EducationalModuleJobDTO> findOne(Long id) {
        log.debug("Request to get EducationalModuleJob : {}", id);
        return educationalModuleJobRepository.findById(id)
            .map(educationalModuleJobMapper::toDto);
    }

    /**
     * Delete the educationalModuleJob by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete EducationalModuleJob : {}", id);
        educationalModuleJobRepository.deleteById(id);
    }

    @Override
    public void deleteByJobId(Long jobId) {
        log.debug("Request to delete EducationalModuleJob by jobId : {}", jobId);

        EducationalModuleJobCriteria criteria = new EducationalModuleJobCriteria();
        LongFilter longFilter = new LongFilter();
        longFilter.setEquals(jobId);
        criteria.setJobId(longFilter);

        final Specification<EducationalModuleJob> specification = educationalModuleJobQueryService.createSpecification(criteria);
        List<EducationalModuleJob> educationalModuleJobs = educationalModuleJobRepository.findAll(specification,
                                                                                                  PageRequest.of(0, Integer.MAX_VALUE)).getContent();
        /*educationalModuleJobs.forEach((a) -> {
            educationalModuleJobRepository.delete(a);
        });*/
        educationalModuleJobRepository.deleteInBatch(educationalModuleJobs);
    }
}
