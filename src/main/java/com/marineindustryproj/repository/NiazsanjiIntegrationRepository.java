package com.marineindustryproj.repository;

import com.marineindustryproj.domain.NiazsanjiIntegration;
import com.marineindustryproj.service.dto.customs.CapitationReportModels.NiazsanjiFardiSummaryDTO;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Spring Data  repository for the NiazsanjiIntegration entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NiazsanjiIntegrationRepository extends JpaRepository<NiazsanjiIntegration, Long>, JpaSpecificationExecutor<NiazsanjiIntegration> {
    @Query(value = "select new com.marineindustryproj.service.dto.customs.CapitationReportModels.NiazsanjiFardiSummaryDTO(niazsanji_integration.niazsanjiYear, niazsanji_integration.requestStatus, niazsanji_integration.prioritizeRequestNiazsanji) from NiazsanjiIntegration niazsanji_integration where niazsanji_integration.requestStatus <> 'IGNIRE' and niazsanji_integration.niazsanjiYear = :niazsanjiYear")
    List<NiazsanjiFardiSummaryDTO> findAllSummaryByNiazsanjiYear(@Param("niazsanjiYear") Integer niazsanjiYear);
}
