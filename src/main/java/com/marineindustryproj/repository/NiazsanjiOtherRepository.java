package com.marineindustryproj.repository;

import com.marineindustryproj.domain.NiazsanjiOther;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the NiazsanjiOther entity.
 */
@SuppressWarnings("unused")
@Repository
public interface NiazsanjiOtherRepository extends JpaRepository<NiazsanjiOther, Long>, JpaSpecificationExecutor<NiazsanjiOther> {

    @Query(value = "select distinct niazsanji_other from NiazsanjiOther niazsanji_other left join fetch niazsanji_other.documents left join fetch niazsanji_other.restrictions",
        countQuery = "select count(distinct niazsanji_other) from NiazsanjiOther niazsanji_other")
    Page<NiazsanjiOther> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct niazsanji_other from NiazsanjiOther niazsanji_other left join fetch niazsanji_other.documents left join fetch niazsanji_other.restrictions")
    List<NiazsanjiOther> findAllWithEagerRelationships();

    @Query("select niazsanji_other from NiazsanjiOther niazsanji_other left join fetch niazsanji_other.documents left join fetch niazsanji_other.restrictions where niazsanji_other.id =:id")
    Optional<NiazsanjiOther> findOneWithEagerRelationships(@Param("id") Long id);

}
