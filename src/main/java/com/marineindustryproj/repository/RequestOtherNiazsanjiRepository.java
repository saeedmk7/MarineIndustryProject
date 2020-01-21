package com.marineindustryproj.repository;

import com.marineindustryproj.domain.RequestOtherNiazsanji;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the RequestOtherNiazsanji entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RequestOtherNiazsanjiRepository extends JpaRepository<RequestOtherNiazsanji, Long>, JpaSpecificationExecutor<RequestOtherNiazsanji> {

    @Query(value = "select distinct request_other_niazsanji from RequestOtherNiazsanji request_other_niazsanji left join fetch request_other_niazsanji.documents left join fetch request_other_niazsanji.restrictions",
        countQuery = "select count(distinct request_other_niazsanji) from RequestOtherNiazsanji request_other_niazsanji")
    Page<RequestOtherNiazsanji> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct request_other_niazsanji from RequestOtherNiazsanji request_other_niazsanji left join fetch request_other_niazsanji.documents left join fetch request_other_niazsanji.restrictions")
    List<RequestOtherNiazsanji> findAllWithEagerRelationships();

    @Query("select request_other_niazsanji from RequestOtherNiazsanji request_other_niazsanji left join fetch request_other_niazsanji.documents left join fetch request_other_niazsanji.restrictions where request_other_niazsanji.id =:id")
    Optional<RequestOtherNiazsanji> findOneWithEagerRelationships(@Param("id") Long id);

}
