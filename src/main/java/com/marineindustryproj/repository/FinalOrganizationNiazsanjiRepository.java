package com.marineindustryproj.repository;

import com.marineindustryproj.domain.FinalOrganizationNiazsanji;
import com.marineindustryproj.service.dto.customs.CapitationReportModels.NiazsanjiOrganizationSummaryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the FinalOrganizationNiazsanji entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FinalOrganizationNiazsanjiRepository extends JpaRepository<FinalOrganizationNiazsanji, Long>, JpaSpecificationExecutor<FinalOrganizationNiazsanji> {

    @Query(value = "select distinct final_organization_niazsanji from FinalOrganizationNiazsanji final_organization_niazsanji left join fetch final_organization_niazsanji.people left join fetch final_organization_niazsanji.documents left join fetch final_organization_niazsanji.restrictions",
        countQuery = "select count(distinct final_organization_niazsanji) from FinalOrganizationNiazsanji final_organization_niazsanji")
    Page<FinalOrganizationNiazsanji> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct final_organization_niazsanji from FinalOrganizationNiazsanji final_organization_niazsanji left join fetch final_organization_niazsanji.people left join fetch final_organization_niazsanji.documents left join fetch final_organization_niazsanji.restrictions")
    List<FinalOrganizationNiazsanji> findAllWithEagerRelationships();

    @Query("select final_organization_niazsanji from FinalOrganizationNiazsanji final_organization_niazsanji left join fetch final_organization_niazsanji.people left join fetch final_organization_niazsanji.documents left join fetch final_organization_niazsanji.restrictions where final_organization_niazsanji.id =:id")
    Optional<FinalOrganizationNiazsanji> findOneWithEagerRelationships(@Param("id") Long id);

    /*@Query(value = "select new com.marineindustryproj.service.dto.customs.CapitationReportModels.NiazsanjiOrganizationSummaryDTO(final_organization_niazsanji.niazsanjiYear, " +
        "final_organization_niazsanji.priceCost, final_organization_niazsanji.status, final_organization_niazsanji.requestStatus, " +
        "final_organization_niazsanji.courseType, final_organization_niazsanji.educationalModule, final_organization_niazsanji.organizationChart) from FinalOrganizationNiazsanji final_organization_niazsanji " +
        "left join fetch final_organization_niazsanji.people")
    List<NiazsanjiOrganizationSummaryDTO> findAllSummary();*/
}
