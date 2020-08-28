package com.marineindustryproj.service;

import com.marineindustryproj.service.dto.FinalNiazsanjiReportPersonDTO;

import com.marineindustryproj.service.dto.customs.CountListModel;
import com.marineindustryproj.service.dto.customs.FinalNiazsanjiPeopleListModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing FinalNiazsanjiReportPerson.
 */
public interface FinalNiazsanjiReportPersonService {

    /**
     * Save a finalNiazsanjiReportPerson.
     *
     * @param finalNiazsanjiReportPersonDTO the entity to save
     * @return the persisted entity
     */
    FinalNiazsanjiReportPersonDTO save(FinalNiazsanjiReportPersonDTO finalNiazsanjiReportPersonDTO);

    /**
     * Get all the finalNiazsanjiReportPeople.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<FinalNiazsanjiReportPersonDTO> findAll(Pageable pageable);


    /**
     * Get the "id" finalNiazsanjiReportPerson.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<FinalNiazsanjiReportPersonDTO> findOne(Long id);

    /**
     * Delete the "id" finalNiazsanjiReportPerson.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Delete the "id" finalNiazsanjiReportPerson.
     *
     * @param finalNiazsanjiReportId the id of the entity
     */
    void deleteByFinalNiazsanjiReportId(Long finalNiazsanjiReportId);

    List<CountListModel> countListFinalNiazsanjiReportPeople(long[] finalNiazsanjiReportIds);

    List<FinalNiazsanjiPeopleListModel> getFinalNiazsanjiReportPeopleList(long[] finalNiazsanjiReportIds);
}
