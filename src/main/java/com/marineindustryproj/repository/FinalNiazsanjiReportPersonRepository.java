package com.marineindustryproj.repository;

import com.marineindustryproj.domain.FinalNiazsanjiReportPerson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the FinalNiazsanjiReportPerson entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FinalNiazsanjiReportPersonRepository extends JpaRepository<FinalNiazsanjiReportPerson, Long>, JpaSpecificationExecutor<FinalNiazsanjiReportPerson> {

    @Query(value = "select distinct final_niazsanji_report_person from FinalNiazsanjiReportPerson final_niazsanji_report_person left join fetch final_niazsanji_report_person.documents",
        countQuery = "select count(distinct final_niazsanji_report_person) from FinalNiazsanjiReportPerson final_niazsanji_report_person")
    Page<FinalNiazsanjiReportPerson> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct final_niazsanji_report_person from FinalNiazsanjiReportPerson final_niazsanji_report_person left join fetch final_niazsanji_report_person.documents")
    List<FinalNiazsanjiReportPerson> findAllWithEagerRelationships();

    @Query("select final_niazsanji_report_person from FinalNiazsanjiReportPerson final_niazsanji_report_person left join fetch final_niazsanji_report_person.documents where final_niazsanji_report_person.id =:id")
    Optional<FinalNiazsanjiReportPerson> findOneWithEagerRelationships(@Param("id") Long id);

}
