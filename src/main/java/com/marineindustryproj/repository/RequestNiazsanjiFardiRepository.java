package com.marineindustryproj.repository;

import com.marineindustryproj.domain.RequestNiazsanjiFardi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the RequestNiazsanjiFardi entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RequestNiazsanjiFardiRepository extends JpaRepository<RequestNiazsanjiFardi, Long>, JpaSpecificationExecutor<RequestNiazsanjiFardi> {

    @Query(value = "select distinct request_niazsanji_fardi from RequestNiazsanjiFardi request_niazsanji_fardi left join fetch request_niazsanji_fardi.documents",
        countQuery = "select count(distinct request_niazsanji_fardi) from RequestNiazsanjiFardi request_niazsanji_fardi")
    Page<RequestNiazsanjiFardi> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct request_niazsanji_fardi from RequestNiazsanjiFardi request_niazsanji_fardi left join fetch request_niazsanji_fardi.documents")
    List<RequestNiazsanjiFardi> findAllWithEagerRelationships();

    @Query("select request_niazsanji_fardi from RequestNiazsanjiFardi request_niazsanji_fardi left join fetch request_niazsanji_fardi.documents where request_niazsanji_fardi.id =:id")
    Optional<RequestNiazsanjiFardi> findOneWithEagerRelationships(@Param("id") Long id);

}
