package com.marineindustryproj.repository;

import com.marineindustryproj.domain.NiazsanjiFardi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the NiazsanjiFardi entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NiazsanjiFardiRepository extends JpaRepository<NiazsanjiFardi, Long>, JpaSpecificationExecutor<NiazsanjiFardi> {

    @Query(value = "select distinct niazsanji_fardi from NiazsanjiFardi niazsanji_fardi left join fetch niazsanji_fardi.documents",
        countQuery = "select count(distinct niazsanji_fardi) from NiazsanjiFardi niazsanji_fardi")
    Page<NiazsanjiFardi> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct niazsanji_fardi from NiazsanjiFardi niazsanji_fardi left join fetch niazsanji_fardi.documents")
    List<NiazsanjiFardi> findAllWithEagerRelationships();

    @Query("select niazsanji_fardi from NiazsanjiFardi niazsanji_fardi left join fetch niazsanji_fardi.documents where niazsanji_fardi.id =:id")
    Optional<NiazsanjiFardi> findOneWithEagerRelationships(@Param("id") Long id);

}
