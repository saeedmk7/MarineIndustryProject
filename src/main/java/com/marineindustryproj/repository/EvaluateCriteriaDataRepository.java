package com.marineindustryproj.repository;

import com.marineindustryproj.domain.EvaluateCriteriaData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the EvaluateCriteriaData entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EvaluateCriteriaDataRepository extends JpaRepository<EvaluateCriteriaData, Long>, JpaSpecificationExecutor<EvaluateCriteriaData> {

    @Query(value = "select distinct evaluate_criteria_data from EvaluateCriteriaData evaluate_criteria_data left join fetch evaluate_criteria_data.documents",
        countQuery = "select count(distinct evaluate_criteria_data) from EvaluateCriteriaData evaluate_criteria_data")
    Page<EvaluateCriteriaData> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct evaluate_criteria_data from EvaluateCriteriaData evaluate_criteria_data left join fetch evaluate_criteria_data.documents")
    List<EvaluateCriteriaData> findAllWithEagerRelationships();

    @Query("select evaluate_criteria_data from EvaluateCriteriaData evaluate_criteria_data left join fetch evaluate_criteria_data.documents where evaluate_criteria_data.id =:id")
    Optional<EvaluateCriteriaData> findOneWithEagerRelationships(@Param("id") Long id);

}
