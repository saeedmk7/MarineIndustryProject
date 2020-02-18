package com.marineindustryproj.repository;

import com.marineindustryproj.domain.EvaluateCriteriaTraining;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the EvaluateCriteriaTraining entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EvaluateCriteriaTrainingRepository extends JpaRepository<EvaluateCriteriaTraining, Long>, JpaSpecificationExecutor<EvaluateCriteriaTraining> {

    @Query(value = "select distinct evaluate_criteria_training from EvaluateCriteriaTraining evaluate_criteria_training left join fetch evaluate_criteria_training.documents",
        countQuery = "select count(distinct evaluate_criteria_training) from EvaluateCriteriaTraining evaluate_criteria_training")
    Page<EvaluateCriteriaTraining> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct evaluate_criteria_training from EvaluateCriteriaTraining evaluate_criteria_training left join fetch evaluate_criteria_training.documents")
    List<EvaluateCriteriaTraining> findAllWithEagerRelationships();

    @Query("select evaluate_criteria_training from EvaluateCriteriaTraining evaluate_criteria_training left join fetch evaluate_criteria_training.documents where evaluate_criteria_training.id =:id")
    Optional<EvaluateCriteriaTraining> findOneWithEagerRelationships(@Param("id") Long id);

}
