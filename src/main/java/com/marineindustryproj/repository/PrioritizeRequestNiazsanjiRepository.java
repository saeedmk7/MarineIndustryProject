package com.marineindustryproj.repository;

import com.marineindustryproj.domain.PrioritizeRequestNiazsanji;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the PrioritizeRequestNiazsanji entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PrioritizeRequestNiazsanjiRepository extends JpaRepository<PrioritizeRequestNiazsanji, Long>, JpaSpecificationExecutor<PrioritizeRequestNiazsanji> {

    @Query(value = "select distinct prioritize_request_niazsanji from PrioritizeRequestNiazsanji prioritize_request_niazsanji left join fetch prioritize_request_niazsanji.documents left join fetch prioritize_request_niazsanji.restrictions",
        countQuery = "select count(distinct prioritize_request_niazsanji) from PrioritizeRequestNiazsanji prioritize_request_niazsanji")
    Page<PrioritizeRequestNiazsanji> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct prioritize_request_niazsanji from PrioritizeRequestNiazsanji prioritize_request_niazsanji left join fetch prioritize_request_niazsanji.documents left join fetch prioritize_request_niazsanji.restrictions")
    List<PrioritizeRequestNiazsanji> findAllWithEagerRelationships();

    @Query("select prioritize_request_niazsanji from PrioritizeRequestNiazsanji prioritize_request_niazsanji left join fetch prioritize_request_niazsanji.documents left join fetch prioritize_request_niazsanji.restrictions where prioritize_request_niazsanji.id =:id")
    Optional<PrioritizeRequestNiazsanji> findOneWithEagerRelationships(@Param("id") Long id);

}
