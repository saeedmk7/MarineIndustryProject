package com.marineindustryproj.repository;

import com.marineindustryproj.domain.MatchingEducationalRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the MatchingEducationalRecord entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MatchingEducationalRecordRepository extends JpaRepository<MatchingEducationalRecord, Long>, JpaSpecificationExecutor<MatchingEducationalRecord> {

    @Query(value = "select distinct matching_educational_record from MatchingEducationalRecord matching_educational_record left join fetch matching_educational_record.skillableLevelOfSkills left join fetch matching_educational_record.documents",
        countQuery = "select count(distinct matching_educational_record) from MatchingEducationalRecord matching_educational_record")
    Page<MatchingEducationalRecord> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct matching_educational_record from MatchingEducationalRecord matching_educational_record left join fetch matching_educational_record.skillableLevelOfSkills left join fetch matching_educational_record.documents")
    List<MatchingEducationalRecord> findAllWithEagerRelationships();

    @Query("select matching_educational_record from MatchingEducationalRecord matching_educational_record left join fetch matching_educational_record.skillableLevelOfSkills left join fetch matching_educational_record.documents where matching_educational_record.id =:id")
    Optional<MatchingEducationalRecord> findOneWithEagerRelationships(@Param("id") Long id);

}
